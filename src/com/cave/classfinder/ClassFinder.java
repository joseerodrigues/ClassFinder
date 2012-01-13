package com.cave.classfinder;

import com.cave.util.filefinder.FileFinder;
import com.cave.util.filefinder.FileFoundListener;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Enumeration;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author José Rodrigues
 */
public final class ClassFinder {

    private static final JavaResourcesFileFilter javaFileFilter = new JavaResourcesFileFilter();
    private static final int BUFFER_SIZE = 8192;
    
    private ClassFinder() {
    }

    private static void _findClasses(File file, ClassFoundListener listener, String pathPrefix) {
        try {
            JarFile jf = new JarFile(file);

            Enumeration<JarEntry> entries = jf.entries();

            while (entries.hasMoreElements()) {
                JarEntry entry = entries.nextElement();

                if (entry.isDirectory()) {
                    continue;
                }

                String entryName = entry.getName();
                int extIdx = entryName.lastIndexOf(".");

                if (extIdx != -1) {
                    String entryExt = entryName.substring(extIdx + 1);

                    if (entryExt.equalsIgnoreCase("class")) {
                        //System.out.println("\t" + entry.getName());

                        int packageIdx = entryName.lastIndexOf("/");

                        String itemPackage = "";

                        if (packageIdx != -1) {
                            itemPackage = entryName.substring(0, packageIdx);
                        }

                        itemPackage = itemPackage.replace("/", ".");

                        String className = entryName.substring(packageIdx + 1);
                        className = className.substring(0, className.lastIndexOf("."));


                        ClassItem ci = new ClassItem(jf, entry);

                        ci.setItemPackage(itemPackage);
                        ci.setName(className);
                        ci.setResFileName(file.getName());

                        String path = file.getAbsolutePath();
                        if (pathPrefix != null) {
                            path = pathPrefix + File.separator + file.getName();
                        }

                        //System.out.println("PATH : " + path);
                        listener.classFound(path, ci);

                    } else if (javaFileFilter.acceptExtension(entryExt)) {
                        // processar os jars incluidos
                        File temp = File.createTempFile("cfinder_", null);

                        String newPath = temp.getAbsolutePath();

                        newPath = newPath.substring(0, newPath.lastIndexOf(File.separator) + 1) + new File(entryName).getName();

                        temp.delete();

                        temp = new File(newPath);
                                          

                        InputStream eis = jf.getInputStream(entry);
                        OutputStream fos = new FileOutputStream(temp);
                        
                        byte buff[] = new byte[BUFFER_SIZE];
                        int len = 0;

                        while ((len = eis.read(buff)) != -1) {
                            fos.write(buff, 0, len);
                        }

                        eis.close();
                        fos.close();

                        String pathEls[] = entryName.split("/");
                        String path = (pathPrefix != null) ? pathPrefix : file.getAbsolutePath();

                        if (pathEls.length > 1) {
                            for (int i = 0; i < pathEls.length - 1; i++) {
                                path = path + File.separator + pathEls[i];
                            }
                        }

                        //System.out.println("INNER PATH : " + path);
                        _findClasses(temp, listener, path);

                        temp.delete();
                    }
                }
            }

            jf.close();
        } catch (IOException ex) {
            Logger.getLogger(ClassFinder.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void findClasses(File file, final ClassFoundListener listener) {

        if (listener == null) {
            throw new NullPointerException("ClassFoundListener is null.");
        }

        if (file.isDirectory()) {

            FileFinder.findFiles(file, new FileFoundListener() {

                @Override
                public void fileFound(File file) {
                    _findClasses(file, listener, null);
                }
                
            }, javaFileFilter);

        } else if (javaFileFilter.accept(file)) {
            _findClasses(file, listener, null);
        } else {
            throw new IllegalArgumentException("Invalid file '" + file + "'. File argument must represent a directory or a valid java archive file.");
        }
    }

    public static void findClasses(String filePath, final ClassFoundListener listener) {

        if (filePath == null) {
            throw new NullPointerException("filePath is null.");
        }

        File startF = new File(filePath);

        findClasses(startF, listener);
    }
    
    public static void findClasses(File file, final ClassFoundListener listener, final ClassFoundFilter filter) {
        
        if (filter == null){
            throw new NullPointerException("filter is null.");            
        }
        
        ClassFoundListener dListener = new ClassFoundListener() {

            @Override
            public void classFound(String filePath, ClassItem classItem) {
                if (filter.accept(filePath, classItem)){
                    listener.classFound(filePath, classItem);
                }
            }
        };
        
        findClasses(file, dListener);
        
    }
    
    public static void findClasses(String filePath, final ClassFoundListener listener, final ClassFoundFilter filter) {
        
        if (filePath == null) {
            throw new NullPointerException("filePath is null.");
        }

        File startF = new File(filePath);
        
        findClasses(startF, listener, filter);
        
    }
}

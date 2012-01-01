
package com.cave.classfinder.main;

import com.cave.classfinder.ClassFinder;
import com.cave.classfinder.ClassFoundListener;
import com.cave.classfinder.ClassItem;
import com.cave.classfinder.JavaResourcesFileFilter;
import java.io.File;

/**
 *
 * @author José Rodrigues
 */
public class Main {

    static class PrintListener implements ClassFoundListener{

        @Override
        public void classFound(String filePath, ClassItem classItem) {
            System.out.println(classItem.getItemPackage() + "\t" + classItem.getName() + "\t" + classItem.getResFileName() + "\t" + filePath);
        }
        
    }
    
    private static ClassFoundListener myListener = new PrintListener();
        
    public static void main(String args[]) {

        String searchDirs[] = null;
        String searchTerm = null;
        
        if (args.length < 1){
           
            System.err.println();
            System.err.println("Invalid number of arguments.");
            System.err.println("Use with : [-all] [class or package name] [searchDir01] [searchDir02] ...");
            System.err.println();
            System.err.println("If no searchDir is specified, the current directory will be used.");            
            System.err.println();
            
            System.exit(1);
            
        }else if (args.length >= 1){
            
            searchTerm = args[0];
            
            if (args.length == 1){
                searchDirs = new String[]{"."};
            }else{
                searchDirs = new String[args.length - 1];
                System.arraycopy(args, 1, searchDirs, 0, searchDirs.length);
            }                        
        }
        
        if (searchTerm.equalsIgnoreCase("-all")){
            searchTerm = null;
        }
        
        JavaResourcesFileFilter filter = new JavaResourcesFileFilter();
        
        for (String dir : searchDirs){
            
            File f = new File(dir);
            
            if (!f.exists()){
                
                System.err.println("[" + dir + "] does not exist as a file or directory.");
                
            }else if (f.isDirectory() || filter.accept(f)) {
                
                if (searchTerm != null){
                    ClassFinder.findClasses(f, myListener, new SearchTermFilter(searchTerm));
                }else{
                    ClassFinder.findClasses(f, myListener);
                }
                
                
            }else{
                System.err.println("[" + dir + "] is not a directory or a valid java archive file.");
            }            
        }        
    }
}

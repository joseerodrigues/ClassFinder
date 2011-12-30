
package com.cave.classfinder;

import java.io.IOException;
import java.io.InputStream;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

/**
 *
 * @author José Rodrigues
 */
public class ClassItem {
        
    private String name = "";
    private String itemPackage = "";
    private String resFileName = "";

    private JarFile fromFile = null;
    private JarEntry jarEntry = null;
    
    ClassItem(JarFile file, JarEntry entry) {
        fromFile = file;
        jarEntry = entry;
    }
    
    public String getItemPackage() {
        return itemPackage;
    }

    public void setItemPackage(String itemPackage) {
        this.itemPackage = itemPackage;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getResFileName() {
        return resFileName;
    }

    public void setResFileName(String resFileName) {
        this.resFileName = resFileName;
    }      
    
    public InputStream getInputStream() throws IOException{
        return fromFile.getInputStream(jarEntry);
    }
}


package com.cave.classfinder;

import java.io.File;
import java.io.FileFilter;

/**
 *
 * @author José Rodrigues
 */
public class JavaResourcesFileFilter implements FileFilter {

    private static final String[] acceptedExtensions = {"jar", "war", "ear"};   

    public boolean acceptExtension(String ext){
        
        for (int i = 0; i < acceptedExtensions.length; i++) {
            if (ext != null && ext.equalsIgnoreCase(acceptedExtensions[i])){
                return true;
            }
        }
        
        return false;
    }
    
    @Override
    public boolean accept(File pathname) {

        if (pathname.isDirectory()) {
            return true;
        }

        String fname = pathname.getName();

        int sepIdx = fname.lastIndexOf(".");
        String ext = null;

        if (sepIdx != -1) {
            ext = fname.substring(sepIdx + 1);
        }

        return acceptExtension(ext);
    }
}

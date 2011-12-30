
package com.cave.util.filefinder;

import java.io.File;
import java.io.FileFilter;

/**
 * Recursive find files.
 * 
 * @author José Rodrigues
 */
public final class FileFinder {

    private FileFinder(){        
    }
    
    private static void _findFiles(File startDir, FileFoundListener listener, FileFilter filter) {        

        File[] files = startDir.listFiles(filter);

        for (int i = 0; i < files.length; i++) {

            File f = files[i];

            if (f.isDirectory()) {
                _findFiles(f, listener, filter);
            } else {
                listener.fileFound(f);
            }
        }
    }

    public static void findFiles(File startDir, FileFoundListener listener, FileFilter filter) {

        if (startDir == null) {
            throw new NullPointerException("startDir is null.");
        }
        
        if (listener == null) {
            throw new NullPointerException("FileFoundListener is null.");
        }
        
        if (!startDir.isDirectory()) {
            throw new IllegalArgumentException("Start Dir [" + startDir + "] is not a directory");
        }        

        _findFiles(startDir, listener, filter);
    }
    
    public static void findFiles(File startDir, FileFoundListener listener) {
        findFiles(startDir, listener, null);
    }
}

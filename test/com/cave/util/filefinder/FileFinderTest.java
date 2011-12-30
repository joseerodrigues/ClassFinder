
package com.cave.util.filefinder;

import com.cave.classfinder.test.util.AbstractTestingUtil;
import java.io.File;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author José Rodrigues
 */
public class FileFinderTest extends AbstractTestingUtil{
    
    private FileFoundListener dummyListener = new FileFoundListener() {

        @Override
        public void fileFound(File file) {
            
        }
    };
    
    public FileFinderTest() {
    }

    /**
     * Test of findFiles method, of class FileFinder.
     */
    @Test(expected=NullPointerException.class)
    public void nullDir() {        
        
        FileFinder.findFiles(null, null);       
    }
    
    @Test(expected=IllegalArgumentException.class)
    public void invalidDir() {
        
        File start = new File("");
        FileFinder.findFiles(start, dummyListener);       
    }
    
    @Test(expected=NullPointerException.class)
    public void invalidListener() {
        
        File roots[] = File.listRoots();
        
        if (roots.length == 0){
            fail("No Roots Available. Impossible to test.");
        }
        
        FileFinder.findFiles(roots[0], null);       
    }
}

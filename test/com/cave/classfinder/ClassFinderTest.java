
package com.cave.classfinder;

import com.cave.classfinder.test.util.AbstractTestingUtil;
import java.io.File;
import org.junit.Test;

/**
 *
 * @author José Rodrigues
 */
public class ClassFinderTest extends AbstractTestingUtil{
    
    private static final ClassFoundListener dummyListener = new ClassFoundListener() {

        @Override
        public void classFound(String filePath, ClassItem classItem) {
         
        }
    };
    
    public ClassFinderTest() {
    }

    /**
     * Test of findClasses method, of class ClassFinder.
     */
    @Test(expected=NullPointerException.class)
    public void nullListener() {
                
        ClassFinder.findClasses("", null);
        
    }

    @Test(expected=NullPointerException.class)
    public void nullDirString() {
                
        String dir = null;
        ClassFinder.findClasses(dir, dummyListener);
        
    }
    
    @Test(expected=NullPointerException.class)
    public void nullDirFile() {
                
        File f = null;
        ClassFinder.findClasses(f, dummyListener);
        
    }
}

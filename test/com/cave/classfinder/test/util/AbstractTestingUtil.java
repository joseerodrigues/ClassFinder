
package com.cave.classfinder.test.util;

import org.junit.Before;
import org.junit.Rule;
import org.junit.rules.TestName;

/**
 *
 * @author José Rodrigues
 */
public abstract class AbstractTestingUtil {
    @Rule public TestName testName = new TestName();
    
    @Before
    public void setUp() {
        System.out.println(this.getClass().getSimpleName() + "_" + testName.getMethodName());
    }
}

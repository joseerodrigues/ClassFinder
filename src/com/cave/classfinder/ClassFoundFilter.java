
package com.cave.classfinder;

/**
 *
 * @author José Rodrigues
 */
public interface ClassFoundFilter {
    public boolean accept(String filePath, ClassItem classItem);
}

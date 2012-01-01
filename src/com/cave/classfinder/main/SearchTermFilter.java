package com.cave.classfinder.main;

import com.cave.classfinder.ClassFoundFilter;
import com.cave.classfinder.ClassItem;

/**
 *
 * @author José Rodrigues
 */
public class SearchTermFilter implements ClassFoundFilter {

    private String searchTerm = null;

    public SearchTermFilter(String term) {

        if (term == null) {
            throw new NullPointerException("Search Term cannot be null");
        }

        searchTerm = term;
    }

    @Override
    public boolean accept(String filePath, ClassItem classItem) {
        
        String lTerm = searchTerm.toLowerCase();
        String lName = classItem.getName().toLowerCase();
        String lPackage = classItem.getItemPackage().toLowerCase();

        boolean found = lName.indexOf(lTerm) != -1 || lPackage.indexOf(lTerm) != -1;

        return found;
        
    }
}

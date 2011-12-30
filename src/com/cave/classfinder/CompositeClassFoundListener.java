
package com.cave.classfinder;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author José Rodrigues
 */
public class CompositeClassFoundListener implements ClassFoundListener{

    private List<ClassFoundListener> listeners = new ArrayList<ClassFoundListener>();

    public List<ClassFoundListener> getListeners() {
        return listeners;
    }

    public void setListeners(List<ClassFoundListener> listeners) {
        
        if (listeners == null){
            throw new NullPointerException("Listeners is null");
        }
        
        this.listeners = listeners;
    }
        
    @Override
    public void classFound(String filePath, ClassItem classItem) {
        
        List<ClassFoundListener> list = getListeners();
        
        if (list == null){
            throw new IllegalStateException("No Available Listeners [null]");
        }
        
        for (ClassFoundListener l : list){
            l.classFound(filePath, classItem);
        }
    }
    
}

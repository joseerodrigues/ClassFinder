
package com.cave.classfinder;

/**
 *
 * @author José Rodrigues
 */
public class ClassItem {
    
    
    private String name = "";
    private String itemPackage = "";
    private String resFileName = "";

    ClassItem() {
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
}

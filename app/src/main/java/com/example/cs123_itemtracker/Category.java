package com.example.cs123_itemtracker;

import io.realm.RealmObject;

public class Category extends RealmObject {

    private String categoryID;

    private String categoryName;

    private Boolean locationCategory;

    private Boolean itemTypeCat;

    public String getCategoryID() {
        return categoryID;
    }

    public void setCategoryID(String categoryID) {
        this.categoryID = categoryID;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public Boolean getItemTypeCat() {
        return itemTypeCat;
    }

    public void setItemTypeCat(Boolean itemTypeCat) {
        this.itemTypeCat = itemTypeCat;
    }

    public Boolean getLocationCategory() {
        return locationCategory;
    }

    public void setLocationCategory(Boolean locationCategory) {
        this.locationCategory = locationCategory;
    }

    @Override
    public String toString() {
        return "Category{" +
                "categoryID='" + categoryID + '\'' +
                ", categoryName='" + categoryName + '\'' +
                ", locationCategory=" + locationCategory +
                ", itemTypeCat=" + itemTypeCat +
                '}';
    }
}

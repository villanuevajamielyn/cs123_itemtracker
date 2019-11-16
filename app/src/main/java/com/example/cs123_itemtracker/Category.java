package com.example.cs123_itemtracker;

import java.util.ArrayList;

import io.realm.RealmObject;

public class Category extends RealmObject {

    public String categoryID;

    public String categoryName;

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
}

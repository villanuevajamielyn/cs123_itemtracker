package com.example.cs123_itemtracker;

import org.androidannotations.annotations.EBean;

import io.realm.Realm;
import io.realm.RealmResults;

@EBean
public class CategoryManagement {

    private Realm realm;

    public CategoryManagement(){
        realm = Realm.getDefaultInstance();
    }

    public void saveCreds(String UUID, String name, Boolean locType, Boolean itemCatType){
        Category c = new Category();
        c.setCategoryID(UUID);
        c.setCategoryName(name);
        c.setLocationCategory(locType);
        c.setItemTypeCat(itemCatType);
        realm.beginTransaction();
        try{
            realm.copyToRealmOrUpdate(c);
        }
        catch (Exception e){
            e.printStackTrace();
        }
        realm.commitTransaction();
    }

    public Category getCreds(String UUID){
        Category c = realm.where(Category.class)
                .equalTo("categoryID",UUID)
                .findFirst();
        return c;
    }

    public RealmResults<Category> findAll()
    {
        return realm.where(Category.class).findAll();
    }

    public void delete(Category c){
        realm.beginTransaction();
        c.deleteFromRealm();
        realm.commitTransaction();
    }

    public void close(){
        realm.close();
    }
}


package com.example.cs123_itemtracker;

import org.androidannotations.annotations.EBean;

import java.util.Date;
import java.util.UUID;

import io.realm.Realm;
import io.realm.RealmResults;


@EBean
public class CategoryManagement {

    private Realm realm;

    public CategoryManagement(){
        realm = Realm.getDefaultInstance();
    }

    public void saveCreds (String UUID, String name){
        Category c = new Category();
        c.setCategoryID(UUID);
        c.setCategoryName(name);
        realm.beginTransaction();
        try {
            realm.copyToRealmOrUpdate(c);
        } catch (Exception e){
            e.printStackTrace();
        }
        realm.commitTransaction();
    }

    public Item getCreds(String UUID){
        Item i = realm.where(Item.class)
                .equalTo("item_ID",UUID)
                .findFirst();
        return i;
    }

    public RealmResults<Item> findAll()
    {
        return realm.where(Item.class).findAll();
    }

    public void delete(Item i){
        realm.beginTransaction();
        i.deleteFromRealm();
        realm.commitTransaction();
    }

    public void close(){
        realm.close();
    }
}


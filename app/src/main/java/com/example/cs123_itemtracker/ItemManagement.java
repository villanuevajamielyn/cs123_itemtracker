package com.example.cs123_itemtracker;

import org.androidannotations.annotations.EBean;

import java.util.Date;
import java.util.UUID;

import io.realm.Realm;
import io.realm.RealmResults;


@EBean
public class ItemManagement {

    private Realm realm;

    public ItemManagement(){
        realm = Realm.getDefaultInstance();
    }

    public void saveCreds (String UUID, String name, String desc, String location, Double latitude, Double longitude){
        Item i = new Item();
        i.setItem_ID(UUID);
        i.setName(name);
        i.setDescription(desc);
        i.setLocation(location);
        i.setGpsPingLatitude(latitude);
        i.setGpsPingLongitude(longitude);
        i.setTimeStamp(System.currentTimeMillis()/1000);
        realm.beginTransaction();
        try {
            realm.copyToRealmOrUpdate(i);
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


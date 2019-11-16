package com.example.cs123_itemtracker;

import android.app.Application;

import org.androidannotations.annotations.EApplication;

import io.realm.Realm;

@EApplication
public class MyRealmApp extends Application {
    public void onCreate(){
        super.onCreate();
        Realm.init(this);
    }
}

package com.example.cs123_itemtracker;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.DisplayMetrics;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import io.realm.RealmResults;

@EActivity (R.layout.a_activity_main)
public class MainActivity extends AppCompatActivity {

    @ViewById
    RecyclerView recyclerView;

    @Bean
    ItemManagement im;

    @AfterViews
    public void create(){
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(this);
        mLayoutManager.setOrientation(RecyclerView.VERTICAL);
        recyclerView.setLayoutManager(mLayoutManager);

        RealmResults<Item> itemList = im.findAll();
        ItemAdapter a = new ItemAdapter(this, itemList);
        recyclerView.setAdapter(a);

        NavigationView nav = findViewById(R.id.nav_view);
        nav.setNavigationItemSelectedListener(new DrawerActivity(this));
    }

    @Click(R.id.fab)
    public void addItem(){
        AddItemActivity_.intent(this)
                .start();
    }
}

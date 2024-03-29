package com.example.cs123_itemtracker;

import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import android.view.View;

import androidx.core.view.GravityCompat;
import androidx.appcompat.app.ActionBarDrawerToggle;

import android.view.MenuItem;

import com.google.android.material.navigation.NavigationView;

import androidx.drawerlayout.widget.DrawerLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.Menu;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import java.util.UUID;

import io.realm.RealmResults;

@EActivity (R.layout.d_activity_category)
public class CategoryActivity extends AppCompatActivity {

    @ViewById
    RecyclerView recyclerView;

    public String categoryUUID;

    @Bean
    CategoryManagement cm;

    @AfterViews
    public void onCreate() {
        System.out.println(cm.findAll());
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(this);
        mLayoutManager.setOrientation(RecyclerView.VERTICAL);
        recyclerView.setLayoutManager(mLayoutManager);

        RealmResults<Category> catList = cm.findAll();
        CategoryAdapter a = new CategoryAdapter(this, catList);
        recyclerView.setAdapter(a);

        categoryUUID = UUID.randomUUID().toString();
        NavigationView nav = findViewById(R.id.nav_view);
        nav.setNavigationItemSelectedListener(new DrawerActivity(this));
    }

    @Click (R.id.fabCat)
    public void click(){
        AddCategoryActivity_.intent(this)
                .start();
    }
}

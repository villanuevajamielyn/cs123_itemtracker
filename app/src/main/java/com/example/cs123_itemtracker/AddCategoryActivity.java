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
import android.widget.EditText;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import java.util.UUID;

import io.realm.RealmResults;

@EActivity (R.layout.add_category_popup)
public class AddCategoryActivity extends AppCompatActivity {

    @ViewById
    EditText categoryName;

    public String categoryUUID;

    @AfterViews
    public void onCreate() {
        categoryUUID = UUID.randomUUID().toString();

    }

    @Click(R.id.addCategory)
    public void addCategory(){
        String name = categoryName.getText().toString();

        MainActivity_.intent(this)
                .start();
    }
}

package com.example.cs123_itemtracker;

import androidx.appcompat.app.AppCompatActivity;

import android.widget.EditText;
import android.widget.Switch;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import java.util.ArrayList;
import java.util.UUID;

@EActivity (R.layout.d_add_category_popup)
public class AddCategoryActivity extends AppCompatActivity {

    @ViewById
    EditText categoryName;

    @ViewById
    Switch locTypeCatSwitch;

    @ViewById
    Switch itemTypeCatSwitch;

    @Bean
    CategoryManagement categoryManagement;

    public String categoryUUID;

    @AfterViews
    public void onCreate() {
        categoryUUID = UUID.randomUUID().toString();
    }

    @Click(R.id.addCategory)
    public void addCategory(){
        String name = categoryName.getText().toString();
        Boolean locationCategory = locTypeCatSwitch.isChecked();
        Boolean itemTypeCat = itemTypeCatSwitch.isChecked();
        categoryManagement.saveCreds(categoryUUID, name, locationCategory,itemTypeCat);
        CategoryActivity_.intent(this)
                .start();
    }
}

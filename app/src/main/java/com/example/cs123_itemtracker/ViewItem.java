package com.example.cs123_itemtracker;

import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.squareup.picasso.MemoryPolicy;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.Extra;
import org.androidannotations.annotations.ViewById;

import java.io.File;

@EActivity(R.layout.more_item_info)
public class ViewItem extends AppCompatActivity {

    @Extra
    String itemID;

    @Bean
    ItemManagement itemManagement;

    @ViewById
    TextView textName;
    @ViewById
    TextView textLoc;
    @ViewById
    TextView textDesc;

    @ViewById
    ImageView imageView;

    @AfterViews
    public void ViewItem() {
        Item item = itemManagement.getCreds(itemID);
        String name = item.getName();
        String location = item.getLocation();
        String desc = item.getDescription();
        Double lat = item.getGpsPingLatitude();
        Double lon = item.getGpsPingLongitude();

        textName.setText(name);
        textLoc.setText(location);
        textDesc.setText(desc);

        File getImageDir = getExternalCacheDir();

        String filename = item.getImageName();
        File savedImage = new File(getImageDir, filename);

        Picasso.with(this)
                .load(savedImage)
                .resize(100,100)
                .networkPolicy(NetworkPolicy.NO_CACHE)
                .memoryPolicy(MemoryPolicy.NO_CACHE)
                .into(imageView);
    }
}

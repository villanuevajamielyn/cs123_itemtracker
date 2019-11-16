package com.example.cs123_itemtracker;

import android.Manifest;
import android.content.Intent;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.karumi.dexter.Dexter;
import com.karumi.dexter.MultiplePermissionsReport;
import com.karumi.dexter.listener.multi.BaseMultiplePermissionsListener;
import com.squareup.picasso.MemoryPolicy;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.UUID;

@EActivity(R.layout.b_add_item_popup)
public class AddItemActivity extends AppCompatActivity {

    @ViewById
    EditText itemName;

    @ViewById
    EditText itemDescription;

    @ViewById
    EditText itemLocation;

    @ViewById
    EditText itemLat;

    @ViewById
    EditText itemLong;

    @ViewById
    ImageView itemImage;

    @Bean
    ItemManagement im;

    private String itemUUID;

    @AfterViews
    public void init(){
        itemUUID = UUID.randomUUID().toString();

        Dexter.withActivity(this)
                .withPermissions(
                        Manifest.permission.READ_EXTERNAL_STORAGE,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE,
                        Manifest.permission.CAMERA

                )

                .withListener(new BaseMultiplePermissionsListener() {
                    public void onPermissionsChecked(MultiplePermissionsReport report) {
                        if (report.areAllPermissionsGranted()) {

                        } else {
                            Toast.makeText(getApplicationContext(), "You must provide permissions for app to run", Toast.LENGTH_LONG).show();
                            finish();
                        }
                    }
                })
                .check();
    }

    @Click(R.id.addItemImage)
    public void addItemImage(){
        ImageActivity_.intent(this)
                .startForResult(0);
    }

    @Click(R.id.addItem)
    public void addItem(){
        String name = itemName.getText().toString();
        String desc = itemDescription.getText().toString();
        String loc = itemLocation.getText().toString();
        Double lat = Double.parseDouble(itemLat.getText().toString());
        Double lng = Double.parseDouble(itemLong.getText().toString());

        im.saveCreds(itemUUID, name, desc, loc, lat, lng);

        MainActivity_.intent(this)
                .start();
    }

    public void onActivityResult(int requestCode, int responseCode, Intent data) {
        if (requestCode == 0) {
            if (responseCode == 100) {
                // save rawImage to file savedImage.jpeg
                // load file via picasso
                byte[] jpeg = data.getByteArrayExtra("rawJpeg");

                try {
                    File savedImage = saveFile(jpeg);
                    refreshImageView(savedImage);
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        }
    }

    private File saveFile(byte[] jpeg) throws IOException {
        File getImageDir = getExternalCacheDir();
        String filename = itemUUID + ".jpeg";
        File savedImage = new File(getImageDir, filename);

        FileOutputStream fos = new FileOutputStream(savedImage);
        fos.write(jpeg);
        fos.close();
        return savedImage;
    }

    private void refreshImageView(File savedImage) {
        Picasso.with(this)
                .load(savedImage)
                .networkPolicy(NetworkPolicy.NO_CACHE)
                .memoryPolicy(MemoryPolicy.NO_CACHE)
                .into(itemImage);
    }
}



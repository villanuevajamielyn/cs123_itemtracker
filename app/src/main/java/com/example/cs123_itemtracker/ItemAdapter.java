package com.example.cs123_itemtracker;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.MemoryPolicy;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;

import java.io.File;

import io.realm.RealmRecyclerViewAdapter;
import io.realm.RealmResults;

public class ItemAdapter extends RealmRecyclerViewAdapter<Item, ItemAdapter.ViewHolder> {

    Activity context;

    public ItemAdapter(Activity context, @Nullable RealmResults<Item> data) {
        super(data, true);
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = context.getLayoutInflater().inflate(R.layout.a_row_all_items, null);
        return new ViewHolder(v);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView itemName;
        TextView categoriesInput;
        ImageView imageView;
        LinearLayout itemRow;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            itemName = itemView.findViewById(R.id.itemName);
            categoriesInput = itemView.findViewById(R.id.categoriesInput);
            itemRow = itemView.findViewById(R.id.itemRow);
            imageView= (ImageView) itemView.findViewById(R.id.itemImage);

            itemRow.setOnClickListener(viewListener);
        }
    }

    private View.OnClickListener viewListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Item i = (Item) v.getTag();
            ViewItem_.intent(context)
                    .itemID(i.getItem_ID())
                    .start();
        }
    };

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Item i = getItem(position);

        holder.itemName.setText(i.getName());

        holder.categoriesInput.setText(i.getItem_ID());

        System.out.println(i.getImageName());
        File getImageDir = context.getExternalCacheDir();

        String filename = i.getImageName();
        File savedImage = new File(getImageDir, filename);

        Picasso.with(context)
                .load(savedImage)
                .resize(100,100)
                .networkPolicy(NetworkPolicy.NO_CACHE)
                .memoryPolicy(MemoryPolicy.NO_CACHE)
                .into(holder.imageView);

        holder.itemName.setTag(i);
        holder.categoriesInput.setTag(i);
        holder.imageView.setTag(i);
        holder.itemRow.setTag(i);
    }

}

package com.example.cs123_itemtracker;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
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

public class CategoryAdapter extends RealmRecyclerViewAdapter<Category, CategoryAdapter.ViewHolder> {

    Activity context;

    public CategoryAdapter(Activity context, @Nullable RealmResults<Category> data) {
        super(data, true);
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = context.getLayoutInflater().inflate(R.layout.d_row_all_categories, null);
        return new ViewHolder(v);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        Button category;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            category = itemView.findViewById(R.id.category);

            category.setOnClickListener(viewListener);
        }
    }

    private View.OnClickListener viewListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Category c = (Category) v.getTag();
        }
    };

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Category c = getItem(position);

        holder.category.setText(c.getCategoryName());

        holder.category.setTag(c);
    }

}

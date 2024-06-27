package com.viha.befreeapp;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
public class GalleryAdapter extends RecyclerView.Adapter<GalleryAdapter.ViewHolder> {

    private Context context;
    private ArrayList<Integer> imageList;

    public GalleryAdapter(Context context, ArrayList<Integer> imageList) {
        this.context = context;
        this.imageList = imageList;
    }
    // Create new views
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Inflate the item layout and create a new ViewHolder
        View view = LayoutInflater.from(context).inflate(R.layout.item_image, parent, false);
        return new ViewHolder(view);
    }

    // Replace the contents of a view
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.imageView.setImageResource(imageList.get(position));
        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Create an intent to start FullscreenImage
                Intent intent = new Intent(context, FullscreenImageActivity.class);
                intent.putExtra("IMAGE_RES_ID", imageList.get(position));
                context.startActivity(intent);// Start the FullscreenImageActivity
            }
        });
    }

    @Override
    public int getItemCount()
    {
        return imageList.size();// Return the size of dataset
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageView);
        }
    }
}
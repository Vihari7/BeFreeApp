package com.viha.befreeapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.content.Intent;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

public class Gallery extends AppCompatActivity {

    private RecyclerView recyclerView;
    private GalleryAdapter galleryAdapter;
    private ArrayList<Integer> imageList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));

        Intent intent = getIntent();
        String category = intent.getStringExtra("CATEGORY");

        imageList = getImagesForCategory(category);
        galleryAdapter = new GalleryAdapter(this, imageList);
        recyclerView.setAdapter(galleryAdapter);
    }

    private ArrayList<Integer> getImagesForCategory(String category) {
        ArrayList<Integer> images = new ArrayList<>();

        if (category.equals("Life")) {
            images.add(R.drawable.life_img1);
            images.add(R.drawable.life_img2);
            images.add(R.drawable.life_img3);
            images.add(R.drawable.life_img4);
            images.add(R.drawable.life_img5);
            images.add(R.drawable.life_img5);
            images.add(R.drawable.life_img1);
            images.add(R.drawable.life_img2);
            images.add(R.drawable.life_img1);
            images.add(R.drawable.life_img2);
            images.add(R.drawable.life_img1);
            images.add(R.drawable.life_img2);
            images.add(R.drawable.life_img1);
            images.add(R.drawable.life_img2);
            // Add more images...
        } else if (category.equals("Love")) {
            images.add(R.drawable.love_img1);
            images.add(R.drawable.love_img2);
            // Add more images...
        }
        // Handle other categories similarly
        return images;
    }
}
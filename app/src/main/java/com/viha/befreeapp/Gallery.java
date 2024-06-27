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
       // add quote images
        if (category.equals("Life")) {
            images.add(R.drawable.life_img1);
            images.add(R.drawable.life_img2);
            images.add(R.drawable.life_img3);
            images.add(R.drawable.life_img4);
            images.add(R.drawable.life_img5);
            images.add(R.drawable.life_img6);
            images.add(R.drawable.life_img7);
            images.add(R.drawable.life_img8);
            images.add(R.drawable.life_img9);
            images.add(R.drawable.life_img10);
            images.add(R.drawable.life_img11);
            images.add(R.drawable.life_img12);
            images.add(R.drawable.life_img13);
            images.add(R.drawable.life_img14);
            images.add(R.drawable.life_img15);
            images.add(R.drawable.life_img16);
            images.add(R.drawable.life_img17);
            images.add(R.drawable.life_img18);
            images.add(R.drawable.life_img19);
            images.add(R.drawable.life_img20);
        } else if (category.equals("Love")) {
            images.add(R.drawable.love_img1);
            images.add(R.drawable.love_img2);
            images.add(R.drawable.love_img3);
            images.add(R.drawable.love_img4);
            images.add(R.drawable.love_img5);
            images.add(R.drawable.love_img6);
            images.add(R.drawable.love_img7);
            images.add(R.drawable.love_img8);
            images.add(R.drawable.love_img9);
            images.add(R.drawable.love_img10);
            images.add(R.drawable.love_img11);
            images.add(R.drawable.love_img12);
            images.add(R.drawable.love_img13);
            images.add(R.drawable.love_img14);
            images.add(R.drawable.love_img15);
            images.add(R.drawable.love_img16);
            images.add(R.drawable.love_img17);
            images.add(R.drawable.love_img18);
            images.add(R.drawable.love_img19);
            images.add(R.drawable.love_img20);
        }else if (category.equals("Motivation")) {
            images.add(R.drawable.motive_img1);
            images.add(R.drawable.motive_img2);
            images.add(R.drawable.motive_img3);
            images.add(R.drawable.motive_img4);
            images.add(R.drawable.motive_img5);
            images.add(R.drawable.motive_img6);
            images.add(R.drawable.motive_img7);
            images.add(R.drawable.motive_img8);
            images.add(R.drawable.motive_img9);
            images.add(R.drawable.motive_img10);
            images.add(R.drawable.motive_img11);
            images.add(R.drawable.motive_img12);
            images.add(R.drawable.motive_img13);
            images.add(R.drawable.motive_img14);
            images.add(R.drawable.motive_img15);
            images.add(R.drawable.motive_img16);
            images.add(R.drawable.motive_img17);
            images.add(R.drawable.motive_img18);
            images.add(R.drawable.motive_img19);
            images.add(R.drawable.motive_img20);
        }
        else if (category.equals("Health")) {
            images.add(R.drawable.health_img1);
            images.add(R.drawable.health_img2);
            images.add(R.drawable.health_img3);
            images.add(R.drawable.health_img4);
            images.add(R.drawable.health_img5);
            images.add(R.drawable.health_img6);
            images.add(R.drawable.health_img7);
            images.add(R.drawable.health_img8);
            images.add(R.drawable.health_img9);
            images.add(R.drawable.health_img10);
            images.add(R.drawable.health_img11);
            images.add(R.drawable.health_img12);
            images.add(R.drawable.health_img13);
            images.add(R.drawable.health_img14);
            images.add(R.drawable.health_img15);
            images.add(R.drawable.health_img16);
            images.add(R.drawable.health_img17);
            images.add(R.drawable.health_img18);
            images.add(R.drawable.health_img19);
            images.add(R.drawable.health_img20);
        }
        else if (category.equals("Education")) {
            images.add(R.drawable.edu_img1);
            images.add(R.drawable.edu_img2);
            images.add(R.drawable.edu_img3);
            images.add(R.drawable.edu_img4);
            images.add(R.drawable.edu_img5);
            images.add(R.drawable.edu_img6);
            images.add(R.drawable.edu_img7);
            images.add(R.drawable.edu_img8);
            images.add(R.drawable.edu_img9);
            images.add(R.drawable.edu_img10);
            images.add(R.drawable.edu_img11);
            images.add(R.drawable.edu_img12);
            images.add(R.drawable.edu_img13);
            images.add(R.drawable.edu_img14);
            images.add(R.drawable.edu_img15);
            images.add(R.drawable.edu_img16);
            images.add(R.drawable.edu_img17);
            images.add(R.drawable.edu_img18);
            images.add(R.drawable.edu_img19);
            images.add(R.drawable.edu_img20);
        }
        else if (category.equals("Self Love")) {
            images.add(R.drawable.self_img1);
            images.add(R.drawable.self_img2);
            images.add(R.drawable.self_img3);
            images.add(R.drawable.self_img4);
            images.add(R.drawable.self_img5);
            images.add(R.drawable.self_img6);
            images.add(R.drawable.self_img7);
            images.add(R.drawable.self_img8);
            images.add(R.drawable.self_img9);
            images.add(R.drawable.self_img10);
            images.add(R.drawable.self_img11);
            images.add(R.drawable.self_img12);
            images.add(R.drawable.self_img13);
            images.add(R.drawable.self_img14);
            images.add(R.drawable.self_img15);
            images.add(R.drawable.self_img16);
            images.add(R.drawable.self_img17);
            images.add(R.drawable.self_img18);
            images.add(R.drawable.self_img19);
            images.add(R.drawable.self_img20);
        }
        return images;
    }
}
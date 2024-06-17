package com.viha.befreeapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.os.Bundle;
import android.media.MediaPlayer;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

public class RelaxingMusic extends AppCompatActivity {
    private MediaPlayer mediaPlayer;
    private ImageButton btnPlay, btnPause, btnNext, btnPrev;
    private ImageView ivTrackImage;
    private TextView tvTrackTitle;
    private ListView lvTracks;
    private int currentTrackIndex = 0;
    private int[] tracks = {R.raw.heaven, R.raw.meditation, R.raw.galaxy}; // Add your track resources here
    private String[] trackTitles = {"Heaven - David Fesliyan", "Meditation - David Fesliyan", "Galaxy - Endless Expanse"}; // Add your track titles here
    private int[] trackImages = {R.drawable.heaven, R.drawable.meditation, R.drawable.galaxy}; // Add your track images here
    private TextView tvTrackDuration;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_relaxing_music);

        ivTrackImage = findViewById(R.id.iv_track_image);
        tvTrackTitle = findViewById(R.id.tv_track_title);
        tvTrackDuration = findViewById(R.id.tv_track_duration);
        btnPlay = findViewById(R.id.btn_play);
        btnPause = findViewById(R.id.btn_pause);
        btnNext = findViewById(R.id.btn_next);
        btnPrev = findViewById(R.id.btn_prev);
        lvTracks = findViewById(R.id.lv_tracks);

        // Set tint color programmatically
        btnPlay.setColorFilter(ContextCompat.getColor(this, R.color.lavender));
        btnPrev.setColorFilter(ContextCompat.getColor(this, R.color.lavender));
        btnNext.setColorFilter(ContextCompat.getColor(this, R.color.lavender));
        btnPause.setColorFilter(ContextCompat.getColor(this, R.color.lavender));


        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, trackTitles);
        lvTracks.setAdapter(adapter);

        lvTracks.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                currentTrackIndex = position;
                if (mediaPlayer != null) {
                    mediaPlayer.release();
                }
                initializeMediaPlayer();
                playMusic();
                updateTrackInfo();
            }
        });

        updateTrackInfo();
        initializeMediaPlayer();

        btnPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                playMusic();
            }
        });

        btnPause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pauseMusic();
            }
        });

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nextTrack();
            }
        });

        btnPrev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                previousTrack();
            }
        });
    }

    private void initializeMediaPlayer() {
        mediaPlayer = MediaPlayer.create(this, tracks[currentTrackIndex]);
        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                nextTrack();
            }
        });
        // Set track duration
        int duration = mediaPlayer.getDuration();
        tvTrackDuration.setText(formatDuration(duration));
    }

    private void playMusic() {
        mediaPlayer.start();
        btnPlay.setVisibility(View.GONE);
        btnPause.setVisibility(View.VISIBLE);
    }

    private void pauseMusic() {
        mediaPlayer.pause();
        btnPlay.setVisibility(View.VISIBLE);
        btnPause.setVisibility(View.GONE);
    }

    private void nextTrack() {
        if (mediaPlayer != null) {
            mediaPlayer.release();
        }
        currentTrackIndex = (currentTrackIndex + 1) % tracks.length;
        initializeMediaPlayer();
        playMusic();
        updateTrackInfo();
    }

    private void previousTrack() {
        if (mediaPlayer != null) {
            mediaPlayer.release();
        }
        currentTrackIndex = (currentTrackIndex - 1 + tracks.length) % tracks.length;
        initializeMediaPlayer();
        playMusic();
        updateTrackInfo();
    }

    private void updateTrackInfo() {
        tvTrackTitle.setText(trackTitles[currentTrackIndex]);
        ivTrackImage.setImageResource(trackImages[currentTrackIndex]);
    }

    @Override
    protected void onDestroy() {
        if (mediaPlayer != null) {
            mediaPlayer.release();
            mediaPlayer = null;
        }
        super.onDestroy();
    }
    private String formatDuration(int duration) {
        int minutes = (duration / 1000) / 60;
        int seconds = (duration / 1000) % 60;
        return String.format("%02d:%02d", minutes, seconds);
    }

}
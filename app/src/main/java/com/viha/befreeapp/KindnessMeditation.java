package com.viha.befreeapp;


import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.VideoView;

import androidx.appcompat.app.AppCompatActivity;
public class KindnessMeditation extends AppCompatActivity {

    private VideoView videoView;
    private Button playPauseButton;
    private SeekBar seekBar;
    private TextView timeTextView;
    private Handler handler = new Handler();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kindness_meditation);

        videoView = findViewById(R.id.videoView);
        playPauseButton = findViewById(R.id.playPauseButton);
        seekBar = findViewById(R.id.seekBar);
        timeTextView = findViewById(R.id.timeTextView);

        String videoPath = "android.resource://" + getPackageName() + "/" + R.raw.kindness_medication;
        Uri uri = Uri.parse(videoPath);
        videoView.setVideoURI(uri);

        videoView.setOnPreparedListener(mp -> {
            seekBar.setMax(videoView.getDuration());
            updateTime();
        });

        playPauseButton.setOnClickListener(v -> {
            if (videoView.isPlaying()) {
                videoView.pause();
                playPauseButton.setText("Play");
            } else {
                videoView.start();
                playPauseButton.setText("Pause");
                updateTime();
            }
        });

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if (fromUser) {
                    videoView.seekTo(progress);
                }
                updateTime();
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

    private void updateTime() {
        handler.postDelayed(() -> {
            if (videoView.isPlaying()) {
                int currentPosition = videoView.getCurrentPosition();
                int duration = videoView.getDuration();
                seekBar.setProgress(currentPosition);
                timeTextView.setText(formatTime(currentPosition) + " / " + formatTime(duration));

                updateTime();
            }
        }, 1000);
    }

    private String formatTime(int milliseconds) {
        int seconds = (milliseconds / 1000) % 60;
        int minutes = (milliseconds / (1000 * 60)) % 60;
        return String.format("%02d:%02d", minutes, seconds);
    }
}
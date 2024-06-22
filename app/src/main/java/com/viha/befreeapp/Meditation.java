package com.viha.befreeapp;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.VideoView;

public class Meditation extends AppCompatActivity {

    private TextView timerTextView;
    private Button startButton;
    private ImageView playIcon;
    private ImageView pauseIcon;
    private CountDownTimer countDownTimer;
    private boolean isTimerRunning = false;
    private long timeLeftInMillis = 600000; // 10 minutes
    private MediaPlayer mediaPlayer;
    private boolean isPlaying = false;
    private VideoView videoView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meditation);

        timerTextView = findViewById(R.id.timerTextView);
        startButton = findViewById(R.id.startButton);
        playIcon = findViewById(R.id.playIcon);
        pauseIcon = findViewById(R.id.pauseIcon);
        videoView = findViewById(R.id.videoView);

        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isTimerRunning) {
                    pauseTimer();
                    pauseAudio();
                    pauseVideo();
                } else {
                    startTimer();
                    startAudio();
                    playVideo();
                }
            }
        });
        playIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startAudio();
                playVideo(); // Start playing video when audio starts
            }
        });

        pauseIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pauseAudio();
                pauseVideo(); // Pause video when audio pauses
            }
        });

        updateCountDownText();
    }

    private void startTimer() {
        countDownTimer = new CountDownTimer(timeLeftInMillis, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                timeLeftInMillis = millisUntilFinished;
                updateCountDownText();
            }

            @Override
            public void onFinish() {
                isTimerRunning = false;
                startButton.setText("Start Meditation");
            }
        }.start();

        isTimerRunning = true;
        startButton.setText("Pause Meditation");
    }

    private void pauseTimer() {
        if (countDownTimer != null) {
            countDownTimer.cancel();
        }
        isTimerRunning = false;
        startButton.setText("Start Meditation");
    }

    private void updateCountDownText() {
        int minutes = (int) (timeLeftInMillis / 1000) / 60;
        int seconds = (int) (timeLeftInMillis / 1000) % 60;
        String timeFormatted = String.format("%02d:%02d", minutes, seconds);
        timerTextView.setText(timeFormatted);
    }

    private void startAudio() {
        if (mediaPlayer == null) {
            mediaPlayer = MediaPlayer.create(this, R.raw.heaven);
        }
        mediaPlayer.start();
        isPlaying = true;
        playIcon.setVisibility(View.GONE);
        pauseIcon.setVisibility(View.VISIBLE);
    }

    private void pauseAudio() {
        if (mediaPlayer != null && mediaPlayer.isPlaying()) {
            mediaPlayer.pause();
        }
        isPlaying = false;
        playIcon.setVisibility(View.VISIBLE);
        pauseIcon.setVisibility(View.GONE);
    }

    private void playVideo() {
        videoView.setVisibility(View.VISIBLE);
        Uri videoUri = Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.body_scan_medi); // meditation video
        videoView.setVideoURI(videoUri);
        videoView.start();
        playIcon.setVisibility(View.GONE);
        pauseIcon.setVisibility(View.VISIBLE);
    }

    private void pauseVideo() {
        videoView.pause();
        playIcon.setVisibility(View.VISIBLE);
        pauseIcon.setVisibility(View.GONE);
    }

    @Override
    protected void onDestroy() {
        if (mediaPlayer != null) {
            mediaPlayer.release();
            mediaPlayer = null;
        }
        super.onDestroy();
    }
}

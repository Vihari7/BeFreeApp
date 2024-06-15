package com.viha.befreeapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.animation.ScaleAnimation;
import android.view.animation.Animation;

public class Breathing extends AppCompatActivity {

    private View circleView;
    private Handler handler = new Handler();
    private boolean isExpanding = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_breathing);

        circleView = findViewById(R.id.circleView);
        startBreathingAnimation();
    }

    private void startBreathingAnimation() {
        final int inhaleDuration = 4000;  // 4 seconds
        final int exhaleDuration = 6000;  // 6 seconds

        handler.post(new Runnable() {
            @Override
            public void run() {
                if (isExpanding) {
                    animateCircle(1.0f, 1.5f, inhaleDuration);
                } else {
                    animateCircle(1.5f, 1.0f, exhaleDuration);
                }
                isExpanding = !isExpanding;
                handler.postDelayed(this, isExpanding ? inhaleDuration : exhaleDuration);
            }
        });
    }

    private void animateCircle(float fromScale, float toScale, int duration) {
        ScaleAnimation scaleAnimation = new ScaleAnimation(
                fromScale, toScale, fromScale, toScale,
                Animation.RELATIVE_TO_SELF, 0.5f,
                Animation.RELATIVE_TO_SELF, 0.5f);
        scaleAnimation.setDuration(duration);
        scaleAnimation.setFillAfter(true);
        circleView.startAnimation(scaleAnimation);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        handler.removeCallbacksAndMessages(null);
    }
}
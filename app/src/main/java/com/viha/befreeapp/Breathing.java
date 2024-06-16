package com.viha.befreeapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.animation.ScaleAnimation;
import android.view.animation.Animation;
import android.widget.TextView;

public class Breathing extends AppCompatActivity {

    private View circleView;
    private Handler handler = new Handler();
    private boolean isExpanding = true;
    private TextView breathingInstructions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_breathing);

        circleView = findViewById(R.id.circleView);
        breathingInstructions = findViewById(R.id.breathingInstructions);
        startBreathingAnimation();
    }

    private void startBreathingAnimation() {
        final int inhaleDuration = 4000;  // 4 seconds
        final int holdDuration = 2000; // 2 seconds
        final int exhaleDuration = 6000;  // 6 seconds

        handler.post(new Runnable() {
            public void run() {
                if (isExpanding) {
                    breathingInstructions.setText("Inhale");
                    animateCircle(1.0f, 1.5f, inhaleDuration);
                    handler.postDelayed(() -> {
                        breathingInstructions.setText("Hold");
                    }, inhaleDuration);
                    handler.postDelayed(this, inhaleDuration + holdDuration);
                } else {
                    breathingInstructions.setText("Exhale");
                    animateCircle(1.5f, 1.0f, exhaleDuration);
                    handler.postDelayed(this, exhaleDuration);
                }
                isExpanding = !isExpanding;
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
package com.example.foodapp.modules.splash.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.TextView;

import com.airbnb.lottie.LottieAnimationView;
import com.example.foodapp.MainActivity;
import com.example.foodapp.R;

public class SplashActivity extends AppCompatActivity {
    TextView appName;
    LottieAnimationView lottie;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        lottie=findViewById(R.id.lottie);
        lottie.animate().translationX(2000).setDuration(2000).setStartDelay(2900);
        new Handler().postDelayed(() -> {
            Intent intent=new Intent(this, MainActivity.class);
            startActivity(intent);
        },4500);
    }
}
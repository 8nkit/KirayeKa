package com.example.kirayeka;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.DrawableImageViewTarget;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ImageView imageView = (ImageView) findViewById(R.id.rmvi);

       DrawableImageViewTarget imageViewTarget = new DrawableImageViewTarget(imageView);

        Glide.with(this).load(R.drawable.nav).into(imageViewTarget);
        new CountDownTimer(1700, 1000) {
            public void onFinish() {
                Intent startActivity = new Intent(MainActivity.this, ChooseAct.class);
                startActivity(startActivity);
                finish();
            }

            public void onTick(long millisUntilFinished) {
            }

        }.start();

    }
}

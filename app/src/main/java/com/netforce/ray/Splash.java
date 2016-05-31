package com.netforce.ray;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.netforce.ray.dashboard.Dashboard;
import com.nineoldandroids.animation.Animator;

public class Splash extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        YoYo.with(Techniques.ZoomInUp)
                .withListener(new Animator.AnimatorListener() {
                    @Override
                    public void onAnimationStart(Animator animation) {

                    }

                    @Override
                    public void onAnimationEnd(Animator animation) {
                        Intent intent = new Intent(getApplicationContext(), Dashboard.class);
                        startActivity(intent);

                    }

                    @Override
                    public void onAnimationCancel(Animator animation) {

                    }

                    @Override
                    public void onAnimationRepeat(Animator animation) {

                    }
                })
                .duration(2000)
                .playOn(findViewById(R.id.logo));

    }

    private void showMessage(String s) {
        Toast.makeText(Splash.this, s, Toast.LENGTH_SHORT).show();
    }
}

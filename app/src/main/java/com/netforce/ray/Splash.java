package com.netforce.ray;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.netforce.ray.dashboard.Dashboard;
import com.netforce.ray.general.NoInternet;
import com.nineoldandroids.animation.Animator;

public class Splash extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Window window = getWindow();

// clear FLAG_TRANSLUCENT_STATUS flag:
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);

// add FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS flag to the window
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            // only for gingerbread and newer versions
            window.setStatusBarColor(ContextCompat.getColor(getApplicationContext(), R.color.statusbar_splash));
        }

        setContentView(R.layout.activity_main);
        YoYo.with(Techniques.ZoomIn)
                .withListener(new Animator.AnimatorListener() {
                    @Override
                    public void onAnimationStart(Animator animation) {

                    }

                    @Override
                    public void onAnimationEnd(Animator animation) {
                        if(isNetworkAvailable()) {
                            Intent intent = new Intent(getApplicationContext(), Dashboard.class);
                            startActivity(intent);
                            finish();
                        }
                        else {
                            Intent intent = new Intent(getApplicationContext(), Dashboard.class);
                            startActivity(intent);
                            finish();
                        }

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

    public void showMessage(String s) {
        Toast.makeText(Splash.this, s, Toast.LENGTH_SHORT).show();
    }
    public boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager
                .getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }
}

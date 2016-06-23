package com.netforce.ray.profile.userprofile;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import com.netforce.ray.R;

public class SettingActivity extends AppCompatActivity implements View.OnClickListener {

    private Toolbar toolbar;
    ImageView imageViewEmailNotification, imageViewNewsLetter, imageViewImmediate, imageViewNotificationSound;
    boolean booleanEmailNotification = true, booleanNewsLetter = true, booleanImmediate = false, booleanNotificationSound = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        setupToolBar("Settings");
        initView();
    }

    private void initView() {
        imageViewEmailNotification = (ImageView) findViewById(R.id.imageViewEmailNotification);
        imageViewImmediate = (ImageView) findViewById(R.id.imageViewImmediate);
        imageViewNewsLetter = (ImageView) findViewById(R.id.imageViewNewsLetter);
        imageViewNotificationSound = (ImageView) findViewById(R.id.imageViewNotificationSound);
        imageViewEmailNotification.setOnClickListener(this);
        imageViewNewsLetter.setOnClickListener(this);
        imageViewImmediate.setOnClickListener(this);
        imageViewNotificationSound.setOnClickListener(this);
        setChecked(imageViewEmailNotification, booleanEmailNotification);
        setChecked(imageViewNewsLetter, booleanNewsLetter);
        setChecked(imageViewImmediate, booleanImmediate);
        setChecked(imageViewNotificationSound, booleanNotificationSound);
    }

    private void setChecked(ImageView imageViewEmailNotification, boolean booleanEmailNotification) {
        if (booleanEmailNotification) {
            imageViewEmailNotification.setImageResource(R.drawable.ic_check_primary);
        } else {
            imageViewEmailNotification.setImageResource(R.drawable.ic_blank_circle);
        }
    }

    private void setupToolBar(String s) {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(s);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                overridePendingTransition(R.anim.left_to_right, R.anim.right_to_left);
                break;
            default:
                return super.onOptionsItemSelected(item);
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.imageViewEmailNotification:
                booleanEmailNotification = !booleanEmailNotification;
                setChecked(imageViewEmailNotification, booleanEmailNotification);
                break;
            case R.id.imageViewNewsLetter:
                booleanNewsLetter = !booleanNewsLetter;
                setChecked(imageViewNewsLetter, booleanNewsLetter);
                break;
            case R.id.imageViewImmediate:
                booleanImmediate = !booleanImmediate;
                setChecked(imageViewImmediate, booleanImmediate);
                break;
            case R.id.imageViewNotificationSound:
                booleanNotificationSound = !booleanNotificationSound;
                setChecked(imageViewNotificationSound, booleanNotificationSound);
                break;
        }
    }
}

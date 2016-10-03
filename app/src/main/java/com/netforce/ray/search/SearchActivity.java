package com.netforce.ray.search;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.netforce.ray.R;

public class SearchActivity extends AppCompatActivity implements View.OnClickListener
{

    private Toolbar toolbar;
    private ImageView close;
    private EditText editTextSearch;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        setupToolBar();
        showkeyboard();
        findViewById(R.id.advanceSearch).setOnClickListener(this);
        getPermission();

    }

    private void showkeyboard() {
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, 0);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.left_to_right, R.anim.right_to_left);
        hideKeyboard();
    }

    private void hideKeyboard() {
        View view = this.getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    private void setupToolBar() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        close = (ImageView) toolbar.findViewById(R.id.imageViewClose);
        editTextSearch = (EditText) toolbar.findViewById(R.id.editTextSearch);
        close.setOnClickListener(this);
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        String teams = "Login";
        getSupportActionBar().setTitle(teams);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            finish();
            overridePendingTransition(R.anim.left_to_right, R.anim.right_to_left);
            hideKeyboard();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.imageViewClose:
                editTextSearch.setText("");
                break;
            case R.id.advanceSearch:
                Intent intent=new Intent(getApplicationContext(), AdvanceSearch.class);
                startActivity(intent);
                hideKeyboard();
                break;
        }
    }

    private void showMessage(String s) {
        Toast.makeText(SearchActivity.this, s, Toast.LENGTH_SHORT).show();
    }

    private void getPermission()
    {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
        {

            String[] permission = {
                    "android.permission.ACCESS_FINE_LOCATION",
                    "android.permission.INTERNET",
                    "android.permission.WAKE_LOCK"
            };

            ActivityCompat.requestPermissions(this,
                    permission, 1);


        }
    }

}

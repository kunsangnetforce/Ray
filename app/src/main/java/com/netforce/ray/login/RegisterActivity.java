package com.netforce.ray.login;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.netforce.ray.R;
import com.netforce.ray.dashboard.Dashboard;

public class RegisterActivity extends AppCompatActivity {


    Button RegisterButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        RegisterButton =  (Button) findViewById(R.id.buttonRegister);


        RegisterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i  = new Intent(RegisterActivity.this,Dashboard.class);
                startActivity(i);
            }
        });
    }
}

package com.netforce.ray.login;

import android.content.Intent;
import android.graphics.Paint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.netforce.ray.R;
import com.netforce.ray.dashboard.Dashboard;

public class RegisterActivity extends AppCompatActivity {


    Button RegisterButton;

    TextView sign_in;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        RegisterButton =  (Button) findViewById(R.id.buttonRegister);

        sign_in = (TextView) findViewById(R.id.buttonSignin);

        sign_in.setPaintFlags(sign_in.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);


        RegisterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i  = new Intent(RegisterActivity.this,Dashboard.class);

                startActivity(i);
                finish();
            }
        });


        sign_in.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i  = new Intent(RegisterActivity.this,Login.class);
                startActivity(i);
            }
        });

    }
}

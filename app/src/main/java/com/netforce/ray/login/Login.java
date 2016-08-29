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

public class Login extends AppCompatActivity {

   TextView sign_up_button;
    Button login_button;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_login);

        login_button = (Button) findViewById(R.id.buttonLogin);

        sign_up_button = (TextView) findViewById(R.id.buttonSignup);
        sign_up_button.setPaintFlags(sign_up_button.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);


        login_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Login.this, Dashboard.class);
                startActivity(i);
            }
        });



        sign_up_button.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent  i  = new Intent(Login.this,RegisterActivity.class);
                startActivity(i);
            }
        });


    }
}

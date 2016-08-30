package com.netforce.ray.login;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Switch;
import android.widget.Toast;

import com.netforce.ray.R;

public class LoginSreen extends AppCompatActivity implements View.OnClickListener {

    Intent intent;
    Button signin_button,signup_button,facebook_button;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_sreen);


        signup_button = (Button) findViewById(R.id.signup_Button);

        signin_button = (Button) findViewById(R.id.signin_Button);

        facebook_button = (Button)  findViewById(R.id.facebook_login);

        signup_button.setOnClickListener(this);

        signin_button.setOnClickListener(this);

        facebook_button.setOnClickListener(this);

    }


    public void onClick(View view){

        switch(view.getId())
        {

            case R.id.facebook_login:

                intent = new Intent(getApplicationContext(), RegisterActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.enter, R.anim.exit);
                break;

            case R.id.signin_Button:
                intent = new Intent(getApplicationContext(), Login.class);
                startActivity(intent);
                overridePendingTransition(R.anim.enter, R.anim.exit);


                break;

            case R.id.signup_Button:
                intent = new Intent(getApplicationContext(), RegisterActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.enter, R.anim.exit);


                break;

        }

    }

}

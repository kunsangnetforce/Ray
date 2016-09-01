package com.netforce.ray.login;

import android.content.Intent;
import android.graphics.Paint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.netforce.ray.R;
import com.netforce.ray.dashboard.Dashboard;

public class Login extends AppCompatActivity  implements View.OnClickListener {

   TextView sign_up_button;
    Button login_button;
    EditText Email_txt,Password_txt;
    ImageView Email_image,Password_image;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_login);


        Email_txt = (EditText)findViewById(R.id.editTextEmail);
        Password_txt= (EditText)findViewById(R.id.editTextPassword);
        login_button = (Button) findViewById(R.id.buttonLogin);
        sign_up_button = (TextView) findViewById(R.id.buttonSignup);
        Email_image = (ImageView)findViewById(R.id.email_image);
        Password_image = (ImageView)findViewById(R.id.email_image);

        Email_txt.setOnClickListener(this);
        Password_txt.setOnClickListener(this);
        login_button.setOnClickListener(this);
        sign_up_button.setOnClickListener(this);


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


    public void onClick(View v) {

        switch (v.getId()){

            case R.id.editTextEmail:
                break;

            case R.id.editTextPassword:
                 // Password_image.setImageResource(R.drawable.ic_account);

                break;

            case R.id.buttonLogin:
                Intent i = new Intent(Login.this, Dashboard.class);
                startActivity(i);
                break;

            case R.id.buttonSignup:
                Intent  i2  = new Intent(Login.this,RegisterActivity.class);
                startActivity(i2);
                break;


        }
        Toast.makeText(getApplicationContext(), "I'm clicked!", Toast.LENGTH_SHORT).show();;
    }
}

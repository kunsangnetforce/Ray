package com.netforce.ray.login;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Paint;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;
import com.netforce.ray.R;
import com.netforce.ray.dashboard.Dashboard;
import com.netforce.ray.home.Home;
import com.netforce.ray.validation.Validation;

import java.util.Arrays;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener{


    Button registerButton;
    EditText user_name,email,mobile_no,password,cpassword;
    TextView sign_in;
     ProgressDialog pd;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        setuplayout();



    }

    private void setuplayout()
    {
        user_name = (EditText) findViewById(R.id.usernameEdittext);

        email = (EditText) findViewById(R.id.emailEdittext);

        password = (EditText) findViewById(R.id.passwordEdittext);

        cpassword = (EditText) findViewById(R.id.cpasswordEdittext);

        mobile_no = (EditText) findViewById(R.id.mobileEdittext);

        registerButton =  (Button) findViewById(R.id.buttonRegister);

        registerButton.setOnClickListener(this);

        sign_in = (TextView) findViewById(R.id.buttonSignin);

        sign_in.setPaintFlags(sign_in.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);

        sign_in.setOnClickListener(this);


    }

    @Override
    public void onClick(View view)
    {

        switch (view.getId()) {

            case R.id.buttonRegister:

               // check_validation();

                Intent sign= new Intent(RegisterActivity.this,Dashboard.class);
                startActivity(sign);

                break;
            case R.id.buttonSignin:
                Intent sign_in = new Intent(RegisterActivity.this,LoginActivity.class);
                startActivity(sign_in);

        }

    }

    private void check_validation()
    {
        Validation validation = new Validation();

        ConnectivityManager connectivityManager = (ConnectivityManager)getSystemService(getApplicationContext().CONNECTIVITY_SERVICE);
        if(connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.CONNECTED || connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.CONNECTED) {
            //we are connected to a network

            if(!user_name.getText().toString().equals(""))
            {
                if (!email.getText().toString().equals(""))
                {

                    if(validation.isEmailAddress(email,true))
                    {

                        if(!mobile_no.getText().toString().equals(""))
                        {
                            if(mobile_no.getText().toString().length()== 10)
                            {

                                if(!password.getText().toString().equals("") )
                                {

                                    pd = new ProgressDialog(RegisterActivity.this);
                                    pd.setMessage("loading");
                                    pd.show();
                                    String url = "http://netforce.biz/seeksell/users/register_user?user_name="+user_name.getText().toString()+"&email="+email.getText().toString()+"&password="+password.getText().toString()+"&action=registarion&mobile="+mobile_no.getText().toString();

                                    Ion.with(RegisterActivity.this)
                                            .load(url)
                                            .asJsonObject()
                                            .setCallback(new FutureCallback<JsonObject>() {
                                                @Override
                                                public void onCompleted(Exception e, JsonObject result)
                                                {

                                                    String status = result.get("status").toString();
                                                    String message = result.get("message").toString();
                                                    System.out.println("result ============" + message + status);
                                                    pd.dismiss();
                                                    Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();

                                                }

                                            });

                                }
                                else
                                {
                                    password.requestFocus();
                                    Toast.makeText(getApplicationContext(),"Please Enter Password",Toast.LENGTH_SHORT).show();

                                }
                            }
                            else
                            {
                                mobile_no.requestFocus();
                                Toast.makeText(getApplicationContext(),"Please Enter 10 digit Mobile Number",Toast.LENGTH_SHORT).show();
                            }

                        }
                        else
                        {
                            mobile_no.requestFocus();
                            Toast.makeText(getApplicationContext(),"Please Enter Mobile Number",Toast.LENGTH_SHORT).show();
                        }
                    }
                    else
                    {

                        email.requestFocus();
                        Toast.makeText(getApplicationContext(),"Email Address is not valid",Toast.LENGTH_SHORT).show();
                    }

                }
                else
                {
                    email.requestFocus();
                    Toast.makeText(getApplicationContext(),"Please Enter Email Address",Toast.LENGTH_SHORT).show();


                }


            }
            else
            {

                user_name.requestFocus();
                Toast.makeText(getApplicationContext(),"Please Enter User Name",Toast.LENGTH_SHORT).show();

            }

        }
        else
        {

            Toast.makeText(getApplicationContext(),"Internet Connection Not Available",Toast.LENGTH_SHORT).show();

        }

    }


    class Register extends AsyncTask<String, Void, String>
    {
        String url ;
        ProgressDialog pd;
        protected void onPreExecute()
        {
            pd = new ProgressDialog(RegisterActivity.this);
            pd.setMessage("loading");
            pd.show();
            url = "http://netforce.biz/seeksell/users/register_user?user_name="+user_name.getText().toString()+"&email="+email.getText().toString()+"&password="+password.getText().toString()+"&action=registarion&mobile="+mobile_no.getText().toString();

            System.out.println("url ============="+ url);

            super.onPreExecute();

        }

        @Override
        protected String doInBackground(String... strings)
        {
            String r = "ji";
            Ion.with(RegisterActivity.this)
                    .load(url)
                    .asJsonObject()
                    .setCallback(new FutureCallback<JsonObject>()
                    {
                        @Override
                        public void onCompleted(Exception e, JsonObject result)
                        {

                            String status = result.get("status").toString();
                            String message = result.get("message").toString();
                            System.out.println("result ============" + message + status);

                            if (status.equals("error"))
                            {
                                Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
                            }
                            else
                            {


                                Intent dashboard = new Intent(RegisterActivity.this,Dashboard.class);
                                startActivity(dashboard);

                            }

                        }
                    });

            return r;

        }


        @Override
        protected void onPostExecute(String result)
        {
            pd.dismiss();

          /*  Intent i  = new Intent(RegisterActivity.this,Dashboard.class);
            startActivity(i);
            finish();*/
        }
    }
}

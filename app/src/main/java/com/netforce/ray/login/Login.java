package com.netforce.ray.login;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Paint;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.JsonObject;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;
import com.netforce.ray.R;
import com.netforce.ray.Splash;
import com.netforce.ray.dashboard.Dashboard;
import com.netforce.ray.home.home2.HeaderFragment;
import com.netforce.ray.validation.Validation;

public class Login extends AppCompatActivity  implements View.OnClickListener
{

    TextView sign_up_button;
    Button login_button;
    EditText Email_txt,Password_txt;
    ImageView Email_image,Password_image;
    ProgressDialog pd;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_login);

        setupLayout();


    }

    private void setupLayout()
    {

        Email_txt = (EditText)findViewById(R.id.editTextEmail);
        Password_txt= (EditText)findViewById(R.id.editTextPassword);
        login_button = (Button) findViewById(R.id.buttonLogin);
        sign_up_button = (TextView) findViewById(R.id.buttonSignup);
        Email_image = (ImageView)findViewById(R.id.email_image);
        Password_image = (ImageView)findViewById(R.id.email_image);


        sign_up_button.setPaintFlags(sign_up_button.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);


        Email_txt.setOnClickListener(this);
        Password_txt.setOnClickListener(this);
        login_button.setOnClickListener(this);
        sign_up_button.setOnClickListener(this);

    }


    public void onClick(View v)
    {

        switch (v.getId())
        {

            case R.id.buttonLogin:
               /* Intent i = new Intent(Login.this, Dashboard.class);
                startActivity(i);*/
                login_validation();
                break;

            case R.id.buttonSignup:
                Intent  i2  = new Intent(Login.this,RegisterActivity.class);
                startActivity(i2);
                break;


        }
        Toast.makeText(getApplicationContext(), "I'm clicked!", Toast.LENGTH_SHORT).show();;
    }

    private void login_validation() {

        Validation validation = new Validation();


        ConnectivityManager connectivityManager = (ConnectivityManager)getSystemService(getApplicationContext().CONNECTIVITY_SERVICE);
        if(connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.CONNECTED || connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.CONNECTED) {


            if (!Email_txt.getText().toString().equals(""))
            {

                if (validation.isEmailAddress(Email_txt, true))
                {
                    if(!Password_txt.getText().toString().equals("") )
                    {


                        pd = new ProgressDialog(Login.this);
                        pd.setMessage("loading");
                        pd.show();

                        String url =  "http://netforce.biz/seeksell/users/authenticate?email="+Email_txt.getText().toString()+"&password="+Password_txt.getText().toString()+"&action=login";

                        System.out.println("url=================="+ url);

                        Ion.with(Login.this)
                                .load(url)
                                .asJsonObject()
                                .setCallback(new FutureCallback<JsonObject>() {
                                    @Override
                                    public void onCompleted(Exception e, JsonObject result) {

                                        try {

                                            String status = result.get("status").getAsString();
                                            String message = result.get("message").getAsString();

                                            System.out.println("result ============" + message + status);
                                            pd.dismiss();


                                            if(!status.toString().equals(""))
                                            {
                                                if(status.toString().equals("error"))
                                                {
                                                    Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();

                                                }
                                                else{
                                                    String user_id = result.get("id").getAsString();
                                               String shared_user_id=Splash.sharedpreferences.getString("user_id", null);
                                                    if(shared_user_id!=null)
                                                    {

                                                    }
                                                    else
                                                    {
                                                        Splash.sharedpreferences.edit().putString("user_id",user_id).commit();
                                                    }


                                                    Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
                                                    Intent login= new Intent(Login.this,Dashboard.class);
                                                    startActivity(login);
                                                    finish();
                                                }
                                            }
                                            else
                                            {


                                            }

                                        }
                                        catch (Exception c){


                                        }


                                    }

                                });


                    }
                    else
                    {
                        Password_txt.requestFocus();
                        Toast.makeText(getApplicationContext(),"Please Enter Password",Toast.LENGTH_SHORT).show();

                    }

                }
                else
                {

                    Email_txt.requestFocus();
                    Toast.makeText(getApplicationContext(),"Email Address is not valid",Toast.LENGTH_SHORT).show();

                }
            }
            else
            {

                Email_txt.requestFocus();
                Toast.makeText(getApplicationContext(),"Please Enter Email Address",Toast.LENGTH_SHORT).show();

            }
        }
        else
        {

            Toast.makeText(getApplicationContext(),"Internet Connection Not Available",Toast.LENGTH_SHORT).show();

        }

    }


    class Logintask extends AsyncTask<String, Void, String>
    {
        String url ;
        ProgressDialog pd;
        protected void onPreExecute()
        {
            pd = new ProgressDialog(Login.this);
            pd.setMessage("loading");
            pd.show();
            url =  "http://netforce.biz/seeksell/users/authenticate?email="+Email_txt.getText().toString()+"&password="+Password_txt.getText().toString()+"&action=login";
            System.out.println("url ============="+ url);

            super.onPreExecute();

        }

        @Override
        protected String doInBackground(String... strings)
        {

            String r = "ji";
            Ion.with(Login.this)
                    .load(url)
                    .asJsonObject()
                    .setCallback(new FutureCallback<JsonObject>()
                    {
                        @Override
                        public void onCompleted(Exception e, JsonObject result) {

                            String status = result.get("status").toString();
                            String message = result.get("message").toString();
                            System.out.println("result ============" + message + status);


                            Toast.makeText(getApplicationContext(),message,Toast.LENGTH_SHORT).show();



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

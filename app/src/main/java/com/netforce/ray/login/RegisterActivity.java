package com.netforce.ray.login;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.Paint;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;
import com.mvc.imagepicker.ImagePicker;
import com.netforce.ray.R;
import com.netforce.ray.Splash;
import com.netforce.ray.dashboard.Dashboard;
import com.netforce.ray.home.Home;
import com.netforce.ray.validation.Validation;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

import de.hdodenhof.circleimageview.CircleImageView;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener{



    private int STORAGE_PERMISSION_CODE = 23;
    CircleImageView user_pic;
    Button registerButton;
    EditText user_name,email,mobile_no,password,cpassword;
     TextView sign_in;
    ProgressDialog pd;

    File imageFile;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        setuplayout();



    }

    private void setuplayout()
    {
        user_pic=(CircleImageView)findViewById(R.id.imageview_registration);
        user_pic.setOnClickListener(this);
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

                check_validation();

                break;
            case R.id.buttonSignin:
                Intent sign_in = new Intent(RegisterActivity.this,LoginActivity.class);
                startActivity(sign_in);
                break;

            case R.id.imageview_registration:
                ImagePicker.pickImage(this, "Select your image:");

                break;

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

                                if(!password.getText().toString().equals("") ||!cpassword.getText().toString().equals(""))
                                {
                                    if(checkPassWordAndConfirmPassword(password.getText().toString(),cpassword.getText().toString())) {
                                        if (imageFile != null) {
                                            if (isReadStorageAllowed()) {
                                                //If permission is already having then showing the toast
                                                //Toast.makeText(this,"You already have the permission",Toast.LENGTH_LONG).show();


                                                //Existing the method with return


                                                pd = new ProgressDialog(RegisterActivity.this);
                                                pd.setMessage("loading");
                                                pd.show();
                                                String url = "http://netforce.biz/seeksell/users/register_user";

                                                Ion.with(RegisterActivity.this)
                                                        .load(url)
                                                        .setMultipartFile("profile_photo", "image/*", imageFile)

                                                        .setMultipartParameter("user_name", user_name.getText().toString())
                                                        .setMultipartParameter("email", email.getText().toString())
                                                        .setMultipartParameter("password", password.getText().toString())
                                                        .setMultipartParameter("action", "registration")
                                                        .setMultipartParameter("mobile", mobile_no.getText().toString())

                                                        .asJsonObject()
                                                        .setCallback(new FutureCallback<JsonObject>() {
                                                            @Override
                                                            public void onCompleted(Exception e, JsonObject result) {


                                                                try {
                                                                    SharedPreferences.Editor editor = getSharedPreferences(Splash.MyPREFERENCES, MODE_PRIVATE).edit();
                                                                    String status = result.get("status").toString();
                                                                    String message = result.get("message").toString();
                                                                    String user_id = result.get("token").getAsString();
                                                                    editor.putString("user_id", user_id);
                                                                    editor.commit();
                                                                    System.out.println("result ============" + message + status);
                                                                    pd.dismiss();

                                                                    if (!status.toString().equals("")) {
                                                                        if (status.toString().equals("error")) {

                                                                            Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
                                                                        } else {

                                                                            Toast.makeText(getApplicationContext(), message + user_id, Toast.LENGTH_SHORT).show();
                                                                            editor.putString("user_id", user_id);
                                                                            Intent sign = new Intent(RegisterActivity.this, Dashboard.class);
                                                                            startActivity(sign);

                                                                        }
                                                                    } else {


                                                                    }
                                                                } catch (Exception e1) {
                                                                    pd.dismiss();
                                                                    Log.e("ex registration", e1.toString());
                                                                }


                                                            }

                                                        });
                                                return;
                                            } else {
                                                requestStoragePermission();
                                            }
                                        } else {
                                            Toast.makeText(this, "Please Select Image first then try again", Toast.LENGTH_SHORT).show();

                                            //If the app has not the permission then asking for the permission

                                        }
                                    }
                                    else{
                                        Toast.makeText(this, "Please Enter Same Password & Confirm Password", Toast.LENGTH_SHORT).show();
                                    }
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





    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {


        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {
            Log.e("resultCode if",resultCode+"");

            Bitmap bitmap = ImagePicker.getImageFromResult(this, requestCode, resultCode, data);
            user_pic.setImageBitmap(bitmap);

            if (data == null) {
                File mediaStorageDir = new File(Environment.getExternalStoragePublicDirectory(
                        Environment.DIRECTORY_PICTURES), "MyApplication");

                /**Create the storage directory if it does not exist*/
                if (!mediaStorageDir.exists()) {
                    if (!mediaStorageDir.mkdirs()) {

                    }
                }

                /**Create a media file name*/
                String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());


                imageFile = new File(mediaStorageDir.getPath() + File.separator +
                        "IMG_" + timeStamp + ".png");
            } else {
                Uri selectedImageURI = data.getData();
                imageFile = new File(getRealPathFromURI(selectedImageURI));
                String path = selectedImageURI.getPath();
                Log.e("path", path);
            }
        }
        else{
            Log.e("resultCode else",resultCode+"");
        }


    }

    private String getRealPathFromURI(Uri contentURI) {
        String result;
        Cursor cursor = getContentResolver().query(contentURI, null, null, null, null);
        if (cursor == null) { // Source is Dropbox or other similar local file path
            result = contentURI.getPath();
        } else {
            cursor.moveToFirst();
            int idx = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA);
            result = cursor.getString(idx);
            cursor.close();
        }
        Log.e("result", result);
        return result;
    }






    private void requestStoragePermission(){

        if (ActivityCompat.shouldShowRequestPermissionRationale(this,Manifest.permission.READ_EXTERNAL_STORAGE)){
            //If the user has denied the permission previously your code will come to this block
            //Here you can explain why you need this permission
            //Explain here why you need this permission
        }

        //And finally ask for the permission
        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, STORAGE_PERMISSION_CODE);
    }

    //This method will be called when the user will tap on allow or deny
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        //Checking the request code of our request
        if(requestCode == STORAGE_PERMISSION_CODE){

            //If permission is granted
            if(grantResults.length >0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){

                //Displaying a toast
                Toast.makeText(this,"Permission granted now you can read the storage",Toast.LENGTH_LONG).show();
            }else{
                //Displaying another toast if permission is not granted
                Toast.makeText(this,"Oops you just denied the permission",Toast.LENGTH_LONG).show();
            }
        }
    }


    // permission request return
    private boolean isReadStorageAllowed() {
        //Getting the permission status
        int result = ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE);

        //If permission is granted returning true
        if (result == PackageManager.PERMISSION_GRANTED)
            return true;

        //If permission is not granted returning false
        return false;
    }
    public boolean checkPassWordAndConfirmPassword(String password,String confirmPassword)
    {
        boolean pstatus = false;
        if (confirmPassword != null && password != null)
        {
            if (password.equals(confirmPassword))
            {
                pstatus = true;
            }
        }
        return pstatus;
    }
}

package com.example.pro.lif;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;

import android.os.AsyncTask;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


public class ProfileHandler extends AppCompatActivity {


    EditText mmEmailView;
    EditText mPasswordView;
    Button btn;
    String emaill;
    String passwordd;

    ProgressBar progressBar;
    JSONArray jsonArray;
    public static String STUDENT_ID;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);




        mmEmailView = (EditText)findViewById(R.id.email);
        mPasswordView = (EditText)findViewById(R.id.password);
        btn=(Button)findViewById(R.id.email_sign_in_button);
        ActionBar actionBar=getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);  //shows the arrow button displayed next to the application icon.
        actionBar.setHomeButtonEnabled(true);




    }

    public void onclick(View view) {

        attemptLogin();
    }

    //ProfileHandler button







    private void attemptLogin() {
        //Reset errors.
        mmEmailView.setError(null);
        mPasswordView.setError(null);
        emaill = mmEmailView.getText().toString();
        this.STUDENT_ID=emaill;

        passwordd = mPasswordView.getText().toString();

        boolean cancel = false;
        View focusView = null;


        // Check for a valid password, if the user entered one.
        if (TextUtils.isEmpty(passwordd)) {
            mPasswordView.setError(getString(R.string.error_field_required));
            focusView = mPasswordView;
            cancel = true;

        }else if(!isPasswordValid(passwordd)){
            mPasswordView.setError(getString(R.string.error_invalid_password));
            focusView = mPasswordView;
            cancel = true;
        }

        // Check for a valid email address.
        if (TextUtils.isEmpty(emaill)) {
            mmEmailView.setError(getString(R.string.error_field_required));
            focusView = mmEmailView;
            cancel = true;
        } else if (!isEmailValid(emaill)) {
            mmEmailView.setError(getString(R.string.error_invalid_email));
            focusView = mmEmailView;
            cancel = true;
        }


        if (cancel) {
            // There was an error; don't attempt ProfileHandler and focus the first
            // form field with an error.
            focusView.requestFocus();
        } else {
            // Show a progress spinner, and kick off a background task to
            // perform the user ProfileHandler attempt.

            progressBar=(ProgressBar)findViewById(R.id.login_progress);
            progressBar.setVisibility(ProgressBar.VISIBLE);
            disableTextView();
            String url ="http://192.168.43.60/allofme/lost-id-for-mobile/login.php";   //calling api connector for json array
            new GetAllCustomerTask().execute(new ApiConnector(url));

        }


    }

    private boolean isEmailValid(String email) {
        //TODO: Replace this with your own logic
        return email.contains("ate/") || email.contains("atr/");
    }

    private boolean isPasswordValid(String password) {
        //TODO: Replace this with your own logic
        return password.length() >= 4;
    }

    //to initialise json array
    private void ini(JSONArray jsonArray){
        this.jsonArray=jsonArray;
        login(emaill,passwordd);
    }



    private class GetAllCustomerTask extends AsyncTask<ApiConnector,Long,JSONArray>
    {
        @Override
        protected JSONArray doInBackground(ApiConnector... params) {
            //this execute in the background thread

            return params[0].GetAllStudents();

        }

        @Override
        protected void onPostExecute(JSONArray jsonArray) {
            //super.onPostExecute(jsonArray);

            ini(jsonArray);
        }
    }



    /*About class diagram *****************************************/

    public void disableTextView(){

        this.mmEmailView.setEnabled(false);
        this.mPasswordView.setEnabled(false);

        btn.setEnabled(false);



    }
    //Enabled Edittext when apply progress end
    public void EnableTextView(){
        this.mmEmailView.setEnabled(true);
        this.mPasswordView.setEnabled(true);


        btn.setEnabled(true);

    }
    //remove text from EditText
    public void removeText(){
        mmEmailView.setText("");
        mPasswordView.setText("");

    }



    public void login(String username, String password){
        String result = null;


        if(jsonArray !=null){

            for (int i=0;i<jsonArray.length();i++) {
                JSONObject json = null;
                try {
                    json = jsonArray.getJSONObject(i);

                    if (username.equals(json.getString("studentId")) && password.equals(json.getString("password"))) {
                        result=null;
                        progressBar.setVisibility(ProgressBar.INVISIBLE);
                        Intent intent = new Intent(this, Home.class);

                        startActivity(intent);



                    }
                    else if(username.equals(json.getString("studentId")) && password.equals(json.getString("password"))){

                        result="Your user name or password are incorrect";
                    }


                } catch (JSONException e) {
                    result="failed";

                }

            }
            if(result!=null) {
                progressBar.setVisibility(ProgressBar.INVISIBLE);
                EnableTextView();
                removeText();
                Toast.makeText(this, result, Toast.LENGTH_SHORT).show();
            }

        }
        else{
            progressBar.setVisibility(ProgressBar.INVISIBLE);
            EnableTextView();
            Toast.makeText(this,"network error, check the connection",Toast.LENGTH_SHORT).show();
        }

    }
    public void logout(){
        Intent intent = new Intent(getApplicationContext(), ProfileHandler.class);
        startActivity(intent);


    }

    public void forget(String answer){


    }

}

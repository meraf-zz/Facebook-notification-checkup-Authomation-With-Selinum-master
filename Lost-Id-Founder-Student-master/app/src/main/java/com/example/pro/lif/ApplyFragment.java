package com.example.pro.lif;

/**
 * Created by pro on 7/23/17.
 */

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ApplyFragment extends Fragment implements View.OnClickListener{
    Button btn;
    EditText fname_view;
    EditText lname_view;
    EditText studentid_view;

    ProgressBar progressBar;

    /* data string variable  */
    String fName;
    String lName;
    String studentId;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_apply, container, false);

        btn =(Button) rootView.findViewById(R.id.apply_button);
        btn.setOnClickListener(this);

        fname_view=(EditText) rootView.findViewById(R.id.fname);
        lname_view=(EditText) rootView.findViewById(R.id.lname);
        studentid_view=(EditText) rootView.findViewById(R.id.student_id);
        progressBar=(ProgressBar) rootView.findViewById(R.id.apply_progress);
        return rootView;
    }


    @Override
    public void onClick(View v) {

        //Toast.makeText(this.getActivity(),fname_view.getText().toString(),Toast.LENGTH_SHORT).show();
        attemptLogin();

    }
    private void attemptLogin() {
        //Reset errors.
        fname_view.setError(null);
        lname_view.setError(null);
        studentid_view.setError(null);
        //
        fName= fname_view.getText().toString();
        lName=lname_view.getText().toString();
        studentId=studentid_view.getText().toString();



        boolean cancel = false;
        View focusView = null;


        // Check for a valid fname, if the user entered one.
        if (TextUtils.isEmpty(fName)) {
            fname_view.setError(getString(R.string.error_field_required));
            focusView = fname_view;
            cancel = true;

        }else if(!isNameValid(fName)){
            fname_view.setError(getString(R.string.error_invalid_name));
            focusView = fname_view;
            cancel = true;
        }

        //check for valid lname
        if (TextUtils.isEmpty(lName)) {
            lname_view.setError(getString(R.string.error_field_required));
            focusView = lname_view;
            cancel = true;

        }else if(!isNameValid(lName)){
            lname_view.setError(getString(R.string.error_invalid_name));
            focusView = lname_view;
            cancel = true;
        }

        // Check for a valid email address.
        if (TextUtils.isEmpty(studentId)) {
            studentid_view.setError(getString(R.string.error_field_required));
            focusView = studentid_view;
            cancel = true;
        } else if (!isStudentIdValid(studentId)) {
            studentid_view.setError(getString(R.string.error_invalid_email));
            focusView = studentid_view;
            cancel = true;
        }


        if (cancel) {
            // There was an error; don't attempt ProfileHandler and focus the first
            // form field with an error.
            focusView.requestFocus();
        } else {
            // Show a progress spinner, and kick off a background task to
            // perform the user ProfileHandler attempt.
            progressBar.setVisibility(ProgressBar.VISIBLE);

            disableTextView();

            sendRequest(fName,lName,studentId);




        }


    }

    private boolean isStudentIdValid(String id) {
        //TODO: Replace this with your own logic
        return true;
    }

    private boolean isNameValid(String name) {
        //TODO: Replace this with your own logic

        return true;
    }


    //disable Edittext when apply in pennding
    public void disableTextView(){

        fname_view.setEnabled(false);
        lname_view.setEnabled(false);
        studentid_view.setEnabled(false);

        btn.setEnabled(false);
    }
    //Enabled Edittext when apply progress end
    public void EnableTextView(){
        fname_view.setEnabled(true);
        lname_view.setEnabled(true);
        studentid_view.setEnabled(true);
        //btn.setEnabled(true);

    }
    //remove text from EditText
    public void removeText(){
        fname_view.setText("");
        lname_view.setText("");
        studentid_view.setText("");
    }



    //net work
    public void sendRequest(String fn, String ln, String id){
        class SendPostReqAsyncTask extends AsyncTask<String, Void, String> {


            @Override
            protected String doInBackground(String... params) {

                String paramUsername = params[0];
                String paramAddress = params[1];
                String paramId = params[2];





                List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
                nameValuePairs.add(new BasicNameValuePair("fname", fName));
                nameValuePairs.add(new BasicNameValuePair("lname",lName));
                nameValuePairs.add(new BasicNameValuePair("studentid",studentId));
                String result;

                try {
                    HttpClient httpClient = new DefaultHttpClient();
                    HttpPost httpPost = new HttpPost(
                            "http://192.168.43.60/allofme/lost-id-for-mobile/apply.php");
                    httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairs));

                    HttpResponse response = httpClient.execute(httpPost);

                    HttpEntity entity = response.getEntity();

                    result="success";

                } catch (ClientProtocolException e) {
                    result="network error, check the connection";

                } catch (IOException e) {
                    result="failed";
                }
                return result;
            }

            @Override
            protected void onPostExecute(String result) {
                super.onPostExecute(result);
                progressBar.setVisibility(ProgressBar.INVISIBLE);
                Toast.makeText(getActivity(), result, Toast.LENGTH_SHORT).show();
                //enable EditText
                //EnableTextView();
                //remove text
                removeText();



            }
        }
        SendPostReqAsyncTask sendPostReqAsyncTask = new SendPostReqAsyncTask();
        sendPostReqAsyncTask.execute(fn,ln,id);
    }


}

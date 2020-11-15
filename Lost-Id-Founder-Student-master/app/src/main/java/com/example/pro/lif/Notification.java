package com.example.pro.lif;


import android.content.Intent;
import android.os.AsyncTask;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONStringer;

import java.util.ArrayList;

public class Notification extends AppCompatActivity {





    private JSONArray jsonArray;
    private ListView listView;

    /* variable for fetching data from database*/

    ArrayList<String> description;
    ArrayList<String> date;
    Integer[] imageArray={ R.drawable.addis_ababa_unversity_logo};
    ProgressBar progressBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);
        ActionBar actionBar=getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);  //shows the arrow button displayed next to the application icon.
        actionBar.setHomeButtonEnabled(true);
        actionBar.setElevation(0);



        progressBar=(ProgressBar)findViewById(R.id.progres_notifcation);

        listView = (ListView)findViewById(R.id.notification_listview);




    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        CreateMenu(menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return MenuChoice(item);
    }

    private void CreateMenu(Menu menu) {
        MenuItem mnu1 = menu.add(0, 0, 0, "Item 1");
        {
            mnu1.setIcon(R.drawable.refresh);
            mnu1.setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM);


        }
    }

    private boolean MenuChoice(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:

                Intent i = new Intent(this, Home.class);
                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(i);
                return true;
            case 0:
                progressBar.setVisibility(ProgressBar.VISIBLE);
                String url ="http://192.168.43.60/allofme/lost-id-for-mobile/notification.php";
                //calling api connector for json array
                new GetAllNotificationTask().execute(new ApiConnector(url));


        }
        return false;
    }


    /*net work*/

    private class GetAllNotificationTask extends AsyncTask<ApiConnector,Long,JSONArray>
    {
        @Override
        protected JSONArray doInBackground(ApiConnector... params) {
            //this execute in the background thread

            return params[0].GetAllStudents();

        }

        @Override
        protected void onPostExecute(JSONArray jsonArray) {
            //super.onPostExecute(jsonArray);

            insertIntoListView(jsonArray);
        }
    }

    //initialise

    private void insertIntoListView(JSONArray jsonArray){
        if(jsonArray !=null) {
            String result = null;
            description = new ArrayList<String>(0);
            date = new ArrayList<String>(0);

            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject json = null;

                try {
                    json = jsonArray.getJSONObject(i);
                    if (ProfileHandler.STUDENT_ID.equals(json.getString("studentId"))) {

                        description.add(json.getString("description"));
                        date.add(json.getString("date"));
                        result = null;

                    } else {
                        result = "Empty";
                    }

                } catch (JSONException e) {
                    result = "failed";

                }

            }


            CustomListAdapter customListAdapter=new CustomListAdapter(this,date,description,imageArray);

            //adding it to the list view.

            listView.setAdapter(customListAdapter);
            progressBar.setVisibility(ProgressBar.GONE);

            if(result !=null) {
                progressBar.setVisibility(ProgressBar.GONE);

                Toast.makeText(this, result, Toast.LENGTH_SHORT).show();

            }

        }
        else{
            progressBar.setVisibility(ProgressBar.GONE);
            Toast.makeText(this,"network error, check the connection",Toast.LENGTH_SHORT).show();
        }



    }
}

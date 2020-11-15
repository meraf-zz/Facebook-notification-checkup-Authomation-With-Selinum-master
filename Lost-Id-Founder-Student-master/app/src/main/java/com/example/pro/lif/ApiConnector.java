package com.example.pro.lif;

import android.util.Log;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;

import java.io.IOException;

/**
 * Created by pro on 7/11/17.
 */

public class ApiConnector {
    String url;
    public ApiConnector(String url){
        this.url=url;
    }

    public JSONArray GetAllStudents(){
        //url for getting all customers


        //get httpResponse object from url
        //get httpEntity

        HttpEntity httpEntity=null;

        try {

            DefaultHttpClient httpClient = new DefaultHttpClient();
            HttpGet httpGet = new HttpGet(url);

            HttpResponse httpResponse= httpClient.execute(httpGet);



            httpEntity = httpResponse.getEntity();
        }
        catch (ClientProtocolException e){
            e.printStackTrace();
        }
        catch (IOException e){
            e.printStackTrace();
        }
        //conver http to json array
        JSONArray jsonArray=null;
        if(httpEntity !=null){
            try{
                String entityResponse= EntityUtils.toString(httpEntity);
                Log.e("Entity Response : ", entityResponse);

                jsonArray =new JSONArray(entityResponse);

            }
            catch (JSONException e){
                e.printStackTrace();

            }
            catch (IOException e){
                e.printStackTrace();

            }
        }
        return jsonArray;
    }
}

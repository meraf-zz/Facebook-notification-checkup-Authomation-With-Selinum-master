package com.example.pro.lif;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by pro on 1/3/01.
 */

public class CustomListAdapter extends ArrayAdapter {

    //to reference the Activity
    private final Activity context;

    //to store the animal images
    private final Integer[] imageIDArray;

    //to store the list of countris
    private final ArrayList<String> nameArray;

    //to store the info of counteris
    private final ArrayList<String> infoArray;

    public CustomListAdapter(Activity context, ArrayList<String> nameArrayParam, ArrayList<String> infoArrayParam, Integer[] imageIDArrayParam)
    {
        super(context,R.layout.listview_row,nameArrayParam);
        this.context=context;
        this.imageIDArray=imageIDArrayParam;
        this.nameArray=nameArrayParam;
        this.infoArray=infoArrayParam;
    }
    public View getView(int position, View view, ViewGroup parent)
    {
        LayoutInflater inflater=context.getLayoutInflater();
        View rowView=inflater.inflate(R.layout.listview_row,null,true);

        //this code gets reference to objects in the list listview_row.xml file
        TextView nameTextField=(TextView)rowView.findViewById(R.id.nameTextViewID);
        TextView infoTextField=(TextView)rowView.findViewById(R.id.infoTextViewID);
        ImageView imageView=(ImageView)rowView.findViewById(R.id.imageView1ID);

        //this code set the value of the objects to values from the array

        nameTextField.setText(nameArray.get(position));
        infoTextField.setText(infoArray.get(position));
        imageView.setImageResource(imageIDArray[0]);
        return rowView;
    }
}

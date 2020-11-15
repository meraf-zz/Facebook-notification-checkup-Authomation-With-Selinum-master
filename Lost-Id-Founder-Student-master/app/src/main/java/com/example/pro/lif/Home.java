package com.example.pro.lif;

import android.content.DialogInterface;
import android.support.v7.app.ActionBar;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.pro.lif.adapter.TabsPagerAdapter;

public class Home extends AppCompatActivity implements ActionBar.TabListener{
    private ViewPager viewPager;
    private TabsPagerAdapter mAdapter;
    private android.support.v7.app.ActionBar actionBar;
    private String[] tabs = new String[]{"Home", "Apply", "About"};
    public Home() {
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        //getActionBar().setDisplayHomeAsUpEnabled(true);
        this.setContentView(R.layout.activity_home);

        //intialition

        viewPager = (ViewPager)findViewById(R.id.pager);
        actionBar=getSupportActionBar();

        mAdapter = new TabsPagerAdapter(getSupportFragmentManager()); // for tab
        viewPager.setAdapter(mAdapter);
        actionBar.setDisplayHomeAsUpEnabled(true);  //shows the arrow button displayed next to the application icon.
        actionBar.setHomeButtonEnabled(true);
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);


        //ading tabs

        /*String[] var5 = this.tabs;
        int var4 = this.tabs.length;

        for(int var3 = 0; var3 < var4; ++var3) {
            String tab_name = var5[var3];
            this.actionBar.addTab(this.actionBar.newTab().setText(tab_name).setTabListener(this));
        }

        this.viewPager.setOnPageChangeListener(new OnPageChangeListener() {
            public void onPageSelected(int position) {
                MainActivity.this.actionBar.setSelectedNavigationItem(position);
            }

            public void onPageScrolled(int arg0, float arg1, int arg2) {
            }

            public void onPageScrollStateChanged(int arg0) {
            }
        });*/

        for(String tab_name:tabs){
            actionBar.addTab(actionBar.newTab().setText(tab_name).setTabListener(this));
        }
        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            public void onPageSelected(int position) {
                actionBar.setSelectedNavigationItem(position);
            }

            public void onPageScrolled(int arg0, float arg1, int arg2) {
            }

            public void onPageScrollStateChanged(int arg0) {
            }
        });
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
            mnu1.setIcon(R.drawable.notifi_cation);
            mnu1.setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM);

        }
        MenuItem mnu2 = menu.add(0, 1, 1, "Item 2");
        {


            mnu2.setIcon(R.drawable.log_out);
            mnu2.setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM);
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
                Intent intent=new Intent(this,Notification.class);
                startActivity(intent);

                return true;
            case 1:


                showmydialog();


        }
        return false;
    }


    @Override
    public void onTabSelected(ActionBar.Tab tab, android.support.v4.app.FragmentTransaction ft) {
        viewPager.setCurrentItem(tab.getPosition());



    }

    @Override
    public void onTabUnselected(ActionBar.Tab tab, android.support.v4.app.FragmentTransaction ft) {

    }

    @Override
    public void onTabReselected(ActionBar.Tab tab, android.support.v4.app.FragmentTransaction ft) {

    }


    /*show dialog*/
    public void showmydialog(){

        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setMessage(R.string.decision);

        alertDialogBuilder.setPositiveButton(R.string.positive_button,
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface arg0, int arg1) {
                        Intent intent = new Intent(getApplicationContext(),ProfileHandler.class);
                        startActivity(intent);




                    }
                });
        alertDialogBuilder.setNegativeButton(R.string.negative_button,
                new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });

        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();


    }
}

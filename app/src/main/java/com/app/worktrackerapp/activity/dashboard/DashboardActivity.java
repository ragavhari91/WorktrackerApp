package com.app.worktrackerapp.activity.dashboard;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.app.worktrackerapp.R;
import com.app.worktrackerapp.model.User;
import com.google.gson.Gson;


public class DashboardActivity extends AppCompatActivity {

    DrawerLayout drawerLayout;
    ActionBarDrawerToggle drawerToggle;
    NavigationView navigation;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        initInstances();

        TextView tv_user_name       = (TextView) findViewById(R.id.textview_user_name);
        TextView tv_user_emailid    = (TextView) findViewById(R.id.textview_user_email);

        Intent userprofileintent = getIntent();
        Gson userprofilegson     = new Gson();


        // Get User Intent data
        User userobj = userprofilegson.fromJson(userprofileintent.getStringExtra("USERDETAILS"), User.class);

        //assign user profile information
        String user_first_name = userobj.getUser_first_name();
        String user_last_name  = userobj.getUser_last_name();
        String name            = user_first_name+" "+user_last_name;
        String user_emailid    = userobj.getUser_email();

        // set user information on sidebar navigation
        tv_user_name.setText(name);
        tv_user_emailid.setText(user_emailid);

    }



    private void initInstances() {
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        drawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);
        drawerToggle = new ActionBarDrawerToggle(DashboardActivity.this, drawerLayout, R.string.hello_world, R.string.hello_world);
        drawerLayout.setDrawerListener(drawerToggle);

        navigation = (NavigationView) findViewById(R.id.navigation_view);
        navigation.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {
                int id = menuItem.getItemId();
                switch (id) {
                    case R.id.nav_home:
                        //Do some thing here
                        // add navigation drawer item onclick method here
                        break;
                    case R.id.nav_manage_projects:
                        //Do some thing here
                        // add navigation drawer item onclick method here
                        break;
                    case R.id.nav_add_project:
                        //Do some thing here
                        // add navigation drawer item onclick method here
                        break;
                }
                return false;
            }
        });

    }

    @Override
    public void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        drawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        drawerToggle.onConfigurationChanged(newConfig);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.navigation_view_items, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (drawerToggle.onOptionsItemSelected(item))
            return true;

        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}

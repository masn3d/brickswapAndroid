package com.example.silas.testbrickswap.brickswap;

import android.content.Intent;
import android.content.res.Configuration;
import android.support.design.widget.NavigationView;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.example.silas.testbrickswap.R;
import com.example.silas.testbrickswap.interfaces.IFragmentListener;


public class MainActivity extends AppCompatActivity implements IFragmentListener {

    private DrawerLayout drawerLayout;
    private Toolbar toolbar;
    private NavigationView nvDrawer;
    private ActionBarDrawerToggle drawerToggle;

    public static String userID;
    public static String userName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //get information from login
        Intent intent = getIntent();
        Bundle extras = getIntent().getExtras();

        if (extras != null) {
            userID = intent.getStringExtra("userID");
            userName = intent.getStringExtra("userName");
        }

        // Set a Toolbar to replace the ActionBar.
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Find our drawer view
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        // Find our drawer view
        nvDrawer = (NavigationView) findViewById(R.id.nvView);
        // Setup drawer view
        setupDrawerContent(nvDrawer);

        drawerToggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.drawer_open, R.string.drawer_close) {
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
            }
        };


        drawerLayout.setDrawerListener(drawerToggle);

        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // The action bar home/up action should open or close the drawer.
        //switch (item.getItemId()) {
        //    case android.R.id.home:
        //        drawerLayout.openDrawer(GravityCompat.START);
        //        return true;
        //}

        if (drawerToggle.onOptionsItemSelected(item)) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    // `onPostCreate` called when activity start-up is complete after `onStart()`
    // NOTE! Make sure to override the method with only a single `Bundle` argument
    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        drawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        drawerToggle.onConfigurationChanged(newConfig);
    }


    // ...

    private void setupDrawerContent(NavigationView navigationView) {
        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {
                        selectDrawerItem(menuItem);
                        return true;
                    }
                });
    }

    public void selectDrawerItem(MenuItem menuItem) {
        // Create a new fragment and specify the fragment to show based on nav item clicked
        Fragment fragment = null;
        Class fragmentClass = null;
        Bundle bundle = null;

        switch (menuItem.getItemId()) {
            case R.id.home_fragment:
                fragmentClass = HomeFragment.class;
                break;
            case R.id.search_fragment:
                fragmentClass = ResultsFragment.class;
                bundle = new Bundle();
                bundle.putString("userID", userID);
                break;
            case R.id.projects_fragment:
                fragmentClass = MyProjectsFragment.class;
                bundle = new Bundle();
                bundle.putString("userID", userID);

                break;
            case R.id.logout_fragment: //used for temp search

                //setLogoutText();
                break;
            default:
                fragmentClass = HomeFragment.class;
        }

        try {
            fragment = (Fragment) fragmentClass.newInstance();


        } catch (Exception e) {
            e.printStackTrace();

        }

        fragment.setArguments(bundle);
        // Insert the fragment by replacing any existing fragment
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.flContent, fragment).commit();

        // Highlight the selected item has been done by NavigationView
        menuItem.setChecked(true);
        // Set action bar title
        setTitle(menuItem.getTitle());
        // Close the navigation drawer
        drawerLayout.closeDrawers();
    }

    @Override
    public void search(String input) {
        String text = input;

        Fragment fragment = null;
        Class fragmentClass = ListViewFragment.class;

        Bundle bundle = new Bundle();
        bundle.putString("userID", userID);
        bundle.putString("input", text);

        try {
            fragment = (Fragment) fragmentClass.newInstance();
            fragment.setArguments(bundle);
        } catch (Exception e) {
            e.printStackTrace();
        }

        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.flContent, fragment).commit();

    }

    @Override
    public void getDetails(LegoSet item) {

        String posterID = item.getPosterID();
        String postID = item.getId();


        Fragment fragment = null;
        Class fragmentClass = DetailsFragment.class;

        Bundle bundle = new Bundle();
        bundle.putString("posterID", posterID);
        bundle.putString("postID", postID);




        try {
            fragment = (Fragment) fragmentClass.newInstance();
            fragment.setArguments(bundle);

        } catch (Exception e) {
            e.printStackTrace();
        }

        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.flContent, fragment).commit();



      //  DetailsFragment detailsFragment = (DetailsFragment) getSupportFragmentManager().findFragmentByTag("detailsFragment");
      //  detailsFragment.setLegoSetData(item);


    }

    public void setLogoutText() {
        TextView logout;
        //logout = (TextView) findViewById(R.id.textViewLogOut);
        //logout.setVisibility(View.VISIBLE);
    }

}
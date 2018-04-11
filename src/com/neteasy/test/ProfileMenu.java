package com.neteasy.test;

import com.neteasy.test.MainActivity.loginlistener;

import android.os.Bundle;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class ProfileMenu extends Activity {

	private DrawerLayout mdrawerLayout;
	private ListView mlistView;
	String Suser, Spass, Sname, Sage, Semail,Scountry,SInterested;
	private ActionBarDrawerToggle mDrawerToggle;
	 private CharSequence mDrawerTitle;
	    private CharSequence mTitle;
	
	private String[] menu = {"Search","Inbox","Edit Profile","My Profile","Settings","Log out"};
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		 mTitle = mDrawerTitle = getTitle();
		super.onCreate(savedInstanceState);
		setContentView(R.layout.profilemenu);
		mdrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
		mlistView = (ListView) findViewById(R.id.left_drawer);
		mlistView.setOnItemClickListener(new SlideMenuClickListener());
		mdrawerLayout.setDrawerShadow(R.drawable.drawer_shadow, GravityCompat.START);
		mlistView.setAdapter(new ArrayAdapter<String>(this,R.layout.drawer_list_item,menu));
		getActionBar().setDisplayHomeAsUpEnabled(true);
		getActionBar().setHomeButtonEnabled(true);
		Fragment fragment=new Search();
		 FragmentManager fragmentManager = getFragmentManager();
	        fragmentManager.beginTransaction()
	                .replace(R.id.content_frame, fragment).commit();
	        
	        // update selected item and title, then close the drawer
	        mlistView.setItemChecked(0, true);
	        mlistView.setSelection(0);
	        setTitle("SEARCH");
	        Bundle gotbasket=getIntent().getExtras();
	        Suser=gotbasket.getString("user");
			Sage=gotbasket.getString("age");
			Semail=gotbasket.getString("email");
			Scountry=gotbasket.getString("country");
			Sage=gotbasket.getString("age");
			SInterested=gotbasket.getString("interested");
		mDrawerToggle = new ActionBarDrawerToggle(this, mdrawerLayout, R.drawable.ic_drawer, R.string.app_name, R.string.hello_world){

			@Override
			public void onDrawerClosed(View drawerView) {
				// TODO Auto-generated method stub
				 getActionBar().setTitle(mTitle);
				invalidateOptionsMenu();
			}

			@Override
			public void onDrawerOpened(View drawerView) {
				// TODO Auto-generated method stub
				 getActionBar().setTitle(mDrawerTitle);
				invalidateOptionsMenu();
			}
			
		};
		 mdrawerLayout.setDrawerListener(mDrawerToggle);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.cool_menu, menu);
		return true;
	}
    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        // If the nav drawer is open, hide action items related to the content view
        boolean drawerOpen = mdrawerLayout.isDrawerOpen(mlistView);
        menu.findItem(R.id.aboutUs).setVisible(!drawerOpen);
        menu.findItem(R.id.exit).setVisible(!drawerOpen);
        return super.onPrepareOptionsMenu(menu);
    }
    public boolean onOptionsItemSelected(MenuItem item) {
  		// TODO Auto-generated method stub
  		 switch(item.getItemId()){
  		    case R.id.aboutUs:
  		    	Intent nIntent=new Intent("com.neteasy.test.ABOUT");
  		    	startActivity(nIntent);
  		    	break;
  		    case R.id.pref:
  		    	Intent i=new Intent("com.neteasy.test.PREF");
  		    	startActivity(i);
  		    	break;
  	        case R.id.exit:
  		    	finish();
  		    	break;	
  		    }
  		    return false;
  	}
    
    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        // Sync the toggle state after onRestoreInstanceState has occurred.
        mDrawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        // Pass any configuration change to the drawer toggls
        mDrawerToggle.onConfigurationChanged(newConfig);
    }

public class SlideMenuClickListener implements OnItemClickListener {

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
            long id) {
		 displayView(position);

	}

}
private void displayView(int position) {
    // update the main content by replacing fragments
    Fragment fragment = null;
    switch (position) {
    case 0:
    	fragment=new Search();
    	break;
    case 1:
        fragment = new Inbox();
        break;
    case 2:
        fragment = new EditProfile();
        break;
    case 3:
        fragment = new MyProfile(Suser, Sname, Sage, Semail,Scountry,SInterested);
        break;
    case 4:
        fragment = new Settings();
        break;
    case 5:
    	Intent i=new Intent("com.neteasy.test.MAINACTIVITY");
        startActivity(i);
        break;
    default:
        break;
    }

    if (fragment != null) {
        FragmentManager fragmentManager = getFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.content_frame, fragment).commit();

        // update selected item and title, then close the drawer
        mlistView.setItemChecked(position, true);
        mlistView.setSelection(position);
        setTitle(menu[position]);
        mdrawerLayout.closeDrawer(mlistView);
    } else {
        // error in creating fragment
        Log.e("MainActivity", "Error in creating fragment");
    }
}
}



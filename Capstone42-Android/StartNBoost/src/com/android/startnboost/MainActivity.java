package com.android.startnboost;

import android.os.Bundle;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Intent;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

public class MainActivity extends Activity {
	public static final String PREFS_NAME = "LoginPrefs";
	private String[] mNavigationDrawerItemTitles;
	private DrawerLayout mDrawerLayout;
	private ListView mDrawerList;
	private ActionBarDrawerToggle mDrawerToggle;
    // nav drawer title
    private CharSequence mDrawerTitle;
 
    // used to store app title
    private CharSequence mTitle;
    private String id;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		Bundle extras = getIntent().getExtras();
		id = extras.getString("userId");
		
		// for proper titles
				mTitle = mDrawerTitle = getTitle();
				
				// initialize properties
				mNavigationDrawerItemTitles = getResources().getStringArray(R.array.navigation_drawer_items_array);
		        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
		        mDrawerList = (ListView) findViewById(R.id.left_drawer);
		        
		        // list the drawer items
		        ObjectDrawerItem[] drawerItem = new ObjectDrawerItem[5];
		   
		        drawerItem[0] = new ObjectDrawerItem(R.drawable.ic_action_profile, "Profile");
		        drawerItem[1] = new ObjectDrawerItem(R.drawable.ic_action_onfire, "Onfire");
		        drawerItem[2] = new ObjectDrawerItem(R.drawable.ic_action_timeline, "Timeline");
		        drawerItem[3] = new ObjectDrawerItem(R.drawable.ic_action_ideas, "New Ideas");
		        drawerItem[4] = new ObjectDrawerItem(R.drawable.ic_action_startup, "Startup Products");
		        
		        // Pass the folderData to our ListView adapter
		        DrawerItemCustomAdapter adapter = new DrawerItemCustomAdapter(this, R.layout.listview_item_row, drawerItem);
		        
		        // Set the adapter for the list view
		        mDrawerList.setAdapter(adapter);
		        
		        // set the item click listener
		        mDrawerList.setOnItemClickListener(new DrawerItemClickListener());
		        
		     // for app icon control for nav drawer
		        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
		        mDrawerToggle = new ActionBarDrawerToggle(
		                this,                  /* host Activity */
		                mDrawerLayout,         /* DrawerLayout object */
		                R.drawable.ic_drawer,  /* nav drawer icon to replace 'Up' caret */
		                R.string.drawer_open,  /* "open drawer" description */
		                R.string.drawer_close  /* "close drawer" description */
		                ) {
		        	
		            /** Called when a drawer has settled in a completely closed state. */
		            public void onDrawerClosed(View view) {
		                super.onDrawerClosed(view);
		                getActionBar().setTitle(mTitle);
		            }

		            /** Called when a drawer has settled in a completely open state. */
		            public void onDrawerOpened(View drawerView) {
		                super.onDrawerOpened(drawerView);
		                getActionBar().setTitle(mDrawerTitle);
		            }
		        };

		        // Set the drawer toggle as the DrawerListener
		        mDrawerLayout.setDrawerListener(mDrawerToggle);
		        
		        // enable ActionBar app icon to behave as action to toggle nav drawer
		        getActionBar().setDisplayHomeAsUpEnabled(true);
		        getActionBar().setHomeButtonEnabled(true);
		        
		        if (savedInstanceState == null) {
		            // on first time display view for first nav item
		        	selectItem(0);
		        }
		
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		
        // The action bar home/up action should open or close the drawer.
        // ActionBarDrawerToggle will take care of this.
       if (mDrawerToggle.onOptionsItemSelected(item)) {
           return true;
       }
       if (item.getItemId() == R.id.logout) {
           Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
           startActivity(intent);
       }
       return super.onOptionsItemSelected(item);
	}
	
	@Override
	public void onBackPressed() {
	    Intent intent = new Intent(Intent.ACTION_MAIN);
	    intent.addCategory(Intent.CATEGORY_HOME);
	    startActivity(intent);
	} 
	
	@Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        // Sync the toggle state after onRestoreInstanceState has occurred.
        mDrawerToggle.syncState();
    }
	
	private class DrawerItemClickListener implements ListView.OnItemClickListener {
	    
	    @Override
	    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
	        selectItem(position);
	    }
	    
	}
	 
	private void selectItem(int position) {
	    Fragment fragment = null;
	    
	    switch (position) {
	    case 0:
	        fragment = new ProfileFragment(id);
	        break;
	    case 1:
	        fragment = new OnfireFragment();
	        break;
	    case 2:
	        fragment = new TimelineFragment();
	        break;
	    case 3:
	    	fragment = new NewideasFragment();
	    	break;
	    case 4:
	    	fragment = new StartupFragment();
	    	break;
	    default:
	        break;
	    }
	    
	    if (fragment != null) {
	        FragmentManager fragmentManager = getFragmentManager();
	        fragmentManager.beginTransaction().replace(R.id.content_frame, fragment).commit();
	 
	        mDrawerList.setItemChecked(position, true);
	        mDrawerList.setSelection(position);
	        setTitle(mNavigationDrawerItemTitles[position]);
	        mDrawerLayout.closeDrawer(mDrawerList);
	        
	    }else {
	        Log.e("MainActivity", "Error in creating fragment");
	    }
	}
	
	@Override
    public void setTitle(CharSequence title) {
        mTitle = title;
        getActionBar().setTitle(mTitle);
    }
}

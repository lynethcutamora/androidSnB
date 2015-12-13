package com.android.startnboost;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends Activity {
	
	private String[] mNavigationDrawerItemTitles;
	private DrawerLayout mDrawerLayout;
	private ListView mDrawerList;
	private ActionBarDrawerToggle mDrawerToggle;

	public Userdtl u1;
    // nav drawer title
    private CharSequence mDrawerTitle;
 
    // used to store app title
    private CharSequence mTitle;
    
    private String lname = "ESTOSE";
    private String id;
    private String[] user_dtl;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		Bundle extras = getIntent().getExtras();
		id = extras.getString("userId");
		ReadData task1 = new ReadData();
		task1.execute(new String[]{"http://192.168.1.107/androidSnB/connectSNBDB/readbyid.php?userId=" + id});
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

	private class ReadData extends AsyncTask<String, Void, Boolean>{
    	ProgressDialog dialog = new ProgressDialog(MainActivity.this);
		@Override
		protected void onPreExecute() {
			dialog.setMessage("Loading...");
			dialog.show();
		}

    	
    	String text = "";
		
		ArrayList<String> list1;
		
		@Override
		protected Boolean doInBackground(String... urls) {
			InputStream is1;				
			for(String url1 : urls){
				//Read from web to InputStream
				try {					
					HttpClient client = new DefaultHttpClient();
					HttpPost post = new HttpPost(url1);					
					HttpResponse response = client.execute(post);
					is1 = response.getEntity().getContent();
					
				} catch (ClientProtocolException e) {
					Toast.makeText(MainActivity.this, e.toString(), Toast.LENGTH_LONG).show();
					return false;
				} catch (IOException e) {
					Toast.makeText(MainActivity.this, e.toString(), Toast.LENGTH_LONG).show();
					return false;
				}
				//end of Read from web to InputStream
				
				//Convert from InputStream to String Text
				BufferedReader reader;				
				try {
					reader = new BufferedReader(new InputStreamReader(is1,"iso-8859-1"), 8);
					String line = null;
					while ((line = reader.readLine()) != null) {
						text += line + "\n";
					}
					is1.close();
				} catch (UnsupportedEncodingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				//end of Convert from InputStream to String Text
				
				//Convert from Text to JSON and add to ArrayList list1
				list1 = new ArrayList<String>();
				try {
					JSONArray jArray = new JSONArray(text);
					for(int i=0;i<jArray.length();i++){
						JSONObject jsonData = jArray.getJSONObject(i);						
						u1 = new Userdtl();
						u1.setlname(jsonData.getString("user_lName"));
						u1.setfname(jsonData.getString("user_fName"));
						u1.setmidinit(jsonData.getString("user_midInit"));
						//u1.setage(jsonData.getString("user_age"));
						//u1.setgender(jsonData.getString("user_gender"));
						//u1.setdescription(jsonData.getString("user_shortSelfDescription"));
						//u1.setnameofbusiness(jsonData.getString("user_nameOfBusiness"));
						//u1.setbusinesstype(jsonData.getString("user_businessType"));
					}
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				//end of Convert from Text to JSON and add to ArrayList list1
			}
			return true;
			
		}
		@Override
		protected void onPostExecute(Boolean result) {
			if(result == true){
				user_dtl = new String[]{u1.getlname()};
			}
			else{
			}
			dialog.dismiss();
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
       
       return super.onOptionsItemSelected(item);
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
	        fragment = new ProfileFragment(user_dtl);
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

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
import android.annotation.SuppressLint;
import android.app.Fragment;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

@SuppressLint("ValidFragment")
public class ProfileFragmentCompany extends Fragment {
	@SuppressLint("ValidFragment")
	private String user,picname;
	
	public ProfileFragmentCompany(String user){
		user = user;
	}
	
	private TextView c_userlname,url;
    private TextView c_userfname,c_datejoined;
    private TextView c_usermi,c_selfdescription;
    private ImageView c_image;
    @Override
    public View onCreateView(LayoutInflater inflater,
            ViewGroup container, Bundle savedInstanceState){
        // -- inflate the layout for this fragment
    	View rootView = inflater.inflate(R.layout.fragment_profile_company, container, false);
    	readData3(user);
    	readData1(user);
    	readData4(user);
    	
        // Set the Text to try this out
    	c_userlname = (TextView) rootView.findViewById(R.id.user_lName_company);
    	c_userfname = (TextView) rootView.findViewById(R.id.user_fName_company);
    	c_usermi = (TextView) rootView.findViewById(R.id.user_midInit_company);
    	c_selfdescription = (TextView) rootView.findViewById(R.id.company_selfdescription);
    	c_datejoined = (TextView) rootView.findViewById(R.id.company_datejoined);
    	c_image = (ImageView) rootView.findViewById(R.id.company_profilepic);
    	
		return rootView;
    }
    
    private void readData1(String userid){
		ReadData1 task1 = new ReadData1();
		task1.execute(new String[]{"http://192.168.1.107/androidSnB/connectSNBDB/profilefragment1.php?userId=" + userid});
	}
    
    private void readData3(String userid){
		ReadData3 task1 = new ReadData3();
		task1.execute(new String[]{"http://192.168.1.107/androidSnB/connectSNBDB/profilefragment3.php?userId=" + userid});
	}
    private void readData4(String userid){
    	ReadData4 task1 = new ReadData4();
    	task1.execute(new String[]{"http://192.168.1.107/androidSnB/connectSNBDB/profilefragment4.php?userId=" + userid});
    }
    
    private class ReadData1 extends AsyncTask<String, Void, Boolean>{
    	String text = "";
		
		ArrayList<String> list1;
		Users u1;
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
					return false;
				} catch (IOException e) {
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
						u1 = new Users();
						u1.setdateregistered(jsonData.getString("user_dateRegistered"));
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
				c_datejoined.setText(u1.getdateregistered());
			}
			else{
			}
		}
	}
    private class ReadData3 extends AsyncTask<String, Void, Boolean>{
    	String text = "";
		
		ArrayList<String> list1;
		Userdtl u1;
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
					return false;
				} catch (IOException e) {
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
						u1.setpicname(jsonData.getString("avatar_name"));
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
				String pname = u1.getpicname();
				picname = pname;
				try{
					int loader = R.drawable.user_profile_pic;
			    	String image_url = "http://192.168.1.107/androidSnB/connectSNBDB/imgupload/uploadedimages/"+picname+"";
			    	ImageLoader imgLoader = new ImageLoader(getApplicationContext());
			    	imgLoader.DisplayImage(image_url, loader, c_image);
			    	url.setText(image_url);
				}catch (Exception e){
					
				}
			}
			else{
				//Toast.makeText(ProfileFragment.this, "Error", Toast.LENGTH_LONG).show();
			}
		}
		private Context getApplicationContext() {
			// TODO Auto-generated method stub
			return null;
		}
	}
    
    private class ReadData4 extends AsyncTask<String, Void, Boolean>{
    	String text = "";
		
		ArrayList<String> list1;
		Companydtl c1;
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
					return false;
				} catch (IOException e) {
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
						c1 = new Companydtl();
						c1.setc_lname(jsonData.getString("company_lName"));
						c1.setc_fname(jsonData.getString("company_fName"));
						c1.setc_mi(jsonData.getString("company_midInit"));
						c1.setc_about(jsonData.getString("company_about"));
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
				c_userlname.setText(c1.getc_lname());
				c_userfname.setText(c1.getc_fname());
				c_usermi.setText(c1.getc_mi());
				c_selfdescription.setText(c1.getc_about());
			}
			else{
				//Toast.makeText(ProfileFragment.this, "Error", Toast.LENGTH_LONG).show();
			}
		}
	}
}

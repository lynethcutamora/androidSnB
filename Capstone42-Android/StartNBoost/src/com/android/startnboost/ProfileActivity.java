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

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

public class ProfileActivity extends Activity{
	TextView userlname,userfname,usermi;
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.fragment_profile);
		userlname = (TextView) findViewById(R.id.user_lName);
		userfname = (TextView) findViewById(R.id.user_fName);
		usermi = (TextView) findViewById(R.id.user_midInit);
		
		Intent i = getIntent();
		String userId = i.getStringExtra("userId");
		ReadData task1 = new ReadData();
		task1.execute(new String[]{"http://192.168.43.3/androidSnB/connectSNBDB/readbyid.php?userId=" + userId});
	}
	private class ReadData extends AsyncTask<String, Void, Boolean>{

		ProgressDialog dialog = new ProgressDialog(ProfileActivity.this);
		@Override
		protected void onPreExecute() {
			dialog.setMessage("Loading...");
			dialog.show();
		}

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
					Toast.makeText(ProfileActivity.this, e.toString(), Toast.LENGTH_LONG).show();
					return false;
				} catch (IOException e) {
					Toast.makeText(ProfileActivity.this, e.toString(), Toast.LENGTH_LONG).show();
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
						u1.setage(jsonData.getString("user_age"));
						u1.setgender(jsonData.getString("user_gender"));
						u1.setdescription(jsonData.getString("user_shortSelfDescription"));
						u1.setnameofbusiness(jsonData.getString("user_nameOfBusiness"));
						u1.setbusinesstype(jsonData.getString("user_businessType"));
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
				userlname.setText(u1.getlname());
				userfname.setText(u1.getfname());
				usermi.setText(u1.getmidinit());
			}
			else{
				Toast.makeText(ProfileActivity.this, "Error", Toast.LENGTH_LONG).show();
			}
			dialog.dismiss();
		}
	}
  //------------------------------------------------------------------------------------------------
	
}

package com.android.startnboost;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class CompanySubActivity extends Activity{
	
	private String id,emailadd;
	private EditText lname,fname,mi;
	private Button submitfullcompany;

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.company_edit_registration);
		Bundle extras = getIntent().getExtras();
		emailadd = extras.getString("comp");
		
		lname = (EditText) findViewById(R.id.edittext_companyowners_lname);
		fname = (EditText) findViewById(R.id.edittext_companyowners_fname);
		mi = (EditText) findViewById(R.id.edittext_companyowners_mi);
		submitfullcompany = (Button) findViewById(R.id.btn_edit_company);
		
		
		
		ReadData task1 = new ReadData();
		task1.execute(new String[]{"http://192.168.1.107/androidSnB/connectSNBDB/viewById.php?emailAdd=" +emailadd.toString()});
		
		submitallcompany();
	}
	
	//------------------------------------------------------------------------------------------------
    private class ReadData extends AsyncTask<String, Void, Boolean>{

		ProgressDialog dialog = new ProgressDialog(CompanySubActivity.this);
		@Override
		protected void onPreExecute() {
			dialog.setMessage("Loading...");
			dialog.show();
		}

		String text = "";
		
		ArrayList<String> list1;
		Users i1;
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
					Toast.makeText(CompanySubActivity.this, e.toString(), Toast.LENGTH_LONG).show();
					return false;
				} catch (IOException e) {
					Toast.makeText(CompanySubActivity.this, e.toString(), Toast.LENGTH_LONG).show();
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
						i1 = new Users();
						i1.setuserid(jsonData.getString("userId"));
						i1.setusertype(jsonData.getString("user_Type"));
						i1.setdateregistered(jsonData.getString("user_dateRegistered"));
						i1.setemailadd(jsonData.getString("user_emailAdd"));
						i1.setpassword(jsonData.getString("user_password"));
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
				id = i1.getuserid();
			}
			else{
				Toast.makeText(CompanySubActivity.this, "Error", Toast.LENGTH_LONG).show();
			}
			dialog.dismiss();
		}
	}
  //------------------------------------------------------------------------------------------------
	
	public void submitallcompany(){
		submitfullcompany.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(lname.length() == 0){
                	lname.setError("Empty Line!!");
                }else if(fname.length() == 0){
                	fname.setError("Empty Line!!");
                }else if(mi.length() == 0){
                	mi.setError("Empty Line!!");
                }else{
                	Editcompany task = new Editcompany();
					task.execute(new String[]{"http://192.168.1.107/androidSnB/connectSNBDB/updateuser.php"});
                }
            }
        });
	}
	//------------------------------------------------------------------------------
	private class Editcompany extends AsyncTask<String, Void, Boolean>{
    	ProgressDialog dialog = new ProgressDialog(CompanySubActivity.this);
		@Override
		protected void onPreExecute() {
			dialog.setMessage("Updating Data...");
			dialog.show();
		}
		@Override
		protected Boolean doInBackground(String... params) {
			for(String url1 : params){
				try {
					ArrayList<NameValuePair> pairs = new ArrayList<NameValuePair>();
					pairs.add(new BasicNameValuePair("submitfullcompany", submitfullcompany.getText().toString()));
					pairs.add(new BasicNameValuePair("id", id.toString()));
					pairs.add(new BasicNameValuePair("lname", lname.getText().toString()));
					pairs.add(new BasicNameValuePair("fname", fname.getText().toString()));
					pairs.add(new BasicNameValuePair("mi", mi.getText().toString()));
					HttpClient client = new DefaultHttpClient();
					HttpPost post = new HttpPost(url1);
					post.setEntity(new UrlEncodedFormEntity(pairs));
					HttpResponse response = client.execute(post);
				} catch (ClientProtocolException e) {
					Toast.makeText(CompanySubActivity.this, e.toString(), Toast.LENGTH_LONG).show();
					return false;
				} catch (IOException e) {
					Toast.makeText(CompanySubActivity.this, e.toString(), Toast.LENGTH_LONG).show();
					return false;
				}
			}
			return true;
		}
		@Override
		protected void onPostExecute(Boolean result) {
			if(result == true){
				Toast.makeText(CompanySubActivity.this, "Welcome New Commers..", Toast.LENGTH_LONG).show();
				Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(intent);
			}
			else{
				Toast.makeText(CompanySubActivity.this, "Error", Toast.LENGTH_LONG).show();
			}
			dialog.dismiss();
		}
	}
	//----------------------------------------------------------------------------
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
}

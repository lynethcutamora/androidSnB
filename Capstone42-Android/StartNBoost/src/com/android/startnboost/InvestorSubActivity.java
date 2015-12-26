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

public class InvestorSubActivity extends Activity{
	
	private String emailadd,id;
	private EditText address1,address2,city,state,zipcode,country,selfdescription,nameofbusiness,typeofbusiness;
	private Button submitfullinvestor;

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.investor_edit_registration);
		Bundle extras = getIntent().getExtras();
		emailadd = extras.getString("e");
		
		
		address1 = (EditText) findViewById(R.id.edittext_investor_addressline1);
		address2 = (EditText) findViewById(R.id.edittext_investor_addressline2);
		city = (EditText) findViewById(R.id.edittext_investor_city);
		state = (EditText) findViewById(R.id.edittext_investor_state);
		zipcode = (EditText) findViewById(R.id.edittext_investor_zipcode);
		country = (EditText) findViewById(R.id.edittext_investor_country);
		selfdescription = (EditText) findViewById(R.id.edittext_investor_selfdescription);
		nameofbusiness = (EditText) findViewById(R.id.edittext_investor_nameofbusiness);
		typeofbusiness = (EditText) findViewById(R.id.edittext_investor_typeofbusiness);
		submitfullinvestor = (Button) findViewById(R.id.btn_edit_investor);
		
		
		
		ReadData task1 = new ReadData();
		task1.execute(new String[]{"http://192.168.1.107/androidSnB/connectSNBDB/viewById.php?emailAdd=" +emailadd.toString()});
		
		submitallinvestor();
	}
	
	//------------------------------------------------------------------------------------------------
    private class ReadData extends AsyncTask<String, Void, Boolean>{

		ProgressDialog dialog = new ProgressDialog(InvestorSubActivity.this);
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
					Toast.makeText(InvestorSubActivity.this, e.toString(), Toast.LENGTH_LONG).show();
					return false;
				} catch (IOException e) {
					Toast.makeText(InvestorSubActivity.this, e.toString(), Toast.LENGTH_LONG).show();
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
				Toast.makeText(InvestorSubActivity.this, "Error", Toast.LENGTH_LONG).show();
			}
			dialog.dismiss();
		}
	}
  //------------------------------------------------------------------------------------------------
	
	public void submitallinvestor(){
		submitfullinvestor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(address1.length() == 0){
                	address1.setError("Empty Line!!");
                }else if(address2.length() == 0){
                	address2.setError("Empty Line!!");
                }else if(city.length() == 0){
                	city.setError("Empty Line!!");
                }else if(state.length() == 0){
                	state.setError("Empty Line!!");
                }else if(zipcode.length() == 0){
                	zipcode.setError("Empty Line!!");
                }else if(country.length() == 0){
                	country.setError("Empty Line!!");
                }else if(selfdescription.length() == 0){
                	selfdescription.setError("Empty Line!!");
                }else if(nameofbusiness.length() == 0){
                	nameofbusiness.setError("Empty Line!!");
                }else if(typeofbusiness.length() == 0){
                	typeofbusiness.setError("Empty Line!!");
                }else{
                	Editinvestor task = new Editinvestor();
					task.execute(new String[]{"http://192.168.1.107/androidSnB/connectSNBDB/updateuser.php"});
                }
            }
        });
	}
	//------------------------------------------------------------------------------
	private class Editinvestor extends AsyncTask<String, Void, Boolean>{
    	ProgressDialog dialog = new ProgressDialog(InvestorSubActivity.this);
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
					pairs.add(new BasicNameValuePair("submitfullinvestor", submitfullinvestor.getText().toString()));
					pairs.add(new BasicNameValuePair("id", id.toString()));
					pairs.add(new BasicNameValuePair("address1", address1.getText().toString()));
					pairs.add(new BasicNameValuePair("address2", address2.getText().toString()));
					pairs.add(new BasicNameValuePair("city", city.getText().toString()));
					pairs.add(new BasicNameValuePair("state", state.getText().toString()));
					pairs.add(new BasicNameValuePair("zipcode", zipcode.getText().toString()));
					pairs.add(new BasicNameValuePair("country", country.getText().toString()));
					pairs.add(new BasicNameValuePair("selfdescription", selfdescription.getText().toString()));
					pairs.add(new BasicNameValuePair("nameofbusiness", nameofbusiness.getText().toString()));
					pairs.add(new BasicNameValuePair("typeofbusiness", typeofbusiness.getText().toString()));
					HttpClient client = new DefaultHttpClient();
					HttpPost post = new HttpPost(url1);
					post.setEntity(new UrlEncodedFormEntity(pairs));
					HttpResponse response = client.execute(post);
				} catch (ClientProtocolException e) {
					Toast.makeText(InvestorSubActivity.this, e.toString(), Toast.LENGTH_LONG).show();
					return false;
				} catch (IOException e) {
					Toast.makeText(InvestorSubActivity.this, e.toString(), Toast.LENGTH_LONG).show();
					return false;
				}
			}
			return true;
		}
		@Override
		protected void onPostExecute(Boolean result) {
			if(result == true){
				Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(intent);
			}
			else{
				Toast.makeText(InvestorSubActivity.this, "Error", Toast.LENGTH_LONG).show();
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
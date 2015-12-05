package com.android.startnboost;

import java.io.IOException;
import java.util.ArrayList;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

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

public class IdeatorActivity extends Activity{
	Button btnSubmitIdeator;
	EditText fname,lname,mi,age,gender,address,email,password,retypepassword;
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.ideator_registration);
		
		btnSubmitIdeator = (Button) findViewById(R.id.btn_submit_ideator);
		
		fname = (EditText) findViewById(R.id.editText_fname);
		lname = (EditText) findViewById(R.id.editText_lastname);
		mi = (EditText) findViewById(R.id.editText_mi);
		age = (EditText) findViewById(R.id.editText_age);
		gender = (EditText) findViewById(R.id.editText_gender);
		address = (EditText) findViewById(R.id.editText_address);
		email = (EditText) findViewById(R.id.editText_email);
		password = (EditText) findViewById(R.id.editText_password);
		retypepassword = (EditText) findViewById(R.id.editText_re_password);
		
		
		submitideator();
	}
	
	public void submitideator(){
		btnSubmitIdeator.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(fname.length() == 0){
                	fname.setError("Empty Line!!");
                }else if(lname.length() == 0){
                	lname.setError("Empty Line!!");
                }else if(age.length() == 0){
                	age.setError("Empty Line!!");
                }else if(gender.length() == 0){
                	gender.setError("Empty Line!!");
                }else if(email.length() == 0){
                	email.setError("Empty Line!!");
                }else if(password.length() == 0){
                	password.setError("Empty Line!!");
                }else if(retypepassword.length() == 0){
                	password.setError("Empty Line!!");
                }else if(!retypepassword.getText().toString().equals(password.getText().toString())){
                	Toast.makeText(getApplicationContext(), "Password did not match!!! =)", Toast.LENGTH_LONG).show();
                }else{
                	Insertideator task1 = new Insertideator();
					task1.execute(new String[]{"http://192.168.43.3/androidSnB/connectSNBDB/insertideator.php"});
                }
            }
        });
	}
	private class Insertideator extends AsyncTask<String, Void, Boolean>{
		ProgressDialog dialog = new ProgressDialog(IdeatorActivity.this);
		@Override
		protected void onPreExecute() {
			dialog.setMessage("Creating Account...");
			dialog.show();
		}
		@Override
		protected Boolean doInBackground(String... urls) {
			for(String url1 : urls){
				try {
					ArrayList<NameValuePair> pairs = new ArrayList<NameValuePair>();
					pairs.add(new BasicNameValuePair("btnideator", btnSubmitIdeator.getText().toString()));
					pairs.add(new BasicNameValuePair("fname", fname.getText().toString()));
					pairs.add(new BasicNameValuePair("lname", lname.getText().toString()));
					pairs.add(new BasicNameValuePair("mi", mi.getText().toString()));
					pairs.add(new BasicNameValuePair("age", age.getText().toString()));
					pairs.add(new BasicNameValuePair("gender", gender.getText().toString()));
					pairs.add(new BasicNameValuePair("email", email.getText().toString()));
					pairs.add(new BasicNameValuePair("password", password.getText().toString()));
					HttpClient client = new DefaultHttpClient();
					HttpPost post = new HttpPost(url1);
					post.setEntity(new UrlEncodedFormEntity(pairs));
					HttpResponse response = client.execute(post);
				} catch (ClientProtocolException e) {
					Toast.makeText(IdeatorActivity.this, e.toString(), Toast.LENGTH_LONG).show();
					return false;
				} catch (IOException e) {
					Toast.makeText(IdeatorActivity.this, e.toString(), Toast.LENGTH_LONG).show();
					return false;
				}
			}
			return true;
		}
		@Override
		protected void onPostExecute(Boolean result) {
			if(result == true){
				Toast.makeText(IdeatorActivity.this, "Create Successfull", Toast.LENGTH_LONG).show();
				Intent i = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(i);
			}
			else{
				Toast.makeText(IdeatorActivity.this, "Error", Toast.LENGTH_LONG).show();
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
}

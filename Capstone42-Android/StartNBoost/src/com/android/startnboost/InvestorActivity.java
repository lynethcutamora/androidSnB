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
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;
import android.widget.RadioGroup.OnCheckedChangeListener;

public class InvestorActivity extends Activity{
	private Button btnSubmitInvestor;
	private EditText lname,fname,mi,age,email,password,retypepassword;
	private RadioGroup radiogroup;
	private RadioButton investor_female,investor_male;
	private String investor_gender;
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.investor_registration);
		
		btnSubmitInvestor = (Button) findViewById(R.id.btnsubmit_investor);
		radiogroup = (RadioGroup) findViewById(R.id.RadioGroup_investorgender);
		
		radiogroup.setOnCheckedChangeListener(new OnCheckedChangeListener() {
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                // find which radio button is selected
                if(checkedId == R.id.rbtn_investor_female) {
                    investor_gender = "F";
                } else if(checkedId == R.id.rbtn_investor_male) {
                    investor_gender = "M";
                }
            }
        });
		
		investor_female = (RadioButton) findViewById(R.id.rbtn_investor_female);
		investor_male = (RadioButton) findViewById(R.id.rbtn_investor_male);
		
		lname = (EditText) findViewById(R.id.edittext_investor_lname);
		fname = (EditText) findViewById(R.id.edittext_investor_fname);
		mi = (EditText) findViewById(R.id.editText_investor_mi);
		age = (EditText) findViewById(R.id.editText_investor_age);
		email = (EditText) findViewById(R.id.editText_investor_email);
		password = (EditText) findViewById(R.id.editText_investor_password);
		retypepassword = (EditText) findViewById(R.id.editText_investor_re_password);
		
		submitinvestor();
	}
	public void submitinvestor(){
		btnSubmitInvestor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(fname.length() == 0){
                	fname.setError("Empty Line!!");
                }else if(lname.length() == 0){
                	lname.setError("Empty Line!!");
                }else if(age.length() == 0){
                	age.setError("Empty Line!!");
                }else if(email.length() == 0){
                	email.setError("Empty Line!!");
                }else if(password.length() == 0){
                	password.setError("Empty Line!!");
                }else if(retypepassword.length() == 0){
                	password.setError("Empty Line!!");
                }else if(!retypepassword.getText().toString().equals(password.getText().toString())){
                	Toast.makeText(getApplicationContext(), "Password did not match!!! =)", Toast.LENGTH_LONG).show();
                }else{
                	Insertinvestor task1 = new Insertinvestor();
					task1.execute(new String[]{"http://192.168.1.107/androidSnB/connectSNBDB/insertuser.php"});
                }
            }
        });
	}
	private class Insertinvestor extends AsyncTask<String, Void, Boolean>{
		ProgressDialog dialog = new ProgressDialog(InvestorActivity.this);
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
					pairs.add(new BasicNameValuePair("btninvestor", btnSubmitInvestor.getText().toString()));
					pairs.add(new BasicNameValuePair("lname", lname.getText().toString()));
					pairs.add(new BasicNameValuePair("fname", fname.getText().toString()));
					pairs.add(new BasicNameValuePair("mi", mi.getText().toString()));
					pairs.add(new BasicNameValuePair("age", age.getText().toString()));
					pairs.add(new BasicNameValuePair("gender", investor_gender));
					pairs.add(new BasicNameValuePair("email", email.getText().toString()));
					pairs.add(new BasicNameValuePair("password", password.getText().toString()));
					HttpClient client = new DefaultHttpClient();
					HttpPost post = new HttpPost(url1);
					post.setEntity(new UrlEncodedFormEntity(pairs));
					HttpResponse response = client.execute(post);
				} catch (ClientProtocolException e) {
					Toast.makeText(InvestorActivity.this, e.toString(), Toast.LENGTH_LONG).show();
					return false;
				} catch (IOException e) {
					Toast.makeText(InvestorActivity.this, e.toString(), Toast.LENGTH_LONG).show();
					return false;
				}
			}
			return true;
		}
		@Override
		protected void onPostExecute(Boolean result) {
			String emailadd = email.getText().toString();
			if(result == true){
				Intent investor = new Intent(getApplicationContext(), InvestorSubActivity.class);
				investor.putExtra("e", emailadd);
                startActivity(investor);
			}
			else{
				Toast.makeText(InvestorActivity.this, "Error", Toast.LENGTH_LONG).show();
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

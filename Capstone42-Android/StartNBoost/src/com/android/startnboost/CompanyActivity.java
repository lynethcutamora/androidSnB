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

public class CompanyActivity extends Activity{
	private Button btnSubmitCompany;
	private EditText companyname,companyemail,companypassword,companyretypepassword,companytype,companyyearfounded,companydescription;
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.company_registration);
		
		btnSubmitCompany = (Button) findViewById(R.id.btn_submit_company);
		companyname = (EditText) findViewById(R.id.edittext_company_name);
		companyemail = (EditText) findViewById(R.id.edittext_company_email);
		companypassword = (EditText) findViewById(R.id.edittext_company_password);
		companyretypepassword = (EditText) findViewById(R.id.edittext_company_retypepassword);
		companytype = (EditText) findViewById(R.id.edittext_company_businesstype);
		companyyearfounded = (EditText) findViewById(R.id.edittext_company_yearfounded);
		companydescription = (EditText) findViewById(R.id.edittext_company_description);
		
		submitcompany();
	}
	public void submitcompany(){
		btnSubmitCompany.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(companyname.length() == 0){
                	companyname.setError("Empty Line!!");
                }else if(companyemail.length() == 0){
                	companyemail.setError("Empty Line!!");
                }else if(companypassword.length() == 0){
                	companypassword.setError("Empty Line!!");
                }else if(companyretypepassword.length() == 0){
                	companyretypepassword.setError("Empty Line!!");
                }else if(companydescription.length() == 0){
                	companydescription.setError("Empty Line!!");
                }else if(companytype.length() == 0){
                	companytype.setError("Empty Line!!");
                }else if(companyyearfounded.length() == 0){
                	companyyearfounded.setError("Empty Line!!");
                }else if(!companyretypepassword.getText().toString().equals(companypassword.getText().toString())){
                	Toast.makeText(getApplicationContext(), "Password did not match!!! =)", Toast.LENGTH_LONG).show();
                }else{
                	Insertcompany task1 = new Insertcompany();
					task1.execute(new String[]{"http://192.168.43.228/androidSnB/connectSNBDB/insertuser.php"});
                }
            }
        });
	}
	private class Insertcompany extends AsyncTask<String, Void, Boolean>{
		ProgressDialog dialog = new ProgressDialog(CompanyActivity.this);
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
					pairs.add(new BasicNameValuePair("btncompany", btnSubmitCompany.getText().toString()));
					pairs.add(new BasicNameValuePair("companyname", companyname.getText().toString()));
					pairs.add(new BasicNameValuePair("email", companyemail.getText().toString()));
					pairs.add(new BasicNameValuePair("password", companypassword.getText().toString()));
					pairs.add(new BasicNameValuePair("businesstype", companytype.getText().toString()));
					pairs.add(new BasicNameValuePair("yearfounded", companyyearfounded.getText().toString()));
					pairs.add(new BasicNameValuePair("description", companydescription.getText().toString()));
					HttpClient client = new DefaultHttpClient();
					HttpPost post = new HttpPost(url1);
					post.setEntity(new UrlEncodedFormEntity(pairs));
					HttpResponse response = client.execute(post);
				} catch (ClientProtocolException e) {
					Toast.makeText(CompanyActivity.this, e.toString(), Toast.LENGTH_LONG).show();
					return false;
				} catch (IOException e) {
					Toast.makeText(CompanyActivity.this, e.toString(), Toast.LENGTH_LONG).show();
					return false;
				}
			}
			return true;
		}
		@Override
		protected void onPostExecute(Boolean result) {
			String email = companyemail.getText().toString();
			if(result == true){
				//Toast.makeText(CompanyActivity.this, "Create Successfull", Toast.LENGTH_LONG).show();
				Intent company = new Intent(getApplicationContext(), CompanySubActivity.class);
				company.putExtra("comp", email);
                startActivity(company);
			}
			else{
				Toast.makeText(CompanyActivity.this, "Error", Toast.LENGTH_LONG).show();
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

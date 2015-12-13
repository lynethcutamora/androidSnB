package com.android.startnboost;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
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
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends Activity{
	
	Button btn_SignUp,btn_Login;
	EditText txt_email,txt_pass;
	
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.login_activity);
		btn_SignUp = (Button) findViewById(R.id.btnSignup);
		btn_Login = (Button) findViewById(R.id.btnLogin);
		txt_email = (EditText) findViewById(R.id.editText_fname);
		txt_pass = (EditText) findViewById(R.id.editText_lastname);
		
		login();
		signup();
	}
	
	public void login(){
		btn_Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               // Toast.makeText(getApplicationContext(), "Welcome Back Sensie!!! =)",
               // 		   Toast.LENGTH_LONG).show();
                if(txt_email.length() == 0){
                	txt_email.setError("Empty Line!!");
                }else if(txt_pass.length() == 0){
                	txt_pass.setError("Empty Line!!");
                }else{
                	ReadData task1 = new ReadData();
					task1.execute(new String[]{"http://192.168.1.107/androidSnB/connectSNBDB/viewById.php?emailAdd=" +txt_email.getText().toString()});
                }
            }
        });
	}
	
	private static final String md5(final String password) {
	    try {
	 
	        MessageDigest digest = java.security.MessageDigest
	                .getInstance("MD5");
	        digest.update(password.getBytes());
	        byte messageDigest[] = digest.digest();
	 
	        StringBuffer hexString = new StringBuffer();
	        for (int i = 0; i < messageDigest.length; i++) {
	            String h = Integer.toHexString(0xFF & messageDigest[i]);
	            while (h.length() < 2)
	                h = "0" + h;
	            hexString.append(h);
	        }
	        return hexString.toString();
	 
	    } catch (NoSuchAlgorithmException e) {
	        e.printStackTrace();
	    }
	    return "";
	}
	
	//------------------------------------------------------------------------------------------------
    private class ReadData extends AsyncTask<String, Void, Boolean>{

		ProgressDialog dialog = new ProgressDialog(LoginActivity.this);
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
					Toast.makeText(LoginActivity.this, e.toString(), Toast.LENGTH_LONG).show();
					return false;
				} catch (IOException e) {
					Toast.makeText(LoginActivity.this, e.toString(), Toast.LENGTH_LONG).show();
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
				String password = i1.getpassword();
				String pass = txt_pass.getText().toString();
				String pas = md5(pass);
				String email = i1.getemailadd();
				String userId = i1.getuserid();
				if(txt_email.getText().toString().equals(email)){
					if(pas.equals(password)){
						Intent intent = new Intent(getApplicationContext(), MainActivity.class);
						intent.putExtra("userId", userId);
						startActivity(intent);
						//startActivity(i);
						
					Toast.makeText(getApplicationContext(), "Welcome Sensie!!! =)",
	             		   Toast.LENGTH_LONG).show();

					}else{
						Toast.makeText(getApplicationContext(), "Password did not match!!! =)",
					           Toast.LENGTH_LONG).show();
					}
				}else{
					Toast.makeText(getApplicationContext(), "Email address is incorrect!!! =)",
		             		   Toast.LENGTH_LONG).show();
				}
				
			}
			else{
				Toast.makeText(LoginActivity.this, "Error", Toast.LENGTH_LONG).show();
			}
			dialog.dismiss();
		}
	}
  //------------------------------------------------------------------------------------------------
	
	public void signup(){
		btn_SignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Launching All products Activity
                Intent i = new Intent(getApplicationContext(), SignupActivity.class);
                startActivity(i);
                Toast.makeText(getApplicationContext(), "Welcome New Fellas!!! =)",
                		   Toast.LENGTH_LONG).show();
            }
        });
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
}

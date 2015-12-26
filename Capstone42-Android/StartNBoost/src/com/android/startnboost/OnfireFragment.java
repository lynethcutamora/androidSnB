package com.android.startnboost;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import android.app.Fragment;
import android.app.ProgressDialog;
import android.net.ParseException;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

public class OnfireFragment extends Fragment {
	ArrayList<OnfireActors> actorsList;
	OnfireActorAdapter adapter;
	TextView lname,postdate,postcontent; 
	
    public OnfireFragment() {
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
 
        View rootView = inflater.inflate(R.layout.fragment_onfire, container, false);
        actorsList = new ArrayList<OnfireActors>();
        
        lname = (TextView) rootView.findViewById(R.id.onfire_profilename);
        postdate = (TextView) rootView.findViewById(R.id.onfire_dataofpost);
        postcontent = (TextView) rootView.findViewById(R.id.onfire_postcomments);
        readData();
        //OnfireData();
        return rootView;
    }
    ///////////////////////////////////////////////////////////////////////////////////////////////
    private void OnfireData(){
    	JSONAsyncTask task1 = new JSONAsyncTask();
		task1.execute(new String[]{"http://192.168.1.107/androidSnB/connectSNBDB/onfire.php"});
	}
    
	private class JSONAsyncTask extends AsyncTask<String, Void, Boolean> {
			
			ProgressDialog dialog;
			
			@Override
			protected void onPreExecute() {
				super.onPreExecute();
				dialog = new ProgressDialog(getActivity());
				dialog.setMessage("Loading, please wait");
				dialog.setTitle("Connecting server");
				dialog.show();
				dialog.setCancelable(false);
			}
			OnfireActors actor;
			@Override
			protected Boolean doInBackground(String... urls) {
				try {
					
					//------------------>>
					HttpGet httppost = new HttpGet(urls[0]);
					HttpClient httpclient = new DefaultHttpClient();
					HttpResponse response = httpclient.execute(httppost);
	
					// StatusLine stat = response.getStatusLine();
					int status = response.getStatusLine().getStatusCode();
	
					if (status == 200) {
						HttpEntity entity = response.getEntity();
						String data = EntityUtils.toString(entity);
						
					
						JSONObject jsono = new JSONObject(data);
						JSONArray jarray = jsono.getJSONArray("actors");
						
						for (int i = 0; i < jarray.length(); i++) {
							JSONObject object = jarray.getJSONObject(i);
						
							actor = new OnfireActors();
							
							actor.setName(object.getString("user_lName"));
							actor.setdateofpost(object.getString("postDate"));
							actor.setpostcontent(object.getString("postContent"));
							//actor.setimageprofile(object.getString("avatar_name"));
							//actor.setimagepost(object.getString("avatar_name"));
							
							//actorsList.add(actor);
						}
						return true;
					}
					
					//------------------>>
					
				} catch (ParseException e1) {
					e1.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				} catch (JSONException e) {
					e.printStackTrace();
				}
				return false;
			}
			
			protected void onPostExecute(Boolean result) {
				dialog.cancel();
				adapter.notifyDataSetChanged();
				if(result == true){
					lname.setText(actor.getName());
					postdate.setText(actor.getdateofpost());
					postcontent.setText(actor.getpostcontent());
				}else if(result == false)
					Toast.makeText(getActivity(), "Unable to fetch data from server", Toast.LENGTH_LONG).show();
	
			}
		}
 /////////////////////////////////////////////////////////////////////////////////////////////////////////////
	private void readData(){
		ReadData task1 = new ReadData();
		task1.execute(new String[]{"http://192.168.1.107/androidSnB/connectSNBDB/onfire.php"});
	}
	
	private class ReadData extends AsyncTask<String, Void, Boolean>{
    	String text = "";
		
		ArrayList<String> list1;
		OnfireActors actor;
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
						actor = new OnfireActors();
						actor.setName(jsonData.getString("user_lName"));
						actor.setdateofpost(jsonData.getString("postDate"));
						actor.setpostcontent(jsonData.getString("postContent"));
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
				lname.setText(actor.getName());
				postdate.setText(actor.getdateofpost());
				postcontent.setText(actor.getpostcontent());
			}
			else{
				//Toast.makeText(ProfileFragment.this, "Error", Toast.LENGTH_LONG).show();
			}
		}
	}
}

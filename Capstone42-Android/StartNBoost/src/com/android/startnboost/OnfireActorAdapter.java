package com.android.startnboost;

import java.io.InputStream;
import java.util.ArrayList;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class OnfireActorAdapter  extends ArrayAdapter<OnfireActors> {
	ArrayList<OnfireActors> actorList;
	LayoutInflater vi;
	int Resource;
	ViewHolder holder;

	public OnfireActorAdapter(Context context, int resource, ArrayList<OnfireActors> objects) {
		super(context, resource, objects);
		vi = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		Resource = resource;
		actorList = objects;
	}
 
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// convert view = design
		View v = convertView;
		if (v == null) {
			holder = new ViewHolder();
			v = vi.inflate(Resource, null);
			holder.imageprofile = (ImageView) v.findViewById(R.id.onfire_imageprofile);
			holder.imagepost = (ImageView) v.findViewById(R.id.onfire_imagepost);
			holder.name = (TextView) v.findViewById(R.id.onfire_profilename);
			holder.dateofpost = (TextView) v.findViewById(R.id.onfire_dataofpost);
			holder.postcontent = (TextView) v.findViewById(R.id.onfire_postcomments);
			v.setTag(holder);
		} else {
			holder = (ViewHolder) v.getTag();
		}
		holder.imageprofile.setImageResource(R.drawable.ic_launcher);
		new DownloadImageTask(holder.imageprofile).execute(actorList.get(position).getimageprofile());
		holder.imagepost.setImageResource(R.drawable.ic_launcher);
		new DownloadImageTask(holder.imagepost).execute(actorList.get(position).getimagepost());
		holder.name.setText(actorList.get(position).getName());
		holder.dateofpost.setText(actorList.get(position).getdateofpost());
		holder.postcontent.setText(actorList.get(position).getpostcontent());
		return v;

	}

	static class ViewHolder {
		public ImageView imageprofile;
		public ImageView imagepost;
		public TextView name;
		public TextView dateofpost;
		public TextView reputation;
		public TextView postcontent;
	}

	private class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {
		ImageView bmImage;

		public DownloadImageTask(ImageView bmImage) {
			this.bmImage = bmImage;
		}

		protected Bitmap doInBackground(String... urls) {
			String urldisplay = urls[0];
			Bitmap mIcon11 = null;
			try {
				InputStream in = new java.net.URL(urldisplay).openStream();
				mIcon11 = BitmapFactory.decodeStream(in);
			} catch (Exception e) {
				Log.e("Error", e.getMessage());
				e.printStackTrace();
			}
			return mIcon11;
		}

		protected void onPostExecute(Bitmap result) {
			bmImage.setImageBitmap(result);
		}

	}
}
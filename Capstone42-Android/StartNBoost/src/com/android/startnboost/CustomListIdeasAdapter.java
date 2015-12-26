package com.android.startnboost;

import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class CustomListIdeasAdapter extends ArrayAdapter<RowIdeas> {
 
    Context context;
 
    public CustomListIdeasAdapter(Context context, int resourceId,
            List<RowIdeas> items) {
        super(context, resourceId, items);
        this.context = context;
    }
     
    /*private view holder class*/
    private class ViewHolder {
        ImageView imageView,imagepost;
        TextView txtName,txtDesc,txtDate;
    }
     
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        RowIdeas rowItem = getItem(position);
         
        LayoutInflater mInflater = (LayoutInflater) context
                .getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.list_ideas, null);
            holder = new ViewHolder();
            holder.imagepost = (ImageView) convertView.findViewById(R.id.listideas_userpost);
            holder.txtDate = (TextView) convertView.findViewById(R.id.listideas_postdate);
            holder.txtDesc = (TextView) convertView.findViewById(R.id.listideas_postdescription);
            holder.txtName = (TextView) convertView.findViewById(R.id.listideas_profilename);
            holder.imageView = (ImageView) convertView.findViewById(R.id.listideas_profileimage);
            convertView.setTag(holder);
        } else
            holder = (ViewHolder) convertView.getTag();
                 
        holder.imagepost.setImageResource(rowItem.getImagepost());
        holder.txtDate.setText(rowItem.getDate());
        holder.txtDesc.setText(rowItem.getDesc());
        holder.txtName.setText(rowItem.getName());
        holder.imageView.setImageResource(rowItem.getImageId());
         
        return convertView;
    }
}

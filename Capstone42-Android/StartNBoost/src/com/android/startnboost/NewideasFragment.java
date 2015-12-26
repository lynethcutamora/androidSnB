package com.android.startnboost;

import java.util.ArrayList;
import java.util.List;

import android.app.AlertDialog;
import android.app.Fragment;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.Toast;

public class NewideasFragment extends Fragment implements OnItemClickListener {
	 
	public static final String[] date = new String[] {"2015","2014","2013","2012"};
	
	public static final String[] titles = new String[] { "Isidro",
        "Abe", "Estose", "Jr" };

	public static final String[] descriptions = new String[] {
        "It is an aggregate accessory fruit" +
        "It is an aggregate accessory fruit" +
        "It is an aggregate accessory fruit" +
        "It is an aggregate accessory fruit",
        "It is the largest herbaceous flowering plant" +
        "It is the largest herbaceous flowering plant" +
        "It is the largest herbaceous flowering plant" +
        "It is the largest herbaceous flowering plant", "Citrus Fruit",
        "Mixed Fruits" };

	public static final Integer[] images = { R.drawable.ic_action_ideas,
        R.drawable.ic_action_onfire, R.drawable.ideators, R.drawable.ic_action_startup };

	public static final Integer[] imagepost = { R.drawable.ic_action_ideas,
        R.drawable.ic_action_onfire, R.drawable.ideators, R.drawable.ic_action_startup };
	
	ListView listView;
	List<RowIdeas> rowIdeas;
	
    public NewideasFragment() {
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
 
        View rootView = inflater.inflate(R.layout.fragment_newideas, container, false);
        
        rowIdeas = new ArrayList<RowIdeas>();
        for (int i = 0; i < titles.length; i++) {
            RowIdeas item = new RowIdeas(images[i], titles[i], descriptions[i], date[i], imagepost[i]);
            rowIdeas.add(item);
        }
 
        listView = (ListView) rootView.findViewById(R.id.listideas);
        CustomListIdeasAdapter adapter = new CustomListIdeasAdapter(getActivity(),R.layout.list_ideas, rowIdeas);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(this);
        
        return rootView;
    }
	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
		// TODO Auto-generated method stub
		Toast toast = Toast.makeText(getActivity(),
	            "Item " + (position + 1) + ": " + rowIdeas.get(position),
	            Toast.LENGTH_SHORT);
	        toast.setGravity(Gravity.BOTTOM|Gravity.CENTER_HORIZONTAL, 0, 0);
	        toast.show();
	        
	//------------------------------------------------------------------------------------------
	/*        Cursor res = mydb.getAllStudent();
    		if(res.getCount() == 0){
    			showMessage("Error","Nothing Found!");
    			return;
    		}
    		StringBuffer buffer = new StringBuffer();
    		while(res.moveToNext()){
    			buffer.append("ID: "+ res.getString(0)+"\n");
    			buffer.append("FirstName: "+ res.getString(1)+"\n");
    			buffer.append("LastName: "+ res.getString(2)+"\n");
    			buffer.append("Course: "+ res.getString(3)+"\n");
    			buffer.append("Year: "+ res.getString(4)+"\n");
    		}
    		showMessage("List Students", buffer.toString());*/
	}
	
	public void showMessage(String title, String Message){
    	AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
    	builder.setCancelable(true);
    	builder.setTitle(title);
    	builder.setMessage(Message);
    	builder.show();
    }
 
}
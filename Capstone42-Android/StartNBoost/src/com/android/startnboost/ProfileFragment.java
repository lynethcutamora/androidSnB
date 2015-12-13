package com.android.startnboost;

import android.annotation.SuppressLint;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

@SuppressLint("ValidFragment")
public class ProfileFragment extends Fragment {
	
	@SuppressLint("ValidFragment")
	private String[] user_dtl;
    public ProfileFragment(String[] user) {
    	user_dtl =  user;
    }    
    private TextView userlname;
    private TextView userfname;
    private TextView usermi;
    @Override
    public View onCreateView(LayoutInflater inflater,
            ViewGroup container, Bundle savedInstanceState){
        // -- inflate the layout for this fragment
    	View rootView = inflater.inflate(R.layout.fragment_profile, container, false);
    	
        // Set the Text to try this out
    	userlname = (TextView) rootView.findViewById(R.id.user_lName);
    	userfname = (TextView) rootView.findViewById(R.id.user_fName);
    	usermi = (TextView) rootView.findViewById(R.id.user_midInit);
    	
    	//userlname.setText(user_dtl[0]);
    	//userfname.setText(u1.getfname());
    	//usermi.setText(u1.getmidinit());
    	
		return rootView;
    }
}
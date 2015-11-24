package com.android.startnboost;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;

public class CompanyActivity extends Activity{
	Button btnSubmitCompany;
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.company_registration);
	}
}

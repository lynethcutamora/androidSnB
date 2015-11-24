package com.android.startnboost;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;

public class InvestorActivity extends Activity{
	Button btnSubmitInvestor;
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.investor_registration);
	}
}

package com.android.startnboost;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

public class SignupActivity extends Activity{
	ImageButton btnideator,btninvestor,btncompany;
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.signup_activity);
		btnideator = (ImageButton) findViewById(R.id.btn_ideator);
		btninvestor = (ImageButton) findViewById(R.id.btn_investor);
		btncompany = (ImageButton) findViewById(R.id.btn_company);
		//ideator button
		btnideator.setOnClickListener(new View.OnClickListener() {
	       	 
            @Override
            public void onClick(View view) {
                // Launching All products Activity
                Intent i = new Intent(getApplicationContext(), IdeatorActivity.class);
                startActivity(i);
                Toast.makeText(getApplicationContext(), "Welcome Ideator!!! =)",
             		   Toast.LENGTH_LONG).show();
            }
        });
		//investor button
		btninvestor.setOnClickListener(new View.OnClickListener() {
	       	 
            @Override
            public void onClick(View view) {
                // Launching All products Activity
                Intent i = new Intent(getApplicationContext(), InvestorActivity.class);
                startActivity(i);
                Toast.makeText(getApplicationContext(), "Welcome Investor!!! =)",
             		   Toast.LENGTH_LONG).show();
            }
        });
		//company button
		btncompany.setOnClickListener(new View.OnClickListener() {
	       	 
            @Override
            public void onClick(View view) {
                // Launching All products Activity
                Intent i = new Intent(getApplicationContext(), CompanyActivity.class);
                startActivity(i);
                Toast.makeText(getApplicationContext(), "Welcome Company!!! =)",
             		   Toast.LENGTH_LONG).show();
            }
        });
	}
}

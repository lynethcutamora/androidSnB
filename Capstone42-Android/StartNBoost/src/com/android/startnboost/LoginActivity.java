package com.android.startnboost;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class LoginActivity extends Activity{
	
	Button btn_SignUp,btn_Login;
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.login_activity);
		btn_SignUp = (Button) findViewById(R.id.btnSignup);
		btn_Login = (Button) findViewById(R.id.btnLogin);
		
		btn_Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Launching All products Activity
                Intent i = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(i);
                Toast.makeText(getApplicationContext(), "Welcome Back Sensie!!! =)",
                		   Toast.LENGTH_LONG).show();
            }
        });
		
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

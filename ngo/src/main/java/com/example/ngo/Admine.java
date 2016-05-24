package com.example.ngo;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;

public class Admine extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_admine);
	}
	
	/**
	 * 
	 * @param v
	 */
	public void onUserdata(View view) {
		Intent intent = new Intent(Admine.this, Userdata.class);
		startActivity(intent);
	}
	
	public void onPostinformationdata(View view) {
		Intent intent = new Intent(Admine.this, Postinformationdata.class);
		startActivity(intent);
		
		
	}

	public void onMakedonation(View view) {
		Intent intent = new Intent(Admine.this, Makedonation.class);
		startActivity(intent);
		
		
	}

	public void onLogout(View view) {
		Intent intent = new Intent(Admine.this, Index.class);
		startActivity(intent);
		
		
	}

	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.admine, menu);
		return true;
	}

	
	
}

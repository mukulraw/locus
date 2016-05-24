package com.example.ngo;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class Heathcare extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_heathcare);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.heathcare, menu);
		return true;
	}

}

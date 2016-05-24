package com.example.ngo;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.app.Activity;
/*
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;


*/import android.view.Menu;
import android.view.View;
import android.widget.TextView;

public class Emergencyhelp extends Activity
		implements View.OnClickListener {

	private TextView mTollFreeNumber;
	private TextView mWithoutTollFreeNumber;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_emergencyhelp);

		init();
	}

	/**
	 * Initializing all views.
	 */
	private void init() {
		mTollFreeNumber = (TextView) findViewById(R.id.textView2);
		mTollFreeNumber.setOnClickListener(this);

		mWithoutTollFreeNumber = (TextView) findViewById(R.id.TextView01);
		mWithoutTollFreeNumber.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
			case R.id.textView2:
				startMakingCall(mTollFreeNumber.getText().toString());
				break;

			case R.id.TextView01:
				startMakingCall(mWithoutTollFreeNumber.getText().toString());
				break;
		}
	}

	/**
	 * This method will start call for selected number.
	 * @param mobileNumber passing mobile number.
	 */
	private void startMakingCall(String mobileNumber) {

		Intent callIntentActivity = new Intent(Intent.ACTION_CALL);
		callIntentActivity.setData(Uri.parse("tel:" + mobileNumber));

		startActivity(callIntentActivity);
	}


}

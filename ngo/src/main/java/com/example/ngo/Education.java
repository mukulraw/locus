package com.example.ngo;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.webkit.WebView;
import android.widget.TextView;

public class Education extends Activity 
implements View.OnClickListener     {

	private TextView mTollFreeNumber;
	private TextView mWithoutTollFreeNumber;
	/*
	private TextView mWebAddress1;
	private TextView mWebAddress2;
	private TextView mWebAddress3;
	
	*/ 
	//private TextView mWithoutTollFreeNumber1;
	private WebView webView;
	String url="http://actionforindia.org";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState); 
		setContentView(R.layout.activity_education);
		//webView = (WebView) findViewById(R.id.webView);
		//  webView.setWebViewClient(new MyWebViewClient());
		
		 // webView.getSettings().setJavaScriptEnabled(true);
		  //webView.loadUrl(url);
		
		init();

	}

	/**
	 * Initializing all views.
	 */
	private void init() {
		mTollFreeNumber = (TextView) findViewById(R.id.TextView02);
		mTollFreeNumber.setOnClickListener(this);

		mWithoutTollFreeNumber = (TextView) findViewById(R.id.textView3);
		mWithoutTollFreeNumber.setOnClickListener(this);
	/*
		mWebAddress1 = (TextView) findViewById(R.id.TextView03);
		mWithoutTollFreeNumber.setOnClickListener(this);
		
		mWebAddress2 = (TextView) findViewById(R.id.TextView7);
		mWithoutTollFreeNumber.setOnClickListener(this);
		
		mWebAddress3 = (TextView) findViewById(R.id.textView4);
		mWithoutTollFreeNumber.setOnClickListener(this);
	*/	
		
		//mWithoutTollFreeNumber1 = (TextView) findViewById(R.id.TextView03);
		//mWithoutTollFreeNumber1.setOnClickListener(this);
	
		
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
			case R.id.TextView02:
				startMakingCall(mTollFreeNumber.getText().toString());
				break;

			case R.id.textView3:
				startMakingCall(mWithoutTollFreeNumber.getText().toString());
				break;
		
			/*	
			case R.id.TextView03:
				WebView(mWebAddress1.getText().toString());
				break;
			
			case R.id.TextView7:
				WebView(mWebAddress2.getText().toString());
				break;
				
			case R.id.textView4:
				WebView(mWebAddress3.getText().toString());
				break;
			*/
				
			//case R.id.TextView03:
				//startMakingCall(mWithoutTollFreeNumber1.getText().toString());
				//break;
	
		
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
	
/*	private void startULR(String url) {
		Intent i = new Intent(Intent.ACTION_VIEW);
		  i.setData(Uri.parse(url));
		  startActivity(i);
		
		
	}
*/
	/*
	
private void WebView(String webConnect){
	
	//Intent browserIntent = new Intent("android.intent.action.VIEW", Uri.parse(webConnect));
	
	Intent webIntentActivity=new Intent(Intent.ACTION_VIEW);
	webIntentActivity.setData(Uri.parse(webConnect));
	
	startActivity(webIntentActivity);
	
}
*/	
	
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.education, menu);
		return true;
	}
	
	

}

package com.example.ngo;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.View;

public class Index extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_index);
	}

	public void onRegister(View view) {
		startActivity(new Intent(Index.this,
				Register.class));
	}

	public void onLogin(View view) {
		startActivity(new Intent(Index.this,
				Login1.class));
	}

	public void onVolunteer(View view) {
		startActivity(new Intent(Index.this,
				Volunteer.class));
	}

	public void onMakeDonation(View view) {
		startActivity(new Intent(Index.this,
				Donation.class));
	}

	public void onEmergency(View view) {
		startActivity(new Intent(Index.this,
				Emergencyhelp.class));
	}

	public void onFaq(View view) {
		startActivity(new Intent(Index.this,
				Faq.class));
	}
}

	



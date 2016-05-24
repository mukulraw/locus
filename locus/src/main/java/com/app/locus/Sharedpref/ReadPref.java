package com.app.locus.Sharedpref;


import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

public class ReadPref {
	
	Context context;
	private SharedPreferences prefs;
	 String myprefs="ourteam";
	 int mode = Activity.MODE_PRIVATE;
	boolean result=false;
	String TAG="ReadPref";
	String res="";
	
	public ReadPref(Context context){
		this.context = context;
		prefs = this.context.getSharedPreferences(myprefs, mode);
	}
	
	public String getUserId(){
		res = "";
		res = prefs.getString("userid", "");
		return res;
	}

	public boolean getIsUserVerified(){
		boolean res;
		res = prefs.getBoolean("isverified", false);
		return res;
	}

}

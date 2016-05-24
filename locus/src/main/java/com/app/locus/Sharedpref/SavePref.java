package com.app.locus.Sharedpref;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

public class SavePref {
	
	Context context;
	SharedPreferences preferences;
	 String myprefs="ourteam";
	 int mode = Activity.MODE_PRIVATE;
	 
	 public SavePref(Context context){
			this.context = context;
			preferences = context.getSharedPreferences(myprefs, mode);
		}
	 
	 public void saveUserId(String string){
	        SharedPreferences.Editor editor = preferences.edit();
	        editor.putString("userid",string );
	        editor.commit();
		}

	public void saveIsUserVerified(Boolean isverified){
		SharedPreferences.Editor editor = preferences.edit();
		editor.putBoolean("isverified", isverified );
		editor.commit();
	}
 }

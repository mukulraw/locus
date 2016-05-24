package com.app.locus.utils;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;


public class Util {
	Context context;
	public Util(Context context){
		this.context = context;
	}
	
	public static boolean emailValidater(String email){
		boolean isValid;
		String emailStr = email.trim();

		String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

		if (emailStr.matches(emailPattern))
		{
			isValid=true;
		}
		else 
		{
			isValid=false;
		}
		return isValid;
	}

	final static String[] hex = { "%00", "%01", "%02", "%03", "%04", "%05",
			"%06", "%07", "%08", "%09", "%0a", "%0b", "%0c", "%0d", "%0e",
			"%0f", "%10", "%11", "%12", "%13", "%14", "%15", "%16", "%17",
			"%18", "%19", "%1a", "%1b", "%1c", "%1d", "%1e", "%1f", "%20",
			"%21", "%22", "%23", "%24", "%25", "%26", "%27", "%28", "%29",
			"%2a", "%2b", "%2c", "%2d", "%2e", "%2f", "%30", "%31", "%32",
			"%33", "%34", "%35", "%36", "%37", "%38", "%39", "%3a", "%3b",
			"%3c", "%3d", "%3e", "%3f", "%40", "%41", "%42", "%43", "%44",
			"%45", "%46", "%47", "%48", "%49", "%4a", "%4b", "%4c", "%4d",
			"%4e", "%4f", "%50", "%51", "%52", "%53", "%54", "%55", "%56",
			"%57", "%58", "%59", "%5a", "%5b", "%5c", "%5d", "%5e", "%5f",
			"%60", "%61", "%62", "%63", "%64", "%65", "%66", "%67", "%68",
			"%69", "%6a", "%6b", "%6c", "%6d", "%6e", "%6f", "%70", "%71",
			"%72", "%73", "%74", "%75", "%76", "%77", "%78", "%79", "%7a",
			"%7b", "%7c", "%7d", "%7e", "%7f", "%80", "%81", "%82", "%83",
			"%84", "%85", "%86", "%87", "%88", "%89", "%8a", "%8b", "%8c",
			"%8d", "%8e", "%8f", "%90", "%91", "%92", "%93", "%94", "%95",
			"%96", "%97", "%98", "%99", "%9a", "%9b", "%9c", "%9d", "%9e",
			"%9f", "%a0", "%a1", "%a2", "%a3", "%a4", "%a5", "%a6", "%a7",
			"%a8", "%a9", "%aa", "%ab", "%ac", "%ad", "%ae", "%af", "%b0",
			"%b1", "%b2", "%b3", "%b4", "%b5", "%b6", "%b7", "%b8", "%b9",
			"%ba", "%bb", "%bc", "%bd", "%be", "%bf", "%c0", "%c1", "%c2",
			"%c3", "%c4", "%c5", "%c6", "%c7", "%c8", "%c9", "%ca", "%cb",
			"%cc", "%cd", "%ce", "%cf", "%d0", "%d1", "%d2", "%d3", "%d4",
			"%d5", "%d6", "%d7", "%d8", "%d9", "%da", "%db", "%dc", "%dd",
			"%de", "%df", "%e0", "%e1", "%e2", "%e3", "%e4", "%e5", "%e6",
			"%e7", "%e8", "%e9", "%ea", "%eb", "%ec", "%ed", "%ee", "%ef",
			"%f0", "%f1", "%f2", "%f3", "%f4", "%f5", "%f6", "%f7", "%f8",
			"%f9", "%fa", "%fb", "%fc", "%fd", "%fe", "%ff" };

	public static String encode(String s) {
		StringBuffer sbuf = new StringBuffer();
		try {
			int len = s.length();

			for (int i = 0; i < len; i++) {
				int ch = s.charAt(i);
				if ('A' <= ch && ch <= 'Z') { // 'A'..'Z'
					sbuf.append((char) ch);
				} else if ('a' <= ch && ch <= 'z') { // 'a'..'z'
					sbuf.append((char) ch);
				} else if ('0' <= ch && ch <= '9') { // '0'..'9'
					sbuf.append((char) ch);
				} else if (ch == ' ') { // space
					sbuf.append('+');
				} else if (ch == '-'
						|| ch == '_' // unreserved
						|| ch == '.' || ch == '!' || ch == '~' || ch == '*'
						|| ch == '\'' || ch == '(' || ch == ')') {
					sbuf.append((char) ch);
				} else if (ch <= 0x007f) { // other ASCII
					sbuf.append(hex[ch]);
				} else if (ch <= 0x07FF) { // non-ASCII <= 0x7FF
					sbuf.append(hex[0xc0 | (ch >> 6)]);
					sbuf.append(hex[0x80 | (ch & 0x3F)]);
				} else { // 0x7FF < ch <= 0xFFFF
					sbuf.append(hex[0xe0 | (ch >> 12)]);
					sbuf.append(hex[0x80 | ((ch >> 6) & 0x3F)]);
					sbuf.append(hex[0x80 | (ch & 0x3F)]);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return sbuf.toString();
	}
	
/*public void showDialog(String msg){
		
		AlertDialog alertDialog = new AlertDialog.Builder(context).create();
		alertDialog.setTitle("Alert!");
		alertDialog.setMessage(msg);
		
		 alertDialog.setButton("OK", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                	
                }
        });
 
        alertDialog.show();
	}*/
	
/*
	public void showDialog(String string){
		Button cancelBtn,contBtn;
		TextView textView,header;
			
			final Dialog alertDialog = new Dialog(context);
			alertDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
			alertDialog.setContentView(R.layout.alart_dialog);
			cancelBtn = (Button) alertDialog.findViewById(R.id.conCancel_btn);
			contBtn = (Button) alertDialog.findViewById(R.id.keepRes_btn);
			textView = (TextView) alertDialog.findViewById(R.id.confirmcancel_txt);
			header = (TextView) alertDialog.findViewById(R.id.textView11);
			textView.setText(string);
			
			alertDialog.setTitle("Alert!");
		//	alertDialog.setMessage(msg);
			
			android.graphics.Typeface face1 = android.graphics.Typeface
					.createFromAsset(context.getAssets(), "font/RobotoRegular.ttf");
			
			cancelBtn.setTypeface(face1);
			contBtn.setTypeface(face1);
			textView.setTypeface(face1);
			header.setTypeface(face1);
			
			cancelBtn.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					*//*savePref.saveCurrentRideId("0");
					startActivity(new Intent(FindingNearest.this,PickupLocation.class));
					PickupLocation.isVehicalSelected = false;
					Constants.dropoffLocation = "";
					finish();
					*//*
					//new CancelRide().execute();
				}
			});
			
			contBtn.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					alertDialog.dismiss();
				}
			});
	 try{
	        alertDialog.show();
	 }catch(Exception e){
		 e.printStackTrace();
	 }
		}
	*/
	
	
/*
public String RegisterWithGCM(Context ctx)
{      
	String regId;
	SavePref savePref = new SavePref(context);
	try{
    GCMRegistrar.checkDevice(context);
    GCMRegistrar.checkManifest(context);
    regId = GCMRegistrar.getRegistrationId(context);
    if (regId.equals("")) {
      GCMRegistrar.register(context, "744279721335"); // Note: get the sender id from configuration.
    } else {
         
      Log.v("Registration", "Already registered, regId: " + regId);
    }
    savePref.saveRegId(regId);
    return regId;
	}
	catch(Exception e)
	{
		e.printStackTrace();
		return null;
	}
}*/


public  boolean isNetworkAvailable(Context context) {
	
	NetworkInfo info = (NetworkInfo) ((ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE)).getActiveNetworkInfo();
	
	if (info == null)
    {
         return false;
    }
    else
    {
        if(info.isConnected())
        {
            return true;
        }
        else
        {
            return true;
        }

    }
  

}
}

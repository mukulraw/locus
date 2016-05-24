package com.app.locus.assignment;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ListActivity;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
 
 
public class SplashActivity extends Activity implements OnClickListener{

    //private  EditText editTextemail;

    private EditText editTextUserName;
    private EditText editTextPassword;
    
    private TextView tvRegistration;
	private TextView tvForgotPassword;
 
    public static final String MyPREFERENCES="MyPrefs";
    public static final String USER_NAME = "USERNAME";

    String email;
    String name;
    String password;

    InputStream is = null;

    String json = null;
    JSONObject jObj = null;
    String result = null;
    private static final String FORGOT_URL = "http://www.kickassassignmenthelp.com/wp-content/themes/assignment/lost-data.php";
    //http://www.kickassassignmenthelp.com/wp-content/themes/assignment/lost-data.php
 
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
 
        editTextUserName = (EditText) findViewById(R.id.et_userName);
        editTextPassword = (EditText) findViewById(R.id.et_password);

        //editTextemail=(EditText)findViewById(R.id.et_email);
        
        
    	tvRegistration = (TextView) findViewById(R.id.tv_register);
		tvForgotPassword = (TextView) findViewById(R.id.tv_forgotPassword);
		
		tvRegistration.setOnClickListener(this);
		tvForgotPassword.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				showInputDialog();
				
						
			}

		});
		
    }
    
    private void showInputDialog() {
		// TODO Auto-generated method stub
    	// get prompts.xml view
    			LayoutInflater layoutInflater = LayoutInflater.from(SplashActivity.this);
    			View promptView = layoutInflater.inflate(R.layout.dialog_signin, null);
    			AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(SplashActivity.this);
    			alertDialogBuilder.setView(promptView);

    			final EditText editTextemail = (EditText) promptView.findViewById(R.id.et_email);
    			// setup a dialog window
        final AlertDialog.Builder builder = alertDialogBuilder.setCancelable(false)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        //editTextUserName.setText("Hello, " + editTextUserName.getText().toString());
                        //String gh = editTextUserName.getText().toString();
                        //Toast.makeText(getApplicationContext(), "hell0"+gh,  Toast.LENGTH_SHORT).show();
                        email = editTextemail.getText().toString().trim();

                       // Toast.makeText(getApplicationContext(), "hell0"+email,  Toast.LENGTH_SHORT).show();

                        System.out.println("------------Forgot data Conditon---------------" + email);

                           if(email.length()==0){
                               Toast.makeText(SplashActivity.this,"Please Enter Your Email ID",Toast.LENGTH_SHORT).show();
                           }

                        else {
                               onsend(email);
                           }
                    }

                    private void onsend(final String email){
                    /*
     * AsyncTask is an abstract class. which helps us to use the UI thread properly.
     */
                        class forgotpassword extends AsyncTask<String, Void, String> {
                            ProgressDialog loading;
                            RegisterUserClass ruc = new RegisterUserClass();

                            // forgotpassword
                                    /*
                                               @Override
                                               protected void onPreExecute() {
                                                   super.onPreExecute();
                                                   loading = ProgressDialog.show(Register.this, "Please Wait",null, true, true);
                                               }
                                    */
        /*
         * (non-Javadoc)
         * @see android.os.AsyncTask#onPreExecute()
         *
         * This method is called before doInBackground method is called.
         */
                            @Override
                            protected void onPreExecute() {
                                super.onPreExecute();
                                loading = new ProgressDialog(SplashActivity.this);
                                loading.setMessage("Please Wait...");
                                loading.show();


                            }

                            /*
                             * (non-Javadoc)
                             * @see android.os.AsyncTask#onPostExecute(java.lang.Object)
                             *
                             * This method is called after doInBackground method completes processing.
                             * Result from doInBackground is passed to this method.
                             */
                            @Override
                            protected void onPostExecute(String s) {
                                super.onPostExecute(s);
                                loading.dismiss();
                                Toast.makeText(getApplicationContext(), s, Toast.LENGTH_LONG).show();
                               // Toast.makeText(getApplicationContext(), "New password has been sent on your mail id Please check !", Toast.LENGTH_LONG).show();
                                 editTextemail.setText("");


                            }

                            /*
                             * (non-Javadoc)
                             * @see android.os.AsyncTask#doInBackground(Params[])
                             *
                             * Code performing long running operation goes in this method.
                             * When onClick method is executed on click of button,
                             * it calls execute method which accepts parameters and automatically calls doInBackground method with the parameters passed.
                             */
                            @Override
                            protected String doInBackground(String... params) {

                              //  HashMap<String, String> data = new HashMap<String, String>();

                                List<NameValuePair> data = new ArrayList<NameValuePair>();
                                data.add(new BasicNameValuePair("email" , params[0]));


                                try {
                                    HttpClient client = new DefaultHttpClient();
                                    HttpPost post = new HttpPost(FORGOT_URL);
                                    post.setEntity(new UrlEncodedFormEntity(data));
                                    HttpResponse response = client.execute(post);
                                    HttpEntity entity = response.getEntity();
                                    is = entity.getContent();


                                } catch (UnsupportedEncodingException e) {
                                    e.printStackTrace();
                                } catch (ClientProtocolException e) {
                                    e.printStackTrace();
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }


                                try {
                                    BufferedReader reader = new BufferedReader(new InputStreamReader(
                                            is, "utf-8"), 8);
                                    StringBuilder sb = new StringBuilder();
                                    String line = null;
                                    while ((line = reader.readLine()) != null) {
                                        sb.append(line).append("\n");
                                    }
                                    is.close();
                                    json = sb.toString();
                                } catch (Exception e) {
                                    Log.e("Buffer Error", "Error converting result " + e.toString());
                                }

                                // String result = ruc.sendPostRequest(FORGOT_URL, data);

                            //   System.out.println(result);
                               // Toast.makeText(getApplicationContext(), "Hiiii"+result,  Toast.LENGTH_SHORT).show();

                                try {
                                    jObj = new JSONObject(json);
                                } catch (JSONException e) {
                                    Log.e("JSON Parser", "Error parsing data " + e.toString());
                                }

                                try {
                                    String TAG = "message";
                                    if (jObj != null) {
                                        result = jObj.getString(TAG);
                                    }
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }



                                return result;


                            }
                        }

                        forgotpassword ru = new forgotpassword();
                        ru.execute(email);
                    }


                })
                .setNegativeButton("Cancel",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });

        // create an alert dialog
    			AlertDialog alert = alertDialogBuilder.create();
        alert.show();
    		}
    	
		
	
    public void invokeLogin(View view){
    	name = editTextUserName.getText().toString();




        publicvarr.name=name;




        password = editTextPassword.getText().toString();
        
        if(name.length()==0){
            Toast.makeText(SplashActivity.this,"Please Enter Your Name",Toast.LENGTH_SHORT).show();
        }else if(password.length()==0){
            Toast.makeText(SplashActivity.this,"Please Enter Your Password",Toast.LENGTH_SHORT).show();
        }

        else{
              login(name,password);
            }
 
    }
 
    private void login(final String name, String password) {
    	
    	
 
        class LoginAsync extends AsyncTask<String, Void, String>{
 
           // private Dialog loadingDialog;
          private ProgressDialog loading;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = new ProgressDialog(SplashActivity.this);
                loading.setMessage("Please Wait...");
                loading.show();
               
            }

            @Override
            protected String doInBackground(String... params) {
            	
            	String name = params[0];
                String password = params[1];
 
                InputStream is = null;
                List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
                nameValuePairs.add(new BasicNameValuePair("name", name));
                nameValuePairs.add(new BasicNameValuePair("password", password));
                
                Log.d("LIST_VALUE_PAIR", name);
                String result = null;
 
                try{
                    HttpClient httpClient = new DefaultHttpClient();
                    HttpPost httpPost = new HttpPost(
                            "http://www.kickassassignmenthelp.com/wp-content/themes/assignment/app-login.php");
                    httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
 
                    HttpResponse response = httpClient.execute(httpPost);
 
                    HttpEntity entity = response.getEntity();
 
                    is = entity.getContent();
 
                    BufferedReader reader = new BufferedReader(new InputStreamReader(is, "UTF-8"), 8);
                    StringBuilder sb = new StringBuilder();
 
                    String line = null;
                    while ((line = reader.readLine()) != null)
                    {
                        sb.append(line + "\n");
                    }
                    result = sb.toString();
                } catch (ClientProtocolException e) {
                    e.printStackTrace();
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return result;
            }
 
            @Override
            protected void onPostExecute(String result){

                if (result == null){
                    loading.dismiss();
                }else {
                    String s = result.trim();
                    Log.d("LIST_VALUE_PAIR_CONDIT", s);

                    loading.dismiss();
                    //Intent intent = new Intent(SplashActivity.this, Firstpage.class);

                    //Toast.makeText(getApplicationContext(), s, Toast.LENGTH_LONG).show();
                    if (s.equalsIgnoreCase("success")) {
                        Toast.makeText(getApplicationContext(), s, Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(SplashActivity.this, Firstpage.class);
                        intent.putExtra(USER_NAME, name);
                        finish();
                        startActivity(intent);
                    } else {
                        Toast.makeText(getApplicationContext(), "Invalid User Name or Password", Toast.LENGTH_LONG).show();
                        //Intent intent = new Intent(SplashActivity.this, SplashActivity.class);

                    }

                }
            
            }
        }
 
        LoginAsync la=new LoginAsync();
        la.execute(name,password);
 
    }
 
/*

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.splash, menu);
        return true;
    }
 
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
 
        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
 
        return super.onOptionsItemSelected(item);
    }
*/

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		
		switch (v.getId()) {
		case R.id.tv_register:
			startActivity(new Intent(SplashActivity.this,
					Register.class));
			break;

		case R.id.tv_forgotPassword:
			/*startActivity(new Intent(SplashActivity.this,
					.class));*/
			break;
		}

		
	}

	}
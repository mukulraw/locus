package com.app.locus.assignment;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.app.locus.assignment.Util;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import java.util.ArrayList;

import java.util.List;

public class Register extends Activity implements View.OnClickListener{
 

    private EditText etUserName;
	private EditText etMobileno;
	private EditText etEmailid;
	private EditText etPassword;


    private static final String REGISTER_URL = "http://www.kickassassignmenthelp.com/wp-content/themes/assignment/app-save.php";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register2);
        




         etUserName   = (EditText) findViewById(R.id.et_userName);
		 etMobileno   = (EditText) findViewById(R.id.et_mobileNumber);
		 etEmailid    = (EditText) findViewById(R.id.et_emailId);
		 etPassword   = (EditText) findViewById(R.id.et_password);

        Button buttonRegister = (Button) findViewById(R.id.registerBtn);
		TextView tvlogin = (TextView) findViewById(R.id.tv_login);
        
         buttonRegister.setOnClickListener(this);
         tvlogin.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
      
    	
    	/*if(v == buttonRegister){
            registerUser();
        }
    	*/
    	 switch (v.getId()) {
         case R.id.tv_login:
         	startActivity(new Intent(Register.this,
 					SplashActivity.class));
 			
                 finish();
                 break;

                 case R.id.registerBtn:
                	 registerUser();	 
    	 }
    
    }
 /*
  * using the methode for calling the value data
  */



    private void registerUser() {
        String name  = etUserName.getText().toString().trim().toLowerCase();
        String mobile= etMobileno.getText().toString().trim().toLowerCase();
        String email = etEmailid.getText().toString().trim().toLowerCase();
        String password = etPassword.getText().toString().trim().toLowerCase();
       

        if(name.length()==0){
            Toast.makeText(Register.this,"Please Enter Your Name",Toast.LENGTH_SHORT).show();
        }else if(mobile.length()==0){
            Toast.makeText(Register.this,"Please Enter Your Mobile No.",Toast.LENGTH_SHORT).show();
        }else if(email.length()==0){
            Toast.makeText(Register.this,"Please Enter a Your Email ID",Toast.LENGTH_SHORT).show();
        }else if(!Util.emailValidater(email)){
            Toast.makeText(Register.this,"Please Enter a Valid Email",Toast.LENGTH_SHORT).show();
        }else if(password.length()==0){
            Toast.makeText(Register.this,"Please Enter Your Password",Toast.LENGTH_SHORT).show();
        }

        else{

            ConnectionDetector cd = new ConnectionDetector(getBaseContext());
            if(cd.isConnectingToInternet()) {

                register(name, mobile, email, password);
            }
            else
            {
                Toast.makeText(getBaseContext() , "No Internet Connection" , Toast.LENGTH_SHORT).show();
            }
        }
        
    }
 
    /*
     * AsyncTask is an abstract class. which helps us to use the UI thread properly.
     */
    private void register(String name, String mobile, String email, final String password) {
        @SuppressWarnings("deprecation")
        class RegisterUser extends AsyncTask<String, Void, String>{
            ProgressDialog loading;
            final RegisterUserClass ruc = new RegisterUserClass();

            String result = "";

        /*
         * (non-Javadoc)
         * @see android.os.AsyncTask#onPreExecute()
         * 
         * This method is called before doInBackground method is called.
         */
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = new ProgressDialog(Register.this);
                loading.setMessage("Please Wait...");
                loading.show();
                
               
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



                /*

                HashMap<String, String> data = new HashMap<String,String>();

                data.put("name",params[0]);
                data.put("email",params[1]);
                data.put("mobile",params[2]);

                data.put("password",params[3]);
 
                result = ruc.sendPostRequest(REGISTER_URL,data);
                Log.e("asdasdasd", result);
               // Toast.makeText(getBaseContext() , result , Toast.LENGTH_SHORT).show();

                //Toast.makeText(getApplicationContext(), result, Toast.LENGTH_LONG).show();

                //System.out.println("I'm getting the response........................................ : "+result);

                */


                List<NameValuePair> data = new ArrayList<>();
                data.add(new BasicNameValuePair("name" , params[0]));
                data.add(new BasicNameValuePair("mobile" , params[1]));
                data.add(new BasicNameValuePair("email" , params[2]));
                data.add(new BasicNameValuePair("password" , params[3]));

                result = ruc.sendPostRequest(REGISTER_URL , data);


                Log.e("asdasdasd" , result);

                return  result;

            }

            /*e
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


                String a = "You are register successfully";
                //noinspection StatementWithEmptyBody
                //if (result.equals(a));
                //{
                    Toast.makeText(getApplicationContext(),result, Toast.LENGTH_LONG).show();
                //}



                etUserName.setText("");
                etMobileno.setText("");
                etEmailid.setText("");
                etPassword.setText("");
            }


        }
 
        RegisterUser ru = new RegisterUser();
        ru.execute(name,mobile,email,password);
    }
}
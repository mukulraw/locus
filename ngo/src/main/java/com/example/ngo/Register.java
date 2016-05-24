package com.example.ngo;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
//import android.test.suitebuilder.annotation.MediumTest;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

public class Register extends Activity
        implements RadioGroup.OnCheckedChangeListener{
	
	
	
	private EditText etFirstName;
    private EditText etLastName;
    private EditText etAddress;
    private EditText etMobileNumber;
    private EditText etEmailAddress;
    private EditText etPassword;

    private RadioGroup mRadioGroupSubject;
    private RadioGroup mRadioGroupGender;

    private DatabaseHandler mDatabaseHandler;

    private String txtSubject;
    private String txtGender;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
       
        init();
    }

    /**
     * Initializing all views.
     */
    private void init() {
        etFirstName = (EditText) findViewById(R.id.editText1);
        etLastName = (EditText) findViewById(R.id.editText2);
        etAddress = (EditText) findViewById(R.id.editText3);
        etMobileNumber = (EditText) findViewById(R.id.editText4); 
        
        etEmailAddress = (EditText) findViewById(R.id.editText5);
        etPassword = (EditText) findViewById(R.id.editText6);

        mRadioGroupSubject = (RadioGroup) findViewById(R.id.radioGroupSubject);
        mRadioGroupSubject.setOnCheckedChangeListener(Register.this);

        mRadioGroupGender = (RadioGroup) findViewById(R.id.radioGroupGender);
        mRadioGroupGender.setOnCheckedChangeListener(Register.this);

        mDatabaseHandler = DatabaseHandler.getInstance(Register.this);
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch (checkedId) {
            case R.id.radioBtnRegular:
                txtSubject = "Regular";
                break;

            case R.id.radioBtnOccasionaly:
                txtSubject = "Occasionally";
                break;

            case R.id.radioBtnMale:
                txtGender = "Male";
                break;

            case R.id.radioBtnFemale:
                txtGender = "FeMale";
                break;
        }
    }

    /**
     * On click of register button
     * @param view passing view.
     */
    public void onRegister(View view) {

        if (!hasPopulated())
            return;

        UserModel userModel = new UserModel();

        try {
            userModel.setFirstName(etFirstName.getText().toString());
            userModel.setLastName(etLastName.getText().toString());
            userModel.setAddress(etAddress.getText().toString());
            userModel.setMobileNumber(etMobileNumber.getText().toString());
            userModel.setEmailAddress(etEmailAddress.getText().toString());
            userModel.setPassword(etPassword.getText().toString());
            userModel.setSubject(txtSubject);
            userModel.setGender(txtGender);
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        
        
       
            if (etMobileNumber.getText().toString().trim().length()<10) {
            	//etMobileNumber.setError("Value should be 10 characters in length");
            
            	//finish();
            	
            	//etMobileNumber.requestFocus();
            	
            	   AlertDialog.Builder builder = new AlertDialog.Builder(Register.this);
                   builder.setTitle("Attention")
                           .setMessage("Not valid Mobile no.")
                           .setCancelable(false)
                           .setNegativeButton(android.R.string.ok,
                                   new DialogInterface.OnClickListener() {
                               public void onClick(DialogInterface dialog, int id) {
                                   dialog.cancel();
                                   finish();
                               }
                           });
                   AlertDialog alert = builder.create();
                   alert.show();
                  
                 
            	startActivity(new Intent(Register.this,
            			Register.class));
           		finish();
            	
            }           
            
            /*
            else {

            	
            	AlertDialog.Builder builder = new AlertDialog.Builder(Register.this);
                builder.setTitle("Attention")
                        .setMessage("You have register successfully.")
                        .setCancelable(false)
                        .setNegativeButton(android.R.string.ok,
                                new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                                finish();
                            }
                        });
                AlertDialog alert = builder.create();
                alert.show();
                

    			startActivity(new Intent(Register.this,
    					Index.class));
    			finish();
    		
            

                 //Value is valid (between 1 and 175 characters long)

                  // String text = etMobileNumber.getText().toString();

                 //submit
            } 
        
        
*/        
        
        
        
        if(etEmailAddress.getText().toString().endsWith(".com")&& etEmailAddress.getText().toString().contains("@"))
        {
        	
        mDatabaseHandler.addVolunteer(userModel);

       
        AlertDialog.Builder builder = new AlertDialog.Builder(Register.this);
        builder.setTitle("Attention")
                .setMessage("You have register successfully.")
                .setCancelable(false)
                .setNegativeButton(android.R.string.ok,
                        new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                        finish();
                    }
                });
        AlertDialog alert = builder.create();
        alert.show();
        
        startActivity(new Intent(Register.this,
				Index.class));
		finish();
        
        }
        else
        {
        	AlertDialog.Builder builder = new AlertDialog.Builder(Register.this);
            builder.setTitle("Attention")
                    .setMessage("Not valid Email")
                    .setCancelable(false)
                    .setNegativeButton(android.R.string.ok,
                            new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            dialog.cancel();
                            finish();
                        }
                    });
            AlertDialog alert = builder.create();
            alert.show();
            

			startActivity(new Intent(Register.this,
					Register.class));
			finish();
		
        }
        	

    }
    
    /**
     * This method will check user has enter
     * value or not in edit text field.
     * @return true if all field full else false.
     */
    private boolean hasPopulated() {

        if (etFirstName.getText().length() == 0) {
            etFirstName.setError("Required");
            return false;
        } else if (etLastName.getText().length() == 0) {
            etLastName.setError("Required");
            return false;
        } else if (etAddress.getText().length() == 0) {
            etAddress.setError("Required");
            return false;
        } else if (etMobileNumber.getText().length() == 0) {
            etMobileNumber.setError("Required");
            return false;
        } else if (etEmailAddress.getText().length() == 0) {
            etEmailAddress.setError("Required");
            return false;
        } else if (etPassword.getText().length() == 0) {
            etPassword.setError("Required");
            return false;
        } else if (mRadioGroupSubject.getCheckedRadioButtonId() <=0) {
            Toast.makeText(Register.this,
                    "Please select VolunteerType.", Toast.LENGTH_LONG).show();
            return false;
        } else if (mRadioGroupGender.getCheckedRadioButtonId() <=0) {
            Toast.makeText(Register.this,
                    "Please select Gender.", Toast.LENGTH_LONG).show();
            return false;
        }

        return true;
    }

    /**
     * On click of cancel button closing the activity.
     * @param view passing view.
     */
    public void onCancel(View view) {
        finish();
    }
}

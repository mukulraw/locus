package com.example.ngo;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.Spinner;
import android.widget.AdapterView.OnItemSelectedListener;
 
public class Post_Information extends Activity
 implements RadioGroup.OnCheckedChangeListener{
 
	private EditText name;
    private EditText location;
    private EditText mobileno;
    
    private RadioGroup type;
	
    private Spinner donation;
    
    private DatabaseHandler mDatabaseHandler;

    private String type1;
   
	
    @Override
  //  brew_array
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post__information);
        init();
 
        Spinner staticSpinner = (Spinner) findViewById(R.id.static_spinner);
 
        // Create an ArrayAdapter using the string array and a default spinner
        ArrayAdapter<CharSequence> staticAdapter = ArrayAdapter
                .createFromResource(this, R.array.brew_array,
                        android.R.layout.simple_spinner_item);
 
        // Specify the layout to use when the list of choices appears
       
        
        staticAdapter
                .setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
 
        // Apply the adapter to the spinner
        staticSpinner.setAdapter(staticAdapter);
 /*
        Spinner dynamicSpinner = (Spinner) findViewById(R.id.dynamic_spinner);
 
        String[] items = new String[] { "Chai Latte", "Green Tea", "Black Tea" };
 
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, items);
 
        dynamicSpinner.setAdapter(adapter);
 
       */
        
        staticSpinner.setOnItemSelectedListener(new OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                    int position, long id) {
                Log.v("item", (String) parent.getItemAtPosition(position));
            }
 
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // TODO Auto-generated method stub
            }
        });
    }

	private void init() {
		// TODO Auto-generated method stub	
		
		name=(EditText) findViewById(R.id.editText1);
		location=(EditText) findViewById(R.id.editText2);
		mobileno=(EditText) findViewById(R.id.editText3);
		
		
		 type= (RadioGroup) findViewById(R.id.radioGroup1);
	     type.setOnCheckedChangeListener(Post_Information.this);

	     donation=(Spinner) findViewById(R.id.static_spinner);
	     
	     
	    
	     mDatabaseHandler = DatabaseHandler.getInstance(Post_Information.this);
	       
	}
	
	 public void onCheckedChanged(RadioGroup group, int checkedId) {
	        switch (checkedId) {
	            case R.id.radio0:
	                type1 = "Donar";
	                break;

	            case R.id.radio1:
	                type1 = "Help_Sheeker";
	                break;

	          	        }
	   
	 }
	 
	 public void onRegister(View view) {

	        if (!hasPopulated())
	            return;

	        PostModle postModel = new PostModle();

	        try {
	        	
	        	
	        	postModel.setName(name.getText().toString());
	        	postModel.setLocation(location.getText().toString());
	        	postModel.setMobileno(mobileno.getText().toString());
	        	if(type1==null)
	            type1="Donar";
	        	
	        	postModel.setType(type1);
	        	postModel.setHelptype((String)donation.getSelectedItem());
	        	
	           

	        } catch (Exception e) {
	            e.printStackTrace();
	        }

	        mDatabaseHandler.postInformation(postModel);

	        AlertDialog.Builder builder = new AlertDialog.Builder(Post_Information.this);
	        builder.setTitle("Attention")
	                .setMessage("You have posted information successfully.")
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

	    }

	    /**
	     * This method will check user has enter
	     * value or not in edit text field.
	     * @return true if all field full else false.
	     */
	    private boolean hasPopulated() {

	        if (name.getText().length() == 0) {
	            name.setError("Required");
	            return false;
	        } else if (location.getText().length() == 0) {
	        	location.setError("Required");
	            return false;
	        } else if (mobileno.getText().length() == 0) {
	        	mobileno.setError("Required");
	            return false;
	        }
	        else if (type.getCheckedRadioButtonId() <=0) {
	            Toast.makeText(Post_Information.this,
	                    "Please select subject.", Toast.LENGTH_LONG).show();
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

	 




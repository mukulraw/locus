package com.app.locus.assignment;
/*
import android.app.Activity;
import android.os.Bundle;

*/
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.Spinner;



public class Test extends Activity implements OnClickListener{

	private EditText et_yourName;
	private EditText et_yourPhone;
	private EditText et_yourEmail;
	private EditText et_LastOfSubmission;
	private EditText et_Expectedquoteinsud;
	private EditText et_textarea;

	private Spinner spnr_reference;
	private Spinner spnr_subject;
	private Spinner spnr_grade;
	private Spinner spnr_country;
	
	//private Button btnUploadFile;
	//private Button btnRegister;
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		
		//inti();
		
	}
	
/*
Start from there its a comment part	
*/
	private void inti() {
		// TODO Auto-generated method stub
		et_yourName=(EditText)findViewById(R.id.et_Name);
		et_yourPhone=(EditText)findViewById(R.id.et_Phone);
		et_yourEmail=(EditText)findViewById(R.id.et_Email);
		et_LastOfSubmission=(EditText)findViewById(R.id.et_lastOfSubmission);
		et_Expectedquoteinsud=(EditText)findViewById(R.id.et_Expectedquoteinusd);
		et_textarea=(EditText)findViewById(R.id.et_Textarea);
		
		spnr_reference=(Spinner)findViewById(R.id.spnr_Reference);
		spnr_subject=(Spinner)findViewById(R.id.spnr_Subject);
		spnr_grade=(Spinner)findViewById(R.id.spnr_Grade);
		spnr_country=(Spinner)findViewById(R.id.spnr_Country);
		
		
	}
	
	public void onSubmit(View v){
		if(!hasPopulated())
			return;
		
	}

	private boolean hasPopulated() {
		// TODO Auto-generated method stub
		try{
			if(et_yourName.getText().length()==0){
				et_yourPhone.setError("Required");
				return false;
			}
			else if(et_yourPhone.getText().length()==0){
				et_yourPhone.setError("Required");
				return false;
			}
			else if (et_yourEmail.getText().length()==0) {
				et_yourEmail.setError("Required");
				return false;
			}
			else if (et_LastOfSubmission.getText().length()==0) {
				et_LastOfSubmission.setError("Required");
				return false;
			}
			else if(et_Expectedquoteinsud.getText().length()==0){
				et_Expectedquoteinsud.setError("Required");
				return false;
			}
			//else if(et)
			
		}
		catch(Exception e){
			
		}
		
		return false;
	}

	
	/*
	 * 
	 * There is a last part of this comments
	*/
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		
	}
	

}

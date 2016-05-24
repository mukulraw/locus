package com.example.ngo;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

public class Applicationform extends Activity
        {
	
	private EditText etFirstName;
    private EditText etAddress;
    private DatabaseHandler mDatabaseHandler;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_applicationform);
       
        init();
    }

    /**
     * Initializing all views.
     */
    private void init() {
        etFirstName = (EditText) findViewById(R.id.editText1);
        etAddress = (EditText) findViewById(R.id.editText3);

        mDatabaseHandler = DatabaseHandler.getInstance(Applicationform.this);
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
            userModel.setAddress(etAddress.getText().toString());
            } catch (Exception e) {
            e.printStackTrace();
        }

        mDatabaseHandler.addVolunteer(userModel);

        AlertDialog.Builder builder = new AlertDialog.Builder(Applicationform.this);
        builder.setTitle("Attention")
                .setMessage("You have Submit successfully.")
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

        if (etFirstName.getText().length() == 0) {
            etFirstName.setError("Required");
            return false;
        } else if (etAddress.getText().length() == 0) {
            etAddress.setError("Required");
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

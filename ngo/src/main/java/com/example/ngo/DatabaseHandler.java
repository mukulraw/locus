package com.example.ngo;

import java.util.ArrayList;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;
   
public class DatabaseHandler extends SQLiteOpenHelper {

    /**
     * Log TAG of {@link DatabaseHandler}
     */
    public static String TAG = "DB_HELPER";

    // Database version
    private static final int DATABASE_VERSION = 1;

    // Database instance
    private static DatabaseHandler mDatabaseHandlerInstance;

    // Database name
    private static final String DATABASE_NAME = "ngp.sqlite";

    // Volunteer table
   // private static final String TABLE_CONTACTS="volunteer";

    private static final String TABLE_users="volunteer";
    
    private static final String TABLE_information="postinformation";
    
    private static final String TABLE_donation="donation";
    
    
    // All column name for volunteer table
    
    private static final String KEY_ID                  = "volunteer_id";                   //0
    private static final String KEY_FIRST_NAME          = "first_name";                     //1
    private static final String KEY_LAST_NAME           = "last_name";                      //2
    private static final String KEY_ADDRESS             = "address";                        //3
    private static final String KEY_MOBILE_NUMBER       = "mobile_number";                  //4
    private static final String KEY_EMAIL               = "email";                          //5
    private static final String KEY_PASSWORD            = "password";                       //6
    private static final String KEY_SUBJECT             = "subject";                        //7
    private static final String KEY_GENDER              = "gender";                         //8
   
    
    private static final String Information_ID          = "volunteer_id";                   //0
    private static final String user_name               = "username";                       //1
    private static final String location                = "location";                       //2
    private static final String mobileno                = "mobileno";                       //3
    private static final String type                    = "type";                           //4
    private static final String helptype                = "helptype";                       //5
   
    
    private static final String Donation_ID             = "donation_id";                    //0
    private static final String KEY_name                = "name";                           //1
    private static final String KEY_email               = "email";                          //2
    private static final String KEY_mobile              = "mobileno";                       //3
    private static final String KEY_amount              = "amount";                         //4
    
    
    public static DatabaseHandler getInstance(Context context) {
        // Use the application context, which will ensure that you
        // don't accidentally leak an Activity's context.
        // See this article for more information: http://bit.ly/6LRzfx
        if (mDatabaseHandlerInstance == null) {
            mDatabaseHandlerInstance =
                    new DatabaseHandler(context.getApplicationContext());
        }
        return mDatabaseHandlerInstance;
    }

    /**
     * Constructor of DatabaseHandler.
     * @param context passing application context.
     */
    private DatabaseHandler(Context context) { 
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    @Override
  
    public void onCreate(SQLiteDatabase db) {
    	String CREATE_TABLE_USERS = "CREATE TABLE " + TABLE_users + "("
                + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL ,"
                + KEY_FIRST_NAME + " TEXT ,"
                + KEY_LAST_NAME + " TEXT ,"
                + KEY_ADDRESS + " TEXT ,"
                + KEY_MOBILE_NUMBER + " TEXT ,"
                + KEY_EMAIL + " TEXT ,"
                + KEY_PASSWORD + " TEXT ,"
                + KEY_SUBJECT + " TEXT ,"
                + KEY_GENDER + " TEXT " + ")";
        /*
    	String CREATE_VOLUNTEER_TABLE = "CREATE TABLE " + TABLE_CONTACTS + "("
                + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL ,"
                + KEY_FIRST_NAME + " TEXT ,"
                + KEY_LAST_NAME + " TEXT ,"
                + KEY_ADDRESS + " TEXT ,"
                + KEY_MOBILE_NUMBER + " TEXT ,"
                + KEY_EMAIL + " TEXT ,"
                + KEY_PASSWORD + " TEXT ,"
                + KEY_SUBJECT + " TEXT ,"
                + KEY_GENDER + " TEXT " + ")";

        db.execSQL(CREATE_VOLUNTEER_TABLE);*/
    	
    	
    	  String CREATE_information_table = "CREATE TABLE " + TABLE_information + "("
    	            + Information_ID + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL ,"
    	            + user_name + " TEXT ,"
    	            + location + " TEXT ,"
    	            + mobileno + " TEXT ,"
    	            + helptype + " TEXT ,"
    	            + type + " TEXT " + ")";


      	
      	String CREATE_donation_table = "CREATE TABLE " + TABLE_donation + "("
                  + Donation_ID + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL ,"
                  + KEY_name + " TEXT ,"
                  + KEY_email + " TEXT ,"
                  + KEY_mobile + " TEXT ,"
                  + KEY_amount + " TEXT " + ")";
      	
      	
        Log.d(TAG,"ggggggggggggggggggggggggggggexecuted");

    	db.execSQL(CREATE_TABLE_USERS);
    	db.execSQL(CREATE_information_table);
        db.execSQL(CREATE_donation_table);
        Log.d(TAG,"ggggggggggggggggggggggggggggexecuted after");
    }
    
    /*
    
    String CREATE_information_table = "CREATE TABLE " + TABLE_information + "("
            + Donation_ID + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL ,"
            + name + " TEXT ,"
            +location + " TEXT ,"
            + mobileno + " TEXT ,"
            + helptype + " TEXT ,"
            + type + " TEXT)";

    db.execSQL(CREATE_information_table);
	
}


*/   
    
    
    
    
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_users);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_information);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_donation);
         
        // Create tables again
        onCreate(db);
    }

    /**
     * This method will the volunteer in mobile db.
     * @param mUserModel passing user model.
     */
    
    public void addVolunteer(UserModel mUserModel) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(KEY_FIRST_NAME, mUserModel.getFirstName());
        values.put(KEY_LAST_NAME, mUserModel.getLastName());
        values.put(KEY_ADDRESS, mUserModel.getAddress());
        values.put(KEY_MOBILE_NUMBER, mUserModel.getMobileNumber());
        values.put(KEY_EMAIL, mUserModel.getEmailAddress());
        values.put(KEY_PASSWORD, mUserModel.getPassword());
        values.put(KEY_SUBJECT, mUserModel.getSubject());
        values.put(KEY_GENDER, mUserModel.getGender());

        Log.d(TAG, values.toString());

        // Inserting Row
        db.insert(TABLE_users, null, values);
        

        // Closing database connection
        db.close();
    }
    
    public void postInformation(PostModle postmodel) {
        
    	try
    	{
    		SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(user_name, postmodel.getName());
        values.put(location, postmodel.getLocation());
        values.put(mobileno, postmodel.getMobileno());
        values.put(helptype, postmodel.getHelptype());
        values.put(type, postmodel.getType());

        Log.d(TAG, values.toString());

        // Inserting Row
        db.insert(TABLE_information, null, values);
        // Closing database connection
        db.close();
    }
	catch(Exception ex)
	{
		Toast.makeText(null, "exception is "+ex,3).show();	
	}
    }
    
    
    public void donation(DonationModle donationmodel) {
    	
    	try{
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(KEY_name, donationmodel.getName());
        values.put(KEY_email, donationmodel.getEmail());
        values.put(KEY_mobile, donationmodel.getMobile());
        values.put(KEY_amount, donationmodel.getAmount());
     
        Log.d(TAG, values.toString());

        // Inserting Row
        db.insert(TABLE_donation, null, values);
        
      db.close();
    	}
    	catch(Exception ex)
    	{
    		Toast.makeText(null, "exception is "+ex,3).show();	
    	}
      
      
    }
    
    
    
    

    /**
     * This method will check that email id
     * is exist or not in mobile,
     * @param emailAddress passing email addres of user.
     * @return true if exist otherwise false.
     */
    public boolean isEmailExist(String emailAddress) {
        try {
            SQLiteDatabase db = this.getReadableDatabase();

            Cursor cursor = db.query(TABLE_users, new String[]{KEY_EMAIL},
                    KEY_EMAIL + "=?", new String[]{emailAddress}, null, null, null,
                    null);
            if (cursor != null) {
                if (cursor.moveToFirst()) {
                    return true;
                }
                cursor.close();
            }

            db.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * This method will check that password is matching or not.
     * @param userPassword passing password of user.
     * @return true if exist otherwise false.
     */
    public boolean isPasswordMatch(String userPassword) {
        try {
            SQLiteDatabase db = this.getReadableDatabase();

            Cursor cursor = db.query(TABLE_users, new String[]{KEY_PASSWORD},
                    KEY_PASSWORD + "=?", new String[]{userPassword}, null, null, null,
                    null);
            if (cursor != null) {
                if (cursor.moveToFirst()) {
                    return true;
                }
                cursor.close();
            }

            db.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    
      
    /**
     * get single user profile
     * @return
     */
    public UserModel getUserProfile(){
    	UserModel userModel = null;
        try{
        	String selectQuery = "SELECT  * FROM " + TABLE_users 
        		//	+ " WHERE " + KEY_EMAIL + " = 'jitu@gmail.com'";
        			+ " WHERE " + KEY_EMAIL + " = '"+Publicvar.email+"'";
        	SQLiteDatabase db = this.getWritableDatabase();
        	Cursor cursor = db.rawQuery(selectQuery, null);
		             
        	if (cursor.moveToFirst()) {
        		do{
        		//while(cursor.moveToFirst()){
        			userModel = new UserModel();
        			// 	get  the  data into array,or class variable
        			userModel.setFirstName(cursor.getString(1));
        			userModel.setLastName(cursor.getString(2));
        			userModel.setAddress(cursor.getString(3));
        			userModel.setMobileNumber(cursor.getString(4));
        			userModel.setEmailAddress(cursor.getString(5));
        			userModel.setPassword(cursor.getString(6));
        			userModel.setSubject(cursor.getString(7));
        			userModel.setGender(cursor.getString(8));
        		} while (cursor.moveToNext());
        	}
        	db.close();            	 
        }catch(Exception ex) {
        	Toast.makeText(null, "exception is"+ex,3).show();	 
        }
             
        return userModel;
    }
    
    /**
     * Get all user data on admin page.
     * @return all profile details in arraylist.
     */
    public ArrayList<UserModel> getAllUserProfile(){
    	ArrayList<UserModel> userModelArrayList = null;
        try{
        	String selectQuery = "SELECT  * FROM " + TABLE_users;
        	SQLiteDatabase db = this.getWritableDatabase();
        	Cursor cursor = db.rawQuery(selectQuery, null);
		             
        	if (cursor.moveToFirst()) {
        		userModelArrayList = new ArrayList<UserModel>();
        		do {
        			
        			UserModel userModel = new UserModel();
        			// 	get  the  data into array,or class variable
        			userModel.setFirstName(cursor.getString(1));
        			userModel.setLastName(cursor.getString(2));
        			userModel.setAddress(cursor.getString(3));
        			userModel.setMobileNumber(cursor.getString(4));
        			userModel.setEmailAddress(cursor.getString(5));
        			userModel.setPassword(cursor.getString(6));
        			userModel.setSubject(cursor.getString(7));
        			userModel.setGender(cursor.getString(8));
        			
        			userModelArrayList.add(userModel);
        		} while (cursor.moveToNext());
        	}
        	db.close();            	 
        }catch(Exception ex) {
        	Toast.makeText(null, "exception is"+ex,3).show();	 
        }
             
        return userModelArrayList;
    }
 
    
    
    
    
    public ArrayList<PostModle> getAllPostInformationdata(){
    	ArrayList<PostModle> userPostInformationdataArraylist = null;
        try{
        	String selectQuery ="SELECT  * FROM " + TABLE_information;
        	SQLiteDatabase db = this.getWritableDatabase();
        	Cursor cursor = db.rawQuery(selectQuery, null);
        	//Toast.makeText(null, "db executed",3).show();      	
        	if (cursor.moveToFirst()) {
        		userPostInformationdataArraylist = new ArrayList<PostModle>();
        		do {
        			
        			PostModle postModle = new PostModle();
        			// 	get  the  data into array,or class variable
        			
        			//String name=(cursor.getString(1));
        			postModle.setName(cursor.getString(1));
        			//Toast.makeText(null, "name is"+name,3).show();
        			postModle.setMobileno(cursor.getString(2));
        			postModle.setLocation(cursor.getString(3));
        			postModle.setHelptype(cursor.getString(4));
        			postModle.setType(cursor.getString(5));
        			userPostInformationdataArraylist.add(postModle);
        			
        			
        			
        		} while (cursor.moveToNext());
        	}
        	db.close();            	 
        }catch(Exception ex) {
        	Toast.makeText(null, "exception in db method is"+ex,3).show();	 
        }
             
        return userPostInformationdataArraylist;
    }
 
    
    
    
    
    public ArrayList<DonationModle>  getAllmakedonation(){
    	
    	ArrayList<DonationModle> usermakedonationArraylist = null;
        try{
        	      
        	String selectQuery = "SELECT  * FROM " + TABLE_donation;
        	SQLiteDatabase db = this.getWritableDatabase();
        	Cursor cursor = db.rawQuery(selectQuery, null);
        	      
        	if (cursor.moveToFirst()) {
        		usermakedonationArraylist = new ArrayList<DonationModle>();
        		do {
        			//while(cursor.moveToFirst()){
        			DonationModle donationmodle = new DonationModle();
        			// 	get  the  data into array,or class variable
        			
//        			String name=(cursor.getString(1));
//        			donationmodle.setName(name);
//        			Toast.makeText(null, "name is"+name,1).show();
        			donationmodle.setName(cursor.getString(1));
        			donationmodle.setMobile(cursor.getString(2));
        			donationmodle.setEmail(cursor.getString(3));
        			donationmodle.setAmount(cursor.getString(4));
        		
        			usermakedonationArraylist.add(donationmodle);
        			
        			
        		} while (cursor.moveToNext());
        	}
        	db.close();            	 
        }catch(Exception ex) {
        	Toast.makeText(null, "exception in db method is"+ex,3).show();	 
        }
             
        return usermakedonationArraylist;
    }
     
    
}
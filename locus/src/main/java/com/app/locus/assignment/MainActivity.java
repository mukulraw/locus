package com.app.locus.assignment;

import android.Manifest;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.StrictMode;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.app.locus.utils.Util;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.List;

public class MainActivity extends Activity implements AdapterView.OnItemSelectedListener, OnClickListener{

    private static final int REQUEST_PICK_FILE = 1;
    private static final int REQUEST_PICK_IMAGE = 2;
    String path;

    String imagepath;

    String name, email, phone, country, subject, pagrade, reference, lastdateofsubmission, textarea, paexpect,location,camera;

    private static final int PICK_IMAGE_REQUEST=1888;
    //private static final int TAKE_PICTURE_CODE=1888;

    private TextView filePath;
    private Button Browse;
    //private File selectedFile;

    private EditText et_yourName;
    private EditText et_yourPhone;
    private EditText et_yourEmail;
    private EditText et_LastOfSubmission;
    private EditText et_Expectedquoteinsud;
    private EditText et_textarea;


    private Spinner spnr_country;
    private Spinner spnr_subject;
    private Spinner spnr_grade;
    private Spinner spnr_reference;

   // public Bitmap bitmap;
    private ImageButton bt_camerabutton;
    private TextView tv_cameraview;

   // private Uri pathimage;



    private String listSpinner1[] = {"--Select Country--", "Afghanistan", "Akrotiri", "Albania", "Algeria",
            "American Samoa", "Andorra", "Angola", "Anguilla", "Antarctica", "Antigua and Barbuda", "Argentina", "Armenia",
            "Aruba", "Ashmore and Cartier Islands", "Australia", "Austria", "Azerbaijan", "Bahamas Bahrain", "Bangladesh",
            "Barbados", "Bassas da India", "Belarus", "Belgium", "Belize", "Benin", "Bermuda", "Bhutan", "Bolivia", "Bosnia and Herzegovina",
            "Botswana", "Bouvet Island", "Brazil", "British Indian Ocean Territory", "British Virgin Islands", "Brunei", "Bulgaria",
            "Burkina Faso", "Burma", "Burundi", "Cambodia", "Cameroon", "Canada", "Cape Verde", "Cayman Islands", "Central African Republic",
            "Chad", "Chile", "China", "Christmas Island", "Clipperton Island", "Cocos (Keeling) Islands", "Colombia", "Comoros", "Congo, Democratic Republic of the Congo",
            "Republic of the Cook Islands", "Coral Sea Islands", "Costa Rica", "Cote d'Ivoire", "Croatia", "Cuba", "Cyprus Czech Republic",
            "Denmark", "Dhekelia", "Djibouti", "Dominica", "Dominican Republic", "Ecuador", "Egypt” “El Salvador", "Equatorial Guinea",
            "Eritrea", "Estonia", "Ethiopia", "Europa Island", "Falkland Islands (Islas Malvinas)", "Faroe Islands", "Fiji” “Finland",
            "France", "French Guiana", "French Polynesia", "French Southern and Antarctic Lands", "Gabon", "Gambia", "Gaza Strip", "Georgia",
            "Germany", "Ghana", "Gibraltar", "Glorioso Islands", "Greece", "Greenland", "Grenada", "Guadeloupe", "Guam", "Guatemala",
            "Guernsey", "Guinea", "Guinea-Bissau", "Guyana", "Haiti", "Heard Island and McDonald Island", "Holy See (Vatican City)",
            "Honduras", "Hong Kong", "Hungary", "Iceland", "India", "Indonesia", "Iran", "Iraq", "Ireland", "Isle of Man", "Israel", "Italy",
            "Jamaica", "Jan Mayen", "Japan", "Jersey", "Jordan", "Juan de Nova Island", "Kazakhstan", "Kenya", "Kiribati", "Korea, North",
            "Korea, South", "Kuwait", "Kyrgyzstan", "Laos", "Latvia", "Lebanon", "Lesotho", "Liberia", "Libya", "Liechtenstein", "Lithuania",
            "Luxembourg", "Macau", "Macedonia", "Madagascar", "Malawi", "Malaysia", "Maldives", "Mali", "Malta", "Marshall Islands", "Martinique",
            "Mauritania", "Mauritius", "Mayotte", "Mexico", "Micronesia", "Federated States of Moldova", "Monaco", "Mongolia", "Montserrat",
            "Morocco", "Mozambique", "Namibia", "Nauru Navassa Island", "Nepa", "Netherlands", "Netherlands Antille", "New Caledonia",
            "New Zealand", "Nicaragua", "Niger", "Nigeria", "Niue", "Norfolk Island", "Northern Mariana Islands", "Norway", "Oman", "Pakistan",
            "Palau", "Panama", "Papua New Guinea", "Paracel Islands", "Paraguay", "Peru", "Philippines", "Pitcairn Islands", "Poland",
            "Portugal", "Puerto Rico", "Qatar", "Reunion", "Romania", "Russia", "Rwanda", "Saint Helena", "Saint Kitts and Nevis", "Saint Lucia",
            "Saint Pierre and Miquelon", "Saint Vincent and the Grenadines", "Samoa", "San Marino", "Sao Tome and Principe", "Saudi Arabia",
            "Senegal", "Serbia and Montenegro", "Seychelles", "Sierra Leone", "Singapore", "Slovakia", "Slovenia", "Solomon Islands", "Somalia",
            "South Africa", "South Georgia and the South Sandwich Islands", "Spain", "Spratly Islands", "Sri Lanka", "Sudan", "Suriname",
            "Svalbard", "Swaziland", "Sweden", "Switzerland", "Syria", "Taiwan", "Tajikistan", "Tanzania", "Thailand", "Timor-Leste", "Togo",
            "Tokelau", "Tonga", "Trinidad and Tobago", "Tromelin Island", "Tunisia", "Turkey", "Turkmenistan", "Turks and Caicos Islands",
            "Tuvalu", "Uganda", "Ukraine", "United Arab Emirates", "United Kingdom", "United States", "Uruguay", "Uzbekistan", "Vanuatu",
            "Venezuela", "Vietnam", "Virgin Islands", "Wake Island", "Wallis and Futuna", "West Bank", "Western Sahara", "Yemen", "Zambia",
            "Zimbabwe"};


    private String listSpinner2[] = {"--Select Subject--", "Accounts", "Animation", "Archaeology", "Architecture", "Article Biology",
            "Biotechnology", "Business Communication", "Business Ethics", "Business Laws", "Case Study", "Chemical engineering", "Chemistry Civil engineering",
            "Computer Programming", "Computer science", "Computer-aided design", "Corporate Governance", "Database Dissertation",
            "E-Commerce", "Economics Electrical engineering", "Engineering", "English", "ERP", "Essay Writing", "Finance Geography Geology History",
            "HRM", "Information Technology", "International Business", "literature Management", "Marketing", "Mathematics", "Matlab",
            "Mechanical engineering", "MIS", "Operations Management", "Organisation Behaviour", "Physics", "Physiology", "Political Science",
            "Presentation", "Project Management", "Psychology", "Quantitative Techniques", "Research Methodology", "Science", "Security Analysis and Portfolio Management",
            "Sociology", "Statistics", "Term Paper Writing", "Thesis Writing", "Others"};

    private String listSpinner3[] = {"--Select Grade--", "School Level", "K-12 Level", "High School Level",
            "College Level", "Post Graduate Level", "Engineering Level", "Research Level"};

    private String listSpinner4[] = {"--Select Reference--", "Harvard", "APA", "MLA", "Chicago", "Footnotes",
            "Footnotes and bibliography", "Vancouver", "Turabian"};

    Button submitButton;
    private TextView uname;
/*
========================
    protected LocationManager locationManager;
    protected LocationListener locationListener;
    protected Context context;

    String provider;
    protected double latitude, longitude;
    protected boolean gps_enabled, network_enabled;
=======================
*/

    EditText txtLat;
    EditText txtLong;
    // GPSTracker class
    GpsTracker1 gps;

    String both;
   // String imagepath;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);


        String newuser = publicvarr.name;

        System.out.println("NEW USER IS COMING IN............" + newuser);
        uname = (TextView) findViewById(R.id.data);
        uname.setText(newuser);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
                .detectAll()
                .penaltyLog()
                .build();
        StrictMode.setThreadPolicy(policy);
        filePath = (TextView) findViewById(R.id.file_path);
        Browse = (Button) findViewById(R.id.browse);
        Browse.setOnClickListener(this);

        tv_cameraview=(TextView)findViewById(R.id.cameraview);
        bt_camerabutton=(ImageButton)findViewById(R.id.camerabutton);
        bt_camerabutton.setOnClickListener(this);



        et_yourName = (EditText) findViewById(R.id.et_Name);
        et_yourEmail = (EditText) findViewById(R.id.et_Email);
        et_yourPhone = (EditText) findViewById(R.id.et_Phone);
        et_LastOfSubmission = (EditText) findViewById(R.id.et_Lastdateofsubmission);
        et_Expectedquoteinsud = (EditText) findViewById(R.id.et_Expectedquoteinusd);
        et_textarea = (EditText) findViewById(R.id.et_Textarea);
        // userlocation=(EditText) findViewById(R.id.userlocation);


        spnr_country = (Spinner) findViewById(R.id.spnr_Country);
        spnr_subject = (Spinner) findViewById(R.id.spnr_Subject);
        spnr_grade = (Spinner) findViewById(R.id.spnr_Grade);
        spnr_reference = (Spinner) findViewById(R.id.spnr_Reference);

        submitButton = (Button) findViewById(R.id.btn_Submit);
        submitButton.setOnClickListener(this);

        spnr_country.setOnItemSelectedListener(this);

        ArrayAdapter<String> aa = new ArrayAdapter(this, R.layout.simple_spinner_item, listSpinner1);
        // aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnr_country.setAdapter(aa);

        spnr_subject.setOnItemSelectedListener(this);

        ArrayAdapter aa1 = new ArrayAdapter(this, R.layout.simple_spinner_item, listSpinner2);
        // aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnr_subject.setAdapter(aa1);

        spnr_grade.setOnItemSelectedListener(this);

        ArrayAdapter aa2 = new ArrayAdapter(this, R.layout.simple_spinner_item, listSpinner3);
        //aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnr_grade.setAdapter(aa2);

        spnr_reference.setOnItemSelectedListener(this);

        ArrayAdapter aa3 = new ArrayAdapter(this, R.layout.simple_spinner_item, listSpinner4);
        //aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnr_reference.setAdapter(aa3);

    /*
        Calendar c=Calendar.getInstance();
        SimpleDateFormat df=new SimpleDateFormat("dd-MM-yyyy , HH:mm:ss");
        String dateandtime=df.format(c.getTime());
        Toast.makeText(this, dateandtime,Toast.LENGTH_SHORT).show();
  */
    }




    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

       /* Toast.makeText(getApplicationContext(), listSpinner1[position],Toast.LENGTH_LONG).show();
        Toast.makeText(getApplicationContext(), listSpinner2[position],Toast.LENGTH_LONG).show();
        Toast.makeText(getApplicationContext(), listSpinner3[position],Toast.LENGTH_LONG).show();
        Toast.makeText(getApplicationContext(), listSpinner4[position],Toast.LENGTH_LONG).show();
*/
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }


    public void get_location(){

        GPSTracker gpsTracker = new GPSTracker(this);

        if (gpsTracker.getIsGPSTrackingEnabled()) {

            String stringLatitude = String.valueOf(gpsTracker.latitude);

            System.out.println("Latitude is here : " + stringLatitude);

            txtLat = (EditText) findViewById(R.id.userlocation);
            txtLat.setText(String.valueOf(stringLatitude));


            String stringLongitude = String.valueOf(gpsTracker.longitude);

            System.out.println("Longitude is here : " + stringLongitude);

            txtLong = (EditText) findViewById(R.id.address);
            txtLong.setText(String.valueOf(stringLongitude));


        }
        }

    /*

    =================================================
    private void get_location()
    {



        Toast.makeText(getApplicationContext(), "provider " + provider, Toast.LENGTH_LONG).show();

        Location targetLocation = new Location(provider);//provider name is unecessary

        latitude=targetLocation.getLatitude();
        longitude=targetLocation.getLongitude();

        Log.d("Location is find .....", latitude + ":" + longitude);

        System.out.println("Location is coming stil i m getting plz wait...."+(latitude + ":" + longitude));

        txtLat.setText(String.valueOf(latitude) + "" + String.valueOf(longitude));


        Toast.makeText(getApplicationContext(), "Your Location is - \nLat: " + latitude + "\nLong: " + longitude, Toast.LENGTH_LONG).show();

    }
    ==============================================

    */


    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.browse:
                browse_image();

            /*
                Intent intent = new Intent(this, FilePicker.class);
                startActivityForResult(intent, REQUEST_PICK_FILE);

            */
                break;

            case R.id.camerabutton:
                show_camera();
                break;

            case R.id.btn_Submit:
              // get_location();


               // show_location();

                check();



    /*
        boolean check = check();
                if(check == true){
                    send la=new send();
                    la.execute();
                }

*/

                break;


        }
    }

    private void browse_image() {
/*

        Intent intent = new Intent(this, FilePicker.class);
        startActivityForResult(intent, REQUEST_PICK_FILE);
*/

        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), REQUEST_PICK_FILE);

    }

    private void show_camera() {

        Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(cameraIntent, PICK_IMAGE_REQUEST);


        if(isReadStorageAllowed()){
            //If permission is already having then showing the toast
            Toast.makeText(MainActivity.this,"You already have the permission",Toast.LENGTH_LONG).show();
            //Existing the method with return
            return;
        }

        //If the app has not the permission then asking for the permission
        requestStoragePermission();

/*
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (ContextCompat.checkSelfPermission(getApplicationContext(),
                    Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED) {

                Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(cameraIntent, PICK_IMAGE_REQUEST);
                  }
        } else {

            Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            startActivityForResult(cameraIntent, PICK_IMAGE_REQUEST);

        }

*/


/*

        Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(cameraIntent, PICK_IMAGE_REQUEST);
*/

    }


    //We are calling this method to check the permission status
    private boolean isReadStorageAllowed() {
        //Getting the permission status
        int result = ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA);

        //If permission is granted returning true
        if (result == PackageManager.PERMISSION_GRANTED)
            return true;

        //If permission is not granted returning false
        return false;
    }



    //Requesting permission
    private void requestStoragePermission(){

        if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.CAMERA)){
            //If the user has denied the permission previously your code will come to this block
            //Here you can explain why you need this permission
            //Explain here why you need this permission

            }

        //And finally ask for the permission
        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA}, PICK_IMAGE_REQUEST);
    }

    //This method will be called when the user will tap on allow or deny
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        //Checking the request code of our request
        if(requestCode == PICK_IMAGE_REQUEST){

            //If permission is granted
            if(grantResults.length >0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){

                //Displaying a toast
                Toast.makeText(this,"Permission granted now you can read the storage",Toast.LENGTH_LONG).show();
            }else{
                //Displaying another toast if permission is not granted
                Toast.makeText(this,"Oops you just denied the permission",Toast.LENGTH_LONG).show();
            }
        }
    }






    private void check() {

        name = et_yourName.getText().toString().trim().toLowerCase();
        email = et_yourEmail.getText().toString().trim().toLowerCase();
        phone = et_yourPhone.getText().toString().trim().toLowerCase();
        lastdateofsubmission = et_LastOfSubmission.getText().toString().trim().toLowerCase();
        textarea = et_textarea.getText().toString().trim().toLowerCase();
        paexpect = et_Expectedquoteinsud.getText().toString().trim().toLowerCase();
        location = both;
        camera=imagepath;


        //Log.d("Location is coming ", location);
        System.out.println("I am fetching the Location from the server plzz wait for some time :" + location);

        //Toast.makeText(getApplicationContext(), "I am fetching the location from the server :" + location, Toast.LENGTH_LONG).show();

        country = spnr_country.getSelectedItem().toString().trim().toLowerCase();
        subject = spnr_subject.getSelectedItem().toString().trim().toLowerCase();
        pagrade = spnr_grade.getSelectedItem().toString().trim().toLowerCase();
        paexpect = spnr_reference.getSelectedItem().toString().trim().toLowerCase();



        if (name.length() == 0) {
            Toast.makeText(MainActivity.this, "Please Enter Your Name", Toast.LENGTH_SHORT).show();
        } else if (phone.length() == 0) {
            Toast.makeText(MainActivity.this, "Please Enter Your Mobile No.", Toast.LENGTH_SHORT).show();
        } else if (email.length() == 0) {
            Toast.makeText(MainActivity.this, "Please Enter a Your Email ID", Toast.LENGTH_SHORT).show();
        } else if (!Util.emailValidater(email)) {
            Toast.makeText(MainActivity.this, "Please Enter a Valid Email", Toast.LENGTH_SHORT).show();
        } else if (lastdateofsubmission.length() == 0) {
            Toast.makeText(MainActivity.this, "Please Enter Your lastdateofsubmission", Toast.LENGTH_SHORT).show();
        } else if (textarea.length() == 0) {
            Toast.makeText(MainActivity.this, "Please Enter Your Query", Toast.LENGTH_SHORT).show();
        } else if (paexpect.length() == 0) {
            Toast.makeText(MainActivity.this, "Please Enter Your Expected Quote in USD", Toast.LENGTH_SHORT).show();
        }

            else if (location == null) {
                show_location();

/*
                if (location != null) {

                    show_location();
                }
*/

            }


        else {

            send la = new send();
            la.execute();
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (resultCode == RESULT_OK) {

            switch (requestCode) {

                case REQUEST_PICK_FILE:

                    if (requestCode == REQUEST_PICK_FILE) {
                        Uri selectedImageUri = data.getData();
                        path = getPath(selectedImageUri);
                        System.out.println("Image Path : " + path);
                        filePath.setText(path);

/*

                        Uri uri= data.getData();
                        path=String.valueOf(uri);
                        Log.d("image path is coming ",path);
                        filePath.setText(path);
*/

                    }

/*
                   if (data.hasExtra(FilePicker.EXTRA_FILE_PATH)) {

                        selectedFile = new File
                                (data.getStringExtra(FilePicker.EXTRA_FILE_PATH));
                        filePath.setText(selectedFile.getPath());
                        path = selectedFile.getPath();
                    }
  */                 break;

                case PICK_IMAGE_REQUEST:
                    String[] projection = {MediaStore.Images.Media.DATA};
                    Cursor cursor = managedQuery(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, projection, null, null, null);
                    int colunm_index_data = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
                    cursor.moveToNext();
                    //String imagepath = cursor.getString(colunm_index_data);
                    imagepath = cursor.getString(colunm_index_data);
                    // Bitmap bitmapImage= BitmapFactory.decodeFile(imagepath);
                    System.out.println("image path is coming form the sd card user can see............." + imagepath);

                    tv_cameraview.setText(imagepath);
                    break;
            }
        }



        /*if(data!=null) {


            String[] projection = {MediaStore.Images.Media.DATA};
            Cursor cursor = managedQuery(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, projection, null, null, null);
            int colunm_index_data = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
            cursor.moveToNext();
            String imagepath = cursor.getString(colunm_index_data);
            // Bitmap bitmapImage= BitmapFactory.decodeFile(imagepath);
            System.out.println("image path is coming form the sd card user can see............." + imagepath);

            tv_cameraview.setText(imagepath);

        }*/

    }

    public String getPath(Uri uri) {
        String[] projection = { MediaStore.Images.Media.DATA };
        Cursor cursor = managedQuery(uri, projection, null, null, null);
        int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
        cursor.moveToFirst();
        return cursor.getString(column_index);
    }

    public String getStringImage(Bitmap bmp){
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bmp.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        byte[] imageBytes = baos.toByteArray();
        String encodedImage = Base64.encodeToString(imageBytes, Base64.DEFAULT);
        return encodedImage;
    }

    public String post_data() {

        String result = null;
        String charset = "UTF-8";
        File uploadFile1 = new File(path);
        File cameraFile2=new File(camera);
        //File uploadFile2 = new File("e:/Test/PIC2.JPG");
        // String requestURL = "http://localhost:8080/FileUploadSpringMVC/uploadFile.do";
        String requestURL = "http://www.kickassassignmenthelp.com/wp-content/themes/assignment/assignment-save.php";
        try {
            MultipartUtility multipart = new MultipartUtility(requestURL, charset);

            multipart.addHeaderField("User-Agent", "CodeJava");
            multipart.addHeaderField("Test-Header", "Header-Value");

            multipart.addFormField("name", name);
            multipart.addFormField("email", email);
            multipart.addFormField("phone", phone);

            multipart.addFormField("country", country);
            multipart.addFormField("subject", subject);
            multipart.addFormField("pagrade", pagrade);
            multipart.addFormField("reference", reference);
            multipart.addFormField("lastdateofsubmission", lastdateofsubmission);
            multipart.addFormField("textarea", textarea);
            multipart.addFormField("paexpect", paexpect);
            multipart.addFilePart("browse", uploadFile1);
            multipart.addFormField("location", location);
            multipart.addFilePart("camera", cameraFile2);


            // multipart.addFilePart("fileUpload", uploadFile2);

            List<String> response = multipart.finish();

            System.out.println("-------------SERVER REPLIED:-----------" + response);

            for (String line : response) {
                System.out.println(line);
                result = line;
            }


        } catch (IOException ex) {
            System.err.println("-----------error" + ex);
        }

        return result;
    }
private void show_location()
{
    //check();
    // create class object
    gps = new GpsTracker1(MainActivity.this);

    // check if GPS enabled
    if(gps.canGetLocation()){

        double latitude = gps.getLatitude();
        double longitude = gps.getLongitude();

        String lat=String.valueOf(latitude);
        String longi=String.valueOf(longitude);

        both=lat+" "+longi;

        System.out.println("Location is coming Dev can Check now Current Location of User : "+  both);
        // \n is for new line

       // Toast.makeText(getApplicationContext(), "Your Current Location is - \nLat: " + latitude + "\nLong: " + longitude, Toast.LENGTH_LONG).show();

    }else{
        // can't get location
        // GPS or Network is not enabled
        // Ask user to enable GPS/network in settings
        gps.showSettingsAlert();
    }
}
    class send extends AsyncTask<String, Void, String> {

        // private Dialog loadingDialog;
        private ProgressDialog loading;


        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            loading = new ProgressDialog(MainActivity.this);
            loading.setMessage("Please Wait...");
            loading.show();

        }

        @Override
        protected String doInBackground(String... params) {

            String result = post_data();
            return result;
        }

        @Override
        protected void onPostExecute(String result) {

            if (result == null) {
                loading.dismiss();
            } else {
                loading.dismiss();
                //System.out.println("------------result REPLIED:-----------" + result);
                Log.e("asdasdasd" , result);
                Toast.makeText(getApplicationContext(), result, Toast.LENGTH_LONG).show();

            }


            et_yourName.setText("");
            et_yourPhone.setText("");
            et_yourEmail.setText("");
            et_LastOfSubmission.setText("");
            et_Expectedquoteinsud.setText("");
            et_textarea.setText("");

            spnr_country.setSelection(0);
            spnr_reference.setSelection(0);
            spnr_grade.setSelection(0);
            spnr_subject.setSelection(0);

            filePath.setText("No file Selected");
            tv_cameraview.setText("Take a snapshot");


        }
    }

}
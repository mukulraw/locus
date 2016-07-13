package com.app.locus.assignment;

import java.io.ByteArrayOutputStream;

import java.io.File;
import java.io.IOException;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.ContentUris;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;

import android.graphics.BitmapFactory;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;

import android.os.Environment;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.apache.commons.lang3.event.EventListenerSupport;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;


@SuppressWarnings("ALL")
public class Assignment extends Activity implements View.OnClickListener, LocationListener {


    private EditText _name;
    private EditText _email;
    private EditText _phone;
    private Button _last_date;
    private EditText _text_area;
    private EditText _expected;
    private Spinner _country;
    private Spinner _subject;
    private Spinner _grade;
    private Spinner _reference;
    private Button camera;
    private TextView browse_image;
    private TextView camera_image;
    private Button submit;
    private Button browser;
    private String mCurrentPhotoPath;


    String POST_URL = "http://www.kickassassignmenthelp.com/wp-content/themes/assignment/assignment-save.php";


    ProgressBar bar;

    private final String[] listSpinner1 = {"--Select country--","Afghanistan", "Akrotiri", "Albania", "Algeria",
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


    private final String[] listSpinner2 = {"--Select subject--","Accounts", "Animation", "Archaeology", "Architecture", "Article Biology",
            "Biotechnology", "Business Communication", "Business Ethics", "Business Laws", "Case Study", "Chemical engineering", "Chemistry Civil engineering",
            "Computer Programming", "Computer science", "Computer-aided design", "Corporate Governance", "Database Dissertation",
            "E-Commerce", "Economics Electrical engineering", "Engineering", "English", "ERP", "Essay Writing", "Finance Geography Geology History",
            "HRM", "Information Technology", "International Business", "literature Management", "Marketing", "Mathematics", "Matlab",
            "Mechanical engineering", "MIS", "Operations Management", "Organisation Behaviour", "Physics", "Physiology", "Political Science",
            "Presentation", "Project Management", "Psychology", "Quantitative Techniques", "Research Methodology", "Science", "Security Analysis and Portfolio Management",
            "Sociology", "Statistics", "Term Paper Writing", "Thesis Writing", "Others"};

    private final String[] listSpinner3 = {"--Select grade--","School Level", "K-12 Level", "High School Level",
            "College Level", "Post Graduate Level", "Engineering Level", "Research Level"};

    private final String[] listSpinner4 = {"--Select reference--","Harvard", "APA", "MLA", "Chicago", "Footnotes",
            "Footnotes and bibliography", "Vancouver", "Turabian"};


    private assignment_bean bean;

    private final int PICK_IMAGE_REQUEST = 1;
    private final int CAPTURE_IMAGE_REQUEST = 2;


    String CapturedImageURL;

    private Bitmap bitmap;

    Bitmap photo;

    private String strAddress;
    Uri uri;


    private Location current;


    String bo, cm;

    File ph;


    private String path;
    String path3;

    Intent mintent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assignment2);

        bean = new assignment_bean();

        _name = (EditText) findViewById(R.id.nameid);
        _email = (EditText) findViewById(R.id.mailid);
        _phone = (EditText) findViewById(R.id.phoneid);


        _country = (Spinner) findViewById(R.id.spincountry);
        _subject = (Spinner) findViewById(R.id.spinsubject);
        _grade = (Spinner) findViewById(R.id.spingrade);
        _reference = (Spinner) findViewById(R.id.spinreference);

        _last_date = (Button) findViewById(R.id.last_date_id);
        _text_area = (EditText) findViewById(R.id.text_area_id);

        browse_image = (TextView) findViewById(R.id.browse_image_id);

        browser = (Button) findViewById(R.id.browseid);

        camera_image = (TextView) findViewById(R.id.cameraviewid);

        camera = (Button) findViewById(R.id.camerabuttonid);

        _expected = (EditText) findViewById(R.id.expectedid);

        bar = (ProgressBar)findViewById(R.id.progress_upload);



        submit = (Button) findViewById(R.id.submitid);

        submit.setOnClickListener(this);
        browser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent();

                intent.setAction(Intent.ACTION_GET_CONTENT);
                intent.setType("*/*");
                startActivityForResult(Intent.createChooser(intent, "Select File"), PICK_IMAGE_REQUEST);

            }
        });
        camera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
                    int hasLocationPermission = checkSelfPermission(Manifest.permission.CAMERA);
                    if(hasLocationPermission!=PackageManager.PERMISSION_GRANTED)
                    {
                        requestPermissions(new String[]{Manifest.permission.CAMERA} , 111);
                    }
                    else
                    {
                        CaptureImage();
                    }

                }
                else {
                    CaptureImage();
                }







            }
        });

        _last_date.setOnClickListener(this);



        int hasLocationPermission = checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION);
        if(hasLocationPermission == PackageManager.PERMISSION_GRANTED)
        {
            LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
            if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                // TODO: Consider calling
                //    ActivityCompat#requestPermissions
                // here to request the missing permissions, and then overriding
                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                //                                          int[] grantResults)
                // to handle the case where the user grants the permission. See the documentation
                // for ActivityCompat#requestPermissions for more details.
                return;
            }
            locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0, this);
        }




        _country.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position>0)
                {
                    bean.setCountry(listSpinner1[position]);
                }
                else
                {
                    bean.setCountry("---");
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        ArrayAdapter<String> adapter1 = new ArrayAdapter<>(this, R.layout.simple_spinner_item, listSpinner1);
        _country.setAdapter(adapter1);

   //     subject.setOnItemClickListener(this);

        _subject.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                bean.setSubject(listSpinner2[position]);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        ArrayAdapter<String> adapter2 = new ArrayAdapter<>(this, R.layout.simple_spinner_item, listSpinner2);
        _subject.setAdapter(adapter2);

  //      grade.setOnItemClickListener(this);

        _grade.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                bean.setGrade(listSpinner3[position]);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        ArrayAdapter<String> adapter3 = new ArrayAdapter<>(this, R.layout.simple_spinner_item, listSpinner3);
        _grade.setAdapter(adapter3);

  //      reference.setOnItemClickListener(this);

        _reference.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                bean.setReference(listSpinner4[position]);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        ArrayAdapter<String> adapter4 = new ArrayAdapter<>(this, R.layout.simple_spinner_item, listSpinner4);
        _reference.setAdapter(adapter4);










    }


    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == 111)
        {
            if(ActivityCompat.checkSelfPermission(this, Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED){
                CaptureImage();
            }
            else
            {
                if (ActivityCompat.shouldShowRequestPermissionRationale(this , Manifest.permission.CAMERA) ) {

                    Toast.makeText(getApplicationContext() , "Camera permission is required to access Camera" , Toast.LENGTH_SHORT).show();

                }
                else {
                    Toast.makeText(this, "Go to settings and enable permissions", Toast.LENGTH_LONG)
                            .show();
                }
            }
        }

    }

    private void CaptureImage()
    {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        // Ensure that there's a camera activity to handle the intent
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            // Create the File where the photo should go
            File photoFile = null;
            try {
                photoFile = createImageFile();
            } catch (IOException ex) {
                // Error occurred while creating the File

            }
            // Continue only if the File was successfully created
            if (photoFile != null) {
                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT,
                        Uri.fromFile(photoFile));
                startActivityForResult(takePictureIntent, CAPTURE_IMAGE_REQUEST);
            }
        }
    }

    private static boolean hasPermissions(Context context, String... permissions) {
        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && context != null && permissions != null) {
            for (String permission : permissions) {
                if (ActivityCompat.checkSelfPermission(context, permission) != PackageManager.PERMISSION_GRANTED) {
                    return false;
                }
            }
        }
        return true;
    }

    @Override
    public void onClick(View v) {





        if (v == _last_date)
        {

            final Dialog dialog = new Dialog(this);
            dialog.setContentView(R.layout.date_dialog);
            final DatePicker datePicker = (DatePicker)dialog.findViewById(R.id.pick_date);
            dialog.setCancelable(false);
            dialog.show();

            Button ok = (Button)dialog.findViewById(R.id.select);

            ok.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    bean.setLast_date(String.valueOf(datePicker.getDayOfMonth() + "/" + String.valueOf(datePicker.getMonth() + 1) + "/" + datePicker.getYear()));
                    dialog.dismiss();
                }
            });



        }



            if (v == submit) {

                if (_name.getText().length()>0)
                {
                    bean.setName(String.valueOf(_name.getText()));
                }
                else
                {
                    Toast.makeText(getBaseContext() , "Please enter your name" , Toast.LENGTH_SHORT).show();
                    return;
                }

                if (_email.getText().length()>0)
                {
                    bean.setEmail(String.valueOf(_email.getText()));
                }
                else
                {
                    Toast.makeText(getBaseContext() , "Please enter your Email Id" , Toast.LENGTH_SHORT).show();
                    return;
                }


                if (_phone.getText().toString().length()>0)
                {
                    if (isValidMobile(_phone.getText().toString()))
                    {
                        bean.setPhone(String.valueOf(_phone.getText()));
                    }
                    else
                    {
                        Toast.makeText(this , "Invalid Phone Number" , Toast.LENGTH_SHORT).show();
                    }
                }






                    //bean.setLast_date(String.valueOf(_last_date.getDayOfMonth() + "/" + _last_date.getMonth() + "/" + _last_date.getYear()));




                    bean.setArea_text(String.valueOf(_text_area.getText()));



                    bean.setExpected(String.valueOf(_expected.getText()));






                        new upload(bean).execute();






            }

        }




    private File createImageFile() throws IOException {
        // Create an image file name
        @SuppressLint("SimpleDateFormat") String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        File storageDir = Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_DCIM);
        File image = File.createTempFile(
                imageFileName,  /* prefix */
                ".jpg",         /* suffix */
                storageDir      /* directory */
        );

        // Save a file: path for use with ACTION_VIEW intents
        mCurrentPhotoPath =  image.getAbsolutePath();
        Log.d("asdasdasdasdasdad" , mCurrentPhotoPath);
        camera_image.setText(mCurrentPhotoPath);
        return image;
    }
    private boolean isValidMobile(String phone)
    {
        return android.util.Patterns.PHONE.matcher(phone).matches();
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {

            try {
                bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), Uri.parse(String.valueOf(data.getData())));
                browse_image.setVisibility(View.VISIBLE);
                //browse_image.setImageBitmap(bitmap);
                Uri selectedImageUri = data.getData();

                //bean.setBrowse(bitmap);



                path = getPath(getApplicationContext() , selectedImageUri);

                browse_image.setText(path);

                //path = selectedImageUri.getPath();

                //path = String.valueOf(selectedImageUri);
                Log.d("asdasdasd" , String.valueOf(selectedImageUri));
                Log.d("asdasdasd" , path);

                //Log.d("asdasdasd" , path);
                //ph = new File(path);


                //uri = getImageUri(getApplicationContext() , bitmap);


                //String bit = getStringImage(bitmap);
                //bean.setBrowse(bit);
                //bo = bit;
                //Log.d("asdasdasd" , bit);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


        if (requestCode == CAPTURE_IMAGE_REQUEST && resultCode == RESULT_OK) {













        }
    }



    public static String convertImageUriToFile ( Uri imageUri, Activity activity )  {

        Cursor cursor = null;
        int imageID = 0;

        try {

            /*********** Which columns values want to get *******/
            String [] proj={
                    MediaStore.Images.Media.DATA,
                    MediaStore.Images.Media._ID,
                    MediaStore.Images.Thumbnails._ID,
                    MediaStore.Images.ImageColumns.ORIENTATION
            };

            //noinspection deprecation
            cursor = activity.managedQuery(

                    imageUri,         //  Get data for specific image URI
                    proj,             //  Which columns to return
                    null,             //  WHERE clause; which rows to return (all rows)
                    null,             //  WHERE clause selection arguments (none)
                    null              //  Order-by clause (ascending by name)

            );

            //  Get Query Data

            int columnIndex = cursor.getColumnIndexOrThrow(MediaStore.Images.Media._ID);
            int columnIndexThumb = cursor.getColumnIndexOrThrow(MediaStore.Images.Thumbnails._ID);
            int file_ColumnIndex = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);

            //int orientation_ColumnIndex = cursor.
            //    getColumnIndexOrThrow(MediaStore.Images.ImageColumns.ORIENTATION);

            int size = cursor.getCount();

            /*******  If size is 0, there are no images on the SD Card. *****/

            //noinspection StatementWithEmptyBody,StatementWithEmptyBody
            if (size == 0) {


                //imageDetails.setText("No Image");
            }
            else
            {

                int thumbID;
                if (cursor.moveToFirst()) {

                    /**************** Captured image details ************/

                    /*****  Used to show image on view in LoadImagesFromSDCard class ******/
                    imageID     = cursor.getInt(columnIndex);

                    thumbID     = cursor.getInt(columnIndexThumb);

                    String Path = cursor.getString(file_ColumnIndex);

                    //String orientation =  cursor.getString(orientation_ColumnIndex);

                    String CapturedImageDetails = " CapturedImageDetails : \n\n"
                            +" ImageID :"+imageID+"\n"
                            +" ThumbID :"+thumbID+"\n"
                            +" Path :"+Path+"\n";

                    // Show Captured Image detail on activity
                    //imageDetails.setText( CapturedImageDetails );

                }
            }
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }

        // Return Captured Image ImageID ( By this ImageID Image will load from sdcard )

        return ""+imageID;
    }


    public class LoadImagesFromSDCard  extends AsyncTask<String, Void, Void> {

        private final ProgressDialog Dialog = new ProgressDialog(Assignment.this);

        Bitmap mBitmap;

        protected void onPreExecute() {
            /****** NOTE: You can call UI Element here. *****/

            // Progress Dialog
            Dialog.setMessage(" Loading image from Sdcard..");
            Dialog.show();
        }


        // Call after onPreExecute method
        protected Void doInBackground(String... urls) {

            Bitmap bitmap = null;
            Bitmap newBitmap = null;
            Uri uri = null;


            try {



                uri = Uri.withAppendedPath(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "" + urls[0]);

                /**************  Decode an input stream into a bitmap. *********/
                bitmap = BitmapFactory.decodeStream(getContentResolver().openInputStream(uri));

                if (bitmap != null) {

                    /********* Creates a new bitmap, scaled from an existing bitmap. ***********/

                    newBitmap = Bitmap.createScaledBitmap(bitmap, 170, 170, true);

                    bitmap.recycle();

                    if (newBitmap != null) {

                        mBitmap = newBitmap;
                    }
                }
            } catch (IOException e) {
                // Error fetching image, try to recover

                /********* Cancel execution of this task. **********/
                cancel(true);
            }

            return null;
        }


        protected void onPostExecute(Void unused) {

            // NOTE: You can call UI Element here.

            // Close progress dialog
            Dialog.dismiss();

            //noinspection StatementWithEmptyBody
            if(mBitmap != null)
            {
                // Set Image to ImageView

                //showImg.setImageBitmap(mBitmap);
            }

        }

    }



    private static String getPath(final Context context, final Uri uri)
    {
        final boolean isKitKatOrAbove = Build.VERSION.SDK_INT >=  Build.VERSION_CODES.KITKAT;

        // DocumentProvider
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            if (isKitKatOrAbove && DocumentsContract.isDocumentUri(context, uri)) {
                // ExternalStorageProvider
                if (isExternalStorageDocument(uri)) {
                    final String docId = DocumentsContract.getDocumentId(uri);
                    final String[] split = docId.split(":");
                    final String type = split[0];

                    if ("primary".equalsIgnoreCase(type)) {
                        return Environment.getExternalStorageDirectory() + "/" + split[1];
                    }

                    // TODO handle non-primary volumes
                }
                // DownloadsProvider
                else if (isDownloadsDocument(uri)) {

                    final String id = DocumentsContract.getDocumentId(uri);
                    final Uri contentUri = ContentUris.withAppendedId(
                            Uri.parse("content://downloads/public_downloads"), Long.valueOf(id));

                    return getDataColumn(context, contentUri, null, null);
                }
                // MediaProvider
                else if (isMediaDocument(uri)) {
                    final String docId = DocumentsContract.getDocumentId(uri);
                    final String[] split = docId.split(":");
                    final String type = split[0];

                    Uri contentUri = null;
                    if ("image".equals(type)) {
                        contentUri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
                    } else if ("video".equals(type)) {
                        contentUri = MediaStore.Video.Media.EXTERNAL_CONTENT_URI;
                    } else if ("audio".equals(type)) {
                        contentUri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
                    }

                    final String selection = "_id=?";
                    final String[] selectionArgs = new String[] {
                            split[1]
                    };

                    return getDataColumn(context, contentUri, selection, selectionArgs);
                }
            }
            // MediaStore (and general)
            else if ("content".equalsIgnoreCase(uri.getScheme())) {
                return getDataColumn(context, uri, null, null);
            }
            // File
            else if ("file".equalsIgnoreCase(uri.getScheme())) {
                return uri.getPath();
            }
        }

        return null;
    }

    private void setPic() {
        // Get the dimensions of the View
        int targetW = camera_image.getWidth();
        int targetH = camera_image.getHeight();

        // Get the dimensions of the bitmap
        BitmapFactory.Options bmOptions = new BitmapFactory.Options();
        bmOptions.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(mCurrentPhotoPath, bmOptions);
        int photoW = bmOptions.outWidth;
        int photoH = bmOptions.outHeight;

        // Determine how much to scale down the image
        int scaleFactor = Math.min(photoW/targetW, photoH/targetH);

        // Decode the image file into a Bitmap sized to fill the View
        bmOptions.inJustDecodeBounds = false;
        bmOptions.inSampleSize = scaleFactor;
        //noinspection deprecation
        bmOptions.inPurgeable = true;

        Bitmap bitmap = BitmapFactory.decodeFile(mCurrentPhotoPath, bmOptions);
        //camera_image.setImageBitmap(bitmap);
    }


    private static String getDataColumn(Context context, Uri uri, String selection,
                                        String[] selectionArgs) {

        Cursor cursor = null;
        final String column = "_data";
        final String[] projection = {
                column
        };

        try {
            cursor = context.getContentResolver().query(uri, projection, selection, selectionArgs,
                    null);
            if (cursor != null && cursor.moveToFirst()) {
                final int column_index = cursor.getColumnIndexOrThrow(column);
                return cursor.getString(column_index);
            }
        } finally {
            if (cursor != null)
                cursor.close();
        }
        return null;
    }


    /**
     * @param uri The Uri to check.
     * @return Whether the Uri authority is ExternalStorageProvider.
     */
    private static boolean isExternalStorageDocument(Uri uri) {
        return "com.android.externalstorage.documents".equals(uri.getAuthority());
    }

    /**
     * @param uri The Uri to check.
     * @return Whether the Uri authority is DownloadsProvider.
     */
    private static boolean isDownloadsDocument(Uri uri) {
        return "com.android.providers.downloads.documents".equals(uri.getAuthority());
    }

    /**
     * @param uri The Uri to check.
     * @return Whether the Uri authority is MediaProvider.
     */
    private static boolean isMediaDocument(Uri uri) {
        return "com.android.providers.media.documents".equals(uri.getAuthority());
    }



    public Uri getImageUri(Context inContext, Bitmap inImage) {
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        inImage.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
        String path = MediaStore.Images.Media.insertImage(inContext.getContentResolver(), inImage, "Title", null);
        return Uri.parse(path);
    }

    public String getRealPathFromURI(Uri contentUri)
    {
        try
        {
            String[] proj = {MediaStore.Images.Media.DATA};
            @SuppressWarnings("deprecation") Cursor cursor = managedQuery(contentUri, proj, null, null, null);
            int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
            cursor.moveToFirst();
            return cursor.getString(column_index);
        }
        catch (Exception e)
        {
            return contentUri.getPath();
        }
    }
    public String getStringImage(Bitmap bmp){
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bmp.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        byte[] imageBytes = baos.toByteArray();
        return Base64.encodeToString(imageBytes, Base64.DEFAULT);
    }



    public String getPathFromURI(Uri contentUri) {
        String res = null;
        String[] proj = {MediaStore.Images.Media.DATA};
        Cursor cursor = getContentResolver().query(contentUri, proj, null, null, null);
        assert cursor != null;
        if (cursor.moveToFirst()) {
            int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
            res = cursor.getString(column_index);
        }
        cursor.close();
        return res;
    }

    @Override
    public void onLocationChanged(Location location) {
        if (location!=null)
        {
            this.current = location;
        }

    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }


    private class upload extends AsyncTask<Void , Void , Void>
    {

        String result = "";

        final String name;
        final String email;
        final String phone;
        final String country;
        final String subject;
        final String grade;
        final String reference;
        final String last_date;
        final String area_text;
        final String expected;
        final String location;
        final String camera;
        final String browse;

        upload(assignment_bean b)
        {
            this.name = b.getName();
            this.email = b.getEmail();
            this.phone = b.getPhone();
            this.country = b.getCountry();
            this.subject = b.getSubject();
            this.grade = b.getGrade();
            this.reference = b.getReference();
            this.last_date = b.getLast_date();
            this.area_text = b.getArea_text();
            this.expected = b.getExpected();
            this.camera = b.getCamera();
            this.browse = b.getBrowse();
            this.location = b.getLocation();
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            submit.setVisibility(View.INVISIBLE);
            bar.setVisibility(View.VISIBLE);

        }

        @Override
        protected Void doInBackground(Void... params) {
            RegisterUserClass ruc = new RegisterUserClass();


            if (current!=null)
            {
                Geocoder g = new Geocoder(getApplicationContext());
                try {
                    List<Address> addresses = g.getFromLocation(current.getLatitude() , current.getLongitude() , 1);
                    Address address = addresses.get(0);
                    StringBuilder strAddres = new StringBuilder();

                    for(int i=0; i<address.getMaxAddressLineIndex(); i++) {
                        strAddres.append(address.getAddressLine(i)).append("\n");
                    }


                    strAddress = strAddres.toString();
                    Log.d("asdasdasd" , strAddress);





                } catch (IOException e) {
                    e.printStackTrace();
                }catch (NullPointerException e)
                {
                    e.printStackTrace();
                }
            }












            String requestURL = "http://www.kickassassignmenthelp.com/wp-content/themes/assignment/assignment-save.php";





            //File f2 = new File(mCurrentPhotoPath);

            try {
                MultipartUtility multipart = new MultipartUtility(requestURL);

                multipart.addHeaderField("User-Agent", "CodeJava");
                multipart.addHeaderField("Test-Header", "Header-Value");
                multipart.addFormField("name", name);
                multipart.addFormField("email", email);
                multipart.addFormField("phone", phone);
                multipart.addFormField("country", country);
                multipart.addFormField("subject", subject);
                multipart.addFormField("pagrade", grade);
                multipart.addFormField("reference", reference);
                multipart.addFormField("lastdateofsubmission", last_date);
                multipart.addFormField("textarea", area_text);
                multipart.addFormField("paexpect", expected);
                if (strAddress!=null)
                {
                    multipart.addFormField("location", strAddress);
                }
                else
                {
                    multipart.addFormField("location" , "null");
                }

                if (path!=null)
                {
                    File f = null;
                    f = new File(path);
                    multipart.addFilePart("browse", f);
                }
                else
                {
                    multipart.addFormField("browse" , null);
                }

                if (mCurrentPhotoPath!=null)
                {
                    File f2 = null;
                    f2 = new File(mCurrentPhotoPath);
                    multipart.addFilePart("camera", f2);

                }
                else {
                    multipart.addFormField("camera" , null);
                }


                // multipart.addFilePart("fileUpload", uploadFile2);

                List<String> response = multipart.finish();

                System.out.println("-------------SERVER REPLIED:-----------" + response);

                for (String line : response) {
                    System.out.println(line);
                    result = line;
                }
                //Log.d("asdasdasd" , result);


            } catch (IOException ex) {
                System.err.println("-----------error" + ex);
            }

            JSONObject jObj = null;

            try {
                jObj = new JSONObject(result);
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



            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            //Log.d("asdasdasd" , result);
           // Log.d("asdasdasd" , strAddress);


            if(result.equals("Your requirment submit successfully") )
            {
                Toast.makeText(getBaseContext() , "Successfully submitted" , Toast.LENGTH_SHORT).show();
                bar.setVisibility(View.INVISIBLE);
                _name.setText("");
                _email.setText("");
                _phone.setText("");
                _text_area.setText("");
                _expected.setText("");
                browse_image.setText("");
                camera_image.setText("");
                submit.setVisibility(View.VISIBLE);
                path = null;
                mCurrentPhotoPath = null;

            }





        }
    }



}

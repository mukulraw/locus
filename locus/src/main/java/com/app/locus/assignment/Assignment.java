package com.app.locus.assignment;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Objects;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;


public class Assignment extends Activity implements View.OnClickListener, LocationListener {


    EditText _name, _email, _phone, _last_date, _text_area, _expected;
    Spinner _country, _subject, _grade, _reference;
    ImageButton camera;
    ImageView browse_image, camera_image;
    Button submit, browser;


    String POST_URL = "http://www.kickassassignmenthelp.com/wp-content/themes/assignment/assignment-save.php";


    private String listSpinner1[] = {"Afghanistan", "Akrotiri", "Albania", "Algeria",
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


    private String listSpinner2[] = {"Accounts", "Animation", "Archaeology", "Architecture", "Article Biology",
            "Biotechnology", "Business Communication", "Business Ethics", "Business Laws", "Case Study", "Chemical engineering", "Chemistry Civil engineering",
            "Computer Programming", "Computer science", "Computer-aided design", "Corporate Governance", "Database Dissertation",
            "E-Commerce", "Economics Electrical engineering", "Engineering", "English", "ERP", "Essay Writing", "Finance Geography Geology History",
            "HRM", "Information Technology", "International Business", "literature Management", "Marketing", "Mathematics", "Matlab",
            "Mechanical engineering", "MIS", "Operations Management", "Organisation Behaviour", "Physics", "Physiology", "Political Science",
            "Presentation", "Project Management", "Psychology", "Quantitative Techniques", "Research Methodology", "Science", "Security Analysis and Portfolio Management",
            "Sociology", "Statistics", "Term Paper Writing", "Thesis Writing", "Others"};

    private String listSpinner3[] = {"School Level", "K-12 Level", "High School Level",
            "College Level", "Post Graduate Level", "Engineering Level", "Research Level"};

    private String listSpinner4[] = {"Harvard", "APA", "MLA", "Chicago", "Footnotes",
            "Footnotes and bibliography", "Vancouver", "Turabian"};


    assignment_bean bean;

    private int PICK_IMAGE_REQUEST = 1;
    private int CAPTURE_IMAGE_REQUEST = 2;

    Uri fileUri;

    public static final int MEDIA_TYPE_IMAGE = 1;
    public static final int MEDIA_TYPE_VIDEO = 2;
    Bitmap bitmap;


    String strAddress;

    Location current;


    String bo, cm;

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

        _last_date = (EditText) findViewById(R.id.last_date_id);
        _text_area = (EditText) findViewById(R.id.text_area_id);

        browse_image = (ImageView) findViewById(R.id.browse_image_id);

        browser = (Button) findViewById(R.id.browseid);

        camera_image = (ImageView) findViewById(R.id.cameraviewid);

        camera = (ImageButton) findViewById(R.id.camerabuttonid);

        _expected = (EditText) findViewById(R.id.expectedid);

        submit = (Button) findViewById(R.id.submitid);

        submit.setOnClickListener(this);
        browser.setOnClickListener(this);
        camera.setOnClickListener(this);


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


        _country.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                bean.setCountry(listSpinner1[position]);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(this , R.layout.simple_spinner_item , listSpinner1);
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
        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(this , R.layout.simple_spinner_item , listSpinner2);
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
        ArrayAdapter<String> adapter3 = new ArrayAdapter<String>(this , R.layout.simple_spinner_item , listSpinner3);
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
        ArrayAdapter<String> adapter4 = new ArrayAdapter<String>(this , R.layout.simple_spinner_item , listSpinner4);
        _reference.setAdapter(adapter4);










    }


    @Override
    public void onClick(View v) {



        if (v==browser) {
            Intent intent = new Intent();
            intent.setType("image/*");
            intent.setAction(Intent.ACTION_GET_CONTENT);
            startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST);

        }


        if (v==camera)
        {

         //   Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
          //  fileUri = getOutputMediaFileUri(MEDIA_TYPE_IMAGE);
          //  intent.putExtra(MediaStore.EXTRA_OUTPUT, fileUri);

           // startActivityForResult(intent , CAPTURE_IMAGE_REQUEST);



            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            //File photo = new File(Environment.getExternalStorageDirectory(),  "Pic.jpg");
           // intent.putExtra(MediaStore.EXTRA_OUTPUT,
           //         Uri.fromFile(photo));
           // fileUri = Uri.fromFile(photo);
            //fileUri = getOutputMediaFileUri(MEDIA_TYPE_IMAGE);

            //intent.putExtra(MediaStore.EXTRA_OUTPUT, fileUri);
            startActivityForResult(intent, CAPTURE_IMAGE_REQUEST);

        }


        if(v==submit)
        {

            bean.setName(String.valueOf(_name.getText()));
            bean.setEmail(String.valueOf(_email.getText()));
            bean.setPhone(String.valueOf(_phone.getText()));
            bean.setLast_date(String.valueOf(_last_date.getText()));
            bean.setArea_text(String.valueOf(_text_area.getText()));
            bean.setExpected(String.valueOf(_expected.getText()));
            bean.setLocation(String.valueOf(current.getLatitude()) + String.valueOf(current.getLongitude()));


            Log.d("asdasdasd" , String.valueOf(current.getLatitude()) + String.valueOf(current.getLongitude()));


            new upload(bean).execute();

        }


    }







    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {

            try {
                bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), Uri.parse(String.valueOf(data.getData())));
                browse_image.setVisibility(View.VISIBLE);
                browse_image.setImageBitmap(bitmap);
                //bean.setBrowse(bitmap);

                String bit = getStringImage(bitmap);
                bean.setBrowse(bit);
                bo = bit;
                Log.d("asdasdasd" , bit);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


        if (requestCode == CAPTURE_IMAGE_REQUEST && resultCode == RESULT_OK) {

            if (data != null) {








            //   photo = (Bitmap) data.getExtras().get("data");


             //   Uri tempUri = getImageUri(getApplicationContext(), photo);

            //    Log.d("asdasdasd" , getRealPathFromURI(tempUri));

              //  bean.setCamera(new File(getRealPathFromURI(tempUri)));


                Bitmap cam = (Bitmap) data.getExtras().get("data");

                String cams = getStringImage(cam);

                bean.setCamera(cams);

                cm = cams;
                Log.d("asdasdasd" , cams);

                camera_image.setVisibility(View.VISIBLE);
                camera_image.setImageBitmap((Bitmap) data.getExtras().get("data"));











                }
        }
    }


    public String getStringImage(Bitmap bmp){
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bmp.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        byte[] imageBytes = baos.toByteArray();
        return Base64.encodeToString(imageBytes, Base64.DEFAULT);
    }

    @Override
    public void onLocationChanged(Location location) {
        this.current = location;
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

        RegisterUserClass ruc = new RegisterUserClass();

        String result = "";

        String name , email , phone , country , subject , grade , reference , last_date , area_text , expected , location;
        String camera , browse;

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
        protected Void doInBackground(Void... params) {


            /*
            List<NameValuePair> data = new ArrayList<NameValuePair>();
            data.add(new BasicNameValuePair("name" , name));
            data.add(new BasicNameValuePair("email" , email));
            data.add(new BasicNameValuePair("phone" , phone));
            data.add(new BasicNameValuePair("country" , country));
            data.add(new BasicNameValuePair("subject" , subject));
            data.add(new BasicNameValuePair("pagrade" , grade));
            data.add(new BasicNameValuePair("refrence" , reference));
            data.add(new BasicNameValuePair("lastdateofsubmission" , last_date));
            data.add(new BasicNameValuePair("textarea" , area_text));
            data.add(new BasicNameValuePair("paexpect" , expected));
            data.add(new BasicNameValuePair("location" , "12"));
            data.add(new BasicNameValuePair("browse" , browse));
            data.add(new BasicNameValuePair("camera" , camera));

            result = ruc.sendPostRequest(POST_URL , data);
*/

            Geocoder g = new Geocoder(getApplicationContext());
            try {
                List<Address> addresses = g.getFromLocation(current.getLatitude() , current.getLongitude() , 5);
                Address address = addresses.get(0);
                StringBuffer str = new StringBuffer();
                str.append("Name: ").append(address).append("\n");
                str.append("Sub-Admin Areas: ").append(address.getSubAdminArea()).append("\n");
                str.append("Admin Area: ").append(address.getAdminArea()).append("\n");
                str.append("Country: ").append(address.getCountryName()).append("\n");
                str.append("Country Code: ").append(address.getCountryCode()).append("\n");
                strAddress = str.toString();
            } catch (IOException e) {
                e.printStackTrace();
            }


            HashMap<String , String> info = new HashMap<>();

            info.put("name" , name);
            info.put("email" , email);
            info.put("phone" , phone);
            info.put("country" , country);
            info.put("subject" , subject);
            info.put("pagrade" , grade);
            info.put("refrence" , reference);
            info.put("lastdateofsubmission" , last_date);
            info.put("textarea" , area_text);
            info.put("paexpect" , expected);
            info.put("location" , strAddress);
            info.put("browse" , bo);
            info.put("camera" , cm);


            RequestHandler handler = new RequestHandler();
            result = handler.sendPostRequest(POST_URL , info);





     /*       String requestURL = "http://www.kickassassignmenthelp.com/wp-content/themes/assignment/assignment-save.php";

            try {
                MultipartUtility multipart = new MultipartUtility(requestURL, "UTF-8");

                //multipart.addHeaderField("User-Agent", "CodeJava");
               // multipart.addHeaderField("Test-Header", "Header-Value");

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
                multipart.addFilePart("browse", browse);
                multipart.addFormField("location", "13");
                multipart.addFilePart("camera", camera);


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

*/



            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            Log.d("asdasdasd" , result);
            Log.d("asdasdasd" , strAddress);
            Toast.makeText(getBaseContext() , result , Toast.LENGTH_SHORT).show();

            if(result.equals("Your requirment submit successfully"))
            {
                _name.setText("");
                _email.setText("");
                _phone.setText("");
                _last_date.setText("");
                _text_area.setText("");
                _expected.setText("");
                browse_image.setImageBitmap(null);
                camera_image.setImageBitmap(null);

            }





        }
    }



}

package com.app.locus.assignment;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.MediaStore;
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

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Assignment extends Activity implements View.OnClickListener{


    EditText name , email , phone , last_date , text_area , expected;
    Spinner country , subject , grade , reference;
    ImageButton camera;
    ImageView browse_image , camera_image;
    Button submit , browser;

    private Bitmap photo;

    String POST_URL = "http://www.kickassassignmenthelp.com/wp-content/themes/assignment/assignment-save.php";

    String path;

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


    Bitmap bitmap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assignment2);

        bean = new assignment_bean();

        name = (EditText)findViewById(R.id.nameid);
        email = (EditText)findViewById(R.id.mailid);
        phone = (EditText)findViewById(R.id.phoneid);


        country = (Spinner)findViewById(R.id.spincountry);
        subject = (Spinner)findViewById(R.id.spinsubject);
        grade = (Spinner)findViewById(R.id.spingrade);
        reference = (Spinner)findViewById(R.id.spinreference);

        last_date = (EditText)findViewById(R.id.last_date_id);
        text_area = (EditText)findViewById(R.id.text_area_id);

        browse_image = (ImageView)findViewById(R.id.browse_image_id);

        browser = (Button)findViewById(R.id.browseid);

        camera_image = (ImageView)findViewById(R.id.cameraviewid);

        camera = (ImageButton)findViewById(R.id.camerabuttonid);

        expected = (EditText)findViewById(R.id.expectedid);

        submit = (Button)findViewById(R.id.submitid);

        submit.setOnClickListener(this);
        browser.setOnClickListener(this);
        camera.setOnClickListener(this);


        country.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                bean.setCountry(listSpinner1[position]);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(this , R.layout.simple_spinner_item , listSpinner1);
        country.setAdapter(adapter1);

   //     subject.setOnItemClickListener(this);

        subject.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                bean.setSubject(listSpinner2[position]);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(this , R.layout.simple_spinner_item , listSpinner2);
        subject.setAdapter(adapter2);

  //      grade.setOnItemClickListener(this);

        grade.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                bean.setGrade(listSpinner3[position]);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        ArrayAdapter<String> adapter3 = new ArrayAdapter<String>(this , R.layout.simple_spinner_item , listSpinner3);
        grade.setAdapter(adapter3);

  //      reference.setOnItemClickListener(this);

        reference.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                bean.setReference(listSpinner4[position]);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        ArrayAdapter<String> adapter4 = new ArrayAdapter<String>(this , R.layout.simple_spinner_item , listSpinner4);
        reference.setAdapter(adapter4);










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

            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            startActivityForResult(intent , CAPTURE_IMAGE_REQUEST);


        }


        if(v==submit)
        {

            bean.setName(String.valueOf(name.getText()));
            bean.setEmail(String.valueOf(email.getText()));
            bean.setPhone(String.valueOf(phone.getText()));
            bean.setLast_date(String.valueOf(last_date.getText()));
            bean.setArea_text(String.valueOf(text_area.getText()));
            bean.setExpected(String.valueOf(expected.getText()));


            new upload(bean).execute();

        }


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {

            try {
                bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), Uri.parse(String.valueOf(data.getData())));
                browse_image.setImageBitmap(bitmap);
                path = String.valueOf(data.getData());
                bean.setBrowse(bitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


        if (requestCode == CAPTURE_IMAGE_REQUEST && resultCode == RESULT_OK) {

            if (data != null) {

       /*         String[] projection = {MediaStore.Images.Media.DATA};
                Cursor cursor = managedQuery(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, projection, null, null, null);
                int colunm_index_data = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
                cursor.moveToNext();
                //String imagepath = cursor.getString(colunm_index_data);

                String imagepath = cursor.getString(colunm_index_data);
                bean.setCamera(imagepath);
                Bitmap bitmapImage = BitmapFactory.decodeFile(imagepath );
                Log.d("asdasdasd" , imagepath);
*/

                //camera_image.setImageBitmap(bitmapImage );


                photo = (Bitmap) data.getExtras().get("data");
                Log.d("asdasdasd" , String.valueOf(data.getDataString()));
                bean.setCamera(bitmap);
                camera_image.setImageBitmap(photo);

            }
        }
    }




    private class upload extends AsyncTask<Void , Void , Void>
    {

        RegisterUserClass ruc = new RegisterUserClass();

        String result = "";

        String name , email , phone , country , subject , grade , reference , last_date , area_text , expected;
        Bitmap camera , browse;

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
        }

        @Override
        protected Void doInBackground(Void... params) {


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
            data.add(new BasicNameValuePair("browse" , path));
            data.add(new BasicNameValuePair("camera" , path));

            result = ruc.sendPostRequest(POST_URL , data);

           // Toast.makeText(getApplicationContext() , result , Toast.LENGTH_SHORT).show();

            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            Log.d("asdasdasd" , result);
            Toast.makeText(getBaseContext() , result , Toast.LENGTH_SHORT).show();


        }
    }



}

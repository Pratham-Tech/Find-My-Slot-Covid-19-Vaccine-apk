package com.example.findmyslot.activities;

import androidx.appcompat.app.AppCompatActivity;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.Toast;
import com.example.findmyslot.R;

import java.util.Hashtable;
import java.util.Objects;

import static com.example.findmyslot.R.id.radioButton1;
import static com.example.findmyslot.R.id.radioButton2;

public class HomeActivity extends AppCompatActivity{

    private RadioGroup radioGroup;
    private EditText pincodeInput;
    private Spinner stateSpinner;
    private Spinner districtSpinner;
    private Button findSlots;
    private int ALL_SET = 0;
    private int radioId;
    private String Pincode;
    private Intent intent;
    private String District = "Select District";
    private String State = "Select State";
    private String districtID = "0";
    private Hashtable<String, Integer> allDistricts = new Hashtable<>();
    private final String[] state1 = {"North and Middle Andaman","South Andaman","Nicobar"};
    private final String[] state2 = {"Anantapur","Chittoor","East Godavari","Guntur","Krishna",
            "Kurnool","Prakasam","Sri Potti Sriramulu Nellore","Srikakulam","Visakhapatnam",
            "Vizianagaram","West Godavari","YSR District, Kadapa (Cuddapah)"};
    private final String[] state3 = {"Anjaw", "Changlang", "Dibang Valley", "East Kameng", "East Siang",
            "Itanagar Capital Complex", "Kamle", "Kra Daadi", "Kurung Kumey","Lepa Rada",
            "Lohit", "Longding", "Lower Dibang Valley", "Lower Siang", "Lower Subansiri",
            "Namsai", "Pakke Kessang", "Papum Pare", "Shi Yomi", "Siang", "Tawang", "Tirap",
            "Upper Siang", "Upper Subansiri", "West Kameng", "West Siang"};
    private final String[] state4 = {"Dibrugarh", "Sivasagar","Tinsukia","Baksa","Barpeta",
            "Darrang","Kamrup Metropolitan","Kamrup Rural","Karbi-Anglong","Nalbari",
            "Golaghat","Jorhat","Morigaon","Nagaon","Bongaigaon","Chirang","Dhubri",
            "Goalpara","Kokrajhar","Dhemaji","Lakhimpur","Sonitpur","Udalguri","Cachar"
            ,"Dima Hasao","Hailakandi","Karimganj","Hojai","Biswanath","Charaideo","Majuli",
            "South Salmara Mankachar","West Karbi Anglong"};
    private final String[] state5 = {"Araria","Aurangabad","Banka","Begusarai","Bhagalpur","Bhojpur",
            "Buxar","Darbhanga","East Champaran","Gaya","Gopalganj","Jamui","Jehanabad","Kaimur",
            "Katihar","Khagaria","Kishanganj","Lakhisarai","Madhepura","Madhubani","Munger",
            "Muzaffarpur","Nalanda","Nawada","Patna","Purnia","Rohtas","Saharsa","Samastipur",
            "Saran","Sheikhpura","Sheohar","Sitamarhi","Siwan","Supaul","Vaishali", "West Champaran"};
    private final String[] state6 = {"Chandigarh"};
    private final String[] state7 = {"Balod","Baloda bazar","Balrampur","Bastar","Bemetara","Bijapur",
            "Bilaspur","Dantewada","Dhamtari","Durg","Gariaband","Gaurela Pendra Marwahi ",
            "Janjgir-Champa","Jashpur","Kanker","Kawardha","Kondagaon","Korba","Koriya",
            "Mahasamund","Mungeli","Narayanpur","Raigarh","Raipur","Rajnandgaon","Sukma",
            "Surajpur","Surguja"};
    private final String[] state8 = {"Dadra and Nagar Haveli"};
    private final String[] state9 = { "Central Delhi","East Delhi","New Delhi","North Delhi",
            "North East Delhi","North West Delhi","Shahdara","South Delhi","South East Delhi",
            "South West Delhi","West Delhi"};
    private final String[] state10 = { "North Goa","South Goa"};
    private final String[] state11 = { "Ahmedabad","Ahmedabad Corporation","Amreli","Anand","Aravalli",
            "Banaskantha","Bharuch","Bhavnagar","Bhavnagar Corporation","Botad","Chhotaudepur",
            "Dahod","Dang","Devbhumi Dwaraka","Gandhinagar","Gandhinagar Corporation",
            "Gir Somnath","Jamnagar","Jamnagar Corporation","Junagadh","Junagadh Corporation",
            "Kheda","Kutch","Mahisagar","Mehsana","Morbi","Narmada","Navsari","Panchmahal","Patan",
            "Porbandar","Rajkot", "Rajkot Corporation","Sabarkantha","Surat","Surat Corporation",
            "Surendranagar","Tapi","Vadodara","Vadodara Corporation","Valsad"};
    private final String[] state12 = { "Ambala","Bhiwani","Charkhi Dadri","Faridabad","Fatehabad","Gurgaon",
            "Hisar","Jhajjar","Jind","Kaithal","Karnal","Kurukshetra","Mahendragarh","Nuh",
            "Palwal","Panchkula","Panipat","Rewari","Rohtak","Sirsa","Sonipat","Yamunanagar"};
    private final String[] state13 = { "Bilaspur","Chamba","Hamirpur","Kangra","Kinnaur","Kullu",
            "Lahaul Spiti","Mandi","Shimla","Sirmaur","Solan","Una"};
    private final String[] state14 = { "Anantnag","Bandipore","Baramulla","Budgam","Doda","Ganderbal",
            "Jammu","Kathua","Kishtwar","Kulgam","Kupwara","Poonch","Pulwama","Rajouri","Ramban",
            "Reasi","Samba","Shopian","Srinagar","Udhampur"};
    private final String[] state15 = { "Bokaro","Chatra","Deoghar","Dhanbad","Dumka","East Singhbhum",
            "Garhwa","Giridih","Godda","Gumla","Hazaribagh","Jamtara","Khunti","Koderma",
            "Latehar","Lohardaga","Pakur","Palamu","Ramgarh","Ranchi","Sahebganj",
            "Seraikela Kharsawan","Simdega","West Singhbhum"};
    private final String[] state16 = {"Bagalkot","Bangalore Rural","Bangalore Urban","BBMP","Belgaum",
            "Bellary","Bidar","Chamarajanagar","Chikamagalur","Chikkaballapur","Chitradurga",
            "Dakshina Kannada","Davanagere","Dharwad","Gadag","Gulbarga","Hassan","Haveri",
            "Kodagu","Kolar","Koppal","Mandya","Mysore","Raichur","Ramanagara","Shimoga",
            "Tumkur","Udupi","Uttar Kannada","Vijayapura","Yadgir"};
    private final String[] state17 = {"Alappuzha", "Ernakulam", "Idukki","Kannur","Kasaragod","Kollam",
            "Kottayam","Kozhikode","Malappuram"};
    private final String[] state18 = {"Kargil", "Leh"};
    private final String[] state19 = {"Agatti Island", "Lakshadweep"};
    private final String[] state20 = {"Agar","Alirajpur","Anuppur","Ashoknagar","Balaghat","Barwani",
            "Betul","Bhind","Bhopal","Burhanpur","Chhatarpur","Chhindwara","Damoh","Datia",
            "Dewas","Dhar","Dindori","Guna","Gwalior","Harda","Hoshangabad","Indore","Jabalpur",
            "Jhabua","Katni","Khandwa","Khargone","Mandla","Mandsaur","Morena","Narsinghpur",
            "Neemuch","Panna","Raisen","Rajgarh","Ratlam","Rewa","Sagar","Satna","Sehore","Seoni",
            "Shahdol","Shajapur","Sheopur","Shivpuri","Sidhi","Singrauli","Tikamgarh","Ujjain",
            "Umaria","Vidisha"};
    private final String[] state21 = {"Ahmednagar","Akola","Amravati","Aurangabad","Beed","Bhandara",
            "Buldhana","Chandrapur","Dhule","Gadchiroli","Gondia","Hingoli","Jalgaon","Jalna",
            "Kolhapur","Latur","Mumbai","Nagpur","Nanded","Nandurbar","Nashik","Osmanabad",
            "Palghar","Parbhani","Pune","Raigad","Ratnagiri","Sangli","Satara","Sindhudurg",
            "Solapur","Thane","Wardha","Washim","Yavatmal"};
    private final String[] state22 = {"Bishnupur","Chandel","Churachandpur","Imphal East","Imphal West",
            "Jiribam","Kakching","Kamjong","Kangpokpi","Noney","Pherzawl","Senapati","Tamenglong",
            "Tengnoupal","Thoubal","Ukhrul"};
    private final String[] state23 = {"East Garo Hills","East Jaintia Hills","East Khasi Hills",
            "North Garo Hills","Ri-Bhoi","South Garo Hills","South West Garo Hills",
            "South West Khasi Hills","West Garo Hills","West Jaintia Hills","West Khasi Hills"};
    private final String[] state24 = {"Aizawl East","Aizawl West","Champhai","Kolasib","Lawngtlai",
            "Lunglei","Mamit","Serchhip","Siaha"};
    private final String[] state25 = {"Dimapur","Kiphire","Kohima","Longleng","Mokokchung","Mon","Peren",
            "Phek","Tuensang", "Wokha","Zunheboto"};
    final String[] state26 = {"Angul","Balangir","Balasore","Bargarh","Bhadrak","Boudh",
            "Cuttack","Deogarh","Dhenkanal","Gajapati","Ganjam","Jagatsinghpur","Jajpur",
            "Jharsuguda","Kalahandi","Kandhamal","Kendrapara","Kendujhar","Khurda","Koraput",
            "Malkangiri","Mayurbhanj","Nabarangpur","Nayagarh","Nuapada","Puri","Rayagada",
            "Sambalpur","Subarnapur","Sundargarh"};
    private final String[] state27 = {"Karaikal", "Mahe", "Puducherry", "Yanam"};
    private final String[] state28 = {"Amritsar", "Barnala", "Bathinda", "Faridkot", "Fatehgarh Sahib",
            "Fazilka", "Ferozpur", "Gurdaspur", "Hoshiarpur", "Jalandhar", "Kapurthala", "Ludhiana",
            "Mansa", "Moga", "Pathankot", "Patiala", "Rup Nagar", "Sangrur", "SAS Nagar", "SBS Nagar",
            "Sri Muktsar Sahib", "Tarn Taran"};
    private final String[] state29 = {"Ajmer","Alwar","Banswara","Baran","Barmer","Bharatpur","Bhilwara",
            "Bikaner","Bundi","Chittorgarh","Churu","Dausa","Dholpur","Dungarpur","Hanumangarh",
            "Jaipur I","Jaipur II","Jaisalmer","Jalore","Jhalawar","Jhunjhunu","Jodhpur","Karauli",
            "Kota","Nagaur","Pali","Pratapgarh","Rajsamand","Sawai Madhopur","Sikar","Sirohi","Sri Ganganagar",
            "Tonk","Udaipur"};
    private final String[] state30 = {"East Sikkim", "North Sikkim", "South Sikkim", "West Sikkim"};
    private final String[] state31 = {"Aranthangi", "Ariyalur", "Attur", "Chengalpet", "Chennai",
            "Cheyyar","Coimbatore","Cuddalore","Dharmapuri","Dindigul","Erode","Kallakurichi",
            "Kanchipuram","Kanyakumari","Karur","Kovilpatti","Krishnagiri","Madurai",
            "Nagapattinam","Namakkal","Nilgiris","Palani","Paramakudi","Perambalur",
            "Poonamallee","Pudukkottai","Ramanathapuram","Ranipet","Salem","Sivaganga","Sivakasi",
            "Tenkasi","Thanjavur","Theni","Thoothukudi (Tuticorin)","Tiruchirappalli",
            "Tirunelveli","Tirupattur","Tiruppur","Tiruvallur","Tiruvannamalai","Tiruvarur",
            "Vellore","Viluppuram","Virudhunagar"};
    private final String[] state32 = {"Adilabad", "Bhadradri Kothagudem", "Hyderabad", "Jagtial", "Jangaon",
            "Jayashankar Bhupalpally","Jogulamba Gadwal","Kamareddy","Karimnagar","Khammam",
            "Kumuram Bheem","Mahabubabad","Mahabubnagar","Mancherial","Medak","Medchal","Mulugu",
            "Nagarkurnool","Nalgonda","Narayanpet","Nirmal","Nizamabad","Peddapalli","Rajanna Sircilla","Rangareddy","Sangareddy","Siddipet","Suryapet","Vikarabad","Wanaparthy","Warangal(Rural)","Warangal(Urban)","Yadadri Bhuvanagiri"};
    private final String[] state33 = {"Dhalai","Gomati","Khowai","North Tripura","Sepahijala",
            "South Tripura","Unakoti","West Tripura"};
    private final String[] state34 = {"Agra", "Aligarh", "Ambedkar Nagar", "Amethi", "Amroha", "Auraiya",
            "Ayodhya", "Azamgarh", "Badaun", "Baghpat", "Bahraich", "Balarampur", "Ballia",
            "Banda", "Barabanki", "Bareilly", "Basti", "Bhadohi", "Bijnour", "Bulandshahr",
            "Chandauli", "Chitrakoot", "Deoria", "Etah", "Etawah", "Farrukhabad", "Fatehpur",
            "Firozabad", "Gautam Buddha Nagar", "Ghaziabad", "Ghazipur", "Gonda", "Gorakhpur",
            "Hamirpur", "Hapur", "Hardoi", "Hathras", "Jalaun", "Jaunpur", "Jhansi", "Kannauj",
            "Kanpur Dehat", "Kanpur Nagar", "Kasganj", "Kaushambi", "Kushinagar", "Lakhimpur Kheri",
            "Lalitpur", "Lucknow", "Maharajganj", "Mahoba", "Mainpuri", "Mathura", "Mau", "Meerut",
            "Mirzapur", "Moradabad", "Muzaffarnagar", "Pilibhit", "Pratapgarh", "Prayagraj",
            "Raebareli", "Rampur", "Saharanpur", "Sambhal", "Sant Kabir Nagar", "Shahjahanpur",
            "Shamli", "Shravasti", "Siddharthnagar", "Sitapur", "Sonbhadra", "Sultanpur", "Unnao",
            "Varanasi"};
    private final String[] state35 = {"Almora", "Bageshwar", "Chamoli", "Champawat", "Dehradun",
            "Haridwar", "Nainital", "Pauri Garhwal", "Pithoragarh", "Rudraprayag", "Tehri Garhwal",
            "Udham Singh Nagar", "Uttarkashi"};
    private final String[] state36 = {"Alipurduar District", "Bankura", "Basirhat HD (North 24 Parganas)",
            "Birbhum", "Bishnupur HD (Bankura)", "Cooch Behar","COOCHBEHAR", "Dakshin Dinajpur",
            "Darjeeling", "Diamond Harbor HD (S 24 Parganas)", "East Bardhaman", "Hoogly",
            "Howrah", "Jalpaiguri", "Jhargram", "Kalimpong", "Kolkata", "Malda", "Murshidabad",
            "Nadia", "Nandigram HD (East Medinipore)", "North 24 Parganas", "Paschim Medinipore",
            "Purba Medinipore", "Purulia", "Rampurhat HD (Birbhum)", "South 24 Parganas",
            "Uttar Dinajpur", "West Bardhaman"};
    private final String[] state37 = {"Daman", "Diu"};
    private final String[] defaultState = {"Select District"};

    @SuppressLint("NonConstantResourceId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        Initialize();
        intent = new Intent(HomeActivity.this, ResultActivity.class);

        radioGroup.setOnCheckedChangeListener((group, checkedId) -> {

            switch (checkedId) {

                case radioButton1:

                    stateSpinner.setVisibility(View.INVISIBLE);
                    districtSpinner.setVisibility(View.INVISIBLE);
                    pincodeInput.setVisibility(View.VISIBLE);

                    pincodeInput.requestFocus();
                    findSlots.setEnabled(true);
                    radioId = checkedId;

                    break;

                case radioButton2:

                    pincodeInput.setVisibility(View.INVISIBLE);
                    stateSpinner.setVisibility(View.VISIBLE);
                    districtSpinner.setVisibility(View.VISIBLE);

                    ArrayAdapter<CharSequence> stateList = ArrayAdapter.createFromResource(this,
                            R.array.states, R.layout.support_simple_spinner_dropdown_item);

                    stateList.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
                    stateSpinner.setAdapter(stateList);

                    ArrayAdapter<CharSequence> districtList = ArrayAdapter.createFromResource(this,
                            R.array.district, R.layout.support_simple_spinner_dropdown_item);

                    stateList.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
                    districtSpinner.setAdapter(districtList);

                    findSlots.setEnabled(true);
                    radioId = checkedId;
                    break;
            }
        });

        findSlots.setOnClickListener(view -> {

            validate(radioId);

            switch (ALL_SET) {

                case 1:

                    intent.putExtra("input", Pincode);
                    intent.putExtra("type","pincode");
                    startActivity(intent);
                    break;

                case 2:

                    intent.putExtra("input", districtID);
                    intent.putExtra("type","district");
                    startActivity(intent);
                    break;

                case 0:

                    break;
            }
        });

        stateSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                State = parent.getItemAtPosition(position).toString();

                getDistricts(position);
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

                Toast.makeText(getApplicationContext(), "Please select your state"
                        , Toast.LENGTH_SHORT).show();
            }
        });

        districtSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                District = parent.getItemAtPosition(position).toString();

                //districtID = Objects.requireNonNull(allDistricts.get(District)).toString();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

                Toast.makeText(getApplicationContext(), "Please select your district"
                        , Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void Initialize() {

        radioGroup = findViewById(R.id.radioGrp);
        pincodeInput = findViewById(R.id.pincodeInput);
        districtSpinner = findViewById(R.id.listDistrict);
        stateSpinner = findViewById(R.id.listState);
        findSlots = findViewById(R.id.findSlots);

        pincodeInput.setVisibility(View.INVISIBLE);
        stateSpinner.setVisibility(View.INVISIBLE);
        districtSpinner.setVisibility(View.INVISIBLE);
        findSlots.setEnabled(false);
    }

    private void validate(int checkedId) {

        if (checkedId == radioButton1) {

            if (TextUtils.isEmpty(pincodeInput.getText().toString()) || pincodeInput.getText().length() < 6) {

                Toast.makeText(getApplicationContext(), "Please enter any valid pincode", Toast.LENGTH_SHORT).show();
                ALL_SET = 0;
            } else {

                Pincode = pincodeInput.getText().toString();
                findSlots.setEnabled(true);
                ALL_SET = 1;
            }
        }

        if (checkedId == radioButton2) {

            if (!State.equals("Select State") && !District.equals("Select District"))
                ALL_SET = 2;

            else
                ALL_SET = 0;
        }
    }

    private void getDistricts(int position){

        ArrayAdapter<String> districts;

        switch(position) {

            case 1:
                districts = new ArrayAdapter<>(getApplicationContext(),
                        R.layout.support_simple_spinner_dropdown_item, state1);
                break;
            case 2:
                districts = new ArrayAdapter<>(getApplicationContext(),
                        R.layout.support_simple_spinner_dropdown_item, state2);
                break;
            case 3:
                districts = new ArrayAdapter<>(getApplicationContext(),
                        R.layout.support_simple_spinner_dropdown_item, state3);
                break;
            case 4:
                districts = new ArrayAdapter<>(getApplicationContext(),
                        R.layout.support_simple_spinner_dropdown_item, state4);
                break;
            case 5:
                districts = new ArrayAdapter<>(getApplicationContext(),
                        R.layout.support_simple_spinner_dropdown_item, state5);
                break;
            case 6:
                districts = new ArrayAdapter<>(getApplicationContext(),
                        R.layout.support_simple_spinner_dropdown_item, state6);
                break;
            case 7:
                districts = new ArrayAdapter<>(getApplicationContext(),
                        R.layout.support_simple_spinner_dropdown_item, state7);
                break;
            case 8:
                districts = new ArrayAdapter<>(getApplicationContext(),
                        R.layout.support_simple_spinner_dropdown_item, state8);
                break;
            case 9:
                districts = new ArrayAdapter<>(getApplicationContext(),
                        R.layout.support_simple_spinner_dropdown_item, state9);
                break;
            case 10:
                districts = new ArrayAdapter<>(getApplicationContext(),
                        R.layout.support_simple_spinner_dropdown_item, state10);
                break;
            case 11:
                districts = new ArrayAdapter<>(getApplicationContext(),
                        R.layout.support_simple_spinner_dropdown_item, state11);
                break;
            case 12:
                districts = new ArrayAdapter<>(getApplicationContext(),
                        R.layout.support_simple_spinner_dropdown_item, state12);
            case 13:
                districts = new ArrayAdapter<>(getApplicationContext(),
                        R.layout.support_simple_spinner_dropdown_item, state13);
                break;
            case 14:
                districts = new ArrayAdapter<>(getApplicationContext(),
                        R.layout.support_simple_spinner_dropdown_item, state14);
                break;
            case 15:
                districts = new ArrayAdapter<>(getApplicationContext(),
                        R.layout.support_simple_spinner_dropdown_item, state15);
                break;
            case 16:
                districts = new ArrayAdapter<>(getApplicationContext(),
                        R.layout.support_simple_spinner_dropdown_item, state16);
                break;
            case 17:
                districts = new ArrayAdapter<>(getApplicationContext(),
                        R.layout.support_simple_spinner_dropdown_item, state17);
                break;
            case 18:
                districts = new ArrayAdapter<>(getApplicationContext(),
                        R.layout.support_simple_spinner_dropdown_item, state18);
                break;
            case 19:
                districts = new ArrayAdapter<>(getApplicationContext(),
                        R.layout.support_simple_spinner_dropdown_item, state19);
                break;
            case 20:
                districts = new ArrayAdapter<>(getApplicationContext(),
                        R.layout.support_simple_spinner_dropdown_item, state20);
                break;
            case 21:
                districts = new ArrayAdapter<>(getApplicationContext(),
                        R.layout.support_simple_spinner_dropdown_item, state21);
                districtSpinner.setAdapter(districts);
                break;
            case 22:
                districts = new ArrayAdapter<>(getApplicationContext(),
                        R.layout.support_simple_spinner_dropdown_item, state22);
                break;
            case 23:
                districts = new ArrayAdapter<>(getApplicationContext(),
                        R.layout.support_simple_spinner_dropdown_item, state23);
                break;
            case 24:
                districts = new ArrayAdapter<>(getApplicationContext(),
                        R.layout.support_simple_spinner_dropdown_item, state24);
                break;
            case 25:
                districts = new ArrayAdapter<>(getApplicationContext(),
                        R.layout.support_simple_spinner_dropdown_item, state25);
                break;
            case 26:
                districts = new ArrayAdapter<>(getApplicationContext(),
                        R.layout.support_simple_spinner_dropdown_item, state26);
                break;
            case 27:
                districts = new ArrayAdapter<>(getApplicationContext(),
                        R.layout.support_simple_spinner_dropdown_item, state27);
                break;
            case 28:
                districts = new ArrayAdapter<>(getApplicationContext(),
                        R.layout.support_simple_spinner_dropdown_item, state28);
                break;
            case 29:
                districts = new ArrayAdapter<>(getApplicationContext(),
                        R.layout.support_simple_spinner_dropdown_item, state29);
                break;
            case 30:
                districts = new ArrayAdapter<>(getApplicationContext(),
                        R.layout.support_simple_spinner_dropdown_item, state30);
                break;
            case 31:
                districts = new ArrayAdapter<>(getApplicationContext(),
                        R.layout.support_simple_spinner_dropdown_item, state31);
                break;
            case 32:
                districts = new ArrayAdapter<>(getApplicationContext(),
                        R.layout.support_simple_spinner_dropdown_item, state32);
                break;
            case 33:
                districts = new ArrayAdapter<>(getApplicationContext(),
                        R.layout.support_simple_spinner_dropdown_item, state33);
                break;
            case 34:
                districts = new ArrayAdapter<>(getApplicationContext(),
                        R.layout.support_simple_spinner_dropdown_item, state34);
                break;
            case 35:
                districts = new ArrayAdapter<>(getApplicationContext(),
                        R.layout.support_simple_spinner_dropdown_item, state35);
                break;
            case 36:
                districts = new ArrayAdapter<>(getApplicationContext(),
                        R.layout.support_simple_spinner_dropdown_item, state36);
                break;
            case 37:
                districts = new ArrayAdapter<>(getApplicationContext(),
                        R.layout.support_simple_spinner_dropdown_item, state37);
                break;
            default:
                districts = new ArrayAdapter<>(getApplicationContext(),
                        R.layout.support_simple_spinner_dropdown_item, defaultState);
                break;
        }
        districtSpinner.setAdapter(districts);
    }
}
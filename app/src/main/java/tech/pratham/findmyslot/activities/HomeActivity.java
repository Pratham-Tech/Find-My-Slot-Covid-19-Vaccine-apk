package tech.pratham.findmyslot.activities;

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
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import java.util.Hashtable;
import tech.pratham.findmyslot.R;

import static tech.pratham.findmyslot.R.id.radioButton1;
import static tech.pratham.findmyslot.R.id.radioButton2;

public class HomeActivity extends AppCompatActivity {

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
            "Itanagar Capital techplex", "Kamle", "Kra Daadi", "Kurung Kumey","Lepa Rada",
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
    private final String[] state26 = {"Angul","Balangir","Balasore","Bargarh","Bhadrak","Boudh",
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
                            R.array.states, R.layout.spinner_text);

                    stateList.setDropDownViewResource(R.layout.spinner_text);
                    stateSpinner.setAdapter(stateList);

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

                districtID = allDistricts.get(District).toString();
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

        addDistricts();
    }

    private void addDistricts() {

        allDistricts.put("Select District", 0);
        allDistricts.put("Nicobar", 3);
        allDistricts.put("North and Middle Andaman", 1);
        allDistricts.put("South Andaman", 2);
        allDistricts.put("Anantapur", 9);
        allDistricts.put("Chittoor", 10);
        allDistricts.put("East Godavari", 11);
        allDistricts.put("Guntur", 5);
        allDistricts.put("Krishna", 4);
        allDistricts.put("Kurnoon", 7);
        allDistricts.put("parakasam", 12);
        allDistricts.put("Sri Potti Sriramulu Nellore", 13);
        allDistricts.put("Srikakulam", 14);
        allDistricts.put("Vishakhapatnam", 8);
        allDistricts.put("Vizianagaram", 15);
        allDistricts.put("West Godavari", 16);
        allDistricts.put("YSR District, Kadapa (Cuddapah)", 6);
        allDistricts.put("Anjaw", 22);
        allDistricts.put("Changlang", 20);
        allDistricts.put("Dibang Valley", 25);
        allDistricts.put("East Kameng", 23);
        allDistricts.put("East Siang", 42);
        allDistricts.put("Itanagar Capital techplex", 17);
        allDistricts.put("Kamle", 24);
        allDistricts.put("Kra Daadi", 27);
        allDistricts.put("Kurung Valley", 21);
        allDistricts.put("LepaRada", 33);
        allDistricts.put("Lohit", 29);
        allDistricts.put("Longding", 40);
        allDistricts.put("Lower Dibang Valley", 31);
        allDistricts.put("Lower Siang", 18);
        allDistricts.put("Lower Subansiri", 32);
        allDistricts.put("Namsai", 36);
        allDistricts.put("Pakke Kessang", 19);
        allDistricts.put("Papum Pare", 39);
        allDistricts.put("Shi Yomi", 35);
        allDistricts.put("Siang", 37);
        allDistricts.put("Tawang", 30);
        allDistricts.put("Tirap", 26);
        allDistricts.put("Upper Siang", 34);
        allDistricts.put("Upper Subansiri", 41);
        allDistricts.put("West Kameng", 28);
        allDistricts.put("West Siang", 38);
        allDistricts.put("Baksa", 46);
        allDistricts.put("Barpeta", 47);
        allDistricts.put("Biswanath", 765);
        allDistricts.put("Bongaigaon", 57);
        allDistricts.put("Cachar", 66);
        allDistricts.put("Charaideo", 766);
        allDistricts.put("Chirang", 58);
        allDistricts.put("Darrang", 48);
        allDistricts.put("Dhemaji", 62);
        allDistricts.put("Dhubri", 59);
        allDistricts.put("Dibrugarh", 43);
        allDistricts.put("Dima Hasao", 67);
        allDistricts.put("Goalpara", 60);
        allDistricts.put("Golaghat", 53);
        allDistricts.put("Hailakandi", 68);
        allDistricts.put("Hojai", 764);
        allDistricts.put("Jorhat", 54);
        allDistricts.put("Kamrup Metropolitan", 49);
        allDistricts.put("Kamrup Rural", 50);
        allDistricts.put("Karbi-Anglong", 51);
        allDistricts.put("Karimganj", 69);
        allDistricts.put("Kokrajhar", 61);
        allDistricts.put("Lakhimpur", 63);
        allDistricts.put("Majuli", 767);
        allDistricts.put("Morigaon", 55);
        allDistricts.put("Nagaon", 56);
        allDistricts.put("Nalbari", 52);
        allDistricts.put("Sivasagar", 44);
        allDistricts.put("Sonitpur", 64);
        allDistricts.put("South Salmara Mankachar", 768);
        allDistricts.put("Tinsukia", 45);
        allDistricts.put("Udalguri", 65);
        allDistricts.put("West Karbi Anglong", 769);
        allDistricts.put("Araria", 74);
        allDistricts.put("Arwal", 78);
        allDistricts.put("Aurangabad", 77);
        allDistricts.put("Banka", 83);
        allDistricts.put("Begusarai", 98);
        allDistricts.put("Bhagalpur", 82);
        allDistricts.put("Bhojpur", 99);
        allDistricts.put("Buxar", 100);
        allDistricts.put("Darbhanga", 94);
        allDistricts.put("East Champaran", 105);
        allDistricts.put("Gaya", 79);
        allDistricts.put("Gopalganj", 104);
        allDistricts.put("Jamui", 107);
        allDistricts.put("Jehanabad", 91);
        allDistricts.put("Kaimur", 80);
        allDistricts.put("Katihar", 75);
        allDistricts.put("Khagaria", 101);
        allDistricts.put("Kishanganj", 76);
        allDistricts.put("Lakhisarai", 84);
        allDistricts.put("Madhepura", 70);
        allDistricts.put("Madhubani", 95);
        allDistricts.put("Munger", 85);
        allDistricts.put("Muzaffarpur", 86);
        allDistricts.put("Nalanda", 90);
        allDistricts.put("Nawada", 92);
        allDistricts.put("Patna", 97);
        allDistricts.put("Purnia", 73);
        allDistricts.put("Saharsa", 71);
        allDistricts.put("Samastipur", 96);
        allDistricts.put("Saran", 102);
        allDistricts.put("Sheikpura", 93);
        allDistricts.put("Sheohar", 87);
        allDistricts.put("Sitamarhi", 88);
        allDistricts.put("Siwan", 103);
        allDistricts.put("Supaul", 72);
        allDistricts.put("Vaishali", 89);
        allDistricts.put("West Champaran", 106);
        allDistricts.put("Chandigarh", 108);
        allDistricts.put("Balod", 110);
        allDistricts.put("Balod Bazar", 111);
        allDistricts.put("Balrampur", 112);
        allDistricts.put("Bastar", 113);
        allDistricts.put("Bemetara", 114);
        allDistricts.put("Bijapur", 115);
        allDistricts.put("Bilaspur", 116);
        allDistricts.put("Dantewada", 117);
        allDistricts.put("Dhamtari", 118);
        allDistricts.put("Durg", 119);
        allDistricts.put("Ghariaband", 120);
        allDistricts.put("Gaurela Pendra Marwahi", 136);
        allDistricts.put("Janjgir-Champa", 121);
        allDistricts.put("Jashpur", 122);
        allDistricts.put("Kanker", 123);
        allDistricts.put("Kawardha", 135);
        allDistricts.put("Kondagaon", 124);
        allDistricts.put("Korba", 125);
        allDistricts.put("Koriya", 126);
        allDistricts.put("Mahasamund", 127);
        allDistricts.put("Mungeli", 128);
        allDistricts.put("Narayanpur", 129);
        allDistricts.put("Raigarh", 130);
        allDistricts.put("Raipur", 109);
        allDistricts.put("Rajnandgaon", 131);
        allDistricts.put("Sukhma", 132);
        allDistricts.put("Surajpur", 133);
        allDistricts.put("Surguja", 134);
        allDistricts.put("Dadra and Nagar Haveli", 137);
        allDistricts.put("Central Delhi", 141);
        allDistricts.put("East Delhi", 145);
        allDistricts.put("New Delhi", 140);
        allDistricts.put("North Delhi", 146);
        allDistricts.put("North East Delhi", 147);
        allDistricts.put("North West Delhi", 143);
        allDistricts.put("Shahdara", 148);
        allDistricts.put("South Delhi", 149);
        allDistricts.put("South East Delhi", 144);
        allDistricts.put("South West Delhi", 150);
        allDistricts.put("West Delhi", 142);
        allDistricts.put("North Goa", 151);
        allDistricts.put("South Goa", 152);
        allDistricts.put("Ahmedabad", 154);
        allDistricts.put("Ahmedabad Corporation", 770);
        allDistricts.put("Amreli", 174);
        allDistricts.put("Anand", 179);
        allDistricts.put("Aravalli", 158);
        allDistricts.put("Banaskantha", 159);
        allDistricts.put("Bharuch", 180);
        allDistricts.put("Bhavnagar", 175);
        allDistricts.put("Bhavnagar Corporation", 771);
        allDistricts.put("Botad", 176);
        allDistricts.put("Chhotaudepur", 181);
        allDistricts.put("Dahod", 182);
        allDistricts.put("Dang", 163);
        allDistricts.put("Devbhumi Dwarka", 168);
        allDistricts.put("Gandhinagar", 153);
        allDistricts.put("Gandhinagar Corporation", 772);
        allDistricts.put("Gir Somnath", 177);
        allDistricts.put("Jamnagar", 169);
        allDistricts.put("Jamnagar Corporation", 773);
        allDistricts.put("Junagadh", 178);
        allDistricts.put("Junagadh Corporation", 774);
        allDistricts.put("Kheda", 156);
        allDistricts.put("Kutch", 170);
        allDistricts.put("Mahisagar", 183);
        allDistricts.put("Mehsana", 160);
        allDistricts.put("Morbi", 171);
        allDistricts.put("Narmada", 184);
        allDistricts.put("Navsari", 164);
        allDistricts.put("Panchmahal", 185);
        allDistricts.put("Patan", 161);
        allDistricts.put("Porbandar", 172);
        allDistricts.put("Rajkot", 173);
        allDistricts.put("Rajkot Corporation", 775);
        allDistricts.put("Sabarkantha", 162);
        allDistricts.put("Surat", 165);
        allDistricts.put("Surat Corporation", 776);
        allDistricts.put("Surendranagar", 157);
        allDistricts.put("Vadodara", 155);
        allDistricts.put("Thapi", 166);
        allDistricts.put("Vadodara Corporation", 777);
        allDistricts.put("Valsad", 167);
        allDistricts.put("Ambala", 193);
        allDistricts.put("Bhiwani", 200);
        allDistricts.put("Charkhi Dadri", 201);
        allDistricts.put("Faridabad", 199);
        allDistricts.put("Fatehabad", 196);
        allDistricts.put("Gurgaon", 188);
        allDistricts.put("Hisar", 191);
        allDistricts.put("Jhajjar", 189);
        allDistricts.put("Jind", 204);
        allDistricts.put("Kaithal", 190);
        allDistricts.put("Karnal", 203);
        allDistricts.put("Kurukshetra", 186);
        allDistricts.put("Mahendragarh", 206);
        allDistricts.put("Nuh", 205);
        allDistricts.put("Palwal", 207);
        allDistricts.put("Panchkula", 187);
        allDistricts.put("Panipat", 195);
        allDistricts.put("Rewari", 202);
        allDistricts.put("Rohtak", 192);
        allDistricts.put("Sirsa", 194);
        allDistricts.put("Sonipat", 198);
        allDistricts.put("Yamunanagar", 197);
        allDistricts.put("Bilaspur", 219);
        allDistricts.put("Chamba", 214);
        allDistricts.put("Hamirpur", 217);
        allDistricts.put("Kangra", 213);
        allDistricts.put("Kinnaur", 216);
        allDistricts.put("Kullu", 211);
        allDistricts.put("Lahaul Spiti", 210);
        allDistricts.put("Mandi", 215);
        allDistricts.put("Shimla", 208);
        allDistricts.put("Sirmaur", 212);
        allDistricts.put("Solan", 209);
        allDistricts.put("Una", 218);
        allDistricts.put("Anantnag", 224);
        allDistricts.put("Bandipore", 223);
        allDistricts.put("Baramulla", 225);
        allDistricts.put("Budgam", 229);
        allDistricts.put("Doda", 232);
        allDistricts.put("Ganderbal", 228);
        allDistricts.put("Jammu", 230);
        allDistricts.put("Kathua", 234);
        allDistricts.put("Kishtwar", 231);
        allDistricts.put("Kulgam", 221);
        allDistricts.put("Kupwara", 226);
        allDistricts.put("Poonch", 238);
        allDistricts.put("Pulwama", 227);
        allDistricts.put("Rajouri", 237);
        allDistricts.put("Ramban", 235);
        allDistricts.put("Reasi", 239);
        allDistricts.put("Samba", 236);
        allDistricts.put("Shopian", 222);
        allDistricts.put("Srinagar", 220);
        allDistricts.put("Udhampur", 233);
        allDistricts.put("Bokaro", 242);
        allDistricts.put("Chatra", 245);
        allDistricts.put("Deoghar", 253);
        allDistricts.put("Dhanbad", 257);
        allDistricts.put("Dumka", 258);
        allDistricts.put("East Singhbhum", 247);
        allDistricts.put("Garhwa", 243);
        allDistricts.put("Giridih", 256);
        allDistricts.put("Godda", 262);
        allDistricts.put("Gumla", 251);
        allDistricts.put("Hazaribagh", 255);
        allDistricts.put("Jamtara", 259);
        allDistricts.put("Khunti", 252);
        allDistricts.put("Koderma", 241);
        allDistricts.put("Latehar", 244);
        allDistricts.put("Lohardaga", 250);
        allDistricts.put("Pakur", 261);
        allDistricts.put("Palamu", 246);
        allDistricts.put("Ramgarh", 254);
        allDistricts.put("Ranchi", 240);
        allDistricts.put("Sahebganj", 260);
        allDistricts.put("Seraikela Kharsawan", 248);
        allDistricts.put("Simdega", 249);
        allDistricts.put("West Singhbhum", 263);
        allDistricts.put("Bagalkot", 270);
        allDistricts.put("Bangalore Rural", 276);
        allDistricts.put("Bangalore Urban", 265);
        allDistricts.put("BBMP", 294);
        allDistricts.put("Belgaum", 264);
        allDistricts.put("Bellary", 274);
        allDistricts.put("Bidar", 272);
        allDistricts.put("Chamarajanagar", 271);
        allDistricts.put("Chikamagalur", 273);
        allDistricts.put("Chikkaballapur", 291);
        allDistricts.put("Chitradurga", 268);
        allDistricts.put("Dakshina Kannada", 269);
        allDistricts.put("Davanagere", 275);
        allDistricts.put("Dharwad", 278);
        allDistricts.put("Gadag", 280);
        allDistricts.put("Gulbarga", 267);
        allDistricts.put("Hassan", 289);
        allDistricts.put("Haveri", 279);
        allDistricts.put("Kodagu", 283);
        allDistricts.put("Kolar", 277);
        allDistricts.put("Koppal", 282);
        allDistricts.put("Mandya", 290);
        allDistricts.put("Mysore", 266);
        allDistricts.put("Raichur", 284);
        allDistricts.put("Ramanagara", 292);
        allDistricts.put("Shimoga", 287);
        allDistricts.put("Tumkur", 288);
        allDistricts.put("Udupi", 286);
        allDistricts.put("Uttar Kannada", 281);
        allDistricts.put("Vijayapura", 293);
        allDistricts.put("Yadgir", 285);
        allDistricts.put("Alappuzha", 301);
        allDistricts.put("Ernakulam", 307);
        allDistricts.put("Idukki", 306);
        allDistricts.put("Kannur", 297);
        allDistricts.put("Kasaragod", 295);
        allDistricts.put("Kollam", 298);
        allDistricts.put("Kottayam", 304);
        allDistricts.put("Kozhikode", 305);
        allDistricts.put("Malappuram", 302);
        allDistricts.put("Palakkad", 308);
        allDistricts.put("Pathanamthitta", 300);
        allDistricts.put("Thiruvananthapuram", 296);
        allDistricts.put("Thrissur", 303);
        allDistricts.put("Wayanad", 299);
        allDistricts.put("Kargil", 309);
        allDistricts.put("Leh", 310);
        allDistricts.put("Agatti Island", 796);
        allDistricts.put("Lakshadweep", 311);
        allDistricts.put("Agar", 320);
        allDistricts.put("Alirajpur", 357);
        allDistricts.put("Anuppur", 334);
        allDistricts.put("Ashoknaga", 354);
        allDistricts.put("Balaghat", 338);
        allDistricts.put("Barwani", 343);
        allDistricts.put("Betul", 362);
        allDistricts.put("Bhind", 351);
        allDistricts.put("Bhopal", 312);
        allDistricts.put("Burhanpur", 342);
        allDistricts.put("Chhatarpur", 328);
        allDistricts.put("Chhindwara", 337);
        allDistricts.put("Damoh", 327);
        allDistricts.put("Datia", 350);
        allDistricts.put("Dewas", 324);
        allDistricts.put("Dhar", 341);
        allDistricts.put("Dindori", 336);
        allDistricts.put("Guna", 348);
        allDistricts.put("Gwalior", 313);
        allDistricts.put("Harda", 361);
        allDistricts.put("Hoshangabad", 360);
        allDistricts.put("Indore", 314);
        allDistricts.put("Jabalpur", 315);
        allDistricts.put("Jhabua", 340);
        allDistricts.put("Katni", 353);
        allDistricts.put("Khandwa", 339);
        allDistricts.put("Khargone", 344);
        allDistricts.put("Mandla", 335);
        allDistricts.put("Mandsaur", 319);
        allDistricts.put("Morena", 347);
        allDistricts.put("Narsinghpur", 352);
        allDistricts.put("Neemuch", 323);
        allDistricts.put("Panna", 326);
        allDistricts.put("Raisen", 359);
        allDistricts.put("Rajgarh", 358);
        allDistricts.put("Ratlam", 322);
        allDistricts.put("Rewa", 316);
        allDistricts.put("Sagar", 317);
        allDistricts.put("Satna", 333);
        allDistricts.put("Sehore", 356);
        allDistricts.put("Seoni", 349);
        allDistricts.put("Shahdol", 332);
        allDistricts.put("Shajapur", 321);
        allDistricts.put("Sheopur", 346);
        allDistricts.put("Shivpuri", 345);
        allDistricts.put("Sidhi", 331);
        allDistricts.put("Singrauli", 330);
        allDistricts.put("Tikamgarh", 325);
        allDistricts.put("Ujjain", 318);
        allDistricts.put("Umaria", 329);
        allDistricts.put("Vidisha", 355);
        allDistricts.put("Ahmednagar", 391);
        allDistricts.put("Akola", 364);
        allDistricts.put("Amravati", 366);
        allDistricts.put("Aurangabad", 397);
        allDistricts.put("Beed", 384);
        allDistricts.put("Bhandara", 370);
        allDistricts.put("Buldhana", 367);
        allDistricts.put("Chandrapur", 380);
        allDistricts.put("Dhule", 388);
        allDistricts.put("Gadchiroli", 379);
        allDistricts.put("Gondia", 378);
        allDistricts.put("Hingoli", 386);
        allDistricts.put("Jalgaon", 390);
        allDistricts.put("Jalna", 396);
        allDistricts.put("Kolhapur", 371);
        allDistricts.put("Latur", 383);
        allDistricts.put("Mumbai", 395);
        allDistricts.put("Nagpur", 365);
        allDistricts.put("Nanded", 382);
        allDistricts.put("Nandurbar", 387);
        allDistricts.put("Nashik", 389);
        allDistricts.put("Osmanabad", 381);
        allDistricts.put("Palghar", 394);
        allDistricts.put("Parbhani", 385);
        allDistricts.put("Pune", 363);
        allDistricts.put("Raigad", 393);
        allDistricts.put("Ratnagiri", 372);
        allDistricts.put("Sangli", 373);
        allDistricts.put("Satara", 376);
        allDistricts.put("Sindhudurg", 374);
        allDistricts.put("Solapur", 375);
        allDistricts.put("Thane", 392);
        allDistricts.put("Wardha", 377);
        allDistricts.put("Washim", 369);
        allDistricts.put("Yavatmal", 368);
        allDistricts.put("Bishnupur", 398);
        allDistricts.put("Chandel", 399);
        allDistricts.put("Churachandpur", 400);
        allDistricts.put("Imphal East", 401);
        allDistricts.put("Imphal West", 402);
        allDistricts.put("Jiribam", 410);
        allDistricts.put("Kakching", 413);
        allDistricts.put("Kamjong", 409);
        allDistricts.put("Kangpokpi", 408);
        allDistricts.put("Noney", 412);
        allDistricts.put("Pherzawl", 411);
        allDistricts.put("Senapati", 403);
        allDistricts.put("Tamenglong", 404);
        allDistricts.put("Tengnoupal", 407);
        allDistricts.put("Thoubal", 405);
        allDistricts.put("Ukhrul", 406);
        allDistricts.put("East Garo Hills", 424);
        allDistricts.put("East Jaintia Hill", 418);
        allDistricts.put("East Khasi Hills", 414);
        allDistricts.put("North Garo Hills", 423);
        allDistricts.put("Ri-Bhoi", 417);
        allDistricts.put("South Garo Hills", 421);
        allDistricts.put("South West Garo Hills", 422);
        allDistricts.put("South West Khasi Hills", 415);
        allDistricts.put("West Garo Hills", 420);
        allDistricts.put("West Jaintia Hills", 416);
        allDistricts.put("West Khasi Hills", 419);
        allDistricts.put("Aizawl East", 425);
        allDistricts.put("Aizawl West", 426);
        allDistricts.put("Champhai", 429);
        allDistricts.put("Kolasib", 428);
        allDistricts.put("Lawngtlai", 432);
        allDistricts.put("Lunglei", 431);
        allDistricts.put("Mamit", 427);
        allDistricts.put("Serchhip", 430);
        allDistricts.put("Siaha", 433);
        allDistricts.put("Dimapur", 434);
        allDistricts.put("Kiphire", 444);
        allDistricts.put("Kohima", 441);
        allDistricts.put("Longleng", 438);
        allDistricts.put("Mokokchung", 437);
        allDistricts.put("Mon", 439);
        allDistricts.put("Peren", 435);
        allDistricts.put("Phek", 443);
        allDistricts.put("Tuensang", 440);
        allDistricts.put("Wokha", 436);
        allDistricts.put("Zunheboto", 442);
        allDistricts.put("Angul", 445);
        allDistricts.put("Balangir", 448);
        allDistricts.put("Balasore", 447);
        allDistricts.put("Bargarh", 472);
        allDistricts.put("Bhadrak", 454);
        allDistricts.put("Boudh", 468);
        allDistricts.put("Cuttack", 457);
        allDistricts.put("Deogarh", 473);
        allDistricts.put("Dhenkanal", 458);
        allDistricts.put("Gajapati", 467);
        allDistricts.put("Ganjam", 449);
        allDistricts.put("Jagatsinghpur", 459);
        allDistricts.put("Jajpur", 460);
        allDistricts.put("Jharsuguda", 474);
        allDistricts.put("Kalahandi", 464);
        allDistricts.put("Kandhamal", 450);
        allDistricts.put("Kendrapara", 461);
        allDistricts.put("Kendujhar", 455);
        allDistricts.put("Khurda", 446);
        allDistricts.put("Koraput", 451);
        allDistricts.put("Malkangiri", 469);
        allDistricts.put("Mayurbhanj", 456);
        allDistricts.put("Nabarangpur", 470);
        allDistricts.put("Nayagarh", 462);
        allDistricts.put("Nuapada", 465);
        allDistricts.put("Puri", 463);
        allDistricts.put("Rayagada", 471);
        allDistricts.put("Sambalpur", 452);
        allDistricts.put("Subarnapur", 466);
        allDistricts.put("Sundargarh", 453);
        allDistricts.put("Karaikal", 476);
        allDistricts.put("Mahe", 477);
        allDistricts.put("Puducherry", 475);
        allDistricts.put("Yanam", 478);
        allDistricts.put("Amritsar", 485);
        allDistricts.put("Barnala", 483);
        allDistricts.put("Bathinda", 493);
        allDistricts.put("Faridkot", 499);
        allDistricts.put("Fatehgarh Sahib", 484);
        allDistricts.put("Fazilka", 487);
        allDistricts.put("Ferozpur", 480);
        allDistricts.put("Gurdaspur", 489);
        allDistricts.put("Hoshiarpur", 481);
        allDistricts.put("Jalandhar", 492);
        allDistricts.put("Kapurthala", 479);
        allDistricts.put("Ludhiana", 488);
        allDistricts.put("Mansa", 482);
        allDistricts.put("Moga", 491);
        allDistricts.put("Pathankot", 486);
        allDistricts.put("Patiala", 494);
        allDistricts.put("Rup Nagar", 497);
        allDistricts.put("Sangrur", 498);
        allDistricts.put("SAS Nagar", 496);
        allDistricts.put("SBS Nagar", 500);
        allDistricts.put("Sri Muktsar Sahib", 490);
        allDistricts.put("Tarn Taran", 495);
        allDistricts.put("Ajmer", 507);
        allDistricts.put("Alwar", 512);
        allDistricts.put("Banswara", 519);
        allDistricts.put("Baran", 516);
        allDistricts.put("Barmer", 528);
        allDistricts.put("Bharatpur", 508);
        allDistricts.put("Bhilwara", 523);
        allDistricts.put("Bikaner", 501);
        allDistricts.put("Bundi", 514);
        allDistricts.put("Chittorgarh", 521);
        allDistricts.put("Churu", 530);
        allDistricts.put("Dausa", 511);
        allDistricts.put("Dholpur", 524);
        allDistricts.put("Dungarpur", 520);
        allDistricts.put("Hanumangarh", 517);
        allDistricts.put("Jaipur I", 505);
        allDistricts.put("Jaipur II", 506);
        allDistricts.put("Jaisalmer", 527);
        allDistricts.put("Jalore", 533);
        allDistricts.put("Jhalawar", 515);
        allDistricts.put("Jhunjhunu", 510);
        allDistricts.put("Jodhpur", 502);
        allDistricts.put("Karauli", 525);
        allDistricts.put("Kota", 503);
        allDistricts.put("Nagaur", 532);
        allDistricts.put("Pali", 529);
        allDistricts.put("Pratapgarh", 522);
        allDistricts.put("Rajsamand", 518);
        allDistricts.put("Sawai Madhopur", 534);
        allDistricts.put("Sikar", 513);
        allDistricts.put("Sirohi", 531);
        allDistricts.put("Sri Ganganagar", 509);
        allDistricts.put("Tonk", 526);
        allDistricts.put("Udaipur", 504);
        allDistricts.put("East Sikkim", 535);
        allDistricts.put("North Sikkim", 537);
        allDistricts.put("South Sikkim", 538);
        allDistricts.put("West Sikkim", 536);
        allDistricts.put("Aranthangi", 779);
        allDistricts.put("Ariyalur", 555);
        allDistricts.put("Attur", 578);
        allDistricts.put("Chengalpet", 565);
        allDistricts.put("Chennai", 571);
        allDistricts.put("Cheyyar", 778);
        allDistricts.put("Coimbatore", 539);
        allDistricts.put("Cuddalore", 547);
        allDistricts.put("Dharmapuri", 566);
        allDistricts.put("Dindigul", 556);
        allDistricts.put("Erode", 563);
        allDistricts.put("Kallakurichi", 552);
        allDistricts.put("Kanchipuram", 557);
        allDistricts.put("Kanyakumari", 544);
        allDistricts.put("Karur", 559);
        allDistricts.put("Kovilpatti", 780);
        allDistricts.put("Krishnagiri", 562);
        allDistricts.put("Madurai", 540);
        allDistricts.put("Nagapattinam", 576);
        allDistricts.put("Namakkal", 558);
        allDistricts.put("Nilgiris", 577);
        allDistricts.put("Palani", 564);
        allDistricts.put("Paramakudi", 573);
        allDistricts.put("Perambalur", 570);
        allDistricts.put("Poonamallee", 575);
        allDistricts.put("Pudukkottai", 546);
        allDistricts.put("Ramanathapuram", 567);
        allDistricts.put("Ranipet", 781);
        allDistricts.put("Salem", 545);
        allDistricts.put("Sivaganga", 561);
        allDistricts.put("Sivakasi", 580);
        allDistricts.put("Tenkasi", 551);
        allDistricts.put("Thanjavur", 541);
        allDistricts.put("Theni", 569);
        allDistricts.put("Thoothukudi (Tuticorin)", 554);
        allDistricts.put("Tiruchirappalli", 560);
        allDistricts.put("Tirunelveli", 548);
        allDistricts.put("Tirupattur", 550);
        allDistricts.put("Tiruppur", 568);
        allDistricts.put("Tiruvallur", 572);
        allDistricts.put("Tiruvannamalai", 553);
        allDistricts.put("Tiruvarur", 574);
        allDistricts.put("Vellore", 543);
        allDistricts.put("Viluppuram", 542);
        allDistricts.put("Virudhunagar", 549);
        allDistricts.put("Adilabad", 582);
        allDistricts.put("Bhadradri Kothagudem", 583);
        allDistricts.put("Hyderabad", 581);
        allDistricts.put("Jagtial", 584);
        allDistricts.put("Jangaon", 585);
        allDistricts.put("Jayashankar Bhupalpally", 586);
        allDistricts.put("Jogulamba Gadwal", 587);
        allDistricts.put("Kamareddy", 588);
        allDistricts.put("Karimnagar", 589);
        allDistricts.put("Khammam", 590);
        allDistricts.put("Kumuram Bheem", 591);
        allDistricts.put("Mahabubabad", 592);
        allDistricts.put("Mahabubnagar", 593);
        allDistricts.put("Mancherial", 594);
        allDistricts.put("Medak", 595);
        allDistricts.put("Medchal", 596);
        allDistricts.put("Mulugu", 612);
        allDistricts.put("Nagarkurnool", 597);
        allDistricts.put("Narayanpet", 613);
        allDistricts.put("Nirmal", 599);
        allDistricts.put("Peddapalli", 601);
        allDistricts.put("Rajanna Sircilla", 602);
        allDistricts.put("Rangareddy", 603);
        allDistricts.put("Sangareddy", 604);
        allDistricts.put("Siddipet", 605);
        allDistricts.put("Suryapet", 606);
        allDistricts.put("Vikarabad", 607);
        allDistricts.put("Wanaparthy", 608);
        allDistricts.put("Warangal(Rural)", 609);
        allDistricts.put("Warangal(Urban)", 610);
        allDistricts.put("Yadadri Bhuvanagiri", 611);
        allDistricts.put("Dhalai", 614);
        allDistricts.put("Gomati", 615);
        allDistricts.put("Khowai", 616);
        allDistricts.put("North Tripura", 617);
        allDistricts.put("Sepahijala", 618);
        allDistricts.put("South Tripura", 619);
        allDistricts.put("Unakoti", 620);
        allDistricts.put("West Tripura", 621);
        allDistricts.put("Agra", 622);
        allDistricts.put("Aligarh", 623);
        allDistricts.put("Ambedkar Nagar", 625);
        allDistricts.put("Amethi", 626);
        allDistricts.put("Amroha", 627);
        allDistricts.put("Auraiya", 628);
        allDistricts.put("Ayodhya", 646);
        allDistricts.put("Azamgarh", 629);
        allDistricts.put("Badaun", 630);
        allDistricts.put("Baghpat", 631);
        allDistricts.put("Bahraich", 632);
        allDistricts.put("Balarampur", 633);
        allDistricts.put("Ballia", 634);
        allDistricts.put("Banda", 635);
        allDistricts.put("Barabanki", 636);
        allDistricts.put("Bareilly", 637);
        allDistricts.put("Basti", 638);
        allDistricts.put("Bhadohi", 687);
        allDistricts.put("Bijnour", 639);
        allDistricts.put("Bulandshahr", 640);
        allDistricts.put("Chandauli", 641);
        allDistricts.put("Chitrakoot", 642);
        allDistricts.put("Deoria", 643);
        allDistricts.put("Etah", 644);
        allDistricts.put("Etawah", 645);
        allDistricts.put("Farrukhabad", 647);
        allDistricts.put("Fatehpur", 648);
        allDistricts.put("Firozabad", 649);
        allDistricts.put("Gautam Buddha Nagar", 650);
        allDistricts.put("Ghaziabad", 651);
        allDistricts.put("Ghazipur", 652);
        allDistricts.put("Gonda", 653);
        allDistricts.put("Gorakhpur", 654);
        allDistricts.put("Hamirpur", 655);
        allDistricts.put("Hapur", 656);
        allDistricts.put("Hardoi", 657);
        allDistricts.put("Hathras", 658);
        allDistricts.put("Jalaun", 659);
        allDistricts.put("Jaunpur", 660);
        allDistricts.put("Jhansi", 661);
        allDistricts.put("Kannauj", 662);
        allDistricts.put("Kanpur Dehat", 663);
        allDistricts.put("Kanpur Nagar", 664);
        allDistricts.put("Kasganj", 665);
        allDistricts.put("Kaushambi", 666);
        allDistricts.put("Kushinagar", 667);
        allDistricts.put("Lakhimpur Kheri", 668);
        allDistricts.put("Lalitpur", 669);
        allDistricts.put("Lucknow", 670);
        allDistricts.put("Maharajganj", 671);
        allDistricts.put("Mahoba", 672);
        allDistricts.put("Mainpuri", 673);
        allDistricts.put("Mathura", 674);
        allDistricts.put("Mau", 675);
        allDistricts.put("Meerut", 676);
        allDistricts.put("Mirzapur", 677);
        allDistricts.put("Moradabad", 678);
        allDistricts.put("Muzaffarnagar", 679);
        allDistricts.put("Pilibhit", 680);
        allDistricts.put("Pratapgarh", 682);
        allDistricts.put("Prayagraj", 624);
        allDistricts.put("Raebareli", 681);
        allDistricts.put("Rampur", 683);
        allDistricts.put("Saharanpur", 684);
        allDistricts.put("Sambhal", 685);
        allDistricts.put("Sant Kabir Nagar", 686);
        allDistricts.put("Shahjahanpur", 688);
        allDistricts.put("Shamli", 689);
        allDistricts.put("Shravasti", 690);
        allDistricts.put("Siddharthnagar", 691);
        allDistricts.put("Sitapur", 692);
        allDistricts.put("Sonbhadra", 693);
        allDistricts.put("Sultanpur", 694);
        allDistricts.put("Unnao", 695);
        allDistricts.put("Varanasi", 696);
        allDistricts.put("Almora", 704);
        allDistricts.put("Bageshwar", 707);
        allDistricts.put("Chamoli", 699);
        allDistricts.put("Champawat", 708);
        allDistricts.put("Dehradun", 697);
        allDistricts.put("Haridwar", 702);
        allDistricts.put("Nainital", 709);
        allDistricts.put("Pauri Garhwal", 698);
        allDistricts.put("Pithoragarh", 706);
        allDistricts.put("Rudraprayag", 700);
        allDistricts.put("Tehri Garhwal", 701);
        allDistricts.put("Udham Singh Nagar", 705);
        allDistricts.put("Uttarkashi", 703);
        allDistricts.put("Alipurduar District", 710);
        allDistricts.put("Bankura", 711);
        allDistricts.put("Basirhat HD (North 24 Parganas)", 712);
        allDistricts.put("Birbhum", 713);
        allDistricts.put("Bishnupur HD (Bankura)", 714);
        allDistricts.put("Cooch Behar", 715);
        allDistricts.put("COOCHBEHA", 783);
        allDistricts.put("Dakshin Dinajpur", 716);
        allDistricts.put("Darjeeling", 717);
        allDistricts.put("Diamond Harbor HD (S 24 Parganas)", 718);
        allDistricts.put("East Bardhaman", 719);
        allDistricts.put("Hoogly", 720);
        allDistricts.put("Howrah", 721);
        allDistricts.put("Jalpaiguri", 722);
        allDistricts.put("Jhargram", 723);
        allDistricts.put("Kalimpong", 724);
        allDistricts.put("Kolkata", 725);
        allDistricts.put("Malda", 726);
        allDistricts.put("Murshidabad", 727);
        allDistricts.put("Nadia", 728);
        allDistricts.put("Nandigram HD (East Medinipore)", 729);
        allDistricts.put("North 24 Parganas", 730);
        allDistricts.put("Paschim Medinipore", 731);
        allDistricts.put("Purba Medinipore", 732);
        allDistricts.put("Purulia", 733);
        allDistricts.put("Rampurhat HD (Birbhum)", 734);
        allDistricts.put("South 24 Parganas", 735);
        allDistricts.put("Uttar Dinajpur", 736);
        allDistricts.put("West Bardhaman", 737);
        allDistricts.put("Daman", 138);
        allDistricts.put("Diu", 139);
        allDistricts.put("Chamba", 791);
        allDistricts.put("Kangra", 795);
        allDistricts.put("Kinnaur", 792);
        allDistricts.put("Mandi", 793);
        allDistricts.put("Shimla", 794);
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

            else {

                if (State.equals("Select State"))
                    Toast.makeText(getApplicationContext(), "Please select your state"
                            , Toast.LENGTH_SHORT).show();

                ALL_SET = 0;
            }
        }
    }

    private void getDistricts(int position){

        ArrayAdapter<String> districts;

        switch(position) {

            case 1:
                districts = new ArrayAdapter<>(getApplicationContext(),
                        R.layout.spinner_text, state1);
                break;
            case 2:
                districts = new ArrayAdapter<>(getApplicationContext(),
                        R.layout.spinner_text, state2);
                break;
            case 3:
                districts = new ArrayAdapter<>(getApplicationContext(),
                        R.layout.spinner_text, state3);
                break;
            case 4:
                districts = new ArrayAdapter<>(getApplicationContext(),
                        R.layout.spinner_text, state4);
                break;
            case 5:
                districts = new ArrayAdapter<>(getApplicationContext(),
                        R.layout.spinner_text, state5);
                break;
            case 6:
                districts = new ArrayAdapter<>(getApplicationContext(),
                        R.layout.spinner_text, state6);
                break;
            case 7:
                districts = new ArrayAdapter<>(getApplicationContext(),
                        R.layout.spinner_text, state7);
                break;
            case 8:
                districts = new ArrayAdapter<>(getApplicationContext(),
                        R.layout.spinner_text, state8);
                break;
            case 9:
                districts = new ArrayAdapter<>(getApplicationContext(),
                        R.layout.spinner_text, state9);
                break;
            case 10:
                districts = new ArrayAdapter<>(getApplicationContext(),
                        R.layout.spinner_text, state10);
                break;
            case 11:
                districts = new ArrayAdapter<>(getApplicationContext(),
                        R.layout.spinner_text, state11);
                break;
            case 12:
                districts = new ArrayAdapter<>(getApplicationContext(),
                        R.layout.spinner_text, state12);
            case 13:
                districts = new ArrayAdapter<>(getApplicationContext(),
                        R.layout.spinner_text, state13);
                break;
            case 14:
                districts = new ArrayAdapter<>(getApplicationContext(),
                        R.layout.spinner_text, state14);
                break;
            case 15:
                districts = new ArrayAdapter<>(getApplicationContext(),
                        R.layout.spinner_text, state15);
                break;
            case 16:
                districts = new ArrayAdapter<>(getApplicationContext(),
                        R.layout.spinner_text, state16);
                break;
            case 17:
                districts = new ArrayAdapter<>(getApplicationContext(),
                        R.layout.spinner_text, state17);
                break;
            case 18:
                districts = new ArrayAdapter<>(getApplicationContext(),
                        R.layout.spinner_text, state18);
                break;
            case 19:
                districts = new ArrayAdapter<>(getApplicationContext(),
                        R.layout.spinner_text, state19);
                break;
            case 20:
                districts = new ArrayAdapter<>(getApplicationContext(),
                        R.layout.spinner_text, state20);
                break;
            case 21:
                districts = new ArrayAdapter<>(getApplicationContext(),
                        R.layout.spinner_text, state21);
                districtSpinner.setAdapter(districts);
                break;
            case 22:
                districts = new ArrayAdapter<>(getApplicationContext(),
                        R.layout.spinner_text, state22);
                break;
            case 23:
                districts = new ArrayAdapter<>(getApplicationContext(),
                        R.layout.spinner_text, state23);
                break;
            case 24:
                districts = new ArrayAdapter<>(getApplicationContext(),
                        R.layout.spinner_text, state24);
                break;
            case 25:
                districts = new ArrayAdapter<>(getApplicationContext(),
                        R.layout.spinner_text, state25);
                break;
            case 26:
                districts = new ArrayAdapter<>(getApplicationContext(),
                        R.layout.spinner_text, state26);
                break;
            case 27:
                districts = new ArrayAdapter<>(getApplicationContext(),
                        R.layout.spinner_text, state27);
                break;
            case 28:
                districts = new ArrayAdapter<>(getApplicationContext(),
                        R.layout.spinner_text, state28);
                break;
            case 29:
                districts = new ArrayAdapter<>(getApplicationContext(),
                        R.layout.spinner_text, state29);
                break;
            case 30:
                districts = new ArrayAdapter<>(getApplicationContext(),
                        R.layout.spinner_text, state30);
                break;
            case 31:
                districts = new ArrayAdapter<>(getApplicationContext(),
                        R.layout.spinner_text, state31);
                break;
            case 32:
                districts = new ArrayAdapter<>(getApplicationContext(),
                        R.layout.spinner_text, state32);
                break;
            case 33:
                districts = new ArrayAdapter<>(getApplicationContext(),
                        R.layout.spinner_text, state33);
                break;
            case 34:
                districts = new ArrayAdapter<>(getApplicationContext(),
                        R.layout.spinner_text, state34);
                break;
            case 35:
                districts = new ArrayAdapter<>(getApplicationContext(),
                        R.layout.spinner_text, state35);
                break;
            case 36:
                districts = new ArrayAdapter<>(getApplicationContext(),
                        R.layout.spinner_text, state36);
                break;
            case 37:
                districts = new ArrayAdapter<>(getApplicationContext(),
                        R.layout.spinner_text, state37);
                break;
            default:
                districts = new ArrayAdapter<>(getApplicationContext(),
                        R.layout.spinner_text, defaultState);
                break;
        }
        districts.setDropDownViewResource(R.layout.spinner_text);
        districtSpinner.setAdapter(districts);
    }
}
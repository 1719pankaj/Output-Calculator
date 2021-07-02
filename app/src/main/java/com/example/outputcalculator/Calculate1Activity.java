package com.example.outputcalculator;



import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.os.Vibrator;
import android.provider.ContactsContract;
import android.telephony.TelephonyManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.ContextThemeWrapper;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;
import android.widget.ToggleButton;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import static java.lang.String.valueOf;

public class Calculate1Activity extends AppCompatActivity {
    short truck_uldd = 0,wagon_uldd = 0,wagon_pff = 0,pff_shed = 0,truck_ldd = 0,truck_pff = 0,shed_wagonn = 0,wagon_truckk = 0,re_fillingg = 0,
            re_stackk = 0,weigh_mentt = 0,normalll = 0,ott_normall = 0;
    View layout_calculate,contact_layout, layout_validate;
    EditText head, truck_unload, ow_truck_unload, wagon_unload, ow_wagon_unload, wagon_pf, ow_wagon_pf, pf_shed, owpf_shed,
            truck_load, owtruck_load, truck_pf, owtruck_pf, wagon_load, owwagon_load, wagon_truck, owwagon_truck, refilling,
            restacking, owrestacking, weighment;
    private long backpressedTime;
    private  Toast backToast;
    TextView Myname,mynumber;
    EditText data,contact;
    String paco;
    Button save_contact,next,back,save_code,send;
    SharedPreferences sharedPreferences;
    String str,strr;

    TextView tvStart;
    TextView truck_R_unload, wagon_R_unload, wagon_R_pf, pf_R_shed, truck_R_load, truck_R_pf, wagon_R_truck, re_R_filling,
            wagon_R_load, re_R_stacking, weigh_R_ment, tvtotal;
    Button next2, reset;
    String[] list1;
    TextView fulldutyhour,dutyhour,resulttime;
    String time1, starttime;
    Date d1, d2;
    String stTime, ndTime;
    String headnumber, otime;
    String dhour,DHour;
    String fullDhour;
    MediaPlayer oursong;
    Vibrator vibrator;

    private ToggleButton toggleButton;
    TimePickerDialog timepickerdialog;
    Calendar calendar;
    private EditText dateText, timeText;
    private DatePickerDialog datepickerdialog;
    private SimpleDateFormat dateFormatter1;
    private SimpleDateFormat dateFormat;
    private int CalendarHour, CalendarMinute;
    DatabaseHelper1 databaseHelper1;
    @SuppressLint("HardwareIds")
    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculate1);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        vibrator= (Vibrator) getSystemService(VIBRATOR_SERVICE);
        contact_layout=findViewById(R.id.lo_Contact);
        layout_validate=findViewById(R.id.lo_Validate);
        layout_calculate=findViewById(R.id.lo_Calculate);
        Myname= findViewById(R.id.tvName);
        mynumber= findViewById(R.id.tvNumer);
        contact= findViewById(R.id.etContact);
        data = findViewById(R.id.etData);
        save_contact = findViewById(R.id.save_contact);
        next = findViewById(R.id.next_validate);
        send = findViewById(R.id.btWhatsapp);
        back= findViewById(R.id.btBack);
        layout_validate.setVisibility(View.INVISIBLE);
        save_code= findViewById(R.id.btValidate);

        TelephonyManager tMmanager= (TelephonyManager) getSystemService(ContextThemeWrapper.TELEPHONY_SERVICE);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_NUMBERS) != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_SMS)!= PackageManager.PERMISSION_GRANTED){
            return;
        }

        ActivityCompat.requestPermissions(Calculate1Activity.this,new String[]{
                Manifest.permission.READ_PHONE_STATE,Manifest.permission.READ_PHONE_NUMBERS,
                Manifest.permission.READ_PHONE_STATE,Manifest.permission.READ_PHONE_STATE}, 121);

               final TextView contacty =new TextView(this);
        contacty.setText(tMmanager.getLine1Number());
        strr=contacty.getText().toString();
        str=strr;
        char let1=str.charAt(7);
        char let2=str.charAt(8);
        char let3=str.charAt(9);
        char let4=str.charAt(10);
        char let5=str.charAt(11);

        String getCode;
        getCode= "Request Code = ";
        int getcodetext=(let1 * let2 + let3 * let4 - let5);
        contact.setText(getCode+getcodetext);

        final TextView code =new TextView(this);
        code.setText(String.valueOf(((let1*let2+let3*let4-let5)+02)*74));

        final String code1= code.getText().toString();
        sharedPreferences = getSharedPreferences("PASS", Context.MODE_PRIVATE);
        TextView rresult =new TextView(this);
        rresult.setText(String.valueOf(sharedPreferences.getInt("pas", 0)));
        paco=rresult.getText().toString();

        /////  check password  /////
        if (paco.equals(code1)){
            layout_calculate.setVisibility(View.VISIBLE);
            layout_validate.setVisibility(View.INVISIBLE);
            contact_layout.setVisibility(View.INVISIBLE);
        }else
        {
            ActionBar actionBar = getSupportActionBar();
            actionBar.hide();
            contact_layout.setVisibility(View.VISIBLE);
            layout_validate.setVisibility(View.INVISIBLE);
            layout_calculate.setVisibility(View.INVISIBLE);
        }

        save_contact.setOnClickListener(v -> {
            Intent intent = new Intent(ContactsContract.Intents.Insert.ACTION);
            intent.setType(ContactsContract.RawContacts.CONTENT_TYPE);
            intent.putExtra(ContactsContract.Intents.Insert.NAME, Myname.getText());
            intent.putExtra(ContactsContract.Intents.Insert.PHONE, mynumber.getText());
            startActivity(intent);
        });

        next.setOnClickListener(v -> {
            ActionBar actionBar = getSupportActionBar();
            actionBar.hide();
            contact_layout.setVisibility(View.INVISIBLE);
            layout_validate.setVisibility(View.VISIBLE);
            layout_calculate.setVisibility(View.INVISIBLE);
        });

        back.setOnClickListener(v -> {
            contact_layout.setVisibility(View.VISIBLE);
            layout_validate.setVisibility(View.INVISIBLE);
            layout_calculate.setVisibility(View.INVISIBLE);
        });

        save_code.setOnClickListener(v -> {
            String pass = data.getText().toString();
            if (pass.equals(code1)) {
                SharedPreferences.Editor edit = sharedPreferences.edit();
                edit.putInt("pas", Integer.parseInt(pass));
                edit.apply();
                Toast.makeText(Calculate1Activity.this, "Congratulation", Toast.LENGTH_SHORT).show();
                ActionBar actionBar = getSupportActionBar();
                actionBar.show();
                layout_calculate.setVisibility(View.VISIBLE);
                contact_layout.setVisibility(View.INVISIBLE);
                layout_validate.setVisibility(View.INVISIBLE);

            } else
                Toast.makeText(Calculate1Activity.this, "Incorrect password", Toast.LENGTH_LONG).show();
        });

        {
            send.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String shareobject;
                    shareobject=contacty.getText().toString();
                    Intent i = new Intent(Intent.ACTION_SEND);
                    i.setType("text/plain");
                    i.putExtra(Intent.EXTRA_TEXT, shareobject);
                    i.setPackage("com.whatsapp");
                    startActivity(i);
                }
            });
        }

        head = findViewById(R.id.etHead);
        // Request focus on Head//
        head.requestFocus();
        final Spinner spinner1 = findViewById(R.id.spinner1);
        toggleButton = findViewById(R.id.toggleButton1);
        next2 = findViewById(R.id.btNext2);
        final TextView othour = new TextView(this);
        tvStart = new TextView(this);
        fulldutyhour = findViewById(R.id.tvFullDutyHour);
        dutyhour = findViewById(R.id.tvDutyHour);
        resulttime= findViewById(R.id.tvOverTime);
        starttime= "5:30 PM";
        fulldutyhour.setText("07:30");
        dutyhour.setText("07:30");
        timeText = findViewById(R.id.etTime);
        dateFormatter1 = new SimpleDateFormat("dd-MM-yyyy", Locale.US);
        dateFormat = new SimpleDateFormat("hh:mm aa", Locale.US);

        truck_unload = findViewById(R.id.etTruck_unload);
        ow_truck_unload = findViewById(R.id.etOW_TU);
        truck_R_unload = findViewById(R.id.tvTruck_Unload);

        wagon_unload = findViewById(R.id.etWagon_unload);
        ow_wagon_unload = findViewById(R.id.etOW_Wag_UL);
        wagon_R_unload = findViewById(R.id.tvWagon_unload);

        wagon_pf = findViewById(R.id.etWagon_to_PF);
        ow_wagon_pf = findViewById(R.id.etOW_Wag_PF);
        wagon_R_pf = findViewById(R.id.tvWagon_to_PF);

        pf_shed = findViewById(R.id.etPF_to_Shed);
        owpf_shed = findViewById(R.id.etOW_PF_Shed);
        pf_R_shed = findViewById(R.id.tvPF_to_Shed);

        truck_load = findViewById(R.id.etTruck_Load);
        owtruck_load = findViewById(R.id.etOW_Tr_LD);
        truck_R_load = findViewById(R.id.tvTruck_Load);

        truck_pf = findViewById(R.id.etTruck_PF);
        owtruck_pf = findViewById(R.id.etOW_Tr_PF);
        truck_R_pf = findViewById(R.id.tvTruck_PF);

        wagon_load = findViewById(R.id.etShed_to_Wagon);
        owwagon_load = findViewById(R.id.etOW_Shed_Wag);
        wagon_R_load = findViewById(R.id.tvShed_to_Wagon);

        wagon_truck = findViewById(R.id.etWagon_to_Truck);
        owwagon_truck = findViewById(R.id.etOW_Wag_Tr);
        wagon_R_truck = findViewById(R.id.tvWagon_to_Truck);

        refilling = findViewById(R.id.etRefilling);
        re_R_filling = findViewById(R.id.tvRefilling);

        restacking = findViewById(R.id.etRestacking);
        owrestacking = findViewById(R.id.etOW_Restack);
        re_R_stacking = findViewById(R.id.tvRestacking);

        weighment = findViewById(R.id.etWeighmentt);
        weigh_R_ment = findViewById(R.id.tvWeighment);

        tvtotal = findViewById(R.id.tvTotal);
        tvtotal.setText(String.format(Locale.US, "0.00"));


        list1 = new String[]{"10:00 AM", "09:00 AM", "08:00 AM", "07:00 AM", "06:00 AM"};
        ArrayAdapter<String> adapter1 = new ArrayAdapter<>(Calculate1Activity.this, android.R.layout.simple_list_item_1, list1);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner1.setAdapter(adapter1);

        spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                time1 = (String) parent.getItemAtPosition(position);
                tvStart.setText(time1);
                stTime = tvStart.getText().toString();
                ndTime = timeText.getText().toString();

                timeCalculate();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        fulldutyhour.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                fullDhour = fulldutyhour.getText().toString();
                DHour = dutyhour.getText().toString();
                dutuFullHour();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        dutyhour.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                fullDhour = fulldutyhour.getText().toString();
                DHour = dutyhour.getText().toString();
                dutuFullHour();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        reset = findViewById(R.id.btRefresh);
        {
            reset.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    head.requestFocus();
                    head.setText("");
                    list1 = new String[]{"10:00 AM", "09:00 AM", "08:00 AM", "07:00 AM", "06:00 AM"};
                    ArrayAdapter<String> adapter1 = new ArrayAdapter<>(Calculate1Activity.this, android.R.layout.simple_list_item_1, list1);
                    adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spinner1.setAdapter(adapter1);
                    timeText.setText(starttime);
                    truck_unload.setText("");
                    wagon_unload.setText("");
                    wagon_pf.setText("");
                    pf_shed.setText("");
                    truck_load.setText("");
                    truck_pf.setText("");
                    wagon_load.setText("");
                    wagon_truck.setText("");
                    refilling.setText("");
                    restacking.setText("");
                    weighment.setText("");
                    tvtotal.setText(String.format(Locale.US, "0.00"));

                    ow_truck_unload.setText("");
                    ow_wagon_unload.setText("");
                    ow_wagon_pf.setText("");
                    owpf_shed.setText("");
                    owtruck_load.setText("");
                    owtruck_pf.setText("");
                    owwagon_load.setText("");
                    owwagon_truck.setText("");
                    re_R_filling.setText("");
                    owrestacking.setText("");
                    weigh_R_ment.setText("");
                }
            });
        }

////for next Activity Without Any Value--------------------------------
        databaseHelper1 = new DatabaseHelper1(this);
        Cursor cursor1 = databaseHelper1.getAllData();
        if (cursor1.getCount() == 0) {
            Toast.makeText(Calculate1Activity.this, "NORMS DATA NOT FOUND", Toast.LENGTH_LONG).show();
            return;
        }
        while (cursor1.moveToNext()) {
            truck_uldd = Short.parseShort(cursor1.getString(1));
            wagon_uldd = Short.parseShort(cursor1.getString(2));
            wagon_pff = Short.parseShort(cursor1.getString(3));
            pff_shed = Short.parseShort(cursor1.getString(4));
            truck_ldd = Short.parseShort(cursor1.getString(5));
            truck_pff = Short.parseShort(cursor1.getString(6));
            shed_wagonn = Short.parseShort(cursor1.getString(7));
            wagon_truckk = Short.parseShort(cursor1.getString(8));
            re_fillingg = Short.parseShort(cursor1.getString(9));
            re_stackk = Short.parseShort(cursor1.getString(10));
            weigh_mentt = Short.parseShort(cursor1.getString(11));
            normalll = Short.parseShort(cursor1.getString(12));
            ott_normall = Short.parseShort(cursor1.getString(13));
        }

        TextWatcher textWatcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }


            @Override
            public void afterTextChanged(Editable s) {
                calcResult();
            }

            private void calcResult() throws NumberFormatException {
                Double tr_unld = 0.0, owtr_unld = 0.0, tr_unldd, wg_unld = 0.0, owwag_unld = 0.0, wag_unldd, wag_pf = 0.0, owwag_pf = 0.0, wag_pff,
                        pf_sd = 0.0, owpf_sd = 0.0, pf_sdd, tr_load = 0.0, owtr_load = 0.0, tr_loadd, tr_pf = 0.0, owtr_pf = 0.0, tr_pff, wag_load = 0.0, owwag_load = 0.0, wag_llodd,
                        wag_truck = 0.0, owwag_truck = 0.0, wag_trckk, refill = 0.0, re_fill, re_stack = 0.0, owre_stack = 0.0, re_sttacc, weigh_ment = 0.0, wegh_mmentt, total;

                Editable editableText1 = truck_unload.getText();
                if (editableText1 != null && editableText1.length() >= 1)
                    tr_unld = Double.parseDouble(editableText1.toString());
                Editable editableText2 = ow_truck_unload.getText();

                if (editableText2 != null && editableText2.length() >= 1)
                    owtr_unld = Double.parseDouble(editableText2.toString());
                tr_unldd = (tr_unld - owtr_unld)*normalll/truck_uldd + (owtr_unld * normalll / re_fillingg);
                truck_R_unload.setText(String.format(Locale.US, "%.2f", tr_unldd));

                Editable editableText3 = wagon_unload.getText();
                if (editableText3 != null && editableText3.length() >= 1)
                    wg_unld = Double.parseDouble(editableText3.toString());

                Editable editableText4 = ow_wagon_unload.getText();
                if (editableText4 != null && editableText4.length() >= 1)
                    owwag_unld = Double.parseDouble(editableText4.toString());

                wag_unldd = ((wg_unld - owwag_unld) * normalll /  wagon_uldd) + (owwag_unld * normalll / re_fillingg);
                wagon_R_unload.setText(String.format(Locale.US, "%.2f", wag_unldd));

                Editable editableText5 = wagon_pf.getText();
                if (editableText5 != null && editableText5.length() >= 1)
                    wag_pf = Double.parseDouble(editableText5.toString());

                Editable editableText6 = ow_wagon_pf.getText();
                if (editableText6 != null && editableText6.length() >= 1)
                    owwag_pf = Double.parseDouble(editableText6.toString());

                wag_pff = ((wag_pf - owwag_pf) * normalll / wagon_pff) + (owwag_pf * normalll / re_fillingg);
                wagon_R_pf.setText(String.format(Locale.US, "%.2f", wag_pff));

                Editable editableText7 = pf_shed.getText();
                if (editableText7 != null && editableText7.length() >= 1)
                    pf_sd = Double.parseDouble(editableText7.toString());

                Editable editableText8 = owpf_shed.getText();
                if (editableText8 != null && editableText8.length() >= 1)
                    owpf_sd = Double.parseDouble(editableText8.toString());

                pf_sdd = ((pf_sd - owpf_sd) * normalll / pff_shed) + (owpf_sd * normalll / re_fillingg);
                pf_R_shed.setText(String.format(Locale.US, "%.2f", pf_sdd));

                Editable editableText9 = truck_load.getText();
                if (editableText9 != null && editableText9.length() >= 1)
                    tr_load = Double.parseDouble(editableText9.toString());

                Editable editableText10 = owtruck_load.getText();
                if (editableText10 != null && editableText10.length() >= 1)
                    owtr_load = Double.parseDouble(editableText10.toString());

                tr_loadd = ((tr_load - owtr_load) * normalll / truck_ldd) + (owtr_load * normalll / re_fillingg);
                truck_R_load.setText(String.format(Locale.US, "%.2f", tr_loadd));

                Editable editableText11 = truck_pf.getText();
                if (editableText11 != null && editableText11.length() >= 1)
                    tr_pf = Double.parseDouble(editableText11.toString());

                Editable editableText12 = owtruck_pf.getText();
                if (editableText12 != null && editableText12.length() >= 1)
                    owtr_pf = Double.parseDouble(editableText12.toString());

                tr_pff = ((tr_pf - owtr_pf) * normalll / truck_pff) + (owtr_pf * normalll / re_fillingg);
                truck_R_pf.setText(String.format(Locale.US, "%.2f", tr_pff));

                Editable editableText13 = wagon_load.getText();
                if (editableText13 != null && editableText13.length() >= 1)
                    wag_load = Double.parseDouble(editableText13.toString());

                Editable editableText14 = owwagon_load.getText();
                if (editableText14 != null && editableText14.length() >= 1)
                    owwag_load = Double.parseDouble(editableText14.toString());

                wag_llodd = ((wag_load - owwag_load) * normalll / shed_wagonn) + (owwag_load * normalll / re_fillingg);
                wagon_R_load.setText(String.format(Locale.US, "%.2f", wag_llodd));

                Editable editableText15 = wagon_truck.getText();
                if (editableText15 != null && editableText15.length() >= 1)
                    wag_truck = Double.parseDouble(editableText15.toString());

                Editable editableText16 = owwagon_truck.getText();
                if (editableText16 != null && editableText16.length() >= 1)
                    owwag_truck = Double.parseDouble(editableText16.toString());

                wag_trckk = ((wag_truck - owwag_truck) * normalll / wagon_truckk) + (owwag_truck * normalll / re_fillingg);
                wagon_R_truck.setText(String.format(Locale.US, "%.2f", wag_trckk));

                Editable editableText17 = refilling.getText();
                if (editableText17 != null && editableText17.length() >= 1)
                    refill = Double.parseDouble(editableText17.toString());

                re_fill = (refill * normalll / re_fillingg);
                re_R_filling.setText(String.format(Locale.US, "%.2f", re_fill));

                Editable editableText18 = restacking.getText();
                if (editableText18 != null && editableText18.length() >= 1)
                    re_stack = Double.parseDouble(editableText18.toString());

                Editable editableText19 = owrestacking.getText();
                if (editableText19 != null && editableText19.length() >= 1)
                    owre_stack = Double.parseDouble(editableText19.toString());

                re_sttacc = ((re_stack - owre_stack) * normalll / re_stackk) + (owre_stack * normalll / re_fillingg);
                re_R_stacking.setText(String.format(Locale.US, "%.2f", re_sttacc));

                Editable editableText20 = weighment.getText();
                if (editableText20 != null && editableText20.length() >= 1)
                    weigh_ment = Double.parseDouble(editableText20.toString());

                wegh_mmentt = (weigh_ment * normalll / weigh_mentt);
                weigh_R_ment.setText(String.format(Locale.US, "%.2f", wegh_mmentt));

                total = (tr_unldd + wag_unldd + wag_pff + pf_sdd + tr_loadd + tr_pff + wag_llodd + wag_trckk + re_fill + re_sttacc + wegh_mmentt);
                if (total.doubleValue() >= 1)
                    if (total != null) {
                        tvtotal.setText(String.format(Locale.US, "%.2f", total));
                    }
            }

        };
        truck_unload.addTextChangedListener(textWatcher);
        ow_truck_unload.addTextChangedListener(textWatcher);

        wagon_unload.addTextChangedListener(textWatcher);
        ow_wagon_unload.addTextChangedListener(textWatcher);

        wagon_pf.addTextChangedListener(textWatcher);
        ow_wagon_pf.addTextChangedListener(textWatcher);

        pf_shed.addTextChangedListener(textWatcher);
        owpf_shed.addTextChangedListener(textWatcher);

        truck_load.addTextChangedListener(textWatcher);
        owtruck_load.addTextChangedListener(textWatcher);

        truck_pf.addTextChangedListener(textWatcher);
        owtruck_pf.addTextChangedListener(textWatcher);

        wagon_load.addTextChangedListener(textWatcher);
        owwagon_load.addTextChangedListener(textWatcher);

        wagon_truck.addTextChangedListener(textWatcher);
        owwagon_truck.addTextChangedListener(textWatcher);

        refilling.addTextChangedListener(textWatcher);

        restacking.addTextChangedListener(textWatcher);
        owrestacking.addTextChangedListener(textWatcher);

        weighment.addTextChangedListener(textWatcher);

        // Start Intent //
        next2.setOnClickListener(v -> {
            DatabaseHelper databaseHelper;
            databaseHelper = new DatabaseHelper(getApplicationContext());
            Cursor cursor = databaseHelper.getAllData();
            if (cursor.getCount() == 0) {
                Intent intent=new Intent(Calculate1Activity.this,RateActivity.class);//////////////////////////
                startActivity(intent);
                finish();
                return;

            }
            headnumber = head.getText().toString();
            otime = resulttime.getText().toString();
            String notime = ("0:00");
            SimpleDateFormat hourformat = new SimpleDateFormat("HH:mmm");
            try {
                Date d6 = hourformat.parse(otime);
                Date d7 = hourformat.parse(notime);
                long diftime = d6.getTime() - d7.getTime();
                String hou_mint = (valueOf(diftime));
                double MinuteDouble = Double.parseDouble(hou_mint);
                double OTvalue = (MinuteDouble);
                double otminute = Double.parseDouble(String.valueOf(OTvalue));
                double OTvalu = (otminute / (1000 * 60)) / 60;
                othour.setText(String.format(Locale.US, "%.2f", OTvalu));
            } catch (ParseException e) {
                e.printStackTrace();
            }
            if (headnumber.equalsIgnoreCase("")) {
                Toast.makeText(Calculate1Activity.this, "Please Enter: (Number Of 'HEADS' ) ", Toast.LENGTH_LONG).show();
            } else {
                String key1 = head.getText().toString();
                String key2 = othour.getText().toString();
                String key3 = tvtotal.getText().toString();

                Intent intent = new Intent(Calculate1Activity.this, Calculate2Activity.class);///////////////////////

                if (key1 != null && key1.length() >= 1)
                    intent.putExtra("HEAD", key1);
                if (key2 != null && key2.length() >= 1)
                    intent.putExtra("OTHOUR", key2);
                intent.putExtra("BAGS", key3);
                startActivity(intent);
            }
        });

        findViewsById();
        setDateTimeField();
        setTimeField();
        toggleButton.setOnClickListener(v -> {
            boolean off = toggleButton.isChecked();
            if (off) {
                vibrator.vibrate(65);
                String duty_hour="00:30";
                dutyhour.setText(duty_hour);
            } else {
                vibrator.vibrate(65);
                String duty_hour="7:30";
                dutyhour.setText(duty_hour);
            }
        });
    }
                ///////////////////////////****************************/////////////////////////////
    public void timeCalculate (){
        SimpleDateFormat dateFormat = new SimpleDateFormat("hh:mm aa", Locale.US);
        try {
            d1 = dateFormat.parse(stTime);
            d2 = dateFormat.parse(ndTime);

            long diffOTtime = d2.getTime() - d1.getTime();

            int Hours = (int) ((diffOTtime ) / (1000 * 60 * 60));
            int Mins = (int) (diffOTtime ) / (1000 * 60) % 60;

            String timeSetz = "";
            if (Hours < 0)
                timeSetz = "00";
            else
                timeSetz = String.valueOf(Hours);

            String minutez = "";
            if (Mins >= 1 && Mins < 10)
                minutez = "0" + Mins;
            if (Mins < 1)
                minutez = "00";
            else
                minutez = String.valueOf(Mins);
            String dhour = ((timeSetz) + ":" + (minutez));
            fulldutyhour.setText(dhour);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
    public void dutuFullHour(){
        SimpleDateFormat hourformat = new SimpleDateFormat("HH:mmm");
        try {
            Date d6 = hourformat.parse(fullDhour);
            Date d7 = hourformat.parse(DHour);
            long diffOTtime = d6.getTime() - d7.getTime();

            long Hours= diffOTtime/(60*60*1000) %24;
            long Mins= diffOTtime/(60*1000)% 60;
            dhour = ((Hours) + ":" + (Mins));
            resulttime.setText(dhour);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
                 ///////////////////////////////*****************///////////////////////////////////
    ///MENU IN ACTION BAR///
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.menu_item,menu);
        return super.onCreateOptionsMenu(menu);
    }
    ///SELECT ACTIVITY///
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.action_rate:
                Intent i1=new Intent(Calculate1Activity.this,RateActivity.class);
                startActivity(i1);
                finish();
                return true;

            case R.id.action_norms:
                Intent i3=new Intent(Calculate1Activity.this,WorkCategoryActivity.class);
                startActivity(i3);
                finish();
                return true;

            case R.id.action_update:
                Intent i2=new Intent(Calculate1Activity.this,UpdateActivity.class);
                startActivity(i2);
                finish();
                return true;

            case R.id.action_about:
                Intent i=new Intent(Calculate1Activity.this,RateActivity.class);
                startActivity(i);
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void findViewsById() {
        dateText = findViewById(R.id.etDate);
        dateText.setFocusable(false);
        dateText.setKeyListener(null);
        Calendar c = Calendar.getInstance();
        int day = c.get(Calendar.DAY_OF_MONTH);
        int month = c.get(Calendar.MONTH);
        int year = c.get(Calendar.YEAR);
        String date = day + "/" + (month + 1) + "/" + year;
        dateText.setText(date);

        timeText = findViewById(R.id.etTime);
        timeText.setFocusable(false);
        timeText.setKeyListener(null);
        timeText.setText(starttime);

    }

    private void setDateTimeField() {
        dateText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                datepickerdialog.show();
            }
        });
        Calendar newCalender = Calendar.getInstance();
        datepickerdialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                Calendar newDate = Calendar.getInstance();

                newDate.set(year, month, dayOfMonth);
                dateText.setText(dateFormatter1.format(newDate.getTime()));

            }
        }, newCalender.get(Calendar.YEAR), newCalender.get(Calendar.MONTH),
                newCalender.get(Calendar.DAY_OF_MONTH));
    }

    private void setTimeField() {
        timeText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                timepickerdialog.show();
                timepickerdialog.setTitle("Set End Time :");

            }
        });

        calendar = Calendar.getInstance();

        timepickerdialog = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                String timeSet = "";
                if (hourOfDay > 12) {
                    hourOfDay -= 12;
                    timeSet = " PM";
                } else if (hourOfDay == 0) {
                    hourOfDay += 12;
                    timeSet = " AM";
                } else if (hourOfDay == 12)
                    timeSet = " PM";
                else
                    timeSet = " AM";
                String minutes = "";
                if (minute < 10)
                    minutes = "0" + (minute);
                else
                    minutes = String.valueOf(minute);

                timeText.setText(hourOfDay + ":" + minutes + timeSet);

                ndTime = timeText.getText().toString();
                stTime = tvStart.getText().toString();
               timeCalculate();
            }
        }, CalendarHour, CalendarMinute, false);
    }

    @Override
    public void onBackPressed() {
        if (backpressedTime + 2000 > System.currentTimeMillis()){
            backToast.cancel();
            super.onBackPressed();
            return;
        }else {
          backToast= Toast.makeText(getBaseContext(),"press once again to exit",Toast.LENGTH_SHORT);
        backToast.show();
        }
       backpressedTime=System.currentTimeMillis();
    }
}


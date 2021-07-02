package com.example.outputcalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Locale;

public class Calculate2Activity extends AppCompatActivity {
    short normalll = 0,ott_normall = 0;
    
    EditText high_1, high_2, high_3, high_4, high_5, lead_1, lead_2, lead_3, lead_4;
    TextView t_high_1, t_high_2, t_high_3, t_high_4, t_high_5, t_lead_1, t_lead_2, t_lead_3, t_lead_4,
            net_bag, day_bag, ot_bag, day_aft_norm, ot_aft_norm, day_slab_1, ot_slab_1, day_slab_2,
            ot_slab_2, day_slab_3, ot_slab_3, day_slab_4, ot_slab_4, day_slab_1R, ot_slab_1R, day_slab_2R,
            ot_slab_2R, day_slab_3R, ot_slab_3R, day_slab_4R, ot_slab_4R, day_s_value, ot_s_value, total, high, lead, tvgross, overtime;
    String netbag = null, headkey = null, otkey = null;
    Double perday_rate_new, slab_1dbr, slab_2dbr, slab_3dbr, slab_4dbr,high_11, high_13,high_15, high_17,high_19,lead_11,lead_21, lead_31,lead_41;
    Double all_bags = 0.0;
    Button calculate;
    DatabaseHelper databaseHelper;
    DatabaseHelper1 databaseHelper1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculate2);
        high_1 = findViewById(R.id.etHigh_11);
        high_1.requestFocus();                //// Request focus on height_1////
        high_2 = findViewById(R.id.etHigh_13);
        high_3 = findViewById(R.id.etHigh_15);
        high_4 = findViewById(R.id.etHigh_17);
        high_5 = findViewById(R.id.etHigh_19);

        t_high_1 = findViewById(R.id.tvHigh1);
        t_high_2 = findViewById(R.id.tvHigh2);
        t_high_3 = findViewById(R.id.tvHigh3);
        t_high_4 = findViewById(R.id.tvHigh4);
        t_high_5 = findViewById(R.id.tvHigh5);

        lead_1 = findViewById(R.id.etLead_1);
        lead_2 = findViewById(R.id.etLead_2);
        lead_3 = findViewById(R.id.etLead_3);
        lead_4 = findViewById(R.id.etLead_4);

        t_lead_1 = findViewById(R.id.tvLead1);
        t_lead_2 = findViewById(R.id.tvLead2);
        t_lead_3 = findViewById(R.id.tvLead3);
        t_lead_4 = findViewById(R.id.tvLead4);

        calculate = findViewById(R.id.btCalculate);

        ///----------Importing Intent ----------------///

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            otkey = extras.getString("OTHOUR");
            headkey = extras.getString("HEAD");
            netbag = extras.getString("BAGS");
        }
///-----------Slab---Bags---tv------///
        all_bags = Double.parseDouble(String.valueOf(netbag));
        net_bag = findViewById(R.id.tvNet_Bag);
        net_bag.setText(String.format(Locale.US, "%.2f", all_bags));

        day_bag = findViewById(R.id.tvDay_Bag);
        day_aft_norm = findViewById(R.id.tvAfter_norm);

        ot_bag = findViewById(R.id.tvOT_Bag);
        ot_aft_norm = findViewById(R.id.tvAfter_OTnorm);

        day_slab_1 = findViewById(R.id.tvDay_Slab_1);
        day_slab_2 = findViewById(R.id.tvDay_Slab_2);
        day_slab_3 = findViewById(R.id.tvDay_Slab_3);
        day_slab_4 = findViewById(R.id.tvDay_Above_Slab);

        ot_slab_1 = findViewById(R.id.tvOt_Slab_1);
        ot_slab_2 = findViewById(R.id.tvOt_Slab_2);
        ot_slab_3 = findViewById(R.id.tvOt_Slab_3);
        ot_slab_4 = findViewById(R.id.tvOT_Above_Slab);
///----Slab---Bags---tv--R------///
        day_slab_1R = findViewById(R.id.tvSlab_1R);
        day_slab_1R = findViewById(R.id.tvSlab_1R);
        day_slab_2R = findViewById(R.id.tvSlab_2R);
        day_slab_3R = findViewById(R.id.tvSlab_3R);
        day_slab_4R = findViewById(R.id.tvSlab_4R);

        ot_slab_1R = findViewById(R.id.tvot_Slab_1R);
        ot_slab_1R = findViewById(R.id.tvot_Slab_1R);
        ot_slab_2R = findViewById(R.id.tvot_Slab_2R);
        ot_slab_3R = findViewById(R.id.tvot_Slab_3R);
        ot_slab_4R = findViewById(R.id.tvot_Slab_4R);

        day_s_value = findViewById(R.id.tv_Day_Slab_value);
        ot_s_value = findViewById(R.id.tv_OT_Slab_value);

        high = findViewById(R.id.tvHight);
        total = findViewById(R.id.tv_Total);
        lead = findViewById(R.id.tvLead);

        tvgross = findViewById(R.id.tvGross);
        overtime = findViewById(R.id.tvOtr);
///--------------------Data Import from db--------------------///


            databaseHelper = new DatabaseHelper(this);

            Cursor cursor = databaseHelper.getAllData();
            if (cursor.getCount() == 0) {
                Toast.makeText(Calculate2Activity.this, "RATE DATA NOT FOUND", Toast.LENGTH_LONG).show();
                return;
            }

        while (cursor.moveToNext()) {
            perday_rate_new=Double.parseDouble(cursor.getString(8));
            slab_1dbr=Double.parseDouble(cursor.getString(9));
            slab_2dbr=Double.parseDouble(cursor.getString(10));
            slab_3dbr=Double.parseDouble(cursor.getString(11));
            slab_4dbr=Double.parseDouble(cursor.getString(12));
            high_11=Double.parseDouble(cursor.getString(13));
            high_13=Double.parseDouble(cursor.getString(14));
            high_15=Double.parseDouble(cursor.getString(15));
            high_17=Double.parseDouble(cursor.getString(16));
            high_19=Double.parseDouble(cursor.getString(17));

            lead_11=Double.parseDouble(cursor.getString(18));
            lead_21=Double.parseDouble(cursor.getString(19));
            lead_31=Double.parseDouble(cursor.getString(20));
            lead_41=Double.parseDouble(cursor.getString(21));
            }
databaseHelper1 = new DatabaseHelper1(this);
        Cursor cursor1 = databaseHelper1.getAllData();
        if (cursor1.getCount() == 0) {
            Toast.makeText(Calculate2Activity.this, "NORMS DATA NOT FOUND", Toast.LENGTH_LONG).show();
            return;
        }
        while (cursor1.moveToNext()) {

            normalll = Short.parseShort(cursor1.getString(12));
            ott_normall = Short.parseShort(cursor1.getString(13));
        }



                {
                calculate.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        Double hig_1 = 0.0, hig_1_1, hig_2 = 0.0, hig_1_2, hig_3 = 0.0, hig_1_3, hig_4 = 0.0, hig_1_4, hig_5 = 0.0, hig_1_5,
                                result, result2, result3, result4, result5;
                        Double led_1 = 0.0, led_1_1, led_2 = 0.0, led_1_2, led_3 = 0.0, led_1_3, led_4 = 0.0, led_1_4, lead1, lead2, lead3, lead4;
///----------------High Calculation---------------///
                        //high_11
                        hig_1_1 = Double.parseDouble(String.valueOf(high_11));
                        if (high_1 != null && high_1.length() >= 1)
                            hig_1 = Double.parseDouble(high_1.getText().toString());
                        result = hig_1 * hig_1_1;
                        t_high_1.setText(String.format(Locale.US, "%.2f", result));

                        hig_1_2 = Double.parseDouble(String.valueOf(high_13));
                        if (high_2 != null && high_2.length() >= 1)
                            hig_2 = Double.parseDouble(high_2.getText().toString());
                        result2 = hig_1_2 * hig_2;
                        t_high_2.setText(String.format(Locale.US, "%.2f", result2));

                        hig_1_3 = Double.parseDouble(String.valueOf(high_15));
                        if (high_3 != null && high_3.length() >= 1)
                            hig_3 = Double.parseDouble(high_3.getText().toString());
                        result3 = hig_1_3 * hig_3;
                        t_high_3.setText(String.format(Locale.US, "%.2f", result3));

                        hig_1_4 = Double.parseDouble(String.valueOf(high_17));
                        if (high_4 != null && high_4.length() >= 1)
                            hig_4 = Double.parseDouble(high_4.getText().toString());
                        result4 = hig_1_4 * hig_4;
                        t_high_4.setText(String.format(Locale.US, "%.2f", result4));

                        hig_1_5 = Double.parseDouble(String.valueOf(high_19));
                        if (high_5 != null && high_5.length() >= 1)
                            hig_5 = Double.parseDouble(high_5.getText().toString());
                        result5 = hig_1_5 * hig_5;
                        t_high_5.setText(String.format(Locale.US, "%.2f", result5));

///-----------------------Lead Calculation---------------------///
                        led_1_1 = Double.parseDouble(String.valueOf(lead_11));
                        if (lead_1 != null && lead_1.length() >= 1)
                            led_1 = Double.parseDouble(lead_1.getText().toString());
                        lead1 = led_1_1 * led_1;
                        t_lead_1.setText(String.format(Locale.US, "%.2f", lead1));

                        led_1_2 = Double.parseDouble(String.valueOf(lead_21));
                        if (lead_2 != null && lead_2.length() >= 1)
                            led_2 = Double.parseDouble(lead_2.getText().toString());
                        lead2 = led_1_2 * led_2;
                        t_lead_2.setText(String.format(Locale.US, "%.2f", lead2));

                        led_1_3 = Double.parseDouble(String.valueOf(lead_31));
                        if (lead_3 != null && lead_3.length() >= 1)
                            led_3 = Double.parseDouble(lead_3.getText().toString());
                        lead3 = led_1_3 * led_3;
                        t_lead_3.setText(String.format(Locale.US, "%.2f", lead3));

                        led_1_4 = Double.parseDouble(String.valueOf(lead_41));
                        if (lead_4 != null && lead_4.length() >= 1)
                            led_4 = Double.parseDouble(lead_4.getText().toString());
                        lead4 = led_1_4 * led_4;
                        t_lead_4.setText(String.format(Locale.US, "%.2f", lead4));

///------------------ Main---calculation----------------------///
                        Double head = 0.0, over = 0.0, day_aftnorm, dslab1 = 0.0, dslab2 = 0.0, dslab3, dbag = 0.0, adbag = 0.0,
                                dslab4, forotbag, aotbag = 0.0;
                        if (headkey != null && headkey.length() >= 1)
                            head = Double.parseDouble(headkey);
                        if (otkey != null && otkey.length() >= 1)
                            over = Double.parseDouble(otkey);

///----------------Bags-----Distribution--------------------///
                        if (headkey != null && headkey.length() >= 1) if (over >= 0 && over < 7) {
                            head = Double.parseDouble(headkey);
                            dbag = ((all_bags / head) / (7 + over)) * 7;
                            day_bag.setText(String.format(Locale.US, "%.2f", dbag));
                            Double otbag = ((all_bags / head) / (7 + over)) * over;
                            ot_bag.setText(String.format(Locale.US, "%.2f", otbag));
                        } else if (headkey != null && headkey.length() >= 1) if (over == null) {
                            head = Double.parseDouble(String.valueOf(headkey));
                            dbag = (all_bags / head);
                            day_bag.setText(String.format(Locale.US, "%.2f", dbag));
                            Double otbag = 0.0;
                            ot_bag.setText(String.format(Locale.US, "%.2f", otbag));
                        } else if (headkey != null && headkey.length() >= 1) if (over >= 7) {
                            head = Double.parseDouble(headkey);
                            dbag = 0.0;
                            day_bag.setText(String.format(Locale.US, "%.2f", dbag));
                            Double otbag = all_bags / head;
                            ot_bag.setText(String.format(Locale.US, "%.2f", otbag));
                        }
///--------------------day_bag---after norm---------------------///
                        if (day_bag != null && day_bag.length() >= 1) if (dbag <= normalll) {
                            adbag = 0.0;
                            day_aft_norm.setText(String.format(Locale.US, "%.2f", adbag));
                        } else if (day_bag != null && day_bag.length() >= 1) if (dbag > normalll) {
                            adbag = dbag - normalll;
                            day_aft_norm.setText(String.format(Locale.US, "%.2f", adbag));
                        }

///--------------------ot_bag---after norm---------------------///
                        forotbag = Double.parseDouble(ot_bag.getText().toString());
                        if (ot_bag != null && ot_bag.length() >= 1) if (forotbag > over * ott_normall) {
                            aotbag = forotbag - (over * ott_normall);
                            ot_aft_norm.setText(String.format(Locale.US, "%.2f", aotbag));
                        } else if (ot_bag != null && ot_bag.length() >= 1)
                            if (forotbag < over * ott_normall) {
                                aotbag = 0.0;
                                ot_aft_norm.setText(String.format(Locale.US, "%.2f", aotbag));
                            }

///--------------------ot_bag---slabs---------------------///
                        if (day_aft_norm != null && day_aft_norm.length() >= 1) if (adbag <= 30) {
                            day_aftnorm = Double.parseDouble(day_aft_norm.getText().toString());
                            dslab1 = day_aftnorm;
                            day_slab_1.setText(String.format(Locale.US, "%.2f", dslab1));
                        } else if (day_aft_norm != null && day_aft_norm.length() >= 1)
                            if (adbag >= 30) {
                                dslab1 = 30.00;
                                day_slab_1.setText(String.format(Locale.US, "%.2f", dslab1));
                            }
                        //----------------------------//
                        if (day_aft_norm != null && day_aft_norm.length() >= 1) if (adbag <= 60) {
                            day_aftnorm = Double.parseDouble(day_aft_norm.getText().toString());
                            dslab2 = day_aftnorm - dslab1;
                            day_slab_2.setText(String.format(Locale.US, "%.2f", dslab2));
                        } else if (day_aft_norm != null && day_aft_norm.length() >= 1)
                            if (adbag >= 60) {
                                dslab2 = 30.00;
                                day_slab_2.setText(String.format(Locale.US, "%.2f", dslab2));
                            }
                        //----------------------------//
                        if (day_aft_norm != null && day_aft_norm.length() >= 1) if (adbag <= 90) {
                            day_aftnorm = Double.parseDouble(day_aft_norm.getText().toString());
                            dslab3 = day_aftnorm - (dslab1 + dslab2);
                            day_slab_3.setText(String.format(Locale.US, "%.2f", dslab3));
                        } else if (day_aft_norm != null && day_aft_norm.length() >= 1)
                            if (adbag >= 90) {
                                dslab3 = 30.00;
                                day_slab_3.setText(String.format(Locale.US, "%.2f", dslab3));
                            }
                        //----------------------------//
                        if (day_aft_norm != null && day_aft_norm.length() >= 1) if (adbag >= 90) {
                            dslab4 = adbag - 90;
                            day_slab_4.setText(String.format(Locale.US, "%.2f", dslab4));
                        } else if (day_aft_norm != null && day_aft_norm.length() >= 1)
                            if (adbag <= 90) {
                                dslab4 = 0.0;
                                day_slab_4.setText(String.format(Locale.US, "%.2f", dslab4));
                            }
///-----------------ot_bag----Slabs----------------///
                        Double otslab1 = 0.0, otslab2 = 0.0, otslab3, otslab4;

                        if (ot_aft_norm != null && ot_aft_norm.length() >= 1)
                            if (aotbag >= over * 5) {
                                otslab1 = over * 5;
                                ot_slab_1.setText(String.format(Locale.US, "%.2f", otslab1));
                            } else {
                                if (ot_aft_norm != null && ot_aft_norm.length() >= 1)
                                    if (aotbag <= over * 5)
                                        otslab1 = aotbag;
                                ot_slab_1.setText(String.format(Locale.US, "%.2f", otslab1));
                            }
                        //----------------------------//
                        if (ot_aft_norm != null && ot_aft_norm.length() >= 1)
                            if (aotbag <= over * 10) {
                                otslab2 = aotbag - otslab1;
                                ot_slab_2.setText(String.format(Locale.US, "%.2f", otslab2));
                            } else if (ot_aft_norm != null && ot_aft_norm.length() >= 1)
                                if (aotbag >= over * 10) {
                                    otslab2 = over * 5;
                                    ot_slab_2.setText(String.format(Locale.US, "%.2f", otslab2));
                                }
                        //-----------------------------//
                        if (ot_aft_norm != null && ot_aft_norm.length() >= 1)
                            if (aotbag <= over * 15) {
                                otslab3 = aotbag - (otslab1 + otslab2);
                                ot_slab_3.setText(String.format(Locale.US, "%.2f", otslab3));
                            } else if (ot_aft_norm != null && ot_aft_norm.length() >= 1)
                                if (aotbag >= over * 15) {
                                    otslab3 = over * 5;
                                    ot_slab_3.setText(String.format(Locale.US, "%.2f", otslab3));
                                }
                        //-----------------------------//
                        if (ot_aft_norm != null && ot_aft_norm.length() >= 1)
                            if (aotbag > over * 15) {
                                otslab4 = aotbag - (over * 15);
                                ot_slab_4.setText(String.format(Locale.US, "%.2f", otslab4));
                            } else if (ot_aft_norm != null && ot_aft_norm.length() >= 1)
                                if (aotbag <= over * 15) {
                                    otslab4 = 0.0;
                                    ot_slab_4.setText(String.format(Locale.US, "%.2f", otslab4));
                                }

///------------Bag--Slab--Values--Day-----///
                        Double slb_1, slb_2, slb_3, slb_4, slb_1a = 0.0, slb_2a = 0.0, slb_3a = 0.0, slb_4a = 0.0;

                        slb_1 = Double.parseDouble(String.valueOf(slab_1dbr));
                        if (day_slab_1 != null && day_slab_1.length() >= 1)
                            slb_1a = Double.parseDouble(day_slab_1.getText().toString());
                        Double slb_11a = slb_1 * slb_1a;
                        day_slab_1R.setText((String.format(Locale.US, "%.2f", slb_11a) + " Rs."));

                        slb_2 = Double.parseDouble(String.valueOf(slab_2dbr));
                        if (day_slab_2 != null && day_slab_2.length() >= 1)
                            slb_2a = Double.parseDouble(day_slab_2.getText().toString());
                        Double slb_12a = slb_2 * slb_2a;
                        day_slab_2R.setText((String.format(Locale.US, "%.2f", slb_12a) + " Rs."));

                        slb_3 = Double.parseDouble(String.valueOf(slab_3dbr));
                        if (day_slab_3 != null && day_slab_3.length() >= 1)
                            slb_3a = Double.parseDouble(day_slab_3.getText().toString());
                        Double slb_13a = slb_3 * slb_3a;
                        day_slab_3R.setText((String.format(Locale.US, "%.2f", slb_13a) + " Rs."));

                        slb_4 = Double.parseDouble(String.valueOf(slab_4dbr));
                        if (day_slab_4 != null && day_slab_4.length() >= 1)
                            slb_4a = Double.parseDouble(day_slab_4.getText().toString());
                        Double slb_14a = slb_4 * slb_4a;
                        day_slab_4R.setText((String.format(Locale.US, "%.2f", slb_14a) + " Rs."));
///------------Bag--Slab--Values--ot-----///
                        Double slb_1otr, slb_2otr, slb_3otr, slb_4otr, slb_1b = 0.0, slb_2b = 0.0, slb_3b = 0.0, slb_4b = 0.0;
                        Double slb_11otr, slb_12otr, slb_13otr, slb_14otr;
                        slb_1otr = Double.parseDouble(String.valueOf(slab_1dbr));
                        if (ot_slab_1 != null && ot_slab_1.length() >= 1)
                            slb_1b = Double.parseDouble(ot_slab_1.getText().toString());
                        slb_11otr = slb_1otr * slb_1b;
                        ot_slab_1R.setText((String.format(Locale.US, "%.2f", slb_11otr) + " Rs."));

                        slb_2otr = Double.parseDouble(String.valueOf(slab_2dbr));
                        if (ot_slab_2 != null && ot_slab_2.length() >= 1)
                            slb_2b = Double.parseDouble(ot_slab_2.getText().toString());
                        slb_12otr = slb_2otr * slb_2b;
                        ot_slab_2R.setText((String.format(Locale.US, "%.2f", slb_12otr) + " Rs."));

                        slb_3otr = Double.parseDouble(String.valueOf(slab_3dbr));
                        if (ot_slab_3 != null && ot_slab_3.length() >= 1)
                            slb_3b = Double.parseDouble(ot_slab_3.getText().toString());
                        slb_13otr = slb_3otr * slb_3b;
                        ot_slab_3R.setText((String.format(Locale.US, "%.2f", slb_13otr) + " Rs."));

                        slb_4otr = Double.parseDouble(String.valueOf(slab_4dbr));
                        if (ot_slab_4 != null && ot_slab_4.length() >= 1)
                            slb_4b = Double.parseDouble(ot_slab_4.getText().toString());
                        slb_14otr = slb_4otr * slb_4b;
                        ot_slab_4R.setText((String.format(Locale.US, "%.2f", slb_14otr) + " Rs."));

                        ///----------------Total--Slab--Value-------///
                        Double d_slab_velu, ot_slab_velu, total_valu, high_velu, lead_velu, gross, over_time;

                        d_slab_velu = slb_11a + slb_12a + slb_13a;
                        day_s_value.setText((String.format(Locale.US, "%.2f", d_slab_velu) + " Rs."));
                        ot_slab_velu = slb_11otr + slb_12otr + slb_13otr;
                        ot_s_value.setText((String.format(Locale.US, "%.2f", ot_slab_velu) + " Rs."));

                        total_valu = (slb_14a + slb_14otr + d_slab_velu + ot_slab_velu);
                        total.setText((String.format(Locale.US, "%.2f", total_valu) + " Rs."));

                        high_velu = (result + result2 + result3 + result4 + result5) / head;
                        high.setText((String.format(Locale.US, "%.2f", high_velu) + " Rs."));

                        lead_velu = (lead1 + lead2 + lead3 + lead4) / head;
                        lead.setText((String.format(Locale.US, "%.2f", lead_velu) + " Rs."));

                        gross = total_valu + high_velu + lead_velu;
                        tvgross.setText((String.format(Locale.US, " %.2f ", gross) + "Rs."));

                        ///--------------Over Time velue according new rate(new basic)-------------////
                        Double perdaydbl;
                        perdaydbl = Double.parseDouble(String.valueOf(perday_rate_new));
                        over_time= ((perdaydbl/7)*1.1)*over;
                        ///--------------Over Time velue according old rate(Old basic)-------------////
                        //over_time = ((((led_1_4 * 1.1) * normalll) / 7) * over);
                        overtime.setText((String.format(Locale.US, " %.2f ", over_time) + "Rs."));
                    }
                });
            }
        }
    }


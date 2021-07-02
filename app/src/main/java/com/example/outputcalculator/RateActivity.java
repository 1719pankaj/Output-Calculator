package com.example.outputcalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.database.Cursor;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Locale;

public class RateActivity extends AppCompatActivity {
    Animation anim = new AlphaAnimation(0.0f, 1.0f);
    EditText  basic, da, house, days;
    EditText  basicold, daold, houseold;
    TextView basik,id, dearness, hra, perday, perbag;
    TextView basikold, dearnessold, hraold, perdayold;
    TextView slab_a, slab_b, slab_c, slab_d,
            hight_a,hight_b,hight_c,hight_d,hight_e,lead_a,lead_b,lead_c,lead_d;
    Button save;
    Double perbagg;
    DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rate);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        id = findViewById(R.id.etID);
        id.setInputType(InputType.TYPE_NULL);
        basic = findViewById(R.id.etBASIC);
        da = findViewById(R.id.etDA);
        house = findViewById(R.id.etHra);
        days = findViewById(R.id.etDAYS);

        basicold = findViewById(R.id.etBasicOld);
        daold = findViewById(R.id.etDaOld);
        houseold = findViewById(R.id.etHraOld);

        slab_a =findViewById(R.id.tvSlab_a);
        slab_b =findViewById(R.id.tvSlab_b);
        slab_c =findViewById(R.id.tvSlab_c);
        slab_d =findViewById(R.id.tvSlab_d);

        hight_a =findViewById(R.id.tvH1);
        hight_b =findViewById(R.id.tvH2);
        hight_c =findViewById(R.id.tvH3);
        hight_d =findViewById(R.id.tvH4);
        hight_e =findViewById(R.id.tvH5);

        lead_a =findViewById(R.id.tvL1);
        lead_b =findViewById(R.id.tvL2);
        lead_c =findViewById(R.id.tvL3);
        lead_d =findViewById(R.id.tvL4);

        save = findViewById(R.id.btSave);

        final TextView perday_rate = new TextView(this);

        databaseHelper = new DatabaseHelper(this);

        TextWatcher textWatcher = new TextWatcher() {

            @Override
            public void afterTextChanged(Editable s) {
                calcResult();
            }

            private void calcResult() throws NumberFormatException {
                double bba = 0.0, dda = 0.0, day = 0.0, hha = 0.0, bbaa, ddaa, hraa, perdayy;

                Editable editableText1 = basic.getText(),
                        editableText2 = da.getText(),
                        editableText3 = house.getText(),
                        editableText4 = days.getText();

                if (editableText1 != null && editableText1.length() >= 1)
                    bba = Double.parseDouble(editableText1.toString());
                if (editableText2 != null && editableText2.length() >= 1)
                    dda = Double.parseDouble(editableText2.toString());
                if (editableText3 != null && editableText3.length() >= 1)
                    hha = Double.parseDouble(editableText3.toString());
                if (editableText4 != null && editableText4.length() >= 1)
                    day = Double.parseDouble(editableText4.toString());

                double bbaold = 0.0, ddaold = 0.0, hhaold = 0.0, bbaaold, ddaaold, hraaold, perdayyold;
                Editable editableText1old = basicold.getText(),
                        editableText2old = daold.getText(),
                        editableText3old = houseold.getText();
                if (editableText1old != null && editableText1old.length() >= 1)
                    bbaold = Double.parseDouble(editableText1old.toString());
                if (editableText2old != null && editableText2old.length() >= 1)
                    ddaold = Double.parseDouble(editableText2old.toString());
                if (editableText3old != null && editableText3old.length() >= 1)
                    hhaold = Double.parseDouble(editableText3old.toString());

                basik = findViewById(R.id.tvBasic);
                bbaa = bba / 26;
                basik.setText((String.format(Locale.US, "%.3f", bbaa) + " Rs."));
                dearness = findViewById(R.id.tvDa);
                ddaa = (((bba * 12) / 100) * dda) / day;
                dearness.setText((String.format(Locale.US, "%.3f", ddaa) + " Rs."));
                hra = findViewById(R.id.tvHra);
                hraa = (((bba * 12) / 100) * hha) / day;
                hra.setText((String.format(Locale.US, "%.3f", hraa) + " Rs."));
                perday = findViewById(R.id.tvPerDay);
                perdayy = bbaa + ddaa + hraa;
                perday.setText((String.format(Locale.US, "%.3f", perdayy) + " Rs."));
                perbag = findViewById(R.id.tvPerBag);
                perbagg = perdayy / 135;
                perbag.setText((String.format(Locale.US, "%.3f", perbagg) + " Rs."));
                Double slbA, slbB, slbC, slbD, highA, highB, highC, highD, highE, leadA, leadB, leadC, leadD;

                basikold = findViewById(R.id.tvBasicOld);
                bbaaold = bbaold / 26;
                basikold.setText((String.format(Locale.US, "%.3f", bbaaold) + " Rs."));
                dearnessold = findViewById(R.id.tvDaOld);
                ddaaold = (((bbaold * 12) / 100) * ddaold) / day;
                dearnessold.setText((String.format(Locale.US, "%.3f", ddaaold) + " Rs."));
                hraold = findViewById(R.id.tvHraOld);
                hraaold = (((bbaold * 12) / 100) * hhaold) / day;
                hraold.setText((String.format(Locale.US, "%.3f", hraaold) + " Rs."));
                perdayold = findViewById(R.id.tvPerDayOld);
                perdayyold = bbaaold + ddaaold + hraaold;
                perdayold.setText((String.format(Locale.US, "%.3f", perdayyold) + " Rs."));
                perday_rate.setText((String.format(Locale.US, "%.3f", perdayyold)));

                // SLAB---

                slbA = ((perbagg / 100) * 8) + perbagg;
                slab_a.setText(String.format(Locale.US, "%.3f", slbA));
                slbB = ((perbagg / 100) * 15) + perbagg;
                slab_b.setText(String.format(Locale.US, "%.3f", slbB));
                slbC = ((perbagg / 100) * 35) + perbagg;
                slab_c.setText(String.format(Locale.US, "%.3f", slbC));
                slbD = ((perbagg / 100) * 50) + perbagg;
                slab_d.setText(String.format(Locale.US, "%.3f", slbD));
                // HIGH---
                highA = (perbagg / 100) * 10;
                hight_a.setText(String.format(Locale.US, "%.3f", highA));
                highB = (perbagg / 100) * 25;
                hight_b.setText(String.format(Locale.US, "%.3f", highB));
                highC = (perbagg / 100) * 30;
                hight_c.setText(String.format(Locale.US, "%.3f", highC));
                highD = (perbagg / 100) * 40;
                hight_d.setText(String.format(Locale.US, "%.3f", highD));
                highE = (perbagg / 100) * 50;
                hight_e.setText(String.format(Locale.US, "%.3f", highE));
                //LEAD--
                leadA = (perbagg / 100) * 15;
                lead_a.setText(String.format(Locale.US, "%.3f", leadA));
                leadB = (perbagg / 100) * 30;
                lead_b.setText(String.format(Locale.US, "%.3f", leadB));
                leadC = (perbagg / 100) * 50;
                lead_c.setText(String.format(Locale.US, "%.3f", leadC));
                leadD = (perbagg / 100) * 100;
                lead_d.setText(String.format(Locale.US, "%.3f", leadD));
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
        };
        basic.addTextChangedListener(textWatcher);
        da.addTextChangedListener(textWatcher);
        house.addTextChangedListener(textWatcher);
        days.addTextChangedListener(textWatcher);

        basicold.addTextChangedListener(textWatcher);
        daold.addTextChangedListener(textWatcher);
        houseold.addTextChangedListener(textWatcher);

        String update="UPDATE";
        {                                     //To SAVE Data in Database-----
            databaseHelper = new DatabaseHelper(this);

            save.setOnClickListener(view -> {
                String basics,das,houses,dayss,basicolds,daolds,houseolds;
                basics=basic.getText().toString();
                das=da.getText().toString();
                houses=house.getText().toString();
                dayss=days.getText().toString();
                basicolds=basicold.getText().toString();
                daolds=daold.getText().toString();
                houseolds=houseold.getText().toString();

                if (basics.equalsIgnoreCase("") || das.equalsIgnoreCase("")|| houses.equalsIgnoreCase("")|| dayss.equalsIgnoreCase("")||
                        basicolds.equalsIgnoreCase("")|| daolds.equalsIgnoreCase("")|| houseolds.equalsIgnoreCase("")){
                    Toast.makeText(RateActivity.this, "Fill all data", Toast.LENGTH_SHORT).show();
                }else {
                    boolean result = databaseHelper.insertdata(Integer.parseInt(id.getText().toString()), basic.getText().toString(),
                            Double.parseDouble(da.getText().toString()), Double.parseDouble(house.getText().toString()), Integer.parseInt(days.getText().toString()),
                            basicold.getText().toString(),Double.parseDouble(daold.getText().toString()), Double.parseDouble(houseold.getText().toString()), Double.parseDouble(perday_rate.getText().toString()),
                            Double.parseDouble(slab_a.getText().toString()), Double.parseDouble(slab_b.getText().toString()), Double.parseDouble(slab_c.getText().toString()), Double.parseDouble(slab_d.getText().toString()),
                            Double.parseDouble(hight_a.getText().toString()), Double.parseDouble(hight_b.getText().toString()), Double.parseDouble(hight_c.getText().toString()),
                            Double.parseDouble(hight_d.getText().toString()), Double.parseDouble(hight_e.getText().toString()), Double.parseDouble(lead_a.getText().toString()),
                            Double.parseDouble(lead_b.getText().toString()), Double.parseDouble(lead_c.getText().toString()), Double.parseDouble(lead_d.getText().toString()));
                    if (result)
                    {
                        Toast.makeText(RateActivity.this, "DATA INSERTED", Toast.LENGTH_SHORT).show();
                        save.setText(update);
                        DatabaseHelper1 databaseHelper1;
                        databaseHelper1 = new DatabaseHelper1(getApplicationContext());
                        Cursor cursor = databaseHelper1.getAllData();
                        if (cursor.getCount() == 0) {
                            Intent intent=new Intent(RateActivity.this,WorkCategoryActivity.class);//////////////////////////
                            startActivity(intent);
                            finish();
                            return;

                        }
                    } else
                        Toast.makeText(RateActivity.this, "DATA NOT INSERTED", Toast.LENGTH_SHORT).show();
                }
            });
        }

        {                                                        //To UPDATE Data in Database-----
            databaseHelper = new DatabaseHelper(this);

            Cursor cursor = databaseHelper.getAllData();
            while (cursor.moveToNext()) {
                save.setOnClickListener(view -> {
                    String basics,das,houses,dayss,basicolds,daolds,houseolds;
                    basics=basic.getText().toString();
                    das=da.getText().toString();
                    houses=house.getText().toString();
                    dayss=days.getText().toString();
                    basicolds=basicold.getText().toString();
                    daolds=daold.getText().toString();
                    houseolds=houseold.getText().toString();
                    if (basics.equalsIgnoreCase("") || das.equalsIgnoreCase("")|| houses.equalsIgnoreCase("")|| dayss.equalsIgnoreCase("")||
                            basicolds.equalsIgnoreCase("")|| daolds.equalsIgnoreCase("")|| houseolds.equalsIgnoreCase("")){
                        Toast.makeText(RateActivity.this, "Fill all data", Toast.LENGTH_SHORT).show();
                    }else {
                        boolean result = databaseHelper.insertdata(Integer.parseInt(id.getText().toString()), basic.getText().toString(),
                                Double.parseDouble(da.getText().toString()), Double.parseDouble(house.getText().toString()), Integer.parseInt(days.getText().toString()),
                                basicold.getText().toString(),Double.parseDouble(daold.getText().toString()), Double.parseDouble(houseold.getText().toString()), Double.parseDouble(perday_rate.getText().toString()),
                                Double.parseDouble(slab_a.getText().toString()), Double.parseDouble(slab_b.getText().toString()),Double.parseDouble(slab_c.getText().toString()), Double.parseDouble(slab_d.getText().toString()),
                                Double.parseDouble(hight_a.getText().toString()), Double.parseDouble(hight_b.getText().toString()), Double.parseDouble(hight_c.getText().toString()),
                                Double.parseDouble(hight_d.getText().toString()), Double.parseDouble(hight_e.getText().toString()), Double.parseDouble(lead_a.getText().toString()),
                                Double.parseDouble(lead_b.getText().toString()), Double.parseDouble(lead_c.getText().toString()), Double.parseDouble(lead_d.getText().toString()));
                        if (result) {
                            Toast.makeText(RateActivity.this, "DATA UPDATED", Toast.LENGTH_SHORT).show();
                        }else
                            Toast.makeText(RateActivity.this, "DATA NOT UPDATED", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        }

        {
            databaseHelper = new DatabaseHelper(this);

            Cursor cursor = databaseHelper.getAllData();
            if (cursor.getCount() == 0) {
                anim.setDuration(400);
                anim.setRepeatMode(Animation.REVERSE);
                anim .setRepeatCount(Animation.INFINITE);
                save.startAnimation(anim);
                return;
            }

            while (cursor.moveToNext()) {
                id.setText(cursor.getString(0));
                basic.setText(cursor.getString(1));
                da.setText(cursor.getString(2));
                house.setText(cursor.getString(3));
                days.setText(cursor.getString(4));
                basicold.setText(cursor.getString(5));
                daold.setText(cursor.getString(6));
                houseold.setText(cursor.getString(7));
            }
            save.setText(update);
        }
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent=new Intent(RateActivity.this,Calculate1Activity.class);
        startActivity(intent);
        finish();
    }
}
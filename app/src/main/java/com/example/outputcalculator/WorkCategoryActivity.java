package com.example.outputcalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class WorkCategoryActivity extends AppCompatActivity {
    EditText idd,truckunload,wagontoshed,wagontopf,pftoshed,truckloading,trucktopf,shedtowagon,wagontotruck,refill,restack,weight,norm,otnorm;
    Button save;
    String update="UPDATE";
    DatabaseHelper1 databaseHelper1;
    Animation anim = new AlphaAnimation(0.0f, 1.0f);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_work_category);
        idd=findViewById(R.id.etIdd);
        truckunload=findViewById(R.id.etTruck_unload);
        wagontoshed=findViewById(R.id.etWagon_Unload);
        wagontopf=findViewById(R.id.etWagon_Platform);
        pftoshed=findViewById(R.id.etPlatform_Shed);
        truckloading=findViewById(R.id.etTruck_Load);
        trucktopf=findViewById(R.id.etTruck_Platform);
        shedtowagon=findViewById(R.id.etShed_Wagon);
        wagontotruck=findViewById(R.id.etWagon_Truck);
        refill=findViewById(R.id.etRefill);
        restack=findViewById(R.id.etRestack);
        weight=findViewById(R.id.etWeight);
        norm=findViewById(R.id.etNormal);
        otnorm=findViewById(R.id.etOtnorms);
        save= findViewById(R.id.button1);

        databaseHelper1 = new DatabaseHelper1(this);
        {                                                    ////To SAVE Data in Database-----////
            save.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    boolean result = databaseHelper1.insertdata(Integer.parseInt(idd.getText().toString()), Short.parseShort(truckunload.getText().toString()), Short.parseShort(wagontoshed.getText().toString()),
                            Short.parseShort(wagontopf.getText().toString()), Short.parseShort(pftoshed.getText().toString()), Short.parseShort(truckloading.getText().toString()),
                            Short.parseShort(trucktopf.getText().toString()), Short.parseShort(shedtowagon.getText().toString()), Short.parseShort(wagontotruck.getText().toString()),
                            Short.parseShort(refill.getText().toString()), Short.parseShort(restack.getText().toString()), Short.parseShort(weight.getText().toString()),
                            Short.parseShort(norm.getText().toString()), Short.parseShort(otnorm.getText().toString())
                    );
                    if (result) {
                        save.clearAnimation();
                        Toast.makeText(WorkCategoryActivity.this, "DATA INSERTED", Toast.LENGTH_SHORT).show();
                        save.setText(update);

                    } else
                        Toast.makeText(WorkCategoryActivity.this, "DATA NOT INSERTED", Toast.LENGTH_SHORT).show();
                }
            });
        }
        {                                                        //To UPDATE Data in Database-----//
            databaseHelper1 = new DatabaseHelper1(this);
            Cursor cursor = databaseHelper1.getAllData();
            while (cursor.moveToNext()) {
                save.setOnClickListener(view -> {
                        boolean result = databaseHelper1.insertdata(Integer.parseInt(idd.getText().toString()), Short.parseShort(truckunload.getText().toString()), Short.parseShort(wagontoshed.getText().toString()),
                                Short.parseShort(wagontopf.getText().toString()), Short.parseShort(pftoshed.getText().toString()), Short.parseShort(truckloading.getText().toString()),
                                Short.parseShort(trucktopf.getText().toString()), Short.parseShort(shedtowagon.getText().toString()), Short.parseShort(wagontotruck.getText().toString()),
                                Short.parseShort(refill.getText().toString()), Short.parseShort(restack.getText().toString()), Short.parseShort(weight.getText().toString()),
                                Short.parseShort(norm.getText().toString()), Short.parseShort(otnorm.getText().toString())
                        );
                        if (result) {
                            save.clearAnimation();
                            Toast.makeText(WorkCategoryActivity.this, "DATA UPDATED", Toast.LENGTH_SHORT).show();

                        } else
                            Toast.makeText(WorkCategoryActivity.this, "DATA NOT UPDATED", Toast.LENGTH_SHORT).show();
                });
            }
        }
        {
            databaseHelper1 = new DatabaseHelper1(this);
            Cursor cursor = databaseHelper1.getAllData();
            if (cursor.getCount() == 0) {
                anim.setDuration(400);
                anim.setRepeatMode(Animation.REVERSE);
                anim .setRepeatCount(Animation.INFINITE);
                save.startAnimation(anim);
                save.requestFocus();
                return;
            }
            while (cursor.moveToNext()) {
                idd.setText(cursor.getString(0));
                truckunload.setText(cursor.getString(1));
                wagontoshed.setText(cursor.getString(2));
                wagontopf.setText(cursor.getString(3));
                pftoshed.setText(cursor.getString(4));
                truckloading.setText(cursor.getString(5));
                trucktopf.setText(cursor.getString(6));
                shedtowagon.setText(cursor.getString(7));
                wagontotruck.setText(cursor.getString(8));
                refill.setText(cursor.getString(9));
                restack.setText(cursor.getString(10));
                weight.setText(cursor.getString(11));
                norm.setText(cursor.getString(12));
                otnorm.setText(cursor.getString(13));
            }
            save.setText(update);
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent=new Intent(WorkCategoryActivity.this,Calculate1Activity.class);
        startActivity(intent);
        finish();
    }
}

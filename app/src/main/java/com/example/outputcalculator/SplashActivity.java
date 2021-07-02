package com.example.outputcalculator;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.widget.Toast;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activitysplash);
        if (checkREADPERMISSION()){
            Thread timer = new Thread() {
                @Override
                public void run() {
                    super.run();
                    try {
                        sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } finally {
                        Intent intent = new Intent(SplashActivity.this, Calculate1Activity.class);
                        startActivity(intent);
                        finish();
                    }
                }
            };
            timer.start();

        }else {
            ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.READ_PHONE_STATE} , 16);
        }


    }

    private boolean checkREADPERMISSION() {
        int result = ActivityCompat.checkSelfPermission(this,Manifest.permission.READ_PHONE_STATE );
        return result == PackageManager.PERMISSION_GRANTED;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode){
            case 16:
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED){

                    Thread timer = new Thread() {
                        @Override
                        public void run() {
                            super.run();
                            try {
                                sleep(1000);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            } finally {
                                Intent intent = new Intent(SplashActivity.this, Calculate1Activity.class);
                                startActivity(intent);
                                finish();
                            }

                        }
                    };
                    timer.start();

                    Toast.makeText(this,"PERMISSION GRANTED" ,Toast.LENGTH_SHORT ).show();
                }else{
                    checkREADPERMISSION();
                    //IMEI.setText("Permission needed for next step!");
                }
                break;
        }
    }
}

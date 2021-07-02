package com.example.outputcalculator;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.telephony.TelephonyManager;
import android.view.ContextThemeWrapper;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Va_lidate extends AppCompatActivity {
    View contact_layout, layout_validate ;
    TextView Myname,mynumber,testContact;
    EditText data,contact;
    String paco;
    Button save_contact,next,back,save_code,send;
    SharedPreferences sharedPreferences;
    String str,strr,shareobject,contacto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_va_lidate);
        contact_layout=findViewById(R.id.lo_Contact);
        layout_validate=findViewById(R.id.lo_Validate);

        Myname= findViewById(R.id.tvName);
        mynumber= findViewById(R.id.tvNumer);

        testContact= findViewById(R.id.tvTestconyact);/////////////////////////

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

        ActivityCompat.requestPermissions(Va_lidate.this,new String[]{
                Manifest.permission.READ_PHONE_STATE,Manifest.permission.READ_PHONE_NUMBERS,
                Manifest.permission.READ_PHONE_STATE,Manifest.permission.READ_PHONE_STATE}, 121);

        testContact.setText(tMmanager.getLine1Number());

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
        Integer getcodetext=(let1 * let2 + let3 * let4 - let5);
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
            Intent intent=new Intent(Va_lidate.this,Calculate1Activity.class);
            startActivity(intent);
            layout_validate.setVisibility(View.INVISIBLE);
            contact_layout.setVisibility(View.INVISIBLE);
        }else
        {contact_layout.setVisibility(View.VISIBLE);
            layout_validate.setVisibility(View.INVISIBLE);
            }

        save_contact.setOnClickListener(v -> {
            Intent intent = new Intent(ContactsContract.Intents.Insert.ACTION);
            intent.setType(ContactsContract.RawContacts.CONTENT_TYPE);
            intent.putExtra(ContactsContract.Intents.Insert.NAME, Myname.getText());
            intent.putExtra(ContactsContract.Intents.Insert.PHONE, mynumber.getText());
            startActivity(intent);
        });

        next.setOnClickListener(v -> {
            contact_layout.setVisibility(View.INVISIBLE);
            layout_validate.setVisibility(View.VISIBLE);
        });

        back.setOnClickListener(v -> {
            contact_layout.setVisibility(View.VISIBLE);
            layout_validate.setVisibility(View.INVISIBLE);
        });

       save_code.setOnClickListener(v -> {
           String pass = data.getText().toString();
           if (pass.equals(code1)) {
               SharedPreferences.Editor edit = sharedPreferences.edit();
               edit.putInt("pas", Integer.valueOf(pass));
               edit.apply();
           Toast.makeText(Va_lidate.this, "Congratulation", Toast.LENGTH_SHORT).show();
               Intent intent=new Intent(Va_lidate.this,Calculate1Activity.class);
               startActivity(intent);
           } else
               Toast.makeText(Va_lidate.this, "Incorrect password", Toast.LENGTH_LONG).show();
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
    }
}
package com.example.signupform;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Result extends Activity {

    Button btnExt;
    TextView receiver_username;
    TextView receiver_password;
    TextView receiver_birthday;
    TextView receiver_gender;
    TextView receiver_hobbies;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.resultform);

        receiver_username = (TextView)findViewById(R.id.txtUsername);
        receiver_password = (TextView)findViewById(R.id.txtPassword);
        receiver_birthday = (TextView)findViewById(R.id.txtBirthdate);
        receiver_gender = (TextView)findViewById(R.id.txtGender);
        receiver_hobbies = (TextView)findViewById(R.id.txtHobbies);
        btnExt=(Button)findViewById(R.id.btnExit);

        Intent intent = getIntent();
        Bundle reBundle=new Bundle();
        reBundle=intent.getExtras();

        // display the string into textView
        receiver_username.setText(reBundle.getString("name_key"));
        receiver_password.setText(reBundle.getString("pass_key"));
        receiver_birthday.setText(reBundle.getString("birthday_key"));
        receiver_gender.setText(reBundle.getString("gender_key"));
        receiver_hobbies.setText(reBundle.getString("hobbies_key"));

        btnExt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finishAffinity();
                System.exit(0);
            }
        });
    }
}
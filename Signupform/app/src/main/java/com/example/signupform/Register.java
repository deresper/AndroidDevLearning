package com.example.signupform;



import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.content.Context;
import android.text.TextWatcher;
import android.widget.LinearLayout;
import android.app.DatePickerDialog;import android.widget.DatePicker;
import android.widget.Toast;

import java.util.Calendar;
public class Register extends Activity {

    private Context context;
    //PLUMBING: Pairing GUI controls with Java objects
    private Button btnSelect;
    private Button btnReset;
    private Button btnSignUp;
    private EditText edtUserName;
    private EditText edtPassword;
    private EditText edtRetype;
    private EditText edtDate;
    private RadioGroup radGenderType;
    private RadioButton radMale;
    private RadioButton radFeMale;
    private CheckBox cbTeniis, cbFutbal, cbOther;
    private LinearLayout myScreen;
    private TextWatcher watcher;
    private int duration = Toast.LENGTH_SHORT;

    // new birthday
    private EditText editTextDate;
    private int lastSelectedYear;
    private int lastSelectedMonth;
    private int lastSelectedDayOfMonth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registerform);

        Button signup_button = (Button) findViewById(R.id.btnSignUp);
        Button reset_button = (Button) findViewById(R.id.btnReset);
        Button select_button = (Button) findViewById(R.id.btnSelect);
        EditText name_text = (EditText) findViewById(R.id.username_fill);
        EditText pass_text = (EditText) findViewById(R.id.password_fill);
        editTextDate = (EditText) findViewById(R.id.birthday_fill);
        EditText retype_text = (EditText) findViewById(R.id.retype_fill);

        radGenderType = (RadioGroup) findViewById(R.id.radioGroup1);
        radMale = (RadioButton) findViewById(R.id.radMale);
        radFeMale = (RadioButton) findViewById(R.id.radFeMale);

        cbTeniis = (CheckBox) findViewById(R.id.cbTeniis);
        cbFutbal = (CheckBox) findViewById(R.id.cbFutbal);
        cbOther = (CheckBox) findViewById(R.id.cbOther);
        Intent seIntent = new Intent(Register.this, Result.class);


        select_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buttonSelectDate();
            }
        });

        final Calendar c = Calendar.getInstance();
        lastSelectedYear = c.get(Calendar.YEAR);
        lastSelectedMonth = c.get(Calendar.MONTH);
        lastSelectedDayOfMonth = c.get(Calendar.DAY_OF_MONTH);


        signup_button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {


                String name = name_text.getText().toString();
                String pass = "";
                int i = 0;
                while (i < pass_text.getText().toString().length()) {
                    pass += "*";
                    i++;
                }
                ;
                String birthdate = editTextDate.getText().toString();


                String hobi = "";
                if (cbTeniis.isChecked()) hobi += " Teniis";
                if (cbFutbal.isChecked()) hobi += " Futbal";
                if (cbOther.isChecked()) hobi += " Other";

                String gender = "";
                int radioId = radGenderType.getCheckedRadioButtonId();
                if (radMale.getId() == radioId) gender = "Male";
                else if (radFeMale.isChecked()) gender = "Female";



                if ( pass_text.getText().toString().equals(retype_text.getText().toString())==false) {
                    Toast.makeText(getBaseContext(), "Reason can not be blank", Toast.LENGTH_SHORT).show();
                }
                else {

                    Bundle seBundle = new Bundle();

                    seBundle.putString("name_key", name);
                    seBundle.putString("pass_key", pass);
                    seBundle.putString("birthday_key", birthdate);
                    seBundle.putString("gender_key", gender);
                    seBundle.putString("hobbies_key", hobi);
                    seIntent.putExtras(seBundle);

                    startActivity(seIntent);
                }

            }
        });

        reset_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                name_text.setText("");
                pass_text.setText("");
                retype_text.setText("");
                editTextDate.setText("");

                radMale.setChecked(false);
                radFeMale.setChecked(false);

                cbFutbal.setChecked(false);
                cbTeniis.setChecked(false);
                cbOther.setChecked(false);
            }
        });
    }


    //click
    private void buttonSelectDate() {

        // Date Select Listener.
        DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year,
                                  int monthOfYear, int dayOfMonth) {

                lastSelectedYear = year;
                lastSelectedMonth = monthOfYear;
                lastSelectedDayOfMonth = dayOfMonth;

                editTextDate.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);


            }
        };

        DatePickerDialog datePickerDialog = null;


        // Calendar Mode (Default):
        {
            datePickerDialog = new DatePickerDialog(this,
                    dateSetListener, lastSelectedYear, lastSelectedMonth, lastSelectedDayOfMonth);
        }

        // Show
        datePickerDialog.show();
    }
}
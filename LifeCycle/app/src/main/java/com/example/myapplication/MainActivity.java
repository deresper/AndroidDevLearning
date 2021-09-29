package com.example.myapplication;

import java.util.Locale;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.content.Context;

public class MainActivity extends Activity {
    //class variables

    private Context context;
    private int duration = Toast.LENGTH_SHORT;
    //PLUMBING: Pairing GUI controls with Java objects
    private Button btnExit;
    private EditText txtColorSelected;
    private TextView txtSpyBox;
    private LinearLayout myScreen;
    private TextWatcher watcher;
    private String PREFNAME = "myPrefFile1";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //display the main screen
        setContentView(R.layout.activity_main);
        //wiring GUI controls and matching Java objects
        txtColorSelected = (EditText) findViewById(R.id.editText1);
        btnExit = (Button) findViewById(R.id.button);
        txtSpyBox = (TextView) findViewById(R.id.textView);
        myScreen = (LinearLayout) findViewById(R.id.myScreen);
        //set GUI listeners, watchers,...
        btnExit.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        //observe (text) changes made to EditText box (color selection)
        txtColorSelected.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) { /* nothing TODO, needed by interface */ }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) { /* nothing TODO, needed by interface */ }

            @Override
            public void afterTextChanged(Editable s) {
                //set background to selected color
                String chosenColor = s.toString().toLowerCase(Locale.US);
                txtSpyBox.setText(chosenColor);
                setBackgroundColor(chosenColor, myScreen);
            }
        });
    //show the current state's name
        context = getApplicationContext();
        Toast.makeText(context, "onCreate", duration).show();
    } //onCreate

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Toast.makeText(context, "onDestroy", duration).show();
    }

    @Override
    protected void onPause() {
        super.onPause();
        //save state data (background color) for future use
        String chosenColor = txtSpyBox.getText().toString();
        saveStateData(chosenColor);
        Toast.makeText(context, "onPause", duration).show();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Toast.makeText(context, "onRestart", duration).show();
    }

    @Override
    protected void onResume() {
        super.onResume();
        Toast.makeText(context, "onResume", duration).show();
    }

    @Override
    protected void onStart() {
        super.onStart();
        //if appropriate, change background color to chosen value
        updateMeUsingSavedStateData();
        Toast.makeText(context, "onStart", duration).show();
    }

    @Override
    protected void onStop() {
        super.onStop();
        Toast.makeText(context, "onStop", duration).show();
    }

    private void setBackgroundColor(String chosenColor, LinearLayout myScreen) {
        // Note: Van = Nguyen Van Dat, Dat = Le Tien Dat
        if (chosenColor.contains("dat")) myScreen.setBackgroundColor(0xff120189);
        if (chosenColor.contains("van")) myScreen.setBackgroundColor(0xff120190);
        if (chosenColor.contains("hanh")) myScreen.setBackgroundColor(0xff120218);
        if (chosenColor.contains("hao")) myScreen.setBackgroundColor(0xff120220);
        if (chosenColor.contains("khang")) myScreen.setBackgroundColor(0xff120252);
    } //setBackgroundColor

    private void saveStateData(String chosenColor) {
        //this is a little <key,value> table permanently kept in memory
        SharedPreferences myPrefContainer = getSharedPreferences(PREFNAME, Activity.MODE_PRIVATE);
        //pair <key,value> to be stored represents our 'important' data
        SharedPreferences.Editor myPrefEditor = myPrefContainer.edit();
        String key = "chosenBackgroundColor", value = txtSpyBox.getText().toString();
        myPrefEditor.putString(key, value);
        myPrefEditor.commit();
    }//saveStateData

    private void updateMeUsingSavedStateData() {
        // (in case it exists) use saved data telling backg color
        SharedPreferences myPrefContainer = getSharedPreferences(PREFNAME, Activity.MODE_PRIVATE);
        String key = "chosenBackgroundColor";
        String defaultValue = "white";
        if ((myPrefContainer != null) && myPrefContainer.contains(key)) {
            String color = myPrefContainer.getString(key, defaultValue);
            setBackgroundColor(color, myScreen);
        }
    }//updateMeUsingSavedStateData
} //Activity
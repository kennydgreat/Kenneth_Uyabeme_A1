package com.example.kenny.kenneth_uyabeme_a1;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

public class MainActivity extends Activity {
    //FIELDS

    //-------------------------
    //Spinners and respective TextViews
    //-----------------
    Spinner tip_Spinner;
    TextView tip_TextView;
    Spinner numOfPeopleSpinner;
    TextView numOfPeopleTextView;
    //-----------------

    //EditText
    EditText enterAmount_editText;

    //Button
    Button clear_Button;


    //Button listener Class
    private class ClearButtonListener implements View.OnClickListener {

        @Override
        public void onClick(View view) {
            enterAmount_editText.setText("");
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //  Getting reference to spinners and textViews
        tip_Spinner = findViewById(R.id.tip_Spinner);
        tip_TextView = findViewById(R.id.tip_TextView);
        numOfPeopleSpinner = findViewById(R.id.numOfPeopleSpinner);
        numOfPeopleTextView = findViewById(R.id.numOfPeopleTextView);

        //Getting reference to editText
        enterAmount_editText = findViewById(R.id.enterAmount_editText);

        //Getting reference to Button
        clear_Button = findViewById(R.id.clear_Button);
        ClearButtonListener buttonListener = new ClearButtonListener();
        clear_Button.setOnClickListener(buttonListener);

        //String array containing spinner integer data
        String[] tipSpinnerData = getResources().getStringArray(R.array.TipSpinnerData);
        String[] numofPeopleSpinnerData = getResources().getStringArray(R.array.NumberOfPeopleSpinnerData);


        //Creating AraayAdapters
        ArrayAdapter<String> tipSpinnerAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, tipSpinnerData);
        ArrayAdapter<String> numofPplSpinnerAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, numofPeopleSpinnerData);
        tipSpinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        numofPplSpinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        //Assigning adapters to respective spinners
        tip_Spinner.setAdapter(tipSpinnerAdapter);
        numOfPeopleSpinner.setAdapter(numofPplSpinnerAdapter);


    }
}

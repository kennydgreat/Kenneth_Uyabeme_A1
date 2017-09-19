package com.example.kenny.kenneth_uyabeme_a1;

import android.app.Activity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
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
    TextView totalAmountDisplay;
    //-----------------

    //EditText
    EditText enterAmount_editText;

    //Button
    Button clear_Button;

    //CheckBox
    CheckBox hst_checkBox;


    //Button listener Class
    private class ClearButtonListener implements View.OnClickListener {

        @Override
        public void onClick(View view) {
            //Clearing enterAmountEditText
            enterAmount_editText.setText("");
            //Set spinners to the first option .i.e "10" for tip_Spinner and "1" for numOfPeopleSpinner
            tip_Spinner.setSelection(0);
            numOfPeopleSpinner.setSelection(0);
            //Unchecking hst_checkBox
            hst_checkBox.setChecked(false);

        }
    }

    // EditText Listener Class
    private class AmountEditTextListener implements TextWatcher {

        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void afterTextChanged(Editable editable) {
            calculateBill();
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
        totalAmountDisplay = findViewById(R.id.totalTextDisplay);

        //Getting reference to editText and it's listener
        enterAmount_editText = findViewById(R.id.enterAmount_editText);
        AmountEditTextListener amountEditTextListener = new AmountEditTextListener();
        enterAmount_editText.addTextChangedListener(amountEditTextListener);
        //Getting reference to Button
        clear_Button = findViewById(R.id.clear_Button);
        //Create button listener and setting it to clear_button
        ClearButtonListener buttonListener = new ClearButtonListener();
        clear_Button.setOnClickListener(buttonListener);

        //Getting reference to checkBox
        hst_checkBox = findViewById(R.id.hst_checkBox);

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

    /**
     * This Method
     * 1) Gets the amount, tip, Number of people
     * 2) Displays the tip, total and the per person amount
     */
    public void calculateBill() {
        double amount, tipAoumnt, tipPercent, total, numOfPeople, hst;
        String tipDisplayString = getString(R.string.tipDisplayString);
        String totalAmountString = getString(R.string.totalAmountString);
        hst = 1.13; // This is to make calculation for total+hst quicker

        /* The following if and else block is to solve a bug which causes the
        app to crash. The bug is reproduceable when the editText listener calls the calculateBill
        and the method tries get the text in the EditText but there is no text.
        */
        if (enterAmount_editText.getText().toString().equals("")) {
            amount = 0;
        } else amount = Double.valueOf(enterAmount_editText.getText().toString());
        total = amount * hst;

        //Getting the tipPercent, tipAmount and numOfPeople
        tipPercent = Double.valueOf(tip_Spinner.getSelectedItem().toString()) / 100;
        numOfPeople = Double.valueOf(numOfPeopleSpinner.getSelectedItem().toString());

        //The following if and else handles cases when the HST checkbox is check or unchecked
        if (hst_checkBox.isChecked()) tipAoumnt = total * tipPercent;
        else tipAoumnt = amount * tipPercent;

        //Updating the tip_Dispaly and totalAmount display
        tip_TextView.setText(String.format(tipDisplayString, tipAoumnt));
        totalAmountDisplay.setText(String.format(totalAmountString, total));

        //Condition for if there is more than 1 person
        if (numOfPeople > 1) {


        } else {
        }


    }
}

package com.example.kenny.kenneth_uyabeme_a1;

import android.app.Activity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
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

    //Display TextViews
    TextView tip_TextViewDisplay;
    TextView totalAmountDisplay;
    TextView amountPerPersonDisplay;
    //-----------------

    //EditText
    EditText enterAmount_editText;

    //Button
    Button clear_Button;

    //CheckBox
    CheckBox hst_checkBox;


    //Button named inner listener Class
    private class ClearButtonListener implements View.OnClickListener {

        @Override
        public void onClick(View view) {
            //Clearing enterAmountEditText, and display TextViews
            enterAmount_editText.setText("");
            tip_TextViewDisplay.setText("");
            totalAmountDisplay.setText("");
            amountPerPersonDisplay.setText("");
            //Set spinners to the first option .i.e "10" for tip_Spinner and "1" for numOfPeopleSpinner
            tip_Spinner.setSelection(0);
            numOfPeopleSpinner.setSelection(0);
            //Unchecking hst_checkBox
            hst_checkBox.setChecked(false);

        }
    }

    // EditText named inner Listener Class
    private class AmountEditTextListener implements TextWatcher {

        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            //This method isn't needed
        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            //This method isn't needed
        }

        @Override
        public void afterTextChanged(Editable editable) {
            //After a text is inputted or removed calculate the Bill
            calculateBill();
        }
    }

    //Named inner class of hst checkbox listener
    private class HSTcheckBoxListener implements CompoundButton.OnClickListener {

        @Override
        public void onClick(View view) {
            //Calculate the bill whenever the checkbox is clicked
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
        amountPerPersonDisplay = findViewById(R.id.amountPerPerson);
        tip_TextViewDisplay = findViewById(R.id.tipTextDisplay);

        //Getting reference to editText and it's listener
        enterAmount_editText = findViewById(R.id.enterAmount_editText);
        AmountEditTextListener amountEditTextListener = new AmountEditTextListener();
        enterAmount_editText.addTextChangedListener(amountEditTextListener);

        //Getting reference to Button
        clear_Button = findViewById(R.id.clear_Button);
        //Create button listener and setting it to clear_button
        final ClearButtonListener buttonListener = new ClearButtonListener();
        clear_Button.setOnClickListener(buttonListener);

        //Getting reference to checkBox and attaching listener
        hst_checkBox = findViewById(R.id.hst_checkBox);
        HSTcheckBoxListener hsTcheckButtonListener = new HSTcheckBoxListener();
        hst_checkBox.setOnClickListener(hsTcheckButtonListener);

        //String array containing spinner string data
        String[] tipSpinnerData = getResources().getStringArray(R.array.TipSpinnerData);
        String[] numofPeopleSpinnerData = getResources().getStringArray(R.array.NumberOfPeopleSpinnerData);


        //Creating AraayAdapters
        ArrayAdapter<String> tipSpinnerAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, tipSpinnerData);
        ArrayAdapter<String> numofPplSpinnerAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, numofPeopleSpinnerData);
        tipSpinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        numofPplSpinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        //Assigning adapters to respective spinners and setting spinner OnItemSelectedlisteners
        //----------------------------------------
        tip_Spinner.setAdapter(tipSpinnerAdapter);
        numOfPeopleSpinner.setAdapter(numofPplSpinnerAdapter);
        tip_Spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                //Calculate the bill whenever an item is selected
                calculateBill();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                //This method is not needed
            }
        });
        numOfPeopleSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                //Calculate the bill whenever an item is selected
                calculateBill();
            }


            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                //This method is not needed
            }
        });

    }

    /**
     * This Method
     * 1)Is called by all listeners
     * 2) Gets the amount, tip, Number of people
     * 3) Calculates the bill and displays the tip, total and the per person amount
     * 4)Is called by all listens
     */
    public void calculateBill() {
        //Local variables
        double amount, tipAoumnt, tipPercent, total, numOfPeople, hst, amountPerPerson;
        String tipDisplayString = getString(R.string.tipDisplayString);
        String amountPerPersonString = getString(R.string.amountPerPersonString);



        /* The following if and else block is make sure nothing happens whenever
        the editText is empty and gets the amount inputted by the use it isn't
        */
        if (enterAmount_editText.getText().toString().equals("")) {
            return; // exit the method if there is nothing in the editText
        } else amount = Double.valueOf(enterAmount_editText.getText().toString());

        //-------------------------------------
        //Getting HST and setting the total
        hst = amount * 0.13;
        total = amount * 1.13; //1.13 used to make the HST + amount calculation quicker

        //Getting the tipPercent, tipAmount and numOfPeople and setting amountPerPerson
        tipPercent = Double.valueOf(tip_Spinner.getSelectedItem().toString()) / 100;
        numOfPeople = Double.valueOf(numOfPeopleSpinner.getSelectedItem().toString());

        //The following if and else block handles cases when the HST checkbox is check or unchecked
        if (hst_checkBox.isChecked()) {
            tipAoumnt = total * tipPercent; //if checked make sure the tip is from the HST + amount
            total = total + tipAoumnt;
        } else {
            tipAoumnt = amount * tipPercent;
            total = total + tipAoumnt;
        }
        //Setting the amount per Person
        amountPerPerson = total / numOfPeople;
        //Updating the tip_Display and totalAmount display
        tip_TextViewDisplay.setText(String.format(tipDisplayString, tipAoumnt));
        totalAmountDisplay.setText(String.format("Your total is: $%.2f ($%.2f HST)", total, hst));
        //You said the above usage of String literals in code is OK.

        //Condition for if there is more than 1 person
        if (numOfPeople > 1) {
            amountPerPersonDisplay.setText(String.format(amountPerPersonString, amountPerPerson));
        } else amountPerPersonDisplay.setText("");

    }
}

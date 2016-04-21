package com.example.brianita.proyectofinalandroidstudyjam;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    EditText res_sp1, res_sp2, res_sp3;
    TextView result_sp, logsp;
    float serieparallel;
    CheckBox selec_serie, selec_parallel;

    EditText res_sd1, res_sd2, res_sd3;
    EditText res_ds12, res_ds23, res_ds13;
    CheckBox selec_stardelta, selec_deltastar;
    TextView logsd;
    float re1, re2, re3;
    float r12, r13, r23;
    int deltastar;

    EditText res_m1, res_m2, vol_m1, vol_m2;
    TextView res_rm, vol_vm;
    TextView logm;

    EditText res_ohm, vol_ohm, curr_ohm;
    CheckBox selec_resistance, selec_voltage, selec_current;
    int ohm;
    float ohmc;
    TextView logohm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        res_sp1 = (EditText) findViewById(R.id.res_sp1);
        res_sp2 = (EditText) findViewById(R.id.res_sp2);
        res_sp3 = (EditText) findViewById(R.id.res_sp3);
        result_sp = (TextView) findViewById(R.id.result_sp);
        selec_serie = (CheckBox) findViewById(R.id.selec_serie);
        selec_parallel = (CheckBox) findViewById(R.id.selec_parallel);
        logsp = (TextView) findViewById(R.id.logsp);


        res_sd1 = (EditText) findViewById(R.id.res_sd1);
        res_sd2 = (EditText) findViewById(R.id.res_sd2);
        res_sd3 = (EditText) findViewById(R.id.res_sd3);
        res_ds12 = (EditText) findViewById(R.id.res_ds12);
        res_ds23 = (EditText) findViewById(R.id.res_ds23);
        res_ds13 = (EditText) findViewById(R.id.res_ds13);
        selec_stardelta = (CheckBox) findViewById(R.id.selec_stardelta);
        selec_deltastar = (CheckBox) findViewById(R.id.selec_deltastar);
        logsd = (TextView) findViewById(R.id.logsd);


        res_m1 = (EditText) findViewById(R.id.res_m1);
        res_m2 = (EditText) findViewById(R.id.res_m2);
        vol_m1 = (EditText) findViewById(R.id.vol_m1);
        vol_m2 = (EditText) findViewById(R.id.vol_m2);
        res_rm = (TextView) findViewById(R.id.res_rm);
        vol_vm = (TextView) findViewById(R.id.vol_vm);
        logm = (TextView) findViewById(R.id.logm);

        res_ohm = (EditText) findViewById(R.id.res_ohm);
        vol_ohm = (EditText) findViewById(R.id.vol_ohm);
        curr_ohm = (EditText) findViewById(R.id.curr_ohm);
        logohm = (TextView) findViewById(R.id.logohm);
        selec_resistance = (CheckBox) findViewById(R.id.selec_resistance);
        selec_voltage = (CheckBox) findViewById(R.id.selec_voltage);
        selec_current = (CheckBox) findViewById(R.id.selec_current);

    }

    /* Method
    * ==============================================================================================
    *----------------------------->  METHOD SELECTSERIEORPARALLEL  <--------------------------------
    *this method reads if selected series or parallel box controls that exist in the data entered
    *EDITTEXT. performs mathematical operations to obtain the equivalent series resistance or parallel.
    * ==============================================================================================
    * */
    public void selectSerieOrParallel(View view) {

        if (!((res_sp1.getText().toString().equals("")) || (res_sp2.getText().toString().equals("")) || (res_sp3.getText().toString().equals("")))) {
            float r1 = Float.valueOf(res_sp1.getText().toString());
            float r2 = Float.valueOf(res_sp2.getText().toString());
            float r3 = Float.valueOf(res_sp3.getText().toString());
            float x = 1;
            switch (view.getId()) {
                case R.id.selec_serie:
                    selec_parallel.setChecked(false);
                    serieparallel = r1 + r2 + r3;
                    break;
                case R.id.selec_parallel:
                    selec_serie.setChecked(false);
                    x = 1;
                    serieparallel = x / ((x / r1) + (x / r2) + (x / r3));
                    break;
            }

            logsp.setText("Right values! press the button calculate");
            logsp.setTextColor(Color.parseColor("#0da40d"));
        } else {

            logsp.setText("type the resistor values in Ω Please");
            logsp.setTextColor(Color.parseColor("#FF5722"));
            selec_parallel.setChecked(false);
            selec_serie.setChecked(false);
        }
    }

    /*
    * ==============================================================================================
    *----------------------------->  METODO CALCULATESERIEPARALLEL  <-------------------------------
    *this method, enter the result of the operation to the field of  resistance equivalent after
    * pressing the botton calculate
    * ==============================================================================================
    * */
    public void calculateSerieParallel(View view) {

        result_sp.setText("" + serieparallel + " Ω");
    }

    /*
    * ==============================================================================================
    *------------------------------>       METHOD CLEARSP         <---------------------------------
    *deletes the contents of the fields so that the user can enter other values to calculate
    * ==============================================================================================
    * */
    public void clearsp(View view) {
        serieparallel = 0;
        selec_serie.setChecked(false);
        selec_parallel.setChecked(false);
        res_sp1.setText("");
        res_sp2.setText("");
        res_sp3.setText("");
        result_sp.setText("" + serieparallel + " Ω");
    }

    /*
    * ==============================================================================================
    *------------------------------>   METHOD SELECTDELTASTAR     <---------------------------------
    *this method reads if selected Stra-Delta or Delta-Star box controls that exist in the data
    * entered EDITTEXT. performs mathematical operations to obtain the equivalent s resistance
    * Star-Delta or Delta-Star.
    * ==============================================================================================
    * */
    public void selectDeltaStar(View view) {

        switch (view.getId()) {

            case R.id.selec_stardelta: {
                selec_deltastar.setChecked(false);
                if (!((res_sd1.getText().toString().equals("")) || (res_sd2.getText().toString().equals("")) || (res_sd3.getText().toString().equals("")))) {
                    float r1 = Float.valueOf(res_sd1.getText().toString());
                    float r2 = Float.valueOf(res_sd2.getText().toString());
                    float r3 = Float.valueOf(res_sd3.getText().toString());
                    r12 = ((r1 * r2) + (r1 * r3) + (r2 * r3)) / (r3);
                    r13 = ((r1 * r2) + (r1 * r3) + (r2 * r3)) / (r2);
                    r23 = ((r1 * r2) + (r1 * r3) + (r2 * r3)) / (r1);
                    deltastar = 0;
                    logsd.setText("Right values! press the button calculate");
                    logsd.setTextColor(Color.parseColor("#0da40d"));
                } else {
                    logsd.setText("type the resistor values in Ω Please");
                    logsd.setTextColor(Color.parseColor("#FF5722"));
                    selec_stardelta.setChecked(false);
                    selec_deltastar.setChecked(false);

                }
            }

            break;
            case R.id.selec_deltastar: {
                selec_stardelta.setChecked(false);
                if (!((res_ds12.getText().toString().equals("")) || (res_ds13.getText().toString().equals("")) || (res_ds23.getText().toString().equals("")))) {
                    float re12 = Float.valueOf(res_ds12.getText().toString());
                    float re13 = Float.valueOf(res_ds13.getText().toString());
                    float re23 = Float.valueOf(res_ds23.getText().toString());
                    re1 = (re12 * re13) / (re12 + re13 + re23);
                    re2 = (re12 * re23) / (re12 + re13 + re23);
                    re3 = (re13 * re23) / (re12 + re13 + re23);
                    deltastar = 1;
                    logsd.setText("Right values! press the button calculate");
                    logsd.setTextColor(Color.parseColor("#0da40d"));
                } else {
                    logsd.setText("type the resistor values in Ω Please");
                    logsd.setTextColor(Color.parseColor("#FF5722"));
                    selec_stardelta.setChecked(false);
                    selec_deltastar.setChecked(false);

                }
            }
            break;

        }

    }

    /*
    * ==============================================================================================
    *------------------------------>  METHOD CALCULATEDELTASTAR   <---------------------------------
    *It shows the result of equivalent  resistance to Delta or Star
    * ==============================================================================================
    * */
    public void calculateDeltaStar(View view) {

        if (deltastar == 0) {
            res_ds12.setText("" + r12);
            res_ds13.setText("" + r13);
            res_ds23.setText("" + r23);
        } else {
            res_sd1.setText("" + re1);
            res_sd2.setText("" + re2);
            res_sd3.setText("" + re3);

        }

    }

    /*
    * ==============================================================================================
    *------------------------------>       METHOD CLEARDS         <---------------------------------
    *clean the contents of the fields used
    * ==============================================================================================
    * */
    public void cleards(View view) {
        res_ds12.setText("");
        res_ds13.setText("");
        res_ds23.setText("");
        res_sd1.setText("");
        res_sd2.setText("");
        res_sd3.setText("");
        re1 = re2 = re3 = r12 = r13 = r23 = 0;
        selec_stardelta.setChecked(false);
        selec_deltastar.setChecked(false);
    }

    /*
    * ==============================================================================================
    *------------------------------>   METHOD CALCULATEMILLMAN    <---------------------------------
    *verifies that there are no empty fields, obtains data, performs mathematical operations to
    * calculate the resistance and voltage equivalent Millman.
    * It shows the result after pressing the button calculate
    * ==============================================================================================
    * */
    public void calculateMillman(View view) {
        if (!((res_m1.getText().toString().equals("")) || (res_m2.getText().toString().equals("")) || (vol_m1.getText().toString().equals("")) || (vol_m2.getText().toString().equals("")))) {
            float r1 = Float.valueOf(res_m1.getText().toString());
            float r2 = Float.valueOf(res_m2.getText().toString());
            float v1 = Float.valueOf(vol_m1.getText().toString());
            float v2 = Float.valueOf(vol_m2.getText().toString());
            float x = 1;
            float rm = x / ((x / r1) + (x / r2));
            float vm = ((v1 / r1) + (v2 / r2)) / ((x / r1) + (x / r2));
            res_rm.setText("" + rm + " Ω");
            vol_vm.setText("" + vm + " V");
            logm.setText("Right values!");
            logm.setTextColor(Color.parseColor("#0da40d"));
        } else {

            logm.setText("Please, type the resistor and volage values ");
            logm.setTextColor(Color.parseColor("#FF5722"));

        }
    }

    /*
    * ==============================================================================================
    *------------------------------>      METHOD CLEARMILLMAN     <---------------------------------
    *delete the contents of the fields used to calculate the equivalent circuit millman
    * ==============================================================================================
    * */
    public void clearMillman(View view) {
        res_m1.setText("");
        res_m2.setText("");
        vol_m1.setText("");
        vol_m2.setText("");
        res_rm.setText("0 Ω");
        vol_vm.setText("0 V");
    }

    /*
    * ==============================================================================================
    *------------------------------>       METHOD SELECTOHM       <---------------------------------
    *Choose electrical parameter that you want to calculate, verify that there is data in the fields,
    * performs mathematical operations: I ignored to calculate the variable.
    * ==============================================================================================
    * */
    public void selectOhm(View view) {
        float v, i, r;
        switch (view.getId()) {

            case R.id.selec_resistance: {
                selec_voltage.setChecked(false);
                selec_current.setChecked(false);
                if (!((vol_ohm.getText().toString().equals("")) || (curr_ohm.getText().toString().equals("")))) {
                    v = Float.valueOf(vol_ohm.getText().toString());
                    i = Float.valueOf(curr_ohm.getText().toString());
                    ohmc = v / i;
                    ohm = 1;
                    logohm.setText("Right values!press the button calculate");
                    logohm.setTextColor(Color.parseColor("#0da40d"));
                } else {
                    logohm.setText("Please type the  values  ");
                    logohm.setTextColor(Color.parseColor("#FF5722"));
                    selec_resistance.setChecked(false);
                    selec_voltage.setChecked(false);
                    selec_current.setChecked(false);
                }
            }

            break;
            case R.id.selec_voltage: {

                selec_resistance.setChecked(false);
                selec_current.setChecked(false);
                if (!((res_ohm.getText().toString().equals("")) || (curr_ohm.getText().toString().equals("")))) {
                    r = Float.valueOf(res_ohm.getText().toString());
                    i = Float.valueOf(curr_ohm.getText().toString());
                    ohmc = i * r;
                    ohm = 2;
                    logohm.setText("Right values!press the button calculate");
                    logohm.setTextColor(Color.parseColor("#0da40d"));
                } else {
                    logohm.setText("Please type the  values");
                    logohm.setTextColor(Color.parseColor("#FF5722"));
                    selec_resistance.setChecked(false);
                    selec_voltage.setChecked(false);
                    selec_current.setChecked(false);
                }
            }
            break;
            case R.id.selec_current: {

                selec_resistance.setChecked(false);
                selec_voltage.setChecked(false);
                if (!((res_ohm.getText().toString().equals("")) || (vol_ohm.getText().toString().equals("")))) {
                    r = Float.valueOf(res_ohm.getText().toString());
                    v = Float.valueOf(vol_ohm.getText().toString());
                    ohmc = v / r;
                    ohm = 3;
                    logohm.setText("Right values!press the button calculate");
                    logohm.setTextColor(Color.parseColor("#0da40d"));
                } else {
                    logohm.setText("Please type the  values  ");
                    logohm.setTextColor(Color.parseColor("#FF5722"));
                    selec_resistance.setChecked(false);
                    selec_voltage.setChecked(false);
                    selec_current.setChecked(false);
                }
            }

            break;
        }
    }

    /*
    * ==============================================================================================
    *------------------------------>     METHOD CALCULATEOHM      <---------------------------------
    *shows the parameter value sought
    * ==============================================================================================
    * */
    public void calcularOhm(View view) {
        if (ohm == 1)
            res_ohm.setText("" + ohmc);
        else {
            if (ohm == 2)
                vol_ohm.setText("" + ohmc);
            else
                curr_ohm.setText("" + ohmc);
        }
    }

    /*
    * ==============================================================================================
    *------------------------------>       METHOD CLEAROHM        <---------------------------------
    *clean the contents of the fields used
    * ==============================================================================================
    * */
    public void clearOhm(View view) {
        selec_resistance.setChecked(false);
        selec_voltage.setChecked(false);
        selec_current.setChecked(false);
        res_ohm.setText("");
        vol_ohm.setText("");
        curr_ohm.setText("");
    }

    /*
    * ==============================================================================================
    *------------------------------>        METHOD EXIT           <---------------------------------
    *exit app
    * ==============================================================================================
    * */
    public void exit(View v) {
        finish();
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);

    }

}


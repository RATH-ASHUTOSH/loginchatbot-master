package com.example.vmac.WatBot.calori_calculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.vmac.WatBot.R;

import java.lang.reflect.Array;

public class cal_calculator extends AppCompatActivity {

    EditText age,height,weight;
    TextView result;
    RadioButton male,female;
    RadioGroup radioGroup;
    Spinner activity;
    Button submit;
    private double calorie,acti;
    private int w,h,a,gender;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cal_calculator);

        age=findViewById(R.id.getage);
        height=findViewById(R.id.getheight);
        weight=findViewById(R.id.getweight);
        male=findViewById(R.id.male);
        female=findViewById(R.id.female);
        activity=findViewById(R.id.getactivity);
        radioGroup=findViewById(R.id.gender);
        result=findViewById(R.id.result);
        submit=findViewById(R.id.submit);

        final double[] array={1,1.2,1.375,1.465,1.55,1.725,1.9};

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,R.array.activity_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        activity.setAdapter(adapter);

        activity.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                acti=array[i];
                Log.d("value", "activity: "+acti+" "+array[i]);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                Toast.makeText(cal_calculator.this, "Please select activity", Toast.LENGTH_SHORT).show();
            }
        });

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (i){
                    case R.id.male:
                        gender=1;
                        break;
                    case R.id.female:
                        gender=0;
                        break;
                }
            }
        });

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                a=Integer.valueOf(String.valueOf(age.getText()));
                w=Integer.parseInt(weight.getText().toString());
                h=Integer.parseInt(String.valueOf(height.getText()));

                Log.d("value", "onClick: "+a+w+h);

                if (gender == 1){
                    calorie=(((10*w)+(6.25*h)-(5*a)+5)*acti);
                    Log.d("value", "male: "+a+w+h+calorie+acti);
                }
                else if(gender == 0){
                    calorie=(((10*w)+(6.25*h)-(5*a)-161)*acti);
                    Log.d("value", "female: "+a+w+h+calorie+acti);
                }
                result.setText("You require "+calorie+" calories/day to maintain your current weight.");
            }
        });


    }
}

package com.vaibhavs.depthoffieldcalculator.UI;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.vaibhavs.depthoffieldcalculator.Model.Calculator;
import com.vaibhavs.depthoffieldcalculator.Model.Lens;
import com.vaibhavs.depthoffieldcalculator.R;

import java.text.DecimalFormat;

public class DOFCalculator extends AppCompatActivity {

    private static final String MAKE = "Canon";
    private static final String FOCAL_LENGTH = "200";
    private static final String APERTURE = "2.8";
    Lens ln;

    private String formatM(double distanceInM){
        DecimalFormat df = new DecimalFormat("0.00");
        return df.format(distanceInM);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_d_o_f_calculator);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar ab = getSupportActionBar();
        ab.setDisplayHomeAsUpEnabled(true);
        GetData();
        Display_Lens();
        Calculate();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu Mu){
        getMenuInflater().inflate(R.menu.menu_back,Mu);
        return true;
    }

    @Override
    public  boolean onOptionsItemSelected(MenuItem item){
        switch(item.getItemId()) {
            case android.R.id.home:
                Toast.makeText(DOFCalculator.this,"Pressed cancel!",Toast.LENGTH_SHORT).show();
                finish();
            default:
                return super.onOptionsItemSelected(item);
        }
    }


    private void Display_Lens() {
        TextView disp = (TextView)findViewById(R.id.text_display);
        String len_info = ln.getMake() + " " + ln.getFocal_length() + "mm F" + ln.getMaximum_aperture();
        disp.setText(len_info);
    }

    private void Calculate() {
        Button Calc_btn = (Button) findViewById(R.id.btn_Calc);
        Calc_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText coc_et = (EditText) findViewById(R.id.input_CoC);
                double COC =  Double.parseDouble(coc_et.getText().toString());
                EditText dist_et = (EditText) findViewById(R.id.input_Distance);
                double Distance = Double.parseDouble(dist_et.getText().toString());
                EditText aper_et = (EditText) findViewById(R.id.input_Aperature);
                double Apert = Double.parseDouble(aper_et.getText().toString());

                //textview
                TextView NFocal_dist = (TextView) findViewById(R.id.text_NFDistance);
                TextView FFocal_dist = (TextView) findViewById(R.id.text_FFDistance);
                TextView DoField = (TextView) findViewById(R.id.text_DoF);
                TextView HFocal_dist = (TextView) findViewById(R.id.text_HFDistance);

                if(COC <= 0.0) {
                    NFocal_dist.setText("Invalid Circle of Confusion");
                    FFocal_dist.setText("Invalid Circle of Confusion");
                    DoField.setText("Invalid Circle of Confusion");
                    HFocal_dist.setText("Invalid Circle of Confusion");
                } else if (Distance <= 0.0 ) {
                    NFocal_dist.setText("Invalid Distance to Subject");
                    FFocal_dist.setText("Invalid Distance to Subject");
                    DoField.setText("Invalid Distance to Subject");
                    HFocal_dist.setText("Invalid Distance to Subject");
                } else if (Apert < 1.4 || Apert < ln.getMaximum_aperture() ) {
                    NFocal_dist.setText("Invalid Aperture");
                    FFocal_dist.setText("Invalid Aperture");
                    DoField.setText("Invalid Aperture");
                    HFocal_dist.setText("Invalid Aperture");
                } else {
                    Calculator lens = new Calculator(ln,Apert,Distance,COC);
                    NFocal_dist.setText(formatM(lens.Calc_Near_Focalpoint())+"m");
                    FFocal_dist.setText(formatM(lens.Calc_Far_Focalpoint())+"m");
                    DoField.setText(formatM(lens.Calc_Depth_of_Field())+"m");
                    HFocal_dist.setText(formatM(lens.Calc_Hyperfocal_Distance())+"m");
                }
            }
        });
    }

    public static Intent makeLaunchIntent(Context c, Lens lens){
        Intent intent = new Intent(c, DOFCalculator.class);
        intent.putExtra(MAKE,lens.getMake());
        intent.putExtra(FOCAL_LENGTH,lens.getFocal_length());
        intent.putExtra(APERTURE,lens.getMaximum_aperture());
        return intent;
    }

    private void GetData() {
        Intent intent = getIntent();
        String DOF_make = intent.getStringExtra(MAKE);
        int DOF_focalLength = intent.getIntExtra(FOCAL_LENGTH,0);
        double DOF_aperture = intent.getDoubleExtra(APERTURE,0.0);
        ln = new Lens(DOF_make,DOF_aperture,DOF_focalLength,R.drawable.lens1);
    }
}
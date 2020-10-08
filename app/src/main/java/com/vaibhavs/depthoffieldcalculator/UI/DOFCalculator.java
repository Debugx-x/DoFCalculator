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

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.vaibhavs.depthoffieldcalculator.Model.Calculator;
import com.vaibhavs.depthoffieldcalculator.Model.Lens;
import com.vaibhavs.depthoffieldcalculator.Model.LensManager;
import com.vaibhavs.depthoffieldcalculator.R;

import java.text.DecimalFormat;

// Displays camera details and allows user to calculate Depth of Field for specified values

public class DOFCalculator extends AppCompatActivity {

    private static String INDEX = "0";
    private static final int REQUESTED_CODE = 13;
    LensManager lenses;
    static Lens ln;

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

        lenses = LensManager.getInstance();
        FloatingActionButton fab = findViewById(R.id.floatingActionButton_edit);
        fab.setOnClickListener(v -> {
            Intent intent = EditLens.makeLaunchIntent(DOFCalculator.this, Integer.parseInt(INDEX));
            startActivity(intent);
            finish();
            });
        GetData();
        Display_Lens();
        Calculate();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu Mu){
        getMenuInflater().inflate(R.menu.menu_add,Mu);
        return true;
    }

    @Override
    public  boolean onOptionsItemSelected(MenuItem item){
        switch(item.getItemId()) {
            case R.id.action_remove:
                Intent i = new Intent(DOFCalculator.this,RemoveLens.class);
                startActivity(i);
                ln = lenses.lenses.get(Integer.parseInt(INDEX));
                break;
            case android.R.id.home:
                Toast.makeText(DOFCalculator.this,"Pressed Back!",Toast.LENGTH_SHORT).show();
                finish();
            default:
                return super.onOptionsItemSelected(item);
        }
        return super.onOptionsItemSelected(item);
    }


    private void Display_Lens() {
        TextView disp = (TextView)findViewById(R.id.text_display);
        String len_info = ln.getMake() + " " + ln.getFocal_length() + "mm F" + ln.getMaximum_aperture();
        disp.setText(len_info);
    }

    private void Calculate() {
        Button Calc_btn = (Button) findViewById(R.id.btn_Calc);
        Calc_btn.setOnClickListener((View.OnClickListener) v -> {

            //Setting up EditTexts
            EditText coc_et = (EditText) findViewById(R.id.input_CoC);
            double COC =  Double.parseDouble(coc_et.getText().toString());
            EditText dist_et = (EditText) findViewById(R.id.input_Distance);
            double Distance = Double.parseDouble(dist_et.getText().toString());
            EditText aper_et = (EditText) findViewById(R.id.input_Aperature);
            double Apert = Double.parseDouble(aper_et.getText().toString());

            //Setting up TextViews
            TextView NFocal_dist = (TextView) findViewById(R.id.text_NFDistance);
            TextView FFocal_dist = (TextView) findViewById(R.id.text_FFDistance);
            TextView DoField = (TextView) findViewById(R.id.text_DoF);
            TextView HFocal_dist = (TextView) findViewById(R.id.text_HFDistance);

            if(COC <= 0.0) {
                NFocal_dist.setText(R.string.invalid_CoC);
                FFocal_dist.setText(R.string.invalid_CoC);
                DoField.setText(R.string.invalid_CoC);
                HFocal_dist.setText(R.string.invalid_CoC);
            } else if (Distance <= 0.0 ) {
                NFocal_dist.setText(R.string.invalid_distance);
                FFocal_dist.setText(R.string.invalid_distance);
                DoField.setText(R.string.invalid_distance);
                HFocal_dist.setText(R.string.invalid_distance);
            } else if (Apert < 1.4 || Apert < ln.getMaximum_aperture() ) {
                NFocal_dist.setText(R.string.invalid_ap);
                FFocal_dist.setText(R.string.invalid_ap);
                DoField.setText(R.string.invalid_ap);
                HFocal_dist.setText(R.string.invalid_ap);
            } else {
                Calculator lens = new Calculator(ln,Apert,Distance,COC);
                NFocal_dist.setText(formatM(lens.Calc_Near_Focalpoint())+"m");
                FFocal_dist.setText(formatM(lens.Calc_Far_Focalpoint())+"m");
                DoField.setText(formatM(lens.Calc_Depth_of_Field())+"m");
                HFocal_dist.setText(formatM(lens.Calc_Hyperfocal_Distance())+"m");
            }
        });
    }

    public static Intent makeLaunchIntent(Context c, int Lens_index){
        Intent intent = new Intent(c, DOFCalculator.class);
        intent.putExtra(INDEX,Lens_index);
        return intent;
    }

    private void GetData() {
        Intent intent = getIntent();
        int Lens_Index = intent.getIntExtra(INDEX,0);
        ln = lenses.lenses.get(Lens_Index);
    }
}
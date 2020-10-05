package com.vaibhavs.depthoffieldcalculator.UI;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import com.vaibhavs.depthoffieldcalculator.R;

public class DOFCalculator extends AppCompatActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_d_o_f_calculator);
        ImageButton cancel_btn = findViewById(R.id.btn_cancel);
        cancel_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(DOFCalculator.this,"Pressed cancel, Going back",Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(DOFCalculator.this,MainActivity.class);
                startActivity(intent);
            }
        });
    }
}
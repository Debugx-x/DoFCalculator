package com.vaibhavs.depthoffieldcalculator.UI;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.vaibhavs.depthoffieldcalculator.Model.Lens;
import com.vaibhavs.depthoffieldcalculator.Model.LensManager;
import com.vaibhavs.depthoffieldcalculator.R;

public class AddLens extends AppCompatActivity {
    private LensManager lenses;
    Button Save_btn;
    ImageButton cancel_btn;
    EditText make_Et, max_aperature_Et, Focal_len_Et;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_lens);
        lenses = LensManager.getInstance();
        make_Et = findViewById(R.id.text_Make);
        final String Make = make_Et.getText().toString();
        max_aperature_Et = findViewById(R.id.text_aperature);
        final double Aperature = Double.parseDouble(max_aperature_Et.getText().toString());
        Focal_len_Et = findViewById(R.id.text_Focallenght);
        final int Flength = (int)Double.parseDouble(Focal_len_Et.getText().toString());
        Save_btn = findViewById(R.id.btn_Savelens);
        Save_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(make_Et.getText().toString().isEmpty()||max_aperature_Et.getText().toString().isEmpty()||Focal_len_Et.getText().toString().isEmpty()) {
                    Toast.makeText(AddLens.this,"ERROR: Check input",Toast.LENGTH_SHORT);
                }
                lenses.add(new Lens(Make,Aperature,Flength));
                Toast.makeText(AddLens.this, "Lens Saved!", Toast.LENGTH_SHORT);
            }
        });
        cancel_btn = findViewById(R.id.btn_cancel);
        cancel_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(AddLens.this,"Pressed cancel, Going back",Toast.LENGTH_SHORT);
                Intent intent = new Intent(AddLens.this,MainActivity.class);
                startActivity(intent);
            }
        });

    }
}

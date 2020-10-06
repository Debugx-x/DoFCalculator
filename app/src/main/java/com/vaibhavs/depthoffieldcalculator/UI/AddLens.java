package com.vaibhavs.depthoffieldcalculator.UI;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.vaibhavs.depthoffieldcalculator.Model.Lens;
import com.vaibhavs.depthoffieldcalculator.Model.LensManager;
import com.vaibhavs.depthoffieldcalculator.R;

public class AddLens extends AppCompatActivity {

    LensManager lenses;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_lens);
        lenses = LensManager.getInstance();
        //Toolbar tb_add = findViewById(R.id.toolbar);
        //setSupportActionBar(tb_add);
        Save_add();
        Cancel_add();
    }


    private void Save_add() {
        Button Save_btn = findViewById(R.id.btn_Savelens);
        Save_btn.setOnClickListener(v -> {
            EditText make_Et = findViewById(R.id.input_Make);
            String Make = make_Et.getText().toString();
            if(Make.isEmpty()){
                Toast.makeText(AddLens.this,"Make cannot be empty",Toast.LENGTH_SHORT).show();
            }
            EditText max_aperature_Et = findViewById(R.id.input_Aperture);
            double Aperature = Double.parseDouble(max_aperature_Et.getText().toString());
            if(Aperature < 1.4){
                Toast.makeText(AddLens.this,"Aperture cannot be less than 1.4",Toast.LENGTH_SHORT).show();
            }
            EditText Focal_len_Et = findViewById(R.id.input_FocalLength);
            int Flength = Integer.parseInt(Focal_len_Et.getText().toString());
            if(Flength < 0){
                Toast.makeText(AddLens.this,"Focal length cannot be less than 12 or negative",Toast.LENGTH_SHORT).show();
            }
            if(make_Et.getText().toString().isEmpty()||max_aperature_Et.getText().toString().isEmpty()||Focal_len_Et.getText().toString().isEmpty()) {
                Toast.makeText(AddLens.this,"ERROR: Check input!",Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(AddLens.this, "Lens Saved!", Toast.LENGTH_SHORT).show();
                lenses.add(new Lens(Make,Aperature,Flength,R.drawable.lens));
            }
            Intent intent = new Intent();
            intent.putExtra("Name of the lens", Make);
            intent.putExtra("Aperture of the lens", Aperature);
            intent.putExtra("Focal length of the lens", Flength);
            setResult(Activity.RESULT_OK,intent);
            finish();
        });
    }

    public void Cancel_add() {
        ImageButton cancel_btn = findViewById(R.id.btn_cancel);
        cancel_btn.setOnClickListener(v -> {
            Toast.makeText(AddLens.this,"Pressed cancel!",Toast.LENGTH_SHORT).show();
            Intent intent = new Intent();
            setResult(Activity.RESULT_CANCELED,intent);
            finish();
        });
    }
    public  static  Intent makeLaunchIntent(Context context) {
        Intent intent = new Intent(context,AddLens.class);
        return  intent;
    }
}

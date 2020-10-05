package com.vaibhavs.depthoffieldcalculator.UI;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
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

    private static final String EXTRA_MESSAGE = "Extra - message";
    public static Intent makeLaunchIntent(Context c, String msg){
        Intent intent = new Intent(c, AddLens.class);
        intent.putExtra(EXTRA_MESSAGE, msg);
        return intent;
    }

    private static String getMsg() {
        return "msg";
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_lens);

        Intent i = getIntent();
        String message = i.getStringExtra(EXTRA_MESSAGE);
        Toast.makeText(this,message,Toast.LENGTH_LONG).show();
        LensManager lenses = LensManager.getInstance();


        EditText make_Et = findViewById(R.id.text_Make);
        String Make = make_Et.getText().toString();
        EditText max_aperature_Et = findViewById(R.id.text_aperature);
        double Aperature = Double.parseDouble(max_aperature_Et.getText().toString());
        EditText Focal_len_Et = findViewById(R.id.text_Focallenght);
        int Flength = Integer.parseInt(Focal_len_Et.getText().toString());

        Button Save_btn = findViewById(R.id.btn_Savelens);
        Save_btn.setOnClickListener(v -> {
            if(make_Et.getText().toString().isEmpty()||max_aperature_Et.getText().toString().isEmpty()||Focal_len_Et.getText().toString().isEmpty()) {
                Toast.makeText(AddLens.this,"ERROR: Check input!",Toast.LENGTH_SHORT).show();
            } else {
                lenses.add(new Lens(Make, Aperature, Flength,R.drawable.lens));
                Toast.makeText(AddLens.this, "Lens Saved!", Toast.LENGTH_SHORT).show();
            }
        });
        Cancel_add();
    }

    public void Cancel_add() {
        ImageButton cancel_btn = findViewById(R.id.btn_cancel);
        cancel_btn.setOnClickListener(v -> {
            Toast.makeText(AddLens.this,"Pressed cancel, Going back",Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(AddLens.this,MainActivity.class);
            startActivity(intent);
        });
    }
}

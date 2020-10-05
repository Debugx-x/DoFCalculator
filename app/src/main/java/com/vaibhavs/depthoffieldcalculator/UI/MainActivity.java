package com.vaibhavs.depthoffieldcalculator.UI;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;

import com.vaibhavs.depthoffieldcalculator.Model.Lens;
import com.vaibhavs.depthoffieldcalculator.Model.LensManager;
import com.vaibhavs.depthoffieldcalculator.R;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        LensManager lenses = LensManager.getInstance();
        add_defaultlenses(lenses);
        add_morelensbutton();
        }

    private void add_morelensbutton() {
        FloatingActionButton fab = findViewById(R.id.Add_lens);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,AddLens.class);
                startActivity(intent);
            }
        });
    }
    private void add_defaultlenses(LensManager manager) {

        manager.add(new Lens("Canon", 1.8, 50));
        manager.add(new Lens("Tamron", 2.8, 90));
        manager.add(new Lens("Sigma", 2.8, 200));
        manager.add(new Lens("Nikon", 4.0, 200));
    }
}
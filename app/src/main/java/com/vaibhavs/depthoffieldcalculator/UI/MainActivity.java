package com.vaibhavs.depthoffieldcalculator.UI;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.vaibhavs.depthoffieldcalculator.Model.Lens;
import com.vaibhavs.depthoffieldcalculator.Model.LensManager;
import com.vaibhavs.depthoffieldcalculator.R;

import java.util.Collections;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    LensManager lenses;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        LensManager lenses = LensManager.getInstance();
        add_defaultlenses(lenses);
        pop_Listview();
        add_morelensbutton();
        getLensIndex();
        }


    private void add_defaultlenses(LensManager manager) {

        manager.add(new Lens("Canon", 1.8, 50));
        manager.add(new Lens("Tamron", 2.8, 90));
        manager.add(new Lens("Sigma", 2.8, 200));
        manager.add(new Lens("Nikon", 4.0, 200));
    }

    private void pop_Listview() {
        ArrayAdapter<LensManager> adapter = new ArrayAdapter<LensManager>(this,R.layout.list_lens, Collections.singletonList(lenses));
        ListView list = findViewById(R.id.Lens_list);
        list.setAdapter(adapter);
    }

    private void getLensIndex() {
        ListView list = findViewById(R.id.Lens_list);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                TextView lens_selected = (TextView) view;
                String message = "You have selected " + lens_selected.getText().toString();
                Toast.makeText(MainActivity.this,message,Toast.LENGTH_LONG);

            }
        });
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
}
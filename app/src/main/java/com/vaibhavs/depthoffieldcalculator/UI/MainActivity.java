package com.vaibhavs.depthoffieldcalculator.UI;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.vaibhavs.depthoffieldcalculator.Model.Lens;
import com.vaibhavs.depthoffieldcalculator.Model.LensManager;
import com.vaibhavs.depthoffieldcalculator.R;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    LensManager lenses = LensManager.getInstance();
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FloatingActionButton fab = findViewById(R.id.Add_lens);
        fab.setOnClickListener(v -> {
            Intent i = AddLens.makeLaunchIntent(MainActivity.this, "ADD LENS");
            startActivity(i);
        });

        populateLensManger();
        populateList();

        }


    private void populateLensManger() {
        lenses.add(new Lens("Canon", 1.8, 50,R.drawable.lens));
        lenses.add(new Lens("Tamron", 2.8, 90,R.drawable.lens2));
        lenses.add(new Lens("Sigma", 2.8, 200,R.drawable.lens3));
        lenses.add(new Lens("Nikon", 4.0, 200,R.drawable.lens));
    }

    private void populateList() {
        ArrayAdapter<Lens> adapter = new myListAdapter();
        ListView lv = findViewById(R.id.lensesList);
        lv.setAdapter(adapter);
    }

    private class myListAdapter extends ArrayAdapter<Lens>{
        public  myListAdapter(){
            super(MainActivity.this,R.layout.list_lenses, lenses.lenses);
        }

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            View iview = convertView;
            if(iview == null) {
                iview = getLayoutInflater().inflate(R.layout.list_lenses, parent, false);
            }
            Lens temp = lenses.lenses.get(position);
            //img
            ImageView imageV = iview.findViewById(R.id.img_icon);
            imageV.setImageResource(temp.getImgID());
            //make
            TextView makeT = findViewById(R.id.text_Make);
            makeT.setText(temp.getMake());
            //F length
            TextView focallenT = findViewById(R.id.text_Focallenght);
            focallenT.setText(temp.getFocal_length() + "mm");
            //Aperature
            TextView AperT = findViewById(R.id.text_aperature);
            AperT.setText("F" + temp.getMaximum_aperture());
            return iview;
        }
    }

}
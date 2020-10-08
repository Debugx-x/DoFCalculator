package com.vaibhavs.depthoffieldcalculator.UI;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.vaibhavs.depthoffieldcalculator.Model.Lens;
import com.vaibhavs.depthoffieldcalculator.Model.LensManager;
import com.vaibhavs.depthoffieldcalculator.R;

// Home page

public class MainActivity extends AppCompatActivity {

    LensManager lenses;
    private static boolean flag = false;
    private static final int REQUESTED_CODE = 13;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        lenses = LensManager.getInstance();
        FloatingActionButton fab = findViewById(R.id.Add_lens);
        fab.setOnClickListener(v -> {
            Intent intent = AddLens.makeLaunchIntent(MainActivity.this);
            startActivityForResult(intent, REQUESTED_CODE);
            TextView tv1 = findViewById(R.id.text_newlens);
            tv1.setText("");
        });
        populateLensMangerOnlyonce();
        populateList();
        registerClick();
        if(lenses.lenses.size() == 0 )
        {
            TextView tv = (TextView) findViewById(R.id.text_newlens);
            tv.setText(R.string.add_lens_main);
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu Mu){
        getMenuInflater().inflate(R.menu.menu_main,Mu);
        return true;
    }

    @Override
    public  boolean onOptionsItemSelected(MenuItem item){
        switch(item.getItemId()) {
            case R.id.action_remove:
                Intent i = new Intent(MainActivity.this,Setting_page.class);
                startActivity(i);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_CANCELED) {
            return;
        }

        switch (requestCode) {
            case REQUESTED_CODE:
                if (resultCode == Activity.RESULT_OK) {
                    String lens_make = data.getStringExtra("Name of the lens1");
                    int lens_FocalLen = data.getIntExtra("Focal length of the lens1",0);
                    double lens_Aperture = data.getDoubleExtra("Aperture of the lens1", 0);
                    Toast.makeText(MainActivity.this, "Added a New Lens " + lens_make + " " + lens_FocalLen + "mm F" + lens_Aperture, Toast.LENGTH_SHORT).show();
                }
        }
        ArrayAdapter<Lens> adapter = new myListAdapter();
        ListView list = (ListView) findViewById(R.id.lensesList);
        list.setAdapter(adapter);
    }

    private void populateLensMangerOnlyonce() {
        TextView tv = findViewById(R.id.text_newlens);
        if(!flag) {
            lenses.add(new Lens("Canon", 1.8, 50, R.drawable.lens1));
            lenses.add(new Lens("Tamron", 2.8, 90, R.drawable.lens2));
            lenses.add(new Lens("Sigma", 2.8, 200, R.drawable.lens3));
            lenses.add(new Lens("Nikon", 4.0, 200, R.drawable.lens4));
            flag = true;
        }
    }

    private void populateList() {
        ArrayAdapter<Lens> adapter = new myListAdapter();
        ListView lv = findViewById(R.id.lensesList);
        lv.setAdapter(adapter);
    }

    private void registerClick() {
        ListView Lv = (ListView) findViewById(R.id.lensesList);
        Lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Toast.makeText(MainActivity.this, "Lens Selected!", Toast.LENGTH_LONG).show();
                        Intent intent = DOFCalculator.makeLaunchIntent(MainActivity.this, position);
                        startActivity(intent);
            }
        });
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
            ImageView imageV = iview.findViewById(R.id.icon_img);
            imageV.setImageResource(temp.getImgID());
            //make
            TextView makeT = iview.findViewById(R.id.input_Make);
            makeT.setText(temp.getMake());
            //Focal length
            TextView focallenT = iview.findViewById(R.id.text_focallen);
            focallenT.setText(temp.getFocal_length() + "mm");
            //Aperature
            TextView AperT = iview.findViewById(R.id.text_aperature);
            AperT.setText("F" + temp.getMaximum_aperture());
            return iview;
        }
    }
}
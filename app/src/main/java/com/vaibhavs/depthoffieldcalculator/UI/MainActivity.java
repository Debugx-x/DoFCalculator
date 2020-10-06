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

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.vaibhavs.depthoffieldcalculator.Model.Lens;
import com.vaibhavs.depthoffieldcalculator.Model.LensManager;
import com.vaibhavs.depthoffieldcalculator.R;

public class MainActivity extends AppCompatActivity {

    LensManager lenses;
    private static final int REQUESTED_CODE = 33;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Toolbar tb = findViewById(R.id.toolbar);
        //setSupportActionBar(tb);

        lenses = LensManager.getInstance();
        FloatingActionButton fab = findViewById(R.id.Add_lens);
        fab.setOnClickListener(v -> {
            Intent intent = AddLens.makeLaunchIntent(MainActivity.this);
            startActivityForResult(intent, REQUESTED_CODE);
        });

        populateLensManger();
        populateList();
        registerClick();
        }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_CANCELED) {
            Toast.makeText(getBaseContext(), "User Canceled", Toast.LENGTH_LONG).show();
            return;
        }

        switch (requestCode) {
            case REQUESTED_CODE:
                if (resultCode == Activity.RESULT_OK) {
                    String lens_make = data.getStringExtra("Name of lens");
                    int lens_FocalLen = data.getIntExtra("Focal length of lens", 0);
                    double lens_Aperture = data.getDoubleExtra("Aperture of lens", 0);
                    lenses.add( new Lens(lens_make, lens_Aperture, lens_FocalLen, R.drawable.lens));
                    ArrayAdapter<Lens> adapter = new myListAdapter();
                    ListView list = (ListView) findViewById(R.id.lensesList);
                    list.setAdapter(adapter);
                }
        }
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

    private void registerClick() {
        ListView Lv =findViewById(R.id.lensesList);
        Lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(MainActivity.this,"Lens Selected!",Toast.LENGTH_LONG).show();
                Lens temp = lenses.lenses.get(position);
                Intent intent = DOFCalculator.makeLaunchIntent(MainActivity.this,temp);
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
            ImageView imageV = iview.findViewById(R.id.img_icon);
            imageV.setImageResource(temp.getImgID());
            //make
            TextView makeT = iview.findViewById(R.id.text_make);
            makeT.setText(temp.getMake());
            //F length
            TextView focallenT = iview.findViewById(R.id.text_focallen);
            focallenT.setText(temp.getFocal_length() + "mm");
            //Aperature
            TextView AperT = iview.findViewById(R.id.text_aperature);
            AperT.setText("F" + temp.getMaximum_aperture());
            return iview;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu Mu){
        getMenuInflater().inflate(R.menu.menu_main,Mu);
        return  true;
    }

    @Override
    public  boolean onOptionsItemSelected(MenuItem item){
        int id = item.getItemId();
        if(id == R.id.action_settings){
            return  true;
        }
        return super.onOptionsItemSelected(item);
    }
}
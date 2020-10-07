package com.vaibhavs.depthoffieldcalculator.UI;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.vaibhavs.depthoffieldcalculator.Model.Lens;
import com.vaibhavs.depthoffieldcalculator.Model.LensManager;
import com.vaibhavs.depthoffieldcalculator.R;

public class EditLens extends AppCompatActivity {

    private static final String MAKE_LENS = "Canon";
    private static final String FOCAL_LENGTH_LENS = "200";
    private static final String APERTURE_LENS = "2.8";
    LensManager lenses;
    Lens ln;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_lens);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar ab = getSupportActionBar();
        ab.setDisplayHomeAsUpEnabled(true);

        lenses = LensManager.getInstance();
        GetData();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu Mu){
        getMenuInflater().inflate(R.menu.menu_save_back,Mu);
        return true;
    }

    @Override
    public  boolean onOptionsItemSelected(MenuItem item){
        switch(item.getItemId()) {
            case android.R.id.home:
                Toast.makeText(EditLens.this,"Pressed cancel!",Toast.LENGTH_SHORT).show();
                finish();
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void GetData() {
        Intent intent = getIntent();
        String DOF_make = intent.getStringExtra(MAKE_LENS);
        int DOF_focalLength = intent.getIntExtra(FOCAL_LENGTH_LENS,0);
        double DOF_aperture = intent.getDoubleExtra(APERTURE_LENS,0.0);
        ln = new Lens(DOF_make,DOF_aperture,DOF_focalLength,R.drawable.lens1);
    }

    public static Intent makeLaunchIntent(Context c, Lens lens){
        Intent intent = new Intent(c, EditLens.class);
        intent.putExtra(MAKE_LENS,lens.getMake());
        intent.putExtra(FOCAL_LENGTH_LENS,lens.getFocal_length());
        intent.putExtra(APERTURE_LENS,lens.getMaximum_aperture());
        return intent;
    }
}
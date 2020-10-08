package com.vaibhavs.depthoffieldcalculator.UI;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.vaibhavs.depthoffieldcalculator.Model.Lens;
import com.vaibhavs.depthoffieldcalculator.Model.LensManager;
import com.vaibhavs.depthoffieldcalculator.R;

/**
 * This activity is used to edit the Selected lens
 */
public class EditLens extends AppCompatActivity {

    private static final String INDEX = "0";
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
        Settext_hint();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu Mu){
        getMenuInflater().inflate(R.menu.menu_save_back,Mu);
        return true;
    }

    @Override
    public  boolean onOptionsItemSelected(MenuItem item){
        switch(item.getItemId()) {
            case R.id.action_bar_button_ok:
                EditText make_et = findViewById(R.id.input_Lensmake);
                EditText flen_et = findViewById(R.id.input_Lensfl);
                EditText Aper_et = findViewById(R.id.input_Lensaperture);
                if(make_et.getText().toString().isEmpty()){
                    Toast.makeText(EditLens.this,"Make cannot be empty",Toast.LENGTH_SHORT).show();
                    break;
                }
                double Aperture = Double.parseDouble(Aper_et.getText().toString());
                if(Aperture < 1.4){
                    Toast.makeText(EditLens.this,"Aperture cannot be less than 1.4",Toast.LENGTH_SHORT).show();
                    break;
                } else if(Aper_et.getText().toString().isEmpty()){
                    Toast.makeText(EditLens.this,"Aperture cannot be empty",Toast.LENGTH_SHORT).show();
                    break;
                }
                int Flength = Integer.parseInt(flen_et.getText().toString());
                if(Flength < 0){
                    Toast.makeText(EditLens.this,"Focal length cannot be 0 or negative",Toast.LENGTH_SHORT).show();
                    break;
                } else if (flen_et.getText().toString().isEmpty()){
                    Toast.makeText(EditLens.this,"Focal length cannot be empty",Toast.LENGTH_SHORT).show();
                    break;
                }
                if(make_et.getText().toString().isEmpty()||Aper_et.getText().toString().isEmpty()||flen_et.getText().toString().isEmpty()){
                    Toast.makeText(EditLens.this,"ERROR: Check input!",Toast.LENGTH_SHORT).show();
                    break;
                } else {
                    Toast.makeText(EditLens.this, "Lens Edited!", Toast.LENGTH_SHORT).show();
                    // I utilise the setters in the Lens class to actually edit the lens Instead of Delete and then Add
                    lenses.lenses.get(Integer.parseInt(INDEX)).setMake(make_et.getText().toString());
                    lenses.lenses.get(Integer.parseInt(INDEX)).setMaximum_aperture(Aperture);
                    lenses.lenses.get(Integer.parseInt(INDEX)).setFocal_length(Flength);
                    finish();
                }
                break;
            case android.R.id.home:
                Toast.makeText(EditLens.this,"Pressed cancel!",Toast.LENGTH_SHORT).show();
                finish();
            default:
                return super.onOptionsItemSelected(item);
        }
        return super.onOptionsItemSelected(item);
    }

    private void Settext_hint() {
        EditText make_et = findViewById(R.id.input_Lensmake);
        EditText flen_et = findViewById(R.id.input_Lensfl);
        EditText Aper_et = findViewById(R.id.input_Lensaperture);
        make_et.setText(ln.getMake());
        make_et.setHint(ln.getMake());
        flen_et.setText(ln.getFocal_length()+"");
        flen_et.setHint("200 for 200mm");
        Aper_et.setText(""+ln.getMaximum_aperture());
        Aper_et.setHint("2.8 for F2.8");
    }

    private void GetData() {
        Intent intent = getIntent();
        int Lens_Index = intent.getIntExtra(INDEX,0);
        ln = lenses.lenses.get(Lens_Index);
    }

    public static Intent makeLaunchIntent(Context c, int lens_index){
        Intent intent = new Intent(c, EditLens.class);
        intent.putExtra(INDEX,lens_index);
        return intent;
    }
}
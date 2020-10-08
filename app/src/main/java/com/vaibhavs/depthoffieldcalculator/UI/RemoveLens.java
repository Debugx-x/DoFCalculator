package com.vaibhavs.depthoffieldcalculator.UI;

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
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.vaibhavs.depthoffieldcalculator.Model.Lens;
import com.vaibhavs.depthoffieldcalculator.Model.LensManager;
import com.vaibhavs.depthoffieldcalculator.R;

public class RemoveLens extends AppCompatActivity {

    LensManager lenses;
    private static Lens lens_remove = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_remove_lens);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar ab = getSupportActionBar();
        ab.setDisplayHomeAsUpEnabled(true);

        lenses = LensManager.getInstance();

        populateList();
        registerClick();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu Mu){
        getMenuInflater().inflate(R.menu.menu_delete_back,Mu);
        return true;
    }

    @Override
    public  boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_bar_button_ok:
                if (lens_remove != null) {
                    Toast.makeText(RemoveLens.this, "Lens " + lens_remove.toString() + " has been Deleted!", Toast.LENGTH_SHORT).show();
                    lenses.remove(lens_remove);
                    lens_remove = null;
                    finish();
                } else {
                    Toast.makeText(RemoveLens.this, "Please select a Lens to Delete", Toast.LENGTH_SHORT).show();
                    break;
                }
                break;
            case android.R.id.home:
                Toast.makeText(RemoveLens.this, "Pressed Back!", Toast.LENGTH_SHORT).show();
                finish();
            default:
                return super.onOptionsItemSelected(item);
        }
        return super.onOptionsItemSelected(item);
    }
    private void populateList() {
        ArrayAdapter<Lens> adapter = new RemoveLens.myListAdapter();
        ListView lv = findViewById(R.id.list_removelens);
        lv.setAdapter(adapter);
    }

    private void registerClick() {
        ListView Lv = (ListView) findViewById(R.id.list_removelens);
        Lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Lens temp = lenses.lenses.get(position);
                Toast.makeText(RemoveLens.this, "Lens "+ temp.toString() + " Selected!", Toast.LENGTH_LONG).show();
                lens_remove = temp;
            }
        });
    }

    private class myListAdapter extends ArrayAdapter<Lens> {
        public myListAdapter() {
            super(RemoveLens.this, R.layout.list_lenses, lenses.lenses);
        }

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            View iview = convertView;
            if (iview == null) {
                iview = getLayoutInflater().inflate(R.layout.list_lenses, parent, false);
            }
            Lens temp = lenses.lenses.get(position);
            //img
            ImageView imageV = iview.findViewById(R.id.icon_img);
            imageV.setImageResource(temp.getImgID());
            //make
            TextView makeT = iview.findViewById(R.id.input_Make);
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
}
package com.example.azkary;

import android.content.DialogInterface;
import android.graphics.Color;
import android.support.annotation.ColorLong;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.azkary.database.prayerclass;
import com.example.azkary.database.prayers;

import java.util.ArrayList;

public class AddYourPrayers extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_your_prayers);

        prayers praydp = new prayers(this);

        ListView list = findViewById(R.id.list);
        EditText e = findViewById(R.id.addprayer);

        //add to database
       findViewById(R.id.plus).setOnClickListener(v -> {
            if(e.getText().toString().equals("")){
                Toast.makeText(AddYourPrayers.this,"يجب أن تدخل دعاءاً",Toast.LENGTH_LONG).show();
            }else {
                praydp.insertNewPray(e.getText().toString());
                e.getText().clear();
            }
            //show listview
           showlist();
        });
//show in listview when start program
        showlist();

    }

    class Addapter extends BaseAdapter {
        ArrayList<prayerclass> i = new ArrayList<prayerclass>();

        public Addapter(ArrayList<prayerclass> l) {
            this.i = l;
        }

        @Override
        public int getCount() {
            return i.size();
        }

        @Override
        public Object getItem(int position) {
            return i.get(position).pray;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            LayoutInflater ly = getLayoutInflater();
            prayers praydp = new prayers(AddYourPrayers.this);

            View v = ly.inflate(R.layout.praytable, null);

            TextView t1 = v.findViewById(R.id.textpray);
            Button btndel = v.findViewById(R.id.btndel);

            t1.setText(i.get(position).pray+"");

            //click on btn del
            btndel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    new AlertDialog.Builder(AddYourPrayers.this)
                            .setIcon(android.R.drawable.ic_delete)
                            .setTitle("Are you sure ?")
                            .setMessage("Do you want to delete this item")
                            .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    //if click yes del from database
                                    praydp.deletepray(i.get(position).id+"");
                                    //show in listview from database
                                    showlist();
                                }
                            })
                            .setNegativeButton("no",null)
                            .show();
                }
            });
            return v;
        }
    }

  public void showlist(){
      prayers praydp = new prayers(this);

      ListView list = findViewById(R.id.list);
      ArrayList<prayerclass> arr = praydp.Getdata();

      Addapter addapter=new Addapter(arr);
      list.setAdapter(addapter);
  }
}
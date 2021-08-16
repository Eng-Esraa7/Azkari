package com.example.azkary;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.example.azkary.database.prayerclass;
import com.example.azkary.database.prayers;

import java.util.ArrayList;

public class test extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        ListView list = findViewById(R.id.testlist);
        ArrayList<testclass> arr = new ArrayList<testclass>();
        String[] arrstr = getResources().getStringArray(R.array.elsabah);
        String [] arrnum = getResources().getStringArray(R.array.num);
        for (int i=0;i<arrstr.length;i++){
        testclass tt = new testclass(arrstr[i],arrnum[i]);
            arr.add(tt);
        }
        Addapter addapter=new Addapter(arr);
        list.setAdapter(addapter);
    }
    class Addapter extends BaseAdapter {
        ArrayList<testclass> i = new ArrayList<testclass>();

        public Addapter(ArrayList<testclass> l) {
            this.i = l;
        }

        @Override
        public int getCount() {
            return i.size();
        }

        @Override
        public Object getItem(int position) {
            return i.get(position).num;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            LayoutInflater ly = getLayoutInflater();
            String [] arrstr = getResources().getStringArray(R.array.elsabah);
            String [] arrnum = getResources().getStringArray(R.array.num);
            View v = ly.inflate(R.layout.elsabahtable, null);
            TextView t1 = v.findViewById(R.id.text);
            Button btndel = v.findViewById(R.id.btnelsabah);
            t1.setText(arrstr[position]);
            btndel.setText(arrnum[position]);
            return v;
        }
    }
}
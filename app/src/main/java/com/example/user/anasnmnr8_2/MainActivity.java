package com.example.user.anasnmnr8_2;

import android.content.Context;
import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ListView list;
    ////ListView is a view which groups several items and display them in vertical scrollable list. The list items are automatically
    // inserted to the list using an Adapter that pulls content from a source such as an array or database

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        list= (ListView)findViewById(R.id.listview);//creating the listview
        list.setAdapter(new MyAdapter(this));//creating Adapter object

    }
    //Creating MyAda
    // per class it extend the BaseAdapter class
    class MyAdapter extends BaseAdapter {
        //BaseAdapter:Common base class of common implementation for an Adapter that can be used in both ListView (by implementing
        // the specialized ListAdapter interface) and Spinner (by implementing the specialized SpinnerAdapter interface).
        ArrayList<SingleRow> list;//creating array list
        Context context;
        //Interface to global information about an application environment
        MyAdapter(Context c){
            context=c;
            list=new ArrayList<SingleRow>();
            //using getResources method to pull Name and phonenumber
            Resources res= c.getResources();
            //The Android resource system keeps track of all non-code assets associated with an application.
            String[] name=res.getStringArray(R.array.Name);
            //get the name from the array which we declared in the string
            String[] phoneNumber=  res.getStringArray(R.array.PhoneNumber);
            for (int i=0;i<name.length;i++){
                //Creating single row object
                list.add( new SingleRow( name[i],phoneNumber[i]));
            }
        }

        @Override
        public int getCount() {
            return list.size();
            //it will take the count and return the list size
        }

        @Override
        public Object getItem(int position) {
            return list.get(position);
            //position of the list
        }

        @Override
        public long getItemId(int position) {
            return position;
            //id of every item with the position
        }

        public class SingleRow {
            String Name;

            public SingleRow(String name, String phoneNumber) {
                Name = name;
                PhoneNumber = phoneNumber;
            }

            String PhoneNumber;

        }//here we declare the values we need to print in the list view

        //getview method it take cares of all the views
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            // LayoutInflater :Instantiates a layout XML file into its corresponding View objects.
            //
            LayoutInflater inflater= (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
            //here it call all the methods we used for layoutinflater
            //inflater.inflate is used to create the view from our xml file
            View row=   inflater.inflate(R.layout.adapter1,parent,false);
            TextView name= (TextView) row.findViewById(R.id.textView3);
            TextView phoneNumber= (TextView) row.findViewById(R.id.textView4);
            SingleRow step= list.get(position);//get's the position
            name.setText(step.Name);//printing them
            phoneNumber.setText(step.PhoneNumber);
            return row ;//returning them
        }
    }
}
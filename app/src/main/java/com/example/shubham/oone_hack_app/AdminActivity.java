package com.example.shubham.oone_hack_app;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.QuickContactBadge;
import android.widget.Spinner;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AdminActivity extends AppCompatActivity {

    static String sentdate;
    int durindex = -1;
    int slotindex = -1;
    int locid = -1;
    static Button dateSelect;
    ArrayList<Location> locations=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);





         dateSelect=(Button)findViewById(R.id.dateSelect);
        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);
        dateSelect.setText(year+"-"+month+"-"+day);
        dateSelect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment newFragment = new DatePickerFragment();
                newFragment.show(getSupportFragmentManager(), "datePicker");
            }
        });




        final Spinner loc = (Spinner)findViewById(R.id.loc);

        ApiService apiService=ApiClient.getApiService();
        final Call<ArrayList<Location>> call=apiService.getLocations();
        call.enqueue(new Callback<ArrayList<Location>>() {
            @Override
            public void onResponse(Call<ArrayList<Location>> call, Response<ArrayList<Location>> response) {
                locations=response.body();
                ArrayList<String> strings=new ArrayList<>();
                for(Location l:locations){
                    strings.add(l.getName());

                }

                ArrayAdapter<String> adapter3 = new ArrayAdapter(AdminActivity.this, android.R.layout.simple_spinner_dropdown_item, strings);
                loc.setAdapter(adapter3);

            }

            @Override
            public void onFailure(Call<ArrayList<Location>> call, Throwable t) {

            }
        });



        loc.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                locid=14;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                locid=14;
            }
        });

        Spinner slot = (Spinner)findViewById(R.id.slot);
        Spinner dur=(Spinner)findViewById(R.id.duration);
        final HashMap<String,Integer> map=new HashMap<>();
        map.put("8-10",5);
        map.put("10-12",6);
        map.put("12-14",7);
        map.put("14-16",8);
        map.put("16-18",9);
        final HashMap<String,Integer> map2=new HashMap<>();
        map2.put("0-10",1);
        map2.put("10-20",2);
        map2.put("20-30",3);
        map2.put("30-40",4);
        map2.put("40-50",5);
        map2.put("50-60",6);

        final ArrayList<String> slots=new ArrayList<>();

        for(String s:map.keySet()){
            slots.add(s);
        }

        final ArrayList<String> durations=new ArrayList<>();

        for(String s:map2.keySet()){
            durations.add(s);
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, slots);
        slot.setAdapter(adapter);
        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, durations);
        dur.setAdapter(adapter2);


        dur.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                durindex=3;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                durindex=3;
            }
        });



        slot.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                slotindex=map.get(slots.get(position));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                slotindex=map.get(slots.get(0));
            }
        });
        Button submit=(Button)findViewById(R.id.submit);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ApiService apiService1=ApiClient.getApiService();
                if(sentdate!=null && locid!= -1 && slotindex!= -1 && durindex != -1) {
                    Call<ArrayList<BidDetail>> call1 = apiService1.postBid(new BidInfo(sentdate, locid, slotindex, durindex));
                    call1.enqueue(new Callback<ArrayList<BidDetail>>() {
                        @Override
                        public void onResponse(Call<ArrayList<BidDetail>> call, Response<ArrayList<BidDetail>> response) {
                            ArrayList<BidDetail> arrayList=response.body();
                            Intent intent=new Intent(AdminActivity.this,BiddingActivity.class);
                            Bundle args = new Bundle();
                            args.putSerializable("ARRAYLIST",(Serializable)arrayList);
                            intent.putExtra("BUNDLE",args);
                            startActivity(intent);
                        }

                        @Override
                        public void onFailure(Call<ArrayList<BidDetail>> call, Throwable t) {

                        }
                    });
                }
            }
        });
    }

    public static class DatePickerFragment extends DialogFragment
            implements DatePickerDialog.OnDateSetListener {

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            final Calendar c = Calendar.getInstance();
            int year = c.get(Calendar.YEAR);
            int month = c.get(Calendar.MONTH);
            int day = c.get(Calendar.DAY_OF_MONTH);
            DatePickerDialog dialog = new DatePickerDialog(getActivity(), this, year, month, day);
            return  dialog;
        }

        public void onDateSet(DatePicker view, int year, int month, int day) {
            sentdate= year+"-"+month+"-"+day;
            dateSelect.setText(year+"-"+month+"-"+day);
        }
    }
}

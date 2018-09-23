package example.com.androidlogin.Attempt;

import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import example.com.androidlogin.EmptyRecyclerView;
import example.com.androidlogin.Login.ApiUtils;
import example.com.androidlogin.Login.UserService;
import example.com.androidlogin.R;
import example.com.androidlogin.Attempt.AttemptRecyclerAdapter;

import fr.ganfra.materialspinner.MaterialSpinner;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by applelab1 on 7/12/18.
 */

public class AttemptActivity extends AppCompatActivity {

    String Subject1;
    String Subject2;
    String Subject3;
    String selectedItemText1;
    String selectedItemText2;
    Boolean status1=false;
    Boolean status2=false;
    FloatingActionButton fab;
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private AttemptRecyclerAdapter adapter;
    private List<Attempt> attempts;
    UserService userService;
    MaterialSpinner yearSpinner,subjectSpinner;
    static String token;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attempt);
        fab=(FloatingActionButton)findViewById(R.id.fab);
        recyclerView=(RecyclerView) findViewById(R.id.recyclerView);
        userService= ApiUtils.getUserService();

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            Subject1 = extras.getString("Subject1");
            Subject2 = extras.getString("Subject2");
            Subject3 = extras.getString("Subject3");
            token=extras.getString("Token");
        }

        //year Spinner
        yearSpinner = (MaterialSpinner) findViewById(R.id.yearSpinner);
        ArrayAdapter<String> myAdapter;
        myAdapter = new ArrayAdapter<String>(AttemptActivity.this, android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.names)) {
            @Override
            public boolean isEnabled(int position){
                if(position >= 0)
                {
                    return true;
                }
                else
                {
                    return false;
                }
            }
            @Override
            public View getDropDownView(int position, View convertView,
                                        ViewGroup parent) {
                View view = super.getDropDownView(position, convertView, parent);
                TextView tv = (TextView) view;
                if(position >= 0){
                    tv.setTextColor(Color.BLACK);
                }
                else {
                    tv.setTextColor(Color.GREEN);
                }
                return view;
            }
        };

        myAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        yearSpinner.setAdapter(myAdapter);
        yearSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedItemText1 = parent.getItemAtPosition(position).toString();
                if(position >= 0){


                        status1=true;
                        check();


                }else{
                    status1=false;
                    check();
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        //subject Spinner
        subjectSpinner = (MaterialSpinner) findViewById(R.id.subjectSpinner);
        List<String> spinnerArray = new ArrayList<String>();

        spinnerArray.add(Subject1);
        spinnerArray.add(Subject2);
        spinnerArray.add(Subject3);

        ArrayAdapter<String> adapter;
        adapter = new ArrayAdapter<String>(AttemptActivity.this, android.R.layout.simple_spinner_item, spinnerArray) {
            @Override
            public boolean isEnabled(int position){
                if(position >= 0)
                {
                    return true;
                }
                else
                {
                    return false;
                }
            }
            @Override
            public View getDropDownView(int position, View convertView,
                                        ViewGroup parent) {
                View view = super.getDropDownView(position, convertView, parent);
                TextView tv = (TextView) view;
                if(position >= 0){
                    tv.setTextColor(Color.BLACK);
                }
                else {
                    tv.setTextColor(Color.GREEN);
                }
                return view;
            }
        };
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        subjectSpinner.setAdapter(adapter);
        subjectSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedItemText2 = parent.getItemAtPosition(position).toString();
                if(position >= 0){


                        status2=true;
                        check();


                }else{
                    status2=false;
                    check();
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                yearSpinner.setSelection(0);
                subjectSpinner.setSelection(0);
                status1=false;
                status2=false;
                check();

            }
        });

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (dy > 0 && fab.getVisibility() == View.VISIBLE) {
                    fab.hide();
                } else if (dy < 0 && fab.getVisibility() != View.VISIBLE) {
                    fab.show();
                }
            }
        });

    }

    private void check() {


        if(status1==false && status2==false){
            System.out.println("show nothing");
            recyclerView.setAdapter(null);

        }else if(status1==true && status2==false){

            System.out.println("Show 1 only");

            Call<List<Attempt>> call=userService.getAttemptByYear(selectedItemText1,token);
            //System.out.println(selectedItemText1);

            call.enqueue(new Callback<List<Attempt>>() {
                @Override
                public void onResponse(Call <List<Attempt>> call, Response<List<Attempt>> response) {

                    layoutManager= new LinearLayoutManager(AttemptActivity.this);
                    recyclerView.setLayoutManager(layoutManager);
                    recyclerView.setHasFixedSize(true);

                    attempts =response.body();
                    adapter=new AttemptRecyclerAdapter(attempts);
                    recyclerView.setAdapter(adapter);

                }
                @Override
                public void onFailure(Call<List<Attempt>> call, Throwable t) {

                    recyclerView.setAdapter(null);

                }
            });

        }else if(status1==false && status2==true){
            System.out.println("Show 2 only");


            Call<List<Attempt>> call=userService.getAttemptBySubject(selectedItemText2,token);
            //System.out.println(selectedItemText1);

            call.enqueue(new Callback<List<Attempt>>() {
                @Override
                public void onResponse(Call <List<Attempt>> call, Response <List<Attempt>> response) {

                    layoutManager= new LinearLayoutManager(AttemptActivity.this);
                    recyclerView.setLayoutManager(layoutManager);
                    recyclerView.setHasFixedSize(true);

                    attempts =response.body();
                    adapter=new AttemptRecyclerAdapter(attempts);
                    recyclerView.setAdapter(adapter);

                }
                @Override
                public void onFailure(Call<List<Attempt>> call, Throwable t) {

                    recyclerView.setAdapter(null);

                }
            });




        }else if(status1==true && status2==true){

            System.out.println("Show both only");

            Call<List<Attempt>> call=userService.getAttemptByYearAndSubject(selectedItemText2,selectedItemText1,token);
            //System.out.println(selectedItemText1);

            call.enqueue(new Callback<List<Attempt>>() {
                @Override
                public void onResponse(Call <List<Attempt>> call, Response <List<Attempt>> response) {

                    layoutManager= new LinearLayoutManager(AttemptActivity.this);
                    recyclerView.setLayoutManager(layoutManager);
                    recyclerView.setHasFixedSize(true);

                    attempts =response.body();
                    adapter=new AttemptRecyclerAdapter(attempts);
                    recyclerView.setAdapter(adapter);

                }
                @Override
                public void onFailure(Call<List<Attempt>> call, Throwable t) {

                    recyclerView.setAdapter(null);

                }
            });

        }
    }

}

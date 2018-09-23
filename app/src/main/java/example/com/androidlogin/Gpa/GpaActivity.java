package example.com.androidlogin.Gpa;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import example.com.androidlogin.Login.ApiUtils;
import example.com.androidlogin.Login.LoginActivity;
import example.com.androidlogin.Login.MainActivity;

import example.com.androidlogin.Login.UserService;
import example.com.androidlogin.R;
import example.com.androidlogin.Result.Result;
import example.com.androidlogin.Result.ResultActivity;
import example.com.androidlogin.Result.ResultRecyclerAdapter;
import fr.ganfra.materialspinner.MaterialSpinner;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by applelab1 on 7/12/18.
 */

public class GpaActivity extends AppCompatActivity {

    String Subject1;
    String Subject2;
    String Subject3;
    String Grand;
    String selectedItemText1;
    Boolean status1=false;
    RelativeLayout relative;
    UserService userService;
    String Credits;
    String gpa;
    TextView textCredit;
    TextView textGpa;
    FloatingActionButton fab;
    MaterialSpinner subjectSpinner;
    static String token;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gpa);
        relative=(RelativeLayout)findViewById(R.id.relative);
        fab=(FloatingActionButton)findViewById(R.id.fab);
        userService= ApiUtils.getUserService();
        textCredit=(TextView)findViewById(R.id.textCredit);
        textGpa=(TextView)findViewById(R.id.textGpa);
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            Subject1 = extras.getString("Subject1");
            Subject2 = extras.getString("Subject2");
            Subject3 = extras.getString("Subject3");
            Grand=extras.getString("Grand");
            token=extras.getString("Token");

        }

        //subject Spinner
        subjectSpinner = (MaterialSpinner) findViewById(R.id.subjectSpinner);
        List<String> spinnerArray = new ArrayList<String>();

        spinnerArray.add(Subject1);
        spinnerArray.add(Subject2);
        spinnerArray.add(Subject3);
        spinnerArray.add(Grand);


        ArrayAdapter<String> adapter;
        adapter = new ArrayAdapter<String>(GpaActivity.this, android.R.layout.simple_spinner_item, spinnerArray) {
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


        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                subjectSpinner.setSelection(0);
                status1=false;

                check();

            }
        });


    }


    private void check() {


        if(status1==false ){
            System.out.println("show nothing");

            if (relative .getVisibility() == View.INVISIBLE) {
                relative.setVisibility(View.INVISIBLE);
            }else {
                if (relative.getVisibility() == View.VISIBLE)
                    relative.setVisibility(View.INVISIBLE);
            }


        }else{

            System.out.println("Show 1 only");
            if (relative .getVisibility() == View.INVISIBLE) {
                relative.setVisibility(View.VISIBLE);
            }else {
                relative.setVisibility(View.VISIBLE);
            }

            System.out.println(selectedItemText1);
            Call <Gpa> call=userService.getGpaBySubject(selectedItemText1,token);
            call.enqueue(new Callback<Gpa>() {
                @Override
                public void onResponse(Call<Gpa> call, Response<Gpa> response) {

                    if(response.isSuccessful()){
                        Gpa resObj=response.body();
                        if(resObj.getMessage().equals("true")){

                            Credits=response.body().getData().get(0).getNoOfCredits();
                            gpa=response.body().getData().get(0).getGpa();

                          textCredit.setText(Credits);
                          textGpa.setText(gpa);
                        }else{
                            Toast.makeText(GpaActivity.this,"Error1",Toast.LENGTH_SHORT).show();
                        }
                    }else{
                        Toast.makeText(GpaActivity.this,"Error 2",Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<Gpa> call, Throwable t) {
                    Toast.makeText(GpaActivity.this,"Error 3",Toast.LENGTH_SHORT).show();
                }
            });


        }

    }
}





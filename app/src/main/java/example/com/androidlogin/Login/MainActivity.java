package example.com.androidlogin.Login;

import android.content.Intent;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import example.com.androidlogin.Attempt.AttemptActivity;
import example.com.androidlogin.Gpa.GpaActivity;
import example.com.androidlogin.R;
import example.com.androidlogin.Rank.rankActivity;
import example.com.androidlogin.Result.ResultActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    TextView txtUsername;

    String username;
    String StdName;
    String Combination;
    String Subject1;
    String Subject2;
    String Subject3;
    UserService userService;
    String Grand;
    CardView resultCard;
    CardView attemptCard;
    CardView gpaCard;
    CardView rankCard;
    long mLastClickTime = 0;
    static String token;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtUsername=(TextView)findViewById(R.id.txtUsername);
        resultCard=(CardView)findViewById(R.id.resultCard);
        attemptCard=(CardView)findViewById(R.id.attemptCard);
        gpaCard=(CardView)findViewById(R.id.gpaCard);
        rankCard=(CardView)findViewById(R.id.rankCard);

        userService= ApiUtils.getUserService();
        Grand="Grand";
        Bundle extras=getIntent().getExtras();



        if(extras!=null){

            StdName=extras.getString("lastWord");
            Combination=extras.getString("Combination");
            txtUsername.setText("Welcome "+StdName);
            token=extras.getString("Token");
        }


        Call<ResObj> call=userService.getSubjects(Combination,token);
        call.enqueue(new Callback<ResObj>() {
            @Override
            public void onResponse(Call<ResObj> call, Response<ResObj> response) {

                if(response.isSuccessful()){
                    ResObj resObj=response.body();
                    if(resObj.getMessage().equals("true")){

                        Subject1=response.body().getData().get(0).getSubject1();
                        Subject2=response.body().getData().get(0).getSubject2();
                        Subject3=response.body().getData().get(0).getSubject3();

                    }else{
                        Toast.makeText(MainActivity.this,"not exist",Toast.LENGTH_SHORT).show();
                    }
                }else{
                    Toast.makeText(MainActivity.this,"No results",Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onFailure(Call<ResObj> call, Throwable t) {
                Toast.makeText(MainActivity.this,t.getMessage(),Toast.LENGTH_SHORT).show();
            }
        });

        resultCard.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {
            if (SystemClock.elapsedRealtime() - mLastClickTime < 1000){
                return;
            }
            mLastClickTime = SystemClock.elapsedRealtime();

                Intent intent=new Intent(MainActivity.this, ResultActivity.class);

                intent.putExtra("Subject1", Subject1);
                intent.putExtra("Subject2", Subject2);
                intent.putExtra("Subject3", Subject3);
                intent.putExtra("Token",token);
                startActivity(intent);


            }
        });

        attemptCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (SystemClock.elapsedRealtime() - mLastClickTime < 1000){
                    return;
                }
                mLastClickTime = SystemClock.elapsedRealtime();

                Intent intent=new Intent(MainActivity.this, AttemptActivity.class);

                intent.putExtra("Subject1", Subject1);
                intent.putExtra("Subject2", Subject2);
                intent.putExtra("Subject3", Subject3);
                intent.putExtra("Token",token);
                startActivity(intent);
            }
        });

        gpaCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (SystemClock.elapsedRealtime() - mLastClickTime < 1000){
                    return;
                }
                mLastClickTime = SystemClock.elapsedRealtime();
                Intent intent=new Intent(MainActivity.this, GpaActivity.class);

                intent.putExtra("Subject1", Subject1);
                intent.putExtra("Subject2", Subject2);
                intent.putExtra("Subject3", Subject3);
                intent.putExtra("Grand",Grand);
                intent.putExtra("Token",token);
                startActivity(intent);
            }
        });


        rankCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (SystemClock.elapsedRealtime() - mLastClickTime < 1000){
                    return;
                }
                mLastClickTime = SystemClock.elapsedRealtime();
                Intent intent=new Intent(MainActivity.this, rankActivity.class);

                intent.putExtra("Subject1", Subject1);
                intent.putExtra("Subject2", Subject2);
                intent.putExtra("Subject3", Subject3);
                intent.putExtra("Token",token);
                startActivity(intent);
            }
        });
    }





    }


package example.com.androidlogin.Login;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.SystemClock;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;
import android.util.Log;
import example.com.androidlogin.R;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity implements TextWatcher,
        CompoundButton.OnCheckedChangeListener{

    EditText edtUsername;
    EditText edtPassword;
    Button btnLogin;
    UserService userService;
    String username;
    String password;
    private CheckBox rem_userpass;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    private static final String PREF_NAME = "prefs";
    private static final String KEY_USERNAME = "username";
    private static final String KEY_PASS = "password";
    private static final String KEY_REMEMBER = "remember";
    long mLastClickTime = 0;
    TextInputLayout textInputLayout3;
    TextInputLayout textInputLayout2;
    private static final String TAG = "LoginActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        textInputLayout2= (TextInputLayout)findViewById(R.id.textInputLayout2);
        textInputLayout3= (TextInputLayout)findViewById(R.id.textInputLayout3);
        edtUsername=(EditText)findViewById(R.id.edtUsername);
        edtPassword=(EditText) findViewById(R.id.edtPassword);
        btnLogin=(Button)findViewById(R.id.btnLogin);
        userService= ApiUtils.getUserService();

        sharedPreferences = getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();

        rem_userpass = (CheckBox)findViewById(R.id.checkBox);
        if(sharedPreferences.getBoolean(KEY_REMEMBER, false))
            rem_userpass.setChecked(true);
        else
            rem_userpass.setChecked(false);

        edtUsername.setText(sharedPreferences.getString(KEY_USERNAME,""));
        edtPassword.setText(sharedPreferences.getString(KEY_PASS,""));

        edtUsername.addTextChangedListener(this);
        edtPassword.addTextChangedListener(this);
        rem_userpass.setOnCheckedChangeListener(this);



        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (SystemClock.elapsedRealtime() - mLastClickTime < 1000){
                    return;
                }
                mLastClickTime = SystemClock.elapsedRealtime();
                username=edtUsername.getText().toString();
                password=edtPassword.getText().toString();

                if(validateLogin(username,password)){

                    doLogin(username,password);
                }
            }
        });

    }
    private boolean validateLogin(String username,String password){
        if(username==null || username.trim().length()==0){
//
            textInputLayout3.setError("Username is required");
            Log.i(TAG,"Enter a valid username");
            return false;
        }else{
            textInputLayout3.setError(null);
            Log.i(TAG,"username entered");
        }

        if(password==null || password.trim().length()==0){
            textInputLayout2.setError("Password is required");
            Log.i(TAG,"Enter a valid password");
            return false;
        }else{
            textInputLayout2.setError(null);
            Log.i(TAG,"password entered");
        }
        Log.i(TAG,"both username and password entered");
           return true;
    }

    private void doLogin(final String username,final String password){

        Call<ResObj> call=userService.login(username,password);
        call.enqueue(new Callback<ResObj>() {
            @Override
            public void onResponse(Call<ResObj> call, Response<ResObj> response) {

                if(response.isSuccessful()){
                    ResObj resObj=response.body();
                    if(resObj.getMessage().equals("true")){

                    Intent intent=new Intent(LoginActivity.this, MainActivity.class);

                        String StdName=response.body().getData().get(0).getStdName();

                        String lastWord=StdName.substring(StdName.lastIndexOf(" ")+1);
                        System.out.println(lastWord);
                        String Combination=response.body().getData().get(0).getCombination();
                        String token=response.body().getToken();
                        intent.putExtra("username",username);
                        intent.putExtra("lastWord",lastWord);
                        intent.putExtra("Combination",Combination);
                        intent.putExtra("Token",token);
                        intent.putExtra("KEY_USERNAME",KEY_USERNAME);
                        intent.putExtra("KEY_PASS",KEY_PASS);
                        startActivity(intent);

                    }else{
                        Toast.makeText(LoginActivity.this,"The username or password is incorrect",Toast.LENGTH_SHORT).show();
                        edtPassword.setText("");

                    }
                }else{
                    Toast.makeText(LoginActivity.this,"Error please try again",Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onFailure(Call<ResObj> call, Throwable t) {
                Toast.makeText(LoginActivity.this,t.getMessage(),Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        managePrefs();
    }

    private void managePrefs() {
        if(rem_userpass.isChecked()){
            editor.putBoolean(KEY_REMEMBER, true);
            editor.putString(KEY_USERNAME, edtUsername.getText().toString().trim());
            editor.putString(KEY_PASS, edtPassword.getText().toString().trim());
            editor.apply();
        }else{
            editor.putBoolean(KEY_REMEMBER, false);
            editor.remove(KEY_PASS);
            editor.remove(KEY_USERNAME);
            editor.apply();
        }
    }

    @Override
    public void afterTextChanged(Editable editable) {

    }

    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
        managePrefs();
    }
}

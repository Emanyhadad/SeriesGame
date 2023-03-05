package com.example.final_project;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.final_project.databinding.LoginActivityBinding;

public class Login extends AppCompatActivity {

    LoginActivityBinding binding;
    SharedPreferences sp;
    SharedPreferences.Editor editor;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = LoginActivityBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        sp = getSharedPreferences(Register.PREF_NAME ,MODE_PRIVATE);
        editor = sp.edit();


        // استقبال البيانات
        if(getIntent() != null){
            String EML = getIntent().getStringExtra(Register.USERNAME_KEY);
            String PAS = getIntent().getStringExtra(Register.PASSWORD_KEY);
            // if there's no retrieved data from sign up activity :
            if( EML != null && PAS != null ){
                binding.edUsersName.setText(EML);
                binding.edPass.setText(PAS);
            }
        }
        else{
            Toast.makeText(getBaseContext(), "No retrieved Data", Toast.LENGTH_SHORT).show();
        }

        //للذهاب لشاشة تسجيل مستخدم جديد
        binding.tvSignup.setOnClickListener(view -> {
            Intent intent = new Intent(getApplicationContext(), Register.class);
            startActivity(intent);
        });

        //للذهاب للعبة بعد اتمام عملية الدخول
        binding.btnLogin.setOnClickListener(view -> {
            getData();
        });


    }


    public void getData() {
        String em = binding.edUsersName.getText().toString().trim();
        String ps = binding.edPass.getText().toString().trim();

        String sp_username = sp.getString(Register.USERNAME_KEY,"No");
        String sp_password = sp.getString(Register.PASSWORD_KEY,"No");

        if(!(ps.isEmpty())&&(!em.isEmpty())&&em.equals(sp_username) && ps.equals(sp_password)){

            if(binding.cbRemember.isChecked()){
                editor.putBoolean(Register.RememberK,true);
                editor.apply();
            }else {
                editor.putBoolean(Register.RememberK, false);
                editor.apply();
            }

            Intent intent1 = new Intent(getApplicationContext(), Rules.class);
            startActivity(intent1);
            Toast.makeText(getBaseContext(), "successfully", Toast.LENGTH_SHORT).show();

        } else {
            Toast.makeText(getBaseContext(), "Invalid Information", Toast.LENGTH_SHORT).show();
        }
    }







    }

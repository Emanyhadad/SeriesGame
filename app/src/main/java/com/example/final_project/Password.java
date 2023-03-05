package com.example.final_project;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.final_project.databinding.ActivityPasswordBinding;

public class Password extends AppCompatActivity {
ActivityPasswordBinding binding;
    MyDatabase myDB;
    public SharedPreferences sp;
    public SharedPreferences.Editor editor;
    public final String PASSWORD_KEY = "PASSWORD";
    public String NewPass , ReNewPass , OldPass;
   public Boolean result;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= ActivityPasswordBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.btnSavePass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NewPass = binding.edNewPass.getText().toString().trim();
                ReNewPass = binding.edRENewPass.getText().toString().trim();
                OldPass = binding.edOldPass.getText().toString().trim();

                if (!NewPass.equals("" ) && !NewPass.equals("" ) && !NewPass.equals("" )) {
                    result = Register.ChangePass(NewPass, ReNewPass, OldPass);

                    // if true = password has changed , else = false = didn't change for some reason .
                    if (result) {

                        String newPassTxt = Register.getPass();
                        Toast.makeText( Password.this , "Password has changed SUCCESSFULLY !"+ newPassTxt, Toast.LENGTH_SHORT ).show( );

                    } else {
                        Toast.makeText(Password.this , "New Passwords May Not Match or Old Password is wrong ", Toast.LENGTH_LONG).show();}

                }else {
                    Toast.makeText(Password.this , "one or more feild are empty ! ", Toast.LENGTH_LONG).show();}

            }
        });

    }



  }
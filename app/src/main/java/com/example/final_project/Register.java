package com.example.final_project;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.icu.util.Calendar;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.example.final_project.databinding.RegisterActivityBinding;

public class Register extends AppCompatActivity {
    public static final int CAMERA_PERMISSION_CODE = 100;
   public static final int STORAGE_PERMISSION_CODE = 101;
    public static final int PERMISSION_REQUEST_CODE = 1;
    RegisterActivityBinding binding;
    public int age1;
    static SharedPreferences sp;
    static SharedPreferences.Editor editor;
    public static final String USERNAME_KEY = "username";
    public static final String RememberK = " Remember_K";
    public static final String FULL_NAME_KEY = "full_name";
    public  static final String EMAIL_KEY = "email";
    public static final String PASSWORD_KEY = "PASSWORD";
    public static final String RE_PASSWORD_KEY = "RE_PASSWORD";
    public static final String BD_KEY = "BD";
    public  static final String COUNTRY_KEY = "COUNTRY";
    public static final String AGE_KEY ="age";
    public static final String GENDER_KEY="G_K";
    public static final String PHOTO_KEY="P_K";
    public static String res;
    public static final String PREF_NAME = "RegisterPreferences";
    public static Uri UriImage ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= RegisterActivityBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        

        sp = getSharedPreferences(PREF_NAME ,MODE_PRIVATE);
        editor=sp.edit();

        //check if permission is already taken from user or not , returns INT .
        // compact = support library
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            // if permission isn't granted take it in thins activity
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                    PERMISSION_REQUEST_CODE);
        }


        //لوضع حد أخذ صورة عند الضغط على التقاط وتخزينها في الصورة
        ActivityResultLauncher<String[]> A=
                registerForActivityResult( new ActivityResultContracts.
                                OpenDocument( ) ,
                        result -> {
                            UriImage = result;
                            // Check for the freshest data.

                            getContentResolver().takePersistableUriPermission(result,
                                    (Intent.FLAG_GRANT_READ_URI_PERMISSION
                                    | Intent.FLAG_GRANT_WRITE_URI_PERMISSION));

                            binding.imRegisterUserImage.setImageURI(result);
                        } );


        binding.tvRegisterPic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkPermission(
                        Manifest.permission.WRITE_EXTERNAL_STORAGE,
                        STORAGE_PERMISSION_CODE);
                A.launch(new String[]{"image/*"});
            }
        });
        //fot date
        binding.edRegisterBD.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View view) {
                password();
                Calendar now = Calendar.getInstance();
                com.wdullaer.materialdatetimepicker.date.DatePickerDialog dpd =
                        com.wdullaer.materialdatetimepicker.date.DatePickerDialog.newInstance(
                                new com.wdullaer.materialdatetimepicker.date.DatePickerDialog.OnDateSetListener() {
                                    @Override
                                    public void onDateSet(com.wdullaer.materialdatetimepicker
                                                                  .date.DatePickerDialog view, int year, int monthOfYear, int dayOfMonth) {
                                        binding.edRegisterBD.setText(dayOfMonth+"/"+(monthOfYear+1) +"/"+year);
                                        age1 = now.get(Calendar.YEAR)-year;
                                    }
                                },
                                now.get(Calendar.YEAR),
                                now.get(Calendar.MONTH),
                                now.get(Calendar.DAY_OF_MONTH)

                        );dpd.show(getSupportFragmentManager(), "Datepickerdialog");
            }
        });
        binding.edRegisterEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                full_name();
            }
        });
        binding.edRegisterUsername.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Email();
            }
        });
        binding.edRegisterPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                user_name();
            }
        });
        binding.btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkAll();

            }
        });
        binding.tvSignin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplicationContext(),Login.class);
                startActivity(intent);
                finish();
            }
        });
    }

    // Function to check and request permission.
    public void checkPermission(String permission, int requestCode)
    {
        if (ContextCompat.checkSelfPermission(Register.this, permission)
                == PackageManager.PERMISSION_DENIED) {

            // Requesting the permission
            ActivityCompat.requestPermissions(Register.this,
                    new String[] { permission },
                    requestCode);
        }
        else {
            Toast.makeText(Register.this,
                    "Permission already granted",
                    Toast.LENGTH_SHORT)
                    .show();
        }
    }

    public boolean full_name(){
        String FullName=binding.edRegisterFullName.getText().toString().trim();
        if (FullName.isEmpty()){
            binding.edRegisterFullName.setError("FULL NAME IS REQUIRED");
            binding.edRegisterFullName.requestFocus();
            return false;
        }else return true;
    }
    public boolean Email(){
        String Email=binding.edRegisterEmail.getText().toString().trim();
        if (Email.isEmpty()){
            binding.edRegisterEmail.setError("EMAIL IS REQUIRED");
            binding.edRegisterEmail.requestFocus();
            return false;
        }
        else if (!Email.matches("[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+")){
            binding.edRegisterEmail.setError("PLEASE PROVIDE VALID EMAIL ");
            binding.edRegisterEmail.requestFocus();
            return false;
        }else return true;}
    public boolean user_name(){
        String UserName=binding.edRegisterUsername.getText().toString().trim();
        if (UserName.isEmpty()){
            binding.edRegisterUsername.setError("USER NAME IS REQUIRED");
            binding.edRegisterUsername.requestFocus();
            return false;
        }else return true;
    }
    public boolean password() {
        String Password=binding.edRegisterPassword.getText().toString().trim();
        String RE_Password=binding.edRegisterRepass.getText().toString().trim();
        if (Password.isEmpty()){
            binding.edRegisterPassword.setError("PASSWORD IS REQUIRED");
            binding.edRegisterPassword.requestFocus();
            return false;
        }
        else   if (Password.length()<6){
            binding.edRegisterPassword.setError("MIN PASSWORD LENGTH SHOULD BE 6 CHARACTERS !");
            binding.edRegisterPassword.requestFocus();
            return false;
        }
        else if (!RE_Password.equals(Password)){
            binding.edRegisterRepass.setError("IT MUST MATCH THE PASSWORD!");
            binding.edRegisterRepass.requestFocus();
            return false;
        }else return true;


    }
    public void checkAll(){
        if (full_name()&&Email()&&user_name()&&password())
        {
            save_data();
        }else
            Toast.makeText(this, "Complete the deficiency", Toast.LENGTH_SHORT).show();
    }
    public void save_data(){

        String FullName=binding.edRegisterFullName.getText().toString();
        String  Email=binding.edRegisterEmail.getText().toString();
        String  UserName=binding.edRegisterUsername.getText().toString();
        String  BD=binding.edRegisterBD.getText().toString();
        int age = age1;
        String Password=binding.edRegisterPassword.getText().toString();
        String  Country = binding.spRegisterCountry.getSelectedItem().toString();

        editor.putString(USERNAME_KEY,UserName);
        editor.putString(FULL_NAME_KEY,FullName);
        editor.putString(EMAIL_KEY,Email);
        editor.putString(PASSWORD_KEY,Password);
        editor.putString(BD_KEY,BD);
        editor.putString(COUNTRY_KEY,Country);
        editor.putString(AGE_KEY, String.valueOf(age));
        if (!(UriImage ==null)){
            editor.putString(PHOTO_KEY, String.valueOf(UriImage) );
        }


        //editor.putString(PHOTO_KEY, res);

        if (binding.rbRegisterMale.isClickable()) {
            editor.putString(GENDER_KEY,"Male");
        } else  if (binding.rbRegisterFemale.isClickable())
            editor.putString(GENDER_KEY, "Female");

        editor.apply();

        Intent intent = new Intent(getBaseContext(),Login.class);
        intent.putExtra(USERNAME_KEY, UserName);
        intent.putExtra(PASSWORD_KEY, Password);
        startActivity(intent);

        Toast.makeText(this, "save well done", Toast.LENGTH_SHORT).show();

    }


    public static boolean ChangePass(String NewPass, String ReNewPass, String OldPass){
        String SavedPass =  sp.getString(PASSWORD_KEY, null) ;
        if( OldPass.equals(SavedPass) && ReNewPass.equals(NewPass)){
            editor.putString(PASSWORD_KEY, NewPass);
            editor.putString(RE_PASSWORD_KEY, ReNewPass);
            editor.apply();
            return true ;
        }else {
            return false;
        }
    }

    public static String getPass(){
        return sp.getString(PASSWORD_KEY,"");}


    private void requestPermissions() {
        if(ActivityCompat.shouldShowRequestPermissionRationale(this,Manifest.permission.READ_EXTERNAL_STORAGE)){
            new AlertDialog.Builder(this).setTitle("Permission Needed")
                    .setMessage("This permission is needed for your Profile Photo")
                    .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            ActivityCompat.requestPermissions(
                                    Register.this,new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},PERMISSION_REQUEST_CODE); }
                    }).setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    dialogInterface.dismiss();
                }
            }).create().show();
        } else ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},PERMISSION_REQUEST_CODE);}


@Override
public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
    super.onRequestPermissionsResult(requestCode, permissions, grantResults);

    switch (requestCode) {
        case PERMISSION_REQUEST_CODE: {
            //مفروض افحص انه نفس البيرمشن بالريكوست الي فوق
            // بعمل دالة للمطلوب ننفذه هنا
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this, "PERMISSION GRANTED", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "PERMISSION DENIED", Toast.LENGTH_SHORT).show();
            }
            break;
        }
    }}}

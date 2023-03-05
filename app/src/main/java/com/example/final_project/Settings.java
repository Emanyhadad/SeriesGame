package com.example.final_project;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.final_project.databinding.SettingsActivityBinding;

import java.util.ArrayList;

public class Settings extends AppCompatActivity {
SettingsActivityBinding binding;
    MyDatabase myDB;
    ArrayList<ModelClass> list;
    RecyclerViewAdapter customAdapter;
    public SharedPreferences sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=SettingsActivityBinding.inflate(getLayoutInflater());

        setContentView(binding.getRoot());


        binding.tvAllGames.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplicationContext(), AllGAME.class);
                startActivity(intent);
            }
        });


        binding.tvChPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplicationContext(),Password.class);
                startActivity(intent);
            }
        });

        binding.tvClearGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder delete_message=new AlertDialog.Builder(Settings.this);
                delete_message.setTitle("Clear History")//لعنوان مربع الحوار
                        .setMessage("Are you sure you want to delete your games history?")//السؤال

                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override // في حال كان نعم نفذ:
                            public void onClick(DialogInterface dialogInterface, int i) {
                                MyDatabase db=new MyDatabase(Settings.this);
                                db.ClearGame();
                            }
                        })

                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override // في حال كان لا الغي مربع الحوار
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.cancel();
                            }
                        });

                AlertDialog dialog=delete_message.create(); // أنشئ مربع الحوار
                dialog.show(); // أظهر مربع الحوار

            }
        });

        binding.tvLastGame.setOnClickListener( new View.OnClickListener( ) {
            @Override
            public void onClick( View view ) {
                myDB=new MyDatabase(Settings.this);
                String date=myDB.getLastGames();
                Toast.makeText( Settings.this , date , Toast.LENGTH_SHORT ).show( );
            }
        } );
        binding.tvLogout.setOnClickListener( new View.OnClickListener( ) {
            @Override
            public void onClick( View view ) {
                SharedPreferences SP = getSharedPreferences(Register.PREF_NAME ,MODE_PRIVATE);
                SharedPreferences.Editor EDIT = SP.edit();

                EDIT.putBoolean(Register.RememberK,false);
                EDIT.apply();

                Intent intent1 = new Intent(Settings.this, Login.class);
                // اغلق كل الشاشات التطبيق
                intent1.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent1);

                Toast.makeText(Settings.this, "Sign out", Toast.LENGTH_SHORT).show();

            }
        } );
        binding.tvGameRules.setOnClickListener( new View.OnClickListener( ) {
            @Override
            public void onClick( View view ) {
                Intent intent = new Intent( getApplicationContext(), Rules.class );
                startActivity( intent );
            }
        } );


        binding.tvContactUs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =new Intent(Intent.ACTION_SENDTO);
                String data= "emanhadad17@gmail.com";
                intent.setData( Uri.parse("mailto:"+data));
                startActivity(intent);
            }
        });
    }

}
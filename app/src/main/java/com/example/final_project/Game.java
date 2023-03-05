package com.example.final_project;

import static com.example.final_project.R.drawable.falseans;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.icu.util.Calendar;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.example.final_project.databinding.GameActivityBinding;

import java.text.SimpleDateFormat;

public class Game extends AppCompatActivity {
   GameActivityBinding binding;

    public int n_score;
    public String [][] data;
    public String [][] x;
    public Question question;
    public int hiddenNumber;
    public SharedPreferences sp;
    SharedPreferences.Editor editor;
    Calendar calendar;
    SimpleDateFormat simpleDateFormat;
    String DateTime;

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= GameActivityBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.toolbar);//عشان يخلي التول بار الجديد هو الافتراضي
        setTitle("");//عشان يحذف العنوان
        name_age();//عشان يسترجع الاسم والعمر ويخزنهم في المتغيرات
        new_Game();//عشان يبدأ لعبة جديد
        binding.btnNewGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new_Game();
            }
        });
    }


    @SuppressLint("UseCompatLoadingForDrawables")
    public void Q(){

        data= Util.generteQuestion().getData().clone();//مصفوفة
        hiddenNumber = Util.generteQuestion().getHiddenNumber();//رقم
        question=new Question(data,hiddenNumber);//أوبجكت
        x = question.getData();//مصفوفة
        String num1 = x[0][0];
        String num2 = x[0][1];
        String num3 = x[0][2];
        String num4 = x[1][0];
        String num5 = "??";
        String num6 = x[1][2];
        String num7 = x[2][0];
        String num8 = x[2][1];
        String num9 = x[2][2];
        binding.tvNum1.setText(num1);
        binding.tvNum2.setText(num2);
        binding.tvNum3.setText(num3);
        binding.tvNum4.setText(num4);
        binding.tvNum5.setText(num5);
        binding.tvNum5.setBackground(getResources().getDrawable(R.drawable.num));
        binding.tvNum6.setText(num6);
        binding.tvNum7.setText(num7);
        binding.tvNum8.setText(num8);
        binding.tvNum9.setText(num9);

    }//استيراد الاسئلة
    public void true_ans(){
        binding.btnCheck.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @SuppressLint("UseCompatLoadingForDrawables")
            @Override
            public void onClick(View view) {
                int o_score= Integer.parseInt(binding.tvScore.getText().toString());
                if (binding.edNumber.getText().toString().equals(x[1][1])){//اذا كان الاجابة تساوي الرقم المخفي

                    //يظهر الاجابة جواته
                    n_score= o_score+10;//يزيد السكور
                    MediaPlayer song = MediaPlayer.create(getApplicationContext(), R.raw.correctanswersound);//يستورد ملف الصوت
                    song.start();//يطلع صوت اجابة صحيحة
                    LayoutInflater inflater = getLayoutInflater();
                    View layout = inflater.inflate(R.layout.toast_layout,
                            (ViewGroup) findViewById(R.id.toast_layout_root));
                    Toast toast = new Toast(getApplicationContext());//يطبع اجابة صحيحة
                    toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
                    toast.setDuration(Toast.LENGTH_SHORT);
                    toast.setView(layout);
                    toast.show();
                    binding.tvNum5.setBackground(getResources().getDrawable(R.drawable.trueans));//يركب الفيو الاخضر
                    binding.tvNum5.setText(x[1][1]);
                    new_Game();//يبدأ لعبة جديدة
                }
                else {
                    MediaPlayer song = MediaPlayer.create(getApplicationContext(), R.raw.wrongclaksonsoundeffect);
                    song.start();
                    LayoutInflater inflater = getLayoutInflater();
                    View layout = inflater.inflate(R.layout.toast_layout1,
                            findViewById(R.id.toast_layout_root));
                    Toast toast = new Toast(getApplicationContext());
                    toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
                    toast.setDuration(Toast.LENGTH_SHORT);
                    toast.setView(layout);
                    toast.show();
                    binding.tvNum5.setBackground(getResources().getDrawable(falseans));
                    binding.tvNum5.setText("??");
                }
                binding.tvScore.setText(String.valueOf(n_score));//يرجع السكور الجديد
            }}); }//فحص الاجابة وزيادة السكور
    public void new_Game(){
        Q();
        true_ans();
    }
    public void name_age(){

        sp = getSharedPreferences(Register.PREF_NAME ,MODE_PRIVATE);
        editor = sp.edit();
        String FullName= sp.getString(Register.FULL_NAME_KEY,"NoName");
        binding.tvFullName.setText(FullName);
        String Age= sp.getString(Register.AGE_KEY,"NoAge");
        binding.tvAge.setText(Age);
        String uri = sp.getString(Register.PHOTO_KEY,null);
        if (!( TextUtils.isEmpty(uri))) {
            binding.imageView.setImageURI( Uri.parse( uri ) );
        }
    }
    @SuppressLint("SimpleDateFormat")
    @RequiresApi(api = Build.VERSION_CODES.N)
    public void db(){
        calendar=Calendar.getInstance();
        simpleDateFormat=new SimpleDateFormat("dd-MM-yyyy - HH:mm:ss");
        DateTime=simpleDateFormat.format(calendar.getTime());
        String Full_name=sp.getString(Register.FULL_NAME_KEY,"");
        int Score =Integer.parseInt(binding.tvScore.getText().toString());
        MyDatabase my_db=new MyDatabase(Game.this);
        ModelClass game=new ModelClass(Score,Full_name,DateTime);
        boolean result=my_db.insert_Game(game);
        if (result)
            Toast.makeText(Game.this, "Successful", Toast.LENGTH_SHORT).show();
        else
            Toast.makeText(Game.this, "Error occurred", Toast.LENGTH_SHORT).show();
    }

    @RequiresApi(api = Build.VERSION_CODES.N)

    protected void onPause() {
        super.onPause();
        int f = Integer.parseInt(binding.tvScore.getText().toString());
        if (f>0){
        db();
        binding.tvScore.setText( "0" );
    }}

//    @Override
//    protected void onDestroy() {
//        super.onDestroy();
//        int f = Integer.parseInt(binding.tvScore.getText().toString());
//        if (f>0){
//            db();
//            binding.tvScore.setText("0");
//    }}


    //عشان يركب المنيو على التول بار
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);
        return true;
    }
    //لعمل ليسنر على المنيو
    @SuppressLint("NonConstantResourceId")
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case  R.id.menu_setting:
                Intent intent = new Intent(getBaseContext(),Settings.class);
                startActivity(intent);
                return true;
            case R.id.menu_sign_out:
                SharedPreferences SP = getSharedPreferences(Register.PREF_NAME ,MODE_PRIVATE);
                SharedPreferences.Editor EDIT = SP.edit();

                EDIT.putBoolean(Register.RememberK,false);
                EDIT.apply();

                Intent intent1 = new Intent(Game.this, Login.class);
                // اغلق كل الشاشات التطبيق
                intent1.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent1);
                finish();
                Toast.makeText(Game.this, "Sign out", Toast.LENGTH_SHORT).show();

                return true;
        }
        return false;
    }


}
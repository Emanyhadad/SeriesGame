package com.example.final_project;
import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import java.util.ArrayList;

public class MyDatabase extends SQLiteOpenHelper {
    private static final String DB_NAME="GAMES.db";
    private static final int version=3;
    Context context;
    private static final String TABLE_NAME = "my_games";
    private static final String COLUMN_ID="ID";
    private static final String COLUMN_FULL_NAME="FULL_NAME";
    private static final String COLUMN_TIME_DATE="TIME_DATE";
    private static final String COLUMN_SCORE="SCORE";

    public MyDatabase(Context context) {
        super(context, DB_NAME, null, version);
        this.context=context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE "+TABLE_NAME+
                " ("+COLUMN_ID+" INTEGER PRIMARY KEY,"+
                COLUMN_FULL_NAME + " TEXT,"+
                COLUMN_TIME_DATE+" TEXT,"+
                COLUMN_SCORE+" INTEGER);";
        db.execSQL(query);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db , int i, int i1) {
        //يتم استدعاءها عن كل تحديث للداتابيس، يعني تغير الفيرجن
        db.execSQL(" DROP TABLE IF EXISTS "+TABLE_NAME);
        onCreate(db);
    }
    public boolean insert_Game(ModelClass game){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues cv=new ContentValues();
        cv.put(COLUMN_FULL_NAME,game.GAME_FULL_NAME);
        cv.put(COLUMN_TIME_DATE,game.GAME_TIME);
        cv.put(COLUMN_SCORE,game.GAME_SCORE);
        long result= db.insert(TABLE_NAME,null,cv);
        return result != -1;
    }

    public boolean update_Game(ModelClass game) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_FULL_NAME, game.GAME_FULL_NAME);
        cv.put(COLUMN_TIME_DATE, game.GAME_TIME);
        cv.put(COLUMN_SCORE, game.GAME_SCORE);
        String[] args ={game.getGAME_ID()+""};
        int result=db.update(TABLE_NAME,cv,"id=?",args);
        return result > 0;
    }

    public long getGameCount(){
        SQLiteDatabase db = this.getReadableDatabase();
        return DatabaseUtils.queryNumEntries(db,TABLE_NAME);
    }


    Cursor readAllDate(){
        String query = "SELECT * FROM "+TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor=null;
        if (db != null){
            cursor=db.rawQuery(query,null);
        }return cursor;
    }

    //دالة الاسترجاع لبيانات اللعبة
    public ArrayList<ModelClass> getAllGames(){
        ArrayList<ModelClass> games = new ArrayList<>();
        SQLiteDatabase db= getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM "+TABLE_NAME+" ORDER BY "+COLUMN_ID +" DESC ; ",null);
        //كود التعامل مع الكورسر وتحويله لمصفوفة من نوع جييم
        //فحص هل الكورسر يحتوي على بيانات أم لا
        if (cursor != null && cursor.moveToNext()){
            do {
//                    @SuppressLint("Range") int id = cursor.getInt(cursor.getColumnIndex(COLUMN_ID)+1);
                @SuppressLint("Range") String fullName = cursor.getString(cursor.getColumnIndex(COLUMN_FULL_NAME));
                @SuppressLint("Range") String DT = cursor.getString(cursor.getColumnIndex(COLUMN_TIME_DATE));
                @SuppressLint("Range") int Score = cursor.getInt(cursor.getColumnIndex(COLUMN_SCORE));

                ModelClass p = new ModelClass(Score,fullName,DT);
                games.add(p);

            }while (cursor.moveToNext());
            cursor.close();
        }return games;
    }


    public String getLastGames(){
//        ArrayList<ModelClass> games = new ArrayList<>();
        String Date = "";
        SQLiteDatabase db= getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM "+TABLE_NAME +" ORDER BY "+COLUMN_ID +" DESC",null);
       //كود التعامل مع الكورسر وتحويله لمصفوفة من نوع جييم
        //فحص هل الكورسر يحتوي على بيانات أم لا
        if (cursor != null && cursor.moveToFirst()){
                Date = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_TIME_DATE));
            }else{ Date = "  "; }
            cursor.close();
            return Date;
    }


    //دالة البحث
    public ArrayList<ModelClass> getGames(String full_name) {
        ArrayList<ModelClass> games = new ArrayList<>();
        SQLiteDatabase db= getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM "+TABLE_NAME +" WHERE "+ COLUMN_FULL_NAME + " =? ",
                new String[]{full_name});
        //كود التعامل مع الكورسر وتحويله لمصفوفة من نوع جييم
        //فحص هل الكورسر يحتوي على بيانات أم لا
        if (cursor != null && cursor.moveToNext()){
            do {
                @SuppressLint("Range") int id = cursor.getInt(cursor.getColumnIndex(COLUMN_ID));
                @SuppressLint("Range") String fullName = cursor.getString(cursor.getColumnIndex(COLUMN_FULL_NAME));
                @SuppressLint("Range") String DT = cursor.getString(cursor.getColumnIndex(COLUMN_TIME_DATE));
                @SuppressLint("Range") int Score = cursor.getInt(cursor.getColumnIndex(COLUMN_SCORE));
                ModelClass game = new ModelClass(id,fullName,DT,Score);
                games.add(game);
            }while (cursor.moveToNext());
            cursor.close();
        }return games;
    }



    public boolean ClearGame(){
        SQLiteDatabase db=getWritableDatabase();
        db.execSQL("delete from "+ TABLE_NAME);
        long result = db.delete(TABLE_NAME,null,null);
        Toast.makeText(context, "Clear done successfully", Toast.LENGTH_SHORT).show();
        return result > 0;
    }



}

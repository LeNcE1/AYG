package com.example.android.normalnotdagger.data_base;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class CreadDB extends SQLiteOpenHelper {
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "DB";
    public static final String TABLE_MESSAGES  = "messages";
    public static final String FIELD_FROM_ID  = "from_id";
    public static final String FIELD_USER_LOGIN  = "user_login";
    public static final String FIELD_TO_ID  = "to_id";
    public static final String FIELD_DATE  = "date";
    public static final String FIELD_TEXT  = "text";

    public CreadDB(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("create table " + TABLE_MESSAGES + "("
                + FIELD_FROM_ID + " text,"
                + FIELD_USER_LOGIN + " text,"
                + FIELD_TO_ID + " text,"
                + FIELD_DATE + " text,"
                + FIELD_TEXT + "text);");
    }
    String[] dat;
    String s = "select sysdate from dual";

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("drop table if exists " + TABLE_MESSAGES);
        onCreate(sqLiteDatabase);
        sqLiteDatabase.rawQuery(s, dat);
    }
}

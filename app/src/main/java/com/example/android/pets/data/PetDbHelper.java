package com.example.android.pets.data;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Bola on 7/1/2018.
 */

public class PetDbHelper extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "shelter";
    private static final String CREATE_TABLE_INSTRUCTION = "CREATE TABLE " + PetContract.PetEntry.TABLE_NAME + "(" +
            PetContract.PetEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            PetContract.PetEntry.COLUMN_PET_NAME + " TEXT NOT NULL, " +
            PetContract.PetEntry.COLUMN_PET_BREED + " TEXT, " +
            PetContract.PetEntry.COLUMN_PET_GENDER + " INTEGER NOT NULL," +
            PetContract.PetEntry.COLUMN_PET_WEIGHT + " INTEGER NOT NULL DEFAULT 0)";

    public PetDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CREATE_TABLE_INSTRUCTION);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        String DELETE_TABLE = "DROP TABLE "+ PetContract.PetEntry.TABLE_NAME;
        sqLiteDatabase.execSQL(DELETE_TABLE);
        sqLiteDatabase.execSQL(CREATE_TABLE_INSTRUCTION);
    }
}

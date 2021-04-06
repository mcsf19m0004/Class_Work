package com.example.sqlitelec10;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class DBHelper extends SQLiteOpenHelper {

    public static final String STUDENT_NAME="StudentName";
    public static final String STUDENT_AGE="studentAge";
    public static final String ACTIVE_STUDENT="activeStudent";
    public static final String STUDENT_ID="StudentID";
    public static final String STUDENT_TABLE="StudentTable";


    public DBHelper(@Nullable Context context){
        super(context,"MyDB.db",null,1);

    }
    @Override
    public void onCreate(SQLiteDatabase db)
    {
        String createTableStatement="CREATE TABLE STUDENT_TABLE("+STUDENT_ID+"Integer PRIMARY KEY AUTOINCREMENT,"
                + STUDENT_NAME +"Text,"+STUDENT_AGE+" Int,"+ACTIVE_STUDENT+" BOOL)";
            db.execSQL(createTableStatement);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db,int oldVersion, int newVersion)
    {

    }
    public boolean addStudent(CustomerModels customerModels)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues cv=new ContentValues();

        cv.put(STUDENT_NAME,customerModels.getName());
        cv.put(STUDENT_AGE,customerModels.getAge());
        cv.put(ACTIVE_STUDENT,customerModels.isActive());

        long insert=db.insert(STUDENT_TABLE,null,cv);
        if(insert==-1)
            return false;
        else
            return true;
    }

    public List<CustomerModels> getAllRecord()
    {
        List<CustomerModels> myList=new ArrayList<>();

        String query=" SELECT *FROM "+ STUDENT_TABLE;
        SQLiteDatabase DB=this.getReadableDatabase();
       Cursor cursor= DB.rawQuery(query,null);
        if(cursor.moveToFirst())
        {
            do{
                String studentName=cursor.getString(1);
                int studentAge=cursor.getInt(2);
                boolean isActivie=cursor.getInt(3) == 1? true: false;
                CustomerModels newCusModel=new CustomerModels(studentName,studentAge,isActivie,1);
                myList.add(newCusModel);


            }while (cursor.moveToNext());

        }
        cursor.close();
        DB.close();
        return myList;
    }
}

package com.example.jula.pms_lab4_4;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;


public class Add extends Activity {
    Button addButton;
    EditText nameText, famText, otchText;
    DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add);
        addButton = (Button) findViewById(R.id.addBtn);
        nameText = (EditText) findViewById(R.id.namText);
        famText = (EditText) findViewById(R.id.famText);
        otchText = (EditText) findViewById(R.id.otchText);

    }


    public void addButtonClick(View v)
    {
        dbHelper = new DBHelper(this);
        // создаем объект для данных
        ContentValues cv = new ContentValues();

        // получаем данные из полей ввода
        String name = nameText.getText().toString();
        String name1 = famText.getText().toString();
        String name2 = otchText.getText().toString();
        SimpleDateFormat format1 = new SimpleDateFormat("hh:mm");

        cv.put("name", name);
        cv.put("name1", name1);
        cv.put("name2", name2);
        cv.put("data", String.valueOf(format1.format(new Date())));

        // подключаемся к БД
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        long rowID = db.insert("mytable", null, cv);
        Toast t = Toast.makeText(getApplicationContext(), "User with #" + String.valueOf(rowID) + " successfully added!", Toast.LENGTH_SHORT);
        t.show();

    }
    class DBHelper extends SQLiteOpenHelper {

        public DBHelper(Context context) {
            // конструктор суперкласса
            super(context, "myDB", null, 1);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL("create table mytable ("
                    + "id integer primary key autoincrement,"
                    + "name text,"
                    + "name1 text,"
                    + "name2 text,"
                    + "data text" + ");");
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        }
    }
}
package com.example.bbg;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.SQLException;

/**
 * Created by Богдан on 17.12.2015.
 */
public class DataBaseHelper extends SQLiteOpenHelper{
    private static String DB_PATH = "/data/data/com.example.bbg/databases/";
    private static String DB_NAME = "db";
    private static final int SCHEMA = 1; // версия базы данных
    public String TABLE;

    public static final String COLUMN_ID = "category_product_id";
    public static final String COLUMN_NAME = "category_product_name";

    public SQLiteDatabase database;
    private Context myContext;

    public DataBaseHelper(Context context,String table_name) {
        super(context, DB_NAME, null, SCHEMA);
        this.myContext=context;
        this.TABLE = table_name;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion,  int newVersion) {

    }

    public void create_db(){
        InputStream myInput;
        OutputStream myOutput;
        try {
            File file = new File(DB_PATH + DB_NAME);
            if (!file.exists()) {
                this.getReadableDatabase();
                //получаем локальную бд как поток
                myInput = myContext.getAssets().open(DB_NAME);
                // Путь к новой бд
                String outFileName = DB_PATH + DB_NAME;

                // Открываем пустую бд
                myOutput = new FileOutputStream(outFileName);

                // побайтово копируем данные
                byte[] buffer = new byte[1024];
                int length;
                while ((length = myInput.read(buffer)) > 0) {
                    myOutput.write(buffer, 0, length);
                }

                myOutput.flush();
                myOutput.close();
                myInput.close();
            }
        }
        catch(IOException ex){

        }
    }
    public void open() throws SQLException {
        String path = DB_PATH + DB_NAME;
        database = SQLiteDatabase.openDatabase(path, null,
                SQLiteDatabase.OPEN_READWRITE);

    }

    @Override
    public synchronized void close() {
        if (database != null) {
            database.close();
        }
        super.close();
    }
}

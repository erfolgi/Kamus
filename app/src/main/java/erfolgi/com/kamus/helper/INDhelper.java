package erfolgi.com.kamus.helper;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.util.Log;

import java.util.ArrayList;

import erfolgi.com.kamus.db.DBhelper;
import erfolgi.com.kamus.model.INDmodel;

import static android.provider.BaseColumns._ID;
import static erfolgi.com.kamus.db.DatabaseContract.IndColumns.MEAN_IND;
import static erfolgi.com.kamus.db.DatabaseContract.IndColumns.WORD_IND;
import static erfolgi.com.kamus.db.DatabaseContract.TABLE_IND;

public class INDhelper {
    private Context context;
    private DBhelper dataBaseHelper;

    private SQLiteDatabase database;

    public INDhelper(Context context) {
        this.context = context;
    }

    public INDhelper open() throws SQLException {
        dataBaseHelper = new DBhelper(context);
        database = dataBaseHelper.getWritableDatabase();
        return this;
    }


    public void close(){
        dataBaseHelper.close();
    }
    public void drop(){
        database.delete(TABLE_IND,null,null);
    }

    public ArrayList<INDmodel> getDataByName(String kata){
        if (kata==""){
            kata="a";
        }
        Cursor cursor = database.query(TABLE_IND,null,WORD_IND+" LIKE ?",new String[]{kata+"%"},null,null,_ID + " ASC",null);
        cursor.moveToFirst();
        ArrayList<INDmodel> arrayList = new ArrayList<>();
        INDmodel indModel;
        int a=0;
        if (cursor.getCount()>0) {
            Log.d("Debug IND", kata+" Count: "+cursor.getCount());
            do {

                indModel = new INDmodel();
                indModel.setId_ind(cursor.getInt(cursor.getColumnIndexOrThrow(_ID)));
                indModel.setWord_ind(cursor.getString(cursor.getColumnIndexOrThrow(WORD_IND)));
                indModel.setMean_ind(cursor.getString(cursor.getColumnIndexOrThrow(MEAN_IND)));
                arrayList.add(indModel);
                cursor.moveToNext();
                a++;
            } while (cursor.getPosition()<=50&&!cursor.isAfterLast());//Maks. 50 data agar loading tidak lama.
            Log.d("AAA", String.valueOf(!cursor.isAfterLast()));
        }
        cursor.close();
        return arrayList;
    }

    public ArrayList<INDmodel> getAllData(){
        Cursor cursor = database.query(TABLE_IND,null,null,null,null,null,_ID+ " ASC",null);
        cursor.moveToFirst();
        ArrayList<INDmodel> arrayList = new ArrayList<>();
        INDmodel indoModel;
        int a=0;
        int b =100; //Data terlalu banyak.. jumlahnya di tampilan awal dikurangi agar loading tidak lama.
        if (cursor.getCount()>0) {
            Log.d("Debug IND", "Count: "+cursor.getCount());
            Log.d("Debug IND", "Count Now: "+b);
            do {
                a++;
                indoModel = new INDmodel();
                indoModel.setId_ind(cursor.getInt(cursor.getColumnIndexOrThrow(_ID)));
                indoModel.setWord_ind(cursor.getString(cursor.getColumnIndexOrThrow(WORD_IND)));
                indoModel.setMean_ind(cursor.getString(cursor.getColumnIndexOrThrow(MEAN_IND)));
                arrayList.add(indoModel);
                cursor.moveToNext();
            } while (cursor.getPosition()<=50&&!cursor.isAfterLast());//Maks. 50 data agar loading tidak lama.
        }
        cursor.close();
        return arrayList;
    }

    public void insertTransaction(INDmodel indModel){
        String sql = "INSERT INTO "+TABLE_IND+" ("+WORD_IND+", "+MEAN_IND
                +") VALUES (?, ?)";
        SQLiteStatement stmt = database.compileStatement(sql);
        stmt.bindString(1, indModel.getWord_ind());
        stmt.bindString(2, indModel.getMean_ind());
        stmt.execute();
        stmt.clearBindings();

    }

    public void beginTransaction(){
        database.beginTransaction();
    }

    public void setTransactionSuccess(){
        database.setTransactionSuccessful();
    }

    public void endTransaction(){
        database.endTransaction();
    }
}

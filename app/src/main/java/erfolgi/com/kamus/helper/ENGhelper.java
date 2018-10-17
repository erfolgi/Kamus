package erfolgi.com.kamus.helper;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.util.Log;

import java.util.ArrayList;

import erfolgi.com.kamus.db.DBhelper;
import erfolgi.com.kamus.model.ENGmodel;

import static android.provider.BaseColumns._ID;
import static erfolgi.com.kamus.db.DatabaseContract.EngColumns.MEAN_ENG;
import static erfolgi.com.kamus.db.DatabaseContract.EngColumns.WORD_ENG;
import static erfolgi.com.kamus.db.DatabaseContract.TABLE_ENG;

public class ENGhelper {
    private Context context;
    private DBhelper dataBaseHelper;

    private SQLiteDatabase database;

    public ENGhelper(Context context) {
        this.context = context;
    }

    public ENGhelper open() throws SQLException {
        dataBaseHelper = new DBhelper(context);
        database = dataBaseHelper.getWritableDatabase();
        return this;
    }

    public void close(){
        dataBaseHelper.close();
    }

    public ArrayList<ENGmodel> getDataByName(String kata){
        String result = "";
        Cursor cursor = database.query(TABLE_ENG,null,WORD_ENG+" LIKE ?",new String[]{kata+"%"},null,null,_ID + " ASC",null);
        cursor.moveToFirst();
        ArrayList<ENGmodel> arrayList = new ArrayList<>();
        ENGmodel engModel;
        Log.d("indHelp eng count: ", String.valueOf(cursor.getCount()));
        if (cursor.getCount()>0) {
            do {
                engModel = new ENGmodel();
                engModel.setId_eng(cursor.getInt(cursor.getColumnIndexOrThrow(_ID)));
                engModel.setWord_eng(cursor.getString(cursor.getColumnIndexOrThrow(WORD_ENG)));
                engModel.setMean_eng(cursor.getString(cursor.getColumnIndexOrThrow(MEAN_ENG)));

                arrayList.add(engModel);
                cursor.moveToNext();

            } while (cursor.getPosition()<=50&&!cursor.isAfterLast());//Maks. 50 data agar loading tidak lama.
        }
        cursor.close();
        return arrayList;
    }
    public void drop(){
        database.delete(TABLE_ENG,null,null);
    }
    public ArrayList<ENGmodel> getAllData(){
        Cursor cursor = database.query(TABLE_ENG,null,null,null,null,null,_ID+ " ASC",null);
        cursor.moveToFirst();
        ArrayList<ENGmodel> arrayList = new ArrayList<>();
        ENGmodel engModel;
        if (cursor.getCount()>0) {
            Log.d("Debug ENG", "Count: "+cursor.getCount());
            do {
                engModel = new ENGmodel();
                engModel.setId_eng(cursor.getInt(cursor.getColumnIndexOrThrow(_ID)));
                engModel.setWord_eng(cursor.getString(cursor.getColumnIndexOrThrow(WORD_ENG)));
                engModel.setMean_eng(cursor.getString(cursor.getColumnIndexOrThrow(MEAN_ENG)));

                arrayList.add(engModel);
                cursor.moveToNext();
            } while (cursor.getPosition()<=50&&!cursor.isAfterLast());
        }
        cursor.close();
        return arrayList;
    }

    public void insertTransaction(ENGmodel engModel){
        String sql = "INSERT INTO "+TABLE_ENG+" ("+WORD_ENG+", "+MEAN_ENG
                +") VALUES (?, ?)";
        SQLiteStatement stmt = database.compileStatement(sql);
        stmt.bindString(1, engModel.getWord_eng());
        stmt.bindString(2, engModel.getMean_eng());
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

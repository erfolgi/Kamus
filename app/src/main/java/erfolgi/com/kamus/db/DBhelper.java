package erfolgi.com.kamus.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import static android.provider.BaseColumns._ID;
import static erfolgi.com.kamus.db.DatabaseContract.EngColumns.MEAN_ENG;
import static erfolgi.com.kamus.db.DatabaseContract.EngColumns.WORD_ENG;
import static erfolgi.com.kamus.db.DatabaseContract.IndColumns.MEAN_IND;
import static erfolgi.com.kamus.db.DatabaseContract.IndColumns.WORD_IND;
import static erfolgi.com.kamus.db.DatabaseContract.TABLE_ENG;
import static erfolgi.com.kamus.db.DatabaseContract.TABLE_IND;

public class DBhelper extends SQLiteOpenHelper {

    private static String DATABASE_NAME = "dbkamus";

    private static final int DATABASE_VERSION = 1;

    public static String CREATE_TABLE_ENG = "create table "+TABLE_ENG+
            " ("+_ID+" integer primary key autoincrement, " +
            WORD_ENG+" text not null, " +
            MEAN_ENG+" text not null);";

    public static String CREATE_TABLE_IND = "create table "+TABLE_IND+
            " ("+_ID+" integer primary key autoincrement, " +
            WORD_IND+" text not null, " +
            MEAN_IND+" text not null);";

    public DBhelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_ENG);
        db.execSQL(CREATE_TABLE_IND);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_ENG);
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_IND);
        onCreate(db);
    }

    @Override
    public synchronized void close() {
        super.close();
    }
}

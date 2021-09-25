package ddwucom.mobile.report01;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class ContactDBHelper extends SQLiteOpenHelper {

    private final static String DB_NAME = "contact_db";
    public final static String TABLE_NAME = "contact_table";
    public final static String COL_ID = "_id";
    public final static String COL_NAME = "name";
    public final static String COL_MUSIC_TITLE = "music_title";
    public final static String COL_ARTIST = "artist";

    public ContactDBHelper(Context context) {
        super(context, DB_NAME, null, 1);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_NAME + " ( " + COL_ID + " integer primary key autoincrement,"
                + COL_NAME + " TEXT, " + COL_MUSIC_TITLE + " TEXT, " + COL_ARTIST + " TEXT);");

//		샘플 데이터
        db.execSQL("INSERT INTO " + TABLE_NAME + " VALUES (null, 'hong', 'Money', 'LISA');");
        db.execSQL("INSERT INTO " + TABLE_NAME + " VALUES (null, 'musicstar', 'cool with it', 'brb');");
        db.execSQL("INSERT INTO " + TABLE_NAME + " VALUES (null, 'joo', 'camellia', 'slchld');");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table " + TABLE_NAME);
        onCreate(db);
    }

}

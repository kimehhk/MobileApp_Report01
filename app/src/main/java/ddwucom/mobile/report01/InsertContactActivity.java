package ddwucom.mobile.report01;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class InsertContactActivity extends AppCompatActivity {

    EditText etTitle;
    EditText etArtist;
    EditText etContent;

    ContactDBHelper helper;
    Cursor cursor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert_contact);

        etTitle = findViewById(R.id.et_insertTitle);
        etArtist = findViewById(R.id.et_insertArtist);
        etContent = findViewById(R.id.et_insertContent);

        helper = new ContactDBHelper(this);

        /*SQLiteDatabase db = helper.getReadableDatabase();
        cursor = db.rawQuery("SELECT _id FROM " + ContactDBHelper.TABLE_NAME, null);
        cursor.moveToLast();

        int id = Integer.parseInt(cursor.getString(cursor.getColumnIndex(ContactDBHelper.COL_ID))) + 1;

        Toast.makeText(InsertContactActivity.this, Integer.toString(id), Toast.LENGTH_SHORT).show();
        helper.close();*/

    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_insertComplete:
                insertPost();
                finish();
                break;
            case R.id.btn_insertCancel:
                finish();
                break;
        }
    }

    public void insertPost() {
        SQLiteDatabase db = helper.getReadableDatabase();
        cursor = db.rawQuery("SELECT _id FROM " + ContactDBHelper.TABLE_NAME, null);
        cursor.moveToLast();

        int id = Integer.parseInt(cursor.getString(cursor.getColumnIndex(ContactDBHelper.COL_ID))) + 1;
        String title = etTitle.getText().toString();
        String artist = etArtist.getText().toString();
        String content = etContent.getText().toString();


        db.execSQL("INSERT INTO " + ContactDBHelper.TABLE_NAME + " VALUES (" + Integer.toString(id) + ", 'user', '" + title + "', '" + artist + "', '" + content + "');");

        helper.close();
        cursor.close();
    }

    protected void onDestory() {
        super.onDestroy();
        if(cursor != null)
            cursor.close();
    }

}
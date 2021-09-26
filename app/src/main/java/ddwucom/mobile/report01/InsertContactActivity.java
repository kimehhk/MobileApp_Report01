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

    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_insertComplete:
                if(insertPost() == 1) {
                    finish();
                }
                break;
            case R.id.btn_insertCancel:
                finish();
                break;
        }
    }

    public int insertPost() {
        int flag = 0;

        SQLiteDatabase db = helper.getReadableDatabase();
        cursor = db.rawQuery("SELECT _id FROM " + ContactDBHelper.TABLE_NAME, null);
        cursor.moveToLast();

        int id = Integer.parseInt(cursor.getString(cursor.getColumnIndex(ContactDBHelper.COL_ID))) + 1;
        String title = etTitle.getText().toString();
        String artist = etArtist.getText().toString();
        String content = etContent.getText().toString();

        if (title.length() == 0) {
            Toast.makeText(getApplicationContext(), "제목을 입력하세요", Toast.LENGTH_SHORT).show();
        } else if (artist.length() == 0) {
            Toast.makeText(getApplicationContext(), "아티스트를 입력하세요", Toast.LENGTH_SHORT).show();
        } else if (content.length() == 0) {
            Toast.makeText(getApplicationContext(), "내용을 입력하세요", Toast.LENGTH_SHORT).show();
        } else {
            db.execSQL("INSERT INTO " + ContactDBHelper.TABLE_NAME + " VALUES (" + Integer.toString(id) + ", 'user', '" + title + "', '" + artist + "', '" + content + "');");
            flag = 1;
        }

        helper.close();
        return flag;
    }

    protected void onDestory() {
        super.onDestroy();
        if(cursor != null)
            cursor.close();
    }

}
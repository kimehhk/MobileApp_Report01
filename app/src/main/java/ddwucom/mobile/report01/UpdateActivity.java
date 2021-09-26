package ddwucom.mobile.report01;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class UpdateActivity extends AppCompatActivity {

    EditText etTitle;
    EditText etArtist;
    EditText etContent;

    ContactDBHelper helper;
    Cursor cursor;

    String id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        Intent intent = getIntent();
        id = intent.getStringExtra("post_id");

        etTitle = findViewById(R.id.et_updateTitle);
        etArtist = findViewById(R.id.et_updateArtist);
        etContent = findViewById(R.id.et_updateContent);

        helper = new ContactDBHelper(this);

        SQLiteDatabase db = helper.getReadableDatabase();
        cursor = db.rawQuery("SELECT * FROM " + ContactDBHelper.TABLE_NAME + " WHERE _id = " + id, null);

        while (cursor.moveToNext()) {
            etTitle.setText(cursor.getString(cursor.getColumnIndex(ContactDBHelper.COL_MUSIC_TITLE)));
            etArtist.setText(cursor.getString(cursor.getColumnIndex(ContactDBHelper.COL_ARTIST)));
            etContent.setText(cursor.getString(cursor.getColumnIndex(ContactDBHelper.COL_CONTENT)));
        }

        helper.close();

    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_updateComplete:
                updatePost(id);
                finish();
                break;
            case R.id.btn_updateCancel:
                finish();
                break;
        }
    }

    public void updatePost(String post_id) {
        SQLiteDatabase db = helper.getWritableDatabase();

        String title = etTitle.getText().toString();
        String artist = etArtist.getText().toString();
        String content = etContent.getText().toString();

        db.execSQL("UPDATE " + ContactDBHelper.TABLE_NAME + " SET music_title = '" + title + "', artist = '" + artist + "', content = '" + content + "' WHERE _id = " + post_id);

        helper.close();


    }
}
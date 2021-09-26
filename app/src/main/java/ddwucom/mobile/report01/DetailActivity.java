package ddwucom.mobile.report01;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class DetailActivity extends AppCompatActivity {

    ContactDBHelper helper;
    Cursor cursor;

    TextView name;
    TextView title;
    TextView artist;
    TextView content;

    String id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Intent intent = getIntent();
        id = intent.getStringExtra("post_id");

        name = findViewById(R.id.name_detail);
        title = findViewById(R.id.title_detail);
        artist = findViewById(R.id.artist_detail);
        content = findViewById(R.id.content_detail);

        helper = new ContactDBHelper(this);
    }

    public void onClick(View v) {
        Intent intent;

        switch (v.getId()) {
            case R.id.btnUpdateDetail:
                intent = new Intent(this, UpdateActivity.class);
                intent.putExtra("post_id", id);
                startActivity(intent);
                break;
        }
    }

    protected void onResume() {
        super.onResume();
        SQLiteDatabase db = helper.getReadableDatabase();
        cursor = db.rawQuery("SELECT * FROM " + ContactDBHelper.TABLE_NAME + " WHERE _id = " + id, null);

        while (cursor.moveToNext()) {
            name.setText(cursor.getString(cursor.getColumnIndex(ContactDBHelper.COL_NAME)));
            title.setText(cursor.getString(cursor.getColumnIndex(ContactDBHelper.COL_MUSIC_TITLE)));
            artist.setText(cursor.getString(cursor.getColumnIndex(ContactDBHelper.COL_ARTIST)));
            content.setText(cursor.getString(cursor.getColumnIndex(ContactDBHelper.COL_CONTENT)));
        }

        helper.close();
    }

    protected void onDestory() {
        super.onDestroy();
        if(cursor != null)
            cursor.close();
    }
}
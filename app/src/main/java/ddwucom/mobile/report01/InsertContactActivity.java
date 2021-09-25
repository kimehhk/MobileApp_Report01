package ddwucom.mobile.report01;

import androidx.appcompat.app.AppCompatActivity;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class InsertContactActivity extends AppCompatActivity {

    EditText etTitle;
    EditText etArtist;
    EditText etContent;

    ContactDBHelper helper;

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
                insertPost();
                break;
            case R.id.btn_insertCancel:
                finish();
                break;
        }
    }

    public void insertPost() {
        SQLiteDatabase db = helper.getWritableDatabase();
        db.execSQL("INSERT INTO " + ContactDBHelper.TABLE_NAME);
        helper.close();
    }

}
package ddwucom.mobile.report01;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

public class AllContactsActivity extends AppCompatActivity {

    ListView listView;
    ContactDBHelper helper;
    Cursor cursor;
    MyCursorAdapter adatper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_contacts);
        listView = (ListView)findViewById(R.id.allContacts);

        helper = new ContactDBHelper(this);

        adatper = new MyCursorAdapter(getApplicationContext(), R.layout.activity_item, null);
        listView.setAdapter(adatper);


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getApplicationContext(), DetailActivity.class);
                intent.putExtra("post_id", Long.toString(id));
                startActivity(intent);
            }
        });

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                AlertDialog.Builder builder = new AlertDialog.Builder(AllContactsActivity.this);
                builder.setTitle("삭제 확인")
                        .setMessage("해당 글을 삭제하시겠습니까?")
                        .setPositiveButton("확인", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                deletePost(id);
                            }
                        })
                        .setNegativeButton("취소", null)
                        .setCancelable(false)
                        .show();
                return true;
            }
        });
    }

    public void deletePost(Long id) {
        String post_id = Long.toString(id);
        SQLiteDatabase db = helper.getWritableDatabase();
        db.execSQL("DELETE FROM " + ContactDBHelper.TABLE_NAME + " WHERE _id = " + post_id);
        onResume();
        helper.close();
    }

    public void onClick(View v) {
        Intent intent;

        switch (v.getId()) {
            case R.id.btn_newItem:
                intent = new Intent(this, InsertContactActivity.class);
                startActivity(intent);
                break;
        }

    }

    protected void onResume() {
        super.onResume();
        SQLiteDatabase db = helper.getReadableDatabase();
        cursor = db.rawQuery("SELECT * FROM " + ContactDBHelper.TABLE_NAME, null);

        adatper.changeCursor(cursor);
        helper.close();
    }

    protected void onDestory() {
        super.onDestroy();
        if(cursor != null)
            cursor.close();
    }
}
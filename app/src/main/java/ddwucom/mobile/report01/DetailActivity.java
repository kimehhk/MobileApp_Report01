package ddwucom.mobile.report01;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
    }

    public void onClick(View v) {
        Intent intent;

        switch (v.getId()) {
            case R.id.btnUpdateDetail:
                intent = new Intent(this, UpdateActivity.class);
                break;
        }
    }
}
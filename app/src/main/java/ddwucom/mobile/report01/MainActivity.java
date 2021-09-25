package ddwucom.mobile.report01;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onClick(View v) {
        Intent intent;

        switch (v.getId()) {
            case R.id.btnBrowseAllList:
                intent = new Intent(this, AllContactsActivity.class);
                break;
        }

    }
}
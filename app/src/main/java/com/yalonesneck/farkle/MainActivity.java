package com.yalonesneck.farkle;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        try {
            this.getPackageManager().getPackageInfo(this.getPackageName(), 0);
            PackageInfo pInfo = this.getPackageManager().getPackageInfo(this.getPackageName(), 0);
            TextView versionView = this.findViewById(R.id.textViewVersion);
            String versionString = String.format(getResources().getString(R.string.version), pInfo.versionName, pInfo.versionCode);
            versionView.setText(versionString);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void newGame(View view) {
        Intent intent = new Intent(this, NewGameActivity.class);
        startActivity(intent);
    }
}
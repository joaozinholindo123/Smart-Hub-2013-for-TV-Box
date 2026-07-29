package com.smarthub.launcher;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

public class MainActivity extends Activity {

    private final String PLUTO_PACKAGE_NAME = "tv.pluto.android";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Garanta que o nome do XML aqui seja o mesmo que está na pasta res/layout
        setContentView(R.layout.main); 

        Button btnOpenPluto = findViewById(R.id.btnOpenPluto);
        LinearLayout btnAppPluto = findViewById(R.id.btnAppPluto);

        View.OnClickListener plutoListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openPlutoTV();
            }
        };

        if (btnOpenPluto != null) btnOpenPluto.setOnClickListener(plutoListener);
        if (btnAppPluto != null) btnAppPluto.setOnClickListener(plutoListener);
    }

    private void openPlutoTV() {
        Intent launchIntent = getPackageManager().getLaunchIntentForPackage(PLUTO_PACKAGE_NAME);
        if (launchIntent != null) {
            startActivity(launchIntent);
        } else {
            try {
                Intent webIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://pluto.tv/pt/live-tv"));
                startActivity(webIntent);
            } catch (Exception e) {
                Toast.makeText(this, "Pluto TV não instalada", Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    public void onBackPressed() {
        // Trava o botão voltar para o app agir como launcher
    }
}
package com.example.rommel.mathwiz;

import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.os.SystemClock;
import android.view.View;
import android.widget.Button;

public class MathWizAbout extends Activity {

    private Button closeButton = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_math_wiz_about);

        closeButton = (Button) findViewById(R.id.about_page_close_button);
        closeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setResult(RESULT_OK);
                finish();
            }
        });
    }

}

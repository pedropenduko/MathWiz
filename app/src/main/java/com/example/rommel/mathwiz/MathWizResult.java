package com.example.rommel.mathwiz;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MathWizResult extends Activity {

    private static final int REQUEST_CODE = 1;
    private int step = 0;
    private int magicNumber = 0;
    private TextView resultTextView = null;
    private Button closeButton = null;
    private Button aboutButton = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_math_wiz_result);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            int passedValue = extras.getInt("step");
            if (passedValue != 0) {
                //Toast.makeText(this, "This is step " + Integer.toString(passedValue), Toast.LENGTH_SHORT).show();
                step = passedValue;
            }
            if (step > 2) {
                magicNumber = extras.getInt("magic number");
            }
        }

        resultTextView = (TextView) findViewById(R.id.result_text);
        resultTextView.setText("The answer is " + Integer.toString(magicNumber/2) + ".\n\nI know right? :)");

        aboutButton = (Button) findViewById(R.id.about_button);
        aboutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(v.getContext(), MathWizAbout.class);
                startActivityForResult(i, REQUEST_CODE);
            }
        });

        closeButton = (Button) findViewById(R.id.close_button);
        closeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setResult(RESULT_OK);
                finish();
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            setResult(RESULT_OK);
            finish();
        }
    }
}

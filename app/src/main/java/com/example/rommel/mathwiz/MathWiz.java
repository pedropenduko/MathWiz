package com.example.rommel.mathwiz;

// import android.support.v7.app.AppCompatActivity;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class MathWiz extends Activity {

    private String[] instructions = new String[]{
            "Think of any number that is easy for you to manipulate...\n\nkeep it as your secret number.",
            "Multiply that number by 2...",
            "Add",
            "Divide the answer by 2...",
            "Subtract your secret number from the previous answer...",
    };

    private Button nextButton = null;
    private TextView instructionTextView = null;
    private int step = 0;
    private int magicNumber = 0;
    public static final int REQUEST_CODE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_math_wiz);

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

        String instruction = instructions[step];
        if (step == 2) {
            Double value = new Double(Math.random() * 100);
            magicNumber = value.intValue();
            if ((magicNumber % 2) != 0) {
                magicNumber++;
            }
            instruction = instruction + " " + Integer.toString(magicNumber) + "...";
        }

        instructionTextView = (TextView) findViewById(R.id.instruction_text);
        instructionTextView.setText(instruction);

        nextButton = (Button) findViewById(R.id.next_button);
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (step < 4) {
                    Intent i = new Intent(v.getContext(), MathWiz.class);
                    i.putExtra("step", step + 1);
                    if (step >= 2) {
                        i.putExtra("magic number", magicNumber);
                    }
                    startActivityForResult(i, REQUEST_CODE);
                } else {
                    Intent i = new Intent(v.getContext(), MathWizResult.class);
                    i.putExtra("step", step + 1);
                    i.putExtra("magic number", magicNumber);
                    startActivityForResult(i, REQUEST_CODE);
                }
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

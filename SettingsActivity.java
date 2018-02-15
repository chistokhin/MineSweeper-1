package com.minesweeper.s6h.minesweeper;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class SettingsActivity extends AppCompatActivity {

    private static RadioGroup mSettingsGroup;
    private static RadioButton mBeginner;
    private static RadioButton mIntermediate;
    private static RadioButton mExpert;
    private static Button mSaveBtn;

    private static int bombNumber = 10;
    private static int columnNumber = 10;
    private static int rowNumber = 10;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        mSettingsGroup = findViewById(R.id.settings_radio_group);

        mBeginner = findViewById(R.id.radio_beginner);
        mIntermediate = findViewById(R.id.radio_intermediate);
        mExpert = findViewById(R.id.radio_expert);

        mSaveBtn  = findViewById(R.id.save_btn);

        mSettingsGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

            }
        });


        mSaveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int checkedId = mSettingsGroup.getCheckedRadioButtonId();

                if (checkedId == mBeginner.getId()) {
                    mBeginner.isChecked();
                    setBombNumber(5);
                    setRowNumber(5);
                    setColumnNumber(5);
                } else if (checkedId == mIntermediate.getId()) {
                    mIntermediate.isChecked();
                    setBombNumber(10);
                    setRowNumber(10);
                    setColumnNumber(10);
                } else if (checkedId == mExpert.getId()) {
                    mExpert.isChecked();
                    setBombNumber(15);
                    setRowNumber(15);
                    setColumnNumber(15);
                } else {
                    mIntermediate.setChecked(true);
                    setBombNumber(10);
                    setRowNumber(10);
                    setColumnNumber(10);
                }

                Toast.makeText(getApplicationContext(), "Settings saved", Toast.LENGTH_SHORT)
                        .show();
            }
        });

    }

    public static int getBombNumber() {
        return bombNumber;
    }

    public static void setBombNumber(int bomb) {
        bombNumber = bomb;
    }

    public static void setColumnNumber(int column) {
        columnNumber = column;
    }

    public static void setRowNumber(int row) {
        rowNumber = row;
    }

    public static int getColumnNumber() {
        return columnNumber;
    }

    public static int getRowNumber() {
        return rowNumber;
    }
}

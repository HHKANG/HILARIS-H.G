package com.example.samsung.hilaris;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Detailview extends AppCompatActivity {

    EditText mTextFieldmin;
    EditText mTextFieldsec;
    CountDownTimer countDownTimer;
    Button start;
    Button stop;
    Button reset;

    int num;
    int time;

    boolean ChangeableNum = true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        setContentView(R.layout.activity_detailview);


        mTextFieldmin = (EditText) findViewById(R.id.edittext_timermin);
        mTextFieldsec = (EditText) findViewById(R.id.edittext_timersec);



        start = (Button) findViewById(R.id.button_start);
        stop = (Button) findViewById(R.id.button_stop);
        reset = (Button) findViewById(R.id.button_reset);

        start.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                mTextFieldmin.setEnabled(false);
                mTextFieldsec.setEnabled(false);


                start.setClickable(false);
                stop.setClickable(true);

                if(ChangeableNum) {
                    num = Integer.parseInt(mTextFieldmin.getText().toString()) * 60 + Integer.parseInt(mTextFieldsec.getText().toString());
                    ChangeableNum = false;
                }
                time = Integer.parseInt(mTextFieldmin.getText().toString()) * 60 + Integer.parseInt(mTextFieldsec.getText().toString());
                countDownTimer = new CountDownTimer(time * 1000, 1000) {

                    @Override
                    public void onTick(long millisUntilFinished) {

                        mTextFieldmin.setText(""+millisUntilFinished / (1000*60));
                        mTextFieldsec.setText(""+(millisUntilFinished/1000)%60);
                    }
                    @Override
                    public void onFinish() {
                        mTextFieldmin.setText("00");
                        mTextFieldsec.setText("00");
                        start.setClickable(true);
                        mTextFieldmin.setEnabled(true);
                        mTextFieldsec.setEnabled(true);
                    }
                }.start();
            }
        });
        stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                countDownTimer.cancel();
                start.setClickable(true);

                stop.setClickable(false);
            }
        });
        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                countDownTimer.cancel();
                mTextFieldmin.setEnabled(true);
                mTextFieldsec.setEnabled(true);
                start.setClickable(true);
                stop.setClickable(true);
                ChangeableNum = true;
                mTextFieldmin.setText(""+num/60);
                mTextFieldsec.setText(""+num%60);
            }
        });
    }
    /**
     * Action Bar에 메뉴를 생성
     * @param menu
     * @return
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.logout_menu, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;

            case R.id.logout:
                Intent intent = new Intent(Detailview.this, Login.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
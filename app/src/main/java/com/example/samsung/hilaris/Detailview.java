package com.example.samsung.hilaris;

import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.provider.MediaStore;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.Surface;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.VideoView;

import java.io.IOException;

public class Detailview extends AppCompatActivity {


    /******Attributes for Timer********/
    EditText mTextFieldmin;
    EditText mTextFieldsec;
    CountDownTimer countDownTimer;
    Button start, stop, reset;
    int num;
    int seconds;
    boolean ChangeableNum = true;
    /******For Visibility of Layout (Description, Routine, Timer**********/
    LinearLayout layout_description, layout_routine, layout_timer;
    Button Description, Routine, Timer;
    /******TextViews of Routine**********/
    TextView Set, Repetition, Time, Intensity, BodyPart, Equipment;
    /*********TextView for Description********/
    TextView txtDescription;
    /**********Buttons for Switching Video or Image***********/
    Button Previous, Next;
    /*****************************************************/
    Button show_HR;
    Object object;
    TextView exercise_name;
    MediaController mediaController;
    SurfaceView videoSurface;
    VideoView Videoview;
    MediaPlayer player;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        setContentView(R.layout.activity_detailview);


        Videoview = (VideoView) findViewById(R.id.Videoview);
        videoSurface = (SurfaceView) findViewById(R.id.videoSurface);
        SurfaceHolder videoHolder = videoSurface.getHolder();

        /*********Settings for button Next, Prev when switching the image or video*****/
        Previous = (Button) findViewById(R.id.button_prev);
        Next = (Button) findViewById(R.id.button_next);
        /******************Settings for Visibility**********************************/
        Description = (Button) findViewById(R.id.button_description);
        Routine = (Button) findViewById(R.id.button_routine);
        Timer = (Button) findViewById(R.id.button_timer);
        layout_description = (LinearLayout) findViewById(R.id.layout_description);
        layout_routine = (LinearLayout) findViewById(R.id.layout_routine);
        layout_timer = (LinearLayout) findViewById(R.id.layout_timer);
        setVisibility();
        /*************************Settings for Description********************************/
        txtDescription =(TextView) findViewById(R.id.textview_desription);
        /*******************Settings for Routine*****************************************/
        Set = (TextView) findViewById(R.id.routine_set);
        Repetition = (TextView) findViewById(R.id.routine_repetition);
        Time = (TextView) findViewById(R.id.routine_time);
        BodyPart = (TextView) findViewById(R.id.routine_body_part);
        Intensity = (TextView) findViewById(R.id.routine_intensity);
        Equipment = (TextView) findViewById(R.id.routine_equipment);
        /****************Settings for Timer*********************/
        mTextFieldmin = (EditText) findViewById(R.id.edittext_timermin);
        mTextFieldsec = (EditText) findViewById(R.id.edittext_timersec);
        start = (Button) findViewById(R.id.button_start);
        stop = (Button) findViewById(R.id.button_stop);
        reset = (Button) findViewById(R.id.button_reset);
        Timer();
        /*******************Settings for Button (next, previous)*****************************************/
        Next = (Button)findViewById(R.id.button_next);
        Previous = (Button) findViewById(R.id.button_prev);
        NextPrevButton();
        /**********************************************************************************************/
        //Need to get Exercise Name from previous class or Get it frome current class --> code change needed below
        Intent intent = getIntent();
        exercise_name = (TextView) findViewById(R.id.exercise_name);
        exercise_name.setText(intent.getStringExtra("exercise_name"));
        /*************************
         object = intent.getStringExtra("object");
         setValues(object);
         **************************/
        mediaController = new MediaController(this);

        //Will get uriPath dynamically from database
        String uriPath = "android.resource://"+getPackageName() + "/raw/dumbell";
    }
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
    //Settings for TImer (Start, Stop, Reset)
    public void Timer()
    {
        //When Start button clicked
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
                seconds = Integer.parseInt(mTextFieldmin.getText().toString()) * 60 + Integer.parseInt(mTextFieldsec.getText().toString());
                countDownTimer = new CountDownTimer(seconds * 1000, 1000) {

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
        //When Stop Button Clicked

        stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                countDownTimer.cancel();
                start.setClickable(true);

                stop.setClickable(false);
            }
        });
        //When Reset Button Clicked

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
    //Settings for visibility when button (Description, Routine, Timer) clicked
    public void setVisibility()
    {
        Description.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                layout_description.setVisibility(v.VISIBLE);
                layout_routine.setVisibility(v.INVISIBLE);
                layout_timer.setVisibility(v.INVISIBLE);
            }
        });
        Routine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                layout_description.setVisibility(v.INVISIBLE);
                layout_routine.setVisibility(v.VISIBLE);
                layout_timer.setVisibility(v.INVISIBLE);
            }
        });
        Timer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                layout_description.setVisibility(v.INVISIBLE);
                layout_routine.setVisibility(v.INVISIBLE);
                layout_timer.setVisibility(v.VISIBLE);
            }
        });
    }
    public void setValues(Object object)
    {
        String set, repetition, time, intensity, body_part, equipment;
        String description;
        /***********Below will be changed when Database construction finish****/
        set = object.toString();
        repetition = object.toString();
        time=object.toString();
        intensity = object.toString();
        body_part = object.toString();
        equipment = object.toString();
        description = object.toString();
        /****************/
        setRoutineValue(set, repetition, time, intensity, body_part, equipment );
        setDescription(description);
    }
    public void setRoutineValue(String set, String repetition, String time, String intensity, String body_part, String equipment)
    {
        Set.setText(set);
        Repetition.setText(repetition);
        Time.setText(time);
        Intensity.setText(intensity);
        BodyPart.setText(body_part);
        Equipment.setText(equipment);
    }
    public void setDescription(String description)
    {
        txtDescription.setText(description);
    }
    public void NextPrevButton()
    {
        Next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Detailview.class);
                startActivity(intent);
                finish();
            }
        });
        Previous.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Detailview.class);
                startActivity(intent);
                finish();
            }
        });
    }

    public void setVideoview(String uriPath)
    {
        mediaController.setAnchorView(Videoview);
        Uri video = Uri.parse(uriPath);
        Videoview.setMediaController(mediaController);
        Videoview.setVideoURI(video);
        Videoview.requestFocus();
        Videoview.start();

        Videoview.postDelayed(new Runnable() {
            @Override
            public void run() {
                mediaController.show(0);
                Videoview.pause();
            }
        }, 100);
    }
}

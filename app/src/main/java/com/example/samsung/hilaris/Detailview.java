package com.example.samsung.hilaris;


import android.content.Intent;

import android.net.Uri;
import android.os.Bundle;
import android.os.CountDownTimer;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;

import android.view.Menu;
import android.view.MenuItem;

import android.view.View;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.VideoView;

import org.json.JSONObject;


public class Detailview extends AppCompatActivity {

    JSONObject Exercise = null;
    /*******For Image and Video**********/
    String ImageUri;
    String VideoUri;
   ImageView Imageview;
    VideoView Videoview;
    Button B_ImageView, B_VideoView;
    /******Attributes for Timer********/
    EditText mTextFieldmin;
    EditText mTextFieldsec;
    CountDownTimer countDownTimer;
    ImageButton start, pause, reset;
    int num;
    int seconds;
    boolean ChangeableNum = true;
    /******For Visibility of Layout (Description, Routine, Timer**********/
    LinearLayout layout_description, layout_routine, layout_timer;
    Button Description, Routine, Timer;
    /******TextViews of Routine**********/
    TextView Set, Repetition, Time, Intensity, BodyPart, Equipment;
    /*********TextView for Description********/
    TextView txtDescription,txtBenefit,txtCaution;
    /**********Buttons for Switching Video or Image***********/
    ImageButton Previous, Next;

    /*****************************************************/
    Object object;
    TextView exercise_name;
    MediaController mediaController;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        setContentView(R.layout.activity_detailview);

        //Switch = (ImageSwitcher) findViewById(R.id.ImageSwitcher);
        Imageview = (ImageView) findViewById(R.id.ImageView) ;
        Videoview = (VideoView) findViewById(R.id.Exercise_Videoview);
        /*********Settings for button Next, Prev , Image, Video  when switching the image or video*****/
        B_ImageView = (Button) findViewById(R.id.button_image);
        B_VideoView = (Button) findViewById(R.id.button_video);
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
        txtBenefit = (TextView) findViewById(R.id.textview_benefit);
        txtCaution = (TextView) findViewById(R.id.textview_caution);
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
        start = (ImageButton) findViewById(R.id.button_start);
        pause = (ImageButton) findViewById(R.id.button_stop);
        reset = (ImageButton) findViewById(R.id.button_reset);
        Timer();
        /*******************Settings for Button (next, previous)*****************************************/
        Previous = (ImageButton) findViewById(R.id.button_prev);
        Next = (ImageButton) findViewById(R.id.button_next);
        NextPrevButton();
        /**********************************************************************************************/
        //Need to get Exercise Name from previous class or Get it frome current class --> code change needed below
        exercise_name = (TextView) findViewById(R.id.exercise_name);

        Intent intent = getIntent();
        /*************Set Values of Description and Routine Part************
         *
         *
        object = intent.getStringExtra("object");
         change object to Json Object
        setValues(object);
         *
         *
         *
        **************************/
        mediaController = new MediaController(this);

        String uriPath = "android.resource://"+getPackageName() + "/drawable/exercise1";
        //String uriPath = "http://221.153.186.186:3100/"+Exercise.toString(); //Get Name of Image from Json Object
        setImageView(uriPath);
        ImageVideoButton(Exercise);
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
         adjustTimerImageButton();
       //When Start button clicked
       start.setOnClickListener(new View.OnClickListener() {

           @Override
           public void onClick(View v) {
               mTextFieldmin.setEnabled(false);
               mTextFieldsec.setEnabled(false);


               start.setClickable(false);
               pause.setClickable(true);

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

       pause.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               countDownTimer.cancel();
               start.setClickable(true);
               pause.setClickable(false);
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
               pause.setClickable(true);
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
       String exercise_title;
       String set, repetition, time, intensity, body_part, equipment;
       String description, benefit, caution;
       /***********Below will be changed when Database construction finish****/

       //Should be implented when JSon Object reached
       exercise_title = object.toString(); //title
        set = object.toString();
       repetition = object.toString();
       time=object.toString();
       intensity = object.toString();
       body_part = object.toString();
       equipment = object.toString();
       /*******************/
       description = object.toString();
       benefit = object.toString();
       caution = object.toString();
       /****************/
       setExerciseTitle(exercise_title);
       setRoutineValue(set, repetition, time, intensity, body_part, equipment );
       setDescription(description, benefit, caution);
   }
   public void setExerciseTitle(String title)
   {
       exercise_name.setText(title);
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
    public void setDescription(String description, String benefits, String caution)
    {
        txtDescription.setText(description);
        txtBenefit.setText(benefits);
        txtCaution.setText(caution);
    }
    public void adjustTimerImageButton()
    {
       start.setImageResource(R.drawable.ic_media_start);
        start.setScaleType(ImageView.ScaleType.FIT_CENTER);
        pause.setImageResource(R.drawable.ic_media_pause);
        pause.setScaleType(ImageView.ScaleType.FIT_CENTER);
        reset.setImageResource(R.drawable.ic_media_reset);
        reset.setScaleType(ImageView.ScaleType.FIT_CENTER);
    }
    public void adjustPrevNextImageButton()
    {
        Previous.setImageResource(R.drawable.exercise_prev);
        Previous.setScaleType(ImageView.ScaleType.FIT_CENTER);
        Next.setImageResource(R.drawable.exercise_next);
        Next.setScaleType(ImageView.ScaleType.FIT_CENTER);
    }
    public void NextPrevButton()
    {
     adjustPrevNextImageButton();
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
    public void ImageVideoButton(JSONObject Exercise)
    {
        /*
         ImageUri = "http://221.153.186.186:3100/"+Exercise.toString(); //Get Name of Image from Json Object
         VideoUri = "http://221.153.186.186:3100/"+Exercise.toString(); // Get Name of Video from Json Object
        */

        B_ImageView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                String uriPath = "android.resource://"+getPackageName() + "/drawable/exercise1";//임시방편
               // setImageView(ImageUri);
                Videoview.setVisibility(v.INVISIBLE);
                Imageview.setVisibility(v.VISIBLE);
            }
        });
        B_VideoView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
               String uriPath = "android.resource://"+getPackageName() + "/raw/dumbell";//임시방편
                setVideoview(uriPath); // 임시방편
                // setVideoview(VideoUri);

                Videoview.setVisibility(v.VISIBLE);
                Imageview.setVisibility(v.INVISIBLE);
            }
        });
    }
    public void setImageView(String uriPath)
    {
        Imageview.setImageURI(Uri.parse(uriPath));
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

package com.kuhrusty.autodad;

import android.content.Intent;
import android.content.res.Resources;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

import com.kuhrusty.autodad.data.Question;
import com.kuhrusty.autodad.data.Question.AnswerValue;

import java.util.Random;

/**
 * This displays the answer to an asked question.
 */
public class DisplayAnswerActivity extends ActionBarActivity {

    public static final String INTENT_QUESTION = "com.kuhrusty.autodad.question";
    private static Random random = null;

    private Question question;
    private MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setVolumeControlStream(AudioManager.STREAM_MUSIC);

        Resources res = getResources();

        Intent intent = getIntent();
        question = intent.getParcelableExtra(INTENT_QUESTION);
        int bgcolor = res.getColor(R.color.red);
        int fgcolor = res.getColor(R.color.black);
        String msg = null;
        if (question != null) {
            msg = question.getAnswerMessage();
            if (question.getAnswer().equals(AnswerValue.YES)) {
                bgcolor = res.getColor(R.color.green);
            }
        } else {
            msg = "WHAA!?";
            bgcolor = res.getColor(R.color.yellow);
        }

        // Create the text view
        TextView tv = new TextView(this);
        tv.setTextSize(120);
        tv.setText(msg);
        tv.setTextColor(fgcolor);
        tv.setBackgroundColor(bgcolor);
        tv.setGravity(Gravity.CENTER);
        tv.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                finish();
                return true;
            }
        });
        // Set the text view as the activity layout
        setContentView(tv);
        playSound();
    }

    @Override
    public void onStop() {
        if (mediaPlayer != null) {
            mediaPlayer.release();
        }
        super.onStop();
    }

    private int nextRand() {
        if (random == null) {
            random = new Random();
        }
        int rv = random.nextInt(7);
        switch (rv) {
            case 0: return R.raw.no1;
            case 1: return R.raw.no2;
            case 2: return R.raw.no3;
            case 3: return R.raw.no4;
            case 4: return R.raw.no5;
            case 5: return R.raw.no6;
            case 6: return R.raw.no7;
        }
        return R.raw.no1;
    }

    private void playSound() {
        //  do we even have an audio file to play?

        if (question != null) {
            int res = R.raw.no1;
            if (question.getAnswer().equals(AnswerValue.YES)) {
                res = R.raw.yes;
            } else {
                res = nextRand();
            }
            mediaPlayer = MediaPlayer.create(getApplicationContext(), res);
        }
        if (mediaPlayer != null) {
            mediaPlayer.start();
        }
    }
}

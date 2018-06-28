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
//        setContentView(R.layout.activity_display_answer);

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

//        if ((tq != null) && tq.getAnswer().equals(AnswerValue.YES))
//        String ac = intent.getStringExtra(MainActivity.INTENT_ANSWER);
//        String msg = intent.getStringExtra(MainActivity.INTENT_CUSTOM_ANSWER);
//        if (msg == null) {
//
//        }

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
//                return false;
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

//        final AudioManager am = (AudioManager)(getApplicationContext().getSystemService(Context.AUDIO_SERVICE));
//        AudioManager.OnAudioFocusChangeListener afChangeListener =
//                new AudioManager.OnAudioFocusChangeListener() {
//            public void onAudioFocusChange(int focusChange) {
//                if (focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT) {
//                    // Pause playback
//                } else if (focusChange == AudioManager.AUDIOFOCUS_GAIN) {
//                    // Resume playback
//                } else if (focusChange == AudioManager.AUDIOFOCUS_LOSS) {
////                    am.unregisterMediaButtonEventReceiver(RemoteControlReceiver);
//                    am.abandonAudioFocus(this);
//                    // Stop playback
//                }
//            }
//        };
//
//        int afres = am.requestAudioFocus(afChangeListener,
//                                 // Use the music stream.
//                                 AudioManager.STREAM_MUSIC,
//                                 // Request permanent focus.
//                                 AudioManager.AUDIOFOCUS_GAIN_TRANSIENT_MAY_DUCK);
//        if (afres == AudioManager.AUDIOFOCUS_REQUEST_GRANTED) {
////            am.registerMediaButtonEventReceiver(RemoteControlReceiver);
//            // Start playback.
//        }
    }
}

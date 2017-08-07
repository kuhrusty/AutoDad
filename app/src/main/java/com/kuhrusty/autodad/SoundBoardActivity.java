package com.kuhrusty.autodad;

import android.content.res.Resources;
import android.media.MediaPlayer;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.kuhrusty.autodad.data.Question;
import com.kuhrusty.autodad.data.Sound;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * A bunch of buttons which play sounds.
 */
public class SoundBoardActivity extends ActionBarActivity
        implements MediaPlayer.OnCompletionListener,
                   MediaPlayer.OnErrorListener {

    private ArrayList<Sound> sounds = null;
    private Set<MediaPlayer> activePlayers = Collections.synchronizedSet(new HashSet<MediaPlayer>());

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (savedInstanceState != null) {
//            //  Do we have a list of questions?
//            ql = savedInstanceState.getParcelableArrayList(SAVED_QUESTIONS);
        }
        if ((sounds == null) || (sounds.size() == 0)) {
            loadSounds();
        }
        setContentView(R.layout.activity_ask_prepared_question);
        View cv = findViewById(R.id.linear_question_layout);
//Log.d("foo", "got cv " + cv);
        ViewGroup cvg = (cv instanceof ViewGroup) ? (ViewGroup)cv : null;
        for (Sound ts : sounds) {
            Button tb = new Button(this);
            tb.setText(ts.getName());
            tb.setOnClickListener(new SoundButtonClickListener(ts));
//WILL CROAK IF CVG NULL
            cvg.addView(tb);
        }
//        setContentView(R.layout.activity_sound_board);
    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.menu_sound_board, menu);
//        return true;
//    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onStop() {
        for (MediaPlayer mp : activePlayers) {
            mp.release();
        }
        activePlayers.clear();
        super.onStop();
    }

    private class SoundButtonClickListener implements View.OnClickListener {
        private final Sound sound;
        public SoundButtonClickListener(Sound ts) {
            sound = ts;
        }
        @Override
        public void onClick(View v) {
            playSound(sound);
        }
    };

    //  MediaPlayer.OnCompletionListener
    @Override
    public void onCompletion(MediaPlayer mp) {
        mp.release();
        activePlayers.remove(mp);
    }

    @Override
    public boolean onError(MediaPlayer mp, int what, int extra) {
        //  not real clever; same as onCompletion(), which is what would happen
        //  if we didn't have an error listener at all.
        mp.release();
        activePlayers.remove(mp);
        return true;
    }

    private void loadSounds() {
        ArrayList<Sound> rv = new ArrayList<Sound>();
        //  Really we would like this to read from properties rather than
        //  having these hard-coded.
        Resources res = getResources();
        rv.add(new Sound(R.raw.no1, res.getString(R.string.sound_name_no1)));
        rv.add(new Sound(R.raw.no2, res.getString(R.string.sound_name_no2)));
        rv.add(new Sound(R.raw.no3, res.getString(R.string.sound_name_no3)));
        rv.add(new Sound(R.raw.no4, res.getString(R.string.sound_name_no4)));
        rv.add(new Sound(R.raw.no5, res.getString(R.string.sound_name_no5)));
        rv.add(new Sound(R.raw.no6, res.getString(R.string.sound_name_no6)));
        rv.add(new Sound(R.raw.no7, res.getString(R.string.sound_name_no7)));
        rv.add(new Sound(R.raw.yes, res.getString(R.string.sound_name_yes)));
        sounds = rv;
    }

    private void playSound(Sound sound) {
        if ((sound == null) || (!sound.isValid())) {
            return;
        }
        MediaPlayer mp = MediaPlayer.create(getApplicationContext(), sound.getResourceID());
        activePlayers.add(mp);
        mp.setOnCompletionListener(this);
        mp.setOnErrorListener(this);
        mp.start();
    }
}
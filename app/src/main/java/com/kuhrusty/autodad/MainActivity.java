package com.kuhrusty.autodad;

import android.content.Intent;
import android.media.AudioManager;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;


public class MainActivity extends ActionBarActivity {

    private static final int BOGO_RESULT_CODE = 0;
    public static final String INTENT_ANSWER = "com.kuhrusty.autodad.ANSWER";
    public static final String INTENT_CUSTOM_ANSWER = "com.kuhrusty.autodad.ANSWER.MSG";
    public enum AnswerValue {
        NO,
        YES,
        CUSTOM;
    }
//    public static final String ANSWER_YES = "com.kuhrusty.autodad.ANSWER.YES";
//    public static final String ANSWER_NO = "com.kuhrusty.autodad.ANSWER.NO";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
        setVolumeControlStream(AudioManager.STREAM_MUSIC);
        if (savedInstanceState == null) {
            Intent intent = new Intent(this, AskPreparedQuestionActivity.class);
//Intent intent = new Intent(this, SoundBoardActivity.class);

//    EditText editText = (EditText) findViewById(R.id.edit_message);
//    String message = editText.getText().toString();
//    intent.putExtra(EXTRA_MESSAGE, message);
            startActivityForResult(intent, BOGO_RESULT_CODE);
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        finish();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

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
}

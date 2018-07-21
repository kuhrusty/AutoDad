package com.kuhrusty.autodad;

import android.content.Intent;
import android.media.AudioManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.kuhrusty.autodad.data.Question;
import com.kuhrusty.autodad.data.Question.AnswerValue;

import java.util.ArrayList;

/**
 * This displays the list of available questions.
 */
public class AskPreparedQuestionActivity extends AppCompatActivity {

    public static final String SAVED_QUESTIONS = "com.kuhrusty.autodad.questions";

    private class QuestionListener implements View.OnClickListener {
        private final Question question;
        public QuestionListener(Question tq) {
            question = tq;
        }
        @Override
        public void onClick(View v) {
            questionAsked(question);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setVolumeControlStream(AudioManager.STREAM_MUSIC);

        ArrayList<Question> ql = null;
        if (savedInstanceState != null) {
            //  Do we have a list of questions?
            ql = savedInstanceState.getParcelableArrayList(SAVED_QUESTIONS);
        }
        if ((ql == null) || (ql.size() == 0)) {
            ql = loadQuestions();
        }
        setContentView(R.layout.activity_ask_prepared_question);
        View cv = findViewById(R.id.linear_question_layout);
        ViewGroup cvg = (cv instanceof ViewGroup) ? (ViewGroup)cv : null;
        for (Question tq : ql) {
            Button tb = new Button(this);
            tb.setText(tq.getQuestion());
            tb.setOnClickListener(new QuestionListener(tq));
//WILL CROAK IF CVG NULL
            cvg.addView(tb);
        }
    }

    private ArrayList<Question> loadQuestions() {
        ArrayList<Question> rv = null;
        rv = new ArrayList<Question>();
        rv.add(new Question("id1", "Can I do something fun?", AnswerValue.NO, null));
        rv.add(new Question("id2", "Can I hang out with my friends?", AnswerValue.NO, null));
        rv.add(new Question("id3", "Want to play Call of Duty?", AnswerValue.NO, null));
        rv.add(new Question("id4", "Can I set something on fire?", AnswerValue.CUSTOM, "OH HELL NO"));//R.string.answer_hell_no));
        rv.add(new Question("id5", "Can I work on homework?", AnswerValue.YES, null));
        return rv;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_ask_prepared_question, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        if (id == R.id.action_soundboard) {
            Intent intent = new Intent(this, SoundBoardActivity.class);
            startActivity(intent);
        }

        return super.onOptionsItemSelected(item);
    }

    private void questionAsked(Question question) {
        Intent intent = new Intent(this, DisplayAnswerActivity.class);
        intent.putExtra(DisplayAnswerActivity.INTENT_QUESTION, question);
        startActivity(intent);
    }
}

package com.kuhrusty.autodad;

import android.content.Intent;
import android.test.ActivityInstrumentationTestCase2;

import com.kuhrusty.autodad.data.Question;
import com.kuhrusty.autodad.data.Question.AnswerValue;

/**
 * Created by rusty on 1/6/15.
 */
public class DisplayAnswerActivityTest2 extends ActivityInstrumentationTestCase2<DisplayAnswerActivity> {
    private DisplayAnswerActivity activity;

    public DisplayAnswerActivityTest2() {
        super(DisplayAnswerActivity.class);
    }
    @Override
    protected void setUp() throws Exception {
        super.setUp();
        setActivityInitialTouchMode(false);

        Intent ti = new Intent();
String id = null;
        ti.putExtra(DisplayAnswerActivity.INTENT_QUESTION, new Question(id, "Hork Blergh?", AnswerValue.NO, null));
        setActivityIntent(ti);

        activity = getActivity();
    }

    public void testSomeStuff() {
//        activity.get
    }
}

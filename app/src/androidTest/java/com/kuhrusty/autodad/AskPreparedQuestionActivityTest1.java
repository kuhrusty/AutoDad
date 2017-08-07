package com.kuhrusty.autodad;

import android.test.ActivityInstrumentationTestCase2;

//import org.junit.Ignore;

/**
 * Created by rusty on 1/6/15.
 */
public class AskPreparedQuestionActivityTest1 extends ActivityInstrumentationTestCase2<AskPreparedQuestionActivity> {
    private AskPreparedQuestionActivity activity;

    public AskPreparedQuestionActivityTest1() {
        super(AskPreparedQuestionActivity.class);
    }
    @Override
    protected void setUp() throws Exception {
        super.setUp();
        setActivityInitialTouchMode(false);

//Intent ti = new Intent();
//String id = null;
//ti.putExtra(DisplayAnswerActivity.INTENT_QUESTION, new Question(id, "FOOOARGHH?", AnswerValue.NO, null));
//setActivityIntent(ti);

        activity = getActivity();
    }

//    @Ignore
    public void testSomeStuff() {
//        activity.get
    }
}

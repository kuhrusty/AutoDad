package com.kuhrusty.autodad;

import android.app.Application;
import android.os.Parcel;
import android.test.ApplicationTestCase;
import com.kuhrusty.autodad.MainActivity.AnswerValue;
import com.kuhrusty.autodad.data.Question;

/**
 * <a href="http://d.android.com/tools/testing/testing_android.html">Testing Fundamentals</a>
 */
public class ApplicationTest extends ApplicationTestCase<Application> {
    public ApplicationTest() {
        super(Application.class);
    }

    public void testAnswerValues() {
//        assertTrue(AnswerValue.NO.ordinal() == 0);
//        assertTrue(AnswerValue.YES.ordinal() == 1);
//        assertTrue(AnswerValue.CUSTOM.ordinal() == 2);
        assertTrue(AnswerValue.NO.equals(AnswerValue.valueOf("NO")));
        assertTrue(AnswerValue.YES.equals(AnswerValue.valueOf("YES")));
        assertTrue(AnswerValue.CUSTOM.equals(AnswerValue.valueOf("CUSTOM")));
    }

    public void testQuestion() {
        Question tq = new Question();
        assertEquals(AnswerValue.NO, tq.getAnswer());

        tq.setAnswer(null);
        assertEquals(AnswerValue.NO, tq.getAnswer());

        tq.setAnswer(AnswerValue.YES);
        assertEquals(AnswerValue.YES, tq.getAnswer());

        tq.setAnswer(null);
        assertEquals(AnswerValue.NO, tq.getAnswer());

        tq.setAnswer(AnswerValue.CUSTOM);
        tq.setAnswerMessage("Oh hell no.");
        assertEquals(AnswerValue.CUSTOM, tq.getAnswer());
        assertEquals("Oh hell no.", tq.getAnswerMessage());
        assertFalse("Oh hell no!".equals(tq.getAnswerMessage()));

        //  Now some parceling.
        Parcel tp;
        Question after;
        tp = Parcel.obtain();
        assertNotNull(tp);
//Question.hose = true;
        tq.writeToParcel(tp, 0);
        tp.setDataPosition(0);
        after = Question.CREATOR.createFromParcel(tp);
        tp.recycle();
        assertEquals(AnswerValue.CUSTOM, after.getAnswer());
        assertEquals("Oh hell no.", after.getAnswerMessage());

        tq.setAnswer(null);
        tq.setAnswerMessage(null);
        tp = Parcel.obtain();
        assertNotNull(tp);
        tq.writeToParcel(tp, 0);
        tp.setDataPosition(0);
        after = Question.CREATOR.createFromParcel(tp);
        tp.recycle();
        assertEquals(AnswerValue.NO, after.getAnswer());
//        assertNull(after.getAnswerMessage());
    }

    public void testDisplayAnswerActivity() {

    }
}
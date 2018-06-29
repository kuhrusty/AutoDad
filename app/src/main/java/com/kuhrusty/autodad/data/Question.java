package com.kuhrusty.autodad.data;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by rusty on 1/6/15.
 */
public class Question implements Parcelable {
    private String questionMsg;
    private AnswerValue answer = AnswerValue.NO;
    //  null unless this is AnswerValue.CUSTOM
    private String answerMsg;

    public enum AnswerValue {
        NO,
        YES,
        CUSTOM;
    }

    public Question() {
    }
    public Question(String id, String qstring, AnswerValue answer, String customAnswer) {
//id
//qstring
        questionMsg = qstring;
        if (answer != null) {
            this.answer = answer;
        }
        this.answerMsg = customAnswer;
    }

    public String getQuestion() {
        return questionMsg;
    }
    public void setQuestion(String msg) {
        questionMsg = msg;
    }

    /**
     * @param val may be null, in which case NO will be used instead.
     */
    public void setAnswer(AnswerValue val) {
        answer = (val != null) ? val : AnswerValue.NO;
    }

    /**
     * Returns NO, never null.
     */
    public AnswerValue getAnswer() {
        return answer;
    }

    /**
     * @param msg may be null.
     */
    public void setAnswerMessage(String msg) {
        answerMsg = msg;
    }

    /**
     * May be null, even if getAnswer() is CUSTOM.
     */
    public String getAnswerMessage() {
        return (answerMsg != null) ? answerMsg : answer.name();
    }

    //  Parcelable
    @Override
    public int describeContents() {
        return 0;
    }

    //  Parcelable
    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(questionMsg);
        dest.writeString(answer.name());
        dest.writeString(answerMsg);
    }

    public static final Parcelable.Creator<Question> CREATOR =
            new Parcelable.Creator<Question>() {
        public Question createFromParcel(Parcel in) {
            String id = null;
            String qstring = in.readString();
            String ac = in.readString();
            AnswerValue av = (ac != null) ? AnswerValue.valueOf(ac) : null;
            String ca = in.readString();
            return new Question(id, qstring, av, ca);
        }
        public Question[] newArray(int size) {
            return new Question[size];
        }
    };
}

package com.kuhrusty.autodad.data;

import android.os.Parcel;
import android.os.Parcelable;

import com.kuhrusty.autodad.MainActivity.AnswerValue;

/**
 * Created by rusty on 1/6/15.
 */
public class Question implements Parcelable {
    private String questionMsg;
    private AnswerValue answer = AnswerValue.NO;
    //  null unless this is AnswerValue.CUSTOM
    private String answerMsg;

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
        if (answerMsg != null) {
            return answerMsg;
        } else if (answer == AnswerValue.NO) {
//            return R.string.
        }
return answer.name();
//return null;
//        return answer == AnswerValue.CUSTOM ? answerMsg : null;
    }

    //  Parcelable
    @Override
    public int describeContents() {
        return 0;
    }

public static boolean hose = false;

    //  Parcelable
    @Override
    public void writeToParcel(Parcel dest, int flags) {
if (hose) throw new RuntimeException("writing string " + answer.name());
        dest.writeString(questionMsg);
        dest.writeString(answer.name());
        if ((answerMsg != null) && answer.equals(AnswerValue.CUSTOM)) {
if (hose) throw new RuntimeException("writing string " + answerMsg);
            dest.writeString(answerMsg);
        }
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

package com.kuhrusty.autodad.data;

import android.os.Parcel;
import android.os.Parcelable;

import com.kuhrusty.autodad.MainActivity.AnswerValue;

/**
 * Created by rusty on 1/6/15.
 */
public class Sound implements Parcelable {
    private final static int UNKNOWN_RESOURCE_ID = 0;  //  we hope that's not used!

    private int resourceID = UNKNOWN_RESOURCE_ID;
    private String name;
//    private String questionMsg;
//    private AnswerValue answer = AnswerValue.NO;
//    //  null unless this is AnswerValue.CUSTOM
//    private String answerMsg;

    public Sound() {
    }
    public Sound(int resourceID, String name) {
        this.resourceID = resourceID;
        this.name = name;
    }

    public int getResourceID() {
        return resourceID;
    }
    public void setResourceID(int resourceID) {
        this.resourceID = resourceID;
    }

    /**
     * Returns true if getResourceID() isn't our unknown resource ID, which...
     * is not a very good check.
     */
    public boolean isValid() {
        return resourceID != UNKNOWN_RESOURCE_ID;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

//    public String getQuestion() {
//        return questionMsg;
//    }
//    public void setQuestion(String msg) {
//        questionMsg = msg;
//    }

//    /**
//     * @param val may be null, in which case NO will be used instead.
//     */
//    public void setAnswer(AnswerValue val) {
//        answer = (val != null) ? val : AnswerValue.NO;
//    }
//
//    /**
//     * Returns NO, never null.
//     */
//    public AnswerValue getAnswer() {
//        return answer;
//    }

//    /**
//     * @param msg may be null.
//     */
//    public void setAnswerMessage(String msg) {
//        answerMsg = msg;
//    }
//
//    /**
//     * May be null, even if getAnswer() is CUSTOM.
//     */
//    public String getAnswerMessage() {
//        if (answerMsg != null) {
//            return answerMsg;
//        } else if (answer == AnswerValue.NO) {
////            return R.string.
//        }
//return answer.name();
////return null;
////        return answer == AnswerValue.CUSTOM ? answerMsg : null;
//    }

    //  Parcelable
    @Override
    public int describeContents() {
        return 0;
    }

    //  Parcelable
    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(resourceID);
        dest.writeString(name);
    }

    public static final Creator<Sound> CREATOR = new Creator<Sound>() {
        public Sound createFromParcel(Parcel in) {
            int resID = in.readInt();
            String name = in.readString();
            return new Sound(resID, name);
        }
        public Sound[] newArray(int size) {
            return new Sound[size];
        }
    };
}

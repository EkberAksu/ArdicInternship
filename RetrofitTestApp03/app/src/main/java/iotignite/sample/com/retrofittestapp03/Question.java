package iotignite.sample.com.retrofittestapp03;

/**
 * Created by A.Aziz on 1/30/2018.
 */

import com.google.gson.annotations.SerializedName;

public class Question {

    public String title;
    public String body;

    @SerializedName("question_id")
    public String questionId;

    @Override
    public String toString() {
        return(title);
    }
}

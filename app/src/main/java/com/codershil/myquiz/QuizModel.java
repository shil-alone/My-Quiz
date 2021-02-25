package com.codershil.myquiz;

import android.os.Build;
import android.util.Base64;

import androidx.annotation.RequiresApi;

import org.json.JSONArray;
import org.json.JSONException;

import java.nio.charset.StandardCharsets;

public class QuizModel {
    private final String question ;
    private final String answer ;
    private final String[] incorrectAnswers = new String[3];

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public QuizModel(String question, String answer, JSONArray optionsArray) throws JSONException {
        this.question = question;
        this.answer = answer;

        byte[] option1 = Base64.decode( optionsArray.getString(0), Base64.DEFAULT);
        byte[] option2 = Base64.decode( optionsArray.getString(1), Base64.DEFAULT);
        byte[] option3 = Base64.decode( optionsArray.getString(2), Base64.DEFAULT);

        incorrectAnswers[0] = new String(option1, StandardCharsets.UTF_8);
        incorrectAnswers[1] =  new String(option2, StandardCharsets.UTF_8);
        incorrectAnswers[2] =  new String(option3, StandardCharsets.UTF_8);
    }


    // getter methods
    public String getQuestion() {
        return question;
    }

    public String getAnswer() {
        return answer;
    }

    public String getIncorrectAnswers(int i) {
        return incorrectAnswers[i];
    }
}

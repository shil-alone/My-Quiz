package com.codershil.myquiz;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collections;

public class MainActivity extends AppCompatActivity {

    int NO_OF_QUESTIONS=10 ;
    final int USER_PROGRESS = (int) Math.ceil(100.0/NO_OF_QUESTIONS);
    int mQuestionIndex ;
    int score ;

    MediaPlayer correct_sound;
    MediaPlayer wrong_sound;

    QuizModel[] mQuizModel = new QuizModel[NO_OF_QUESTIONS] ;


    TextView txtQuestion , txtScore ,txtResponse;
    Button   btnFirst , btnSecond, btnThird , btnFourth ;
    ProgressBar mProgressBar ,mProgressBarLoad;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        loadQuestions();
        initializeViews();
    }

    public void initializeViews(){
        txtQuestion = findViewById(R.id.question);
        txtScore = findViewById(R.id.score);
        txtResponse = findViewById(R.id.response);
        btnFirst = findViewById(R.id.option1);
        btnSecond = findViewById(R.id.option2);
        btnThird = findViewById(R.id.option3);
        btnFourth = findViewById(R.id.option4);
        mProgressBar = findViewById(R.id.progressBar);
        mProgressBarLoad = findViewById(R.id.progressBar2);
        mProgressBarLoad.setVisibility(View.VISIBLE);

        correct_sound = MediaPlayer.create(this,R.raw.correct_sound);
        wrong_sound = MediaPlayer.create(this,R.raw.wrong_sound);
    }

    /**
     * json object request from api and storing the data from json ;
     */
    public void loadQuestions() {

        Intent intent = getIntent();
        final String url = intent.getStringExtra(Categories.getKey());

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {

                    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray jsonArray = response.getJSONArray("results");

                            for (int i=0;i<jsonArray.length();i++){

                                JSONObject jsonObjectQuestions = jsonArray.getJSONObject(i);
                                String onlineQuestionEncoded =jsonObjectQuestions.getString("question");
                                byte[] data = Base64.decode(onlineQuestionEncoded, Base64.DEFAULT);
                                String onlineQuestion = new String(data, StandardCharsets.UTF_8);

                                JSONArray optionsArray = jsonObjectQuestions.getJSONArray("incorrect_answers");

                                String correctOptionEncoded = jsonObjectQuestions.getString("correct_answer");
                                data = Base64.decode(correctOptionEncoded, Base64.DEFAULT);
                                String correctOption = new String(data, StandardCharsets.UTF_8);

                                // creating the QuizModel class object and passing arguments to constructor
                                mQuizModel[i] = new QuizModel(onlineQuestion,correctOption,optionsArray);
                                mProgressBarLoad.setVisibility(View.GONE);
                            }
                        }

                        catch (JSONException e) {
                            e.printStackTrace();
                        }

                        txtQuestion.setText(mQuizModel[0].getQuestion());
                        btnFirst.setText(mQuizModel[0].getIncorrectAnswers(0));
                        btnSecond.setText(mQuizModel[0].getIncorrectAnswers(1));
                        btnThird.setText(mQuizModel[0].getAnswer());
                        btnFourth.setText(mQuizModel[0].getIncorrectAnswers(2));

                        btnFirst.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Button view = (Button) v ;
                                evaluateAnswer(view);
                                updateQuestion();
                                updateOptions();
                            }
                        });
                        btnSecond.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Button view = (Button) v ;
                                evaluateAnswer(view);
                                updateQuestion();
                                updateOptions();
                            }
                        });
                        btnThird.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Button view = (Button) v ;
                                evaluateAnswer(view);
                                updateQuestion();
                                updateOptions();
                            }
                        });
                        btnFourth.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Button view = (Button) v ;
                                evaluateAnswer(view);
                                updateQuestion();
                                updateOptions();
                            }
                        });

                    }
                },

                        new Response.ErrorListener() {

                            @Override
                            public void onErrorResponse(VolleyError error) {
                                Toast.makeText(MainActivity.this, "check your internet connection", Toast.LENGTH_SHORT).show();
                                mProgressBarLoad.setVisibility(View.GONE);

                            }
                        });

        // Add the request to the RequestQueue using singleton class
        MySingleTon.getInstance(this).addToRequestQue(jsonObjectRequest);

    }

    public void updateQuestion(){

        mQuestionIndex = (mQuestionIndex + 1) % NO_OF_QUESTIONS ;

        if (mQuestionIndex == 0){
            AlertDialog.Builder quizAlert = new AlertDialog.Builder(this);
            quizAlert.setCancelable(false);
            quizAlert.setTitle("Quiz is over");
            quizAlert.setMessage("your score is "+score);
            quizAlert.setPositiveButton("Finish the Quiz", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    finish();
                }
            });
            quizAlert.show();
        }
            txtQuestion.setText(mQuizModel[mQuestionIndex].getQuestion());
            mProgressBar.incrementProgressBy(USER_PROGRESS);

    }
    public void updateOptions(){
        
        ArrayList<String> optionsArrayList = new ArrayList<>();
        optionsArrayList.add(mQuizModel[mQuestionIndex].getIncorrectAnswers(0));
        optionsArrayList.add(mQuizModel[mQuestionIndex].getIncorrectAnswers(1));
        optionsArrayList.add(mQuizModel[mQuestionIndex].getIncorrectAnswers(2));
        optionsArrayList.add(mQuizModel[mQuestionIndex].getAnswer());
        Collections.shuffle(optionsArrayList);

        btnFirst.setText(optionsArrayList.get(0));
        btnSecond.setText(optionsArrayList.get(1));
        btnThird.setText(optionsArrayList.get(2));
        btnFourth.setText(optionsArrayList.get(3));

    }

    public void evaluateAnswer(Button btnView){
        String correctAnswer = mQuizModel[mQuestionIndex].getAnswer();
        String  usersAnswer = btnView.getText().toString();
        if (correctAnswer.equals(usersAnswer)){
            txtResponse.setText("Your Answer is :"+ correctAnswer );
            score = score + 1 ;
            txtScore.setText("Score = "+score);
            correct_sound.start();
        }
        else{
            wrong_sound.start();
            txtResponse.setText("INCORRECT ANSWER !\n"+"The Correct Answer is :"+ correctAnswer);
        }

    }
}
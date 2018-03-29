package com.example.android.spanishvocabquiz;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Pair;
import android.view.Gravity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    //Global variables
    int correctAnswerCount = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    //Method used to total number of correct answers and report back to user when submit button is pressed.
    public void submitAnswers (View view){
        //Lists to be used for checkCheckBoxAnswer
        List <Pair<Integer, Boolean> > questionTwo = Arrays.asList(
                new Pair<> (R.id.question_two_a, true),
                new Pair<>(R.id.question_two_b, false),
                new Pair<>(R.id.question_two_c, true),
                new Pair<>(R.id.question_two_d, false));
        List <Pair<Integer, Boolean>> questionFive = Arrays.asList(
                new Pair<>(R.id.question_five_a, false),
                new Pair<>(R.id.question_five_b, false),
                new Pair<>(R.id.question_five_c, true),
                new Pair<>(R.id.question_five_d, true));
        List <Pair<Integer, Boolean>> questionEight = Arrays.asList(
                new Pair<>(R.id.question_eight_a, true),
                new Pair<>(R.id.question_eight_b, false),
                new Pair<>(R.id.question_eight_c, true),
                new Pair<>(R.id.question_eight_d, false));
        //Calculating the final scores
        int score = 0;
        score = score + checkCheckBoxAnswer(questionTwo);
        score = score + checkCheckBoxAnswer(questionFive);
        score = score + checkCheckBoxAnswer(questionEight);
        correctAnswerCount = score + checkRadioButtonAnswer() + checkEditTextAnswer();
        //Creating Toast Messages with final score for users
        String scoreMessage1 = getString(R.string.congrats) + "\n" + getString(R.string.all_correct);
        String scoreMessage2 = getString(R.string.you_got) + correctAnswerCount + getString(R.string.blank_correct);
        if (correctAnswerCount == 10) {
            Toast toast = Toast.makeText(MainActivity.this, scoreMessage1, Toast.LENGTH_LONG);
            toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
            toast.show();
        } else {
            Toast toast = Toast.makeText(MainActivity.this, scoreMessage2, Toast.LENGTH_LONG);
            toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
            toast.show();
        }
    }

    //Method for checking the RadioButton questions for correct answers and adding the number of correct answers to the final score
    private int checkRadioButtonAnswer() {
        int radioButtonTotal = 0;
        RadioButton questionOneB = findViewById(R.id.question_one_b);
        boolean isCorrectQOneB = questionOneB.isChecked();
        RadioButton questionFourD = findViewById(R.id.question_four_d);
        boolean isCorrectQFourD = questionFourD.isChecked();
        RadioButton questionSevenB = findViewById(R.id.question_seven_b);
        boolean isCorrectQSevenB = questionSevenB.isChecked();
        RadioButton questionTenB = findViewById(R.id.question_ten_b);
        boolean isCorrectQTenB = questionTenB.isChecked();
        if (isCorrectQOneB){
            radioButtonTotal = radioButtonTotal + 1;
        }
        if (isCorrectQFourD){
            radioButtonTotal = radioButtonTotal + 1;
        }
        if (isCorrectQSevenB){
            radioButtonTotal = radioButtonTotal + 1;
        }
        if (isCorrectQTenB){
            radioButtonTotal = radioButtonTotal + 1;
        }
        return radioButtonTotal;
    }

    //Method for checking the CheckBox questions for correct answers and adding the number of correct answers to the final score
    private int checkCheckBoxAnswer(List <Pair<Integer, Boolean>> checkAnswersCheckBoxIds) {
        int checkBoxTotal = 0;
        for (Pair<Integer, Boolean> pair:checkAnswersCheckBoxIds) {
            CheckBox question = findViewById(pair.first);
            boolean isChecked = question.isChecked();
            if (isChecked == pair.second){
                checkBoxTotal = checkBoxTotal + 1;
            }
        }
        if (checkBoxTotal == checkAnswersCheckBoxIds.size()){
            return 1;
        }
        return 0;
    }

    //Method for checking the EditText questions for correct answers and adding the number of correct answers to the final score.
    private int checkEditTextAnswer() {
        int editTextTotal = 0;
        EditText bread = (findViewById(R.id.question_three));
        String breadAnswer = bread.getText().toString().toLowerCase();
        EditText cake = (findViewById(R.id.question_six));
        String cakeAnswer = cake.getText().toString().toLowerCase();
        EditText vegetables = (findViewById(R.id.question_nine));
        String vegetablesAnswer = vegetables.getText().toString().toLowerCase();
        if (breadAnswer.compareTo(getString(R.string.el_pan)) == 0){
            editTextTotal = editTextTotal + 1;
        }else if (breadAnswer.compareTo(getString(R.string.pan)) == 0){
            editTextTotal = editTextTotal + 1;
        }
        if (cakeAnswer.compareTo(getString(R.string.el_pastel)) == 0){
            editTextTotal = editTextTotal + 1;
        }else if (cakeAnswer.compareTo(getString(R.string.pastel_es)) == 0){
            editTextTotal = editTextTotal + 1;
        }
        if (vegetablesAnswer.compareTo(getString(R.string.las_verduras)) == 0){
            editTextTotal = editTextTotal + 1;
        }else if (vegetablesAnswer.compareTo(getString(R.string.verduras)) == 0){
            editTextTotal = editTextTotal + 1;
        }
        return editTextTotal;
    }
}

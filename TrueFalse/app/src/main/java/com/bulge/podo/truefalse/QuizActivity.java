package com.bulge.podo.truefalse;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


public class QuizActivity extends Activity {

    private static final String TAG = "QuizActivity";
    private static final String KEY_INDEX = "index";
    private static final String KEY_IS_CHEAT_PRESSED = "cheat";
    private TextView mQuestionTextView;
    private Button mTrueButton;
    private Button mFalseButton;
    private Button mNextQuestionButton;
    private Button mCheatButton;

    private TrueFalse[] mQuestionBank = new TrueFalse[]{
            new TrueFalse(R.string.question_1, false),
            new TrueFalse(R.string.question_2, true),
            new TrueFalse(R.string.question_3, true),
            new TrueFalse(R.string.question_4, true),
            new TrueFalse(R.string.question_5, false),
            new TrueFalse(R.string.question_6, false),
            new TrueFalse(R.string.question_7, false),
            new TrueFalse(R.string.question_8, true),
            new TrueFalse(R.string.question_9, true),
            new TrueFalse(R.string.question_10, false),
            new TrueFalse(R.string.question_11, false),
            new TrueFalse(R.string.question_12, true),
            new TrueFalse(R.string.question_13, false),
            new TrueFalse(R.string.question_14, true),
            new TrueFalse(R.string.question_15, true),
            new TrueFalse(R.string.question_16, false),
            new TrueFalse(R.string.question_17, false),
            new TrueFalse(R.string.question_18, false),
            new TrueFalse(R.string.question_19, true),
            new TrueFalse(R.string.question_20, false),
            new TrueFalse(R.string.question_21, true),
            new TrueFalse(R.string.question_22, false),
            new TrueFalse(R.string.question_23, true)

    };

    private int mCurrentIndex = 0;
    private boolean mIsCheater;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (data == null) {
            return;
        }
        mIsCheater = data.getBooleanExtra(CheatActivity.EXTRA_ANSWER_SHOWN, false);
    }

    private void updateQuestion() {
        int question = mQuestionBank[mCurrentIndex].getQuestion();
        mQuestionTextView.setText(question);
    }

    private void checkAnswer(boolean userPressedTrue) {
        boolean answerIsTrue = mQuestionBank[mCurrentIndex].isTrueQuestion();

        int messageResultID = 0;
        if (mIsCheater) {
            messageResultID = R.string.judgment_toast;
        } else {
            if (userPressedTrue == answerIsTrue) {
                messageResultID = R.string.correct_toast;
            } else {
                messageResultID = R.string.incorrect_toast;
            }
        }

        Toast.makeText(QuizActivity.this, messageResultID, Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate()");
        setContentView(R.layout.activity_quiz);

        mQuestionTextView = (TextView) findViewById(R.id.question_text_textview);

        mTrueButton = (Button) findViewById(R.id.true_button);
        mFalseButton = (Button) findViewById(R.id.false_button);
        mCheatButton = (Button) findViewById(R.id.cheat_button);
        mNextQuestionButton = (Button) findViewById(R.id.next_button);


        mTrueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer(true);
            }
        });

        mFalseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer(false);
            }
        });

        mCheatButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(QuizActivity.this, CheatActivity.class);
                Log.d(TAG, "кнопка");

                startActivityForResult(intent, 0);
            }
        });

        mNextQuestionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCurrentIndex = (mCurrentIndex + 1) % mQuestionBank.length;
                mIsCheater = false;
                updateQuestion();
            }
        });

        mQuestionTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCurrentIndex = (mCurrentIndex + 1) % mQuestionBank.length;
                mIsCheater = false;
                updateQuestion();
            }
        });

        if (savedInstanceState != null) {
            mCurrentIndex = savedInstanceState.getInt(KEY_INDEX, 0);
            mIsCheater = savedInstanceState.getBoolean(KEY_IS_CHEAT_PRESSED, false);
        }

        updateQuestion();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(KEY_INDEX, mCurrentIndex);
        outState.putBoolean(KEY_IS_CHEAT_PRESSED, mIsCheater);
    }
}

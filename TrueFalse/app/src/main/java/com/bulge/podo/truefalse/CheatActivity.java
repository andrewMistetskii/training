package com.bulge.podo.truefalse;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by podo on 19.12.14.
 */
public class CheatActivity extends Activity {
    public static final String EXTRA_ANSWER_IS_TRUE =
            "answer_is_true";
    public static final String EXTRA_ANSWER_SHOWN =
            "answer_shown";


    private Button showAnswerButton;
    private TextView showAnswerTextView;
    private boolean mAnswerIsTrue;

    private void setAnswerShownResult(boolean isAnswerShown){
        Intent data = new Intent();
        data.putExtra(EXTRA_ANSWER_SHOWN, isAnswerShown);
        setResult(RESULT_OK, data);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cheat);
        setAnswerShownResult(false);
        mAnswerIsTrue = getIntent().getBooleanExtra(EXTRA_ANSWER_IS_TRUE, false);
        showAnswerButton = (Button) findViewById(R.id.showAnswerButton);
        showAnswerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mAnswerIsTrue) {
                    showAnswerTextView.setText(R.string.true_button);
                } else {
                    showAnswerTextView.setText(R.string.false_button);
                }
                setAnswerShownResult(true);
            }
        });
        showAnswerTextView = (TextView) findViewById(R.id.showAnswerTextView);


    }
}

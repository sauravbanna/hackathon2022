package com.example.hackathonproject.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hackathonproject.R;
import com.example.hackathonproject.model.Question;
import com.example.hackathonproject.model.Store;
import com.example.hackathonproject.model.scores.ColourScore;
import com.example.hackathonproject.model.scores.PriceScore;
import com.example.hackathonproject.model.scores.RatingScore;
import com.example.hackathonproject.model.scores.Score;

public class RecommendProduct extends AppCompatActivity {

    // CONSTANTS
    public static final int FADE_LENGTH = 400;
    public static final Score DEFAULT_INPUT = new RatingScore(5);
    public static final double SURVEY_WELCOME_SCALE = 0.07;
    public static final double SLIDER_COUNT_SCALE = 0.04;

    // FIELDS
    private ConstraintLayout surveyQuestion;
    private ConstraintLayout surveyStart;
    private TextView question;
    private TextView sliderCount;
    private TextView surveyWelcomeText;
    private RadioButton yesButton;
    private RadioButton noButton;
    private RadioButton abstainButton;
    private RadioGroup choiceGroup;
    private RadioGroup colourGroup;
    private SeekBar slider;
    private SeekBar priceSlider;
    private Button startButton;
    private Button nextButton;
    private Store store;
    public static int surveyHeadingSize;
    public static int sliderCountSize;

    // METHODS

    public void init() {
        store = new Store();
        initLayoutObjects();
    }

    public void initLayoutObjects() {
        surveyHeadingSize = (int)(HomePage.height * SURVEY_WELCOME_SCALE);
        sliderCountSize = (int)(HomePage.height * SLIDER_COUNT_SCALE);

        yesButton = findViewById(R.id.yes);
        yesButton.setTextSize(TypedValue.COMPLEX_UNIT_DIP, sliderCountSize);
        noButton = findViewById(R.id.no);
        noButton.setTextSize(TypedValue.COMPLEX_UNIT_DIP, sliderCountSize);
        abstainButton = findViewById(R.id.no_opinion);
        abstainButton.setTextSize(TypedValue.COMPLEX_UNIT_DIP, sliderCountSize);
        choiceGroup = findViewById(R.id.choiceGroup);
        colourGroup = findViewById(R.id.colourGroup);

        surveyStart = findViewById(R.id.surveyStart);
        surveyQuestion = findViewById(R.id.surveyQuestion);
        surveyQuestion.setVisibility(View.GONE);
        surveyStart.setVisibility(View.VISIBLE);
        surveyQuestion.setAlpha(0f);
        surveyStart.setAlpha(0f);

        surveyWelcomeText = findViewById(R.id.surveyWelcome);
        surveyWelcomeText.setTextSize(TypedValue.COMPLEX_UNIT_DIP, surveyHeadingSize);

        startButton = findViewById(R.id.startButton);
        startButton.setTextSize(TypedValue.COMPLEX_UNIT_DIP, HomePage.buttonTextSize);
        nextButton = findViewById(R.id.nextQuestion);
        nextButton.setTextSize(TypedValue.COMPLEX_UNIT_DIP, HomePage.buttonTextSize);

        slider = findViewById(R.id.ratingSlider);
        priceSlider = findViewById(R.id.priceSlider);
        sliderCount = findViewById(R.id.sliderCount);
        sliderCount.setTextSize(TypedValue.COMPLEX_UNIT_DIP, sliderCountSize);
        slider.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                sliderCount.setText(String.valueOf(slider.getProgress()));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        priceSlider.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                sliderCount.setText(String.valueOf(1000 * priceSlider.getProgress()));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recommend_product);

        init();
        fadeInStartScreen();
    }

    public void fadeInStartScreen() {
        surveyStart.animate()
                .alpha(1f)
                .setDuration(FADE_LENGTH)
                .setListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        startButton.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                fadeOutStartScreen();
                            }
                        });
                    }
                });
    }

    public void fadeOutStartScreen() {
        surveyStart.animate()
                .alpha(0f)
                .setDuration(FADE_LENGTH)
                .setListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        surveyStart.setVisibility(View.GONE);
                        startButton.setOnClickListener(null);
                        surveyQuestion.setVisibility(View.VISIBLE);
                        fadeInQuestion();
                    }
                });
    }

    public void fadeOutQuestion() {
        surveyQuestion.animate()
                .alpha(0f)
                .setDuration(FADE_LENGTH)
                .setListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        surveyQuestion.setVisibility(View.GONE);
                        fadeInQuestion();
                    }
                });
    }


    public void fadeInQuestion() {
        if (store.scorePositionCheck()) {
            surveyQuestion.setVisibility(View.VISIBLE);
            hideUserInput();
            surveyQuestion.animate()
                    .alpha(1f)
                    .setDuration(FADE_LENGTH)
                    .setListener(new AnimatorListenerAdapter() {
                        @Override
                        public void onAnimationEnd(Animator animation) {
                            displayQuestion();
                        }
                    });
        } else {

        }
    }

    public void hideUserInput() {
        slider.setVisibility(View.GONE);
        sliderCount.setVisibility(View.GONE);
        choiceGroup.setVisibility(View.GONE);
        priceSlider.setVisibility(View.GONE);
        colourGroup.setVisibility(View.GONE);
    }

    public void displayQuestion() {

        nextButton.setOnClickListener(null);
        Question question = store.getQuestions().get(store.getCurrentScorePosition());
        TextView questionText = findViewById(R.id.questionSlider);
        questionText.setText(question.getQuestion());

        displayInput(question.getType());

        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Score userScore = inputSelect(question.getType());
                store.addAttribute(question.getAttribute(), userScore);
                store.addCurrentScorePosition();
                Toast toast = Toast.makeText(getApplicationContext(), store.getProductScores().get(0).toString(), Toast.LENGTH_LONG);
                toast.show();
                fadeOutQuestion();
            }
        });

    }


    public void displayInput(int type) {
        switch (type) {
            case 0:
                slider.setVisibility(View.VISIBLE);
                sliderCount.setVisibility(View.VISIBLE);
                sliderCount.setText(String.valueOf(slider.getProgress()));
                break;
            case 1:
                choiceGroup.setVisibility(View.VISIBLE);
                break;
            case 2:
                priceSlider.setVisibility(View.VISIBLE);
                sliderCount.setVisibility(View.VISIBLE);
                sliderCount.setText(String.valueOf(1000 * priceSlider.getProgress()));
                break;
            case 3:
                colourGroup.setVisibility(View.VISIBLE);
                break;
            default:
                break;
        }
    }

    public Score inputSelect(int type) {
        switch (type) {
            case 0:
                return new RatingScore(slider.getProgress());
            case 1:
                return new RatingScore(buttonIdToInt(choiceGroup.getCheckedRadioButtonId()));
            case 2:
                return new PriceScore(1000 * priceSlider.getProgress());
            case 3:
                return new ColourScore(colourIdToHex(colourGroup.getCheckedRadioButtonId()));
            default:
                return DEFAULT_INPUT;
        }
    }

    private int buttonIdToInt(int id) {
        String buttonName = choiceGroup.getResources().getResourceEntryName(id);
        switch (buttonName) {
            case "yes" :
                return 10;
            case "no" :
                return 0;
            default :
                return 5;
        }
    }

    private String colourIdToHex(int id) {
        String buttonName = colourGroup.getResources().getResourceEntryName(id);
        switch (buttonName) {
            case "black" :
                return "000000";
            case "red" :
                return "FF0000";
            case "blue" :
                return "0000FF";
            default :
                return "FFFFFF";
        }
    }



}
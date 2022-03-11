package com.example.hackathonproject.ui;

import static com.example.hackathonproject.ui.HomePage.store;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.Intent;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.TextView;

import com.example.hackathonproject.R;
import com.example.hackathonproject.model.Question;
import com.example.hackathonproject.model.Store;

public class RecommendProduct extends ProductInfoDisplay {

    // CONSTANTS
    public static final int FADE_LENGTH = 400;
    public static final double SURVEY_WELCOME_SCALE = 0.06;
    public static final double BEST_PRODUCT_SCALE = 0.05;
    public static final double SLIDER_COUNT_SCALE = 0.04;
    public static final double QUESTION_SCALE = 0.05;

    // FIELDS
    private ConstraintLayout surveyQuestion;
    private ConstraintLayout surveyStart;
    private TextView question;
    private TextView sliderCount;
    private TextView bestProductHeading;
    private TextView bestProduct1;
    private TextView bestProduct2;
    private TextView bestProduct3;
    private Button moreInfo1;
    private Button moreInfo2;
    private Button moreInfo3;
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
    private Button backButton;
    public static int surveyHeadingSize;
    public static int sliderCountSize;
    public static int bestProductHeadingSize;
    public static int bestProductSize;

    // METHODS

    public void init() {
        initLayoutObjects();
        initImageViews();
    }

    public void initLayoutObjects() {
        surveyHeadingSize = (int)(HomePage.height * SURVEY_WELCOME_SCALE);
        sliderCountSize = (int)(HomePage.height * SLIDER_COUNT_SCALE);
        bestProductHeadingSize = (int)(HomePage.height * SURVEY_WELCOME_SCALE);
        bestProductSize = (int)(HomePage.height * BEST_PRODUCT_SCALE);

        bestProduct1 = findViewById(R.id.bestProduct1);
        bestProduct2 = findViewById(R.id.bestProduct2);
        bestProduct3 = findViewById(R.id.bestProduct3);
        bestProduct1.setTextSize(TypedValue.COMPLEX_UNIT_DIP, bestProductSize);
        bestProduct2.setTextSize(TypedValue.COMPLEX_UNIT_DIP, bestProductSize);
        bestProduct3.setTextSize(TypedValue.COMPLEX_UNIT_DIP, bestProductSize);
        moreInfo1 = findViewById(R.id.moreInfo1);
        moreInfo2 = findViewById(R.id.moreInfo2);
        moreInfo3 = findViewById(R.id.moreInfo3);
        moreInfo1.setTextSize(HomePage.buttonTextSize);
        moreInfo2.setTextSize(HomePage.buttonTextSize);
        moreInfo3.setTextSize(HomePage.buttonTextSize);

        moreInfo1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setRatings(store.getProductScores().get(0).getProduct());


            }
        });

        moreInfo2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setRatings(store.getProductScores().get(1).getProduct());
            }
        });

        moreInfo3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setRatings(store.getProductScores().get(2).getProduct());
            }
        });

        refreshBestProducts();

        bestProductHeading = findViewById(R.id.bestProductHeading);
        bestProductHeading.setTextSize(TypedValue.COMPLEX_UNIT_DIP, bestProductHeadingSize);

        question = findViewById(R.id.questionSlider);
        question.setTextSize(TypedValue.COMPLEX_UNIT_DIP ,(int)(HomePage.height * QUESTION_SCALE));

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
        backButton = findViewById(R.id.previousQuestion);
        backButton.setTextSize(TypedValue.COMPLEX_UNIT_DIP, HomePage.buttonTextSize);

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
                sliderCount.setText(String.valueOf(100 * priceSlider.getProgress()));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

    }

    public void refreshBestProducts() {
        bestProduct1.setText(store.getProductScores().get(0).toString());
        bestProduct2.setText(store.getProductScores().get(1).toString());
        bestProduct3.setText(store.getProductScores().get(2).toString());
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
            Intent intent = new Intent(this, BestProducts.class);
            startActivity(intent);
            finish();
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
        backButton.setOnClickListener(null);
        nextButton.setOnClickListener(null);
        Question answeredQuestion = store.getQuestions().get(store.getCurrentScorePosition());
        question.setText(answeredQuestion.getQuestion());

        displayInput(answeredQuestion.getType());

        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Integer userScore = inputSelect(answeredQuestion.getType());
                store.addAttribute(answeredQuestion.getAttribute(), userScore);
                refreshBestProducts();
                store.addCurrentScorePosition();
                fadeOutQuestion();
            }
        });
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                store.removeAttribute(answeredQuestion.getAttribute());
                refreshBestProducts();
                store.reduceCurrectScorePosition();
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

    public int inputSelect(int type) {
        switch (type) {
            case 0:
                return slider.getProgress();
            case 1:
                return buttonIdToInt(choiceGroup.getCheckedRadioButtonId());
            case 2:
                return (int)(10*(Math.abs(100 * priceSlider.getProgress() - Store.MAX_PRICE) / (double)Store.MAX_PRICE));
            default:
                return 5;
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




}
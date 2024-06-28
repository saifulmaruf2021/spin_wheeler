package com.devmarufIt.spinwheel;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity2 extends AppCompatActivity {
    private ImageButton spin;
    private ImageView wheel;
    /*int intValue;
    boolean isads = true;
    Random r;
    String current_score;
    int degree = 0;
    int degree_old = 0;
    int score = 0;*/
    RelativeLayout rl_wallet;
    //int score = 0;
    TextView tv;
    private boolean isSpinning = false;
    private static final int SECTORS = 8;
    Random random = new Random();
    private String[] divisions = {"1", "2", "3", "2", "2", "1", "2", "5"}; // Array of division labels
    private float currentAngle = 0.0f; // Keeps track of the current wheel rotation

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        spin = findViewById(R.id.spin);
        wheel = findViewById(R.id.wheel);
        tv = findViewById(R.id.tv);
        rl_wallet = findViewById(R.id.rl_wheel);
        rl_wallet.setOnClickListener(v -> {
            startActivity(new Intent(MainActivity2.this, MainActivity.class));
        });

        /*current_score = currentNumber(360 - (this.degree % 360));
        r = new Random();*/

        /*spin.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                getcoin();
            }
        });*/

        spin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!isSpinning) {
                    spinTheWheel();
                }
            }

        });

    }

    /*private void spinWheel() {
        float randomAngle = (float) (Math.random() * 360);
        float anglePerDivision = 360.0f / 8.0f;
        float finalAngle = currentAngle + randomAngle % anglePerDivision;

        RotateAnimation rotateAnimation = new RotateAnimation(
                currentAngle, finalAngle,
                Animation.RELATIVE_TO_SELF, 0.5f,
                Animation.RELATIVE_TO_SELF, 0.5f);
        rotateAnimation.setDuration(3000);

        currentAngle = finalAngle;

        wheel.startAnimation(rotateAnimation);

        // Calculate landed division based on final angle
        int landedDivision = Math.round(finalAngle / anglePerDivision) % divisions.length;

        // Display landed number on toast
        Toast.makeText(getApplicationContext(), "Landed on: " + divisions[landedDivision], Toast.LENGTH_SHORT).show();
    }*/


    private void spinTheWheel(){

        int degree = random.nextInt(360) + 3600; // Random degree for spinning
        float pivotX = wheel.getWidth() / 5f;
        float pivotY = wheel.getHeight() / 5f;

        ObjectAnimator animator = ObjectAnimator.ofFloat(wheel, "rotation", 0f, degree);
        animator.setDuration(3000); // Duration of the spin
        animator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationStart(Animator animation) {
                isSpinning = true;
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                isSpinning = false;
                int resultSector = (degree % 360) / (360 / SECTORS);
                handleSpinResult(resultSector);
            }
        });
        animator.start();
    }
    private void handleSpinResult(int sector) {
        // Handle the result of the spin
        // For example, display the selected category
        String[] categories = {"1", "2", "3", "2", "2", "1", "2", "5"};
        String selectedCategory = categories[sector];
        // Display the selected category (you could use a Toast, a TextView, etc.)
        Toast.makeText(this,"Selected: " + selectedCategory,Toast.LENGTH_SHORT).show();
    }



    /*private void getcoin() {
        this.degree_old = this.degree % 360;
        this.degree = this.r.nextInt(3600) + 780;
        RotateAnimation rotateAnimation = new RotateAnimation((float) this.degree_old, (float) this.degree, 1, 0.5f, 1, 0.5f);
        rotateAnimation.setDuration(3000);
        rotateAnimation.setFillAfter(true);
        rotateAnimation.setInterpolator(new DecelerateInterpolator());
        rotateAnimation.setAnimationListener(new Animation.AnimationListener() {
            public void onAnimationRepeat(Animation animation) {
            }

            public void onAnimationStart(Animation animation) {
                tv.setText("Wait..");
            }

            public void onAnimationEnd(Animation animation) {
                MainActivity2.this.tv.setText(MainActivity2.this.currentNumber(360 - (MainActivity2.this.degree % 360)));
                SharedPreferences.Editor edit = MainActivity2.this.getSharedPreferences("your_prefs", 0).edit();
                edit.putInt("your_int_key", MainActivity2.this.intValue + MainActivity2.this.score);
                edit.commit();
                MainActivity2.this.getSharedPreferences("your_prefs", 0);
                MainActivity2.this.diaglog();
            }
        });
        wheel.startAnimation(rotateAnimation);
    }*/

    /*private String currentNumber(int i) {
        String str = "";
        float f = (float) i;
        if (f >= 10.0f && f < 30.0f) {
            str = "1";
            this.score += 1;
        }
        if (f >= 30.0f && f < 50.0f) {
            str = "2";
            this.score += 2;
        }
        if (f >= 50.0f && f < 70.0f) {
            str = "2";
            this.score += 3;
        }
        if (f >= 70.0f && f < 90.0f) {
            str = "1";
            this.score += 2;
        }
        if (f >= 90.0f && f < 110.0f) {
            str = "2";
            this.score += 2;
        }
        if (f >= 110.0f && f < 130.0f) {
            str = "3";
            this.score += 1;
        }


        //return ((f < 345.0f || i >= 360) && (i < 0 || f >= 15.0f)) ? str : "0 point";
        return str;
    }*/

    /*public void diaglog() {
        final Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.win_popup);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(0));
        Button button = (Button) dialog.findViewById(R.id.cool_id);
        TextView textView = (TextView) dialog.findViewById(R.id.dialog_score_id);
        String currentNumber = currentNumber(360 - (this.degree % 360));
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(currentNumber);
        stringBuilder.append(" Points");
        textView.setText(stringBuilder.toString());
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (MainActivity2.this.isads) {
                    MainActivity2.this.isads = false;
                    //AppAdsHandler.loadFBInterstitialAds(MainActivity.this);
                } else {
                    MainActivity2.this.isads = true;
                    //AppAdsHandler.loadAdmobInterstitialAds(Activity_Spin_Wheel.this);
                }
                dialog.dismiss();
            }
        });
        dialog.show();
    }*/

    /*public void finish() {
        Intent intent = new Intent(this, MainActivity2.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }*/

    /*protected void onStart() {
        super.onStart();
        this.intValue = getIntent().getIntExtra("INT", 0);
    }*/


    @SuppressLint("MissingSuperCall")
    @Override
    public void onBackPressed() {
        //super.onBackPressed();
        finishAffinity();
    }
}
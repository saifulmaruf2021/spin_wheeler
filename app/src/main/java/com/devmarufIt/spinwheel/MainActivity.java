package com.devmarufIt.spinwheel;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    String current_score;
    int degree = 0;
    int degree_old = 0;
    ImageButton ib_spin;
    ImageView ib_wheel, back_btn;
    int intValue;
    boolean isads = true;
    Random r;
    RelativeLayout rl_wallet;
    int score = 0;
    TextView tv;
    //private com.facebook.ads.InterstitialAd interstitialAd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //interstitialAd = new com.facebook.ads.InterstitialAd(this, AppAdsHandler.Puzzle_FB_INTRESTITIAL_AD_PUB_ID);

        /*interstitialAd.setAdListener(new InterstitialAdListener() {
            @Override
            public void onInterstitialDisplayed(Ad ad) {

            }

            @Override
            public void onInterstitialDismissed(Ad ad) {
                finish();
                interstitialAd.loadAd();
            }

            @Override
            public void onError(Ad ad, AdError adError) {

            }

            @Override
            public void onAdLoaded(Ad ad) {
                // interstitialAd.show();
            }

            @Override
            public void onAdClicked(Ad ad) {

            }

            @Override
            public void onLoggingImpression(Ad ad) {

            }
        });*/

        // Load a new interstitial.
        //interstitialAd.loadAd();
        this.ib_wheel = (ImageView) findViewById(R.id.wheel);
        this.ib_spin = (ImageButton) findViewById(R.id.button);
        this.tv = (TextView) findViewById(R.id.textview);
        this.rl_wallet = (RelativeLayout) findViewById(R.id.rl_goto_wallet);
        this.back_btn = (ImageView) findViewById(R.id.back_btn);

        back_btn.setOnClickListener(v -> {
            startActivity(new Intent(MainActivity.this,MainActivity2.class));
        });
        this.rl_wallet.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                //Activity_Spin_Wheel.this.startActivity(new Intent(Activity_Spin_Wheel.this, Activity_Redeem.class));
            }
        });
        this.current_score = currentNumber(360 - (this.degree % 360));
        this.r = new Random();
        this.ib_spin.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                MainActivity.this.getcoin();
            }
        });

    }

    private void getcoin() {
        this.degree_old = this.degree % 360;
        this.degree = this.r.nextInt(3600) + 720;
        RotateAnimation rotateAnimation = new RotateAnimation((float) this.degree_old, (float) this.degree, 1, 0.5f, 1, 0.5f);
        rotateAnimation.setDuration(3600);
        rotateAnimation.setFillAfter(true);
        rotateAnimation.setInterpolator(new DecelerateInterpolator());
        rotateAnimation.setAnimationListener(new Animation.AnimationListener() {
            public void onAnimationRepeat(Animation animation) {
            }

            public void onAnimationStart(Animation animation) {
                MainActivity.this.tv.setText("Wait..");
            }

            public void onAnimationEnd(Animation animation) {
                MainActivity.this.tv.setText(MainActivity.this.currentNumber(360 - (MainActivity.this.degree % 360)));
                SharedPreferences.Editor edit = MainActivity.this.getSharedPreferences("your_prefs", 0).edit();
                edit.putInt("your_int_key", MainActivity.this.intValue + MainActivity.this.score);
                edit.commit();
                MainActivity.this.getSharedPreferences("your_prefs", 0);
                MainActivity.this.diaglog();
            }
        });
        this.ib_wheel.startAnimation(rotateAnimation);
    }

    private String currentNumber(int i) {
        String str = "";
        float f = (float) i;
        if (f >= 15.0f && f < 45.0f) {
            str = "2";
            this.score += 2;
        }
        if (f >= 45.0f && f < 75.0f) {
            str = "3";
            this.score += 3;
        }
        if (f >= 75.0f && f < 105.0f) {
            str = "10";
            this.score += 10;
        }
        if (f >= 105.0f && f < 135.0f) {
            str = "5";
            this.score += 5;
        }
        if (f >= 135.0f && f < 165.0f) {
            str = "6";
            this.score += 6;
        }
        if (f >= 165.0f && f < 195.0f) {
            str = "7";
            this.score += 7;
        }
        if (f >= 195.0f && f < 225.0f) {
            str = "8";
            this.score += 8;
        }
        if (f >= 225.0f && f < 255.0f) {
            str = "9";
            this.score += 9;
        }
        if (f >= 255.0f && f < 285.0f) {
            str = "100";
            this.score += 100;
        }
        if (f >= 285.0f && f < 315.0f) {
            str = "11";
            this.score += 11;
        }
        if (f >= 315.0f && f < 345.0f) {
            str = "12";
            this.score += 12;
        }
        return ((f < 345.0f || i >= 360) && (i < 0 || f >= 15.0f)) ? str : "0 point";
    }

    public void diaglog() {
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
                if (MainActivity.this.isads) {
                    MainActivity.this.isads = false;
                    //AppAdsHandler.loadFBInterstitialAds(MainActivity.this);
                } else {
                    MainActivity.this.isads = true;
                    //AppAdsHandler.loadAdmobInterstitialAds(Activity_Spin_Wheel.this);
                }
                dialog.dismiss();
            }
        });
        dialog.show();
    }

    public void finish() {
        Intent intent = new Intent(this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }

    protected void onStart() {
        super.onStart();
        this.intValue = getIntent().getIntExtra("INT", 0);
    }
    /*public void onBackPressed() {
        if (interstitialAd == null || !interstitialAd.isAdLoaded()) {
            super.onBackPressed();
        } else {
            // Ad was loaded, show it!
            interstitialAd.show();
        }

    }*/

    @SuppressLint("MissingSuperCall")
    @Override
    public void onBackPressed() {
        //super.onBackPressed();
        startActivity(new Intent(MainActivity.this,MainActivity2.class));

    }



    }
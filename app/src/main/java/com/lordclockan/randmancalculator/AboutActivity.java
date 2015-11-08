package com.lordclockan.randmancalculator;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

public class AboutActivity extends AppCompatActivity {

    private TextView tvAppInfo, tvAboutExplanation;
    private boolean mAlreadyClicked = false;
    private ImageView ivAboutLogo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        tvAboutExplanation = (TextView) findViewById(R.id.tvAboutExplanation);
        tvAppInfo = (TextView) findViewById(R.id.tvAboutInfo);
        tvAppInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!mAlreadyClicked) {
                    tvAboutExplanation.setVisibility(View.VISIBLE);
                    mAlreadyClicked = true;
                } else {
                    tvAboutExplanation.setVisibility(View.INVISIBLE);
                    mAlreadyClicked = false;
                }

            }
        });

        ivAboutLogo = (ImageView) findViewById(R.id.ivAboutLogo);
        ivAboutLogo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.startAnimation(AnimationUtils.loadAnimation(getApplicationContext(),
                        R.anim.rotate));
            }
        });

        addListenerOnAuthor();
    }

    public void addListenerOnAuthor() {

        TextView lblAuthor = (TextView) findViewById(R.id.lblInfo_Author);

        lblAuthor.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {

                Intent emailIntent =
                        new Intent(Intent.ACTION_SEND);
                String[] recipients = new String[]{"davor@losinj.com", "",};
                emailIntent.putExtra(Intent.EXTRA_EMAIL, recipients);
                emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Randman calculator info");
                emailIntent.setType("text/plain");
                startActivity(Intent.createChooser(emailIntent, "Send mail..."));
                finish();

            }

        });
    }

    @Override
    public boolean onSupportNavigateUp(){
        finish();
        return true;
    }

    /*@Override
    public void onBackPressed() {
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_left);
        super.onBackPressed();
    }*/
}

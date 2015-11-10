package com.lordclockan.randmancalculator;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private EditText mOlivesInKg, mOlivesInLt;
    private TextView mRandmanResult;
    private String mOlivesInKgString, mOlivesInLtString, mResult;
    private double mFormula;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mOlivesInKg = (EditText) findViewById(R.id.etOlivesInKg);
        mOlivesInLt = (EditText) findViewById(R.id.etOlivesInLt);
        mRandmanResult = (TextView) findViewById(R.id.tvRandmanResult);

        mOlivesInKg.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                // If the event is a key-down event on the "enter" button
                if ((event.getAction() == KeyEvent.ACTION_DOWN) &&
                        (keyCode == KeyEvent.KEYCODE_ENTER)) {
                    // Perform action on key press
                    calculateRandman();
                    return true;
                }
                return false;
            }
        });

        mOlivesInLt.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                // If the event is a key-down event on the "enter" button
                if ((event.getAction() == KeyEvent.ACTION_DOWN) &&
                        (keyCode == KeyEvent.KEYCODE_ENTER)) {
                    // Perform action on key press
                    calculateRandman();
                    return true;
                }
                return false;
            }
        });

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                view.startAnimation(AnimationUtils.loadAnimation(getApplicationContext(),
                        R.anim.image_click));
                calculateRandman();
            }
        });

        FloatingActionButton fabClear = (FloatingActionButton) findViewById(R.id.fabClear);
        fabClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final View coordinatorLayoutView = findViewById(R.id.snackbar);
                v.startAnimation(AnimationUtils.loadAnimation(getApplicationContext(),
                        R.anim.image_click));
                if (mOlivesInKg.getText().toString().isEmpty() &&
                        mOlivesInLt.getText().toString().isEmpty() &&
                        mRandmanResult.getText().toString().isEmpty()) {
                    Snackbar.make(coordinatorLayoutView, R.string.already_cleared_snackbar,
                            Snackbar.LENGTH_SHORT).setAction("Action", null).show();
                } else {
                    mOlivesInKg.setText("");
                    mOlivesInLt.setText("");
                    mRandmanResult.setText("");
                    Snackbar.make(coordinatorLayoutView, R.string.clear_snackbar,
                            Snackbar.LENGTH_SHORT).setAction("Action", null).show();
                }
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_about) {
            Intent intent = new Intent(MainActivity.this, AboutActivity.class);
            MainActivity.this.startActivity(intent);
            overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_left);
        }

        return super.onOptionsItemSelected(item);
    }

    public void calculateRandman () {
        final View coordinatorLayoutView = findViewById(R.id.snackbar);
        if (mOlivesInKg.getText().toString().isEmpty() ||
                mOlivesInLt.getText().toString().isEmpty()) {
            Snackbar.make(coordinatorLayoutView, R.string.empty_fields_snackbar, Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show();
        } else if (mOlivesInKg.getText().toString().equals("0") ||
                mOlivesInLt.getText().toString().equals("0")) {
            Snackbar.make(coordinatorLayoutView, R.string.zero_fields_snackbar, Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show();
        } else {
            mOlivesInKgString = mOlivesInKg.getText().toString();
            mOlivesInLtString = mOlivesInLt.getText().toString();
            mFormula = ((Double.parseDouble(mOlivesInLtString) * 0.9) /
                    Double.parseDouble(mOlivesInKgString)) * 100;
            mResult = String.format("%.3f", mFormula);
            mRandmanResult.setText(mResult + " %");
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    /*@Override
    protected void onResume() {
        super.onResume();
        fadeInAnimation();
    }

    public void fadeInAnimation() {
        final View coordinatorLayoutView = findViewById(R.id.snackbar);
        Animation animRotateCardView = AnimationUtils.loadAnimation(this, R.anim.fade_in);
        coordinatorLayoutView.startAnimation(animRotateCardView);
    }*/
}

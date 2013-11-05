package com.example.demoprogressbutton;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

public class ProgressButtonActivity extends Activity {
    private ProgressButton mProgressBtn;
    private Button mFillBtn;
    private Button mStrokeBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_progress_button);

        mProgressBtn = (ProgressButton)findViewById(R.id.progress_btn);
        mProgressBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                mProgressBtn.setClickable(false);
                new ProgressTask().execute();
            }
        });
        mFillBtn = (Button)findViewById(R.id.type_fill_btn);
        mFillBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                mProgressBtn.setType(ProgressButton.TYPE_FILL);    // the fill type
            }
        });
        mStrokeBtn = (Button)findViewById(R.id.type_stroke_btn);
        mStrokeBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                mProgressBtn.setType(ProgressButton.TYPE_STROKE);    //the stroke type
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.progress_button, menu);
        return true;
    }

    public class ProgressTask extends AsyncTask<Void, Integer, Void> {
        private int mProgress = 0;

        @Override
        protected Void doInBackground(Void... params) {
            while(mProgress >= 0 && mProgress <= 100) {
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                publishProgress(mProgress += 3);
            }

            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            mProgressBtn.setClickable(true);
            Toast.makeText(getApplicationContext(), "finish download task",
                    Toast.LENGTH_SHORT).show();
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            mProgressBtn.updateProgress(values[0]);
        }

    }
}

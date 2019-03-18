package com.haiphon.customviewdemo.plugins;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import com.haiphon.customviewdemo.R;
import com.haiphon.customviewdemo.customview.CircleProgressView;

public class CircleProgressActivity extends AppCompatActivity {

    private int mTotalProgress = 90;
    private int mCurrentProgress = 0;
    //进度条
    private CircleProgressView mTasksView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_circleprogess);

        mTasksView = (CircleProgressView) findViewById(R.id.tasks_view);

        new Thread(new ProgressRunable()).start();
    }

    class ProgressRunable implements Runnable {
        @Override
        public void run() {
            while (mCurrentProgress < mTotalProgress) {
                mCurrentProgress += 1;
                mTasksView.setProgress(mCurrentProgress);
                try {
                    Thread.sleep(90);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

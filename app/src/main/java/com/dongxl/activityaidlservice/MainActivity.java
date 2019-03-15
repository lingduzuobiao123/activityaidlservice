package com.dongxl.activityaidlservice;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.dongxl.activityaidlservice.activity.MyActivity;
import com.dongxl.activityaidlservice.fragment.MyFragment;
import com.dongxl.activityaidlservice.service.MyService;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.bottom)
    Button bottom;
    @BindView(R.id.bottom1)
    Button bottom1;
    @BindView(R.id.bottom2)
    Button bottom2;
    @BindView(R.id.bottom3)
    Button bottom3;
    @BindView(R.id.bottom4)
    Button bottom4;
    @BindView(R.id.bottom5)
    Button bottom5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.bottom, R.id.bottom1, R.id.bottom2, R.id.bottom3, R.id.bottom4, R.id.bottom5, R.id.bottom6})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.bottom:
                startActivity();
                break;
            case R.id.bottom1:
                break;
            case R.id.bottom2:
                break;
            case R.id.bottom3:
                break;
            case R.id.bottom4:
                startService();
                break;
            case R.id.bottom5:
                stopService();
                break;
            case R.id.bottom6:
                addFragment();
                break;
        }
    }

    private void startActivity() {
        Intent intent = new Intent();
        intent.putExtra("MyActivity", "startActivity");
        intent.setClass(this, MyActivity.class);
        startActivity(intent);
    }

    private void startService() {
        Intent intent = new Intent();
        intent.putExtra("MyService", "startService");
        intent.setClass(this, MyService.class);
        startService(intent);
    }

    private void stopService() {
        Intent intent = new Intent();
        intent.setClass(this, MyService.class);
        startService(intent);
    }

    private void addFragment() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.addToBackStack(null);
        transaction.replace(R.id.container, MyFragment.newInstance());
        transaction.commitAllowingStateLoss();
    }
}

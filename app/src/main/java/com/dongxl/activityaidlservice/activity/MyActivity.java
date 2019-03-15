package com.dongxl.activityaidlservice.activity;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.dongxl.activityaidlservice.IServiceUiAidlInterface;
import com.dongxl.activityaidlservice.IUiServiceAidlInterface;
import com.dongxl.activityaidlservice.R;
import com.dongxl.activityaidlservice.bean.TestBean;
import com.dongxl.activityaidlservice.bean.TestBean2;
import com.dongxl.activityaidlservice.service.MyService;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @Author: dongxl
 * @Date: 2019/3/15
 * @Description:
 */
public class MyActivity extends AppCompatActivity {

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
    private IServiceUiAidlInterface serviceUiAidlInterface = new IServiceUiAidlInterface.Stub() {

        @Override
        public void basicTypes(int anInt, long aLong, boolean aBoolean, float aFloat, double aDouble, String aString) throws RemoteException {

        }

        @Override
        public void serviceUiTest(TestBean testBean) throws RemoteException {
            Log.e("dongxl", "activity--TestBean==" + testBean.toString());
        }

        @Override
        public void serviceUiTest2(TestBean2 testBean) throws RemoteException {
            Log.e("dongxl", "activity--TestBean2==" + testBean.toString());
        }
    };
    private IUiServiceAidlInterface uiServiceAidlInterface;
    ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            Log.e("dongxl", "activity--ServiceConnection==onServiceConnected==" + name);
            uiServiceAidlInterface = IUiServiceAidlInterface.Stub.asInterface(service);
            try {
                uiServiceAidlInterface.registerCallback(serviceUiAidlInterface);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            Log.e("dongxl", "activity--ServiceConnection==onServiceConnected==" + name);
            if (null != uiServiceAidlInterface) {
                try {
                    uiServiceAidlInterface.unRegisterCallback(serviceUiAidlInterface);
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
                uiServiceAidlInterface = null;
            }
        }
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.e("dongxl", "activity--onCreate==" + getIntent().getStringExtra("MyActivity"));
        setContentView(R.layout.activity_my);
        ButterKnife.bind(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.e("dongxl", "activity--==onResume==");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.e("dongxl", "activity--==onPause==");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.e("dongxl", "activity--onStop====");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.e("dongxl", "activity--==onDestroy==");
    }

    /**
     * 隐士启动
     */
    private void bindServer() {
        //另一个应用程序中去绑定Service的时候并没有MyService这个类，这时就必须使用到隐式Intent了
        //AndroidManifest.xml中的代码，给MyService加上一个action
        //Android 5.0以后不支持隐式意图启动服务
        Intent intent = new Intent();
        intent.putExtra("MyService", "bindService");
        intent.setAction("com.dongxl.activityaidlservice.service.MyService");
        intent.setComponent(new ComponentName("com.dongxl.activityaidlservice", "com.dongxl.activityaidlservice.service.MyService"));
        bindService(intent, serviceConnection, Context.BIND_AUTO_CREATE);
    }

    /**
     * 显示启动
     */
    private void bindServer1() {
        //Activity与Service之间建立关联，需要调用bindService()方法，并将Intent作为参数传递进去，在Intent里指定好要绑定的Service
        //在构建Intent的时候是使用MyService.class来指定要绑定哪一个Service的
        Intent bindIntent = new Intent();
        bindIntent.putExtra("MyService", "bindServer1");
        bindIntent.setClass(this, MyService.class);
        bindService(bindIntent, serviceConnection, Context.BIND_AUTO_CREATE);
    }

    /**
     * 接触绑定
     */
    private void unBindServer() {
        try {
            // 当需要多次调用doSomething()方法的时候，如果直接bindService是会报错的
            unbindService(serviceConnection);
        } catch (Exception e) {
            e.printStackTrace();
        }
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
        stopService(intent);
    }

    @OnClick({R.id.bottom1, R.id.bottom2, R.id.bottom3, R.id.bottom4, R.id.bottom5})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.bottom1:
                bindServer();
                break;
            case R.id.bottom2:
                bindServer1();
                break;
            case R.id.bottom3:
                unBindServer();
                break;
            case R.id.bottom4:
                startService();
                break;
            case R.id.bottom5:
                stopService();
                break;
        }
    }
}

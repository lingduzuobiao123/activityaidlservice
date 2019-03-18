package com.dongxl.activityaidlservice.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.annotation.IntDef;
import android.util.Log;

import com.dongxl.activityaidlservice.IServiceUiAidlInterface;
import com.dongxl.activityaidlservice.IUiServiceAidlInterface;
import com.dongxl.activityaidlservice.bean.ChildTestBean;
import com.dongxl.activityaidlservice.bean.TestBean;
import com.dongxl.activityaidlservice.bean.TestBean2;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * @Author: dongxl
 * @Date: 2019/3/15
 * @Description:
 */
public class MyService extends Service {
    /**
     * 默认
     */
    public static final int SERVICE_DEFAULT = 0;
    public static final int SERVICE_BINDING = 1;
    public static final int SERVICE_STARTING = 2;

    @IntDef({SERVICE_DEFAULT, SERVICE_BINDING, SERVICE_STARTING})
    @Retention(RetentionPolicy.SOURCE)
    public @interface ServiceState {
    }

    private int mServiceState = SERVICE_DEFAULT;

    private IServiceUiAidlInterface serviceUiAidlInterface;
    private IUiServiceAidlInterface uiServiceAidlInterface;
//    private IUiServiceAidlInterface uiServiceAidlInterface = new IUiServiceAidlInterface.Stub() {
//        @Override
//        public void registerCallback(IServiceUiAidlInterface callback) throws RemoteException {
//            Log.e("dongxl", "service--registerCallback==");
//            serviceUiAidlInterface = callback;
//            TestBean testBean = new TestBean();
//            testBean.setTestStr1("TestBean");
//            ChildTestBean childTestBean = new ChildTestBean();
//            childTestBean.setString("childTestBean");
//            testBean.setChildTestBean(childTestBean);
//            serviceUiAidlInterface.serviceUiTest(testBean);
//            TestBean2 testBean2 = new TestBean2();
//            testBean2.setaDouble1(1.11d);
//            testBean2.setTestStr1("TestBean2");
//            ChildTestBean childTestBean2 = new ChildTestBean();
//            childTestBean2.setString("childTestBean2");
//            testBean2.setChildTestBean(childTestBean2);
//            serviceUiAidlInterface.serviceUiTest2(testBean2);
//        }
//
//        @Override
//        public void unRegisterCallback(IServiceUiAidlInterface callback) throws RemoteException {
//            Log.e("dongxl", "service--unRegisterCallback==");
//            serviceUiAidlInterface = null;
//        }
//    };

    class UiServiceAidlInterface extends IUiServiceAidlInterface.Stub {

        @Override
        public void registerCallback(IServiceUiAidlInterface callback) throws RemoteException {
            Log.e("dongxl", "service--registerCallback==" + MyService.this);
            serviceUiAidlInterface = callback;
            TestBean testBean = new TestBean();
            testBean.setTestStr1("TestBean");
            ChildTestBean childTestBean = new ChildTestBean();
            childTestBean.setString("childTestBean");
            testBean.setChildTestBean(childTestBean);
            serviceUiAidlInterface.serviceUiTest(testBean);
            TestBean2 testBean2 = new TestBean2();
            testBean2.setaDouble1(1.11d);
            testBean2.setTestStr1("TestBean2");
            ChildTestBean childTestBean2 = new ChildTestBean();
            childTestBean2.setString("childTestBean2");
            testBean2.setChildTestBean(childTestBean2);
            serviceUiAidlInterface.serviceUiTest2(testBean2);
        }

        @Override
        public void unRegisterCallback(IServiceUiAidlInterface callback) throws RemoteException {
            Log.e("dongxl", "service--unRegisterCallback==" + MyService.this);
            serviceUiAidlInterface = null;
        }
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mServiceState = SERVICE_DEFAULT;
        Log.e("dongxl", "service--onCreate==" + MyService.this);
    }

    @Override
    public IBinder onBind(Intent intent) {
        Log.e("dongxl", "service--onBind==" + intent.getStringExtra("MyService") + "===" + MyService.this);
        if (null == uiServiceAidlInterface) {
            uiServiceAidlInterface = new UiServiceAidlInterface();
        }
        mServiceState = SERVICE_BINDING;
        return uiServiceAidlInterface.asBinder();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.e("dongxl", "service--onStartCommand==flags==" + flags + "==startId==" + startId + "==" + intent.getStringExtra("MyService") + "==" + serviceUiAidlInterface + "==" + MyService.this);
        if (mServiceState != SERVICE_BINDING) {
            mServiceState = SERVICE_STARTING;
        }
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mServiceState = SERVICE_DEFAULT;
        Log.e("dongxl", "service--onDestroy==" + "==" + serviceUiAidlInterface + "==" + MyService.this);
    }

    @Override
    public boolean onUnbind(Intent intent) {
        Log.e("dongxl", "service--onUnbind==" + "==" + serviceUiAidlInterface + "==" + MyService.this);
        mServiceState = SERVICE_DEFAULT;
        return super.onUnbind(intent);
    }

    @Override
    public void onRebind(Intent intent) {
        super.onRebind(intent);
        mServiceState = SERVICE_BINDING;
        Log.e("dongxl", "service--onRebind==" + MyService.this);
    }

    public int getServiceState() {
        return mServiceState;
    }
}

package com.dongxl.activityaidlservice.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;

import com.dongxl.activityaidlservice.IServiceUiAidlInterface;
import com.dongxl.activityaidlservice.IUiServiceAidlInterface;
import com.dongxl.activityaidlservice.bean.ChildTestBean;
import com.dongxl.activityaidlservice.bean.TestBean;
import com.dongxl.activityaidlservice.bean.TestBean2;

/**
 * @Author: dongxl
 * @Date: 2019/3/15
 * @Description:
 */
public class MyService extends Service {
    private IServiceUiAidlInterface serviceUiAidlInterface;
    private IUiServiceAidlInterface uiServiceAidlInterface = new IUiServiceAidlInterface.Stub() {
        @Override
        public void basicTypes(int anInt, long aLong, boolean aBoolean, float aFloat, double aDouble, String aString) throws RemoteException {
            Log.e("dongxl", "service--basicTypes==");
        }

        @Override
        public void registerCallback(IServiceUiAidlInterface callback) throws RemoteException {
            Log.e("dongxl", "service--registerCallback==");
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
            Log.e("dongxl", "service--unRegisterCallback==");
            serviceUiAidlInterface = null;
        }
    };

    @Override
    public void onCreate() {
        super.onCreate();
        Log.e("dongxl", "service--onCreate==");
    }

    @Override
    public IBinder onBind(Intent intent) {
        Log.e("dongxl", "service--onBind==" + intent.getStringExtra("MyService"));
        return uiServiceAidlInterface.asBinder();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.e("dongxl", "service--onStartCommand==flags==" + flags + "==startId==" + startId + "==" + intent.getStringExtra("MyService") + "==" + serviceUiAidlInterface);
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.e("dongxl", "service--onDestroy==" + "==" + serviceUiAidlInterface);
    }

    @Override
    public boolean onUnbind(Intent intent) {
        Log.e("dongxl", "service--onUnbind==" + "==" + serviceUiAidlInterface);
        return super.onUnbind(intent);
    }

    @Override
    public void onRebind(Intent intent) {
        super.onRebind(intent);
        Log.e("dongxl", "service--onRebind==");
    }
}

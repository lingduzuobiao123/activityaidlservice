package com.dongxl.activityaidlservice.fragment;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
import butterknife.Unbinder;

/**
 * @Author: dongxl
 * @Date: 2019/3/15
 * @Description:
 */
public class MyFragment extends Fragment {
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
    Unbinder unbinder;

    public static MyFragment newInstance() {
        MyFragment fragment = new MyFragment();
        return fragment;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Log.e("dongxl", "fragment--onAttach==");
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.e("dongxl", "fragment--onCreate==");
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.e("dongxl", "fragment--onCreateView==");
        return inflater.inflate(R.layout.fragment_my, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        Log.e("dongxl", "fragment--onViewCreated==");
        super.onViewCreated(view, savedInstanceState);
        unbinder = ButterKnife.bind(this, view);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.e("dongxl", "fragment--onActivityCreated==");
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.e("dongxl", "fragment--onResume==");
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.e("dongxl", "fragment--onPause==");
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.e("dongxl", "fragment--onStop==");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.e("dongxl", "fragment--onDestroy==");
    }

    @Override
    public void onDetach() {
        super.onDetach();
        Log.e("dongxl", "fragment--onDetach==");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
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

    private IUiServiceAidlInterface uiServiceAidlInterface;
    private IServiceUiAidlInterface serviceUiAidlInterface;
    private ServiceConnection serviceConnection;

    /**
     * 隐士启动
     */
    private void bindServer() {
        //另一个应用程序中去绑定Service的时候并没有MyService这个类，这时就必须使用到隐式Intent了
        //AndroidManifest.xml中的代码，给MyService加上一个action
        //Android 5.0以后不支持隐式意图启动服务
        if (null == serviceConnection) {
            serviceConnection = new MyServiceConnection();
        }
        Intent intent = new Intent();
        intent.putExtra("MyService", "bindService");
        intent.setAction("com.dongxl.activityaidlservice.service.MyService");
        intent.setComponent(new ComponentName("com.dongxl.activityaidlservice", "com.dongxl.activityaidlservice.service.MyService"));
        getActivity().bindService(intent, serviceConnection, Context.BIND_AUTO_CREATE);
    }

    /**
     * 显示启动
     */
    private void bindServer1() {
        //Activity与Service之间建立关联，需要调用bindService()方法，并将Intent作为参数传递进去，在Intent里指定好要绑定的Service
        //在构建Intent的时候是使用MyService.class来指定要绑定哪一个Service的
        if (null == serviceConnection) {
            serviceConnection = new MyServiceConnection();
        }
        Intent bindIntent = new Intent();
        bindIntent.putExtra("MyService", "bindServer1");
        bindIntent.setClass(getActivity(), MyService.class);
        getActivity().bindService(bindIntent, serviceConnection, Context.BIND_AUTO_CREATE);
    }

    class MyServiceConnection implements ServiceConnection {

        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            Log.e("dongxl", "fragment--ServiceConnection==onServiceConnected==" + name);
            uiServiceAidlInterface = IUiServiceAidlInterface.Stub.asInterface(service);
            try {
                if (null == serviceUiAidlInterface) {
                    serviceUiAidlInterface = new ServiceUiAidlInterface();
                }
                uiServiceAidlInterface.registerCallback(serviceUiAidlInterface);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            Log.e("dongxl", "fragment--ServiceConnection==onServiceConnected==" + name);
            if (null != uiServiceAidlInterface) {
                try {
                    if (null != serviceUiAidlInterface) {
                        uiServiceAidlInterface.unRegisterCallback(serviceUiAidlInterface);
                        serviceUiAidlInterface = null;
                    }
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
                uiServiceAidlInterface = null;
            }
        }

        @Override
        public void onBindingDied(ComponentName name) {
            Log.e("dongxl", "fragment--ServiceConnection==onBindingDied==" + name);
        }

        @Override
        public void onNullBinding(ComponentName name) {
            Log.e("dongxl", "fragment--ServiceConnection==onNullBinding==" + name);
        }
    }

    class ServiceUiAidlInterface extends IServiceUiAidlInterface.Stub {

        @Override
        public void serviceUiTest(TestBean testBean) throws RemoteException {
            Log.e("dongxl", "fragment--TestBean==" + testBean.toString());
        }

        @Override
        public void serviceUiTest2(TestBean2 testBean) throws RemoteException {
            Log.e("dongxl", "fragment--TestBean2==" + testBean.toString());
        }
    }

    /**
     * 接触绑定
     */
    private void unBindServer() {
        try {
            if (null != serviceConnection) {
                // 当需要多次调用doSomething()方法的时候，如果直接bindService是会报错的
                getActivity().unbindService(serviceConnection);
                serviceConnection = null;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void startService() {
        Intent intent = new Intent();
        intent.putExtra("MyService", "startService");
        intent.setClass(getActivity(), MyService.class);
        getActivity().startService(intent);
    }

    private void stopService() {
        Intent intent = new Intent();
        intent.setClass(getActivity(), MyService.class);
        getActivity().stopService(intent);
    }
}

// IUiServiceAidlInterface.aidl
package com.dongxl.activityaidlservice;
import com.dongxl.activityaidlservice.IServiceUiAidlInterface;

// Declare any non-default types here with import statements
//ui调用service中方法
interface IUiServiceAidlInterface {
    void registerCallback(IServiceUiAidlInterface callback);   //用于UI注册IServiceUiAidlInterface
    void unRegisterCallback(IServiceUiAidlInterface callback); //用于UI注销IServiceUiAidlInterface
}

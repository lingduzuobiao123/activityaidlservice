// IUiServiceAidlInterface.aidl
package com.dongxl.activityaidlservice;
import com.dongxl.activityaidlservice.IServiceUiAidlInterface;

// Declare any non-default types here with import statements
//ui调用service中方法
interface IUiServiceAidlInterface {
    /**
     * Demonstrates some basic types that you can use as parameters
     * and return values in AIDL.
     */
    void basicTypes(int anInt, long aLong, boolean aBoolean, float aFloat,
            double aDouble, String aString);


    void registerCallback(IServiceUiAidlInterface callback);   //用于UI注册IServiceUiAidlInterface
    void unRegisterCallback(IServiceUiAidlInterface callback); //用于UI注销IServiceUiAidlInterface
}

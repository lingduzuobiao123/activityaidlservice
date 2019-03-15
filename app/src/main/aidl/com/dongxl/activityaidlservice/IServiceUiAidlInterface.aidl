// IServiceUiAidlInterface.aidl
package com.dongxl.activityaidlservice;
import com.dongxl.activityaidlservice.bean.TestBean;
import com.dongxl.activityaidlservice.bean.TestBean2;
// Declare any non-default types here with import statements
//service调用ui中方法
interface IServiceUiAidlInterface {
    void serviceUiTest(inout TestBean testBean);
    void serviceUiTest2(inout TestBean2 testBean);
}

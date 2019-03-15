# activityserviceaidl
## 描述：activity server 之间相互通信
#### 1. 添加配置信息：
1.module中的build.gradle添加：
(```)
sourceSets {
    main {
        //指定源文件的路径，把aidl包含进去了
        java.srcDirs = ['src/main/java', 'src/main/aidl']
    }
}
(```)
#### 2. activity server结合
2.1. 客户端(activity)和服务端(service)的AIDL接口文件所在的包必须相同
2.2. 需要一个Service类的配合
2.3. manifiest 声明：
(```)
<service android:name=".service.MyService">
    <intent-filter>
        <action android:name="com.dongxl.activityaidlservice.service.MyService" />
    </intent-filter>
</service>
(```)
2.4. 多次调用相同方法：直接unbindService是会报错的。这时候可以在unbind之前加上下面这样的
(```)
try{     unbindService(connection); }catch(Exception e){     e.printTrace(); }
(```)
#### 3. AIDL传递复杂类型对象的特殊处理 
　　前面已经介绍了通过AIDL接口在进程间传递系统允许的数据，如果需要传递一个复杂类型的对象，就没那么简单了，需要额外做一些处理。如下：
	2.1.	定义数据接口的AIDL文件中，使用parcelable关键字，例如：parcelable Message;
	2.2.	在其数据实现类中实现Parcelable接口，并实现对应的方法。
	2.3.	在业务接口的AIDL文件中，使用import引入数据接口AIDL的包名。
	2.4. 传递的对象参数前面需要 in，out，inout三个当中的其中一个修饰符，否则会报错。如 void serviceUiTest(inout TestBean testBean);

2.5. 对象TestBean序列化重写writeToParcel和readFromParcel方法中写数据操作和读取操作顺序必须保持一致，否则会报错。
2.6. 在使用到TestBean对象的地方都需要引入 import com.dongxl.activityaidlservice.bean;包路径。否则也会报错找不到 TestBean类。
	2.7.  例如：
	TestBean.aidl
	parcelable TestBean;
	








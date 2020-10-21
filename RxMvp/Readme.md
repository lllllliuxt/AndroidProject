# MVP + Rxjava + Retrofit

## 功能
点击下载获取天气信息

## api
http://www.weather.com.cn/data/sk/101010100.html

## 文件夹的组成
* utity

 **实体类**，注意实体类的成员变量名要和api接口的名字一样，不然会下载为空
* mvp

mvp架构：View接口定义需要使用的视图层方法，Present整合View和Model，Model层调用Rxjava和Retrofit实现下载，并且将下载内容传递给Presenter层。
* netUtil

ApiStore定义网络方法，RetrofitClient能够提供一个retrofit实例。使用这个实例调用ApiStore的方法能够得到Observable对象

package feicui.edu.xinwen;

import android.app.Application;
import android.content.res.Configuration;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.util.HashMap;

public class MyApplication extends Application {
    /**用来保存整个应用程序的数据*/
    private HashMap<String,Object> allData=new HashMap<>();
    /**存数据 */
    public void addAllData(String key,Object value){
        allData.put(key,value);
    }
    /**取数据*/
    public Object getAllData(String key){
        if(allData.containsKey(key)){
            return allData.get(key);
        }
        return null;
    }
    /**删除一条数据*/
    public void delAllDataBykey(String key){
        if (allData.containsKey(key)){
            allData.remove(key);
        }
    }
    /**删除所有数据*/
    public void delAllData(){
        allData.clear();
    }
    /**单例模式*/
    private static MyApplication sApplication;

    public static MyApplication getInstance(){
        Log.d("杀杀杀", "getInstance: ");
        return sApplication;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        sApplication=this;
        Log.d("错", "onCreate: ");
    }

    /**内存不足的时候*/
    @Override
    public void onLowMemory() {
        super.onLowMemory();
    }
    /**
     * 结束的时候
     */
    @Override
    public void onTerminate() {
        super.onTerminate();
    }

    /**配置改变的时候*/
    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
    }
}

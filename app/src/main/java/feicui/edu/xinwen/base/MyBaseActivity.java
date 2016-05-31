package feicui.edu.xinwen.base;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

/**
 * Created by hp on 2016/5/30.
 * 父类 activity 用来调试打印 activity 生命周期和节目的进入和退出动画
 */
public class MyBaseActivity extends Activity{
    protected int screen_w,screen_h;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        screen_w=getWindowManager().getDefaultDisplay().getWidth();
        screen_h=getWindowManager().getDefaultDisplay().getHeight();
    }

    //通过 class 名字进行界面跳转并带 Bundle， Uri 数据
    public void openActivity(Class<?>pClass, Bundle bundle, Uri uri){
        Intent intent=new Intent(this,pClass);
        if (bundle!=null){
            intent.putExtras(bundle);
        }
        if (uri != null) {
            intent.setData(uri);

        }
        startActivity(intent);
    }

    //通过 class 名字进行界面跳转只带 Bundle 数据
    public void openActivity(Class<?> pClass,Bundle bundle){
        openActivity(pClass,bundle,null);
    }

    //通过 class 名字进行界面跳转
    public void openActivity(Class<?> pClass){
        openActivity(pClass,null,null);
    }

    //通过 action 字符串进行界面跳转
    public void openActivity(String action){
       // openActivity(action,null,null);
    }

    @Override
    protected void onStart() {
        super.onStart();
    }
}

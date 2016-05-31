package feicui.edu.xinwen.activiti;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;

import android.widget.Button;
import android.widget.ImageView;

import java.util.ArrayList;

import feicui.edu.xinwen.R;

/**
 * Created by hp on 2016/5/30.欢迎界面
 *
 */
public class LogoActivity extends Activity implements ViewPager.OnPageChangeListener, View.OnClickListener {
    public static final String MAIN_CONFIG="main_config";
    public static final String IS_FIRST_RUN = "isFirstRun";

    //定义控件
    private ViewPager mViewPager;
    private Button mButton;
    private ArrayList<View> mList;

    int[] pisc={R.mipmap.adware_style_applist,R.mipmap.adware_style_banner,R.mipmap.adware_style_creditswall};

    ImageView icon1,icon2,icon3;
    ImageView[] icons={icon1,icon2,icon3};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

       //   判断是否是第一次运行程序
        SharedPreferences sp=getSharedPreferences(MAIN_CONFIG,MODE_PRIVATE);

        boolean isFirstRun=sp.getBoolean(IS_FIRST_RUN,true);
       if (!isFirstRun){
            Intent intent=new Intent(LogoActivity.this,HomeActivity.class);
           startActivity(intent);
           finish();
        }else {
            setContentView(R.layout.acticity_logo);
            initView();
        }

    }

    private void initView() {
        icons[0]= (ImageView) findViewById(R.id.icon1);
        icons[1]= (ImageView) findViewById(R.id.icon2);
        icons[2]= (ImageView) findViewById(R.id.icon3);
        icons[0].setImageResource(R.mipmap.adware_style_selected);

        mViewPager= (ViewPager) findViewById(R.id.vp);
        mButton= (Button) findViewById(R.id.btn);
        mButton.setOnClickListener(this);

        mList=new ArrayList<>();
       for (int i=0;i<pisc.length;i++){
           ImageView iv=new ImageView(this);
           iv.setImageResource(pisc[i]);
           mList.add(iv);
       }
        mViewPager.setAdapter(new MyPagerAdapter(mList));

        mViewPager.addOnPageChangeListener(this);
        mViewPager.setPageTransformer(true,new ZoomOutPageTransformer());
    }

    private void savePrefrences(){
        SharedPreferences sp=getSharedPreferences(MAIN_CONFIG,MODE_PRIVATE);
        SharedPreferences.Editor se=sp.edit();
        se.putBoolean(IS_FIRST_RUN,false);
        se.apply();
    }




    // 正在滚动的时候 调用的方法 会反复调用
    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        if (position==2){
            mButton.setVisibility(View.VISIBLE);
        }else {
            mButton.setVisibility(View.INVISIBLE);
        }
         for (int i=0;i<icons.length;i++){
             icons[i].setImageResource(R.mipmap.adware_style_default);
         }
        icons[position].setImageResource(R.mipmap.adware_style_selected);
    }

    // 当viewpager在滚动的时候 调用的第一个方法
    @Override
    public void onPageScrollStateChanged(int state) {

    }

    // 按钮的跳转方法
    @Override
    public void onClick(View v) {
        savePrefrences();
        Intent intent=new Intent(LogoActivity.this,HomeActivity.class);
        startActivity(intent);
        finish();
    }

    private class MyPagerAdapter extends PagerAdapter{
        private  ArrayList<View> mList;

        public MyPagerAdapter(ArrayList<View> list){
            mList=list;
        }

        // 初始化position 展现到界面上来
        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            container.addView(mList.get(position),0);
            return mList.get(position);
        }

        //当页面不可见，销毁页面

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView(mList.get(position));
        }

        @Override
        public int getCount() {
            if (mList != null) {
                return mList.size();
            }
            return 0;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view==object;
        }
    }



    // 官方提供的动画2
    public class ZoomOutPageTransformer implements ViewPager.PageTransformer {
        private static final float MIN_SCALE = 0.85f;
        private static final float MIN_ALPHA = 0.5f;

        public void transformPage(View view, float position) {
            int pageWidth = view.getWidth();
            int pageHeight = view.getHeight();
            if (position < -1) { // [-Infinity,-1)
                // This page is way off-screen to the left.
                view.setAlpha(0);

            } else if (position <= 1) { // [-1,1]
                // Modify the default slide transition to shrink the page as well
                float scaleFactor = Math.max(MIN_SCALE, 1 - Math.abs(position));
                float vertMargin = pageHeight * (1 - scaleFactor) / 2;
                float horzMargin = pageWidth * (1 - scaleFactor) / 2;
                if (position < 0) {
                    view.setTranslationX(horzMargin - vertMargin / 2);
                } else {
                    view.setTranslationX(-horzMargin + vertMargin / 2);
                }

                // Scale the page down (between MIN_SCALE and 1)
                view.setScaleX(scaleFactor);
                view.setScaleY(scaleFactor);

                // Fade the page relative to its size.
                view.setAlpha(MIN_ALPHA +
                        (scaleFactor - MIN_SCALE) /
                                (1 - MIN_SCALE) * (1 - MIN_ALPHA));
            } else { // (1,+Infinity]
                // This page is way off-screen to the right.
                view.setAlpha(0);
            }
        }
    }

}

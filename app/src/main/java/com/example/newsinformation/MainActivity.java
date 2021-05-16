package com.example.newsinformation;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.newsinformation.fragment.NewsFragment;
import com.example.newsinformation.adapter.PageAdapter;
import com.example.newsinformation.fragment.PictureFragment;
import com.example.newsinformation.fragment.MyselfFragment;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends FragmentActivity implements View.OnClickListener {
    private List<Fragment> fragments;
    private ViewPager viewPager;
    private LinearLayout first,second,third,forth;
    private ImageButton firstImg,secondImg,thirdImg,forthImg;
    private TextView viewtitle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fragments = new ArrayList<Fragment>();
        initview();
    }

    //初始化
    private void initview() {
        viewtitle = findViewById(R.id.viewtitle);
        //初始化tab
        viewPager = findViewById(R.id.vp_fragment);
        first= findViewById(R.id.first);
        second = findViewById(R.id.second);
        third = findViewById(R.id.third);

        firstImg = findViewById(R.id.img_first);
        secondImg = findViewById(R.id.img_second);
        thirdImg = findViewById(R.id.img_third);

        //初始化fragment数据
        NewsFragment newsFragment = new NewsFragment();
        fragments.add(newsFragment);
        PictureFragment pictureFragment = new PictureFragment();
        fragments.add(pictureFragment);
        MyselfFragment myselfFragment = new MyselfFragment();
        fragments.add(myselfFragment);

        /**
         * 这里Activity 只有继承 FragmentActivity 的时候 ，才会 getSupportFragmentManager()
         */

        // 设置适配器
        PageAdapter pageAdapter = new PageAdapter(getSupportFragmentManager(),fragments);
        viewPager.setAdapter(pageAdapter);
        //给tabs设计点击事件
        first.setOnClickListener(this);
        second.setOnClickListener(this);
        third.setOnClickListener(this);


        //设置ViewPage 切换效果
        viewPager.setOnPageChangeListener(new vpOnChangeListener());
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.first:
                SetTabsSelectedImg(0);
                break;
            case R.id.second:
                SetTabsSelectedImg(1);
                break;
            case R.id.third:
                SetTabsSelectedImg(2);
                break;

            default:
                break;
        }
    }
    /**
     * (1)实现选中后的 tabs的img
     * (2)切换 viewpager item
     * @param i
     */
    private void SetTabsSelectedImg(int i){
        switch (i){
            case 0:
                firstImg.setImageResource(R.drawable.first_on);
                viewtitle.setText(R.string.title1);
                break;
            case 1:
                secondImg.setImageResource(R.drawable.second_on);
                viewtitle.setText(R.string.title2);
                break;
            case 2:
                thirdImg.setImageResource(R.drawable.third_on);
                viewtitle.setText(R.string.title3);
                break;

            default:
                break;
        }
        //切换 viewpage item
        viewPager.setCurrentItem(i);

    }
    /**
     * 将 tabs 的 图片设置 默认颜色
     */
    private void ResetTabsImg() {
        // 重置tab 图片
        firstImg.setImageResource(R.drawable.first_off);
        secondImg.setImageResource(R.drawable.second_off);
        thirdImg.setImageResource(R.drawable.third_off);

    }
    /**
     * 实现滑动 切换 tabs
     * @author yuan
     */
    class vpOnChangeListener extends ViewPager.SimpleOnPageChangeListener{
        @Override
        public void onPageSelected(int position) {
            super.onPageSelected(position);
            //设置 tab 背景
            ResetTabsImg();
            SetTabsSelectedImg(position);
        }
    }
}
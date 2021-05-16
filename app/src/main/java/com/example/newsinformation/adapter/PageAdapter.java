package com.example.newsinformation.adapter;

import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import java.util.List;

public class PageAdapter extends FragmentPagerAdapter {
    private List<Fragment> mfragmentList;
    public PageAdapter(FragmentManager fm, List<Fragment>fragmentList) {
        super(fm);
        this.mfragmentList = fragmentList;
    }

    @Override
    public Fragment getItem(int position) {
        return mfragmentList.get(position);
    }

    @Override
    public int getCount() {
        return mfragmentList.size();
    }
    /**
     * 下面的 instantiteItem和destroyItem两个方法 创建不需要修改
     *
     */
    @Override
    public Object instantiateItem( ViewGroup container, int position) {
        return super.instantiateItem(container, position);
    }

    @Override
    public void destroyItem( ViewGroup container, int position,Object object) {
        super.destroyItem(container, position, object);
    }
}

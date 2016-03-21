package org.geek.universalimageloaderexample.fragment;

import android.support.v4.view.ViewPager;
import android.view.View;

import org.geek.universalimageloaderexample.R;
import org.geek.universalimageloaderexample.adapter.ImageViewpagerAdapter;
import org.geek.universalimageloaderexample.base.BaseFragment;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * File Description  :  Viewpagerfragment
 *
 * @author : zhanggeng
 * @version : v1.0
 *          **************修订历史*************
 * @email : zhanggengdyx@gmail.com
 * @date : 16/3/21 10:54
 */
public class ImageViewpagerFragment extends BaseFragment {

    private View mBaseView;

    @Bind(R.id.pager)
    ViewPager viewPager;

    @Override
    public void initData() {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_viewpager;
    }

    @Override
    public View initView() {

        mBaseView = inflateView(getLayoutId());

        ButterKnife.bind(this, mBaseView);

        ImageViewpagerAdapter imageViewpagerAdapter = new ImageViewpagerAdapter(getActivity());

        viewPager.setAdapter(imageViewpagerAdapter);

        viewPager.setCurrentItem(0);

        return mBaseView;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        ButterKnife.unbind(this);
    }
}

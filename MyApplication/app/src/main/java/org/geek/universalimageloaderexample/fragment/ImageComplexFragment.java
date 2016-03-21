package org.geek.universalimageloaderexample.fragment;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.View;

import org.geek.universalimageloaderexample.R;
import org.geek.universalimageloaderexample.adapter.ImagePagerAdapter;
import org.geek.universalimageloaderexample.base.BaseFragment;
import org.geek.universalimageloaderexample.utils.L;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * File Description  : Complex fragment
 *
 * @author : zhanggeng
 * @version : v1.0
 *          **************修订历史*************
 * @email : zhanggengdyx@gmail.com
 * @date : 16/3/21 17:14
 */
public class ImageComplexFragment extends BaseFragment {

    private static final String STATE_POSITION = "STATE_POSITION";

    View baseView;

    @Bind(R.id.pager)
    ViewPager viewPager;

    private int position = 0;

    @Override
    public void initData() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        position = savedInstanceState == null ? 0 : savedInstanceState.getInt(STATE_POSITION);

        L.et("position", position + "======");
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_complex;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putInt(STATE_POSITION, viewPager.getCurrentItem());
    }

    @Override
    public View initView() {

        baseView = inflateView(getLayoutId());
        ButterKnife.bind(this, baseView);


        ImagePagerAdapter imagePagerAdapter = new ImagePagerAdapter(getFragmentManager());

        viewPager.setAdapter(imagePagerAdapter);

        viewPager.setCurrentItem(position);

        imagePagerAdapter.notifyDataSetChanged();

        L.et("position", position + "======");

        return baseView;
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }
}

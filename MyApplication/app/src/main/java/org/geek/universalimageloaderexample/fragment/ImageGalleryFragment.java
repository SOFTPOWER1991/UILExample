package org.geek.universalimageloaderexample.fragment;

import android.view.View;
import android.widget.Gallery;

import org.geek.universalimageloaderexample.R;
import org.geek.universalimageloaderexample.adapter.ImageGalleryAdapter;
import org.geek.universalimageloaderexample.base.BaseFragment;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * File Description  :  gallery fragment
 *
 * @author : zhanggeng
 * @version : v1.0
 *          **************修订历史*************
 * @email : zhanggengdyx@gmail.com
 * @date : 16/3/21 10:54
 */
public class ImageGalleryFragment extends BaseFragment {

    private View mBaseView;


    @Bind(R.id.gallery)
    Gallery gallery;

    @Override
    public void initData() {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_gallery;
    }

    @Override
    public View initView() {

        mBaseView = inflateView(getLayoutId());

        ButterKnife.bind(this, mBaseView);

        ImageGalleryAdapter imageGalleryAdapter = new ImageGalleryAdapter(getActivity());

        gallery.setAdapter(imageGalleryAdapter);

        return mBaseView;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        ButterKnife.unbind(this);
    }
}

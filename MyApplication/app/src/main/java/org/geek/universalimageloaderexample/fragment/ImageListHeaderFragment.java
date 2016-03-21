package org.geek.universalimageloaderexample.fragment;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import org.geek.universalimageloaderexample.Constants;
import org.geek.universalimageloaderexample.R;
import org.geek.universalimageloaderexample.adapter.ImageListHeaderFragmentAdapter;
import org.geek.universalimageloaderexample.base.BaseFragment;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * File Description  :  圆形头像fragment
 *
 * @author : zhanggeng
 * @version : v1.0
 *          **************修订历史*************
 * @email : zhanggengdyx@gmail.com
 * @date : 16/3/21 10:54
 */
public class ImageListHeaderFragment extends BaseFragment {

    private View mBaseView;

    @Bind(R.id.recycle_list_fragment)
    RecyclerView recyclerView;

    @Override
    public void initData() {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_image_list;
    }

    @Override
    public View initView() {

        mBaseView = inflateView(getLayoutId());

        ButterKnife.bind(this, mBaseView);

        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        ImageListHeaderFragmentAdapter imageListHeaderFragmentAdapter = new ImageListHeaderFragmentAdapter(getActivity(), Constants.IMAGES);

        recyclerView.setAdapter(imageListHeaderFragmentAdapter);

        return mBaseView;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        ButterKnife.unbind(this);
    }
}

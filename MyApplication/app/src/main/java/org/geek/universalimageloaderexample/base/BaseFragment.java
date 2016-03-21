package org.geek.universalimageloaderexample.base;

import android.app.Dialog;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Fragment 基类
 */

public abstract class BaseFragment extends Fragment {

    protected LayoutInflater mInflater;
    private Dialog netDialog;

    private View baseView;

    private final int HANDLER_MSG_TAG_NETDIALOG_SHOW = 1;

    private final int HANDLER_MSG_TAG_NETDIALOG_CANCLE = 2;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    public abstract void initData();


    protected int getLayoutId() {
        return 0;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        this.mInflater = inflater;
        baseView = initView();
        initData();
        return baseView;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    public abstract View initView();

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }


    protected View inflateView(int resId) {
        return this.mInflater.inflate(resId, null);
    }


    public void showNetDialog() {

        Message msg = handler.obtainMessage();
        msg.what = HANDLER_MSG_TAG_NETDIALOG_SHOW;
        handler.sendMessage(msg);

    }

    public void cancelNetDialog() {
        Message msg = handler.obtainMessage();
        msg.what = HANDLER_MSG_TAG_NETDIALOG_CANCLE;
        handler.sendMessage(msg);
    }

    private void initNetDialog() {
    }


    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case HANDLER_MSG_TAG_NETDIALOG_SHOW:
                    if (netDialog == null) {
                        initNetDialog();
                    }
                    if (!netDialog.isShowing()) {
                        netDialog.show();
                    }
                    break;
                case HANDLER_MSG_TAG_NETDIALOG_CANCLE:
                    if (netDialog == null) {
                        return;
                    }
                    if (netDialog.isShowing())
                        netDialog.dismiss();
                    break;
            }
        }
    };
}

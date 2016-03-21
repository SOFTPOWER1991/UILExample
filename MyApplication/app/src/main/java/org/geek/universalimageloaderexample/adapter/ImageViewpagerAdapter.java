package org.geek.universalimageloaderexample.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.listener.SimpleImageLoadingListener;

import org.geek.universalimageloaderexample.Constants;
import org.geek.universalimageloaderexample.ImageLoadProxy;
import org.geek.universalimageloaderexample.R;

/**
 * File Description  : viewpager adapter
 *
 * @author : zhanggeng
 * @version : v1.0
 *          **************修订历史*************
 * @email : zhanggengdyx@gmail.com
 * @date : 16/3/21 16:33
 */
public class ImageViewpagerAdapter extends PagerAdapter {

    private LayoutInflater layoutInflater;
    private Context context;

    public ImageViewpagerAdapter(Context context) {
        this.layoutInflater = LayoutInflater.from(context);
        this.context = context;
    }

    @Override
    public int getCount() {
        return Constants.IMAGES.length;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view.equals(object);
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {

        container.removeView((View) object);
    }

    @Override
    public Object instantiateItem(ViewGroup view, int position) {
        View baseView = layoutInflater.inflate(R.layout.item_pager_image, view, false);

        ImageView imageView = (ImageView) baseView.findViewById(R.id.image);
        final ProgressBar spinner = (ProgressBar) baseView.findViewById(R.id.loading);

        ImageLoader.getInstance().displayImage(Constants.IMAGES[position], imageView, ImageLoadProxy.getOptions4PictureList(), new SimpleImageLoadingListener() {
            @Override
            public void onLoadingStarted(String imageUri, View view) {
                spinner.setVisibility(View.VISIBLE);
            }

            @Override
            public void onLoadingFailed(String imageUri, View view, FailReason failReason) {
                String message = null;
                switch (failReason.getType()) {
                    case IO_ERROR:
                        message = "Input/Output error";
                        break;
                    case DECODING_ERROR:
                        message = "Image can't be decoded";
                        break;
                    case NETWORK_DENIED:
                        message = "Downloads are denied";
                        break;
                    case OUT_OF_MEMORY:
                        message = "Out Of Memory error";
                        break;
                    case UNKNOWN:
                        message = "Unknown error";
                        break;
                }
                Toast.makeText(view.getContext(), message, Toast.LENGTH_SHORT).show();

                spinner.setVisibility(View.GONE);
            }

            @Override
            public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage) {
                spinner.setVisibility(View.GONE);
            }
        });

        view.addView(baseView, 0);

        return baseView;
    }
}

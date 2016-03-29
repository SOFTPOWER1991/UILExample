package org.geek.universalimageloaderexample.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.nostra13.universalimageloader.core.ImageLoader;

import org.geek.universalimageloaderexample.Constants;
import org.geek.universalimageloaderexample.ImageLoadUtils;
import org.geek.universalimageloaderexample.R;

/**
 * File Description  :
 *
 * @author : zhanggeng
 * @version : v1.0
 *          **************修订历史*************
 * @email : zhanggengdyx@gmail.com
 * @date : 16/3/21 16:50
 */
public class ImageGalleryAdapter extends BaseAdapter {

    private LayoutInflater layoutInflater;

    public ImageGalleryAdapter(Context context) {
        this.layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return Constants.IMAGES.length;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {


        ViewHolder holder;

        if (convertView == null){

            convertView = layoutInflater.inflate(R.layout.item_fragment_gallery , parent , false);
            holder = new ViewHolder();
            holder.imageView = (ImageView) convertView.findViewById(R.id.img_gallery);

            convertView.setTag(holder);
        }else {
            holder = (ViewHolder) convertView.getTag();
        }

        ImageLoader.getInstance().displayImage(Constants.IMAGES[position] , holder.imageView , ImageLoadUtils.getOptions4Gallery());


        return convertView;
    }

    class ViewHolder {
        private ImageView imageView;
    }
}

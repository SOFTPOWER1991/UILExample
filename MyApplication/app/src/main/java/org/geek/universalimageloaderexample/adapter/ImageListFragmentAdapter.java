package org.geek.universalimageloaderexample.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.nostra13.universalimageloader.core.ImageLoader;

import org.geek.universalimageloaderexample.ImageLoadUtils;
import org.geek.universalimageloaderexample.R;
import org.geek.universalimageloaderexample.utils.L;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * File Description  : ImageListFragmentAdater
 *
 * @author : zhanggeng
 * @version : v1.0
 *          **************修订历史*************
 * @email : zhanggengdyx@gmail.com
 * @date : 16/3/21 11:30
 */
public class ImageListFragmentAdapter extends RecyclerView.Adapter<ImageListFragmentAdapter.ImageListHolder> {

    private String[] array;

    private Context context;

    private LayoutInflater layoutInflater;


    public ImageListFragmentAdapter(Context context, String[] array) {
        this.array = array;
        this.context = context;
        this.layoutInflater = LayoutInflater.from(context);

    }


    @Override
    public ImageListHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ImageListHolder(layoutInflater.inflate(R.layout.item_fragment_image_list, parent, false));
    }

    @Override
    public void onBindViewHolder(ImageListHolder holder, int position) {

        ImageLoader.getInstance().displayImage(array[position], holder.imageView, ImageLoadUtils.getOptions4PictureList());
    }

    @Override
    public int getItemCount() {
        return array.length;
    }

    public static class ImageListHolder extends RecyclerView.ViewHolder {

        @Bind(R.id.image_fragment_list)
        ImageView imageView;

        public ImageListHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);

            view.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    L.et("image_tag", "image click");
                }
            });

        }
    }
}

package org.geek.universalimageloaderexample.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import org.geek.universalimageloaderexample.fragment.ImageGalleryFragment;
import org.geek.universalimageloaderexample.fragment.ImageListFragment;
import org.geek.universalimageloaderexample.utils.L;

/**
 * File Description  : imagepager
 *
 * @author : zhanggeng
 * @version : v1.0
 *          **************修订历史*************
 * @email : zhanggengdyx@gmail.com
 * @date : 16/3/21 17:23
 */
public class ImagePagerAdapter extends FragmentPagerAdapter {


    public ImagePagerAdapter(FragmentManager fm) {
        super(fm);

    }

    @Override
    public Fragment getItem(int position) {

        L.et("position", position + "======");


        switch (position) {
            case 0:
                return new ImageListFragment();
            case 1:
                return new ImageGalleryFragment();
            default:

                L.et("position", position + "======");
                return null;
        }
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return "list";
            case 1:
                return "grid";
            default:
                return null;
        }
    }
}

package org.geek.universalimageloaderexample;

import android.app.Application;

/**
 * File Description  : 应用程序application
 *
 * @author : zhanggeng
 * @email : zhanggengdyx@gmail.com
 * @date : 16/3/21 10:30
 * @version     : v1.0
 * **************修订历史*************
 */
public class UILExampleApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        ImageLoadUtils.initImageLoader(getApplicationContext());
    }

}

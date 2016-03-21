package org.geek.universalimageloaderexample;

import android.app.Application;
import android.content.Context;
import android.os.Environment;

import com.nostra13.universalimageloader.cache.disc.impl.UnlimitedDiskCache;
import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;

import java.io.File;

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

        initImageLoader(getApplicationContext());
    }

    private void initImageLoader(Context applicationContext) {

        ImageLoaderConfiguration.Builder config = new ImageLoaderConfiguration.Builder(applicationContext);
        config.threadPoolSize(8);
        config.threadPriority(Thread.NORM_PRIORITY);
        config.denyCacheImageMultipleSizesInMemory();
        config.diskCacheFileNameGenerator(new Md5FileNameGenerator());
        config.diskCacheSize(50 * 1024 * 1024); // 50 MiB
        config.tasksProcessingOrder(QueueProcessingType.LIFO);
        config.diskCache(new UnlimitedDiskCache(new File(Environment.getExternalStorageDirectory() + "/UILDemo/cache")));

        if (BuildConfig.DEBUG){
            config.writeDebugLogs();
        }

        ImageLoader.getInstance().init(config.build());

    }
}

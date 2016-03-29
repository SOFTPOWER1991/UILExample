UIL出来也很久了，网上的使用方法大多不全，或者是基于老的版本而来的，有必要做些更新了。

因此，我决定，自己把这个使用方法详细的分享出来，以下内容翻译自
[Android-Universal-Image-Loader wiki](https://github.com/nostra13/Android-Universal-Image-Loader/wiki)。

关于UIL的介绍，不再赘述。参见，Github地址：[Android-Universal-Image-Loader](https://github.com/nostra13/Android-Universal-Image-Loader)

# 快速设置

##添加依赖

* 添加依赖：

 *下载jar包并导入你的工程*
 
[JAR]()

*使用Maven依赖*

```
<dependency>
    <groupId>com.nostra13.universalimageloader</groupId>
    <artifactId>universal-image-loader</artifactId>
    <version>1.9.5</version>
</dependency>
```

*Gradle依赖*

```
compile 'com.nostra13.universalimageloader:universal-image-loader:1.9.5'
```

* 在Manifest中添加权限：

```
<manifest>
    <!-- 添加网络权限，如果需要从网络上下载图片 -->
    <uses-permission android:name="android.permission.INTERNET" />
    <!-- 添加写外部存储权限，如果需要往sd卡上写图片的话 -->
    <uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE" />
    ...
</manifest>
```

* 在Application或者activity中进行配置，来确保在Application初始化的时候，整个App中有一个ImageLoader存在。

注意：这儿感觉大有文章，在稍后章节详细描述。

```
// 创建全局的配置来初始化ImageLoader
        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(this)
            ...
            .build();
        ImageLoader.getInstance().init(config);
        ...

```

#ImageLoaderConfiguration

ImageLoader的配置参数——ImageLoaderConfiguration 对Application来说是全局的，你只能设置一次。

所有关于Configuration builder中的配置选项都是可选的，使用你真实需要的选项来进行配置。

可以如下文档来查看各个选项的默认值：

[ImageLoaderConfiguration.java](https://github.com/nostra13/Android-Universal-Image-Loader/blob/master/library/src/main/java/com/nostra13/universalimageloader/core/ImageLoaderConfiguration.java)

```
//不要将这段代码拷贝进你的工程。这仅仅是所有配置选项使用的范例。
//  查看sample工程来学习如何正确的使用ImageLoader

//这里的路径可以自定义
 File cacheDir = StorageUtils.getCacheDirectory(context);

        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(context)
                // 默认等于你的屏幕尺寸，设备屏幕宽高
                .memoryCacheExtraOptions(480, 800)
                // 在将下载的图片保存到你的sd卡之前会重新计算，压缩。
                // 这个属性不要滥用，只有你在对应的需求时再用，因为他会使你的ImageLoader变的很慢。
                .diskCacheExtraOptions(480, 800, null)
                //用于执行从源获取图片任务的 Executor，为configuration中的 taskExecutor，
                // 如果为null，则会调用DefaultConfigurationFactory.createExecutor(…)根据配置返回一个默认的线程池。
                .taskExecutor(null)
                //用于执行从缓存获取图片任务的 Executor，为configuration中的 taskExecutorForCachedImages，
                // 如果为null，则会调用DefaultConfigurationFactory.createExecutor(…)根据配置返回一个默认的线程池。
                .taskExecutorForCachedImages(null)
                // 表示核心池大小(最大并发数) 默认为3
                .threadPoolSize(3)
                // 线程优先级，默认Thread.NORM_PRIORITY - 2
                .threadPriority(Thread.NORM_PRIORITY - 2)
                // 任务进程的顺序，默认为：FIFO 先进先出
                .tasksProcessingOrder(QueueProcessingType.FIFO)
                //设置内存缓存不允许缓存一张图片的多个尺寸，默认允许。
                .denyCacheImageMultipleSizesInMemory()
                //图片内存缓存
                .memoryCache(new LruMemoryCache(2 * 1024 * 1024))
                //memoryCacheSize 为 0，则设置该内存缓存的最大字节数为 App 最大可用内存的 1/8。
                .memoryCacheSize(2 * 1024 * 1024)
                // 创建最大的内存缓存百分比，默认为 13%
                .memoryCacheSizePercentage(13)
                // 硬盘缓存路径，默认为StorageUtils.getCacheDirectory(context)
                .diskCache(new UnlimitedDiskCache(cacheDir))
                //硬盘缓存大小
                .diskCacheSize(50 * 1024 * 1024)
                //缓存文件数量
                .diskCacheFileCount(100)
                // 硬盘缓存文件名生成器，默认为哈希文件名生成器
                .diskCacheFileNameGenerator(new HashCodeFileNameGenerator())
                // 创建图片下载器，默认是BaseImageDownloader
                .imageDownloader(new BaseImageDownloader(context))
                // 图片解码器，负责将图片输入流InputStream转换为Bitmap对象
                .imageDecoder(new BaseImageDecoder(true))
                // 图片显示的配置项。比如加载前、加载中、加载失败应该显示的占位图片，图片是否需要在磁盘缓存，是否需要在内存缓存等。
                .defaultDisplayImageOptions(DisplayImageOptions.createSimple())
                //是否显示调试log信息
                .writeDebugLogs() 
                .build();

        ImageLoader.getInstance().init(config);
```
以上是官方文档的写法，下面对这写配置进行讲解，并且给出，我认为的比较合理的写法。

我所推荐的写法：

```
ImageLoaderConfiguration.Builder config = new ImageLoaderConfiguration.Builder(context);
		config.threadPriority(Thread.NORM_PRIORITY - 2);
		config.denyCacheImageMultipleSizesInMemory();
		config.diskCacheFileNameGenerator(new Md5FileNameGenerator());
		config.diskCacheSize(50 * 1024 * 1024); // 50 MiB
		config.tasksProcessingOrder(QueueProcessingType.LIFO);
		config.diskCache(new UnlimitedDiskCache(new File(Environment.getExternalStorageDirectory()+"/target")));

		if (BuildConfig.DEBUG){
			config.writeDebugLogs(); // 发布release包时，移除log信息		}

//  用configuration初始化ImageLoader	ImageLoader.getInstance().init(config.build());
```

这里主要是想为你展示：

```
if (BuildConfig.DEBUG){
			config.writeDebugLogs(); // Remove for release app
		}

```

在我们发布release包时，需要屏蔽UIL中的log信息，上述代码可以做到。

#Display Options

Display Options (DisplayImageOptions)对于每一个展示任务都可以使用

`ImageLoader.displayImage(...)`

注意：

如果没有给ImageLoader.displayImage(xxx)这个方法传递Display Options，那么这个方法将会使用默认的Display Options ,

```
ImageLoaderConfiguration.defaultDisplayImageOptions(...)
```

```
        DisplayImageOptions options = new DisplayImageOptions.Builder()
                // 正在加载时显示的占位图
                .showImageOnLoading(R.drawable.ic_stub)
                // URL为空时显示的占位图
                .showImageForEmptyUri(R.drawable.ic_empty)
                // 加载失败时显示的占位图
                .showImageOnFail(R.drawable.ic_error)
                // 在加载前是否重置 view，默认为false
                .resetViewBeforeLoading(false)
                //设置在开始加载前的延迟时间，单位为毫秒，通过 Builder 构建的对象默认为 0
                .delayBeforeLoading(1000)
                // 是否缓存在内存中，通过 Builder 构建的对象默认为 false
                .cacheInMemory(false)
                // 是否缓存在磁盘中，通过 Builder 构建的对象默认为 false。
                .cacheOnDisk(false)
                //缓存在内存之前的处理程序，默认为 null
                .preProcessor(null)
                //缓存在内存之后的处理程序，默认为 null。
                .postProcessor(null)
                //下载器需要的辅助信息。下载时传入ImageDownloader.getStream(String, Object)的对象，方便用户自己扩展，默认为 null。
                .extraForDownloader(null)
                // 是否考虑图片的 EXIF 信息，通过 Builder 构建的对象默认为 false。
                .considerExifParams(false)
                // 图片的缩放类型，通过 Builder 构建的对象默认为IN_SAMPLE_POWER_OF_2
                .imageScaleType(ImageScaleType.IN_SAMPLE_POWER_OF_2)
                // bitmap的质量，默认为ARGB_8888
                .bitmapConfig(Bitmap.Config.ARGB_8888)
                //为 BitmapFactory.Options，用于BitmapFactory.decodeStream(imageStream, null, decodingOptions)得到图片尺寸等信息。
                .decodingOptions(null)
                // 在ImageAware中显示 bitmap 对象的接口。可在实现中对 bitmap 做一些额外处理，比如加圆角、动画效果。
                .displayer(new SimpleBitmapDisplayer())
                // handler 对象，默认为 null
                .handler(new Handler())
                .build();

```

*注意*

> 千万不要把这段代码整体拷贝如你的工程。这仅仅是所有选项的示例。查看实例工程来学习ImageLoader 中 DisplayImageOptions 的正确用法。


#可支持的格式：

`http , file , content, assets , drawable , https`

```
"http://site.com/image.png" // from Web
"file:///mnt/sdcard/image.png" // from SD card
"file:///mnt/sdcard/video.mp4" // from SD card (video thumbnail)
"content://media/external/images/media/13" // from content provider
"content://media/external/video/media/13" // from content provider (video thumbnail)
"assets://image.png" // from assets
"drawable://" + R.drawable.img // from drawables (non-9patch images)
"https://www.eff.org/sites/default/files/chrome150_0.jpg",//Image from HTTPS
```

注意: 
如果你真的需要使用drawable:// ，最好还是用Native加载drawable方式，ImageView.setImageResource(....)而不是使用ImageLoader.



#Library Map

[Library Map](https://github.com/nostra13/Android-Universal-Image-Loader/wiki/Library-Map)



#Useful Info

[Useful Info](https://github.com/nostra13/Android-Universal-Image-Loader/wiki/Useful-Info)

#User Support

[User Support](https://github.com/nostra13/Android-Universal-Image-Loader/wiki/User-Support)


上述只是，对官方Wiki的一个简单整理，对于UIL的使用实例，我将在Demo中给出！


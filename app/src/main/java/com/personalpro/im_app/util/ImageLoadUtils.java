package com.personalpro.im_app.util;

import android.content.Context;
import android.widget.ImageView;

import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.nostra13.universalimageloader.core.display.BitmapDisplayer;
import com.nostra13.universalimageloader.core.display.RoundedBitmapDisplayer;
import com.nostra13.universalimageloader.core.display.SimpleBitmapDisplayer;
import com.nostra13.universalimageloader.core.download.BaseImageDownloader;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;
import com.personalpro.im_app.Constant;
import com.personalpro.im_app.IMApplication;
import com.personalpro.im_app.R;

import java.io.IOException;
import java.net.HttpURLConnection;

public enum ImageLoadUtils {
    INSTANCE;
    /**
     * 标准配置
     */
    private DisplayImageOptions normalOptions;
    /**
     * 圆形配置
     */
    private DisplayImageOptions circleOptions;
    /**
     * 圆角图片配置
     */
    private DisplayImageOptions roundedOptions;


    private BitmapDisplayer simpleBitmapDisplay;
    private BitmapDisplayer roundedBitmapDisplay;

    private int onLoadingImageResId;
    private int onEmptyImageResId;
    private int onFailedImageResId;

    /**
     * 构造方法 参数初始化 单例形式 只会初始化一次 避免不必要的资源开支
     */
    ImageLoadUtils() {
        //初始化 全局默认图片
        onLoadingImageResId = R.drawable.ic_launcher_background;//正在加载中的图片资源
        onEmptyImageResId = R.drawable.ic_launcher_background;//在图片为空时的图片资源
        onFailedImageResId = R.drawable.ic_launcher_background;//图片加载失败时的图片资源

        simpleBitmapDisplay = new SimpleBitmapDisplayer();
        normalOptions = getOption(onLoadingImageResId, onEmptyImageResId, onFailedImageResId, simpleBitmapDisplay);

        //圆角图片 圆角半径dp
        int cornerRadiusDp = 8;
        //圆角大小通过 dp2px转换 使得 不同分辨率设备上呈现一致显示效果
        roundedBitmapDisplay = new RoundedBitmapDisplayer(dip2px(IMApplication.getContext(),cornerRadiusDp));
        roundedOptions = getOption(onLoadingImageResId, onEmptyImageResId, onFailedImageResId, roundedBitmapDisplay);

    }

    /**
     * 重构 抽取出的通用生成Option方法
     * @param onLoadingImageResId
     * @param onEmptyImageResId
     * @param onFailedImageResId
     * @param bitmapDisplayer normal 或圆形、圆角 bitmapDisplayer
     *
     * @return
     */
    private DisplayImageOptions getOption(int onLoadingImageResId, int onEmptyImageResId, int onFailedImageResId, BitmapDisplayer bitmapDisplayer) {

        return new DisplayImageOptions.Builder()
//                .showImageOnLoading(onLoadingImageResId)
//                .showImageForEmptyUri(onEmptyImageResId)
//                .showImageOnFail(onFailedImageResId)
                .cacheInMemory(true)
                .cacheOnDisk(true)
                .considerExifParams(true)
                .displayer(bitmapDisplayer)
                .build();
    }


    public void loadImageView(ImageView iv, String url) {
        ImageLoader.getInstance().displayImage(url, iv, normalOptions);
    }

    public void loadCircleImageView(ImageView iv, String url) {
        ImageLoader.getInstance().displayImage(url, iv, circleOptions);
    }

    public void loadRoundedImageView(ImageView iv, String url) {
        ImageLoader.getInstance().displayImage(url, iv, roundedOptions);
    }

    public void displayImage(String uri, ImageView imageView, ImageLoadingListener listener) {
        ImageLoader.getInstance().displayImage(uri, imageView, listener);
    }

    public void displayImage(String uri, ImageView imageView) {
        ImageLoader.getInstance().displayImage(uri, imageView);
    }


    /**
     * dip px 转换工具类 将圆角进行转换 以实现不同分辨率设备上呈现相同效果
     * @param context
     * @param dpValue
     * @return
     */
    public static int dip2px(Context context, float dpValue) {
        final float density = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * density + 0.5f);
    }

    /**
     * 初始化方法
     * @param context
     */
    public void init(Context context) {
        ImageLoaderConfiguration.Builder config = new ImageLoaderConfiguration.Builder(context);
        config.threadPriority(Thread.NORM_PRIORITY - 2);
        config.denyCacheImageMultipleSizesInMemory();
        config.diskCacheFileNameGenerator(new Md5FileNameGenerator());
        config.diskCacheSize(80 * 1024 * 1024); // 80 MiB
        config.tasksProcessingOrder(QueueProcessingType.LIFO);
        config.imageDownloader(new AuthDownloader(IMApplication.getContext()));
        ImageLoader.getInstance().init(config.build());
    }

    public class AuthDownloader extends BaseImageDownloader{
        public AuthDownloader(Context context) {
            super(context);
        }

        @Override
        protected HttpURLConnection createConnection(String url, Object extra) throws IOException {
            String sessionId = SharedPrefUtil.getInstance(IMApplication.getContext()).getValue(Constant.SESSION_KEY,String.class);
            HttpURLConnection conn = super.createConnection(url, extra);
            conn.setRequestProperty("Cookie", "JSESSIONID="+sessionId);
            return conn;
        }

    }

}
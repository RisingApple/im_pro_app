package com.personalpro.im_app.adapter.viewdapter;

import android.util.Log;
import android.widget.ImageView;

import androidx.databinding.BindingAdapter;

import com.personalpro.im_app.Constant;
import com.personalpro.im_app.util.ImageLoadUtils;

public class ViewAdapter {

    @BindingAdapter("img")
    public static void setImage(ImageView view,String url){
        if(url==null||url.length()==0) return;
        if(!url.contains(".jpg")){
            url = url+".jpg";
        }
        url = Constant.BASE_URL+"/" + url;
        Log.i("url",url);
        ImageLoadUtils.INSTANCE.loadRoundedImageView(view,url);
    }

}

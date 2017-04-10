package ru.romanblack.mapdemo.util;

import android.content.Context;
import android.databinding.BindingAdapter;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import ru.romanblack.mapdemo.R;

public final class CustomDataBinder {

    private CustomDataBinder() {}

    @BindingAdapter("imageUrl")
    public static void setImageUrl(ImageView imageView, String url) {
        Context context = imageView.getContext();

        Glide.with(context)
                .load(url)
                .centerCrop()
                .placeholder(R.drawable.ic_photo_camera_black_72dp)
                .into(imageView);
    }
}

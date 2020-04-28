package com.personalpro.im_app.adapter;

import android.graphics.Rect;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

/**
 * RecycleView分割线
 *
 * @author Administrator
 */
public class RecycleViewItemDecoration extends RecyclerView.ItemDecoration {

    private int offset;

    public RecycleViewItemDecoration() {
        this(30);
    }

    public RecycleViewItemDecoration(int offset) {
        this.offset = offset;
    }

    @Override
    public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        outRect.set(0, 0, 0, offset);
    }
}

package com.patriciadevitasamara.uas_pyb.utils;

import android.graphics.Rect;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class SpacesItemDecorations extends RecyclerView.ItemDecoration {
    private final int space;

    public SpacesItemDecorations(int dimensionPixelSize) {
        this.space = dimensionPixelSize;
    }

    public void getItemOffsets(@NonNull Rect outRect, @NonNull View view,
                               @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        outRect.right = space;
        outRect.left = space;
        outRect.bottom = space;

        // Add top margin only for the first item to avoid double space between items
        if (parent.getChildLayoutPosition(view) == 0) {
            outRect.top = space;
        } else {
            outRect.top = 0;
        }
    }
}

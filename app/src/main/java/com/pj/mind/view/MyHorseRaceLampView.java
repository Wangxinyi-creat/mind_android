package com.pj.mind.view;


import android.content.Context;
import android.util.AttributeSet;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class MyHorseRaceLampView extends androidx.appcompat.widget.AppCompatTextView {
    public MyHorseRaceLampView(@NonNull Context context) {
        super(context);
    }

    public MyHorseRaceLampView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public MyHorseRaceLampView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean isFocused() {
        return true;
    }
}




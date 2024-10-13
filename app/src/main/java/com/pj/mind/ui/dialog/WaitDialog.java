package com.pj.mind.ui.dialog;

import android.content.Context;

import com.hjq.base.BaseDialog;
import com.pj.mind.R;

public final class WaitDialog {

    public static final class Builder
            extends BaseDialog.Builder<Builder> {

        public Builder(Context context) {
            super(context);
            setContentView(R.layout.wait_dialog);
            setAnimStyle(BaseDialog.ANIM_TOAST);
            setBackgroundDimEnabled(false);
            setCancelable(false);
        }
    }
}

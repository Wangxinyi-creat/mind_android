package com.pj.mind.ui.dialog.popupView;

import android.content.Context;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.hjq.toast.Toaster;
import com.lxj.xpopup.core.BottomPopupView;
import com.lxj.xpopup.util.XPopupUtils;
import com.pj.mind.R;
import com.pj.mind.utils.NullUtil;


public class TieziPopup extends BottomPopupView {
    private ImageView iv_close;
    private TextView tv_fapinglun;
    private EditText editText,et_title;
    private CheckBox checkbox;
    private OnConfirmListener onConfirmListener;


    public TieziPopup(@NonNull Context context, OnConfirmListener onConfirmListener) {
        super(context);
        this.onConfirmListener = onConfirmListener;
    }

    @Override
    protected int getImplLayoutId() {
        return R.layout.dialog_tiezi;
    }

    @Override
    protected void onCreate() {
        super.onCreate();
        initView();
        initListener();
    }

    private void initView() {
        iv_close = findViewById(R.id.iv_close);
        tv_fapinglun = findViewById(R.id.tv_fapinglun);
        editText = findViewById(R.id.editText);
        checkbox = findViewById(R.id.checkbox);
        et_title = findViewById(R.id.et_title);
    }

    private void initListener() {
        iv_close.setOnClickListener(v -> dismiss());
        tv_fapinglun.setOnClickListener(v -> {
            String text = editText.getText().toString().trim();
            String title = et_title.getText().toString().trim();
            if (NullUtil.isStringEmpty(text)) {
                Toaster.show("清输入评论内容");
                return;
            }
            if (onConfirmListener != null) {
                dismiss();
                onConfirmListener.onConfirm(title, text, checkbox.isChecked() ? 1 : 0);
            }
        });
    }

    @Override
    protected int getMaxHeight() {
        return (int) (XPopupUtils.getScreenHeight(getContext()) * .5f);
    }

    public interface OnConfirmListener {
        void onConfirm(String title, String text, int anonymous);
    }
}
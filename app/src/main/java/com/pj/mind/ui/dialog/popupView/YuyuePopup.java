package com.pj.mind.ui.dialog.popupView;

import android.content.Context;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.hjq.toast.Toaster;
import com.lxj.xpopup.core.CenterPopupView;
import com.lxj.xpopup.util.XPopupUtils;
import com.pj.mind.R;
import com.pj.mind.utils.NullUtil;


public class YuyuePopup extends CenterPopupView {
    private EditText et_name, et_xueyuan, et_banji, et_phone;
    private TextView tv_ok, close;
    private OnConfirmListener onConfirmListener;


    public YuyuePopup(@NonNull Context context, OnConfirmListener onConfirmListener) {
        super(context);
        this.onConfirmListener = onConfirmListener;
    }

    @Override
    protected int getImplLayoutId() {
        return R.layout.dialog_yuyue;
    }

    @Override
    protected void onCreate() {
        super.onCreate();
        initView();
        initListener();
    }

    private void initView() {
        et_name = findViewById(R.id.et_name);
        et_xueyuan = findViewById(R.id.et_xueyuan);
        et_banji = findViewById(R.id.et_banji);
        et_phone = findViewById(R.id.et_phone);
        tv_ok = findViewById(R.id.tv_ok);
        close = findViewById(R.id.close);
    }

    private void initListener() {
        close.setOnClickListener(v -> dismiss());
        tv_ok.setOnClickListener(v -> {
            if (NullUtil.isTextEmpty(et_name)) {
                Toaster.show("请输入姓名");
                return;
            }
            if (NullUtil.isTextEmpty(et_xueyuan)) {
                Toaster.show("请输入学院");
                return;
            }
            if (NullUtil.isTextEmpty(et_banji)) {
                Toaster.show("请输入班级");
                return;
            }
            if (NullUtil.isTextEmpty(et_phone)) {
                Toaster.show("请输入手机号");
                return;
            }
            if (onConfirmListener != null) {
                dismiss();
                String name = et_name.getText().toString().trim();
                String college = et_xueyuan.getText().toString().trim();
                String collegeClass = et_banji.getText().toString().trim();
                String phone = et_phone.getText().toString().trim();
                onConfirmListener.onConfirm(new FormData(name, college, collegeClass, phone));
            }
        });
    }

    @Override
    protected int getMaxHeight() {
        return (int) (XPopupUtils.getScreenHeight(getContext()) * .5f);
    }

    public interface OnConfirmListener {
        void onConfirm(FormData data);
    }

    public static class FormData {
        private String name;
        private String college;
        private String collegeClass;
        private String phone;

        public FormData(String name, String college, String collegeClass, String phone) {
            this.name = name;
            this.college = college;
            this.collegeClass = collegeClass;
            this.phone = phone;
        }

        public String getName() {
            return name;
        }

        public String getCollege() {
            return college;
        }

        public String getCollegeClass() {
            return collegeClass;
        }

        public String getPhone() {
            return phone;
        }
    }
}
package com.pj.mind.ui.adapter;

import android.content.Context;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.hjq.base.BaseAdapter;
import com.pj.mind.AppContract;
import com.pj.mind.R;
import com.pj.mind.app.AppAdapter;
import com.pj.mind.http.api.main.MainApi;
import com.pj.mind.http.api.mind.CounselorApi;
import com.pj.mind.utils.GlideLoadUtils;
import com.pj.mind.utils.GlideUtils;
import com.pj.mind.utils.ToolUtils;


public class ZixunshiAdapter extends AppAdapter<CounselorApi.CounselorBean> {

    public ZixunshiAdapter(@NonNull Context context) {
        super(context);
    }

    @NonNull
    @Override
    public BaseAdapter<?>.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder();
    }


    private final class ViewHolder extends AppAdapter<?>.ViewHolder {

        private final ImageView iv_image;
        private final TextView tv_xueli, tv_shanchang, tv_yuyue1, tv_yuyue2, tv_name;

        private ViewHolder() {
            super(R.layout.item_zixunshi);
            iv_image = findViewById(R.id.iv_image);
            tv_xueli = findViewById(R.id.tv_xueli);
            tv_shanchang = findViewById(R.id.tv_shanchang);
            tv_yuyue1 = findViewById(R.id.tv_yuyue1);
            tv_yuyue2 = findViewById(R.id.tv_yuyue2);
            tv_name = findViewById(R.id.tv_name);
        }

        @Override
        public void onBindView(int position) {
            CounselorApi.CounselorBean bean = getItem(position);
            tv_name.setText(bean.getCounselorName());
            tv_xueli.setText(bean.getEducation());
            tv_shanchang.setText(bean.getSpeciality());
            tv_yuyue1.setText(ToolUtils.getWeek(bean.getAvailableWeek()));
            tv_yuyue2.setText(bean.getAvailableTime1() + " " + bean.getAvailableTime2());
            GlideLoadUtils.getInstance().glideLoadCornerImage(getContext(), AppContract.BASE_URL + bean.getPicUrl(), iv_image, R.mipmap.bg_login, 6);
        }
    }
}

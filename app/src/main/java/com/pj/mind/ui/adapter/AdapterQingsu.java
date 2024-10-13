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
import com.pj.mind.http.api.mind.ActivityApi;
import com.pj.mind.http.api.mind.CounselorRecordListApi;
import com.pj.mind.utils.GlideLoadUtils;


public class AdapterQingsu extends AppAdapter<CounselorRecordListApi.CounselorRecordBean> {

    public AdapterQingsu(@NonNull Context context) {
        super(context);
    }

    @NonNull
    @Override
    public BaseAdapter<?>.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder();
    }


    private final class ViewHolder extends AppAdapter<?>.ViewHolder {
        private TextView tv_name, status, tv_xueli, tv_shanchang;
        private ImageView iv_image;

        private ViewHolder() {
            super(R.layout.item_qingsu);
            tv_name = findViewById(R.id.tv_name);
            status = findViewById(R.id.status);
            tv_xueli = findViewById(R.id.tv_xueli);
            tv_shanchang = findViewById(R.id.tv_shanchang);
            iv_image = findViewById(R.id.iv_image);
        }

        @Override
        public void onBindView(int position) {
            CounselorRecordListApi.CounselorRecordBean bean = getItem(position);
            tv_name.setText(bean.getMindCounselor().getCounselorName());
            tv_xueli.setText(bean.getMindCounselor().getEducation());
            tv_shanchang.setText(bean.getMindCounselor().getSpeciality());
            GlideLoadUtils.getInstance().glideLoadCornerImage(getContext(), AppContract.BASE_URL + bean.getMindCounselor().getPicUrl(), iv_image, R.mipmap.bg_login, 6);

            switch (bean.getAppointmentStatus()) {
                case 0:
                    status.setText("待开始");
                    break;
                case 1:
                    status.setText("进行中");
                    break;
                case 2:
                    status.setText("已结束");
                    break;
            }

        }
    }
}

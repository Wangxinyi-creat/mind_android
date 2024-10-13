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


public class YueyueHAdapter extends AppAdapter<CounselorApi.CounselorBean> {

    public YueyueHAdapter(@NonNull Context context) {
        super(context);
    }

    @NonNull
    @Override
    public BaseAdapter<?>.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder();
    }


    private final class ViewHolder extends AppAdapter<?>.ViewHolder {

        private final ImageView iv_touxiang;
        private final TextView tv_name,tv_time1,tv_time2;

        private ViewHolder() {
            super(R.layout.item_yuyue_h);
            iv_touxiang = findViewById(R.id.iv_touxiang);
//            tv_man = findViewById(R.id.tv_man);
            tv_name = findViewById(R.id.tv_name);
            tv_time1 = findViewById(R.id.tv_time1);
            tv_time2 = findViewById(R.id.tv_time2);
        }

        @Override
        public void onBindView(int position) {
            CounselorApi.CounselorBean bean = getItem(position);
//            tv_title.setText(payItemBean.getHealthyLevelSpareGoal());
            tv_time1.setText("上午"+bean.getAvailableTime1());
            tv_time2.setText("下午"+bean.getAvailableTime2());
            tv_name.setText(bean.getCounselorName());
            GlideLoadUtils.getInstance().glideLoadCornerImage(getContext(), AppContract.BASE_URL + bean.getPicUrl(), iv_touxiang, R.mipmap.bg_login, 6);

        }
    }
}

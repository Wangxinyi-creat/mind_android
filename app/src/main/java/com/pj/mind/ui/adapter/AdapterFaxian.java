package com.pj.mind.ui.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.hjq.base.BaseAdapter;
import com.pj.mind.R;
import com.pj.mind.app.AppAdapter;
import com.pj.mind.http.api.main.MainApi;
import com.pj.mind.http.api.mind.ActivityApi;


public class AdapterFaxian extends AppAdapter<ActivityApi.ActivityBean> {

    public AdapterFaxian(@NonNull Context context) {
        super(context);
    }

    @NonNull
    @Override
    public BaseAdapter<?>.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder();
    }


    private final class ViewHolder extends AppAdapter<?>.ViewHolder {
        private TextView title, details, status, tv_time, tv_loc;

        private ViewHolder() {
            super(R.layout.item_faxian);
            details = findViewById(R.id.details);
            title = findViewById(R.id.title);
            status = findViewById(R.id.status);
            tv_time = findViewById(R.id.tv_time);
            tv_loc = findViewById(R.id.tv_loc);
        }

        @Override
        public void onBindView(int position) {
            ActivityApi.ActivityBean bean = getItem(position);
            title.setText(bean.getActivityName());
            details.setText(bean.getDetails());
            tv_time.setText(bean.getActivityTime());
            tv_loc.setText(bean.getLocation());
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

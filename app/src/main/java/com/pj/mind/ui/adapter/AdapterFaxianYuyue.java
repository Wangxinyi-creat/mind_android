package com.pj.mind.ui.adapter;

import android.content.Context;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.hjq.base.BaseAdapter;
import com.pj.mind.R;
import com.pj.mind.app.AppAdapter;
import com.pj.mind.http.api.mind.ActivityYuyueListApi;
import com.pj.mind.utils.ToolUtils;


public class AdapterFaxianYuyue extends AppAdapter<ActivityYuyueListApi.ActivityYuyueBean> {

    public AdapterFaxianYuyue(@NonNull Context context) {
        super(context);
    }

    @NonNull
    @Override
    public BaseAdapter<?>.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder();
    }


    private final class ViewHolder extends AppAdapter<?>.ViewHolder {
        private TextView tv_time, tv_loc, tv_title;

        private ViewHolder() {
            super(R.layout.item_faxian_yuyue);
            tv_time = findViewById(R.id.tv_time);
            tv_title = findViewById(R.id.tv_title);
            tv_loc = findViewById(R.id.tv_loc);
        }

        @Override
        public void onBindView(int position) {
            ActivityYuyueListApi.ActivityYuyueBean bean = getItem(position);
            String time = bean.getMindActivity().getActivityTime();
            tv_time.setText(ToolUtils.getDateNoYear(time));
            tv_title.setText(bean.getMindActivity().getActivityName());
            tv_loc.setText(bean.getMindActivity().getLocation());
        }
    }
}

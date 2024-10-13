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
import com.pj.mind.utils.ToolUtils;


public class AdapterFaxianTop extends AppAdapter<ActivityApi.ActivityBean> {

    public AdapterFaxianTop(@NonNull Context context) {
        super(context);
    }

    @NonNull
    @Override
    public BaseAdapter<?>.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder();
    }


    private final class ViewHolder extends AppAdapter<?>.ViewHolder {
        private LinearLayout ll_bg, ll_loc;
        private TextView tv_time, tv_loc1, tv_time2, tv_title,num;
        private ImageView iv_bg;

        private ViewHolder() {
            super(R.layout.item_faxian_top);
            tv_time = findViewById(R.id.tv_time);
            tv_loc1 = findViewById(R.id.tv_loc1);
            tv_time2 = findViewById(R.id.tv_time2);
            tv_title = findViewById(R.id.tv_title);
            iv_bg = findViewById(R.id.iv_bg);
            ll_bg = findViewById(R.id.ll_bg);
            ll_loc = findViewById(R.id.ll_loc);
            num = findViewById(R.id.num);
        }

        @Override
        public void onBindView(int position) {
            int type = position % 3;

            switch (type) {
                case 0:
                    ll_bg.setBackground(getDrawable(R.drawable.bg_gi_faxian_top1));
                    tv_time.setBackgroundColor(Color.WHITE);
                    ll_loc.setBackgroundColor(Color.BLACK);
                    tv_title.setTextColor(Color.WHITE);
                    iv_bg.setBackground(getDrawable(R.mipmap.bg_faxian_top1));
                    tv_loc1.setTextColor(Color.WHITE);
                    break;
                case 1:
                    ll_bg.setBackground(getDrawable(R.drawable.bg_gi_faxian_top2));
                    tv_time.setBackgroundResource(R.drawable.bg_gi_b4fdff);
                    ll_loc.setBackgroundColor(Color.parseColor("#EFEFEF"));
                    tv_title.setTextColor(Color.BLACK);
                    iv_bg.setBackground(getDrawable(R.mipmap.bg_faxian_top2));
                    tv_loc1.setTextColor(Color.BLACK);
                    break;
                case 2:
                    ll_bg.setBackground(getDrawable(R.drawable.bg_gi_faxian_top3));
                    tv_time.setBackgroundResource(R.drawable.bg_gi_b886f8);
                    ll_loc.setBackgroundColor(Color.BLACK);
                    tv_title.setTextColor(Color.BLACK);
                    iv_bg.setBackground(getDrawable(R.mipmap.bg_faxian_top3));
                    tv_loc1.setTextColor(Color.WHITE);
                    break;
            }
            ActivityApi.ActivityBean bean = getItem(position);
            String time = bean.getActivityTime();
            tv_time.setText(ToolUtils.getDate(time));
            tv_time2.setText(ToolUtils.getTime(time));
            tv_loc1.setText(bean.getLocation());
            tv_title.setText(bean.getActivityName());
            num.setText(bean.getCapacityNow()+"/"+bean.getCapacity());
        }
    }

}

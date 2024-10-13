package com.pj.mind.ui.adapter;

import android.content.Context;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.hjq.base.BaseAdapter;
import com.pj.mind.R;
import com.pj.mind.app.AppAdapter;
import com.pj.mind.http.api.main.MainApi;


public class YueyueItemHAdapter extends AppAdapter<MainApi.MainBean> {

    public YueyueItemHAdapter(@NonNull Context context) {
        super(context);
    }

    @NonNull
    @Override
    public BaseAdapter<?>.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder();
    }


    private final class ViewHolder extends AppAdapter<?>.ViewHolder {


        private ViewHolder() {
            super(R.layout.item_yuyue_item_h);

        }

        @Override
        public void onBindView(int position) {
            MainApi.MainBean payItemBean = getItem(position);
//            tv_title.setText(payItemBean.getHealthyLevelSpareGoal());
        }
    }
}

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
import com.pj.mind.http.api.mind.CommentListApi;
import com.pj.mind.utils.GlideLoadUtils;
import com.pj.mind.utils.NullUtil;


public class AdapterPinglun extends AppAdapter<CommentListApi.CommentBean> {

    public AdapterPinglun(@NonNull Context context) {
        super(context);
    }

    @NonNull
    @Override
    public BaseAdapter<?>.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder();
    }


    private final class ViewHolder extends AppAdapter<?>.ViewHolder {

        private final TextView tv_title, tv_time, tv_content;
        private final ImageView iv_image;

        private ViewHolder() {
            super(R.layout.item_pinglun);
            tv_title = findViewById(R.id.tv_title);
            iv_image = findViewById(R.id.iv_image);
            tv_time = findViewById(R.id.tv_time);
            tv_content = findViewById(R.id.tv_content);
        }

        @Override
        public void onBindView(int position) {
            CommentListApi.CommentBean bean = getItem(position);
            tv_title.setText(bean.getUser().getNickName());
            tv_time.setText(bean.getCreateTime());
            if (!NullUtil.isStringEmpty(bean.getUser().getAvatar())){
                GlideLoadUtils.getInstance().glideLoadNormal(getContext(), AppContract.BASE_URL + bean.getUser().getAvatar(), iv_image, R.mipmap.bg_login);
            }
            tv_content.setText(bean.getContent());
//            tv_title.setText(payItemBean.getHealthyLevelSpareGoal());
        }
    }
}

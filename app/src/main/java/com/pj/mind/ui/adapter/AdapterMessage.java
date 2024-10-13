package com.pj.mind.ui.adapter;

import android.content.Context;
import android.media.Image;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.hjq.base.BaseAdapter;
import com.pj.mind.AppContract;
import com.pj.mind.R;
import com.pj.mind.app.AppAdapter;
import com.pj.mind.http.api.mind.CommentListApi;
import com.pj.mind.http.api.mind.MessageListApi;
import com.pj.mind.utils.GlideLoadUtils;
import com.pj.mind.utils.NullUtil;
import com.pj.mind.utils.PreferencesUtils;


public class AdapterMessage extends AppAdapter<MessageListApi.MessageBean> {

    public AdapterMessage(@NonNull Context context) {
        super(context);
    }

    @NonNull
    @Override
    public BaseAdapter<?>.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder();
    }


    private final class ViewHolder extends AppAdapter<?>.ViewHolder {

        private LinearLayout ll_left, ll_right;
        private TextView left_text, right_text;
        private ImageView touxiang,touxiang2;

        private ViewHolder() {
            super(R.layout.item_message);
            ll_left = findViewById(R.id.ll_left);
            ll_right = findViewById(R.id.ll_right);
            left_text = findViewById(R.id.left_text);
            right_text = findViewById(R.id.right_text);
            touxiang = findViewById(R.id.touxiang);
            touxiang2 = findViewById(R.id.touxiang2);
        }

        @Override
        public void onBindView(int position) {

            MessageListApi.MessageBean bean = getItem(position);

            if (bean.getSenderId() == PreferencesUtils.getInstance().get("userId", 0)) {
                GlideLoadUtils.getInstance().glideLoadNormal(getContext(), AppContract.BASE_URL + bean.getUrl(), touxiang2, R.mipmap.bg_login);

                ll_left.setVisibility(View.GONE);
                ll_right.setVisibility(View.VISIBLE);
                right_text.setText(bean.getMessageContent());
            } else {
                GlideLoadUtils.getInstance().glideLoadNormal(getContext(), AppContract.BASE_URL + bean.getUrl(), touxiang, R.mipmap.bg_login);
                ll_left.setVisibility(View.VISIBLE);
                ll_right.setVisibility(View.GONE);
                left_text.setText(bean.getMessageContent());
            }
        }
    }
}

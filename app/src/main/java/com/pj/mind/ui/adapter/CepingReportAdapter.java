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
import com.pj.mind.http.api.mind.AssessmentListApi;
import com.pj.mind.http.api.mind.AssessmentReportListApi;
import com.pj.mind.utils.GlideLoadUtils;


public class CepingReportAdapter extends AppAdapter<AssessmentReportListApi.AssessmentReportBean> {

    public CepingReportAdapter(@NonNull Context context) {
        super(context);
    }

    @NonNull
    @Override
    public BaseAdapter<?>.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder();
    }


    private final class ViewHolder extends AppAdapter<?>.ViewHolder {

        private final ImageView iv_image;
        private final TextView title, tv_result;

        private ViewHolder() {
            super(R.layout.item_report);
            iv_image = findViewById(R.id.iv_image);
            title = findViewById(R.id.title);
            tv_result = findViewById(R.id.tv_result);

        }

        @Override
        public void onBindView(int position) {
            AssessmentReportListApi.AssessmentReportBean bean = getItem(position);
            title.setText(bean.getAssessmentName());
            tv_result.setText("测试结果:" + bean.getResult());
            GlideLoadUtils.getInstance().glideLoadCornerImage(getContext(), AppContract.BASE_URL + bean.getPicUrl(), iv_image, R.mipmap.bg_login, 6);
        }
    }
}

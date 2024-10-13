package com.pj.mind.ui.activity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.widget.AppCompatSeekBar;

import com.google.gson.reflect.TypeToken;
import com.hjq.base.action.ClickAction;
import com.hjq.gson.factory.GsonFactory;
import com.hjq.http.EasyHttp;
import com.hjq.http.listener.HttpCallbackProxy;
import com.hjq.toast.Toaster;
import com.luck.picture.lib.tools.ValueOf;
import com.lxj.xpopup.XPopup;
import com.lxj.xpopup.interfaces.OnConfirmListener;
import com.pj.mind.R;
import com.pj.mind.app.AppActivity;
import com.pj.mind.bean.CepingBean;
import com.pj.mind.http.Model.HttpData;
import com.pj.mind.http.api.mind.AssessmentReportApi;
import com.pj.mind.http.api.mind.InboxApi;
import com.pj.mind.utils.PreferencesUtils;
import com.pj.mind.utils.ToolUtils;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class CepingDetailActivity extends AppActivity implements ClickAction {

    private static final String INTENT_KEY_IN_ASSESSMENT_ID = "assessmentId";
    private static final String INTENT_KEY_IN_ASSESSMENT_NAME = "assessmentName";


    private TextView tv_step, tv_step_all, tv_title;
    private List<CepingBean> list = new ArrayList<>();
    private int currentItem = 0;
    private AppCompatSeekBar seekBar;

    public static void start(Context context, int assessmentId, String assessmentName) {
        Intent intent = new Intent(context, CepingDetailActivity.class);
        intent.putExtra(INTENT_KEY_IN_ASSESSMENT_ID, assessmentId);
        intent.putExtra(INTENT_KEY_IN_ASSESSMENT_NAME, assessmentName);
        context.startActivity(intent);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_ceping_detail;
    }

    @Override
    protected void initView() {
        setOnClickListener(R.id.tv_item1, R.id.tv_item2, R.id.tv_item3, R.id.tv_item4, R.id.iv_back);
        tv_step = findViewById(R.id.tv_step);
        tv_step_all = findViewById(R.id.tv_step_all);
        tv_title = findViewById(R.id.tv_title);
        seekBar = findViewById(R.id.seekbar);
        seekBar.setEnabled(false);
    }

    @SuppressLint("SetTextI18n")
    @Override
    protected void initData() {

        String json = ToolUtils.loadJson(this, "ceping.json");
        Type type = new TypeToken<List<CepingBean>>() {
        }.getType();
        list = GsonFactory.getSingletonGson().fromJson(json, type);
        tv_step_all.setText("/" + list.size());
        seekBar.setMax(list.size());
        initCeping();
    }

    private void initCeping() {
        if (list.size() > 0) {
            tv_step.setText(ValueOf.toString(currentItem + 1));
            tv_title.setText(list.get(currentItem).getTitle());
            seekBar.setProgress(currentItem);
        }
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onNoDoubleClick(View view) {

        switch (view.getId()) {
            case R.id.tv_item1:
                clickItem(0);
                break;
            case R.id.tv_item2:
                clickItem(1);
                break;
            case R.id.tv_item3:
                clickItem(2);
                break;
            case R.id.tv_item4:
                clickItem(3);
                break;
            case R.id.iv_back:
                onBackPressed();
                break;
        }

    }

    public void postResult(String result) {
        EasyHttp.post(this).api(new AssessmentReportApi()
                        .setResult(result)
                        .setAssessmentId(getInt(INTENT_KEY_IN_ASSESSMENT_ID))
                        .setAssessmentName(getString(INTENT_KEY_IN_ASSESSMENT_NAME))
                        .setUserId(PreferencesUtils.getInstance().get("userId", 0)))
                .request(new HttpCallbackProxy<HttpData<Void>>(this) {

                    @Override
                    public void onHttpSuccess(HttpData<Void> bean) {
//                        Toaster.show("提交成功");
                    }
                });
    }

    public void clickItem(int select) {
        if (currentItem == list.size() - 1) {
            String result = jisuan();
            postResult(result);
            new XPopup.Builder(this).asConfirm("结果", "您的测评结果为:" + result, this::finish).show();
            return;
        }
        list.get(currentItem).setSelect(select);
        currentItem++;
        initCeping();
    }

    public String jisuan() {
        int s = 0;
        for (CepingBean cepingBean : list) {
            s += cepingBean.score[cepingBean.select];
        }
        s = (int) (s * 1.25);
        if (s <= 53) {
            return "正常";
        } else if (s <= 62) {
            return "轻度抑郁";
        } else if (s <= 72) {
            return "中度抑郁";
        } else if (s > 72) {
            return "高度抑郁";
        } else {
            return "无法评估";
        }
    }
}


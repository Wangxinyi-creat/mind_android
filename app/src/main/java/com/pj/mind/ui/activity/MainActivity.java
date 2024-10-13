package com.pj.mind.ui.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.RadioButton;

import androidx.annotation.IdRes;

import com.hjq.base.action.ClickAction;
import com.hjq.http.EasyHttp;
import com.pj.mind.R;
import com.pj.mind.app.AppActivity;
import com.pj.mind.app.AppFragment;
import com.pj.mind.manage.FragmentManagerHelper;
import com.pj.mind.ui.fragment.FaxianFragment;
import com.pj.mind.ui.fragment.MainFragment;
import com.pj.mind.ui.fragment.MyFragment;
import com.pj.mind.ui.fragment.YuyueFragment;


public class MainActivity extends AppActivity implements ClickAction {
    private static final String INTENT_KEY_IN_FRAGMENT = "id";

    private RadioButton radio_home, radio_faxian, radio_yuyue, radio_my;
    private int currentId = R.id.radio_home;
    private MainFragment mainFragment;
    private MyFragment myFragment;
    private YuyueFragment yuyueFragment;
    private FaxianFragment faxianFragment;
    private FragmentManagerHelper fragmentManagerHelper;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    public static void start(Context context, @IdRes int id) {
        Intent intent = new Intent(context, MainActivity.class);
        intent.putExtra(INTENT_KEY_IN_FRAGMENT, id);
        if (!(context instanceof Activity)) {
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        }
        context.startActivity(intent);
    }

    @Override
    protected void initView() {
        radio_home = findViewById(R.id.radio_home);
        radio_faxian = findViewById(R.id.radio_faxian);
        radio_yuyue = findViewById(R.id.radio_yuyue);
        radio_my = findViewById(R.id.radio_my);
        fragmentManagerHelper = new FragmentManagerHelper(getSupportFragmentManager(), R.id.ll_con);
        initFragment();
        setOnClickListener(R.id.radio_home, R.id.radio_faxian, R.id.radio_yuyue, R.id.radio_my);
    }

    @Override
    protected void initData() {
    }


    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        currentId = getInt(INTENT_KEY_IN_FRAGMENT, R.id.radio_home);
        initFragment();
    }

    private void initFragment() {
        radio_home.setChecked(currentId == R.id.radio_home);
        radio_yuyue.setChecked(currentId == R.id.radio_yuyue);
        radio_faxian.setChecked(currentId == R.id.radio_faxian);
        radio_my.setChecked(currentId == R.id.radio_my);
        fragmentManagerHelper.switchFragment(getFragment(currentId));
    }

    @Override
    public void onClick(View view) {
        currentId = view.getId();
        initFragment();
    }

    private AppFragment<AppActivity> getFragment(@IdRes int viewId) {
        switch (viewId) {
            case R.id.radio_home:
            default:
                if (mainFragment == null) {
                    mainFragment = MainFragment.newInstance();
                }
                return mainFragment;

            case R.id.radio_my:
                if (myFragment == null) {
                    myFragment = MyFragment.newInstance();
                }
                return myFragment;
            case R.id.radio_faxian:
                if (faxianFragment == null) {
                    faxianFragment = FaxianFragment.newInstance();
                }
                return faxianFragment;
            case R.id.radio_yuyue:
                if (yuyueFragment == null) {
                    yuyueFragment = YuyueFragment.newInstance();
                }
                return yuyueFragment;

        }
    }

//    @Override
//    public void onBackPressed() {
//        // 移动到上一个任务栈，避免侧滑引起的不良反应
//        moveTaskToBack(false);
//    }
}
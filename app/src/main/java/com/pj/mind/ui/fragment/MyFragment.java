package com.pj.mind.ui.fragment;


import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.hjq.base.action.ClickAction;
import com.hjq.http.EasyHttp;
import com.hjq.http.listener.HttpCallbackProxy;
import com.hjq.permissions.OnPermissionCallback;
import com.hjq.permissions.Permission;
import com.hjq.permissions.XXPermissions;
import com.luck.picture.lib.entity.LocalMedia;
import com.luck.picture.lib.listener.OnResultCallbackListener;
import com.pj.mind.AppContract;
import com.pj.mind.R;
import com.pj.mind.app.AppActivity;
import com.pj.mind.app.AppApplication;
import com.pj.mind.app.AppFragment;
import com.pj.mind.http.Model.HttpData;
import com.pj.mind.http.api.login.AvatarApi;
import com.pj.mind.http.api.login.UserApi;
import com.pj.mind.manage.ActivityManager;
import com.pj.mind.ui.activity.BrowserActivity;
import com.pj.mind.ui.activity.CepingReportActivity;
import com.pj.mind.ui.activity.LoginActivity;
import com.pj.mind.ui.activity.MainActivity;
import com.pj.mind.ui.activity.QingsuAcitivity;
import com.pj.mind.ui.activity.ShoucangAcitivity;
import com.pj.mind.ui.activity.YuyueAcitivity;
import com.pj.mind.utils.GlideCircleTransformWithBorderUtils;
import com.pj.mind.utils.GlideLoadUtils;
import com.pj.mind.utils.NullUtil;
import com.pj.mind.utils.PreferencesUtils;
import com.pj.mind.utils.ToolUtils;
import com.pj.mind.view.CircleImageView;

import java.io.File;
import java.util.List;

import okhttp3.Call;


/**
 * @author Created by   on 2023/8/2
 */
public class MyFragment extends AppFragment<AppActivity> implements ClickAction {

    private TextView name;
    private ImageView iv_touxiang;

    public static MyFragment newInstance() {
        return new MyFragment();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_my;
    }

    @Override
    protected void initView() {
        setOnClickListener(R.id.iv_touxiang, R.id.ll_yuyue, R.id.ll_suqiu, R.id.login_out, R.id.ll_report,R.id.ll_shoucang);
        name = findViewById(R.id.name);
        iv_touxiang = findViewById(R.id.iv_touxiang);
    }

    @Override
    protected void initData() {
        getMain();
    }

    private void getMain() {
        EasyHttp.get(this).api(new UserApi())
                .request(new HttpCallbackProxy<HttpData<UserApi.UserBean>>(this) {

                    @Override
                    public void onHttpSuccess(HttpData<UserApi.UserBean> bean) {
                        if (bean.getData() == null) {
                            return;
                        }
                        name.setText(bean.getData().getNickName());
                        GlideLoadUtils.getInstance().glideLoadNormal(getContext(), AppContract.BASE_URL + bean.getData().getAvatar(), iv_touxiang, R.mipmap.bg_login);
                    }
                });
    }


    @Override
    public void onNoDoubleClick(View view) {
        switch (view.getId()) {
            case R.id.login_out:
                PreferencesUtils.getInstance().clear(AppApplication.getInstance());
                // 登录信息失效，跳转到登录页
                ActivityManager.getInstance().finishAllActivities();
                LoginActivity.start(AppApplication.getInstance());
                break;
            case R.id.ll_yuyue:
                YuyueAcitivity.start(getContext());
                break;
            case R.id.ll_report:
                CepingReportActivity.start(getContext());
                break;
            case R.id.ll_suqiu:
                QingsuAcitivity.start(getContext(), -1);
                break;
            case R.id.ll_shoucang:
                ShoucangAcitivity.start(getContext());
                break;
            case R.id.iv_touxiang:
                XXPermissions.with(this)
                        .permission(Permission.READ_MEDIA_IMAGES, Permission.READ_MEDIA_VIDEO, Permission.READ_MEDIA_AUDIO)
                        .request(new OnPermissionCallback() {
                            @Override
                            public void onGranted(@NonNull List<String> permissions, boolean allGranted) {
                                selectPhoto();
                            }

                            @Override
                            public void onDenied(@NonNull List<String> permissions, boolean doNotAskAgain) {

                            }
                        });
                break;
        }
    }

    private void selectPhoto() {
        ToolUtils.getTouxiangImgWithCrop(getActivity(), new OnResultCallbackListener<LocalMedia>() {
            @Override
            public void onResult(List<LocalMedia> result) {
                String realPath = result.get(0).getCutPath();
                upload(new File(realPath));
            }

            @Override
            public void onCancel() {

            }
        });

    }


    private void upload(File file) {

        EasyHttp.post(this)
                .api(new AvatarApi().setFile(file))
                .request(new HttpCallbackProxy<HttpData>(this) {

                    @Override
                    public void onHttpSuccess(HttpData data) {
                        if (NullUtil.isStringEmpty(data.getImgUrl())) {
                            return;
                        }
                        GlideLoadUtils.getInstance().glideLoadCircle(getContext(), AppContract.BASE_URL + data.getImgUrl(), iv_touxiang, R.mipmap.bg_login);
                    }

                    @Override
                    public void onHttpEnd(Call call) {
                    }
                });
    }

}

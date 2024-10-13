package com.pj.mind.utils;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Environment;

import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.config.PictureConfig;
import com.luck.picture.lib.config.PictureMimeType;
import com.luck.picture.lib.entity.LocalMedia;
import com.luck.picture.lib.listener.OnResultCallbackListener;
import com.luck.picture.lib.style.PictureSelectorUIStyle;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

public class ToolUtils {
    /**
     * dp 转化为 px
     */
    public static int dp2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    public static boolean isEmail(String email) {
        String str = "^([a-zA-Z0-9_\\-\\.]+)@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.)|(([a-zA-Z0-9\\-]+\\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\\]?)$";
        Pattern p = Pattern.compile(str);
        Matcher m = p.matcher(email);

        return m.matches();
    }

    public static boolean isNetworkAvailable(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (cm != null) {
            //如果仅仅是用来判断网络连接
            //则可以使用 cm.getActiveNetworkInfo().isAvailable();
            NetworkInfo[] info = cm.getAllNetworkInfo();
            if (info != null) {
                for (NetworkInfo networkInfo : info) {
                    if (networkInfo.getState() == NetworkInfo.State.CONNECTED) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public static String stringFilter(String str) throws PatternSyntaxException {
        // 只允许字母、空格
        String regEx = "[^a-zA-Z\\s]";
        Pattern p = Pattern.compile(regEx);
        Matcher m = p.matcher(str);
        return m.replaceAll("").trim();
    }

    public static File compressImageAndSave(Context context, byte[] buffer, int rotation) throws Exception {
        BitmapFactory.Options options = new BitmapFactory.Options();
        //设置为将图片压缩为原来的三分之一大小
        options.inSampleSize = 1;
        Bitmap image = BitmapFactory.decodeByteArray(buffer, 0, buffer.length);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();

        image.compress(Bitmap.CompressFormat.JPEG, 100, baos);//这里100表示不压缩，将不压缩的数据存放到baos中
        int per = 100;
        while (baos.toByteArray().length / 1024 > 1000) { // 循环判断如果压缩后图片是否大于500kb,大于继续压缩
            baos.reset();// 重置baos即清空baos
            image.compress(Bitmap.CompressFormat.JPEG, per, baos);// 将图片压缩为原来的(100-per)%，把压缩后的数据存放到baos中
            per -= 10;// 每次都减少10

        }
        if (rotation != 0) {
            // 只能是裁剪完之后再旋转了。有没有别的更好的方案呢？
            Matrix matrix = new Matrix();
            matrix.postRotate(rotation);
            Bitmap rotatedBitmap = Bitmap.createBitmap(
                    image, 0, 0, image.getWidth(), image.getHeight(), matrix, false);
            if (image != rotatedBitmap) {
                // 有时候 createBitmap会复用对象
                image.recycle();
            }
            image = rotatedBitmap;
        }
        String outputPath = "/kin_" + System.currentTimeMillis() + ".jpg";
        File outputFile = new File(getDiskCacheDir(context), outputPath);
        if (!outputFile.exists()) {
            outputFile.createNewFile();
        }
        FileOutputStream fileOutputStream = new FileOutputStream(outputFile);
        image.compress(Bitmap.CompressFormat.JPEG, 90, fileOutputStream);
        fileOutputStream.flush();
        fileOutputStream.close();
        return outputFile;
    }

    public static  String loadJson(Context context,String fileName){
        try {
           InputStream inputStream = context.getAssets().open(fileName);

           byte[] buffer = new byte[inputStream.available()];
           inputStream.read(buffer);
           return new String(buffer, StandardCharsets.UTF_8);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }

    public static String getDiskCacheDir(Context context) {
        return getDiskCacheDirFile(context).getPath();
    }

    public static File getDiskCacheDirFile(Context context) {
        File cachePath = null;
        if (Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())
                || !Environment.isExternalStorageRemovable()) {
            cachePath = context.getExternalCacheDir();
        } else {
            cachePath = context.getCacheDir();
        }
        return cachePath;
    }


    public static void clearCache(Context context) {
        File cacheDir = getDiskCacheDirFile(context);
        if (cacheDir != null && cacheDir.isDirectory()) {
            File[] files = cacheDir.listFiles();
            if (files != null) {
                for (File file : files) {
                    file.delete();
                }
            }
        }
    }

    public static String getTime(String timestamp) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
        try {
            Date date = dateFormat.parse(timestamp);
            SimpleDateFormat timeOnlyFormat = new SimpleDateFormat("HH:mm", Locale.getDefault());
            return timeOnlyFormat.format(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return "";

    }

    public static String getDateNoYear(String time){
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
        try {
            Date date = dateFormat.parse(time);
            SimpleDateFormat timeOnlyFormat = new SimpleDateFormat("MM-dd HH:mm", Locale.getDefault());
            return timeOnlyFormat.format(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return "";
    }

    public static String getDate(String timestamp) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
        try {
            Date date = dateFormat.parse(timestamp);
            SimpleDateFormat dateOnlyFormat = new SimpleDateFormat("MM-dd", Locale.getDefault());
            return dateOnlyFormat.format(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return "";
    }

    public static String getWeek(String week){
        String[] dayArray = week.split(",");
        List<String> weekdays = new ArrayList<>();
        for (String day : dayArray) {
            switch (day) {
                case "1":
                    weekdays.add("周一");
                    break;
                case "2":
                    weekdays.add("周二");
                    break;
                case "3":
                    weekdays.add("周三");
                    break;
                case "4":
                    weekdays.add("周四");
                    break;
                case "5":
                    weekdays.add("周五");
                    break;
                case "6":
                    weekdays.add("周六");
                    break;
                case "7":
                    weekdays.add("周日");
                    break;
                // 如果有其他可能的数字，可以在此处添加case
                default:
                    // 可以根据需要处理无效的输入
                    break;
            }
        }
        return String.join(" ", weekdays);

    }

    public static void getBookImgWithCrop(Activity activity, OnResultCallbackListener<LocalMedia> onResultCallbackListener) {
        PictureSelector.create(activity)
                .openGallery(PictureMimeType.ofImage())// 全部.PictureMimeType.ofAll()、图片.ofImage()、视频.ofVideo()、音频.ofAudio()
                .imageEngine(GlideEngine.createGlideEngine())// 外部传入图片加载引擎，必传项
                .setPictureUIStyle(PictureSelectorUIStyle.ofNewStyle())// 主题样式设置
                .isWeChatStyle(true)// 是否开启微信图片选择风格
                .isWithVideoImage(true)// 图片和视频是否可以同选,只在ofAll模式下有效
                .isMaxSelectEnabledMask(true)// 选择数到了最大阀值列表是否启用蒙层效果
                .maxSelectNum(1)// 最大选择数量
                .minSelectNum(1)// 最小选择数量
                .selectionMode(PictureConfig.SINGLE)// MULTIPLE 多选 or SINGLE 单选
                .isEnableCrop(true)// 是否裁剪
                .withAspectRatio(2, 4)// 裁剪比例 如16:9 3:2 3:4 1:1 可自定义
                .hideBottomControls(false)// 是否显示uCrop工具栏，默认不显示
                .isCropDragSmoothToCenter(true)// 裁剪框拖动时图片自动跟随居中
                .isSingleDirectReturn(false)// 单选模式下是否直接返回，PictureConfig.SINGLE模式下有效
                .isPreviewImage(true)// 是否可预览图片
                .isPreviewVideo(true)// 是否可预览视频
                .isCamera(true)// 是否显示拍照按钮
                .selectionData(null)// 是否传入已选图片
                .isAutoScalePreviewImage(true)// 如果图片宽度不能充满屏幕则自动处理成充满模式
                .forResult(onResultCallbackListener);
    }

    public static void getTouxiangImgWithCrop(Activity activity, OnResultCallbackListener<LocalMedia> onResultCallbackListener) {
        PictureSelector.create(activity)
                .openGallery(PictureMimeType.ofImage())// 全部.PictureMimeType.ofAll()、图片.ofImage()、视频.ofVideo()、音频.ofAudio()
                .imageEngine(GlideEngine.createGlideEngine())// 外部传入图片加载引擎，必传项
                .setPictureUIStyle(PictureSelectorUIStyle.ofNewStyle())// 主题样式设置
                .isWeChatStyle(true)// 是否开启微信图片选择风格
                .isWithVideoImage(true)// 图片和视频是否可以同选,只在ofAll模式下有效
                .isMaxSelectEnabledMask(true)// 选择数到了最大阀值列表是否启用蒙层效果
                .maxSelectNum(1)// 最大选择数量
                .minSelectNum(1)// 最小选择数量
                .selectionMode(PictureConfig.SINGLE)// MULTIPLE 多选 or SINGLE 单选
                .isEnableCrop(true)// 是否裁剪
                .circleDimmedLayer(true)

                .withAspectRatio(1, 1)// 裁剪比例 如16:9 3:2 3:4 1:1 可自定义
                .hideBottomControls(false)// 是否显示uCrop工具栏，默认不显示
                .isCropDragSmoothToCenter(true)// 裁剪框拖动时图片自动跟随居中
                .isSingleDirectReturn(false)// 单选模式下是否直接返回，PictureConfig.SINGLE模式下有效
                .isPreviewImage(true)// 是否可预览图片
                .isPreviewVideo(true)// 是否可预览视频
                .isCamera(true)// 是否显示拍照按钮
                .selectionData(null)// 是否传入已选图片
                .isAutoScalePreviewImage(true)// 如果图片宽度不能充满屏幕则自动处理成充满模式
                .forResult(onResultCallbackListener);
    }

}


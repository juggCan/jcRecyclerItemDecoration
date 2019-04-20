package wxhelp.jugg.com.jcnewdome;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.TrafficStats;
import android.net.Uri;
import android.os.Environment;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewPropertyAnimator;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.lang.reflect.Field;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class SystemUtil {
    private static final String TAG = "SystemUtil";
    public final static int DEFAULT_DELAY = 0;

    public static boolean isNetworkAvailable(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        return netInfo != null && netInfo.isConnectedOrConnecting();
    }

    private static ConnectivityManager mConnectivityManager = null;

    private static ConnectivityManager getConnectivityManager(Context context) {
        if (mConnectivityManager == null) {
            mConnectivityManager = (ConnectivityManager) context
                    .getSystemService(Context.CONNECTIVITY_SERVICE);
        }
        return mConnectivityManager;
    }

    public static boolean isNetworkConnected(Context context) {
        if (context != null) {
            NetworkInfo mNetworkInfo = getConnectivityManager(context).getActiveNetworkInfo();
            if (mNetworkInfo != null) {
                return mNetworkInfo.isAvailable();
            }
        }
        return false;
    }

    public static boolean isWifiConnected(Context context) {
        if (context != null) {
            NetworkInfo mWiFiNetworkInfo = getConnectivityManager(context).getActiveNetworkInfo();
            if (mWiFiNetworkInfo != null) {
                return (mWiFiNetworkInfo.getType() == ConnectivityManager.TYPE_WIFI);
            }
        }
        return false;
    }

    public static boolean isMobileConnected(Context context) {
        if (context != null) {
            NetworkInfo mNetworkInfo = getConnectivityManager(context).getActiveNetworkInfo();
            if (mNetworkInfo != null) {
                return (mNetworkInfo.getType() == ConnectivityManager.TYPE_MOBILE);
            }
        }
        return false;
    }

    public static byte[] bmpToByteArray(final Bitmap bmp, final boolean needRecycle) {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        bmp.compress(Bitmap.CompressFormat.PNG, 100, output);
        if (needRecycle) {
            bmp.recycle();
        }

        byte[] result = output.toByteArray();
        try {
            output.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

    public static long getTotalRxBytes(Context context) {
//        Class class1 = null;
//        try {
//            class1 = Class.forName("android.net.TrafficStats");
//            //Constructor<?> constructor = class1.getConstructor(Context.class);
//            Object obj = class1.newInstance();
//            Method method = class1.getMethod("getTotalRxBytes", null);
//            return (long) method.invoke(obj, null);
//        } catch (ClassNotFoundException e) {
//            e.printStackTrace();
//        } catch (NoSuchMethodException e) {
//            e.printStackTrace();
//        } catch (InstantiationException e) {
//            e.printStackTrace();
//        } catch (IllegalAccessException e) {
//            e.printStackTrace();
//        } catch (InvocationTargetException e) {
//            e.printStackTrace();
//        } finally {
//            return 0;
//        }
        return TrafficStats.getTotalRxBytes();
    }

    /**
     * Shows a view by scaling
     *
     * @param v the view to be scaled
     * @return the ViewPropertyAnimation to manage the animation
     */
    public static ViewPropertyAnimator showViewByScale(View v, int a) {

        ViewPropertyAnimator propertyAnimator = v.animate().setStartDelay(DEFAULT_DELAY).setDuration(a)
                .scaleX(1).scaleY(1);

        return propertyAnimator;
    }

    /**
     * Scale and set the pivot when the animation will start from
     *
     * @param v the view to set the pivot
     */
    public static void configureHideYView(View v) {
        v.setScaleY(0);
        v.setPivotY(0);
    }

    /**
     * Reduces the Y from a view
     *
     * @param v the view to be scaled
     * @return the ViewPropertyAnimation to manage the animation
     */
    public static ViewPropertyAnimator hideViewByScaleY(View v) {

        return hideViewByScale(v, DEFAULT_DELAY, 1, 0);
    }

    /**
     * Reduces the X & Y
     *
     * @param v     the view to be scaled
     * @param delay to start the animation
     * @param x     integer to scale
     * @param y     integer to scale
     * @return the ViewPropertyAnimation to manage the animation
     */
    private static ViewPropertyAnimator hideViewByScale(View v, int delay, int x, int y) {

        ViewPropertyAnimator propertyAnimator = v.animate().setStartDelay(delay)
                .scaleX(x).scaleY(y);

        return propertyAnimator;
    }

    public static int getScreenWidth(Context context) {
        WindowManager wm = (WindowManager) context
                .getSystemService(Context.WINDOW_SERVICE);
        return wm.getDefaultDisplay().getWidth();
    }

    public static int getScreenHeight(Context context) {
        WindowManager wm = (WindowManager) context
                .getSystemService(Context.WINDOW_SERVICE);
        return wm.getDefaultDisplay().getHeight();
    }

    //判断是否安装qq
    public static boolean isQQClientAvailable(Context context) {
        final PackageManager packageManager = context.getPackageManager();
        List<PackageInfo> pinfo = packageManager.getInstalledPackages(0);
        if (pinfo != null) {
            for (int i = 0; i < pinfo.size(); i++) {
                String pn = pinfo.get(i).packageName;
                if (pn.equals("com.tencent.mobileqq")) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * 保留两位小数
     */
    public static String decimal2Format(String str) {
        float f = 0;
        try {
            f = Float.parseFloat(str);
        } catch (Exception e) {
            e.printStackTrace();
        }
        DecimalFormat df = new DecimalFormat("#0.00");
        return df.format(f);
    }

    /**
     * 保留一位小数
     */
    public static String decimal1Format(String str) {
        float f = 0;
        try {
            f = Float.parseFloat(str);
        } catch (Exception e) {
            e.printStackTrace();
        }
        DecimalFormat df = new DecimalFormat("#0.0");
        return df.format(f);
    }

    /**
     * 根据手机的分辨率从 px(像素) 的单位 转成为 dp
     */
    public static int px2dip(Context context, float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }

    /**
     * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
     */
    public static int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    /**
     * 获取sd卡路径
     *
     * @return
     */
    /**
     * 判断SD卡是否插入
     ***/
    public static boolean sdCardExist() {
        boolean sdCardExist = Environment.getExternalStorageState().equals(
                Environment.MEDIA_MOUNTED);
        return sdCardExist;
    }


    public static String getSDPath() {
        File sdDir = null;
        if (sdCardExist()) {
            sdDir = Environment.getExternalStorageDirectory();
        }
        if (sdDir != null) {
            return sdDir.toString();
        } else {
            return "";
        }
    }


    /**
     * 验证手机号码是否正确
     *
     * @param mobiles
     * @return
     */
    public static boolean isMobileNO(String mobiles) {
        Pattern p = Pattern.compile("^((13[0-9])|(14[5,7])|(15[^4,\\D])|(17[0,5-9])|(18[0,5-9]))\\d{8}$");
        Matcher m = p.matcher(mobiles);
        return m.matches();
    }


    /*
     *获取版本名称
     */
    public static String getVersionName(Context context) {
        try {
            PackageInfo packageInfo = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
            return packageInfo.versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return "0";
        }
    }

    /**
     * 获取应用版本号
     *
     * @param a
     * @return
     */
    public static int getVersionCode(Context ctx) {
        PackageManager pm = ctx.getPackageManager();
        PackageInfo pi;
        try {
            pi = pm.getPackageInfo(ctx.getPackageName(), 0);

            return pi.versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }

        return 0;
    }

    /**
     * 获取IMEI码
     * 读取imei号  在24 以上需要申请权限
     *
     * @return
     */
    public static String getDeviceImei(Context sContext) {
        TelephonyManager tm = (TelephonyManager) sContext
                .getSystemService(Context.TELEPHONY_SERVICE);
//        if (ActivityCompat.checkSelfPermission(sContext, Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
//            return TODO;
//        }
        return tm.getDeviceId();
    }

    /*
     * 生成一个uuid
     */
    public static String newRandomUUID() {
        String uuidRaw = UUID.randomUUID().toString();
        return uuidRaw.replaceAll("-", "");
    }

    /**
     * 防止inputMethodManager造成的内存泄漏
     *
     * @param destContext
     */
    public static void fixInputMethodManagerLeak(Context destContext) {
        if (destContext == null) {
            return;
        }
        InputMethodManager imm = (InputMethodManager) destContext.getSystemService(Context.INPUT_METHOD_SERVICE);
        if (imm == null) {
            return;
        }
        String[] arr = new String[]{"mCurRootView", "mServedView", "mNextServedView"};
        Field f = null;
        Object obj_get = null;
        for (int i = 0; i < arr.length; i++) {
            String param = arr[i];
            try {
                f = imm.getClass().getDeclaredField(param);
                if (f.isAccessible() == false) {
                    f.setAccessible(true);
                } // author: sodino mail:sodino@qq.com
                obj_get = f.get(imm);
                if (obj_get != null && obj_get instanceof View) {
                    View v_get = (View) obj_get;
//                    if (v_get.getContext() == destContext) { // 被InputMethodManager持有引用的context是想要目标销毁的
                    f.set(imm, null); // 置空，破坏掉path to gc节点
//                    } else {
//                        // 不是想要目标销毁的，即为又进了另一层界面了，不要处理，避免影响原逻辑,也就不用继续for循环了
//                        break;
//                    }
                }
            } catch (Throwable t) {
                t.printStackTrace();
            }
        }
    }


    /**
     * @param input 输入字符串
     * @return 去掉数字后的整数
     * @descreption: 取出字符串中的所有数字，并转换成整数
     */
    public static Integer removeDigit(String input) {
        Integer rtn = 0;
        try {
            Pattern parttern = Pattern.compile("[^0-9]");
            Matcher matcher = parttern.matcher(input);
            String replaceAll = matcher.replaceAll("").trim();
            rtn = Integer.valueOf(replaceAll);
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        return rtn;
    }

    public static Bitmap loadBitmapFromView(View v) {
        if (v == null) {
            return null;
        }
        v.setDrawingCacheEnabled(true);
        Bitmap screenshot = v.getDrawingCache();
//        v.setDrawingCacheEnabled(false);
//        v.measure(View.MeasureSpec.makeMeasureSpec(v.getWidth(),
//                View.MeasureSpec.UNSPECIFIED), View.MeasureSpec.makeMeasureSpec(
//                v.getHeight(), View.MeasureSpec.UNSPECIFIED));
//        v.layout(0, 0, v.getMeasuredWidth(), v.getMeasuredHeight());
//        v.buildDrawingCache();
//        screenshot = v.getDrawingCache();
//        if (screenshot == null) {
//            v.setDrawingCacheEnabled(true);
//            screenshot = Bitmap.createBitmap(v.getWidth(), v.getHeight(),
//                    Bitmap.Config.ARGB_8888);
//            Canvas c = new Canvas(screenshot);
//            c.translate(-v.getScrollX(), -v.getScrollY());
//            v.draw(c);
//            return screenshot;
//        }
        return screenshot;
    }

    /*
     *输入年月 得到当前余月的天数
     */
    public static int getMonthOfDay(int year, int month) {
        int day = 0;
        if (year % 4 == 0 && year % 100 != 0 || year % 400 == 0) {
            day = 29;
        } else {
            day = 28;
        }
        switch (month) {
            case 1:
            case 3:
            case 5:
            case 7:
            case 8:
            case 10:
            case 12:
                return 31;
            case 4:
            case 6:
            case 9:
            case 11:
                return 30;
            case 2:
                return day;

        }

        return 0;
    }

    /*
    String 转时间
     */
    public static int[] stringtoCalender(String time) {
        int[] time1 = new int[3];

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        Date date = null;
        try {
            date = sdf.parse(time);
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);

            time1[0] = calendar.get(Calendar.YEAR);
            time1[1] = calendar.get(Calendar.MONTH) + 1;
            time1[2] = calendar.get(Calendar.DAY_OF_MONTH);
        } catch (ParseException e) {
            e.printStackTrace();
            time1[0] = 0;
            time1[1] = 0;
            time1[2] = 0;
        }
        return time1;
    }


    /**
     * 拨打电话
     *
     * @param activity
     * @param telString
     */
    public static boolean makeCall(Activity activity, String telString) {

        try {

            Intent intent = new Intent(Intent.ACTION_DIAL);
            intent.setData(Uri.parse("tel:" + telString));
            activity.startActivity(intent);

            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    //
    public static String httptohttps(String url) {
        String urlcopy = url;
        if (TextUtils.isEmpty(urlcopy))
            return "";
        if (urlcopy.startsWith("https:")) {
            return urlcopy;
        }
        if (urlcopy.startsWith("http:")) {
            urlcopy = urlcopy.replace("http:", "https:");
            return urlcopy;
        }
        return "";
    }


    //
    public static String stringisNull(String str, String d_str) {
        if (str != null) {
            return str;
        }
        return d_str;
    }
}

package kr.benefitplus.drawerexam.lib;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Point;
import android.os.Build;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.ViewConfiguration;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

import kr.benefitplus.drawerexam.MainActivity;

public class BplusLib {

    static Context context;
    static final int PX_SIZE = 1;
    static final int DP_SIZE = 2;

    public BplusLib(Context context) {
        BplusLib.context = context;
    }

    /*********************************************************
     * 화면 사이즈를 구한다.
     * reruen : Point size
     ********************************************************/
    public Point getScreenSize(int size_type) {
        Display display =  ((MainActivity)context).getWindowManager().getDefaultDisplay();
        DisplayMetrics outMetrics = new DisplayMetrics ();
        display.getMetrics(outMetrics);
        Point size = new Point();

        switch (size_type) {
            case  PX_SIZE:
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
                    display.getRealSize(size); // or getSize(size)getRealSize
                }else{
                    size.y = outMetrics.heightPixels;
                    size.x = outMetrics.widthPixels;
                }
                break;
            case  DP_SIZE:
                float density = context.getResources().getDisplayMetrics().density;
                size.y = (int) (outMetrics.heightPixels / density);
                size.x = (int) (outMetrics.widthPixels / density);
                break;
        }
        return size;
    }

    /*********************************************************
     * 화면 Density(밀도)를 구한다.
     * reruen : Point size
     ********************************************************/
    public int getDpi(){
        DisplayMetrics displayMetrics = new DisplayMetrics();
        ((MainActivity)context).getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        return displayMetrics.densityDpi;
    }

    public int getActionBarHeight(){
        int actionBarHeight = 0;
        final TypedArray styledAttributes = ((MainActivity)context).getTheme().obtainStyledAttributes(
                new int[] { android.R.attr.actionBarSize }
        );
        actionBarHeight = (int) styledAttributes.getDimension(0, 0);
        styledAttributes.recycle();

        return actionBarHeight;
    }

    public int getStatusBarHeight() {
        int	result = 0;
        int resourceId = ((MainActivity)context).getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            result = ((MainActivity)context).getResources().getDimensionPixelSize(resourceId);
        }
        return result;
    }

    public int getNavigationBarHeight() {

        int navigationBarHeight = 0;
        boolean hasMenuKey = ViewConfiguration.get(context).hasPermanentMenuKey();
        int resId = ((MainActivity)context).getResources().getIdentifier("navigation_bar_height", "dimen", "android");
        if (resId > 0 && !hasMenuKey) {
            navigationBarHeight = ((MainActivity)context).getResources().getDimensionPixelSize(resId);
            return navigationBarHeight;
        }

        return 0;
    }

    /*********************************************************************
     *  TimeZone은 단말의 Setting에서 살정한 TimeZome을 가지고 온다.
     *  tz.getDefault().getDisplayName() : 한국 표준시
     *  tz.getID() : Asia/Seoul
     ********************************************************************/
    public static String getTimeZoneId() {

        Calendar cal = Calendar.getInstance();
        TimeZone tz = cal.getTimeZone();

        //Log.d (TAG,"TimeZone    ::: "+ tz.getDefault().getDisplayName() + " / Timezon id ::: " +tz.getID());

        return tz.getID();
    }

    /*********************************************************************
     *  날자와 시간 관련 LIB
     *  1. String getYMD :      yyyy-MM-dd   2020-11-11
     *  2. String getHMS :      hh:mm:ss     12:23:30
     *  3. String getYMDHMS :   yyyy-MM-dd hh:mm:ss
     *  3. int getYear : 2021
     *  4. int getMonth : 12
     *  5. int getDay : 25
     *  6. int getHour : 12
     *  7. int getMinute : 12
     *  8. int getSecond : 25
     ********************************************************************/
    public String getYMD(){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return sdf.format(new Date(System.currentTimeMillis()));
    }

    public String getHMS(){
        SimpleDateFormat sdf = new SimpleDateFormat("hh:mm:ss");
        return sdf.format(new Date(System.currentTimeMillis()));
    }

    public String getYMDHMS(){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        return sdf.format(new Date(System.currentTimeMillis()));
    }

    public static int getYear(){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
        return Integer.parseInt(sdf.format(new Date(System.currentTimeMillis())));
    }

    public static int getYear (Date date){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
        return Integer.parseInt(sdf.format(date));
    }

    public static int getMonth(){
        SimpleDateFormat sdf = new SimpleDateFormat("MM");
        return Integer.parseInt(sdf.format(new Date(System.currentTimeMillis())));
    }

    public static int getMonth (Date date){
        SimpleDateFormat sdf = new SimpleDateFormat("MM");
        return Integer.parseInt(sdf.format(date));
    }


    public static int getDay(){
        SimpleDateFormat sdf = new SimpleDateFormat("dd");
        return Integer.parseInt(sdf.format(new Date(System.currentTimeMillis())));
    }

    public static int getDay (Date date){
        SimpleDateFormat sdf = new SimpleDateFormat("dd");
        return Integer.parseInt(sdf.format(date));
    }


    public static int getHour(boolean ampm){
        SimpleDateFormat sdf;
        if (ampm) {    // true = 24시간제
            sdf = new SimpleDateFormat("HH");
        }
        else {          // false = 12시간제
            sdf = new SimpleDateFormat("hh");
        }
        return Integer.parseInt(sdf.format(new Date(System.currentTimeMillis())));
    }

    public static String getAmpm () {
        SimpleDateFormat sdf = new SimpleDateFormat("a");
        return sdf.format(new Date(System.currentTimeMillis()));
    }

    public static int getMinute(){
        SimpleDateFormat sdf = new SimpleDateFormat("mm");
        return Integer.parseInt(sdf.format(new Date(System.currentTimeMillis())));
    }

    public int getSecond(){
        SimpleDateFormat sdf = new SimpleDateFormat("ss");
        return Integer.parseInt(sdf.format(new Date(System.currentTimeMillis())));
    }

    public static String getMilliSecond(){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.SSS");
        return sdf.format(new Date(System.currentTimeMillis()));
    }

    public static String getMilliSecond(long mills){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
        return sdf.format(new Date(mills));
    }

    public static int getWeek(int year, int month, int day) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month - 1, day);
        return calendar.get(Calendar.DAY_OF_WEEK);
    }

    public static String getWeekStr(int year, int month, int day) {
        String [] weekstr = {"일", "월", "화", "수", "목", "금", "토"};

        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month-1, day);
        /**
         Log.d (TAG, year + "/" + month + "/" + day + "은 " +
         calendar.get(Calendar.DAY_OF_WEEK) + ", " +
         weekstr[calendar.get(Calendar.DAY_OF_WEEK) - 1]);
         **/
        return weekstr[calendar.get(Calendar.DAY_OF_WEEK) - 1];
    }

    public static String getWeekStr(int week) {
        String [] weekstr = {"일", "월", "화", "수", "목", "금", "토"};
        return weekstr[week - 1] + "요일";
    }


    public static Date toDate(int year, int month, int day){
        String strDate = LPAD(year, 4) + LPAD(month, 2) + LPAD(day, 2);
        SimpleDateFormat fmt = new SimpleDateFormat("yyyyMMdd");
        try {
            return fmt.parse(strDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Date toDate(String dateStr, String pattern){

        SimpleDateFormat fmt = new SimpleDateFormat(pattern);
        try {
            return fmt.parse(dateStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Date toDate(String dateStr) {
        SimpleDateFormat fmt = new SimpleDateFormat("yyyyMMdd");
        Date date = null;
        try {
            date =  fmt.parse(dateStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

    public static String getDateStr (Date date){
        SimpleDateFormat fmt = new SimpleDateFormat("yyyyMMdd");
        return fmt.format(date);
    }

    public static int getDaysBetween(Date solraFixedDate, Date solraDate){
        return (int)((solraDate.getTime() - solraFixedDate.getTime()) / (24 * 60 * 60 * 1000));
    }

    public static String getDistentDate(Date solDate, int days) {
        //Log.d (TAG, "solDate = " + solDate.getTime() + ", " + getDateStr(solDate));
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");

        Calendar cal = Calendar.getInstance();
        cal.setTime(solDate);
        cal.add(Calendar.DAY_OF_MONTH, days);
        sdf.format(cal.getTime());

        //Log.d(TAG, "days = " + days + ", solDate = " + getDateStr(solDate) + ", " + sdf.format(cal.getTime()));
        return sdf.format(cal.getTime());
    }

    /*********************************************************************
     *  Strikng 관련 LIB
     *  1. String LPAD(int num, int size) : 왼쪽 0 채움
     *  2. String RPAD(int num, int size) : 오른쪽 0 채움
     ********************************************************************/
    public static String LPAD (int num, int size) {
        String fm = "%0" + size + "d";
        return String.format(fm, num);
    }

    public static String RPAD (int num, int size) {
        String strNum = num + "";
        if (strNum.length() > size) return strNum;

        StringBuilder sb = new StringBuilder();
        while (sb.length() < size - strNum.length()) {
            sb.append('0');
        }
        sb.append(strNum);

        return sb.toString();
    }

    public static void msgDialog (String title, String Msg) {

        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle(title).setMessage(Msg);
        builder.setPositiveButton("확인", new DialogInterface.OnClickListener(){

            @Override
            public void onClick(DialogInterface dialog, int id)
            {

            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    /*********************************************************************
     *  이미지 관련 라이브러리
     *
     *  1. 이미지의 밝기를 구한다.
     *  2. 컬러의 값을 밝기로값으로 가져오기
     *********************************************************************/
    public static float[] calculateBrightnessEstimate (Bitmap bitmap, int pixelSpacing){
        int R = 0;
        int G = 0;
        int B = 0;
        int T = 0;
        float S = 0;
        int height = bitmap.getHeight();
        int width = bitmap.getWidth();
        int n = 0;
        int[] pixels = new int[width * height];
        bitmap.getPixels(pixels, 0, width, 0, 0, width, height);
        for (int i = 0; i < pixels.length; i += pixelSpacing * 15) {
            int color = pixels[i];
            float[] hsv = new float[3];
            R += Color.red(color);
            G += Color.green(color);
            B += Color.blue(color);
            T += getColorToBrightness(color);
            Color.colorToHSV(color, hsv);
            S += hsv[1];
            n++;
        }

        //T = (R + B + G);
        float[] rtn = {(T / n), (S / n)};
        return rtn;
    }

    //컬러의 값을 밝기로값으로 가져오기
    private static int getColorToBrightness(int color) {
        int R = Color.red(color);
        int G = Color.green(color);
        int B = Color.blue(color);
        return (int) Math.sqrt(R * R * .241 + G * G * .691 + B * B * .068);
    }

    // bitmap의 밝기값을 매개값에 넣는다.
    // 리턴값으로 bitmap이미지위의 텍스트 색상 출력
    private int getColorStringFromBrightness(int brightness) {
        if (brightness < 195) {
            return 1;
        } else {
            return 2;
        }
    }

    public Bitmap resizeBitmapImage(
            Bitmap bmpSource, int maxResolution){
        int iWidth = bmpSource.getWidth();
        int iHeight = bmpSource.getHeight();
        int newWidth = iWidth ;
        int newHeight = iHeight ;
        float rate = 0.0f;

        if(iWidth > iHeight ){
            if(maxResolution < iWidth ){
                rate = maxResolution / (float) iWidth ;
                newHeight = (int) (iHeight * rate);
                newWidth = maxResolution;
            }
        }else{
            if(maxResolution < iHeight ){
                rate = maxResolution / (float) iHeight ;
                newWidth = (int) (iWidth * rate);
                newHeight = maxResolution;
            }
        }

        return Bitmap.createScaledBitmap(
                bmpSource, newWidth, newHeight, true);
    }
}

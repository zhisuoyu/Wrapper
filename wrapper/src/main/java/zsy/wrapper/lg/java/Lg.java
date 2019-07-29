package zsy.wrapper.lg.java;

import java.lang.reflect.Method;

import zsy.wrapper.lg.android.AndroidPrinter;


public class Lg {
    public static final int VERBOSE = 2;
    public static final int DEBUG = 3;
    public static final int INFO = 4;
    public static final int WARN = 5;
    public static final int ERROR = 6;

    public static final LogAdapter DEFAULT_LOG_ADAPTER = defaultLogAdapter();//new AssembleLogAdapter(new LogFormat.Builder().build());

    private static LogAdapter logAdapter;

    private static LogAdapter defaultLogAdapter() {
        if (isAndroidLoggable()) {
            return new AssembleLogAdapter(new LogFormat.Builder()
                    .setPrinter(AndroidPrinter.INSTANCE)
                    .setBaseTag("ZSY")
                    .setMethodOffset(5)
                    .setMethodCount(3)
                    .build());
        } else {
            return new AssembleLogAdapter(new LogFormat.Builder()
                    .setPrinter(SystemPrinter.INSTACNE)
                    .setBaseTag("ZSY")
                    .setMethodOffset(5)
                    .setMethodCount(3)
                    .build());
        }
    }
//

    private static boolean isAndroidLoggable() {
        boolean isAndroidLoggable = false;
        try {
            Class<?> cls = Class.forName("android.util.Log");
            Method method = cls.getDeclaredMethod("isLoggable", String.class, int.class);
            method.invoke(null, "", 2);
            isAndroidLoggable = true;
        } catch (Exception e) {
//            e.printStackTrace();
        }
        return isAndroidLoggable;
    }

//    private static void init() {
//
////        Class<?> cls = null;
////        try {
////            cls = Class.forName("android.util.Log");
////
////        } catch (ClassNotFoundException e) {
////            e.printStackTrace();
////        }
////        System.out.println("loggable:" + Log.isLoggable("", Log.VERBOSE));
////        Log.i("hello","world");
//    }

    public static void main(String[] args) {
        Lg.v("v","vvvvv");
        Lg.d("d","DDDDD");
        Lg.i("i","iiiii");
        Lg.w("w","wwwww");
        Lg.e("e","eeeee");
        Lg.setLogAdapter(new SystemLogAdapter());
        Lg.v("v","vvvvv");
        Lg.d("d","DDDDD");
        Lg.i("i","iiiii");
        Lg.w("w","wwwww");
        Lg.e("e","eeeee");
//        System.out.println("rt:" + isAndroidLoggable());
//        init();

//        Lg.setLogAdapter(new AssembleLogAdapter(
//                new LogFormat.Builder()
//                        .setBaseTag("Lg")
//                        .setShowDesInfo(true)
////                        .setMinLevel(DEBUG)
//                        .setMethodOffset(4)
//                        .setShowMethodTrace(true)
//                        .build()));
//        Lg.w("jj", "hello");
//        Lg.v("jj", "world");
//        Lg.setLogAdapter(new SystemLogAdapter());
//        Lg.w("jj", "hello");
//        Lg.v("jj", "world");

    }


    public static void setLogAdapter(LogAdapter logAdapter) {
        Lg.logAdapter = logAdapter;
    }


    private static LogAdapter getLogAdapter() {
        return logAdapter != null ? logAdapter : DEFAULT_LOG_ADAPTER;
    }

    private static void l(int level, String tag, String msg) {
        getLogAdapter().log(level, tag, msg);
    }


    public static void v(String tag, String msg) {
        l(VERBOSE, tag, msg);
    }

    public static void v(String msg) {
        v(null, msg);
    }


    public static void d(String tag, String msg) {
        l(DEBUG, tag, msg);
    }

    public static void d(String msg) {
        d(null, msg);
    }


    public static void i(String tag, String msg) {
        l(INFO, tag, msg);
    }

    public static void i(String msg) {
        i(null, msg);
    }


    public static void w(String tag, String msg) {
        l(WARN, tag, msg);
    }

    public static void w(String msg) {
        w(null, msg);
    }


    public static void e(String tag, String msg) {
        l(ERROR, tag, msg);
    }

    public static void e(String msg) {
        e(null, msg);
    }


}

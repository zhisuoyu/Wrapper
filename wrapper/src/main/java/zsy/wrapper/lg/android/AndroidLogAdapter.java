package zsy.wrapper.lg.android;


import zsy.wrapper.lg.java.Lg;
import zsy.wrapper.lg.java.LogAdapter;

public class AndroidLogAdapter implements LogAdapter {


    private int minLevel = Lg.VERBOSE;
    private String baseTag = "BMap";

    public AndroidLogAdapter setBaseTag(String baseTag) {
        this.baseTag = baseTag;
        return this;
    }

    public void setMinLevel(int minLevel) {
        this.minLevel = minLevel;
    }

//    @Override
//    public boolean isLoggable() {
//        boolean isAndroidLoggable = false;
//        try {
//            Class<?> cls = Class.forName("android.util.Log");
//            Method method = cls.getDeclaredMethod("isLoggable", String.class, int.class);
//            method.invoke(null, "", 2);
//            isAndroidLoggable = true;
//        } catch (Exception e) {
////            e.printStackTrace();
//        }
//        return isAndroidLoggable;
//    }

    private String createTag(String baseTag, String tag) {
        if (tag == null) {
            return String.valueOf(baseTag);
        }
        if (baseTag == null) {
            return tag;
        }
        return baseTag + "-" + tag;
    }


    @Override
    public void log(int level, String tag, String msg) {
        if (level < minLevel) {
            return;
        }
        tag = createTag(baseTag, tag);
        AndroidPrinter.INSTANCE.log(level, tag, msg);
    }
}

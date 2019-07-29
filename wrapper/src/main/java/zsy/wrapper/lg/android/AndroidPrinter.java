package zsy.wrapper.lg.android;

import android.util.Log;

import zsy.wrapper.lg.java.Lg;
import zsy.wrapper.lg.java.Printer;


public enum AndroidPrinter implements Printer {
    INSTANCE;

    @Override
    public boolean isPrefix() {
        return false;
    }

    @Override
    public void println(int level, String baseTag, String tag, String msg) {
        String tagInfo = tag == null ? baseTag : baseTag + "-" + tag;
        log(level, tagInfo, msg);
    }


    public void log(int level, String tag, String msg) {
        switch (level) {
            case Lg.VERBOSE:
                Log.v(tag, msg);
                break;
            case Lg.DEBUG:
                Log.d(tag, msg);
                break;
            case Lg.INFO:
                Log.i(tag, msg);
                break;
            case Lg.WARN:
                Log.w(tag, msg);
                break;
            case Lg.ERROR:
                Log.e(tag, msg);
                break;
        }
    }
}

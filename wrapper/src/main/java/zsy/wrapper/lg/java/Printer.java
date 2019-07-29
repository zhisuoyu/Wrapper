package zsy.wrapper.lg.java;

public interface Printer {

    boolean isPrefix();

    void println(int level, String baseTag, String tag, String msg);
}

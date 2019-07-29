package zsy.wrapper.lg.java;

public class SystemLogAdapter implements LogAdapter {


    @Override
    public void log(int level, String tag, String msg) {
        msg = createMsg(tag, msg);
        SystemPrinter.INSTACNE.println(level, msg);
    }

    private String createMsg(String tag, String msg) {
        return tag + " : " + msg;
    }
}

package zsy.wrapper.lg.java;

public enum SystemPrinter implements Printer {

    INSTACNE;

    @Override
    public boolean isPrefix() {
        return true;
    }

    @Override
    public void println(int level, String baseTag, String tag, String msg) {
//        if (!isPrefix()) {
//            tag = baseTag + "-" + tag;
//            msg = tag + " : " + msg;
//        }
        println(level, msg);
    }

    public void println(int level, String msg) {
        if (level <= Lg.INFO) {
            System.out.println(msg);
        } else {
            System.err.println(msg);
        }
    }
}

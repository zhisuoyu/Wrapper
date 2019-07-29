package zsy.wrapper.lg.java;

import java.text.SimpleDateFormat;
import java.util.Locale;

public class LogFormat implements ILogFormat {

    public static final SimpleDateFormat SDF = new SimpleDateFormat("MM-dd HH:mm:ss sss", Locale.CHINA);
    private static final char TOP_LEFT_CORNER = '┌';
    private static final char BOTTOM_LEFT_CORNER = '└';
    private static final char MIDDLE_CORNER = '├';
    private static final char HORIZONTAL_LINE = '│';
    private static final String TAB = " ";
    private static final String DOUBLE_DIVIDER = "────────────────────────────────────────────────────────";
    private static final String SINGLE_DIVIDER = "┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄";
    private static final String MSG_PREFIX = HORIZONTAL_LINE + TAB;
    private static final String TOP_BORDER = TOP_LEFT_CORNER + DOUBLE_DIVIDER + DOUBLE_DIVIDER;
    private static final String BOTTOM_BORDER = BOTTOM_LEFT_CORNER + DOUBLE_DIVIDER + DOUBLE_DIVIDER;
    private static final String MIDDLE_BORDER = MIDDLE_CORNER + SINGLE_DIVIDER + SINGLE_DIVIDER;



    private int minLevel;
    private boolean showDesInfo;
    private boolean showMethodTrace;
    private int methodOffset;
    private int methodCount;
    private String baseTag;
    private Printer printer;


    private LogFormat(Builder builder) {
        this.minLevel = builder.minLevel;
        this.showDesInfo = builder.showDesInfo;
        this.showMethodTrace = builder.showMethodTrace;
        this.methodOffset = builder.methodOffset;
        this.methodCount = builder.methodCount;
        this.baseTag = builder.baseTag;
        this.printer = builder.printer;
    }

//    protected String assemble() {
//        StringBuilder sb = new StringBuilder();
//        final String prefix = prefix();
//        sb.append(prefix)
//                .append(TOP_BORDER)
//                .append(BK);
//        if (showDesInfo) {
//            sb.append(assembleDes(prefix));
//        }
//
//        if (showMethodTrace) {
//            sb.append(assembleMethodTrace(prefix));
//        }
//
//        sb.append(prefix).append(BOTTOM_BORDER);
//        return sb.toString();
//    }

//    private String assembleDes(String prefix) {
//        return prefix +
//                des() +
//                BK +
//                prefix +
//                MIDDLE_BORDER +
//                BK;
//    }
//
//    private String assembleMethodTrace(String prefix) {
//        StringBuilder sb = new StringBuilder();
//        StackTraceElement[] traces = new Throwable().getStackTrace();
//        int end = Math.max(methodOffset + methodCount, traces.length);
//        for (int i = methodOffset; i < end; i++) {
//            sb.append(prefix)
//                    .append(traces[i])
//                    .append(BK);
//        }
//        sb.append(prefix)
//                .append(MIDDLE_BORDER)
//                .append(BK);
//        return sb.toString();
//    }
//
//    private String assembleMsg(String prefix, String msg) {
//        StringBuilder sb = new StringBuilder(msg);
//        int size = sb.length() / MAX_LINE_LEN;
//
//        for (int i = size; i >= 0; i--) {
//            sb.insert(i * MAX_LINE_LEN, BK + prefix + HORIZONTAL_LINE);
//        }
//        return sb.toString();
//    }


    private String timeInfo() {
        return SDF.format(System.currentTimeMillis());
    }


    private String threadInfo() {
        Thread t = Thread.currentThread();
        return t.getName() + "(" + t.getId() + ")";
    }

    private String prefix(String tag) {
        String tagInfo = tag == null ? baseTag : baseTag + "-" + tag;
        return timeInfo() +  " /" + tagInfo + " : ";
    }

    @Override
    public synchronized void log(int level, String tag, String msg) {
        if (!checkLevel(level)) {
            return;
        }
        String prefix = printer.isPrefix() ? prefix(tag) : "";
        printer.println(level, baseTag, tag, prefix + TOP_BORDER);
        if (showDesInfo) {
            printer.println(level, baseTag, tag, prefix + MSG_PREFIX + threadInfo());
            printer.println(level, baseTag, tag, prefix + MIDDLE_BORDER);
        }

        if (showMethodTrace) {
//            StackTraceElement[] traces = new Throwable().getStackTrace();
            StackTraceElement[] traces = Thread.currentThread().getStackTrace();
            int end = Math.min(methodOffset + methodCount, traces.length);
            for (int i = methodOffset; i < end; i++) {
                printer.println(level, baseTag, tag, prefix + MSG_PREFIX + "\tat " + traces[i]);
            }
            printer.println(level, baseTag, tag, prefix + MIDDLE_BORDER);
        }

//        StringBuilder sb = new StringBuilder(msg);
        printer.println(level, baseTag, tag, prefix + MSG_PREFIX + msg);

        printer.println(level, baseTag, tag, prefix + BOTTOM_BORDER);

    }


    private boolean checkLevel(int level) {
        return level >= minLevel;
    }

    public static class Builder {
        private int minLevel = Lg.VERBOSE;
        private boolean showDesInfo = true;
        private boolean showMethodTrace = true;
        private int methodOffset = 3;
        private int methodCount = 3;
        private String baseTag = "ZSY";
        private Printer printer = SystemPrinter.INSTACNE;


        public Builder setMinLevel(int minLevel) {
            this.minLevel = minLevel;
            return this;
        }

        public Builder setShowDesInfo(boolean showDesInfo) {
            this.showDesInfo = showDesInfo;
            return this;
        }

        public Builder setShowMethodTrace(boolean showMethodTrace) {
            this.showMethodTrace = showMethodTrace;
            return this;
        }

        public Builder setMethodOffset(int methodOffset) {
            this.methodOffset = methodOffset;
            return this;
        }

        public Builder setMethodCount(int methodCount) {
            this.methodCount = methodCount;
            return this;
        }

        public Builder setBaseTag(String baseTag) {
            this.baseTag = baseTag;
            return this;
        }

        public Builder setPrinter(Printer printer) {
            this.printer = printer;
            return this;
        }

        public LogFormat build() {
            return new LogFormat(this);
        }
    }
}

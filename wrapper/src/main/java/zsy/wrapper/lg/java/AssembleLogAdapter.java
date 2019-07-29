package zsy.wrapper.lg.java;

public class AssembleLogAdapter implements LogAdapter {


    private ILogFormat iLogFormat;

    public AssembleLogAdapter(ILogFormat iLogFormat) {
        this.iLogFormat = iLogFormat;
    }


    @Override
    public void log(int level, String tag, String msg) {
        iLogFormat.log(level, tag, msg);
    }


}

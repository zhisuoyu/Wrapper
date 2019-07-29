package zsy.wrapper.nav;

public abstract class NavListener {

    private int pagePosition = -1;
    private int tabPosition = -1;


    public void init(int position) {
        pagePosition = position;
        tabPosition = position;
    }

    public void onPageSelected(int position) {
        pagePosition = position;
        if (tabPosition != position) {
            selectTab(position);
        }
    }

    public void onTabSelected(int position) {
        tabPosition = position;
        if (pagePosition != position) {
            selectPage(position);
        }
    }

    protected abstract void selectPage(int position);

    protected abstract void selectTab(int position);


}

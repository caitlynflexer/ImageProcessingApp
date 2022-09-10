public class FilterPanel extends Panel {

    public FilterPanel(int _x, int _y, int _w, int _h, int _identifier, String _imageName) {
        super(_x, _y, _w, _h, _identifier, _imageName);
        createTimer(2000);
        applyFilter();
    }

    public void onTimer() {
        reloadImage();
        applyFilter();
    }

    public void applyFilter() {

        int[] filters = {Main.app.THRESHOLD, Main.app.GRAY, Main.app.INVERT, Main.app.POSTERIZE, Main.app.BLUR, Main.app.ERODE, Main.app.DILATE, Main.app.OPAQUE};
        String[] filterNames = {"Threshold", "Gray", "Invert", "Posterized", "Blur", "Erode", "Dilate", "Opaque"};
        float[] filterArgs = {(float)0.5, 0, 0, 4, 6, 0, 0, 0};

        // generate random filter
        int filterIndex = randomInt(0, filters.length);

        int filter = filters[filterIndex];
        float arg = filterArgs[filterIndex];

        if (arg == 0) {
            getImage().filter(filter);
        } else {
            getImage().filter(filter, arg);
        }

        setPanelName("Filter: " + filterNames[filterIndex]);
    }
}
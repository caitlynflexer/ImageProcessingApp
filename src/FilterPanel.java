import processing.core.PApplet;

public class FilterPanel extends Panel {

    public FilterPanel(PApplet _pApplet, int _x, int _y, int _w, int _h, int _identifier) {
        super(_pApplet, _x, _y, _w, _h, _identifier);
        createTimer(2000);
    }

    public void setupImage() {
        super.setupImage();
        applyFilter();
    }

    public void onTimer() {
        setupImage();
    }

    public void applyFilter() {

        int[] filters = {getPApplet().THRESHOLD, getPApplet().GRAY, getPApplet().INVERT, getPApplet().POSTERIZE, getPApplet().BLUR, getPApplet().ERODE, getPApplet().DILATE, getPApplet().OPAQUE};
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
import processing.core.PImage;

public abstract class ImageWrapper {
    private int _x;
    private int _y;
    private int _w;
    private int _h;
    private String _imageName;

    ImageWrapper(int x, int y, int w, int h, String imageName) {
        _x = x;
        _y = y;
        _w = w;
        _h = h;
        _imageName = imageName;

    }

    public int getX() {
        return _x;
    }

    public int getY() {
        return _y;
    }

    public void setX(int _x) {
        _x = _x;
    }

    public void setY(int _y) {
        _y = _y;
    }

    public int getWidth() {
        return _w;
    }

    public int getHeight() {
        return _h;
    }

    public String getImageName() {
        return _imageName;
    }

}

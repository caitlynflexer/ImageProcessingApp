import processing.core.PApplet;

public class AlphaModification extends Panel {

    int alpha;
    int dAlpha;

    public AlphaModification(int _x, int _y, int _w, int _h, int _identifier, String _imageName) {
        super(_x, _y, _w, _h, _identifier, _imageName);

        if (getX() == 0) {
            setPanelName("Transparency");
        }

        alpha = 100;
        dAlpha = -1;
    }

    public void display() {
        Main.app.tint(255, alpha); // Apply transparency without changing color
        super.display();
        Main.app.noTint();

        if (getX() == 0) {
            int percent = alpha * 100 / 255;
            setPanelName("Transparency: " + percent + "%");
        }

        alpha += dAlpha;
        if (alpha <= 0 || alpha >= 255) {
            dAlpha *= -1;
        }

        displayPanelName();
    }
}


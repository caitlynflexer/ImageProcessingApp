import processing.core.PApplet;

public class AlphaModification extends Panel {

    int alpha;
    int dAlpha;

    public AlphaModification(PApplet _pApplet, int _x, int _y, int _w, int _h, int _identifier) {
        super(_pApplet, _x, _y, _w, _h, _identifier);

        if (getX() == 0) {
            setPanelName("Transparency");
        }

        alpha = 100;
        dAlpha = -1;
    }

    public void display() {
        getPApplet().tint(255, alpha); // Apply transparency without changing color
        super.display();
        getPApplet().noTint();

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


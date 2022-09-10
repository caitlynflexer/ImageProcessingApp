public class TintedPanel extends Panel {

    float hue;
    float saturation;
    float lightness;
    float dHue;

    public TintedPanel(int _x, int _y, int _w, int _h, int _identifier, String _imageName) {
        super(_x, _y, _w, _h, _identifier, _imageName);

        hue = (float)randomDouble(0, 1);
        saturation = (float)randomDouble(0.5, 1);
        lightness = (float)randomDouble(0.4, 0.8);
        dHue = (float) 0.005;

        if (_x == 0) {
            setPanelName("Tinted (random rgb)");
        }
    }

    public void display() {

        if (getX() == 0) {
            Main.app.tint(randomInt(0, 256), randomInt(0, 256), randomInt(0, 256), randomInt(200, 256));
        } else {
            int[] rgb = hslToRgb(hue, saturation, lightness);
            Main.app.tint(rgb[0], rgb[1], rgb[2], 255);

            hue += dHue;
            if (hue >= 1 || hue <= 0) {
                dHue *= -1;
            }
            setPanelName("HSL: " + String.format("%.02f", hue) + ", " + String.format("%.02f", saturation) + ", " + String.format("%.02f", lightness));
        }

        super.display();    // or image(getImage(), getX(), getY(), getWidth(), getHeight());
        Main.app.noTint();
    }


    //hsl to rgb conversion from: https://stackoverflow.com/questions/2353211/hsl-to-rgb-color-conversion
    public static int[] hslToRgb(float h, float s, float l) {
        float r, g, b;

        if (s == 0f) {
            r = g = b = l; // achromatic
        } else {
            float q = l < 0.5f ? l * (1 + s) : l + s - l * s;
            float p = 2 * l - q;
            r = hueToRgb(p, q, h + 1f/3f);
            g = hueToRgb(p, q, h);
            b = hueToRgb(p, q, h - 1f/3f);
        }
        int[] rgb = {to255(r), to255(g), to255(b)};
        return rgb;
    }

    public static int to255(float v) {
        return (int)Math.min(255, 256*v);
    }

    /** Helper method that converts hue to rgb */
    public static float hueToRgb(float p, float q, float t) {
        if (t < 0f)
            t += 1f;
        if (t > 1f)
            t -= 1f;
        if (t < 1f/6f)
            return p + (q - p) * 6f * t;
        if (t < 1f/2f)
            return q;
        if (t < 2f/3f)
            return p + (q - p) * (2f/3f - t) * 6f;
        return p;
    }
}
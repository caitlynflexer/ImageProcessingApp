import java.util.Timer;
import java.util.TimerTask;

class PanelTimerTask extends TimerTask {

    Panel panel;

    PanelTimerTask(Panel _panel) {
        panel = _panel;
    }

    public void run() {
        panel.onTimer();
    }
}

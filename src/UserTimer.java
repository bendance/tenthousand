import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer;

// Starts the timer for the user
public class UserTimer
{
    private boolean timerOn;

    /**
     * @return whether or not the timer on
     */
    public boolean isTimerOn()
    {
        return timerOn;
    }

    /**
     * @param timerOn whether or not the timer on
     */
    public void setTimerOn(boolean timerOn)
    {
        this.timerOn = timerOn;
    }

    /**
     * Runs the timer for user
     */
    public void runTimer()
    {
        ScreenComponents screenComponents = new ScreenComponents();

        if (timerOn)
        {
            Timer timer = new Timer(1000, new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e)
                {
                    String currentTime = screenComponents.getCurrentSessionTime().getText();
                    int currentSeconds = Integer.parseInt(currentTime.substring(12, 14));
                    currentSeconds++;

                    // Set current time to new value
                    String newTime = String.format("<html>00:00:%02d</html>", currentSeconds);

                    screenComponents.getCurrentSessionTime().setText(newTime);
                    System.out.println(currentSeconds);
                }
            });
            timer.start();
        }
    }
}

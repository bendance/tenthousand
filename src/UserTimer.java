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
        if (timerOn)
        {
            System.out.println("i work too");
            Timer timer = new Timer(1000, new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    System.out.println("hello");
                }
            });
            timer.start();
        }
    }
}

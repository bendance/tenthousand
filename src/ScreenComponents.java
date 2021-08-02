import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Panels for JFrame
 */
public class ScreenComponents extends JPanel
{
    private JPanel inputPanel = new JPanel();
    private JPanel timePanel = new JPanel();
    private JLabel currentSessionTime;
    private Timer timer;

    private boolean timerOn;

    public ScreenComponents()
    {
        setLayout(new BorderLayout());

        firstScreenComponents();
        secondScreenComponents();

        // create a separator
        JSeparator s = new JSeparator(SwingConstants.VERTICAL);

        setPreferredSize(new Dimension(600, 300));

        add(inputPanel, BorderLayout.LINE_START);
        add(s, BorderLayout.CENTER);
        add(timePanel, BorderLayout.LINE_END);
    }

    public JPanel getInputPanel()
    {
        return inputPanel;
    }

    private void firstScreenComponents()
    {
        // The first half of the screen
        inputPanel.setLayout(null);
        inputPanel.setPreferredSize(new Dimension(400, 300));

        // JLabels for first half of screen
        JLabel currentSession = new JLabel();
        currentSession.setFont(new Font("Calibri", Font.BOLD, 25));
        currentSession.setSize(200, 200);
        currentSession.setLocation(100, -25);
        currentSession.setHorizontalAlignment(JLabel.CENTER);
        currentSession.setText("<html>CURRENT SESSION:</html>");
        inputPanel.add(currentSession);

        currentSessionTime = new JLabel();
        currentSessionTime.setFont(new Font("Calibri", Font.PLAIN, 20));
        currentSessionTime.setSize(200, 200);
        currentSessionTime.setLocation(100, 25);
        currentSessionTime.setHorizontalAlignment(JLabel.CENTER);
        currentSessionTime.setText("<html>00:00:00</html>");
        inputPanel.add(currentSessionTime);

        // Buttons for first half of screen
        JButton startButton = new JButton("Start");
        startButton.setSize(100, 20);
        startButton.setLocation(25,200);
        startButton.addActionListener(e ->
        {
            runTimer();
        });
        inputPanel.add(startButton);

        JButton stopButton = new JButton("Stop");
        stopButton.setSize(100, 20);
        stopButton.setLocation(150,200);
        stopButton.addActionListener(e ->
        {
            timer.stop();
        });
        inputPanel.add(stopButton);

        JButton resetButton = new JButton("Reset");
        resetButton.setSize(100, 20);
        resetButton.setLocation(275,200);
        resetButton.addActionListener(e ->
        {
            String defaultTime = "<html>00:00:00</html>";
            currentSessionTime.setText(defaultTime);
        });
        inputPanel.add(resetButton);
    }

    private void secondScreenComponents()
    {
        timePanel.setLayout(null);
        timePanel.setPreferredSize(new Dimension(199, 300));

        // JLabels for second half
        JLabel hours = new JLabel();
        hours.setText("<html>HOURS:</html>");
        hours.setFont(new Font("Calibri", Font.BOLD, 25));
        hours.setSize(200, 200);
        hours.setLocation(0,-25);
        hours.setHorizontalAlignment(JLabel.CENTER);
        timePanel.add(hours);

        JLabel hoursSpent = new JLabel();
        hoursSpent.setText("<html>10,000 hours</html>");
        hoursSpent.setFont(new Font("Calibri", Font.PLAIN, 20));
        hoursSpent.setSize(200,200);
        hoursSpent.setLocation(0, 25);
        hoursSpent.setHorizontalAlignment(JLabel.CENTER);
        timePanel.add(hoursSpent);
    }

    /**
     * Runs the timer for user
     */
    public void runTimer()
    {
        ScreenComponents screenComponents = new ScreenComponents();

        timer = new Timer(1000, e ->
        {
            String currentTime = currentSessionTime.getText();
            int currentSeconds = Integer.parseInt(currentTime.substring(12, 14));
            int currentMinutes = Integer.parseInt(currentTime.substring(9, 11));
            int currentHours = Integer.parseInt(currentTime.substring(6, 8));

            // Increase the seconds
            currentSeconds++;

            // Reset seconds when seconds equals 60
            if (currentSeconds == 60)
            {
                currentSeconds = 0;
                currentMinutes++;
            }

            // Reset minutes when minutes equals 60
            if (currentMinutes == 60)
            {
                currentMinutes = 0;
                currentHours++;
            }

            // Set current time to new value
            String newTime = String.format("<html>%02d:%02d:%02d</html>", currentHours, currentMinutes, currentSeconds);
            currentSessionTime.setText(newTime);
            System.out.println(newTime);
        });
        timer.start();
    }
}

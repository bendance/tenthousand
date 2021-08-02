import javax.swing.*;
import java.awt.*;

/**
 * Panels for JFrame
 */
public class ScreenComponents extends JPanel
{
    private JPanel inputPanel = new JPanel();
    private JPanel timePanel = new JPanel();

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

    private void firstScreenComponents()
    {
        // The first half of the screen
        inputPanel.setLayout(null);
        inputPanel.setPreferredSize(new Dimension(400, 300));

        // Buttons for first half of screen
        JButton startButton = new JButton("Start");
        startButton.setSize(100, 20);
        startButton.setLocation(25,200);
        inputPanel.add(startButton);

        JButton stopButton = new JButton("Stop");
        stopButton.setSize(100, 20);
        stopButton.setLocation(150,200);
        inputPanel.add(stopButton);

        JButton resetButton = new JButton("Reset");
        resetButton.setSize(100, 20);
        resetButton.setLocation(275,200);
        inputPanel.add(resetButton);

        // JLabels for first half of screen
        JLabel currentSession = new JLabel();
        currentSession.setFont(new Font("Calibri", Font.BOLD, 25));
        currentSession.setSize(200, 200);
        currentSession.setLocation(100, -25);
        currentSession.setHorizontalAlignment(JLabel.CENTER);
        currentSession.setText("<html>CURRENT SESSION:</html>");
        inputPanel.add(currentSession);

        JLabel currentSessionTime = new JLabel();
        currentSessionTime.setFont(new Font("Calibri", Font.PLAIN, 20));
        currentSessionTime.setSize(200, 200);
        currentSessionTime.setLocation(100, 25);
        currentSessionTime.setHorizontalAlignment(JLabel.CENTER);
        currentSessionTime.setText("<html>00:00:00</html>");
        inputPanel.add(currentSessionTime);
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
}

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

/**
 * Panels for JFrame
 */
public class ScreenComponents extends JPanel
{
    private final JPanel inputPanel = new JPanel();
    private final JPanel timePanel = new JPanel();
    private JLabel currentSessionTime;
    private JLabel totalSessionTime;
    private Timer timer;

    private boolean timerStarted = false;
    private String totalTimeString;

    public ScreenComponents() throws FileNotFoundException
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
            // set the flag on
            timerStarted = true;
        });
        inputPanel.add(startButton);

        JButton stopButton = new JButton("Stop");
        stopButton.setSize(100, 20);
        stopButton.setLocation(150,200);
        stopButton.addActionListener(e ->
        {
            timer.stop();
            timerStarted = false;
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

    /**
     * Generates the components for the second half of the screen
     * @throws FileNotFoundException if hoursspent.txt is not found
     */
    private void secondScreenComponents() throws FileNotFoundException
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

        totalSessionTime = new JLabel();
        totalSessionTime.setText("<html>0 hours</html>");
        totalSessionTime.setFont(new Font("Calibri", Font.PLAIN, 20));
        totalSessionTime.setSize(200,200);
        totalSessionTime.setLocation(0, 25);
        totalSessionTime.setHorizontalAlignment(JLabel.CENTER);
        timePanel.add(totalSessionTime);

        // add reset button for total time
        JButton totalResetButton = new JButton("Reset Total");
        totalResetButton.setSize(100, 20);
        totalResetButton.setLocation(50,200);
        totalResetButton.addActionListener(e ->
        {
            try
            {
                confirmTotalReset();
            }
            catch (FileNotFoundException fileNotFoundException)
            {
                fileNotFoundException.printStackTrace();
            }
        });
        timePanel.add(totalResetButton);

        readNewTime();

        // set appropriate session label at beginning
        changeTotalSessionLabel();
    }

    /**
     * Runs the timer for user
     */
    public void runTimer()
    {
        if (!timerStarted)
        {
            timer = new Timer(1000, e ->
            {
                changeCurrentSessionLabel();
                try
                {
                    writeNewTime();
                }
                catch (FileNotFoundException fileNotFoundException)
                {
                    fileNotFoundException.printStackTrace();
                }

            });
            timer.start();
        }
    }

    /**
     * Changes the JLabel of the current session
     */
    public void changeCurrentSessionLabel()
    {
        String currentTime = currentSessionTime.getText();
        currentTime = currentTime.substring(6, currentTime.length() - 7);
        String[] timeArray = currentTime.split("[:]");
        int currentSeconds = Integer.parseInt(timeArray[2]);
        int currentMinutes = Integer.parseInt(timeArray[1]);
        int currentHours = Integer.parseInt(timeArray[0]);

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
        changeTotalSessionLabel();
    }

    /**
     * Changes the JLabel of the total session
     */
    public void changeTotalSessionLabel()
    {
        // If the total hours label and current total hours don't match, change it
        String[] totalTimeArray = totalTimeString.split("[:]");
        int currentTotalHours = Integer.parseInt(totalTimeArray[0]);

        // Integer value of what is currently displayed in total hours label
        totalSessionTime.setText(String.format("<html>%d hours</html>", currentTotalHours));
    }

    /**
     * Read the total number of hours the user has spent
     */
    public void readNewTime() throws FileNotFoundException
    {
        Scanner in = new Scanner(new File("src/hoursspent.txt"));

        // flag stating whether or not database has newline
        boolean hasTime = false;

        while(in.hasNextLine())
        {
            hasTime = true;
            totalTimeString = in.nextLine();
        }

        if (!hasTime)
        {
            totalTimeString = "00:00:00";
        }

        System.out.println(totalTimeString);
    }

    /**
     * Add the past time up to write the new time
     */
    public void writeNewTime() throws FileNotFoundException
    {
        // Split up total time and current time strings
        String[] totalTimeArray = totalTimeString.split("[:]");
        int currentTotalSeconds = Integer.parseInt(totalTimeArray[2]);
        int currentTotalMinutes = Integer.parseInt(totalTimeArray[1]);
        int currentTotalHours = Integer.parseInt(totalTimeArray[0]);

        int combinedSeconds = 1 + currentTotalSeconds;
        int combinedMinutes = currentTotalMinutes;
        int combinedHours = currentTotalHours;

        // change int values when limits are reached
        if (combinedSeconds > 60)
        {
            combinedMinutes++;
            combinedSeconds -= 60;
        }

        if (combinedMinutes > 60)
        {
            combinedHours++;
            combinedMinutes -= 60;
        }

        PrintWriter out = new PrintWriter("src/hoursspent.txt");
        totalTimeString = String.format("%02d:%02d:%02d", combinedHours, combinedMinutes, combinedSeconds);
        out.println(totalTimeString);

        out.close();
    }

    /**
     * Asks user whether or not they would like to reset their total time
     */
    public void confirmTotalReset() throws FileNotFoundException
    {
        // Create an option pane
        Object[] options = {"Yes", "No"};

        int choice = JOptionPane.showOptionDialog(timePanel, "Are you sure you would like to reset your total" +
                "hours spent?", "Confirm Total Hours Reset", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null,
                options, options[1]);

        if (choice == 0)
        {
            // Reset the hoursspent.txt
            PrintWriter out = new PrintWriter("src/hoursspent.txt");
            out.println("00:00:00");
            out.close();

            totalTimeString = "00:00:00";
            System.out.println(totalTimeString);
            String defaultTime = "<html>00:00:00</html>";
            currentSessionTime.setText(defaultTime);
        }
    }
}

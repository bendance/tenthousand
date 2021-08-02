import javax.swing.*;
import java.awt.*;

/**
 * Components for JFrame
 */
public class ScreenComponents extends JPanel
{
    public ScreenComponents()
    {
        // The first half of the screen
        JPanel userInputPanel = new JPanel();
        userInputPanel.setLayout(null);

        JButton startButton = new JButton("Start");
        startButton.setSize(100, 20);
        userInputPanel.add(startButton);

        JButton l = new JButton("this is label 1");
        JLabel l1 = new JLabel("this is label 2");

        // create a separator
        JSeparator s = new JSeparator();

        // set layout as vertical
        s.setOrientation(SwingConstants.VERTICAL);

        setPreferredSize(new Dimension(600, 300));

        add(userInputPanel);
        add(s);
        add(l1);

        // set layout
        setLayout(new GridLayout(1,0));

    }

    public void paintComponent(Graphics g)
    {
        g.setColor(Color.black);
        g.drawString("hello", 50,50);
    }
}

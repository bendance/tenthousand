import javax.swing.*;
import java.io.FileNotFoundException;

/**
 * Used to display JFrame for user
 */
public class Screen
{
    /**
     * Displays a JFrame on the user's screen
     */
    public static void JFrame() throws FileNotFoundException
    {
        JFrame frame = new JFrame();
        frame.setTitle("Ten Thousand Hours");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // add the components to the screen
        ScreenComponents screenComponents = new ScreenComponents();
        frame.add(screenComponents);
        frame.setResizable(false);

        frame.pack();
        frame.setVisible(true);
    }

    // main method
    public static void main(String[] args) throws FileNotFoundException
    {
        // display JFrame here
        JFrame();
    }
}

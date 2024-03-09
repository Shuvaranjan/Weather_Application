import javax.swing.SwingUtilities;

import GUI.WeatherApp_Gui;

public class Test {
    public static void main(String[] args) throws Exception {
        // Display the GUI
        SwingUtilities.invokeLater(new Runnable() {

            @Override
            public void run() {
                // Display the output
                new WeatherApp_Gui().setVisible(true);
               
            }

        });
    }
}

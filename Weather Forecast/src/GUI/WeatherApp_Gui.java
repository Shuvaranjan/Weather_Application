package GUI;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.BevelBorder;

import org.json.simple.JSONObject;

import BACKEND.WeatherApp;

public class WeatherApp_Gui extends JFrame {

    private JSONObject weatherData;

    public WeatherApp_Gui() {

        // Set the title of GUI
        super("𝐖𝐞𝐚𝐭𝐡𝐞r 𝐀𝐩𝐩𝐥𝐢𝐜𝐚𝐭𝐢𝐨𝐧🌈™");
        // Set the size of Gui
        setSize(670, 800);

        // Set Layout
        setLayout(null);

        // Set Resizeable
        setResizable(false);

        // After launch set this Gui center
        setLocationRelativeTo(null);

        // when closed this Gui, it properly close
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        getContentPane().setBackground(new Color(21, 21, 21));

        GuiComponents();
        // otherComponents();

    }

    public void GuiComponents() {

        // Set GUI logo
        ImageIcon icon = new ImageIcon("Weather Forecast\\src\\--MEDIA--\\icons8-partly-cloudy-day-100.png");
        this.setIconImage(icon.getImage());

        // Create SearchField
        JTextField searchField = new JTextField();
        searchField.setBounds(30, 45, 500, 43);
        searchField.setForeground(Color.decode("#171717"));
        searchField.setBackground(Color.decode("#FBFBFB"));
        searchField.setBorder(BorderFactory.createLoweredBevelBorder());
        searchField.setFont(new Font("Arial Black", Font.PLAIN, 20));
        searchField.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseEntered(java.awt.event.MouseEvent e) {
                searchField.setBackground(Color.decode("#BFBFBF"));
            }

            @Override
            public void mouseExited(java.awt.event.MouseEvent e) {
                searchField.setBackground(Color.decode("#FBFBFB"));
            }

            @Override
            public void mouseClicked(java.awt.event.MouseEvent e) {
                searchField.setBorder(BorderFactory.createLineBorder(Color.RED, 3));

            }

        });

        // Weather Image
        JLabel weatherConditionImg = new JLabel(loadImage("Weather Forecast\\src\\--MEDIA--\\clouds.png"));
        weatherConditionImg.setBounds(180, 110, 300, 280);

        // Temperature text
        JLabel temperaturetext = new JLabel("21.1° C");
        temperaturetext.setBounds(255, 398, 180, 80);
        temperaturetext.setForeground(Color.decode("#00E6EC"));
        temperaturetext.setFont(new Font("Agency FB", Font.BOLD, 66));

        // weather
        JLabel weatherConditionText = new JLabel("Cloudy");
        weatherConditionText.setBounds(290, 455, 180, 90);
        weatherConditionText.setForeground(Color.decode("#BABABA"));
        weatherConditionText.setFont(new Font("Agency FB", Font.BOLD, 36));

        // Humidity
        JLabel humidityImg = new JLabel(loadImage("Weather Forecast\\src\\--MEDIA--\\humidity (2).png"));
        humidityImg.setBounds(18, 630, 100, 120);

        JLabel humidityText = new JLabel("Humidity");
        humidityText.setBounds(123, 630, 180, 80);
        humidityText.setForeground(Color.decode("#F6068C"));
        humidityText.setFont(new Font("Arial Black", Font.BOLD, 25));

        JLabel humidityText2 = new JLabel("85%");
        humidityText2.setBounds(125, 652, 180, 90);
        humidityText2.setForeground(Color.decode("#BABABA"));
        humidityText2.setFont(new Font("Agency FB", Font.BOLD, 20));

        // Wind Speed
        JLabel windSpeedImg = new JLabel(loadImage("Weather Forecast\\src\\--MEDIA--\\wind.png"));
        windSpeedImg.setBounds(290, 615, 180, 150);

        JLabel windSpeedText = new JLabel("Wind Speed");
        windSpeedText.setBounds(460, 630, 180, 80);
        windSpeedText.setForeground(Color.decode("#F6068C"));
        windSpeedText.setFont(new Font("Arial Black", Font.BOLD, 25));

        JLabel windSpeedText2 = new JLabel("9.2km/h");
        windSpeedText2.setBounds(462, 652, 180, 90);
        windSpeedText2.setForeground(Color.decode("#BABABA"));
        windSpeedText2.setFont(new Font("Agency FB", Font.BOLD, 20));

        // Create a Search Button
        JButton searchButton = new JButton(loadImage("Weather Forecast\\src\\--MEDIA--\\icons8-search-35.png"));
        searchButton.setBounds(555, 45, 70, 43);
        searchButton.setFont(new Font("Arial Black", Font.BOLD, 33));
        searchButton.setBackground(Color.decode("#EA1313"));
        searchButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        searchButton.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED, Color.WHITE, Color.darkGray));

        // add Hover effect
        searchButton.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseEntered(java.awt.event.MouseEvent e) {
                searchButton
                        .setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED, Color.WHITE, Color.darkGray));
            }

            @Override
            public void mouseExited(java.awt.event.MouseEvent e) {
                searchButton
                        .setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED, Color.WHITE, Color.darkGray));
            }

            @Override
            public void mouseReleased(java.awt.event.MouseEvent e) {
                searchField.setBorder(BorderFactory.createLoweredBevelBorder());

            }

        });
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // get location from user
                String userInput = searchField.getText();

                // validate input - remove whitespace to ensure non-empty text
                if (userInput.replaceAll("\\s", "").length() <= 0) {
                    return;
                }

                // retrieve weather data
                weatherData = WeatherApp.getWeatherData(userInput);
                searchField.setText(null);

                // update gui

                // update weather image
                String weatherCondition = (String) weatherData.get("weather_condition");

                // depending on the condition, we will update the weather image that corresponds
                // with the condition
                switch (weatherCondition) {
                    case "Clear":
                        weatherConditionImg.setIcon(loadImage("Weather Forecast\\src\\--MEDIA--\\sun (1).png"));
                        break;
                    case "Cloudy":
                        weatherConditionImg.setIcon(loadImage("Weather Forecast\\src\\--MEDIA--\\clouds.png"));
                        break;
                    case "Rain":
                        weatherConditionImg.setIcon(loadImage("Weather Forecast\\src\\--MEDIA--\\rainy-day.png"));
                        break;
                    case "Snow":
                        weatherConditionImg.setIcon(loadImage("Weather Forecast\\src\\--MEDIA--\\snow.png"));
                        break;
                    case "Fogg":
                        weatherConditionImg.setIcon(loadImage("Weather Forecast\\src\\--MEDIA--\\foggy.png"));
                        break;
                    case "Thunder-Storm":
                        weatherConditionImg.setIcon(loadImage("Weather Forecast\\src\\--MEDIA--\\thunderstorm.png"));
                        break;
                }

                // update temperature text
                double temperature = (double) weatherData.get("temperature");
                temperaturetext.setText(temperature + "° C");

                // update weather condition text
                weatherConditionText.setText(weatherCondition);

                // update humidity text
                long humidity = (long) weatherData.get("humidity");
                humidityText2.setText(humidity + "%");

                // update windspeed text
                double windspeed = (double) weatherData.get("windspeed");
                windSpeedText2.setText(windspeed + "km/h");
            }
        });

        JLabel textLabel = new JLabel("<html><u>Light Mode</u></html>");
        textLabel.setBounds(30, 0, 150, 50);
        textLabel.setFont(new Font("Dialog", Font.BOLD, 15));
        textLabel.setForeground(Color.decode("#EFEFEF"));
        textLabel.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseClicked(MouseEvent e) {
                getContentPane().setBackground(Color.decode("#EFEFEF"));
                searchField.setBorder(BorderFactory.createLoweredBevelBorder());
                temperaturetext.setForeground(Color.decode("#2B2B2B"));
                weatherConditionText.setForeground(Color.decode("#A6A5A5"));
                humidityText.setForeground(Color.decode("#2B2B2B"));
                windSpeedText.setForeground(Color.decode("#2B2B2B"));
                searchButton.setBackground(Color.decode("#150000"));
                searchButton.setIcon(loadImage("Weather Forecast\\src\\--MEDIA--\\icons8-search-35 (1).png"));
                humidityText2.setForeground(Color.decode("#A6A5A5"));
                windSpeedText2.setForeground(Color.decode("#A6A5A5"));
            }

        });

        JLabel textLabel2 = new JLabel("<html><u>Dark Mode</u></html>");
        textLabel2.setBounds(450, 0, 150, 50);
        textLabel2.setFont(new Font("Dialog", Font.BOLD, 15));
        textLabel2.setForeground(Color.decode("#151515"));
        textLabel2.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseClicked(MouseEvent e) {
                getContentPane().setBackground(new Color(21, 21, 21));
                searchField.setBorder(BorderFactory.createLoweredBevelBorder());
                temperaturetext.setForeground(Color.decode("#00E6EC"));
                weatherConditionText.setForeground(Color.decode("#BABABA"));
                humidityText.setForeground(Color.decode("#F6068C"));
                windSpeedText.setForeground(Color.decode("#F6068C"));
                searchButton.setBackground(Color.decode("#EA1313"));
                searchButton.setIcon(loadImage("Weather Forecast\\src\\--MEDIA--\\icons8-search-35.png"));
                humidityText2.setForeground(Color.decode("#BABABA"));
                windSpeedText2.setForeground(Color.decode("#BABABA"));
            }

        });

        add(textLabel);
        add(textLabel2);

        // add Components
        add(searchField);
        add(searchButton);
        add(weatherConditionImg);
        add(temperaturetext);
        add(weatherConditionText);
        add(humidityImg);
        add(humidityText);
        add(humidityText2);
        add(windSpeedImg);
        add(windSpeedText);
        add(windSpeedText2);

    }

    // used to create Images in our Gui Components
    private ImageIcon loadImage(String resourcePath) {
        try {
            // read the image file from the path given
            BufferedImage image = ImageIO.read(new File(resourcePath));

            // return an imageIcon so that our component can render it
            return new ImageIcon(image);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Could not load image !");
        return null;
    }

    public void otherComponents() {

    }

}

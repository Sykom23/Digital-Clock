import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class MainFrame implements ActionListener {
    private static final Color GRAY = new Color(220, 220, 220);
    private static final ImageIcon ICON = new ImageIcon("assets/clock_icon.png");
    private JPanel textPanel;
    private JFrame frame;
    private JTextField hourField, dateField;
    private JButton formatButton;
    private Time time;


    MainFrame() {
        //initialize objects
        time = new Time();
        formatButton = new JButton();
        hourField = new JTextField();
        dateField = new JTextField();
        frame = new JFrame();
        textPanel = new JPanel();


        configureComponents();
        addComponentsToFrame();


        //sets the intial date
        time.updateDate(dateField);

        //Start updating the horfield
        time.updateHourField(hourField, dateField);
    }

    private void addComponentsToFrame(){
        textPanel.add(hourField);
        textPanel.add(dateField);
        frame.add(textPanel);
        frame.add(formatButton);
        frame.setVisible(true);
    }
    private void configureComponents(){
        //configure frame and panel
        frameConfig();
        textPanelConfig();

        //configure date and hour fields receives coordinates x, y, width and height and the JTextField to configure
        textFieldConfig((textPanel.getWidth()/2)- 250, 50, 550, 100, hourField);
        textFieldConfig((textPanel.getWidth()/2) - 200, 200, 450 , 100, dateField);
        textFieldSetFont(hourField, 80);
        textFieldSetFont(dateField, 40);

        //configure the formatButton
        jButtonConfig();
    }
    private void jButtonConfig(){
        formatButton.setBounds(400, 300, 200, 50);
        formatButton.setText("Change hour format");
        formatButton.addActionListener(this);
    }
    private void textFieldSetFont(JTextField field, int size){
        field.setFont(new Font("Arial", Font.BOLD, size));
    }

    private void textPanelConfig() {
        textPanel.setBackground(GRAY);
        textPanel.setLayout(null);
        textPanel.setBounds(0, 0, 1000, 300);
    }
    private void textFieldConfig(int x,int y,int width,int height, JTextField field) {
        field.setBounds(x,y,width,height);
        field.setEditable(false);
        field.setBorder(BorderFactory.createEmptyBorder());
        field.setBackground(GRAY);
    }
    private void frameConfig() {
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);
        frame.setLocationRelativeTo(null);
        frame.setSize(1000, 500);
        frame.setIconImage(ICON.getImage());
        frame.setTitle("Digital clock");
        frame.setResizable(false);
        frame.getContentPane().setBackground(GRAY);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == formatButton){
            //toggle the hour format when formatButton is clicked
            time.setTwelveHourFormat(!time.getTwelveHourFormat());
        }
    }
}

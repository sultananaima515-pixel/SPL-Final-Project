package Main;

import sound.SoundManager;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class FrontPage extends JPanel {
    private JLabel titleLabel;
    private JLabel subHeader;
    private JLabel subTitle;
    private JButton startGame;
    private ImageIcon crown;

    public FrontPage(MainUI mainUI) {
        setLayout(new BorderLayout());
        setBackground(new Color(94,54,23));
        setBorder(new EmptyBorder(25,20,25,20));


//        top Section for Icon
        JPanel topPanel = new JPanel();
        topPanel.setOpaque(false);
        topPanel.setLayout(new BoxLayout(topPanel, BoxLayout.Y_AXIS));
        crown = new ImageIcon(getClass().getResource("crown.png"));

        JLabel crownTop= new JLabel();
        crownTop.setIcon(crown);
        crownTop.setAlignmentX(Component.CENTER_ALIGNMENT);
        topPanel.add(crownTop);


//        title Label
        titleLabel = new JLabel("ULTIMATE ");
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        titleLabel.setFont(new Font("Serif", Font.BOLD, 50));
        titleLabel.setForeground(new Color(243,220,170));

        subHeader = new JLabel("TIC TAC TOE");
        subHeader.setAlignmentX(Component.CENTER_ALIGNMENT);
        subHeader.setFont(new Font("Serif", Font.ITALIC, 30));
        subHeader.setForeground(new Color(220,190,140));


//Subtitle label
        subTitle = new JLabel("Classic * Ultimate * Championship Edition");
        subTitle.setAlignmentX(Component.CENTER_ALIGNMENT);
        subTitle.setFont(new Font("Serif", Font.ITALIC, 20));
        subTitle.setForeground(new Color(220,190,140));

        topPanel.add(Box.createVerticalGlue());
        topPanel.setBorder(new EmptyBorder(80,0,0,0));
        topPanel.add(titleLabel);

        topPanel.add(Box.createVerticalGlue());
        topPanel.add(subHeader);

        topPanel.add(Box.createVerticalGlue());
        topPanel.add(Box.createVerticalStrut(10));
        topPanel.add(subTitle);

        add(topPanel, BorderLayout.NORTH);

        JPanel centerPanel = new JPanel();
        centerPanel.setOpaque(false);
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));
        centerPanel.add(Box.createVerticalStrut(100));

        startGame = new JButton("START GAME");
        startGame.setAlignmentX(Component.CENTER_ALIGNMENT);
        startGame.setBackground(new Color(245,180,60));
        startGame.setForeground(new Color(50,30,10));
        startGame.setPreferredSize(new Dimension(300,60));
        startGame.setMaximumSize(new Dimension(300,60));

        startGame.setFont(new Font("Serif", Font.BOLD, 24));

        startGame.setFocusPainted(false);

        startGame.setCursor(
                Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        startGame.addActionListener(e -> {
            SoundManager.playSound("click.wav");
            mainUI.showMenu();
        });

        centerPanel.add(startGame);
        add(centerPanel, BorderLayout.CENTER);



        JPanel bottom = new JPanel(new FlowLayout(FlowLayout.CENTER,10,0));
        bottom.setOpaque(false);
        bottom.add(Box.createVerticalStrut(180));

        JButton settings = new JButton("Setting");
        settings.setPreferredSize(new Dimension(140,38));
        settings.setFocusPainted(false);
        settings.setBackground(new Color(90,50,20));
        settings.setForeground(new Color(243,220,170));
        settings.setFont(new Font("Serif", Font.BOLD, 16));


        settings.setBorder(BorderFactory.createLineBorder(new Color(243,220,170),2));
        bottom.add(Box.createRigidArea(new Dimension(0,80)));
        settings.addActionListener(e -> {
            SoundManager.playSound("click.wav");

            Frame owner = (Frame) SwingUtilities.getWindowAncestor(this);
            SettingsDialog dialog = new SettingsDialog(owner);
            dialog.setVisible(true);
        });
        bottom.add(settings);

        JButton Exitbtn = new JButton("Exit Game");
        Exitbtn.setPreferredSize(new Dimension(140,38));
        Exitbtn.setFocusPainted(false);
        Exitbtn.setBackground(new Color(90,50,20));
        Exitbtn.setForeground(new Color(243,220,170));
        Exitbtn.setFont(new Font("Serif", Font.BOLD, 16));

        Exitbtn.setBorder(BorderFactory.createLineBorder(new Color(243,220,170),2));
        bottom.add(Box.createRigidArea(new Dimension(80,0)));
        bottom.add(Exitbtn);
        Exitbtn.addActionListener(e -> {

            SoundManager.playSound("click.wav");

            int option = JOptionPane.showConfirmDialog(
                    this,
                    "Are you sure you want to exit the game?",
                    "Exit Game",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.QUESTION_MESSAGE);

            if (option == JOptionPane.YES_OPTION) {

                SoundManager.stopBackgroundMusic();   // if you have this method
                System.exit(0);
            }
        });
        add(bottom,BorderLayout.SOUTH);

        SoundManager.playBackgroundMusic("ambient.wav");


    }

}

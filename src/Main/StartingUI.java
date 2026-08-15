package Main;

import sound.SoundManager;

import java.awt.*;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

public  class StartingUI extends JPanel {

    private JLabel titleLabel,subtitleLabel;

    public StartingUI(MainUI parent)  {

        setLayout(new BorderLayout());
        setBackground(new Color(94,54,23));
        setBorder(new EmptyBorder(25,20,25,20));

//        top Section for Title
        JPanel topPanel = new JPanel();
        topPanel.setOpaque(false);
        topPanel.setLayout(new BoxLayout(topPanel, BoxLayout.Y_AXIS));

//        title Label
        titleLabel = new JLabel("Customized Your Name");
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        titleLabel.setFont(new Font("Serif", Font.BOLD, 25));
        titleLabel.setForeground(new Color(243,220,170));

        topPanel.add(Box.createRigidArea(new Dimension(0,8))); //gap between title and subtitle
        topPanel.add(titleLabel);



//        name input panel
        JPanel namePanel = new JPanel(new GridLayout(1,2,30,0));
        namePanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        namePanel.setOpaque(false);

//player x column
        JPanel xPanel = new JPanel();
        xPanel.setLayout(new BoxLayout(xPanel, BoxLayout.Y_AXIS));
        xPanel.setOpaque(false);

//textfield for the player x
        JLabel xLabel = new JLabel("Player X Name");
        xLabel.setForeground(new Color(243,220,170));
        xLabel.setFont(new Font("Serif", Font.BOLD, 18));

        JTextField playerXField = new JTextField();
        playerXField.setPreferredSize(new Dimension(300,40));
        playerXField.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(140,95,55),2,true),
                BorderFactory.createEmptyBorder(8,12,8,12)
        ));

        playerXField.setForeground(new Color(243,220,170));
        playerXField.setOpaque(false);

        xPanel.add(xLabel);
        xPanel.add(Box.createRigidArea(new Dimension(0,8)));
        xPanel.add(playerXField);

        JPanel oPanel = new JPanel();
        oPanel.setLayout(new BoxLayout(oPanel, BoxLayout.Y_AXIS));
        oPanel.setOpaque(false);

        JLabel oLabel = new JLabel("Player O Name");
        oLabel.setForeground(new Color(243,220,170));
        oLabel.setFont(new Font("Serif", Font.BOLD, 18));

        JTextField playerOField = new JTextField();
        playerOField.setPreferredSize(new Dimension(300,40));
        playerOField.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(140,95,55),2,true),
                BorderFactory.createEmptyBorder(8,12,8,12)
        ));
        playerOField.setForeground(new Color(243,220,170));
        playerOField.setOpaque(false);

        addPlaceholder(playerXField,"Player X");
        addPlaceholder(playerOField,"Player O");

        oPanel.add(oLabel);
        oPanel.add(Box.createRigidArea(new Dimension(0,8)));
        oPanel.add(playerOField);

        namePanel.add(xPanel);
        namePanel.add(oPanel);

        topPanel.add(Box.createRigidArea(new Dimension(0,25)));
        topPanel.add(namePanel);

        add(topPanel, BorderLayout.NORTH);


        JPanel gamePanel = new JPanel();
        gamePanel.setOpaque(false);
        gamePanel.setLayout(new BorderLayout());

        subtitleLabel = new JLabel("Select Your Grid");
        subtitleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        subtitleLabel.setFont(new Font("Serif", Font.BOLD, 25));
        subtitleLabel.setForeground(new Color(243,220,170));

        JPanel middlePanel = new JPanel();
        middlePanel.setOpaque(false);
        middlePanel.add(subtitleLabel);

        gamePanel.add(middlePanel, BorderLayout.NORTH);


//       panel for 4 games
        JPanel centerPanel = new JPanel(new GridLayout(2,2,25,25));
        centerPanel.setOpaque(false);
        centerPanel.setBorder(new EmptyBorder(25,20,25,20));

//        card0 : classic tic-tac-toe
        centerPanel.add(createGameCard(
                "BEGINNER",
                "CLASSIC 3x3",
                "Normal Tic Tac Toe",
                () -> {

                    parent.getGamePanel0().setPlayerName(
                            playerXField.getText(),
                            playerOField.getText()
                    );

                    parent.showGame0();
                }
        ));



//     Card 1: ultimate 3x3
        centerPanel.add(createGameCard(
                "INTERMEDIATE",
                "ULTIMATE 3x3",
                "9 boards within a board - The strategic masterpiece",
                () -> {

                    parent.getGamePanel().setPlayerNames(
                            playerXField.getText(),
                            playerOField.getText()
                    );

                    parent.showGame();
                }
        ));


//        card2: ultimate 4x4
        centerPanel.add(createGameCard(
                "ADVANCED",
                "ULTIMATE 4x4",
                "16 boards within a board - The strategic masterpiece",
                () -> {

                    parent.getGamePanel2().setPlayerNames(
                            playerXField.getText(),
                            playerOField.getText()
                    );

                    parent.showGame2();
                }
        ));

//    card3 : ultimate 5x5
        centerPanel.add(createGameCard(
                "CHAMPIONSHIP",
                "ULTIMATE 5x5",
                "25 boards within a board - The strategic time mode masterpiece",
                () -> {

                    parent.getGamePanel3().setPlayerNames(
                            playerXField.getText(),
                            playerOField.getText()
                    );

                    parent.showGame3();
                }
        ));

        gamePanel.add(centerPanel, BorderLayout.CENTER);
        add(gamePanel, BorderLayout.CENTER);


        JPanel bottom = new JPanel(new FlowLayout(FlowLayout.CENTER,10,0));
        bottom.setOpaque(false);

        JButton BackButton = new JButton("Back");
        BackButton.setPreferredSize(new Dimension(140,38));
        BackButton.setFocusPainted(false);
        BackButton.setBackground(new Color(90,50,20));
        BackButton.setForeground(new Color(243,220,170));
        BackButton.setFont(new Font("Serif", Font.BOLD, 16));

        BackButton.setBorder(BorderFactory.createLineBorder(new Color(243,220,170),2));
        bottom.add(Box.createRigidArea(new Dimension(0,80)));
        BackButton.addActionListener(e -> parent.showFrontPage());

        bottom.add(BackButton);

        //        Gamerules button with action listener
        JButton GameRules = new JButton("Game Rules");
        GameRules.setPreferredSize(new Dimension(140,38));
        GameRules.setFocusPainted(false);
        GameRules.setBackground(new Color(90,50,20));
        GameRules.setForeground(new Color(243,220,170));
        GameRules.setFont(new Font("Serif", Font.BOLD, 16));
//        GameRules.setBorder(BorderFactory.createLineBorder(new Color(194,137,74),2));

        GameRules.setBorder(BorderFactory.createLineBorder(new Color(243,220,170),2));
        bottom.add(Box.createRigidArea(new Dimension(80,0)));
        bottom.add(GameRules);
        GameRules.addActionListener(e -> {

            JFrame frame = (JFrame)
                    SwingUtilities.getWindowAncestor(this);

            GameRulesDialog dialog =
                    new GameRulesDialog(frame);

            dialog.setVisible(true);
        });

        add(bottom, BorderLayout.SOUTH);

    }

    private JPanel createGameCard(
           String level, String title,
            String description,
            Runnable action) {

        JPanel card = new JPanel();
        card.setLayout(new BoxLayout(card, BoxLayout.Y_AXIS));
        card.setBackground(new Color(90,50,20));
        card.setBorder(BorderFactory.createLineBorder(new Color(194,137,74),2));
        card.setPreferredSize(new Dimension(180,140));
        card.setCursor(new Cursor(Cursor.HAND_CURSOR));
        card.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseClicked(MouseEvent e) {

                SoundManager.stopBackgroundMusic();
                SoundManager.playSound("click.wav");

                action.run();
            }
        });

        JPanel textPanel = new JPanel();
        textPanel.setOpaque(false);
        textPanel.setLayout(new BoxLayout(textPanel, BoxLayout.Y_AXIS));

        JLabel levelLabel = new JLabel(level);
        levelLabel.setFont(new Font("Serif", Font.BOLD, 14));
        levelLabel.setForeground(new Color(220,215,175));
        levelLabel.setBorder(BorderFactory.createLineBorder(new Color(194,137,74),2));


        JLabel titleLabel = new JLabel(title);
        titleLabel.setFont(new Font("Serif", Font.PLAIN, 26));
        titleLabel.setForeground(new Color(240,215,175));

        JLabel descLabel = new JLabel("<html>" + description + "</html>");
        descLabel.setFont(new Font("Serif", Font.ITALIC, 18));
        descLabel.setForeground(new Color(220,190,140));

        textPanel.add(Box.createVerticalGlue());
        textPanel.add(levelLabel);
        textPanel.add(Box.createVerticalGlue());
        textPanel.add(titleLabel);
        textPanel.add(Box.createVerticalStrut(4));
        textPanel.add(descLabel);
        textPanel.add(Box.createVerticalGlue());

        card.add(Box.createHorizontalStrut(12));
        card.add(textPanel);

        return card;
    }


    //    to add hint on the name field
    private void addPlaceholder(JTextField textField, String hint) {
        textField.setText(hint);
        textField.setForeground(Color.ORANGE);

        textField.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
//                clear field text when field focus
                if (textField.getText().equals(hint)) {
                    textField.setText("");
                    textField.setForeground(new Color(243,220,170));

                }
            }

            @Override
            public void focusLost(FocusEvent e) {
//                restore the text if not focus with cursor
                if (textField.getText().trim().isEmpty()) {
                    textField.setText(hint);
                    textField.setForeground(Color.ORANGE);
                }
            }
        });
    }

}
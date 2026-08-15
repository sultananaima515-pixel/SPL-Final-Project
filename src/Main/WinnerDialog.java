package Main;

import sound.SoundManager;

import javax.swing.*;
import java.awt.*;

public class WinnerDialog extends JDialog {


    public interface DialogActions {
        void onPlayAgain();
        void onBackToMenu();
    }

    private final JLabel titleLabel;
    private final JLabel messageLabel;
    private final JLabel scoreLabel;   // hidden unless a mode passes a score (e.g. Time Mode)

    public WinnerDialog(Frame owner, DialogActions actions) {
        super(owner, "Winner", true);

        setSize(350, 250);
        setResizable(false);
        setDefaultCloseOperation(JDialog.HIDE_ON_CLOSE); // reused, never disposed

        JPanel panel = new JPanel();
        panel.setBackground(new Color(110, 70, 50));
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        titleLabel = new JLabel("Winner!");
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        titleLabel.setFont(new Font("Serif", Font.BOLD, 30));
        titleLabel.setForeground(new Color(255, 215, 0));

        messageLabel = new JLabel("");
        messageLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        messageLabel.setFont(new Font("Serif", Font.BOLD, 22));
        messageLabel.setForeground(Color.WHITE);

        scoreLabel = new JLabel("");
        scoreLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        scoreLabel.setFont(new Font("SansSerif", Font.BOLD, 18));
        scoreLabel.setForeground(new Color(255, 240, 180));
        scoreLabel.setVisible(false);

        JButton playAgain = new JButton("Play Again");
        playAgain.setBackground(new Color(88, 51, 22));
        playAgain.setForeground(Color.WHITE);
        playAgain.setFocusPainted(false);
        playAgain.addActionListener(e -> {
            SoundManager.playSound("click.wav");
            setVisible(false);
            actions.onPlayAgain();
        });

        JButton menu = new JButton("Menu");
        menu.setBackground(new Color(88, 51, 22));
        menu.setForeground(Color.WHITE);
        menu.setFocusPainted(false);
        menu.addActionListener(e -> {
            SoundManager.playSound("click.wav");
            setVisible(false);
            actions.onBackToMenu();
            SoundManager.playBackgroundMusic("ambient.wav");
        });

        JPanel buttonPanel = new JPanel();
        buttonPanel.setOpaque(false);
        buttonPanel.add(playAgain);
        buttonPanel.add(menu);

        panel.add(Box.createVerticalStrut(20));
        panel.add(titleLabel);
        panel.add(Box.createVerticalStrut(15));
        panel.add(messageLabel);
        panel.add(Box.createVerticalStrut(10));
        panel.add(scoreLabel);
        panel.add(Box.createVerticalStrut(20));
        panel.add(buttonPanel);

        add(panel);
    }

    public void showResult(String title, String message) {
        showResult(title, message, null);
    }

    public void showResult(String title, String message, String score) {
        titleLabel.setText(title);
        messageLabel.setText(message);

        if (score != null) {
            scoreLabel.setText(score);
            scoreLabel.setVisible(true);
        } else {
            scoreLabel.setVisible(false);
        }

        SoundManager.playSound("fanfare.wav");
        setLocationRelativeTo(getOwner());
        setVisible(true);
    }
}

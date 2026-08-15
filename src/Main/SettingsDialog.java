package Main;

import sound.SoundManager;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class SettingsDialog extends JDialog {

    // Same palette used across GameRulesDialog / winner dialogs
    private static final Color BG        = new Color(94, 54, 23);
    private static final Color CARD_BG   = new Color(90, 50, 20);
    private static final Color TEXT      = new Color(243, 220, 170);
    private static final Color ACCENT    = new Color(194, 137, 74);
    private static final Color GOLD      = new Color(255, 215, 0);
    private static final Color OFF_COLOR = new Color(153, 96, 48);

    private JButton soundToggle;

    public SettingsDialog(Frame parent) {
        super(parent, "Settings", true);

        setSize(400, 260);
        setResizable(false);
        setLocationRelativeTo(parent);
        setLayout(new BorderLayout());

        JPanel content = new JPanel();
        content.setBackground(BG);
        content.setLayout(new BoxLayout(content, BoxLayout.Y_AXIS));
        content.setBorder(new EmptyBorder(25, 25, 25, 25));

        JLabel title = new JLabel("SETTINGS");
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        title.setFont(new Font("Serif", Font.BOLD, 26));
        title.setForeground(TEXT);

        content.add(title);
        content.add(Box.createRigidArea(new Dimension(0, 25)));

        // ---- Sound row (card matching GameRulesDialog card style) ----
        JPanel soundCard = new JPanel(new BorderLayout());
        soundCard.setBackground(CARD_BG);
        soundCard.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(ACCENT, 2),
                new EmptyBorder(15, 15, 15, 15)
        ));

        JLabel soundLabel = new JLabel("Sound");
        soundLabel.setFont(new Font("Serif", Font.BOLD, 20));
        soundLabel.setForeground(TEXT);

        soundToggle = new JButton();
        soundToggle.setPreferredSize(new Dimension(90, 38));
        soundToggle.setFont(new Font("Serif", Font.BOLD, 16));
        soundToggle.setFocusPainted(false);
        soundToggle.setBorder(BorderFactory.createLineBorder(ACCENT, 2));
        soundToggle.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        refreshToggleAppearance();

        soundToggle.addActionListener(e -> {
            boolean newState = !SoundManager.isSoundEnabled();
            SoundManager.setSoundEnabled(newState);   // this also stops music if turned off

            if (newState) {
                SoundManager.playBackgroundMusic("ambient.wav");
                SoundManager.playSound("click.wav");
            }

            refreshToggleAppearance();
        });

        soundCard.add(soundLabel, BorderLayout.WEST);
        soundCard.add(soundToggle, BorderLayout.EAST);

        content.add(soundCard);
        content.add(Box.createVerticalGlue());

        // ---- Close button
        JButton closeBtn = new JButton("Close");
        closeBtn.setPreferredSize(new Dimension(140, 38));
        closeBtn.setBackground(BG);
        closeBtn.setForeground(Color.WHITE);
        closeBtn.setFont(new Font("Serif", Font.BOLD, 20));
        closeBtn.setFocusPainted(false);
        closeBtn.setBorder(BorderFactory.createLineBorder(ACCENT, 2));
        closeBtn.addActionListener(e -> dispose());

        JPanel bottom = new JPanel();
        bottom.setBackground(BG);
        bottom.add(closeBtn);

        add(content, BorderLayout.CENTER);
        add(bottom, BorderLayout.SOUTH);
    }


    private void refreshToggleAppearance() {
        boolean on = SoundManager.isSoundEnabled();
        soundToggle.setText(on ? "ON" : "OFF");
        soundToggle.setBackground(on ? GOLD : OFF_COLOR);
        soundToggle.setForeground(on ? new Color(50, 30, 10) : TEXT);
    }
}

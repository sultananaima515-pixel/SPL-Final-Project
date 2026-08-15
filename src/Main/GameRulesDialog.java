package Main;


import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class GameRulesDialog extends JDialog {

    public GameRulesDialog(JFrame parent) {

        super(parent, "Game Rules", true);

        setSize(750, 600);
        setLocationRelativeTo(parent);
        setLayout(new BorderLayout());

        Color BG = new Color(94, 54, 23);
        Color CARD = new Color(153, 96, 48);
        Color TEXT = new Color(243, 220, 170);

        JPanel content = new JPanel();
        content.setLayout(new BoxLayout(content, BoxLayout.Y_AXIS));
        content.setBackground(BG);
        content.setBorder(new EmptyBorder(20, 20, 20, 20));

        JLabel title = new JLabel("GAME RULES");
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        title.setFont(new Font("Serif", Font.BOLD, 30));
        title.setForeground(TEXT);

        content.add(title);
        content.add(Box.createRigidArea(new Dimension(0, 20)));

        addRuleSection(content,
                "CLASSIC 3×3",
                """
                        How To Play:
                        The game is played on a 3 × 3 board.
                        Two players participate:
                        Player X moves first.
                        Player O moves second.
                        Players take turns placing their symbol in an empty cell.
                        
                        Win:
                        A player wins by making:
                        
                        Three symbols in the same row, or
                        Three symbols in the same column, or
                        Three symbols in the same diagonal.
                        
                        Draw:
                        If all nine cells are filled and nobody achieves three in a row, the match ends in a Draw.
                        
                        Once the game ends, no additional moves are allowed until the player selects Play Again or New Game.
                        """);

        addRuleSection(content,
                "ULTIMATE 3×3",
                """
                        Board Structure:
                        • 9 small tic tac toe boards.
                        • Each small board is a normal 3×3 game.
                        
                        Active Board Rule:
                        • Your move determines the board
                          your opponent must play in next.
                        
                        Example:
                        • Play in cell (0,0)
                        • Opponent must play in board 0.
                        
                        Special Rule:
                        • If the target board is already won,
                          opponent may play anywhere.
                        
                        Win:
                        • Capture 3 small boards in a row.
                        """);

        addRuleSection(content,
                "ULTIMATE 4×4",
                """
                        Board Structure:
                        • 16 small boards arranged 4×4.
                        
                        Active Board Rule:
                        • Cell position sends opponent
                          to the matching board.
                        
                        Special Rule:
                        • If target board is completed,
                          player may choose any available board.
                        
                        Win:
                        • Capture 4 boards horizontally,
                          vertically or diagonally.
                        """
        );

        addRuleSection(content,
                "ULTIMATE 5×5",
                """
                        Objective:
                        • Win as many small boards as possible within 2 minutes.
                        
                        Board Structure:
                        • 25 small boards arranged 5x5.
                        
                        Active Board Rule:
                        • Player can play any 3x3 small boards.
                        
                        Win:
                        • When the timer reaches 00:00:
                          .The match ends immediately.
                          .The player with the most small-board victories wins.
                        """
        );

        addRuleSection(content,
                "GENERAL CONTROLS",
                """
                        New Game:
                        • Resets the entire match.
                        
                        Back:
                        • Returns to main menu.
                        
                        Active Board:
                        • Highlighted with yellow border.
                        
                        Finished Boards:
                        • Display a large winner symbol.
                        
                        Winner Screen:
                        • Appears when the match ends.
                        • Shows winner name.
                        • Play Again starts a new game.
                        """);

        JScrollPane scrollPane = new JScrollPane(content);
        scrollPane.setBorder(null);
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);

        JButton closeBtn = new JButton("Close");
        closeBtn.setPreferredSize(new Dimension(140,38));
        closeBtn.setBackground(BG);
//        closeBtn.setForeground(new Color(255, 215, 0));
        closeBtn.setForeground(Color.white);
        closeBtn.setFont(new Font("Serif", Font.BOLD, 20));
        closeBtn.setBorder(BorderFactory.createLineBorder(new Color(194,137,74),2));
        closeBtn.addActionListener(e -> dispose());

        JPanel bottom = new JPanel();
        bottom.setBackground(BG);
        bottom.add(closeBtn);

        add(scrollPane, BorderLayout.CENTER);
        add(bottom, BorderLayout.SOUTH);
    }


    private void addRuleSection(JPanel parent,
                                String heading,
                                String text) {

        JPanel card = new JPanel();
        card.setLayout(new BorderLayout());
        card.setBackground(new Color(90, 50, 20));

        card.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(194, 137, 74), 2),
                new EmptyBorder(15, 15, 15, 15)
        ));

        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
        contentPanel.setOpaque(false);

        JLabel title = new JLabel(heading);
        title.setFont(new Font("Serif", Font.BOLD, 24));
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        title.setForeground(new Color(255, 215, 0)); // Gold

        JSeparator separator = new JSeparator();
        separator.setForeground(new Color(180, 140, 100));
        separator.setBackground(new Color(180, 140, 100));

        JTextArea area = new JTextArea(text);
        area.setEditable(false);
        area.setLineWrap(true);
        area.setWrapStyleWord(true);
        area.setOpaque(false);
        area.setForeground(new Color(240,215,175));
        area.setFont(new Font("SansSerif", Font.PLAIN, 12));

        contentPanel.add(title);
        contentPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        contentPanel.add(separator);
        contentPanel.add(Box.createRigidArea(new Dimension(0, 15)));
        contentPanel.add(area);

        card.add(contentPanel, BorderLayout.CENTER);

        parent.add(card);
        parent.add(Box.createRigidArea(new Dimension(0, 15)));
    }
}

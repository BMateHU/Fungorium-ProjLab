package com.beingchilling.gui;

import com.beingchilling.controller.ControllerComponent;
import com.beingchilling.view.ViewComponent;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Path2D;
import java.util.HashMap;

public class GUI
{
    private HashMap<Object, JComponent> objects;
    private JLabel playerStats;
    private JLabel round;
    private JButton growThreadButton;
    private JButton growMushButton;
    private JButton spreadSporeButton;
    private JButton absorbInsectButton;
    private JButton skipButton;
    private JButton endGameButton;
    private JButton moveButton;
    private JButton eatButton;
    private JButton cutButton;
    private JComboBox<String> growThreadParam1;
    private JComboBox<String> growThreadParam2;
    private JComboBox<String> growMushParam1;
    private JComboBox<String> spreadSporeParam1;
    private JComboBox<String> spreadSporeParam2;
    private JComboBox<String> absorbInsectParam1;
    private JComboBox<String> cutParam1;
    private JComboBox<String> moveParam1;
    private JPanel insectPanel;
    private JPanel mushroomPanel;

    private static final int FRAME_WIDTH = 1200;
    private static final int FRAME_HEIGHT = 800;
    private static final int SIDEBAR_WIDTH = 250;

    private boolean isCurrentPanelMushroom = true; //just for testing u can delete it anytime u want

    ViewComponent vc = new ViewComponent();
    ControllerComponent cc = new ControllerComponent(vc);

    private JFrame frame;

    public GUI() {
        vc.setControllerComponent(cc);

        frame = new JFrame();
        frame.setTitle("Fungorium");
        frame.setSize(FRAME_WIDTH, FRAME_HEIGHT); // Set fixed size
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null); // Center the window
        mushroomPanel = new JPanel();
        insectPanel = new JPanel();
        objects = new HashMap<>();

        init();

        frame.setVisible(true);
    }

    public void init() {
        mushroomPanel.setLayout(new BorderLayout(0, 0));
        JPanel topPanel = createTopPanel();
        JPanel sidebarPanelMushroom = createSidebarPanelForMushroom();
        JPanel contentPanel = createContentPanel();
        JSplitPane splitPaneMushroom = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, sidebarPanelMushroom, contentPanel);
        splitPaneMushroom.setDividerLocation(SIDEBAR_WIDTH);
        splitPaneMushroom.setEnabled(false);

        mushroomPanel.add(topPanel, BorderLayout.NORTH);
        mushroomPanel.add(splitPaneMushroom, BorderLayout.CENTER);

        frame.add(mushroomPanel);

        insectPanel.setLayout(new BorderLayout(0, 0));
        JPanel sidebarPanelInsect = createSidebarPanelForInsect();
        contentPanel = createContentPanel();
        JSplitPane splitPaneInsect = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, sidebarPanelInsect, contentPanel);
        splitPaneInsect.setDividerLocation(SIDEBAR_WIDTH);
        splitPaneInsect.setEnabled(false);

        insectPanel.add(topPanel, BorderLayout.NORTH);
        insectPanel.add(splitPaneInsect, BorderLayout.CENTER);
    }

    public void switchPanels() {
        if(isCurrentPanelMushroom) {
            frame.remove(mushroomPanel);
            frame.add(insectPanel);
        }
        else {
            frame.remove(insectPanel);
            frame.add(mushroomPanel);
        }
        reDrawAll();
    }

    public void reDrawAll() {
        frame.revalidate();
        insectPanel.revalidate();
        mushroomPanel.revalidate();
    }

    private JPanel createTopPanel() {
        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        topPanel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createMatteBorder(0, 0, 1, 0, Color.DARK_GRAY),
                new EmptyBorder(5, 5, 5, 5)
        ));
        round = new JLabel("round " + cc.getRound());
        round.setFont(round.getFont().deriveFont(Font.BOLD, 14f));
        topPanel.add(round);
        return topPanel;
    }

    private JPanel createSidebarPanelForMushroom() {
        // Main sidebar still uses BorderLayout
        JPanel sidebarPanel = new JPanel(new BorderLayout(0, 10)); // 0 Hgap, 10 Vgap
        sidebarPanel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createMatteBorder(0, 0, 0, 1, Color.DARK_GRAY), // Right border
                new EmptyBorder(10, 10, 10, 10) // Padding
        ));
        sidebarPanel.setPreferredSize(new Dimension(SIDEBAR_WIDTH, 0)); // Set preferred width

        // --- Player Info (North) ---
        playerStats = new JLabel("Player " + cc.getWhichPlayer() + ": Gomba " + cc.getWhichPuppet());
        playerStats.setFont(playerStats.getFont().deriveFont(Font.PLAIN, 14f));
        playerStats.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.GRAY), // Border around label area
                new EmptyBorder(5, 5, 5, 5) // Inner padding
        ));
        sidebarPanel.add(playerStats, BorderLayout.NORTH);

        // --- Middle Placeholder Area (Center) ---
        JPanel middlePlaceholdersPanel = new JPanel(new GridLayout(4, 1, 0, 10)); // 4 rows, 1 col, 0 Hgap, 10 Vgap
        Border placeholderBorder = BorderFactory.createLineBorder(Color.DARK_GRAY);
        EmptyBorder innerPadding = new EmptyBorder(5, 5, 5, 5); // Inner padding for placeholders

        // Placeholder 1: Grow Thread
        JPanel placeholder1 = new JPanel();
        placeholder1.setLayout(new BoxLayout(placeholder1, BoxLayout.Y_AXIS));
        placeholder1.setBorder(BorderFactory.createCompoundBorder(placeholderBorder, innerPadding));
        growThreadButton = new JButton("Grow Thread");
        growThreadParam1 = new JComboBox<>(new String[]{"Thread 1"});
        growThreadParam2 = new JComboBox<>(new String[]{"Tekton 1"});

        growThreadButton.addActionListener(e -> {
            cc.ArgumentManagement("/growthread " + growThreadParam1.getSelectedItem() + " " + growThreadParam2.getSelectedItem());
        });

        configurePlaceholderComponents(placeholder1, growThreadButton, growThreadParam1, growThreadParam2);

        // Placeholder 2: Grow Mushroom
        JPanel placeholder2 = new JPanel();
        placeholder2.setLayout(new BoxLayout(placeholder2, BoxLayout.Y_AXIS));
        placeholder2.setBorder(BorderFactory.createCompoundBorder(placeholderBorder, innerPadding));
        growMushButton = new JButton("Grow Mushroom");
        growMushParam1 = new JComboBox<>(new String[]{"Tekton 1"});
        configurePlaceholderComponents(placeholder2, growMushButton, growMushParam1);

        // Placeholder 3: Spread Spore
        JPanel placeholder3 = new JPanel();
        placeholder3.setLayout(new BoxLayout(placeholder3, BoxLayout.Y_AXIS));
        placeholder3.setBorder(BorderFactory.createCompoundBorder(placeholderBorder, innerPadding));
        spreadSporeButton = new JButton("Spread Spore");
        spreadSporeParam1 = new JComboBox<>(new String[]{"Tekton 1"});
        spreadSporeParam2 = new JComboBox<>(new String[]{"Haste"});
        configurePlaceholderComponents(placeholder3, spreadSporeButton, spreadSporeParam1, spreadSporeParam2);

        // Placeholder 4: Absorb Insect
        JPanel placeholder4 = new JPanel();
        placeholder4.setLayout(new BoxLayout(placeholder4, BoxLayout.Y_AXIS));
        placeholder4.setBorder(BorderFactory.createCompoundBorder(placeholderBorder, innerPadding));
        absorbInsectButton = new JButton("Absorb Insect");
        absorbInsectParam1 = new JComboBox<>(new String[]{"Thread 1"});
        configurePlaceholderComponents(placeholder4, absorbInsectButton, absorbInsectParam1);

        // Add placeholders to the grid layout panel
        middlePlaceholdersPanel.add(placeholder1);
        middlePlaceholdersPanel.add(placeholder2);
        middlePlaceholdersPanel.add(placeholder3);
        middlePlaceholdersPanel.add(placeholder4);

        sidebarPanel.add(middlePlaceholdersPanel, BorderLayout.CENTER);

        // --- Bottom Buttons (South) ---
        JPanel bottomButtonsPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 0));
        skipButton = new JButton("skip");
        endGameButton = new JButton("end game");
        bottomButtonsPanel.add(skipButton);
        bottomButtonsPanel.add(endGameButton);
        sidebarPanel.add(bottomButtonsPanel, BorderLayout.SOUTH);

        return sidebarPanel;
    }

    private JPanel createSidebarPanelForInsect() {
        // Main sidebar still uses BorderLayout
        JPanel sidebarPanel = new JPanel(new BorderLayout(0, 10)); // 0 Hgap, 10 Vgap
        sidebarPanel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createMatteBorder(0, 0, 0, 1, Color.DARK_GRAY), // Right border
                new EmptyBorder(10, 10, 10, 10) // Padding
        ));
        sidebarPanel.setPreferredSize(new Dimension(SIDEBAR_WIDTH, 0)); // Set preferred width

        // --- Player Info (North) ---
        playerStats = new JLabel("Player 1: Insect 1");
        playerStats.setFont(playerStats.getFont().deriveFont(Font.PLAIN, 14f));
        playerStats.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.GRAY), // Border around label area
                new EmptyBorder(5, 5, 5, 5) // Inner padding
        ));
        sidebarPanel.add(playerStats, BorderLayout.NORTH);

        // --- Middle Placeholder Area (Center) ---
        JPanel middlePlaceholdersPanel = new JPanel(new GridLayout(3, 1, 0, 10)); // 4 rows, 1 col, 0 Hgap, 10 Vgap
        Border placeholderBorder = BorderFactory.createLineBorder(Color.DARK_GRAY);
        EmptyBorder innerPadding = new EmptyBorder(5, 5, 5, 5); // Inner padding for placeholders

        // Placeholder 1: Grow Thread
        JPanel placeholder1 = new JPanel();
        placeholder1.setLayout(new BoxLayout(placeholder1, BoxLayout.Y_AXIS));
        placeholder1.setBorder(BorderFactory.createCompoundBorder(placeholderBorder, innerPadding));
        moveButton = new JButton("Move");
        moveParam1 = new JComboBox<>(new String[]{"Tekton 1"});


        configurePlaceholderComponents(placeholder1, moveButton, moveParam1);

        // Placeholder 2: Grow Mushroom
        JPanel placeholder2 = new JPanel();
        placeholder2.setLayout(new BoxLayout(placeholder2, BoxLayout.Y_AXIS));
        placeholder2.setBorder(BorderFactory.createCompoundBorder(placeholderBorder, innerPadding));
        eatButton = new JButton("Eat");
        configurePlaceholderComponents(placeholder2, eatButton);

        // Placeholder 3: Spread Spore
        JPanel placeholder3 = new JPanel();
        placeholder3.setLayout(new BoxLayout(placeholder3, BoxLayout.Y_AXIS));
        placeholder3.setBorder(BorderFactory.createCompoundBorder(placeholderBorder, innerPadding));
        cutButton = new JButton("Cut");
        cutParam1 = new JComboBox<>(new String[]{"Thread 1"});
        configurePlaceholderComponents(placeholder3, cutButton, cutParam1);


        // Add placeholders to the grid layout panel
        middlePlaceholdersPanel.add(placeholder1);
        middlePlaceholdersPanel.add(placeholder2);
        middlePlaceholdersPanel.add(placeholder3);

        sidebarPanel.add(middlePlaceholdersPanel, BorderLayout.CENTER);

        // --- Bottom Buttons (South) ---
        JPanel bottomButtonsPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 0));
        skipButton = new JButton("skip");
        endGameButton = new JButton("end game");
        bottomButtonsPanel.add(skipButton);
        bottomButtonsPanel.add(endGameButton);
        sidebarPanel.add(bottomButtonsPanel, BorderLayout.SOUTH);

        return sidebarPanel;
    }

    // Helper method to configure components within a placeholder panel using BoxLayout
    private void configurePlaceholderComponents(JPanel placeholder, JButton button, JComboBox<?>... comboBoxes) {
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
        placeholder.add(Box.createVerticalGlue()); // Push content to vertical center
        placeholder.add(button);
        for (JComboBox<?> comboBox : comboBoxes) {
            comboBox.setMaximumSize(new Dimension(Integer.MAX_VALUE, comboBox.getPreferredSize().height));
            comboBox.setAlignmentX(Component.CENTER_ALIGNMENT);
            placeholder.add(Box.createRigidArea(new Dimension(0, 8))); // Spacing
            placeholder.add(comboBox);
        }
        placeholder.add(Box.createVerticalGlue()); // Push content to vertical center
    }


    // Helper method to create the main content panel with drawing
    private JPanel createContentPanel() {
        JPanel contentPanel = new JPanel(new BorderLayout()); // Use BorderLayout
        contentPanel.setBorder(new EmptyBorder(10, 10, 10, 10)); // Padding
        contentPanel.setBackground(Color.WHITE); // Set background for the main container

        // Create and add the custom drawing panel
        DrawingPanel drawingPanel = new DrawingPanel();
        contentPanel.add(drawingPanel, BorderLayout.CENTER); // Add drawing panel to the center

        return contentPanel;
    }

    // Inner class for custom drawing
    private class DrawingPanel extends JPanel {
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g); // Always call superclass method first
            Graphics2D g2d = (Graphics2D) g; // Cast to Graphics2D for more features

            // Enable anti-aliasing for smoother shapes and text
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);


            // Get panel dimensions for positioning
            int panelWidth = getWidth();
            int panelHeight = getHeight();

            // Define circle properties
            int circleRadius = 50;
            int circleDiameter = circleRadius * 2;
            int triangleBase = panelWidth / 3; // Base width of the triangle formed by centers
            int triangleHeight = panelHeight / 4; // Height of the triangle

            // Calculate positions for a triangle layout

            // Circle 1 (Top vertex) - Centered horizontally, higher up
            int c1x = panelWidth / 2 - circleRadius;
            int c1y = panelHeight / 2 - triangleHeight / 2 - circleRadius; // Move up
            Point center1 = new Point(c1x + circleRadius, c1y + circleRadius);

            // Circle 2 (Bottom-left vertex)
            int c2x = panelWidth / 2 - triangleBase / 2 - circleRadius; // Move left from center
            int c2y = panelHeight / 2 + triangleHeight / 2 - circleRadius; // Move down
            Point center2 = new Point(c2x + circleRadius, c2y + circleRadius);

            // Circle 3 (Bottom-right vertex)
            int c3x = panelWidth / 2 + triangleBase / 2 - circleRadius; // Move right from center
            int c3y = panelHeight / 2 + triangleHeight / 2 - circleRadius; // Move down (same y as c2)
            Point center3 = new Point(c3x + circleRadius, c3y + circleRadius);


            // --- Draw Circles ---
            g2d.setColor(Color.BLUE); // Example color
            g2d.setStroke(new BasicStroke(2)); // Make circle lines slightly thicker
            g2d.drawOval(c1x, c1y, circleDiameter, circleDiameter); // Circle 1 (Top)
            g2d.drawOval(c2x, c2y, circleDiameter, circleDiameter); // Circle 2 (Bottom-left)
            g2d.drawOval(c3x, c3y, circleDiameter, circleDiameter); // Circle 3 (Bottom-right)

            // --- Draw Connecting Lines ---
            Stroke defaultStroke = g2d.getStroke(); // Save default stroke (which is now BasicStroke(2))

            // Line 1: Straight line (Top to Bottom-Left)
            g2d.setColor(Color.BLACK);
            g2d.drawLine(center1.x, center1.y, center2.x, center2.y);

            // Line 2: Dashed line (Top to Bottom-Right)
            // Define a dashed stroke: {dash length, space length}
            Stroke dashedStroke = new BasicStroke(2, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 0, new float[]{9, 5}, 0);
            g2d.setStroke(dashedStroke);
            g2d.drawLine(center1.x, center1.y, center3.x, center3.y);

            g2d.setStroke(defaultStroke); // Restore default stroke (solid, thickness 2)

            // --- Draw Shapes on Circles ---

            // Brown Square on Circle 3 (Bottom-Right)
            int squareSize = 20;
            int squareX = center3.x - squareSize / 2; // Center the square on Circle 3
            int squareY = center3.y - squareSize / 2;
            g2d.setColor(new Color(139, 69, 19)); // Brown color
            g2d.fillRect(squareX, squareY, squareSize, squareSize);
            g2d.setColor(Color.BLACK); // Outline for visibility
            g2d.drawRect(squareX, squareY, squareSize, squareSize);


            // Yellow Triangle on Circle 2 (Bottom-Left)
            int triangleShapeSize = 25; // Size of the yellow triangle shape
            Path2D triangleShape = new Path2D.Double();
            // Define vertices relative to the center of circle 2
            triangleShape.moveTo(center2.x, center2.y - triangleShapeSize / 1.5); // Top point
            triangleShape.lineTo(center2.x - triangleShapeSize / 2.0, center2.y + triangleShapeSize / 3.0); // Bottom left
            triangleShape.lineTo(center2.x + triangleShapeSize / 2.0, center2.y + triangleShapeSize / 3.0); // Bottom right
            triangleShape.closePath();

            g2d.setColor(Color.YELLOW);
            g2d.fill(triangleShape);
            g2d.setColor(Color.BLACK); // Outline for visibility
            g2d.draw(triangleShape);

            // *** NEW: Draw 3 small green circles inside Circle 1 (Top) ***
            int smallCircleRadius = 8;
            int smallCircleDiameter = smallCircleRadius * 2;
            g2d.setColor(Color.GREEN);
            // Position them in a small triangle pattern inside Circle 1
            int offset = circleRadius / 3;
            g2d.fillOval(center1.x - smallCircleRadius, center1.y - offset - smallCircleRadius, smallCircleDiameter, smallCircleDiameter); // Top small circle
            g2d.fillOval(center1.x - offset - smallCircleRadius, center1.y + offset - smallCircleRadius, smallCircleDiameter, smallCircleDiameter); // Bottom-left small circle
            g2d.fillOval(center1.x + offset - smallCircleRadius, center1.y + offset - smallCircleRadius, smallCircleDiameter, smallCircleDiameter); // Bottom-right small circle


            // *** NEW: Draw Labels ***
            g2d.setColor(Color.BLACK);
            g2d.setFont(new Font("Arial", Font.BOLD, 12)); // Set font for labels

            // Label positioning offsets
            int labelOffset = circleRadius + 15; // Distance below the circle center for Tekton labels
            int shapeLabelOffset = 15; // Distance below the shape for Gomba/Rovar labels

            // --- Tekton Labels ---
            // Center the text horizontally below the circle centers
            FontMetrics fm = g2d.getFontMetrics(); // Get font metrics for text centering

            String tekton1Label = "Tekton1";
            int tekton1Width = fm.stringWidth(tekton1Label);
            g2d.drawString(tekton1Label, center1.x - tekton1Width / 2, center1.y + labelOffset);

            String tekton2Label = "Tekton2";
            int tekton2Width = fm.stringWidth(tekton2Label);
            g2d.drawString(tekton2Label, center2.x - tekton2Width / 2, center2.y + labelOffset);

            String tekton3Label = "Tekton3";
            int tekton3Width = fm.stringWidth(tekton3Label);
            g2d.drawString(tekton3Label, center3.x - tekton3Width / 2, center3.y + labelOffset);

            // --- Shape Labels ---
            String gombaLabel = "Gomba1";
            int gombaWidth = fm.stringWidth(gombaLabel);
            // Position below the triangle on Circle 2
            g2d.drawString(gombaLabel, center2.x - gombaWidth / 2, (int)(center2.y + triangleShapeSize / 3.0) + shapeLabelOffset + 5); // Adjusted y-pos based on triangle bottom

            String rovarLabel = "Rovar1";
            int rovarWidth = fm.stringWidth(rovarLabel);
            // Position below the square on Circle 3
            g2d.drawString(rovarLabel, center3.x - rovarWidth / 2, squareY + squareSize + shapeLabelOffset); // Adjusted y-pos based on square bottom

        }
    }
}

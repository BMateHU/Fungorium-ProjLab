package com.beingchilling.gui;

import com.beingchilling.Main;
import com.beingchilling.controller.ControllerComponent;
import com.beingchilling.game.BiMap;
import com.beingchilling.game.GameModel;
import com.beingchilling.model.Tekton;
import com.beingchilling.view.ViewComponent;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Path2D;
import java.io.InputStream;
import java.util.ArrayList;

public class GUI
{
    public static BiMap<Object, JComponent> objects;
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
        mushroomPanel = new JPanel();
        insectPanel = new JPanel();
        objects = new BiMap<>();

        frame = new JFrame();
        frame.setTitle("Fungorium");
        frame.setSize(FRAME_WIDTH, FRAME_HEIGHT); // Set fixed size
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null); // Center the window

        init();

        frame.setVisible(true);

        try {
            InputStream url = Main.class.getClassLoader().getResourceAsStream("start.txt");
            assert url != null;
            cc.load(url);
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
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
        JPanel contentPanel = new JPanel(new BorderLayout());
        contentPanel.setBackground(Color.WHITE);

        DrawingPanel drawingPanel = new DrawingPanel();
        JScrollPane scrollPane = new JScrollPane(drawingPanel);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

        contentPanel.add(scrollPane, BorderLayout.CENTER);
        return contentPanel;
    }

    // Inner class for custom drawing
    private class DrawingPanel extends JPanel {
        private static final int VIRTUAL_WIDTH = 5000;
        private static final int VIRTUAL_HEIGHT = 5000;

        private Point panStartPoint;
        private Point viewStartPoint;

        public DrawingPanel() {
            setPreferredSize(new Dimension(VIRTUAL_WIDTH, VIRTUAL_HEIGHT));
            setBackground(Color.LIGHT_GRAY);

            MouseAdapter mouseAdapter = new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent e) {
                    if (SwingUtilities.isLeftMouseButton(e)) {
                        panStartPoint = e.getPoint();
                        JViewport viewport = (JViewport) getParent();
                        viewStartPoint = viewport.getViewPosition();
                        setCursor(Cursor.getPredefinedCursor(Cursor.MOVE_CURSOR));
                    }
                }

                @Override
                public void mouseDragged(MouseEvent e) {
                    if (SwingUtilities.isLeftMouseButton(e) && panStartPoint != null && viewStartPoint != null) {
                        Point currentPoint = e.getPoint();
                        int dx = currentPoint.x - panStartPoint.x;
                        int dy = currentPoint.y - panStartPoint.y;
                        JViewport viewport = (JViewport) getParent();
                        int newX = viewStartPoint.x - dx;
                        int newY = viewStartPoint.y - dy;
                        if(newX > 0 && newY > 0) {
                            viewport.setViewPosition(new Point(newX, newY));
                        }
                    }
                }

                @Override
                public void mouseReleased(MouseEvent e) {
                    if (SwingUtilities.isLeftMouseButton(e)) {
                        panStartPoint = null;
                        viewStartPoint = null;
                        setCursor(Cursor.getDefaultCursor());
                    }
                }
            };
            addMouseListener(mouseAdapter);
            addMouseMotionListener(mouseAdapter);
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2d = (Graphics2D) g;
            g2d.setColor(Color.BLUE);
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);

            if(!objects.keySet().isEmpty()) {
                for (Tekton t : GameModel.map.tektonList.values()) {
                    int x = ((GTekton) objects.getV(t)).getX();
                    int y = ((GTekton) objects.getV(t)).getY();

                    Stroke defaultStroke = g2d.getStroke();

                    ArrayList<Tekton> neighbourWithoutThread = new ArrayList<>(t.getNeighbors());
                    neighbourWithoutThread.removeAll(t.getNeighborWithThread());

                    for (Tekton t2 : neighbourWithoutThread) {
                        int x2 = ((GTekton) objects.getV(t2)).getX();
                        int y2 = ((GTekton) objects.getV(t2)).getY();

                        Stroke dashedStroke = new BasicStroke(2, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 0, new float[]{9, 5}, 0);
                        g2d.setStroke(dashedStroke);
                        g2d.drawLine(x, y, x2, y2);
                    }

                    for (Tekton t2 : t.getNeighborWithThread()) {
                        int x2 = ((GTekton) objects.getV(t2)).getX();
                        int y2 = ((GTekton) objects.getV(t2)).getY();

                        g2d.setStroke(defaultStroke);
                        g2d.setColor(Color.BLACK);
                        g2d.drawLine(x, y, x2, y2);

                        FontMetrics fm = g2d.getFontMetrics();
                        String threadLabel = GameModel.gameObjects.getK(t2.getThreads().get(0));
                        int tekton1Width = fm.stringWidth(threadLabel);
                        g2d.drawString(threadLabel, x + (x - x2) - tekton1Width / 2, y + (y - y2));
                    }
                }
            }
            if(!objects.keySet().isEmpty()) {
                for (Tekton t : GameModel.map.tektonList.values()) {
                    objects.getV(t).paint(g2d);
                }
            }
        }
    }
}
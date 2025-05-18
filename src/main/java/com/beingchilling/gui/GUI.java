package com.beingchilling.gui;

import com.beingchilling.Main;
import com.beingchilling.controller.ControllerComponent;
import com.beingchilling.game.GameModel;
import com.beingchilling.model.*;
import com.beingchilling.view.ViewComponent;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Path2D;
import java.io.InputStream;
import java.util.*;

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
        objects = new HashMap<>();

        try {
            InputStream url = Main.class.getClassLoader().getResourceAsStream("start.txt");
            assert url != null;
            cc.load(url);
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }

        init();

        frame.setVisible(true);
    }

    private final JPanel topPanel = createTopPanel();
    private final JPanel contentPanel = createContentPanel();

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
    }

    public void switchPanels() {
        if(isCurrentPanelMushroom) {
            frame.remove(mushroomPanel);
            if(insectPanel == null) {
                insectPanel = new JPanel();
                insectPanel.setLayout(new BorderLayout(0, 0));
                JPanel sidebarPanelInsect = createSidebarPanelForInsect();
                JSplitPane splitPaneInsect = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, sidebarPanelInsect, contentPanel);
                splitPaneInsect.setDividerLocation(SIDEBAR_WIDTH);
                splitPaneInsect.setEnabled(false);

                insectPanel.add(topPanel, BorderLayout.NORTH);
                insectPanel.add(splitPaneInsect, BorderLayout.CENTER);
            }
            mushroomPanel = null;
            frame.add(insectPanel);
            isCurrentPanelMushroom = false;
        }
        else {
            frame.remove(insectPanel);
            insectPanel = null;
            if(mushroomPanel == null) {
                mushroomPanel = new JPanel();
                mushroomPanel.setLayout(new BorderLayout(0, 0));
                JPanel topPanel = createTopPanel();
                JPanel sidebarPanelMushroom = createSidebarPanelForMushroom();
                JPanel contentPanel = createContentPanel();
                JSplitPane splitPaneMushroom = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, sidebarPanelMushroom, contentPanel);
                splitPaneMushroom.setDividerLocation(SIDEBAR_WIDTH);
                splitPaneMushroom.setEnabled(false);

                mushroomPanel.add(topPanel, BorderLayout.NORTH);
                mushroomPanel.add(splitPaneMushroom, BorderLayout.CENTER);
            }
            frame.add(mushroomPanel);
            isCurrentPanelMushroom = true;
        }
        reDrawAll();
    }

    public void reDrawAll() {
        frame.revalidate();
        if(insectPanel != null)
            insectPanel.revalidate();
        if(mushroomPanel != null)
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
        playerStats = new JLabel("Player " + (cc.getWhichPlayer()-1) + ": Mushroom " + cc.getWhichPuppet());
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
            String lastThreadID = growThreadParam1.getItemAt(growThreadParam1.getItemCount()-1);
            cc.ArgumentManagement("/growthread " + growThreadParam1.getSelectedItem() + " " + growThreadParam2.getSelectedItem() + " " + lastThreadID.substring(0, lastThreadID.length() - 1) + growThreadParam1.getItemCount());
            reDrawAll();
        });

        //------------------------------------------------------------
        growThreadParam2.removeAllItems();
        growThreadParam1.removeAllItems();
        Set<Tekton> tektons = new HashSet<>();
        for(MushroomThread mt5 : ((MushroomBody) GameModel.gameObjects.getV(vc.getCurrentPuppetID())).getLocation().getThreads().get(0).getThreads()) {
            growThreadParam1.addItem(GameModel.gameObjects.getK(mt5));
            for(Tekton t5 : mt5.getLocation().getNeighbors()) {
                if(!mt5.getLocation().getNeighborWithThread().contains(t5)) {
                    tektons.add(t5);
                }
            }
        }
        for(Tekton t5 : tektons) {
            growThreadParam2.addItem(GameModel.gameObjects.getK(t5));
        }
        //-------------------------------------------------------------

        configurePlaceholderComponents(placeholder1, growThreadButton, growThreadParam1, growThreadParam2);

        // Placeholder 2: Grow Mushroom
        JPanel placeholder2 = new JPanel();
        placeholder2.setLayout(new BoxLayout(placeholder2, BoxLayout.Y_AXIS));
        placeholder2.setBorder(BorderFactory.createCompoundBorder(placeholderBorder, innerPadding));
        growMushButton = new JButton("Grow Mushroom");
        growMushParam1 = new JComboBox<>();

        growMushButton.addActionListener(e -> {
            MushroomSpecies s = (MushroomSpecies)GameModel.gameObjects.getV(vc.getCurrentPlayerID());
            String lastMushroomID = GameModel.gameObjects.getK(s.checkMushroomBody().get(s.checkMushroomBody().size()-1));
            cc.ArgumentManagement("/growmush " + vc.getCurrentPlayerID() + " "+ lastMushroomID.substring(0, lastMushroomID.length() - 1)+s.checkMushroomBody().size() + " " + growMushParam1.getSelectedItem());
            reDrawAll();
        });

        //------------------------------------------------------------------------------
        growMushParam1.removeAllItems();
        MushroomBody mb = (MushroomBody) GameModel.gameObjects.getV(vc.getCurrentPuppetID());
        for(MushroomThread mt : mb.getLocation().getThreads())
            for(MushroomThread mt2 : mt.getThreads()) {
                if(mt2.getLocation().getSpores().size() > 3 && mt2.getLocation().getBody() == null)
                    growMushParam1.addItem(GameModel.gameObjects.getK(mt2.getLocation()));
            }
        //------------------------------------------------------------------------------

        configurePlaceholderComponents(placeholder2, growMushButton, growMushParam1);

        // Placeholder 3: Spread Spore
        JPanel placeholder3 = new JPanel();
        placeholder3.setLayout(new BoxLayout(placeholder3, BoxLayout.Y_AXIS));
        placeholder3.setBorder(BorderFactory.createCompoundBorder(placeholderBorder, innerPadding));
        spreadSporeButton = new JButton("Spread Spore");
        spreadSporeParam1 = new JComboBox<>();
        spreadSporeParam2 = new JComboBox<>(new String[]{"Haste","Slow","Mute","Para","Clone","Normal"});

        spreadSporeButton.addActionListener(e -> {
            cc.ArgumentManagement("/spreadspore " + vc.getCurrentPuppetID() + " " + spreadSporeParam1.getSelectedItem() + " " + ((String)spreadSporeParam2.getSelectedItem()).charAt(0));
            reDrawAll();
        });

        //------------------------------------------------------------------------------
        spreadSporeParam1.removeAllItems();
        MushroomBody mb2 = (MushroomBody) GameModel.gameObjects.getV(vc.getCurrentPuppetID());
        for(Tekton t : mb2.getLocation().getNeighbors()) {
            spreadSporeParam1.addItem(GameModel.gameObjects.getK(t));
        }
        //------------------------------------------------------------------------------

        configurePlaceholderComponents(placeholder3, spreadSporeButton, spreadSporeParam1, spreadSporeParam2);

        // Placeholder 4: Absorb Insect
        JPanel placeholder4 = new JPanel();
        placeholder4.setLayout(new BoxLayout(placeholder4, BoxLayout.Y_AXIS));
        placeholder4.setBorder(BorderFactory.createCompoundBorder(placeholderBorder, innerPadding));
        absorbInsectButton = new JButton("Absorb Insect");
        absorbInsectParam1 = new JComboBox<>(new String[]{"Thread 1"});
        absorbInsectButton.addActionListener(e -> {
            MushroomSpecies s = (MushroomSpecies)GameModel.gameObjects.getV(vc.getCurrentPlayerID());
            String lastMushroomID = GameModel.gameObjects.getK(s.checkMushroomBody().get(s.checkMushroomBody().size()-1));
            cc.ArgumentManagement("/absorbinsect " + absorbInsectParam1.getSelectedItem() + " " + lastMushroomID.substring(0, lastMushroomID.length() - 1)+s.checkMushroomBody().size());
            reDrawAll();
        });

        //------------------------------------------------------------------------------
        absorbInsectParam1.removeAllItems();
        MushroomBody mb3 = (MushroomBody) GameModel.gameObjects.getV(vc.getCurrentPuppetID());
        for(MushroomThread mt : mb3.getLocation().getThreads()) {
            if(mt.getLocation().getBody() == null && mt.getLocation().getInsect() != null)
                absorbInsectParam1.addItem(GameModel.gameObjects.getK(mt));
        }
        //------------------------------------------------------------------------------

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
        skipButton.addActionListener(e -> {
            Set<InsectSpecies> InsectSpeciesSet = new HashSet<>(GameModel.rovarasz.values());
            Set<MushroomSpecies> MushroomSpeciesSet = new HashSet<>(GameModel.gombasz.values());
            if(cc.getWhichPlayer() >= MushroomSpeciesSet.size()) { // if its insect turn
                playerStats.setText("Player " + (cc.getWhichPlayer() - GameModel.gombasz.size()) + ": Insect " + cc.getWhichPuppet());

                //------------------------------------------------------------------------------
                if(cutParam1 != null)
                    cutParam1.removeAllItems();
                Insect i = (Insect)GameModel.gameObjects.getV(vc.getCurrentPuppetID());
                Tekton t = i.getLocation();
                for(MushroomThread thread : t.getThreads()) {
                    if(i.canCutThread()) {
                        cutParam1.addItem(GameModel.gameObjects.getK(thread));
                    }
                }
                //------------------------------------------------------------------------------

                //------------------------------------------------------------------------------
                if(moveParam1 != null)
                    moveParam1.removeAllItems();
                Insect i2 = (Insect)GameModel.gameObjects.getV(vc.getCurrentPuppetID());
                for(Tekton t2 : i2.getLocation().getNeighborWithThread())
                    moveParam1.addItem(GameModel.gameObjects.getK(t2));
                //------------------------------------------------------------------------------

                if(cc.getWhichPlayer() == GameModel.gombasz.size())
                    switchPanels();
                if(cc.getWhichPuppet() >= ((InsectSpecies)GameModel.gameObjects.getV(vc.getCurrentPlayerID())).getInsects().size()) {// if the current puppet is the last thing the player own
                    cc.setWhichPuppet(1);//go to 1 puppet of next player
                    cc.setWhichPlayer(cc.getWhichPlayer()+1);
                    if(cc.getWhichPlayer() > MushroomSpeciesSet.size() + InsectSpeciesSet.size() - 1) { // if no next player,set to 1
                        cc.setRound(cc.getRound()+1);
                        cc.setWhichPlayer(1);
                        cc.setWhichPuppet(1);
                        //round.setText("round " + cc.getRound());
                        topPanel.remove(0);
                        round = new JLabel("round " + cc.getRound());
                        round.setFont(round.getFont().deriveFont(Font.BOLD, 14f));
                        topPanel.add(round);
                        reDrawAll();
                        return;
                    }
                }
                else
                    cc.setWhichPuppet(cc.getWhichPuppet()+1);
            }
            else {//if it mushroom turn

                playerStats.setText("Player " + (cc.getWhichPlayer() - 1) + ": Gomba " + cc.getWhichPuppet());

                //------------------------------------------------------------------------------
                growMushParam1.removeAllItems();
                MushroomBody mb4 = (MushroomBody) GameModel.gameObjects.getV(vc.getCurrentPuppetID()); //here class com.beingchilling.model.Insect cannot be cast to class com.beingchilling.model.MushroomBody (com.beingchilling.model.Insect and com.beingchilling.model.MushroomBody are in unnamed module of loader 'app')
                for(MushroomThread mt : mb4.getLocation().getThreads())
                    for(MushroomThread mt2 : mt.getThreads()) {
                        if(mt2.getLocation().getSpores().size() > 3 && mt2.getLocation().getBody() == null)
                            growMushParam1.addItem(GameModel.gameObjects.getK(mt2.getLocation()));
                    }
                //------------------------------------------------------------------------------

                //------------------------------------------------------------------------------
                spreadSporeParam1.removeAllItems();
                MushroomBody mb5 = (MushroomBody) GameModel.gameObjects.getV(vc.getCurrentPuppetID());
                for(Tekton t : mb5.getLocation().getNeighbors()) {
                    spreadSporeParam1.addItem(GameModel.gameObjects.getK(t));
                }
                //------------------------------------------------------------------------------

                //------------------------------------------------------------
                growThreadParam2.removeAllItems();
                growThreadParam1.removeAllItems();
                Set<Tekton> tektons2 = new HashSet<>();
                for(MushroomThread mt5 : ((MushroomBody) GameModel.gameObjects.getV(vc.getCurrentPuppetID())).getLocation().getThreads().get(0).getThreads()) {
                    growThreadParam1.addItem(GameModel.gameObjects.getK(mt5));
                    for(Tekton t5 : mt5.getLocation().getNeighbors()) {
                        if(!mt5.getLocation().getNeighborWithThread().contains(t5)) {
                            tektons2.add(t5);
                        }
                    }
                }
                for(Tekton t5 : tektons2) {
                    growThreadParam2.addItem(GameModel.gameObjects.getK(t5));
                }
                //-------------------------------------------------------------

                //------------------------------------------------------------------------------
                absorbInsectParam1.removeAllItems();
                MushroomBody mb6 = (MushroomBody) GameModel.gameObjects.getV(vc.getCurrentPuppetID());
                for(MushroomThread mt : mb6.getLocation().getThreads()) {
                    if(mt.getLocation().getBody() == null && mt.getLocation().getInsect() != null)
                        absorbInsectParam1.addItem(GameModel.gameObjects.getK(mt));
                }
                //------------------------------------------------------------------------------
                if(cc.getWhichPuppet() >= ((MushroomSpecies)GameModel.gameObjects.getV(vc.getCurrentPlayerID())).checkMushroomBody().size()) {// if the current puppet is the last thing the player own
                    cc.setWhichPuppet(1);//go to 1 puppet of next player
                    cc.setWhichPlayer(cc.getWhichPlayer()+1);//since for sure there is a insect behind no need boudary check
                    //PROBABLY BECUASE OF THIS (READ BELOW)
                }
                else
                    cc.setWhichPuppet(cc.getWhichPuppet()+1);
                if(cc.getWhichPlayer() == 1)
                    switchPanels();
            }

            reDrawAll();
        });
        endGameButton = new JButton("end game");
        endGameButton.addActionListener(e -> {
            System.exit(0);
        });
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
        playerStats = new JLabel("Player " + (cc.getWhichPlayer() - GameModel.gombasz.size()) + ": Insect " + cc.getWhichPuppet());
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

        JPanel placeholder1 = new JPanel();
        placeholder1.setLayout(new BoxLayout(placeholder1, BoxLayout.Y_AXIS));
        placeholder1.setBorder(BorderFactory.createCompoundBorder(placeholderBorder, innerPadding));
        moveButton = new JButton("Move");
        moveParam1 = new JComboBox<>(new String[]{"Tekton 1"});
        moveButton.addActionListener(e -> {
            cc.ArgumentManagement("/move " + vc.getCurrentPuppetID() + " " + moveParam1.getSelectedItem());
            reDrawAll();
        });

        configurePlaceholderComponents(placeholder1, moveButton, moveParam1);

        JPanel placeholder2 = new JPanel();
        placeholder2.setLayout(new BoxLayout(placeholder2, BoxLayout.Y_AXIS));
        placeholder2.setBorder(BorderFactory.createCompoundBorder(placeholderBorder, innerPadding));
        eatButton = new JButton("Eat");

        eatButton.addActionListener(e -> {
            cc.ArgumentManagement("/eat " + vc.getCurrentPuppetID());
            reDrawAll();
        });
        configurePlaceholderComponents(placeholder2, eatButton);

        JPanel placeholder3 = new JPanel();
        placeholder3.setLayout(new BoxLayout(placeholder3, BoxLayout.Y_AXIS));
        placeholder3.setBorder(BorderFactory.createCompoundBorder(placeholderBorder, innerPadding));
        cutButton = new JButton("Cut");
        cutParam1 = new JComboBox<>();

        cutButton.addActionListener(e -> {
            cc.ArgumentManagement("/cut " + vc.getCurrentPuppetID() + " " + cutParam1.getSelectedItem());
            reDrawAll();
        });
        configurePlaceholderComponents(placeholder3, cutButton, cutParam1);


        // Add placeholders to the grid layout panel
        middlePlaceholdersPanel.add(placeholder1);
        middlePlaceholdersPanel.add(placeholder2);
        middlePlaceholdersPanel.add(placeholder3);

        sidebarPanel.add(middlePlaceholdersPanel, BorderLayout.CENTER);


        //Ez a skip gomb elegge furan mukodik, fixeld pls (eloszor jol lep, majd utana csak 1esevel lep, furan nagyon)
        // --- Bottom Buttons (South) ---
        JPanel bottomButtonsPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 0));
        skipButton = new JButton("skip");
        skipButton.addActionListener(e -> {
            Set<InsectSpecies> InsectSpeciesSet = new HashSet<>(GameModel.rovarasz.values());
            Set<MushroomSpecies> MushroomSpeciesSet = new HashSet<>(GameModel.gombasz.values());
            if(cc.getWhichPlayer() >= MushroomSpeciesSet.size()) { // if its insect turn
                playerStats.setText("Player " + (cc.getWhichPlayer() - GameModel.gombasz.size()) + ": Insect " + cc.getWhichPuppet());

                //------------------------------------------------------------------------------
                cutParam1.removeAllItems();
                Insect i = (Insect)GameModel.gameObjects.getV(vc.getCurrentPuppetID());
                Tekton t = i.getLocation();
                for(MushroomThread thread : t.getThreads()) {
                    if(i.canCutThread()) {
                        cutParam1.addItem(GameModel.gameObjects.getK(thread));
                    }
                }
                //------------------------------------------------------------------------------

                //------------------------------------------------------------------------------
                moveParam1.removeAllItems();
                Insect i2 = (Insect)GameModel.gameObjects.getV(vc.getCurrentPuppetID());
                for(Tekton t2 : i2.getLocation().getNeighborWithThread())
                    moveParam1.addItem(GameModel.gameObjects.getK(t2));
                //------------------------------------------------------------------------------

                if(cc.getWhichPlayer() == GameModel.gombasz.size())
                    switchPanels();
                if(cc.getWhichPuppet() >= ((InsectSpecies)GameModel.gameObjects.getV(vc.getCurrentPlayerID())).getInsects().size()) {// if the current puppet is the last thing the player own
                    cc.setWhichPuppet(1);//go to 1 puppet of next player
                    cc.setWhichPlayer(cc.getWhichPlayer()+1);
                    if(cc.getWhichPlayer() > MushroomSpeciesSet.size() + InsectSpeciesSet.size() - 1) { // if no next player,set to 1
                        if(cc.getRound() >= 20) {
                            String winnerMessage = "Game Over! Maximum " + 20 + " rounds reached.\n\n--- Scores ---\n";

                            int maxNutrients = -1;
                            String insectWinnerInfo = "No insect players or scores.";
                            for (InsectSpecies is : GameModel.rovarasz.values().stream().distinct().toList()) {
                                String playerID = GameModel.gameObjects.getK(is);
                                int totalNutrients = 0;
                                for (Insect i7 : is.getInsects()) {
                                    totalNutrients += i7.getCurrentNutrient();
                                }
                                if (totalNutrients > maxNutrients) {
                                    maxNutrients = totalNutrients;
                                    insectWinnerInfo = "Insect Player " + playerID + ": " + totalNutrients + " nutrients.";
                                } else if (totalNutrients == maxNutrients && maxNutrients != -1) {
                                    insectWinnerInfo += "\nInsect Player " + playerID + ": " + totalNutrients + " nutrients (Tie).";
                                } else if (playerID != null && insectWinnerInfo.equals("No insect players or scores.")){
                                    insectWinnerInfo = "Insect Player " + playerID + ": " + totalNutrients + " nutrients.";
                                }
                            }

                            int maxMushroomScore = -1; // e.g. total count of mushrooms
                            String mushroomWinnerInfo = "No mushroom players or scores.";
                            for (MushroomSpecies ms : GameModel.gombasz.values().stream().distinct().toList()) {
                                String playerID = GameModel.gameObjects.getK(ms);
                                int score = ms.checkMushroomBody().size();
                                if (score > maxMushroomScore) {
                                    maxMushroomScore = score;
                                    mushroomWinnerInfo = "Mushroom Player " + playerID + ": " + score + " mushrooms.";
                                } else if (score == maxMushroomScore && maxMushroomScore != -1) {
                                    mushroomWinnerInfo += "\nMushroom Player " + playerID + ": " + score + " mushrooms (Tie).";
                                } else if (playerID != null && mushroomWinnerInfo.equals("No mushroom players or scores.")) {
                                    mushroomWinnerInfo = "Mushroom Player " + playerID + ": " + score + " mushrooms.";
                                }
                            }

                            winnerMessage += insectWinnerInfo + "\n" + mushroomWinnerInfo;

                            JOptionPane.showMessageDialog(frame, winnerMessage, "Game Over", JOptionPane.INFORMATION_MESSAGE);
                            System.exit(0);
                        }
                        cc.setRound(cc.getRound()+1);
                        cc.setWhichPlayer(1);
                        cc.setWhichPuppet(1);
                        //round.setText("round " + cc.getRound());
                        topPanel.remove(0);
                        round = new JLabel("round " + cc.getRound());
                        round.setFont(round.getFont().deriveFont(Font.BOLD, 14f));
                        topPanel.add(round);
                        reDrawAll();
                        return;
                    }
                }
                else
                    cc.setWhichPuppet(cc.getWhichPuppet()+1);
            }
            else {//if it mushroom turn
                if(cc.getWhichPlayer() == 1)
                    switchPanels();

                playerStats.setText("Player " + (cc.getWhichPlayer() - 1) + ": Gomba " + cc.getWhichPuppet());

                //------------------------------------------------------------------------------
                growMushParam1.removeAllItems();
                MushroomBody mb4 = (MushroomBody) GameModel.gameObjects.getV(vc.getCurrentPuppetID()); //here class com.beingchilling.model.Insect cannot be cast to class com.beingchilling.model.MushroomBody (com.beingchilling.model.Insect and com.beingchilling.model.MushroomBody are in unnamed module of loader 'app')
                for(MushroomThread mt : mb4.getLocation().getThreads())
                    for(MushroomThread mt2 : mt.getThreads()) {
                        if(mt2.getLocation().getSpores().size() > 3 && mt2.getLocation().getBody() == null)
                            growMushParam1.addItem(GameModel.gameObjects.getK(mt2.getLocation()));
                    }
                //------------------------------------------------------------------------------

                //------------------------------------------------------------------------------
                spreadSporeParam1.removeAllItems();
                MushroomBody mb5 = (MushroomBody) GameModel.gameObjects.getV(vc.getCurrentPuppetID());
                for(Tekton t : mb5.getLocation().getNeighbors()) {
                    spreadSporeParam1.addItem(GameModel.gameObjects.getK(t));
                }
                //------------------------------------------------------------------------------

                //------------------------------------------------------------
                growThreadParam2.removeAllItems();
                growThreadParam1.removeAllItems();
                Set<Tekton> tektons2 = new HashSet<>();
                for(MushroomThread mt5 : ((MushroomBody) GameModel.gameObjects.getV(vc.getCurrentPuppetID())).getLocation().getThreads().get(0).getThreads()) {
                    growThreadParam1.addItem(GameModel.gameObjects.getK(mt5));
                    for(Tekton t5 : mt5.getLocation().getNeighbors()) {
                        if(!mt5.getLocation().getNeighborWithThread().contains(t5)) {
                            tektons2.add(t5);
                        }
                    }
                }
                for(Tekton t5 : tektons2) {
                    growThreadParam2.addItem(GameModel.gameObjects.getK(t5));
                }
                //-------------------------------------------------------------

                //------------------------------------------------------------------------------
                absorbInsectParam1.removeAllItems();
                MushroomBody mb6 = (MushroomBody) GameModel.gameObjects.getV(vc.getCurrentPuppetID());
                for(MushroomThread mt : mb6.getLocation().getThreads()) {
                    if(mt.getLocation().getBody() == null && mt.getLocation().getInsect() != null)
                        absorbInsectParam1.addItem(GameModel.gameObjects.getK(mt));
                }
                //------------------------------------------------------------------------------
                if(cc.getWhichPuppet() >= ((MushroomSpecies)GameModel.gameObjects.getV(vc.getCurrentPlayerID())).checkMushroomBody().size()) {// if the current puppet is the last thing the player own
                    cc.setWhichPuppet(1);//go to 1 puppet of next player
                    cc.setWhichPlayer(cc.getWhichPlayer()+1);//since for sure there is a insect behind no need boudary check
                    //PROBABLY BECUASE OF THIS (READ BELOW)
                }
                else
                    cc.setWhichPuppet(cc.getWhichPuppet()+1);
            }

            reDrawAll();
        });
        endGameButton = new JButton("end game");
        endGameButton.addActionListener(e -> {
            System.exit(0);
        });
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

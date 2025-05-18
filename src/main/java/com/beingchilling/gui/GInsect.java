package com.beingchilling.gui;

import com.beingchilling.game.GameModel;
import com.beingchilling.model.Insect;
import com.beingchilling.model.Tekton;
import com.beingchilling.view.InsectView;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Path2D;

public class GInsect extends JComponent {

    InsectView insect;
    public GInsect(InsectView insect) {
        this.insect = insect;
    }
    @Override
    public void paint(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        super.paint(g);
        int coordX = ((GTekton)(GUI.objects.getV(insect.getLocation()))).getX();
        int coordY = ((GTekton)(GUI.objects.getV(insect.getLocation()))).getY();
        int squareSize = 20;
        g2d.setColor(new Color(139, 69, 19));
        g2d.fillRect(coordX - 10, coordY - 30, squareSize, squareSize);
        g2d.setColor(Color.BLACK);
        g2d.drawRect(coordX - 10, coordY - 30, squareSize, squareSize);
        g2d.setFont(new Font("Arial", Font.BOLD, 12));
        FontMetrics fm = g2d.getFontMetrics();
        String insectId = GameModel.gameObjects.getK((Insect)insect);
        //this add the Id next to the object
        g2d.drawString(insectId, coordX + 15, coordY - 15);
        g2d.setColor(Color.BLUE);
    }

}

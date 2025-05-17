package com.beingchilling.gui;

import com.beingchilling.game.GameModel;
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
        int coordX = GUI.objects.getV(insect.getLocation()).getX();
        int coordY = GUI.objects.getV(insect.getLocation()).getY();
        int squareSize = 20;
        g2d.setColor(new Color(139, 69, 19));
        g2d.fillRect(coordX, coordY, squareSize, squareSize);
        g2d.setColor(Color.BLACK);
        g2d.drawRect(coordX, coordY, squareSize, squareSize);
        FontMetrics fm = g2d.getFontMetrics();
        String insectId = GameModel.gameObjects.getK(this);
        //this add the Id next to the object
        g2d.drawString(insectId, coordX + 10, coordY);

    }

}

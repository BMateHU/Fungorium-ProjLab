package com.beingchilling.gui;

import com.beingchilling.game.GameModel;
import com.beingchilling.view.SporeView;
import com.beingchilling.view.TektonView;

import javax.swing.*;
import java.awt.*;

public class GSpore extends JComponent {

    SporeView spore;
    TektonView tekton;

    public GSpore(SporeView spore, TektonView tekton) {
        this.spore = spore;
        this.tekton = tekton;
    }

    @Override
    public void paint(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        super.paint(g);
        int spore = tekton.getSpores().size();
        int coordX = GUI.objects.getV(tekton).getX();
        int coordY = GUI.objects.getV(tekton).getY();
        int smallCircleRadius = 8;
        int smallCircleDiameter = smallCircleRadius * 2;
        g2d.setColor(Color.GREEN);
        // Position them in a small triangle pattern inside Circle
        int offsetY = 10;
        g2d.fillOval(coordX + GTekton.RADIUS + 10, coordY - offsetY, smallCircleDiameter, smallCircleDiameter); // Top small circle
        FontMetrics fm = g2d.getFontMetrics();
        //this add the spore number next to the object
        g2d.drawString(Integer.toString(spore), coordX + GTekton.RADIUS + 15, coordY - offsetY);
        g2d.setColor(Color.BLUE);
    }
}

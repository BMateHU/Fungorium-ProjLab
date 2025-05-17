package com.beingchilling.gui;

import com.beingchilling.game.GameModel;
import com.beingchilling.model.MushroomBody;
import com.beingchilling.view.InsectView;
import com.beingchilling.view.MushroomBodyView;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Path2D;

public class GMushroomBody extends JComponent {
    MushroomBodyView mushroom;

    public GMushroomBody(MushroomBodyView mushroom) {
        this.mushroom = mushroom;
    }

    @Override
    public void paint(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        super.paint(g);
        int coordX = GUI.objects.getV(mushroom.getLocation()).getX();
        int coordY = GUI.objects.getV(mushroom.getLocation()).getY();
        int triangleShapeSize = 25; // Size of the yellow triangle shape
        Path2D triangleShape = new Path2D.Double();
        // Define vertices relative to the center of circle 2
        triangleShape.moveTo(coordX, coordY - triangleShapeSize / 1.5);
        triangleShape.lineTo(coordX - triangleShapeSize / 2.0, coordY + triangleShapeSize / 3.0);
        triangleShape.lineTo(coordX + triangleShapeSize / 2.0, coordY + triangleShapeSize / 3.0);
        triangleShape.closePath();

        g2d.setColor(Color.YELLOW);
        g2d.fill(triangleShape);
        g2d.setColor(Color.BLACK);
        g2d.draw(triangleShape);
        FontMetrics fm = g2d.getFontMetrics();
        String mushroomId = GameModel.gameObjects.getK((MushroomBody)mushroom);
        //this add the Id next to the object
        g2d.drawString(mushroomId, coordX + 10, coordY);
        g2d.setColor(Color.BLUE);
    }
}

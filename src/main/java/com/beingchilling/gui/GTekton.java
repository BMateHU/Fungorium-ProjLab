package com.beingchilling.gui;

import com.beingchilling.game.GameModel;
import com.beingchilling.view.TektonView;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class GTekton extends JComponent {
    TektonView tekton;
    ArrayList<JComponent> spores = new ArrayList<>();
    JComponent mushroomBody;
    JComponent insect;
    public static final int RADIUS = 20;
    private int x, y; //this should mark center point of the circle, (as I saw the normal X and Y is the left upper corner of the circle (so just - radius)

    public GTekton(TektonView tekton) {
        this.tekton = tekton;
    }

    @Override
    public void paint(Graphics g) {
        if(!tekton.getSpores().isEmpty())
            tekton.getSpores().forEach(spore -> spores.add(GUI.objects.getV(spore)));
        if(tekton.getBody() != null)
            mushroomBody = GUI.objects.getV(tekton.getBody());
        if(tekton.getInsect() != null)
            insect = GUI.objects.getV(tekton.getInsect());

        super.paint(g);
        //kor
        g2d.setColor(Color.BLUE);
        g2d.setStroke(new BasicStroke(2));
        g2d.drawOval(x, y, RADIUS * 2, RADIUS * 2);
        FontMetrics fm = g2d.getFontMetrics();
        String tektonId = GameModel.gameObjects.getK(this);
        g2d.drawString(tektonId, x, y + RADIUS + 20);

        if(mushroomBody != null)
            mushroomBody.paint(g);
        if(insect != null)
            insect.paint(g);
        if(!spores.isEmpty())
            for(int i = 0; i < 4; i++) {
                spores.get(i).paint(g);
            }
    }

    @Override
    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    @Override
    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
}

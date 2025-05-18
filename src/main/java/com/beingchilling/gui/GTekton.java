package com.beingchilling.gui;

import com.beingchilling.game.GameModel;
import com.beingchilling.model.Spore;
import com.beingchilling.model.Tekton;
import com.beingchilling.view.SporeView;
import com.beingchilling.view.TektonView;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;
import java.awt.*;


public class GTekton extends JComponent {
    TektonView tekton;
    ArrayList<JComponent> spores = new ArrayList<>();
    JComponent mushroomBody;
    JComponent insect;
    public static final int RADIUS = 50;
    private int x, y; //this should mark center point of the circle, (as I saw the normal X and Y is the left upper corner of the circle (so just - radius)

    public GTekton(TektonView tekton) {
        this.tekton = tekton;
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2d = (Graphics2D) g;
        if(!tekton.getSpores().isEmpty()) {
            List<Spore> sp = tekton.getSpores();
            for(int i = 0 ; i < sp.size() ; i++) {
                spores.add(GUI.objects.getV(sp.get(i)));
            }
        }
        if(tekton.getBody() != null)
            mushroomBody = GUI.objects.getV(tekton.getBody());
        if(tekton.getInsect() != null)
            insect = GUI.objects.getV(tekton.getInsect());
        g2d.setColor(Color.BLUE);
        g2d.setStroke(new BasicStroke(2));
        g2d.drawOval(x- RADIUS, y- RADIUS, RADIUS * 2, RADIUS * 2);
        g2d.setColor(Color.WHITE);
        g2d.fillOval(x- RADIUS + 1, y- RADIUS + 1, RADIUS * 2 - 1, RADIUS * 2 - 1);
        g2d.setColor(Color.BLUE);
        FontMetrics fm = g2d.getFontMetrics();
        g2d.setFont(new Font("Arial", Font.BOLD, 12));
        String tektonId = GameModel.gameObjects.getK((Tekton)tekton);
        g2d.setColor(Color.BLACK);
        g2d.drawString(tektonId, x - 5, y + RADIUS + 20);
        g2d.setColor(Color.BLUE);
        if(mushroomBody != null)
            mushroomBody.paint(g);
        if(insect != null)
            insect.paint(g);
        if(!spores.isEmpty()) {
            spores.get(spores.size() -1 ).paint(g);
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
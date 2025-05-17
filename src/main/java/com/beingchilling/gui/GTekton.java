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
        Graphics2D g2d = (Graphics2D) g;
        tekton.getSpores().forEach(spore -> spores.add(GUI.objects.getV(spore)));
        mushroomBody = GUI.objects.getV(tekton.getBody());
        insect = GUI.objects.getV(tekton.getInsect());
        super.paint(g);
        //kor
        g2d.setColor(Color.BLUE);
        g2d.setStroke(new BasicStroke(2));
        g2d.drawOval(x, y, RADIUS * 2, RADIUS * 2);
        FontMetrics fm = g2d.getFontMetrics();
        String tektonId = GameModel.gameObjects.getK(this);
        g2d.drawString(tektonId, x, y + RADIUS + 20);

        mushroomBody.paint(g);
        insect.paint(g);
        spores.getFirst().paint(g);



        //spora szam kiiras

        //id kiiras tekton ala
        //FontMetrics fm = g2d.getFontMetrics();
        // String threadLabel = GameModel.gameObjects.getK(t2.getThreads().get(0));
        // int tekton1Width = fm.stringWidth(threadLabel);
        // g2d.drawString(threadLabel, x+(x-x2) - tekton1Width/2, y+(y-y2));
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

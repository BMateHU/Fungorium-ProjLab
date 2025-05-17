package com.beingchilling.gui;

import com.beingchilling.view.TektonView;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class GTekton extends JComponent {

    TektonView tekton;

    ArrayList<JComponent> spores = new ArrayList<>();
    JComponent mushroomBody;
    JComponent insect;

    private int x, y; //this should mark center point of the circle, (as I saw the normal X and Y is the left upper corner of the circle (so just - radius)

    public GTekton(TektonView tekton) {
        this.tekton = tekton;
    }

    @Override
    public void paint(Graphics g) {
        tekton.getSpores().forEach(spore -> spores.add(GUI.objects.getV(spore)));
        mushroomBody = GUI.objects.getV(tekton.getBody());
        insect = GUI.objects.getV(tekton.getInsect());

        super.paint(g);

        mushroomBody.paint(g);
        insect.paint(g);
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

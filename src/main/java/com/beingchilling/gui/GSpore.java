package com.beingchilling.gui;

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
        super.paint(g);
    }
}

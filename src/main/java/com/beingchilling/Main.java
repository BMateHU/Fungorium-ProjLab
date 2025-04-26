package com.beingchilling;

import com.beingchilling.controller.ControllerComponent;
import com.beingchilling.view.ViewComponent;

import java.io.IOException;
import java.io.InputStream;

public class Main {

    public static ViewComponent vc = new ViewComponent();
    public static ControllerComponent cc = new ControllerComponent(vc);

    public static void main(String[] args) throws IOException {
        vc.setControllerComponent(cc);
        try {
            InputStream url = Main.class.getClassLoader().getResourceAsStream("start.txt");
            assert url != null;
            cc.load(url);
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
        cc.gameLoop();
    }
}
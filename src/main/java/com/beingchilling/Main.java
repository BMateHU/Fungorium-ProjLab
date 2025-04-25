package com.beingchilling;

import com.beingchilling.controller.ControllerComponent;
import com.beingchilling.model.Insect;
import com.beingchilling.model.MushroomBody;
import com.beingchilling.view.ViewComponent;

import java.io.File;
import java.io.InputStream;
import java.net.URI;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static java.lang.System.exit;

public class Main {

    public static ViewComponent vc = new ViewComponent();
    public static ControllerComponent cc = new ControllerComponent(vc);

    public static void main(String[] args) {
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
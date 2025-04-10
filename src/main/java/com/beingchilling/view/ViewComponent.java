package com.beingchilling.view;

import com.beingchilling.controller.ControllerComponent;
import com.beingchilling.game.GameModel;

public class ViewComponent {

    public ControllerComponent controllerComponent;
    GameModel gameModel;

    public ViewComponent(GameModel gameModel) { this.gameModel = gameModel; }

    public void validate(String command) {
        switch(command) {
            default -> {
                //TO BE IMPLEMENTED
                break;
            }
        }
    }

    public void help() {

    }

    public void showId() {

    }

    public void showMap() {

    }

    public void unsuccessfulCommand() {

    }
}

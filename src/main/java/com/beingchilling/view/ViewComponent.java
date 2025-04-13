package com.beingchilling.view;

import com.beingchilling.controller.ControllerComponent;

public class ViewComponent {

    public ControllerComponent controllerComponent;

    public ViewComponent() {}

    public void setControllerComponent(ControllerComponent controllerComponent)
    {
        this.controllerComponent = controllerComponent;
    }

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

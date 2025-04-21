package com.beingchilling.view;

import com.beingchilling.controller.ControllerComponent;

//otherwise this is just command calls, easy to implement
public class ViewComponent {

    private ControllerComponent controllerComponent; //viewcomponent has controllercomponent (so eachother can call eachother)

    public ViewComponent() {}

    public void setControllerComponent(ControllerComponent controllerComponent)
    {
        this.controllerComponent = controllerComponent;
    }

    //validate is check if command correct -> then to controller, as new function HAVE TO which runs the methods which the command called
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

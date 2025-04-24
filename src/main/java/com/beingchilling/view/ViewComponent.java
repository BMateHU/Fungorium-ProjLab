package com.beingchilling.view;

import com.beingchilling.controller.ControllerComponent;

import java.util.Objects;

//otherwise this is just command calls, easy to implement
public class ViewComponent {

    private ControllerComponent controllerComponent; //viewcomponent has controllercomponent (so eachother can call eachother)

    public ViewComponent() {}

    public void setControllerComponent(ControllerComponent controllerComponent)
    {
        this.controllerComponent = controllerComponent;
    }

    //validate is check if command correct -> then to controller, as new function HAVE TO which runs the methods which the command called
    public boolean validate(String command) {
        String[] words = command.strip().split(" ");
        switch(words[0]) {
            case "/addplayer":
                if(words.length == 3)
                    return true;
                return false;
            case "/addtekton":
                if(words.length == 3) {
                    if(Objects.equals(words[2], "N") || "LS".equals(words[2]) || "TA".equals(words[2]) || "MT".equals(words[2]) || Objects.equals(words[2], "MS"))
                        return true;
                    return false;
                }
                if(words.length == 2)
                    return true;
                return false;
            case "/addinsect":
                if(words.length == 5) {
                    if(Objects.equals(words[2], "S") || "H".equals(words[2]) || "M".equals(words[2]) || "P".equals(words[2]) || "C".equals(words[2]) || "N".equals(words[2]))
                        return true;
                    return false;
                }
                if(words.length == 4)
                    return true;
                return false;
            case "/addmush":
                if(words.length == 7) {
                    if (Integer.parseInt(words[5]) <= 100 && Integer.parseInt(words[5]) >= 1 && Integer.parseInt(words[6]) <= 100 && Integer.parseInt(words[6]) >= 1)
                        return true;
                    return false;
                }
                if(words.length == 6) {
                    if (Integer.parseInt(words[5]) <= 100 && Integer.parseInt(words[5]) >= 1)
                        return true;
                    return false;
                }
                if(words.length == 5)
                    return true;
                return false;
            case "/addspore":
                if(words.length == 5) {
                    if ((Objects.equals(words[3], "S") || "H".equals(words[3]) || "M".equals(words[3]) || "P".equals(words[3]) ||
                            "C".equals(words[3]) || "N".equals(words[3]) && Integer.parseInt(words[4]) <= 10 && Integer.parseInt(words[4]) >= 0))
                        return true;
                    return false;
                }
                if(words.length == 4) {
                    if (Objects.equals(words[3], "S") || "H".equals(words[3]) || "M".equals(words[3]) || "P".equals(words[3]) || "C".equals(words[3]) || "N".equals(words[3]))
                        return true;
                    return false;
                }
                if(words.length == 3)
                    return true;
                return false;
            case "/addthread":
                if(words.length == 3)
                    return true;
                return false;
            case "/setneighbour":
                if(words.length == 3)
                    return true;
                return false;
            case "/connectthread":
                if(words.length == 3)
                    return true;
                return false;
            case "/showID", "/help", "/absorb", "/showMap":
                return true;
            case "/break":
                if(words.length >= 3)
                    return true;
                return false;
            case "/growthread", "growmush":
                if(words.length == 4)
                    return true;
                return false;
            case "/spreadspore":
                if(words.length == 3)
                    return true;
                if(words.length == 4) {
                    if ("S".equals(words[3]) || "H".equals(words[3]) || "M".equals(words[3]) || "P".equals(words[3]) || "C".equals(words[3]) || "N".equals(words[3]))
                        return true;
                    return false;
                }
                return false;
            case "/absorbinsect":
                if(words.length == 3)
                    return true;
                return false;
            case "/cut":
                if(words.length == 3)
                    return true;
                return false;
            case "/eat":
                if(words.length == 2)
                    return true;
                return false;
            case "move":
                if(words.length == 3)
                    return true;
                return false;
            case "/random":
                if(words.length == 2) {
                    if(Objects.equals(words[1], "on") || "off".equals(words[1]))
                        return true;
                    return false;
                }
                return false;
            case "/load":
                if(words.length == 2)
                    return true;
                return false;
        }
        return false;
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

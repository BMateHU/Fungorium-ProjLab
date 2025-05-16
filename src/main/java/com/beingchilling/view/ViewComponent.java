package com.beingchilling.view;

import com.beingchilling.controller.ControllerComponent;
import com.beingchilling.game.GameModel;
import com.beingchilling.model.Insect;
import com.beingchilling.model.InsectSpecies;
import com.beingchilling.model.MushroomBody;
import com.beingchilling.model.MushroomSpecies;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

//otherwise this is just command calls, easy to implement
public class ViewComponent {

    /**
     * Kontoller Komponent,azért hogy a viewComponent elréje a Kontoller Komponentet
     */
    private ControllerComponent controllerComponent;

    public ViewComponent() {}

    public void setControllerComponent(ControllerComponent controllerComponent)
    {
        this.controllerComponent = controllerComponent;
    }

    /**
     *  A id ellenörző kommand hogy helyes-e
     */
    //validate is check if command correct -> then to controller, as new function HAVE TO which runs the methods which the command called
    public boolean validate(String command) {
        String[] words = command.strip().split(" ");
        switch(words[0].toLowerCase()) {
            case "/addplayer":
                if(words.length == 3 && ("R".equals(words[2]) || "G".equals(words[2])))
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
                    if(Objects.equals(words[4], "S") || "H".equals(words[4]) || "M".equals(words[4]) || "P".equals(words[4]) || "C".equals(words[4]) || "N".equals(words[4]))
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
            case "/showid", "/help", "/absorb", "/showmap":
                return true;
            case "/break":
                if(words.length >= 3)
                    return true;
                return false;
            case "/growthread", "/growmush":
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
            case "/move":
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

    /**
     * Ki írja az összes kommandokat helyes formátumban
     */
    public void help() {
        System.out.println("Összes parancs:");
        System.out.println("/showid - Jelenlegi objektumok ID-jainak kilistázása");
        System.out.println("/help - Parancslista megjelenítése");
        System.out.println("/absorb [tekton ID ...] - Gombafonalak felszívása tektonon");
        System.out.println("/growthread <source thread ID> <new thread ID> <tekton ID> - Gombafonal növesztése");
        System.out.println("/growmush <player ID> <mushroom ID> <tekton ID> - Gombatest növesztése");
        System.out.println("/spreadspore <mushroom ID> <tekton ID> [spore type] - Spóra szórása gombatestből");
        System.out.println("/absorbinsect <thread ID> <new mushroom ID> - Rovar felszívása fonal által");
        System.out.println("/cut <insect ID> <thread ID> - Gombafonal elvágása rovarral");
        System.out.println("/eat <insect ID> - Rovar spórát eszik a tektonon");
        System.out.println("/move <insect ID> <tekton ID> - Rovar mozgatása másik tektonra");
        System.out.println("/load - Előre megírt pálya betöltése");
        System.out.println("/showmap - Jelenlegi pályaelemek kilistázása");
    }

    /**
     * Az összes regisztrált objektum ID-ját kiirja
     */
    public void showId() {
        System.out.println("Az összes regisztrált objektum ID-ja:");
        for (String id : GameModel.gameObjects.keySet()) {
            System.out.println("- " + id);
        }
    }

    /**
     *  A pályán lévő Tekton objektumok ID-jait kiírja
     */
    public void showMap() {
        System.out.println("A pályán lévő Tekton objektumok ID-jai:");
        for (String id : GameModel.map.tektonList.keySet()) {
            System.out.println("- " + id);
        }
    }

    /**
     * Ha rossz kommandot írsz be akkor ez hívódik meg
     */
    public void unsuccessfulCommand() {
        System.out.println("Nincs ilyen parancs!");
    }

    public String getCurrentPlayerID(){
        Set<InsectSpecies> InsectSpeciesSet = new HashSet<>(GameModel.rovarasz.values());
        Set<MushroomSpecies> MushroomSpeciesSet = new HashSet<>(GameModel.gombasz.values());
        if(controllerComponent.getWhichPlayer() >= MushroomSpeciesSet.size()) {
            //Set<InsectSpecies> InsectSpeciesSet = new HashSet<>(GameModel.rovarasz.values());
            InsectSpecies is = (InsectSpecies)InsectSpeciesSet.toArray()[controllerComponent.getWhichPlayer()-GameModel.gombasz.size()];
            return GameModel.gameObjects.getK(is);
        }
        else {
            //Set<MushroomSpecies> MushroomSpeciesSet = new HashSet<>(GameModel.gombasz.values());
            MushroomSpecies ms = (MushroomSpecies) MushroomSpeciesSet.toArray()[controllerComponent.getWhichPlayer()-1];
            return GameModel.gameObjects.getK(ms);
        }
    }

    public String getCurrentPuppetID()
    {
        Set<MushroomSpecies> MushroomSpeciesSet = new HashSet<>(GameModel.gombasz.values());
        if(controllerComponent.getWhichPlayer() >= MushroomSpeciesSet.size()) {
            Insect i = ((InsectSpecies) GameModel.gameObjects.getV(getCurrentPlayerID())).getInsects().get(controllerComponent.getWhichPuppet() - 1);
            return GameModel.gameObjects.getK(i);
        }
        else {
            MushroomBody m = ((MushroomSpecies)GameModel.gameObjects.getV(getCurrentPlayerID())).checkMushroomBody().get(controllerComponent.getWhichPuppet() - 1);
            return GameModel.gameObjects.getK(m);
        }
    }
}

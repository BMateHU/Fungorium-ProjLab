package com.beingchilling.controller;

import com.beingchilling.game.GameModel;
import com.beingchilling.model.*;
import com.beingchilling.view.ViewComponent;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

//otherwise this is just command calls, easy to implement
public class ControllerComponent {

    private final ViewComponent viewComponent;

    public ControllerComponent(ViewComponent viewComponent) {
        this.viewComponent = viewComponent;
    }

    public void ArgumentManagement(String command) {
        if(!viewComponent.validate(command))
            return; // i guess exception
        String[] words = command.strip().split(" ");
            switch(words[0]) {
                case "/addplayer":
                    if(Objects.equals(words[2], "R")) {
                        GameModel.gameObjects.put(words[1], new InsectSpecies());
                    }
                    else {
                        GameModel.gameObjects.put(words[1], new MushroomSpecies());
                    }
                    break;
                case "/addtekton":
                    if(words.length == 2) {
                        GameModel.map.tektonList.put(words[1], new Tekton());
                        GameModel.gameObjects.put(words[1], new Tekton());
                    }
                    else {
                        switch(words[2]) {
                            case "N":
                                GameModel.map.tektonList.put(words[1], new Tekton());
                                GameModel.gameObjects.put(words[1], new Tekton());
                                break;
                            case "LS":
                                GameModel.map.tektonList.put(words[1], new LifeSupportTekton());
                                GameModel.gameObjects.put(words[1], new LifeSupportTekton());
                                break;
                            case "TA":
                                GameModel.map.tektonList.put(words[1], new ThreadAbsorbTekton());
                                GameModel.gameObjects.put(words[1], new ThreadAbsorbTekton());
                                break;
                            case "MT":
                                GameModel.map.tektonList.put(words[1], new MultiThreadTekton());
                                GameModel.gameObjects.put(words[1], new MultiThreadTekton());
                                break;
                            case "MS":
                                GameModel.map.tektonList.put(words[1], new MushroomlessTekton());
                                GameModel.gameObjects.put(words[1], new MushroomlessTekton());
                                break;
                        }
                    }
                    break;
                case "/addinsect":
                    Insect newInsect = new Insect();
                    if(words.length == 5) {
                        switch (words[4]) {
                            case "S":
                                newInsect.slowEffect();
                                break;
                            case "H":
                                newInsect.hasteEffect();
                                break;
                            case "M":
                                newInsect.muteEffect();
                                break;
                            case "P":
                                newInsect.paraEffect();
                                break;
                            case "C":
                                newInsect.cloneEffect();
                                break;
                            default:
                                break;
                        }
                    }
                    GameModel.gameObjects.put(words[2], newInsect);
                    ((InsectSpecies)GameModel.gameObjects.getV(words[1])).addInsect(newInsect);
                    ((Tekton)GameModel.gameObjects.getV(words[3])).addInsect(newInsect);
                    break;
                case "/addmush":
                    MushroomBody newMushroom = new MushroomBody(GameModel.map.tektonList.get(words[4]));
                    if(words.length > 4) {
                        newMushroom.setBodyAge(Integer.parseInt(words[5]));
                        if(words.length > 5) {
                            newMushroom.setSporeNumber(Integer.parseInt(words[6]));
                        }
                    }
                    GameModel.gameObjects.put(words[2], newMushroom);
                    ((MushroomSpecies)GameModel.gameObjects.getV(words[1])).addMushroomBody(newMushroom);
                    GameModel.map.tektonList.get(words[4]).addMushroom(newMushroom);
                    MushroomThread newThread = new MushroomThread();
                    GameModel.gameObjects.put(words[3], newThread);
                    GameModel.map.tektonList.get(words[4]).addThread(newThread);
                    break;
                case "/addspore":
                    int nutrient = 5;
                    Tekton tekton = null;
                    if(words.length > 2)
                        tekton = GameModel.map.tektonList.get(words[1]);
                    Spore spore;
                    if(words.length > 4)
                        nutrient = Integer.parseInt(words[5]);
                    if(words.length > 3) {
                        spore = switch (words[3]) {
                            case "S" -> new SlowSpore(nutrient); //only has ctor with sporeNutrient so why is sporeNutrient optional
                            case "H" -> new HasteSpore(nutrient);
                            case "M" -> new MuteSpore(nutrient);
                            case "P" -> new ParaSpore(nutrient);
                            case "C" -> new CloneSpore(nutrient);
                            default -> new Spore(nutrient);
                        };
                        GameModel.gameObjects.put(words[2], spore);
                        tekton.addSpore(spore);
                    }
                    break;
                case "/addthread":
                    MushroomThread newThread2 = new MushroomThread();
                    GameModel.gameObjects.put(words[2], newThread2);
                    GameModel.map.tektonList.get(words[1]).addThread(newThread2);
                    break;
                case "/setneighbour":
                    GameModel.map.tektonList.get(words[1]).addNeighbor(GameModel.map.tektonList.get(words[2]));
                    GameModel.map.tektonList.get(words[2]).addNeighbor(GameModel.map.tektonList.get(words[1]));
                    break;
                case "/connectthread":
                    ((MushroomThread)GameModel.gameObjects.getV(words[1])).addThread(((MushroomThread)GameModel.gameObjects.getV(words[2])));
                    break;
                case "/showID":
                    viewComponent.showId();
                    break;
                case "/help":
                    viewComponent.help();
                    break;
                case "/absorb":
                    if(words.length > 1) {
                        for(int i = 1; i < words.length; i++) {
                            GameModel.map.tektonList.get(words[i]).absorb();
                        }
                    }
                    else {
                        for (Tekton value : GameModel.map.tektonList.values()) {
                            value.absorb();
                        }
                    }
                    break;
                case "/showMap":
                    viewComponent.showMap();
                    break;
                case "/break":
                    breakTekton(GameModel.map.tektonList.get(words[1]), words[2]);
                    if(words.length > 3) {
                        String[] neighbours = words[3].trim().split(", ");
                        List<Tekton> newAdd = new ArrayList<>();
                        for(String str : neighbours) {
                            newAdd.add(GameModel.map.tektonList.get(str));
                        }
                        GameModel.map.tektonList.get(words[1]).updateNeighbor(newAdd, new ArrayList<>());
                    }
                    break;
                case "/growthread":
                    MushroomThread newThread3 = new MushroomThread();
                    GameModel.gameObjects.put(words[2], newThread3);
                    growThread((MushroomThread) GameModel.gameObjects.getV(words[1]), GameModel.map.tektonList.get(words[3]), words[2]);
                    break;
                case "/growmush":
                    MushroomBody mb = new MushroomBody(GameModel.map.tektonList.get(words[3]));
                    GameModel.gameObjects.put(words[2], mb);
                    GameModel.map.tektonList.get(words[3]).growMushroomBody(((MushroomSpecies)GameModel.gameObjects.getV(words[1])));
                    ((MushroomSpecies)GameModel.gameObjects.getV(words[1])).addMushroomBody(mb);
                    break;
                case "/spreadspore":
                    break;
                case "/absorbinsect":
                    ((MushroomThread)GameModel.gameObjects.getV(words[1])).absorbInsect(); //we either need to make it return the mushroom that it grew or we cant add it
                    break;
                case "/cut":
                    ((Insect)GameModel.gameObjects.getV(words[1])).insectCut(((MushroomThread)GameModel.gameObjects.getV(words[2])));
                    break;
                case "/eat":
                    ((Insect)GameModel.gameObjects.getV(words[1])).insectEat();
                    break;
                case "/move":
                    ((Insect)GameModel.gameObjects.getV(words[1])).insectMove(GameModel.map.tektonList.get(words[2]));
                    break;
                case "/random":
                    GameModel.randomSwitch = false;
                    break;
                case "/load":
                    URL url = this.getClass().getResource("");
                    File file = new File(url.getPath());
                    load(file);
                    break;
        }
    }

    public void gameLoop() {

    }

    public void growThread(MushroomThreadController mtC, TektonController target, String newThread) {
        GameModel.gameObjects.put(newThread, target.toView().getThreads().getLast());
        mtC.toView().checkOwner().growThread((MushroomThread)mtC, (Tekton)target);
    }

    public void growMushroom(MushroomBodyController newMushroom, TektonController target) {
        //TO BE IMPLEMENTED
        target.growMushroomBody(null);
    }

    public void spreadSpore(MushroomBodyController source, TektonController target) {
        source.spreadSpore((Tekton)target);
    }

    public void absorbInsect(MushroomThreadController source, MushroomBodyController newMushroom) {
        source.absorbInsect();
        //uj mushroombodyt a mushroomspeciesben be kell adni
        //Ide kell vagy return mushroom vagy copy mushroom

    }

    private void breakTekton(TektonController source, String tektonID) {
        for(Spore spore : source.toView().getSpores())
            GameModel.gameObjects.removeByV(spore);
        for(MushroomThread mt : source.toView().getThreads())
            GameModel.gameObjects.removeByV(mt);
        Tekton newTekton = source.tektonBreak();
        GameModel.map.tektonList.put(tektonID, newTekton);
    }

    private void absorb(List<TektonController> tektons) {
        for(TektonController t : tektons) {
            t.absorb();
        }
    }

    public void cut(InsectController insect, MushroomThreadController target) {
        insect.insectCut((MushroomThread) target);
    }

    public void eat(InsectController insect) {
        insect.insectEat();
    }

    public void move(InsectController insect, TektonController target) {
        insect.insectMove((Tekton) target);
    }

    public void load(File file) {
        try {
            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);
            while(br.ready()) {
                String command2 = br.readLine();
                if(viewComponent.validate(command2))
                    ArgumentManagement(command2);
                else
                    return;
                }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

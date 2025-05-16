package com.beingchilling.controller;

import com.beingchilling.game.GameModel;
import com.beingchilling.model.*;
import com.beingchilling.view.ViewComponent;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import java.util.Objects;


/// Kontroller részét valósítja meg a modellnek
public class ControllerComponent implements IFactory {

    /// A kontroller hozzáfér a viewhoz
    private final ViewComponent viewComponent;

    private int round, whichPlayer, whichPuppet;

    /// Kontroller konstruktora
    public ControllerComponent(ViewComponent viewComponent) {
        this.viewComponent = viewComponent;
    }

    /**
     * A parancsok alapján futtatja a függvényeket és menedzseli ezeket.
     *
     * @param command Ez egy parancs, amit a felhasználó ír a játék során
     */
    public void ArgumentManagement(String command) {
        if(!viewComponent.validate(command)) {
            viewComponent.unsuccessfulCommand();
            return;
        }
        String[] words = command.strip().split(" ");
            switch(words[0].toLowerCase()) {
                case "/addplayer":
                    if(Objects.equals(words[2], "R")) {
                        GameModel.gameObjects.put(words[1], new InsectSpecies());
                    }
                    else {
                        GameModel.gameObjects.put(words[1], new MushroomSpecies());
                    }
                    break;
                case "/addtekton":
                    Tekton t;
                    if(words.length == 2) {
                        t = new Tekton();
                        GameModel.map.tektonList.put(words[1], t);
                        GameModel.gameObjects.put(words[1], t);
                    }
                    else {
                        switch(words[2]) {
                            case "N":
                                t = new Tekton();
                                GameModel.map.tektonList.put(words[1], t);
                                GameModel.gameObjects.put(words[1], t);
                                break;
                            case "LS":
                                t = new LifeSupportTekton();
                                GameModel.map.tektonList.put(words[1], t);
                                GameModel.gameObjects.put(words[1], t);
                                break;
                            case "TA":
                                t = new ThreadAbsorbTekton();
                                GameModel.map.tektonList.put(words[1], t);
                                GameModel.gameObjects.put(words[1], t);
                                break;
                            case "MT":
                                t = new MultiThreadTekton();
                                GameModel.map.tektonList.put(words[1], t);
                                GameModel.gameObjects.put(words[1], t);
                                break;
                            case "MS":
                                t = new MushroomlessTekton();
                                GameModel.map.tektonList.put(words[1], t);
                                GameModel.gameObjects.put(words[1], t);
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
                    InsectSpecies s = (InsectSpecies)GameModel.gameObjects.getV(words[1]);
                    s.addInsect(newInsect);
                    ((Tekton)GameModel.gameObjects.getV(words[3])).addInsect(newInsect);
                    newInsect.setLocation((Tekton)GameModel.gameObjects.getV(words[3]));
                    GameModel.rovarasz.put(newInsect,s);
                    break;
                case "/addmush":
                    MushroomBody newMushroom = new MushroomBody(GameModel.map.tektonList.get(words[4]));
                    newMushroom.setBodyAge(3);
                    newMushroom.setSporeNumber(3);
                    if(words.length > 5) {
                        newMushroom.setBodyAge(Integer.parseInt(words[5]));
                        if(words.length > 6) {
                            newMushroom.setSporeNumber(Integer.parseInt(words[6]));
                        }
                    }
                    GameModel.gameObjects.put(words[2], newMushroom);
                    MushroomSpecies owner = (MushroomSpecies)GameModel.gameObjects.getV(words[1]);
                    owner.addMushroomBody(newMushroom);
                    GameModel.gombasz.put(newMushroom,owner);

                    GameModel.map.tektonList.get(words[4]).addMushroom(newMushroom);
                    MushroomThread newThread = new MushroomThread();
                    newThread.setLifeSupport(true);
                    GameModel.gameObjects.put(words[3], newThread);
                    GameModel.map.tektonList.get(words[4]).addThread(newThread);
                    break;
                case "/addspore":
                    int nutrient = 5;
                    Tekton tekton = GameModel.map.tektonList.get(words[1]);
                    Spore spore = new Spore(nutrient);
                    if(words.length > 4)
                        nutrient = Integer.parseInt(words[5]);
                    if(words.length > 3) {
                        spore = switch (words[3]) {
                            case "S" -> new SlowSpore(nutrient);
                            case "H" -> new HasteSpore(nutrient);
                            case "M" -> new MuteSpore(nutrient);
                            case "P" -> new ParaSpore(nutrient);
                            case "C" -> new CloneSpore(nutrient);
                            default -> new Spore(nutrient);
                        };
                    }
                    GameModel.gameObjects.put(words[2], spore);
                    tekton.addSpore(spore);
                    break;
                case "/addthread":
                    MushroomThread newThread2 = new MushroomThread();
                    if(GameModel.map.tektonList.get(words[1]).addThread(newThread2))
                        GameModel.gameObjects.put(words[2], newThread2);
                    break;
                case "/setneighbour":
                    GameModel.map.tektonList.get(words[1]).addNeighbor(GameModel.map.tektonList.get(words[2]));
                    GameModel.map.tektonList.get(words[2]).addNeighbor(GameModel.map.tektonList.get(words[1]));
                    break;
                case "/connectthread":
                    ((MushroomThread)GameModel.gameObjects.getV(words[1])).addThread(((MushroomThread)GameModel.gameObjects.getV(words[2])));
                    break;
                case "/showid":
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
                case "/showmap":
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
                    growThread((MushroomThread) GameModel.gameObjects.getV(words[1]), GameModel.map.tektonList.get(words[3]), words[2]);
                    break;
                case "/growmush":
                    Tekton target = GameModel.map.tektonList.get(words[3]);
                    MushroomSpecies ms = (MushroomSpecies)GameModel.gameObjects.getV(words[1]);
                    growMushroom(ms, target, words[2]);
                    break;
                case "/spreadspore":
                    String type = "";
                    if(words.length == 4)
                        type = words[3];
                    spreadSpore((MushroomBody)GameModel.gameObjects.getV(words[1]), (Tekton)GameModel.gameObjects.getV(words[2]), type);
                    break;
                case "/absorbinsect":
                    absorbInsect((MushroomThread)GameModel.gameObjects.getV(words[1]), words[2]);
                    break;
                case "/cut":
                    if(GameModel.gameObjects.getV(words[2]) == null)
                        break;//cause meaning rovar tring to cut a not existed fonal
                    cut((Insect)GameModel.gameObjects.getV(words[1]),(MushroomThread)GameModel.gameObjects.getV(words[2]));
                    break;
                case "/eat":
                    eat((Insect)GameModel.gameObjects.getV(words[1]));
                    break;
                case "/move":
                    move((Insect) GameModel.gameObjects.getV(words[1]), GameModel.map.tektonList.get(words[2]));
                    break;
                case "/random":
                    GameModel.randomSwitch = !GameModel.randomSwitch;
                    System.out.println("Jelen esetben a random: " + GameModel.randomSwitch);
                    break;
                case "/load":
                    load(getClass().getClassLoader().getResourceAsStream("start.txt"));
                    break;
        }
    }

    /**
     * Ez egy függvény amely továbbítja a modellbeli részhez.
     * @param mtC MushroomThread amelyet továbbnövesztünk
     * @param target Tekton melyre továbbnő a thread
     * @param newThread Az új thread ID-ja
     */
    public void growThread(MushroomThreadController mtC, TektonController target, String newThread) {
        if(mtC.toView().checkOwner().growThread((MushroomThread)mtC, (Tekton)target))
            GameModel.gameObjects.put(newThread, target.toView().getThreads().get(target.toView().getThreads().size()-1));
        else {
            System.out.println("Nem sikerült a fonal novesztes!");
            
        }
        for(Tekton t : ((Tekton) target).getNeighbors())
        {
            if(!t.getThreads().isEmpty())
            {
                for(MushroomThread MT:t.getThreads())
                {
                    if(!GameModel.gameObjects.containsV(MT))
                    {
                        String id = newThread + ".ran";
                        GameModel.gameObjects.put(id,MT);
                    }
                }
            }
        }
    }

    /**
     * Ez egy függvény amely továbbítja a modellbeli részhez.
     * @param ms A gombának a faja.
     * @param target Tekton amelyen növeszteni szeretné a játékos
     * @param id Az új gomba ID-ja
     */
    public void growMushroom(MushroomSpecies ms, TektonController target, String id) {
        if(target.growMushroomBody(ms)) {
            GameModel.gameObjects.put(id, ms.checkMushroomBody().get(ms.checkMushroomBody().size()-1));
            GameModel.gombasz.put(ms.checkMushroomBody().get(ms.checkMushroomBody().size()-1), ms);
        }
        else {
            System.out.println("Nem sikerült");
            
        }
    }

    /**
     * Ez egy függvény amely továbbítja a modellbeli részhez.
     * @param source MushroomBody amelyből lőjjük a spórát
     * @param target Tekton amelyre lőjjük a spórát
     * @param type Spóra típusa
     */
    public void spreadSpore(MushroomBodyController source, TektonController target, String type) {
        Random rnd = new Random();
        Spore sp;
        if(GameModel.randomSwitch) {
            sp = switch (type) {
                case "S" -> new SlowSpore(rnd.nextInt(10));
                case "H" -> new HasteSpore(rnd.nextInt(10));
                case "M" -> new MuteSpore(rnd.nextInt(10));
                case "P" -> new ParaSpore(rnd.nextInt(10));
                case "C" -> new CloneSpore(rnd.nextInt(10));
                default -> new Spore(rnd.nextInt(10));
            };
        }
        else {
            sp = switch (type) {
                case "S" -> new SlowSpore(5);
                case "H" -> new HasteSpore(5);
                case "M" -> new MuteSpore(5);
                case "P" -> new ParaSpore(5);
                case "C" -> new CloneSpore(5);
                default -> new Spore(5);
            };
        }
        GameModel.gameObjects.put((GameModel.gameObjects.getK(source) + "_spore" + source.toView().getSporeNumber()), sp);
        if(!source.spreadSpore((Tekton)target, sp)) {
            GameModel.gameObjects.removeByV(sp);
            System.out.println("Spóraszórás sikertelen.");
            
        }
    }

    /**
     * Ez egy függvény amely továbbítja a modellbeli részhez.
     * @param source A gombafonal amelynél szívja fel a player a rovart.
     * @param newMushroom Az új gomba ID-je
     */
    public void absorbInsect(MushroomThreadController source, String newMushroom) {
        try {
            MushroomBody mb = source.absorbInsect();
            GameModel.gameObjects.put(newMushroom, mb);
            GameModel.gombasz.put(mb, GameModel.gombasz.get(source.checkOwner()));
            GameModel.gombasz.get(source.checkOwner()).addMushroomBody(mb);
        }
        catch (NullPointerException npe) {
            System.out.println(npe.getMessage());
            
        }
    }

    /**
     * Ez egy függvény amely továbbítja a modellbeli részhez.
     * @param source A tekton amelyet eltörünk
     * @param tektonID Az új tekton id-ja
     */
    private void breakTekton(TektonController source, String tektonID) {
        Tekton newTekton = source.tektonBreak();
        if(newTekton != null) {
            for (Spore spore : source.toView().getSpores())
                GameModel.gameObjects.removeByV(spore);
            for (MushroomThread mt : source.toView().getThreads())
                GameModel.gameObjects.removeByV(mt);
            GameModel.map.tektonList.put(tektonID, newTekton);
            GameModel.gameObjects.put(tektonID, newTekton);
        }
    }

    /**
     * Ez egy függvény amely továbbítja a modellbeli részhez.
     * @param insect A rovar amellyel vágjuk a fonalat
     * @param target A fonal amelyet elvágunk
     */
    public void cut(InsectController insect, MushroomThreadController target) {
        if(!insect.insectCut((MushroomThread) target)) {
            System.out.println("Sikertelen vagas!");
            
        }
    }

    /**
     * Ez egy függvény amely továbbítja a modellbeli részhez.
     * @param insect A rovar amely enni fog
     */
    public void eat(InsectController insect) {
        if(insect.toView().getLocation().getSpores().isEmpty()) {
            
            return;
        }
        Spore s = insect.toView().getLocation().getSpores().get(insect.toView().getLocation().getSpores().size()-1);
        try {
            insect.insectEat();
        } catch (ArrayIndexOutOfBoundsException aioobe) {
            System.out.println("Nem tud enni!");
            
            return;
        }
        GameModel.gameObjects.removeByV(s);
        for(Tekton t : insect.toView().getLocation().getNeighbors())
        {
            if(!GameModel.rovarasz.containsKey(t.getInsect()))
            {
                GameModel.gameObjects.put(GameModel.gameObjects.getK(insect)+".clone",t.getInsect());
                GameModel.rovarasz.put(t.getInsect(),GameModel.rovarasz.get(insect));
                GameModel.rovarasz.get(insect).addInsect(t.getInsect());
            }
        }
    }

    /**
     * Ez egy függvény amely továbbítja a modellbeli részhez.
     * @param insect A rovar amely mozogni fog
     * @param target A tekton, amelyre mozogni fog
     */
    public void move(InsectController insect, TektonController target) {
        if(!insect.insectMove((Tekton) target)) {
            System.out.println("Sikertelen mozgas!");
            
        }
    }

    /**
     * Betölt egy fájlt a GameModelbe
     * @param fis A fájl amit betöltnük
     */
    public void load(InputStream fis) {
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(fis));
            while(br.ready()) {
                String command2 = br.readLine();
                if(viewComponent.validate(command2))
                    ArgumentManagement(command2);
                else
                    return;
            }
            br.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public int getWhichPuppet() {
        return whichPuppet;
    }

    public void setWhichPuppet(int whichPuppet) {
        this.whichPuppet = whichPuppet;
    }

    public int getWhichPlayer() {
        return whichPlayer;
    }

    public void setWhichPlayer(int whichPlayer) {
        this.whichPlayer = whichPlayer;
    }

    public int getRound() {
        return round;
    }

    public void setRound(int round) {
        this.round = round;
    }

    public void nextPuppet() {
        whichPuppet++;
    }

    @Override
    public void onCreationTekton(Tekton t) {

    }

    @Override
    public void onCreationMushroomBody(MushroomBody mb) {

    }

    @Override
    public void onCreationSpore(Spore sp) {

    }

    @Override
    public void onCreationInsect(Insect ins) {

    }

    @Override
    public void onCreationMushroomThread(MushroomThread mt) {

    }
}

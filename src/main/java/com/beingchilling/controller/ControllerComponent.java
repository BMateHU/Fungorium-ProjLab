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
import java.util.Random;
import java.util.Scanner;

import static com.beingchilling.game.GameModel.gombasz;
import static com.beingchilling.game.GameModel.map;
import java.util.Objects;

/// Kontroller részét valósítja meg a modellnek
public class ControllerComponent {

    /// A kontroller hozzáfér a viewhoz
    private final ViewComponent viewComponent;

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
                    growThread((MushroomThread) GameModel.gameObjects.getV(words[1]), GameModel.map.tektonList.get(words[3]), words[2]);
                    break;
                case "/growmush":
                    Tekton target = GameModel.map.tektonList.get(words[3]);
                    MushroomSpecies ms = (MushroomSpecies)GameModel.gameObjects.getV(words[1]);
                    growMushroom(ms, target, words[2]);
                    break;
                case "/spreadspore":
                    spreadSpore((MushroomBody)GameModel.gameObjects.getV(words[1]), (Tekton)GameModel.gameObjects.getV(words[2]), words[3]);
                    break;
                case "/absorbinsect":
                    absorbInsect((MushroomThread)GameModel.gameObjects.getV(words[1]), words[2]);
                    break;
                case "/cut":
                    //((Insect)GameModel.gameObjects.getV(words[1])).insectCut(((MushroomThread)GameModel.gameObjects.getV(words[2])));
                    cut((Insect)GameModel.gameObjects.getV(words[1]),(MushroomThread)GameModel.gameObjects.getV(words[2]));
                    break;
                case "/eat":
                    //((Insect)GameModel.gameObjects.getV(words[1])).insectEat();
                    eat((Insect)GameModel.gameObjects.getV(words[1]));
                    break;
                case "/move":
                    move((Insect)GameModel.gameObjects.getV(words[1]), GameModel.map.tektonList.get(words[2]));
                    break;
                case "/random":
                    GameModel.randomSwitch = !GameModel.randomSwitch;
                    System.out.println("Jelen esetben a random: " + GameModel.randomSwitch);
                    break;
                case "/load":
                    URL url = this.getClass().getResource("");
                    File file = new File(url.getPath());
                    load(file);
                    break;
        }
    }

    /**
     * A játék fő szállát valósítja meg. Körökre van osztva a játék, utána playerenként minden rovarral/gomával lép 1-et
     */
    public void gameLoop() {
        //játékos szám lekérdezés
        //rovarnal a rovarasze-e a rovar nincs ellenorizve, gombanal szinten
        int round = 0;
        //game round
        for(round = 0; round < 20 ; round++) {
            //gombasz turn

            for (MushroomSpecies species : GameModel.gombasz.values()) {

                for(MushroomBody mb: species.checkMushroomBody()){
                    boolean skipped = false;
                    boolean spreadedspore = false;
                    boolean growedthread = false;
                    boolean mushroomgrowed = false; //search for id es add hozza mushroomspecies-hez
                    boolean absorbinsect = false;
                    while(!skipped){
                        Scanner scanner = new Scanner(System.in);

                        System.out.print("Gomba"+ round + "köre : ");

                        String beolvasottSor = scanner.nextLine();

                        String[] words = beolvasottSor.strip().split(" ");
                        switch (words[0]) {
                            case "/absorbinsect":
                                if(!species.checkMushroomBody().contains(GameModel.gameObjects.getV(words[1]))) {
                                    System.out.println("Ez a gombat nem iranyithatod");
                                    break;
                                }
                                if (!absorbinsect) {
                                    ArgumentManagement(beolvasottSor);
                                    species.addMushroomBody((MushroomBody) GameModel.gameObjects.getV(words[2]));
                                } else {
                                    System.out.println("Ezt már csináltad");
                                }
                                absorbinsect = true;
                                break;
                            case "/growmush":
                                if(!species.checkMushroomBody().contains(GameModel.gameObjects.getV(words[1]))) {
                                    System.out.println("Ez a gombat nem iranyithatod");
                                    break;
                                }
                                if (!mushroomgrowed) {
                                    ArgumentManagement(beolvasottSor);
                                } else {
                                    System.out.println("Ezt már csináltad");
                                }
                                mushroomgrowed = true;
                                break;
                            case "/spreadspore":
                                if(!species.checkMushroomBody().contains(GameModel.gameObjects.getV(words[1]))) {
                                    System.out.println("Ez a gombat nem iranyithatod");
                                    break;
                                }
                                if (!spreadedspore) {
                                    ArgumentManagement(beolvasottSor);
                                } else {
                                    System.out.println("Ezt már csináltad");
                                }
                                spreadedspore = true;
                                break;
                            case "/growthread":
                                if(!species.checkMushroomBody().contains(GameModel.gameObjects.getV(words[1]))) {
                                    System.out.println("Ez a gombat nem iranyithatod");
                                    break;
                                }
                                if (!growedthread) {
                                    ArgumentManagement(beolvasottSor);
                                } else {
                                    System.out.println("Ezt már csináltad");
                                }
                                growedthread = true;
                                break;
                            case "/skip":
                                skipped = true;
                                break;
                            default:
                                System.out.println("Ezt nem csinálhatod!");
                        }
                    }

                }

            }
            for(InsectSpecies species: GameModel.rovarasz.values()){

                for(Insect ins: species.getInsects()){
                    boolean skipped = false;
                    boolean moved = false;
                    boolean ate = false;
                    boolean cut = false;
                    while(!skipped){

                        Scanner scanner = new Scanner(System.in);

                        System.out.print("Rovar" + round + "köre : ");

                        String beolvasottSor = scanner.nextLine();
                        viewComponent.validate(beolvasottSor);

                        String[] words = beolvasottSor.strip().split(" ");
                        switch (words[0]) {
                            case "/move":
                                if(!species.getInsects().contains(GameModel.gameObjects.getV(words[1]))) {
                                    System.out.println("Ez a rovart nem iranyithatod");
                                    break;
                                }
                                if (!moved) {
                                    ArgumentManagement(beolvasottSor);
                                } else {
                                    System.out.println("Ezt már csináltad");
                                }
                                moved = true;
                                break;
                            case "/eat":
                                if(!species.getInsects().contains(GameModel.gameObjects.getV(words[1]))) {
                                    System.out.println("Ez a rovart nem iranyithatod");
                                    break;
                                }
                                if (!ate) {
                                    ArgumentManagement(beolvasottSor);
                                } else {
                                    System.out.println("Ezt már csináltad");
                                }
                                ate = true;
                                break;
                            case "/cut":
                                if(!species.getInsects().contains(GameModel.gameObjects.getV(words[1]))) {
                                    System.out.println("Ez a rovart nem iranyithatod");
                                    break;
                                }
                                if (!cut) {
                                    ArgumentManagement(beolvasottSor);
                                } else {
                                    System.out.println("Ezt már csináltad");
                                }
                                cut = true;
                                break;
                            case "/skip":
                                skipped = true;
                                break;
                            default:
                                System.out.println("Ezt nem csinálhatod!");
                        }

                        ArgumentManagement(beolvasottSor);
                    }
                }
            }
            if(round % 5 == 0){
                for(Tekton t :GameModel.map.tektonList.values()){
                    t.tektonBreak();
                }
            }
        }
    }

    /**
     * Ez egy függvény amely továbbítja a modellbeli részhez.
     * @param mtC MushroomThread amelyet továbbnövesztünk
     * @param target Tekton melyre továbbnő a thread
     * @param newThread Az új thread ID-ja
     */
    public void growThread(MushroomThreadController mtC, TektonController target, String newThread) {
        mtC.toView().checkOwner().growThread((MushroomThread)mtC, (Tekton)target);
        GameModel.gameObjects.put(newThread, target.toView().getThreads().getLast());
    }

    /**
     * Ez egy függvény amely továbbítja a modellbeli részhez.
     * @param ms A gombának a faja.
     * @param target Tekton amelyen növeszteni szeretné a játékos
     * @param id Az új gomba ID-ja
     */
    public void growMushroom(MushroomSpecies ms, TektonController target, String id) {
        target.growMushroomBody(ms);
        GameModel.gameObjects.put(id, ms.checkMushroomBody().getLast());
    }

    /**
     * Ez egy függvény amely továbbítja a modellbeli részhez.
     * @param source MushroomBody amelyből lőjjük a spórát
     * @param target Tekton amelyre lőjjük a spórát
     * @param type Spóra típusa
     */
    public void spreadSpore(MushroomBodyController source, TektonController target, String type) {
        Random rnd = new Random();
        Spore sp = switch (type) {
            case "S" -> new SlowSpore(rnd.nextInt(10));
            case "H" -> new HasteSpore(rnd.nextInt(10));
            case "M" -> new MuteSpore(rnd.nextInt(10));
            case "P" -> new ParaSpore(rnd.nextInt(10));
            case "C" -> new CloneSpore(rnd.nextInt(10));
            default -> new Spore(rnd.nextInt(10));
        };
        int i = 0;
        while(true) {
            Object o = GameModel.gameObjects.getV(("s" + i++));
            if(o == null)
                break;
        }
        GameModel.gameObjects.put(("s" + i), sp);
        source.spreadSpore((Tekton)target, sp);
    }

    /**
     * Ez egy függvény amely továbbítja a modellbeli részhez.
     * @param source A gombafonal amelynél szívja fel a player a rovart.
     * @param newMushroom Az új gomba ID-je
     */
    public void absorbInsect(MushroomThreadController source, String newMushroom) {
        MushroomBody mb = source.absorbInsect();
        GameModel.gameObjects.put(newMushroom, mb);
    }

    /**
     * Ez egy függvény amely továbbítja a modellbeli részhez.
     * @param source A tekton amelyet eltörünk
     * @param tektonID Az új tekton id-ja
     */
    private void breakTekton(TektonController source, String tektonID) {
        for(Spore spore : source.toView().getSpores())
            GameModel.gameObjects.removeByV(spore);
        for(MushroomThread mt : source.toView().getThreads())
            GameModel.gameObjects.removeByV(mt);
        Tekton newTekton = source.tektonBreak();
        GameModel.map.tektonList.put(tektonID, newTekton);
    }

    /**
     * Ez egy függvény amely továbbítja a modellbeli részhez.
     * @param insect A rovar amellyel vágjuk a fonalat
     * @param target A fonal amelyet elvágunk
     */
    public void cut(InsectController insect, MushroomThreadController target) {
        insect.insectCut((MushroomThread) target);
    }

    /**
     * Ez egy függvény amely továbbítja a modellbeli részhez.
     * @param insect A rovar amely enni fog
     */
    public void eat(InsectController insect) {
        GameModel.gameObjects.removeByV(insect.toView().getLocation().getSpores().getFirst());
        insect.insectEat();
    }

    /**
     * Ez egy függvény amely továbbítja a modellbeli részhez.
     * @param insect A rovar amely mozogni fog
     * @param target A tekton, amelyre mozogni fog
     */
    public void move(InsectController insect, TektonController target) {
        insect.insectMove((Tekton) target);
    }

    /**
     * Betölt egy fájlt a GameModelbe
     * @param file A fájl amit betöltnük
     */
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

package com.beingchilling.controller;

import com.beingchilling.game.GameModel;
import com.beingchilling.model.*;
import com.beingchilling.view.ViewComponent;

import java.io.File;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

import static com.beingchilling.game.GameModel.gombasz;
import static com.beingchilling.game.GameModel.map;

//otherwise this is just command calls, easy to implement
public class ControllerComponent {

    private final ViewComponent viewComponent;

    public ControllerComponent(ViewComponent viewComponent) {
        this.viewComponent = viewComponent;
    }

    public void ArgumentManagement(String command) {}

    public void gameLoop() {
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
                    boolean mushroomgrowed = false;
                    while(!skipped){
                        Scanner scanner = new Scanner(System.in);

                        System.out.print("Gomba"+ round + "köre : ");

                        String beolvasottSor = scanner.nextLine();

                        String[] words = beolvasottSor.strip().split(" ");
                        switch (words[0]) {
                            case "growmush":
                                if (mushroomgrowed == false) {
                                    ArgumentManagement(beolvasottSor);
                                } else {
                                    System.out.println("Ezt már csináltad");
                                }
                                mushroomgrowed = true;
                                break;
                            case "/spreadspore":
                                if (spreadedspore == false) {
                                    ArgumentManagement(beolvasottSor);
                                } else {
                                    System.out.println("Ezt már csináltad");
                                }
                                spreadedspore = true;
                                break;
                            case "/growthread":
                                if (growedthread == false) {
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
                    while(skipped){

                        Scanner scanner = new Scanner(System.in);

                        System.out.print("Rovar" + round + "köre : ");

                        String beolvasottSor = scanner.nextLine();

                        String[] words = beolvasottSor.strip().split(" ");
                        switch (words[0]) {
                            case "/move":
                                if (moved == false) {
                                    ArgumentManagement(beolvasottSor);
                                } else {
                                    System.out.println("Ezt már csináltad");
                                }
                                moved = true;
                                break;
                            case "/eat":
                                if (ate == false) {
                                    ArgumentManagement(beolvasottSor);
                                } else {
                                    System.out.println("Ezt már csináltad");
                                }
                                ate = true;
                                break;
                            case "/cut":
                                if (cut == false) {
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

    public void growThread(TektonController source, MushroomThreadController newThread, TektonController target) {
        //TO BE IMPLEMENTED
        newThread.growThread( (Tekton)target);
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

    private void breakTekton(TektonController source, TektonController newTekton) {
        source.tektonBreak();
        //Same shit cause of ID-s
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

    }
}

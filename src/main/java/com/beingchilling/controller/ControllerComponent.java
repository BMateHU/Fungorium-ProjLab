package com.beingchilling.controller;

import com.beingchilling.model.*;
import com.beingchilling.view.ViewComponent;

import java.io.File;
import java.util.List;

public class ControllerComponent {

    public ViewComponent viewComponent;

    public ControllerComponent(ViewComponent viewComponent) {
        this.viewComponent = viewComponent;
    }

    public void gameLoop() {

    }

    public void growThread(TektonController source, MushroomThreadController newThread, TektonController target) {
        //TO BE IMPLEMENTED
        newThread.growThread((Tekton)source, (Tekton)target);
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

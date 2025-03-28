package com.beingchilling;

import java.util.ArrayList;
import java.util.List;

/**
 * Az osztály a gombatestek által létrehozott gombafonalak funkciójait valósítja meg.
 */
public class MushroomThread {
    /**
     * Eltárolja az előtte lévő gombafonalat.
     */
    private MushroomThread preGrowed;
    /**
     *  Eltárolja az utána lévő gombafonalakat.
     */
    private List<MushroomThread> nextGrowed;
    /**
     * Eltárolja melyik tektonon van a gombafonal.
     */
    private Tekton location;

    public MushroomThread() {
        preGrowed = null;
        nextGrowed = new ArrayList<MushroomThread>();
    }

    /**
     * Ha fonal tektonjára spóra kerül, akkor egy ideig magától hosszabulni fog a fonal.
     */
    public void speedUpGrowing(){
        Szkeleton.indentation++;
        Szkeleton.printIndentation();
        System.out.println(">MushroomThread.speedUpGrowing():void");
        Szkeleton.printIndentation();

        System.out.println("<");
        Szkeleton.indentation--;

    }

    /**
     * A listához hozzáadja az adott fonalat.
     * @param thread adott fonal
     */
    public void addThread(MushroomThread thread){
        Szkeleton.indentation++;
        Szkeleton.printIndentation();
        System.out.println(">MushroomThread.addThread(MushroomThread thread):void");
        Szkeleton.printIndentation();
        System.out.println("<");
        Szkeleton.indentation--;
        nextGrowed.add(thread);

    }

    /**
     * A fonal elvágása után, törli az utána lévő fonalakat.
     */
    public void removeThreadAfter(){
        Szkeleton.indentation++;
        Szkeleton.printIndentation();
        System.out.println(">MushroomThread.removeThreadAfter():void");
        Szkeleton.printIndentation();
        System.out.println("<");
        Szkeleton.indentation--;

    }

    /**
     * Megnézi, hogy melyik gombatesthez tartozik a jelen lévő gombafonal.
     * @return A gombatest amihez a fonal tartozik
     */
    public MushroomBody checkOwner(){
        Szkeleton.indentation++;
        Szkeleton.printIndentation();
        System.out.println(">MushroomThread.checkOwner():MushroomBody");
        Szkeleton.printIndentation();

        System.out.println("<mushroom: MushroomBody");
        Szkeleton.indentation--;

        return new MushroomBody();
    }

    public MushroomThread getPreGrowed() {
        return preGrowed;
    }

    public void setPreGrowed(MushroomThread preGrowed) {
        this.preGrowed = preGrowed;
    }

    public List<MushroomThread> getNextGrowed() {
        return nextGrowed;
    }

    public void setNextGrowed(List<MushroomThread> nextGrowed) {
        this.nextGrowed = nextGrowed;
    }

    public Tekton getLocation() {
        return location;
    }

    public void setLocation(Tekton location) {
        this.location = location;
    }
}

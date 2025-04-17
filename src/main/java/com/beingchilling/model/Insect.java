package com.beingchilling.model;

import com.beingchilling.controller.InsectController;
import com.beingchilling.view.InsectView;

import java.util.ArrayList;
import java.util.List;

/**
 * Ez egy Rovar osztaly, ami tárolja rovarnak a sebbességét, vágásabilitását, spóraevésnek a sikeressége,
 * és jelenlegi evett tápanyagnak a mennyisége, illetve még egy Tektont, ami rajta mozog.
 */
public class Insect implements InsectController, InsectView {
    /**
     * rovarnak a sebbessége
     */
    private int insectSpeed;
    /**
     * rovar képes-e elvágni a gombaFonalat
     */
    private boolean cutThread;
    /**
     * rovar képes-e megenni a spórát
     */
    private boolean eatSpore;
    /**
     * rovar megtáplákozott tápanyag mennyisége
     */
    private int currentNutrient;
    /**
     * Tekton, ahol a rovar rajta elhelyezkedett
     */
    private Tekton location;

    //Konstruktor
    public Insect() {
        insectSpeed = 2;
        cutThread = true;
        eatSpore = true;
        currentNutrient = 0;
    }
    public Insect(Insect i) {
        insectSpeed = i.insectSpeed;
        cutThread = i.cutThread;
        eatSpore = i.eatSpore;
        currentNutrient = 0;
    }

    /**
     * függvény megmondja, hogy a paraméterként kapott Tektonra lehet a rovar átmenni.
     * Vissza ad egy boolean értéket, True ad vissza, ha tekton távolsága a rovar sebbességével elérhető
     * akkor a paraméterként kapott tektonra helyezzük át a rovart,
     * és a jelenlegi tektonról pedig eltávolítjuk.
     * Egyébként pedig False értéket ad vissza.
     * @param tek, hogy erre a tektonra tud-e a rovar átmenni
     * @return true, ha áttud menni. False, nem tud átmenni
     */
    
    public boolean insectMove(Tekton tek){
        if(insectSpeed <= 0)
            return false;
        List<Tekton> LT = getReachableTekton(insectSpeed);
        if(LT.contains(tek)) {
            tek.addInsect(this);
            location.removeInsect();
            location = tek;
            return true;
        }
        else
            return false;
    }

    /**
     * A rovar megeszik a tektonon elhelyezett spórát.
     */
    public void insectEat() throws ArrayIndexOutOfBoundsException {
        if(eatSpore && !location.getSpores().isEmpty()) {
            Spore ss = location.popSpore();
            ss.sporeEffect(this);
        }
        else {
            throw new ArrayIndexOutOfBoundsException("Cant eat spore");
        }
    }

    /**
     * A függvény megmondja a rovar elvágja-e a gombaFonalat. 
     * @param mt, gombaFonal, amit a rovar elvágna.
     * @return Ha igen, akkor true értéket ad vissza, ha nem akkor pedig False-t.
     */
    
    public boolean insectCut(MushroomThread mt) {
        if(cutThread && mt.getLocation().equals(location) && mt.getPreGrowed() != null) {
            mt.disconnectThread();
            return true;
        }
        return false;
    }

    /**
     * A függvény visszadja az elérhető tektonokat, amiket a rovar elér a sebbességével
     * @param speed, a rovarnak a mozgási sebbessége.
     * @return List<Tekton> értéket ad vissza, ami a rovar sebbességgel elérhető.
     */
    public List<Tekton> getReachableTekton(int speed){
        if(speed == 1)
            return location.getNeighborWithThread();
        else if(speed == 2) {
            List<Tekton> a = new ArrayList<Tekton>();
            for(Tekton t : location.getNeighborWithThread()) {
                if(a.contains(t))
                    continue;
                a.add(t);
                for(Tekton t2 : t.getNeighborWithThread()) {
                    if(a.contains(t2))
                        continue;
                    a.add(t2);
                }
            }
        }
        return new ArrayList<Tekton>();
    }

    /**
     * A függvény beállítja a rovart gyorsított mode-ba.
     */
    public void hasteEffect(){
        insectSpeed = 3;
    }

     /**
     * A függvény beállítja a rovart lassított mode-ba.
     */
    public void slowEffect(){
        insectSpeed = 1;
    }

    /**
     * A függvény beállítja a rovart bénított mode-ba.
     */
    public void paraEffect(){
        insectSpeed = 0;
        eatSpore = false;
        cutThread = false;
    }

    /**
     * A függvény beállítja a rovart némított mode-ba.
     */
    public void muteEffect(){
        cutThread = false;
    }

    public void cloneEffect() {
        //only an insect, needs to be added to insect species
        for(Tekton t : location.getNeighbors())
        {
            if(t.getInsect() != null)
                continue;
            t.addInsect(new Insect(this));
        }
        throw new ArrayIndexOutOfBoundsException("no place to clone, clone failed");
    }

    /**
     * A függvény megszünteti a hatásokat ami a rovaron van.
     */
    public void endEffect(){
        cutThread = true;
        eatSpore = true;
        insectSpeed = 2;
    }

    public void destroy() {
        //need to delete it from insect species and when deleting dont forget to get the points out
        location.addInsect(null);
    }

    public int getInsectSpeed() {
        return insectSpeed;
    }

    public boolean canCutThread() {
        return cutThread;
    }

    public boolean canEatSpore() {
        return eatSpore;
    }

    public int getCurrentNutrient() {
        return currentNutrient;
    }

    public Tekton getLocation() {
        return location;
    }

    public void setLocation(Tekton location) {
        this.location = location;
    }

    public InsectView toView() {
        return this;
    }

    public InsectController toController() {
        return this;
    }

}

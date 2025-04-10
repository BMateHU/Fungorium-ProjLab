package com.beingchilling.model;

import com.beingchilling.controller.InsectController;
import com.beingchilling.view.InsectView;

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
        
        System.out.println(">Insect.insectMove(Tekton L): boolean");
        if (insectSpeed > 0){
            List<Tekton> LT = getReachableTekton(insectSpeed);
            if(LT.contains(tek)) {
                tek.addInsect(this);
                location.removeInsect();
                location = tek;
                
                System.out.println("<result: true");
                
                return true;
            }
            else {
                
                System.out.println("<result: false");
                
                return false;
            }
        }
        else {
            
            System.out.println("<result: false");

            
            return false;
        }
    }

    /**
     * A rovar megeszik a tektonon elhelyezett spórát.
     */
    public void insectEat() throws ArrayIndexOutOfBoundsException {
        
        System.out.println(">Insect.insectEat(): void");
        if(eatSpore) {
            Spore ss = location.popSpore();
            ss.sporeEffect(this);
        }
        else {
            throw new ArrayIndexOutOfBoundsException("Cant eat spore");
        }
        

        System.out.println("<");
        

    }

    /**
     * A függvény megmondja a rovar elvágja-e a gombaFonalat. 
     * @param mt, gombaFonal, amit a rovar elvágna.
     * @return Ha igen, akkor true értéket ad vissza, ha nem akkor pedig False-t.
     */
    
    public boolean insectCut(MushroomThread mt){
        
        System.out.println(">Insect.insectCut(): boolean");
        if(cutThread){
            

            System.out.println("<result: true");
            

            return true;
        }
        
        System.out.println("<result: false");

        return false;
    }

    /**
     * A függvény visszadja az elérhető tektonokat, amiket a rovar elér a sebbességével
     * @param speed, a rovarnak a mozgási sebbessége.
     * @return List<Tekton> értéket ad vissza, ami a rovar sebbességgel elérhető.
     */
    public List<Tekton> getReachableTekton(int speed){
        
        System.out.println(">Insect.getReachableTekton(): void");
        
        System.out.println("<");
        
        return location.getNeighborWithThread();
    }

    /**
     * A függvény beállítja a rovart gyorsított mode-ba.
     */
    public void hasteEffect(){
        
        System.out.println(">Insect.hasteEffect(): void");
        

        insectSpeed = 3;

        System.out.println("<");
        
    }

     /**
     * A függvény beállítja a rovart lassított mode-ba.
     */
    public void slowEffect(){
        
        System.out.println(">Insect.slowEffect(): void");
        

        insectSpeed = 1;

        System.out.println("<");
        
    }

    /**
     * A függvény beállítja a rovart bénított mode-ba.
     */
    public void paraEffect(){
        
        System.out.println(">Insect.paraEffect(): void");
        

        insectSpeed = 0;
        eatSpore = false;
        cutThread = false;

        System.out.println("<");
        
    }

    /**
     * A függvény beállítja a rovart némított mode-ba.
     */
    public void muteEffect(){
        
        System.out.println(">Insect.muteEffect(): void");
        

        cutThread = false;

        System.out.println("<");
        
    }

    public void cloneEffect() {
        location.getNeighbors().getFirst().addInsect(new Insect()); //only an insect, needs to be added to insect species
    }

    /**
     * A függvény megszünteti a hatásokat ami a rovaron van.
     */
    public void endEffect(){
        
        System.out.println(">Insect.endEffect(): void");
        

        cutThread = true;
        eatSpore = true;
        insectSpeed = 2;

        System.out.println("<");
        
    }

    public void destroy() {
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

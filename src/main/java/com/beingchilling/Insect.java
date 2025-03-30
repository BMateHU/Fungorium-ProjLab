package com.beingchilling;

import java.util.List;

/**
 * Ez egy Rovar osztaly, ami tárolja rovarnak a sebbességét, vágásabilitását, spóraevésnek a sikeressége,
 * és jelenlegi evett tápanyagnak a mennyisége, illetve még egy Tektont, ami rajta mozog.
 */
public class Insect {
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
        Szkeleton.indentation++;
        Szkeleton.printIndentation();
        System.out.println(">Insect.insectMove(Tekton L): boolean");
        if (insectSpeed > 0){
            List<Tekton> LT = getReachableTekton(insectSpeed);
            if(LT.contains(tek)) {
                tek.addInsect(this);
                location.removeInsect();
                location = tek;
                Szkeleton.printIndentation();
                System.out.println("<result: true");
                Szkeleton.indentation--;
                return true;
            }
            else {
                Szkeleton.printIndentation();
                System.out.println("<result: false");
                Szkeleton.indentation--;
                return false;
            }
        }
        else {
            Szkeleton.printIndentation();
            System.out.println("<result: false");

            Szkeleton.indentation--;
            return false;
        }
    }

    /**
     * A rovar megeszik a tektonon elhelyezett spórát.
     */
    public void insectEat() throws ArrayIndexOutOfBoundsException {
        Szkeleton.indentation++;
        Szkeleton.printIndentation();
        System.out.println(">Insect.insectEat(): void");
        if(eatSpore) {
            Spore ss = location.popSpore();
            ss.sporeEffect(this);
        }
        else {
            throw new ArrayIndexOutOfBoundsException("Cant eat spore");
        }
        Szkeleton.printIndentation();

        System.out.println("<");
        Szkeleton.indentation--;

    }

    /**
     * A függvény megmondja a rovar elvágja-e a gombaFonalat. 
     * @param mt, gombaFonal, amit a rovar elvágna.
     * @return Ha igen, akkor true értéket ad vissza, ha nem akkor pedig False-t.
     */
    
    public boolean insectCut(MushroomThread mt){
        Szkeleton.indentation++;
        Szkeleton.printIndentation();
        System.out.println(">Insect.insectCut(): boolean");
        if(cutThread){
            Szkeleton.printIndentation();

            System.out.println("<result: true");
            Szkeleton.indentation--;

            return true;
        }
        Szkeleton.indentation--;
        System.out.println("<result: false");

        return false;
    }

    /**
     * A függvény visszadja az elérhető tektonokat, amiket a rovar elér a sebbességével
     * @param speed, a rovarnak a mozgási sebbessége.
     * @return List<Tekton> értéket ad vissza, ami a rovar sebbességgel elérhető.
     */
    public List<Tekton> getReachableTekton(int speed){
        Szkeleton.indentation++;
        Szkeleton.printIndentation();
        System.out.println(">Insect.getReachableTekton(): void");
        Szkeleton.printIndentation();
        System.out.println("<");
        Szkeleton.indentation--;
        return location.getNeighborWithThread();
    }

    /**
     * A függvény beállítja a rovart gyorsított mode-ba.
     */
    public void hasteEffect(){
        Szkeleton.indentation++;
        Szkeleton.printIndentation();
        System.out.println(">Insect.hasteEffect(): void");
        Szkeleton.printIndentation();

        insectSpeed = 3;

        System.out.println("<");
        Szkeleton.indentation--;
    }

     /**
     * A függvény beállítja a rovart lassított mode-ba.
     */
    public void slowEffect(){
        Szkeleton.indentation++;
        Szkeleton.printIndentation();
        System.out.println(">Insect.slowEffect(): void");
        Szkeleton.printIndentation();

        insectSpeed = 1;

        System.out.println("<");
        Szkeleton.indentation--;
    }

    /**
     * A függvény beállítja a rovart bénított mode-ba.
     */
    public void paraEffect(){
        Szkeleton.indentation++;
        Szkeleton.printIndentation();
        System.out.println(">Insect.paraEffect(): void");
        Szkeleton.printIndentation();

        insectSpeed = 0;
        eatSpore = false;
        cutThread = false;

        System.out.println("<");
        Szkeleton.indentation--;
    }

    /**
     * A függvény beállítja a rovart némított mode-ba.
     */
    public void muteEffect(){
        Szkeleton.indentation++;
        Szkeleton.printIndentation();
        System.out.println(">Insect.muteEffect(): void");
        Szkeleton.printIndentation();

        cutThread = false;

        System.out.println("<");
        Szkeleton.indentation--;
    }

    public void cloneEffect() {
        location.getNeighbors().getFirst().addInsect(new Insect()); //only an insect, needs to be added to insectspecies
    }

    /**
     * A függvény megszünteti a hatásokat ami a rovaron van.
     */
    public void endEffect(){
        Szkeleton.indentation++;
        Szkeleton.printIndentation();
        System.out.println(">Insect.endEffect(): void");
        Szkeleton.printIndentation();

        cutThread = true;
        eatSpore = true;
        insectSpeed = 2;

        System.out.println("<");
        Szkeleton.indentation--;
    }

    public void destroy() {

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

    public void setCurrentNutrient(int currentNutrient) {
        this.currentNutrient = currentNutrient;
    }

    public void setEatSpore(boolean eatSpore) {
        this.eatSpore = eatSpore;
    }

    public void setCutThread(boolean cutThread) {
        this.cutThread = cutThread;
    }

    public void setInsectSpeed(int insectSpeed) {
        this.insectSpeed = insectSpeed;
    }
}

import java.util.List;

public class Insect {
    public int insectSpeed;
    public boolean cutThread;
    public boolean eatSpore;
    public int currentNutrient;
    public Tekton location;

    //Konstruktor
    public Insect() {
        insectSpeed = 2;
        cutThread = true;
        eatSpore = true;
        currentNutrient = 0;
    }

    //A függvény megmondja, hogy a paraméterként kapott Tektonra lehet a rovar átmenni.
    //Vissza ad egy boolean értéket, True ad vissza, ha tekton távolsága a rovar sebbességével elérhető
    //akkor a paraméterként kapott tektonra helyezzük át a rovart,
    // és a jelenlegi tektonról pedig eltávolítjuk.
    //Egyébként pedig False értéket ad vissza.
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

    //A rovar megeszik a tektonon elhelyezett spórát.
    public void insectEat(){
        Szkeleton.indentation++;
        Szkeleton.printIndentation();
        System.out.println(">Insect.insectEat(): void");
        Spore ss = location.popSpore();
        ss.sporeEffect(this);
        Szkeleton.printIndentation();

        System.out.println("<");
        Szkeleton.indentation--;

    }

    //A függvény megmondja a paraméterként kapott gombaFonalat elvágja-e vagy nem. Ha igen, akkor true
    //értéket ad vissza, ha nem akkor pedig False-t.
    public boolean insectCut(MushroomThread mt){
        Szkeleton.indentation++;
        Szkeleton.printIndentation();
        System.out.println(">Insect.insectCut(): boolean");
        if(cutThread){
            mt.removeThreadAfter();
            Szkeleton.printIndentation();

            System.out.println("<result: true");
            Szkeleton.indentation--;

            return true;
        }
        Szkeleton.indentation--;
        System.out.println("<result: false");

        return false;
    }

    //A függvény olyan távolsági List<Tekton> értéket ad vissza, ami a rovar sebbességgel elérhető.
    public List<Tekton> getReachableTekton(int speed){
        Szkeleton.indentation++;
        Szkeleton.printIndentation();
        System.out.println(">Insect.getReachableTekton(): void");
        Szkeleton.printIndentation();
        System.out.println("<");
        Szkeleton.indentation--;
        return location.getNeighborWithThread();
    }

    //A függvény beállítja a rovart gyorsított mode-ba.
    public void hasteEffect(){
        Szkeleton.indentation++;
        Szkeleton.printIndentation();
        System.out.println(">Insect.hasteEffect(): void");
        Szkeleton.printIndentation();

        System.out.println("<");
        Szkeleton.indentation--;
    }

     //A függvény beállítja a rovart lassított mode-ba.
    public void slowEffect(){
        Szkeleton.indentation++;

        Szkeleton.printIndentation();

        System.out.println(">Insect.slowEffect(): void");
        Szkeleton.printIndentation();
        System.out.println("<");
        Szkeleton.indentation--;
    }

     //A függvény beállítja a rovart bénított mode-ba.
    public void paraEffect(){
        Szkeleton.indentation++;
        Szkeleton.printIndentation();
        System.out.println(">Insect.paraEffect(): void");
        Szkeleton.printIndentation();
        System.out.println("<");
        Szkeleton.indentation--;
    }

     //A függvény beállítja a rovart némított mode-ba.
    public void muteEffect(){
        Szkeleton.indentation++;
        Szkeleton.printIndentation();
        System.out.println(">Insect.muteEffect(): void");
        Szkeleton.printIndentation();

        System.out.println("<");
        Szkeleton.indentation--;
    }

     //A függvény megszütetti a hatásokat ami a rovaron van.
    public void endEffect(){
        Szkeleton.indentation++;
        Szkeleton.printIndentation();
        System.out.println(">Insect.endEffect(): void");
        Szkeleton.printIndentation();
        System.out.println("<");
        Szkeleton.indentation--;
    }
}

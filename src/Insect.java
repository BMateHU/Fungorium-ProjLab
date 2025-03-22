import java.util.List;

public class Insect {
    public int insectSpeed;
    public boolean cutThread;
    public boolean eatSpore;
    public int currentNutrient;
    public Tekton location;

    public Insect() {
        insectSpeed = 2;
        cutThread = true;
        eatSpore = true;
        currentNutrient = 0;
    }
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
            Szkeleton.indentation--;
            return false;
        }
    }

    public void insectEat(){
        System.out.println(">Insect.insectEat(): void");
        Spore ss = location.popSpore();
        ss.sporeEffect(this);
        System.out.println("<");
    }

    public boolean insectCut(MushroomThread mt){
        System.out.println(">Insect.insectCut(): void");
        if(cutThread){
            mt.removeThreadAfter();
            System.out.println("<result: true");
            return true;
        }
        return false;
    }

    public List<Tekton> getReachableTekton(int speed){
        Szkeleton.indentation++;
        Szkeleton.printIndentation();
        System.out.println(">Insect.getReachableTekton(): void");
        Szkeleton.printIndentation();
        System.out.println("<");
        Szkeleton.indentation--;
        return location.getNeighborWithThread();
    }

    public void hasteEffect(){
        System.out.println(">Insect.hasteEffect(): void");
        System.out.println("<");
    }

    public void slowEffect(){
        System.out.println(">Insect.slowEffect(): void");
        System.out.println("<");
    }

    public void paraEffect(){
        System.out.println(">Insect.paraEffect(): void");
        System.out.println("<");
    }

    public void muteEffect(){
        System.out.println(">Insect.muteEffect(): void");
        System.out.println("<");
    }

    public void endEffect(){
        System.out.println(">Insect.endEffect(): void");
        System.out.println("<");
    }
}

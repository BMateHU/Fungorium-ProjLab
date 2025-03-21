import java.util.List;

public class Insect {
    private int insectSpeed;
    private Boolean cutThread;
    private Boolean eatSpore;
    private int currentNutrient;
    private Tekton tekton;

    public boolean insectMove(Tekton L){
        System.out.println("\t>Insect.insectMove(Tekton L): boolean");
        System.out.println("<result: boolean");
        return true;
    }

    public void insectEat(){
        System.out.println("\t>Insect.insectEat(): void");
        System.out.println("<");
    }

    public List<Tekton> getReachableTekton(){
        System.out.println("\t>Insect.getReachableTekton(): void");
        System.out.println("<");
        return tekton.getNeighborWithThread();
    }

    public void hasteEffect(){
        System.out.println("\t>Insect.hasteEffect(): void");
        System.out.println("<");
    }

    public void slowEffect(){
        System.out.println("\t>Insect.slowEffect(): void");
        System.out.println("<");
    }

    public void paraEffect(){
        System.out.println("\t>Insect.paraEffect(): void");
        System.out.println("<");
    }

    public void muteEffect(){
        System.out.println("\t>Insect.muteEffect(): void");
        System.out.println("<");
    }

    public void endEffect(){
        System.out.println("\t>Insect.endEffect(): void");
        System.out.println("<");
    }
}

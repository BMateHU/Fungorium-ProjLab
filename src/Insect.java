import java.util.List;

public class Insect {
    private int InsectSpeed;
    private Boolean cutThread;
    private Boolean eatSpore;
    private int currentNutrient;

    public boolean insectMove(Tekton L){
        System.out.println("\t>Insect.insectMove(): boolean");
        System.out.println("<");
        return true;
    }

    public void insectEat(){
        System.out.println("\t>Insect.insectEat(): void");
        System.out.println("<");
    }

    public List<Tekton> getReachableTekton(){
        System.out.println("\t>Insect.getReachableTekton(): void");
        System.out.println("<");
        return;
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

import java.util.List;

public class Insect {
    private int insectSpeed;
    private Boolean cutThread;
    private Boolean eatSpore;
    private int currentNutrient;
    private Tekton tekton;

    public boolean insectMove(Tekton tek){
        List<Tekton> fonolasTekton = tek.getNeighborWithThread();
        System.out.println(">Insect.insectMove(Tekton L): boolean");
        System.out.println("<result: boolean");
        for(Tekton t : fonolasTekton){
            if(tekton.checkNeighbor(t)){
                return true;
            }
        }
        return false;
    }

    public void insectEat(){
        System.out.println(">Insect.insectEat(): void");
        System.out.println("<");
    }

    public List<Tekton> getReachableTekton(){
        System.out.println(">Insect.getReachableTekton(): void");
        System.out.println("<");
        return tekton.getNeighborWithThread();
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

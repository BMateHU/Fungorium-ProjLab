import java.util.List;
public class Tekton {
    private List<Tekton> neighbors;
    private Spore spore;
    public boolean addThread(MushroomThread mt){
        System.out.println(">Tekton.addThread(): Boolean");
        System.out.println("<");
        return true;
    }

   public void tektonBreak(){
    System.out.println(">Tekton.tektonBreak(): void");
    System.out.println("<");
   }

    public void tektonEffect(){
        System.out.println(">Tekton.tektonEffect(): void");
        System.out.println("<");
    }

    public Spore PopSpore(){
        System.out.println(">Tekton.PopSpore(): Spore");
        System.out.println("<Spore:spore");
        return spore;
    }

    public void clearSpore(){
        System.out.println(">Tekton.clearSpore(): void");
        System.out.println("<");
    }

    public boolean growMushroomBody(MushroomSpecies ms){
        System.out.println(">Tekton.growMushroomBody(): boolean");
        System.out.println("<result:boolean");
        return true;
    }

    public List<Tekton> getNeighborWithThread(){
        System.out.println(">Tekton.getNeighbor(): List<Tekton>");
        System.out.println("<");
        return neighbors;
    }

    public boolean checkNeighbor(Tekton t){
        System.out.println(">Tekton.checkNeighbor(): boolean");
        System.out.println("<");
        return true;
    }

    public void addSpore(Spore s){
        System.out.println(">Tekton.addSpore(): void");
        System.out.println("<");
    }

    public void updateNeighbor(){
        System.out.println(">Tekton.updateNeighbor(): void");
        System.out.println("<");
    }
    
}

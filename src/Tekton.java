import java.util.LinkedList;
import java.util.List;
public class Tekton {
    public List<Tekton> neighbors;
    public List<Spore> spore;
    public MushroomThread mushroomThread;
    public Insect insect;

    public Tekton(){
        neighbors = new LinkedList<>();
        spore = new LinkedList<>();
        mushroomThread = null;
        insect = null;
    }
    public boolean addThread(MushroomThread mt){
        System.out.println(">Tekton.addThread(): Boolean");
        if(mushroomThread != null){
            System.out.println("<false");
            return false;
        }
        System.out.println("<true");
        return true;
    }

   public void tektonBreak(){
        System.out.println(">Tekton.tektonBreak(): void");
        if(insect != null){
            System.out.println("<");
            return;
        }

        mushroomThread.removeThreadAfter();
        clearSpore();
        Tekton T2 = new Tekton();
        T2.neighbors.add(this);
        for(Tekton t : neighbors){
            t.updateNeighbor(null,null);
        }
        List<Tekton> L = new LinkedList<Tekton>();
        L.add(T2);
        updateNeighbor(L,null);
        System.out.println("<");
   }

    public void tektonEffect(){
        System.out.println(">Tekton.tektonEffect(): void");
        System.out.println("<");
    }

    public Spore popSpore(){
        System.out.println(">Tekton.PopSpore(): Spore");
        System.out.println("<Spore:spore");
        return new Spore(1);
    }

    public void clearSpore(){
        System.out.println(">Tekton.clearSpore(): void");
        System.out.println("<");
    }

    public boolean growMushroomBody(MushroomSpecies ms){
        System.out.println(">Tekton.growMushroomBody(): boolean");
        if(spore.size()>=3 && mushroomThread!=null) {
            useSporeToGrow();
            MushroomBody M = new MushroomBody();
            M.location = this;
            ms.addMushroomBody(M);
            System.out.println("<result:true");
            return true;
        }
        System.out.println("<result:false");
        return true;

    }

    public List<Tekton> getNeighborWithThread(){
        Szkeleton.indentation++;
        Szkeleton.printIndentation();
        System.out.println(">Tekton.getNeighborWithThread(): List<Tekton>");
        Szkeleton.printIndentation();
        System.out.println("<");
        Szkeleton.indentation--;
        return neighbors;
    }

    public boolean checkNeighbor(Tekton t){
        Szkeleton.indentation++;
        Szkeleton.printIndentation();
        System.out.println(">Tekton.checkNeighbor(): boolean");
        if(neighbors.contains(t))
        {
            Szkeleton.printIndentation();
            System.out.println("<result:true");
            Szkeleton.indentation--;
            return true;
        }
        Szkeleton.printIndentation();
        System.out.println("<false");
        Szkeleton.indentation--;
        return false;
    }

    public void addSpore(Spore s){
        System.out.println(">Tekton.addSpore(): void");
        System.out.println("<");
    }

    public void updateNeighbor(List<Tekton> newAdd, List<Tekton> delete){
        System.out.println(">Tekton.updateNeighbor(): void");
        System.out.println("<");
    }

    public List<Tekton> getNeighbors() {
        return neighbors;
    }

    public void deleteNeighbor(Tekton t){
        System.out.println(">Tekton.deleteNeighbor(): void");
        System.out.println("<");
    }
    
    public void addNeighbor(Tekton t) {
        System.out.println(">Tekton.addNeighbor(): void");
        System.out.println("<");
    }
    public void addInsect(Insect i) {
        Szkeleton.indentation++;
        Szkeleton.printIndentation();
        System.out.println(">Tekton.addInsect(): void");
        Szkeleton.printIndentation();
        System.out.println("<");
        Szkeleton.indentation--;
    }
    public void removeInsect() {
        Szkeleton.indentation++;
        Szkeleton.printIndentation();
        System.out.println(">Tekton.removeInsect(): void");
        Szkeleton.printIndentation();
        System.out.println("<");
        Szkeleton.indentation--;
    }
    public void useSporeToGrow(){

        System.out.println(">Tekton.useSporeToGrow(): void");
        System.out.println("<");
    }

}

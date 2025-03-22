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
        Szkeleton.indentation++;
        Szkeleton.printIndentation();
        System.out.println(">Tekton.addThread(): Boolean");
        if(mushroomThread != null){
            Szkeleton.printIndentation();
            System.out.println("<false");
            Szkeleton.indentation--;

            return false;
        }
        Szkeleton.printIndentation();

        System.out.println("<true");
        Szkeleton.indentation--;

        return true;
    }

   public void tektonBreak(){
       Szkeleton.indentation++;
       Szkeleton.printIndentation();
        System.out.println(">Tekton.tektonBreak(): void");
        if(insect != null){
            Szkeleton.printIndentation();

            System.out.println("<");
            Szkeleton.indentation--;
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
       Szkeleton.printIndentation();

       System.out.println("<");
       Szkeleton.indentation--;

   }

    public void tektonEffect(){
        Szkeleton.indentation++;
        Szkeleton.printIndentation();
        System.out.println(">Tekton.tektonEffect(): void");
        Szkeleton.printIndentation();

        System.out.println("<");
        Szkeleton.indentation--;

    }

    public Spore popSpore(){
        Szkeleton.indentation++;
        Szkeleton.printIndentation();
        System.out.println(">Tekton.PopSpore(): Spore");
        Szkeleton.printIndentation();

        System.out.println("<Spore:spore");
        Szkeleton.indentation--;

        return spore.removeFirst();
    }

    public void clearSpore(){
        Szkeleton.indentation++;
        Szkeleton.printIndentation();
        System.out.println(">Tekton.clearSpore(): void");
        Szkeleton.printIndentation();

        System.out.println("<");
        Szkeleton.indentation--;

    }

    public boolean growMushroomBody(MushroomSpecies ms){
        Szkeleton.indentation++;
        Szkeleton.printIndentation();
        System.out.println(">Tekton.growMushroomBody(): boolean");
        if(spore.size()>=3 && mushroomThread!=null) {
            useSporeToGrow();
            MushroomBody M = new MushroomBody();
            M.location = this;
            ms.addMushroomBody(M);
            Szkeleton.printIndentation();

            System.out.println("<result:true");
            Szkeleton.indentation--;

            return true;
        }
        Szkeleton.printIndentation();

        System.out.println("<result:false");
        Szkeleton.indentation--;

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
        Szkeleton.indentation++;
        Szkeleton.printIndentation();
        System.out.println(">Tekton.addSpore(): void");
        Szkeleton.printIndentation();

        System.out.println("<");
        Szkeleton.indentation--;

    }

    public void updateNeighbor(List<Tekton> newAdd, List<Tekton> delete){
        Szkeleton.indentation++;
        Szkeleton.printIndentation();
        System.out.println(">Tekton.updateNeighbor(): void");
        Szkeleton.printIndentation();

        System.out.println("<");
        Szkeleton.indentation--;

    }

    public List<Tekton> getNeighbors() {
        return neighbors;
    }

    public void deleteNeighbor(Tekton t){
        Szkeleton.indentation++;
        Szkeleton.printIndentation();
        System.out.println(">Tekton.deleteNeighbor(): void");
        Szkeleton.printIndentation();
        System.out.println("<");
        Szkeleton.indentation--;
    }
    
    public void addNeighbor(Tekton t) {
        Szkeleton.indentation++;
        Szkeleton.printIndentation();

        System.out.println(">Tekton.addNeighbor(): void");
        Szkeleton.printIndentation();
        System.out.println("<");
        Szkeleton.indentation--;
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
        Szkeleton.indentation++;
        Szkeleton.printIndentation();
        System.out.println(">Tekton.useSporeToGrow(): void");
        Szkeleton.printIndentation();

        System.out.println("<");
        Szkeleton.indentation--;

    }

}

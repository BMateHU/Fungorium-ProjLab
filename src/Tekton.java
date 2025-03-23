import java.util.LinkedList;
import java.util.List;
public class Tekton {
    public List<Tekton> neighbors;
    public List<Spore> spore;
    public MushroomThread mushroomThread;
    public Insect insect;

    //Konstruktor
    public Tekton(){
        neighbors = new LinkedList<>();
        spore = new LinkedList<>();
        mushroomThread = null;
        insect = null;
    }

    //A GombaFonal növeszthető-e a Tektonon. Ha igen True értéket ad vissza, ha nem akkor pedig False.
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

    //Tekton kettétörese, ha a Tektonon van Gombafonal és Spóra akkor azokat eltávolítjuk a Tektonról
    //Létre hozzuk egy új Tektont és ezt az új létre hozott tekton hozzáadjuk a jelenlegi Tekton neighborhoz
    //És az új létre hozott Tektonnak is hozzáadjuk a jelenlegi Tektont mint neighborként.
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

   //Megmondja, hogy a jelenlegi Tekton milyen hatással rendelkezik. 
   //PL: Gombatest nélküli vagy Gombafonal nélküli
    public void tektonEffect(){
        Szkeleton.indentation++;
        Szkeleton.printIndentation();
        System.out.println(">Tekton.tektonEffect(): void");
        Szkeleton.printIndentation();

        System.out.println("<");
        Szkeleton.indentation--;

    }

    //A függvény megmondja a Tektonon lévő spóra 
    public Spore popSpore(){
        Szkeleton.indentation++;
        Szkeleton.printIndentation();
        System.out.println(">Tekton.PopSpore(): Spore");
        Szkeleton.printIndentation();

        System.out.println("<Spore:spore");
        Szkeleton.indentation--;

        return spore.removeFirst();
    }

    //A Tektonon lévő spórák eltávolítása illetve megsemmisitése 
    public void clearSpore(){
        Szkeleton.indentation++;
        Szkeleton.printIndentation();
        System.out.println(">Tekton.clearSpore(): void");
        Szkeleton.printIndentation();

        System.out.println("<");
        Szkeleton.indentation--;

    }

    //A GombaTest növeszthető-e a Tektonon. A GombaTestet növesztéshez,
    //Szükséges három darab spóra és rendelkezik gombaFonal a Tektonon.
    //Az új létre hozott gombaTest hozzáadjuk az (MushroomSpecies)ms-be, 
    //hogy megmondja ki az ownerja a gombaTestnek.
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

        return false;

    }

    //A függvény visszaadja egy List<Tekton> típusú értéket, ami megmondja, 
    //hogy milyen fonalasTektonnal van szomszédban.
    public List<Tekton> getNeighborWithThread(){
        Szkeleton.indentation++;
        Szkeleton.printIndentation();
        System.out.println(">Tekton.getNeighborWithThread(): List<Tekton>");
        Szkeleton.printIndentation();
        System.out.println("<");
        Szkeleton.indentation--;
        return neighbors;
    }

    //A függvény eldönti, hogy a kapott paraméterként Tekton szomszédba van-e a jelenlegi Tektonnal
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

    //Spóra, ami paraméterként kapott a függvényen, hozzáadása a Tektonra
    public void addSpore(Spore s){
        Szkeleton.indentation++;
        Szkeleton.printIndentation();
        System.out.println(">Tekton.addSpore(): void");
        Szkeleton.printIndentation();

        System.out.println("<");
        Szkeleton.indentation--;

    }

    //A Tektonnak a szomszéd tektonok frissítése, az első paraméterként kapott List<Tekton> tektonok
    //azokat hozzáadjuk a szomszéd listához, a második paraméterként kapottakat pedig kitöröljük
    public void updateNeighbor(List<Tekton> newAdd, List<Tekton> delete){
        Szkeleton.indentation++;
        Szkeleton.printIndentation();
        System.out.println(">Tekton.updateNeighbor(): void");
        Szkeleton.printIndentation();

        System.out.println("<");
        Szkeleton.indentation--;

    }

    //A tektonnak a szomszédos Tektonok lekérdezése. Ami vissza ad egy List<Tekton> értéket.
    public List<Tekton> getNeighbors() {
        Szkeleton.indentation++;
        Szkeleton.printIndentation();
        System.out.println(">Tekton.getNeighbors(): List<Tekton>");
        Szkeleton.printIndentation();
        System.out.println("<");
        Szkeleton.indentation--;
        return neighbors;
    }

    //A paraméterként kapott tekont kitörlése a szomszédos tektonok listából
    public void deleteNeighbor(Tekton t){
        Szkeleton.indentation++;
        Szkeleton.printIndentation();
        System.out.println(">Tekton.deleteNeighbor(): void");
        Szkeleton.printIndentation();
        System.out.println("<");
        Szkeleton.indentation--;
    }

     //A paraméterként kapott tekont hozzáadás a szomszéd tektonok listából
    public void addNeighbor(Tekton t) {
        Szkeleton.indentation++;
        Szkeleton.printIndentation();

        System.out.println(">Tekton.addNeighbor(): void");
        Szkeleton.printIndentation();
        System.out.println("<");
        Szkeleton.indentation--;
    }

     //A paraméterként kapott insect hozzáadás a tektonra, ami csak egy lehetséges
    public void addInsect(Insect i) {
        Szkeleton.indentation++;
        Szkeleton.printIndentation();
        System.out.println(">Tekton.addInsect(): void");
        Szkeleton.printIndentation();
        System.out.println("<");
        Szkeleton.indentation--;
    }

    //A paraméterként kapott insect eltávolítása a tektonról.
    public void removeInsect() {
        Szkeleton.indentation++;
        Szkeleton.printIndentation();
        System.out.println(">Tekton.removeInsect(): void");
        Szkeleton.printIndentation();
        System.out.println("<");
        Szkeleton.indentation--;
    }

    //A spórákat felhasználva, létre hozza a tekton egy gombaTest
    public void useSporeToGrow(){
        Szkeleton.indentation++;
        Szkeleton.printIndentation();
        System.out.println(">Tekton.useSporeToGrow(): void");
        Szkeleton.printIndentation();

        System.out.println("<");
        Szkeleton.indentation--;

    }

}

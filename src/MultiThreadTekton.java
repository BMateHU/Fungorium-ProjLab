public class MultiThreadTekton extends Tekton{

    //Felülírja a tekton addThread() metódusát, hogy a tektonon több gombaFonalat lehet növeszteni.
    @Override
    public boolean addThread(MushroomThread mt){
        Szkeleton.indentation++;
        Szkeleton.printIndentation();
        System.out.println(">Tekton.addThread(): Boolean");
        Szkeleton.printIndentation();

        System.out.println("<true");
        Szkeleton.indentation--;

        return true;
    }
}

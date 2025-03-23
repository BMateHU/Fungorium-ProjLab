public class ThreadAbsorbTekton extends Tekton{

    //Felülírja a tekton tektonEffect() metódusát, hogy a tektonon lévő gombaFonal egy idő után
    //felszívódik, azaz eltűnik tektonról.
    @Override
    public void tektonEffect(){
        Szkeleton.indentation++;
        Szkeleton.printIndentation();
        System.out.println(">ThreadAbsorbTekton.tektonEffect(): void");
        Szkeleton.printIndentation();

        System.out.println("<");
        Szkeleton.indentation--;

    }
}

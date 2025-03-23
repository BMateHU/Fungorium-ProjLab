public class MushroomlessTekton extends Tekton{
    
    //Felülírja a tekton growMushroomBody() metódusát, hogy a tektonon nem lehet gombaTestet növeszteni.
    @Override 
    public boolean growMushroomBody(MushroomSpecies ms){
        Szkeleton.indentation++;
        Szkeleton.printIndentation();
        System.out.println(">Tekton.growMushroomBody(): boolean");
        Szkeleton.printIndentation();

        System.out.println("<result:false");
        Szkeleton.indentation--;

        return false;
    }
}

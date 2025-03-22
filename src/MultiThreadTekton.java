public class MultiThreadTekton extends Tekton{
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

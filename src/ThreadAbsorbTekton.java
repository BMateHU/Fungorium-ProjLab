public class ThreadAbsorbTekton extends Tekton{
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

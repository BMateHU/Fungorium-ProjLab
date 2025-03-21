public class MultiThreadTekton extends Tekton{
    @Override
    public boolean addThread(MushroomThread mt){
        System.out.println(">MultiThreadTekton.tektonEffect(): void");
        System.out.println("<");
        return true;
    }
}

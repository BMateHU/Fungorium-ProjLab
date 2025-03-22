public class MultiThreadTekton extends Tekton{
    @Override
    public boolean addThread(MushroomThread mt){
        System.out.println(">Tekton.addThread(): Boolean");
        System.out.println("<true");
        return true;
    }
}

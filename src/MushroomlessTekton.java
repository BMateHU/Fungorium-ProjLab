public class MushroomlessTekton extends Tekton{
    @Override 
    public boolean growMushroomBody(MushroomSpecies ms){
        System.out.println(">Tekton.growMushroomBody(): boolean");
        System.out.println("<result:false");
        return false;
    }
}

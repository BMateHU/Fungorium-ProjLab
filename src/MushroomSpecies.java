import java.util.List;

public class MushroomSpecies {
    private List<MushroomBody> mushroomBodies;
    public List<MushroomBody> checkMushroomBody(){
        System.out.println("MushroomSpecies.checkMushroomBody():List<MushroomBody>");
        return mushroomBodies;
    }
    public void addMushroomBody(MushroomBody mushroomBody){
        System.out.println("MushroomSpecies.addMushroomBody(MushroomBody mushroomBody):void");
    }

}

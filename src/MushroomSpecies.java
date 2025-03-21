import java.util.List;

public class MushroomSpecies {
    private List<MushroomBody> mushroomBodies;
    public List<MushroomBody> checkMushroomBody(){
        System.out.println("checkMushroomBody():List<MushroomBody>");
        return mushroomBodies;
    }
    public void addMushroomBody(MushroomBody mushroomBody){
        System.out.println("addMushroomBody(MushroomBody mushroomBody):void");
    }

}

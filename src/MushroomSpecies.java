import java.util.List;

public class MushroomSpecies {
    public List<MushroomBody> mushroomBodies;

    public List<MushroomBody> checkMushroomBody(){
        Szkeleton.indentation++;
        Szkeleton.printIndentation();
        System.out.println(">MushroomSpecies.checkMushroomBody():List<MushroomBody>");
        Szkeleton.printIndentation();
        System.out.println("<mushruooms:List<MushroomBody>");
        Szkeleton.indentation--;

        return mushroomBodies;
    }
    public void addMushroomBody(MushroomBody mushroomBody){
        Szkeleton.indentation++;

        Szkeleton.printIndentation();

        System.out.println(">MushroomSpecies.addMushroomBody(MushroomBody mushroomBody):void");
        Szkeleton.printIndentation();

        System.out.println("<");
        Szkeleton.indentation--;

    }

}

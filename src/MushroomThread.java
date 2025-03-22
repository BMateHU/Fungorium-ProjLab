import java.util.ArrayList;
import java.util.List;

public class MushroomThread {
    public MushroomThread preGrowed;
    public List<MushroomThread> nextGrowed;
    public Tekton location;

    public MushroomThread() {
        preGrowed = null;
        nextGrowed = new ArrayList<MushroomThread>();
    }
    public void speedUpGrowing(){
        Szkeleton.indentation++;
        Szkeleton.printIndentation();
        System.out.println(">MushroomThread.speedUpGrowing():void");
        Szkeleton.printIndentation();

        System.out.println("<");
        Szkeleton.indentation--;

    }
    public void addThread(MushroomThread thread){
        Szkeleton.indentation++;
        Szkeleton.printIndentation();
        System.out.println(">MushroomThread.addThread(MushroomThread thread):void");
        Szkeleton.printIndentation();
        System.out.println("<");
        Szkeleton.indentation--;


    }
    public void removeThreadAfter(){
        Szkeleton.indentation++;
        Szkeleton.printIndentation();
        System.out.println(">MushroomThread.removeThreadAfter():void");
        Szkeleton.printIndentation();
        System.out.println("<");
        Szkeleton.indentation--;

    }
    public MushroomBody checkOwner(){
        Szkeleton.indentation++;
        Szkeleton.printIndentation();
        System.out.println(">MushroomThread.checkOwner():MushroomBody");
        Szkeleton.printIndentation();

        System.out.println("<mushroom: MushroomBody");
        Szkeleton.indentation--;

        return new MushroomBody();
    }

}

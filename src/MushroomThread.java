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
        System.out.println(">MushroomThread.speedUpGrowing():void");
        System.out.println("<");
    }
    public void addThread(MushroomThread thread){
        System.out.println(">MushroomThread.addThread(MushroomThread thread):void");
        System.out.println("<");


    }
    public void removeThreadAfter(){
        System.out.println(">MushroomThread.removeThreadAfter():void");
        System.out.println("<");

    }
    public MushroomBody checkOwner(){
        System.out.println(">MushroomThread.checkOwner():MushroomBody");
        System.out.println("<mushroom: MushroomBody");

        if(preGrowed == null){
            return null;
        }
        return preGrowed.checkOwner();
    }

}

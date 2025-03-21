public class MushroomThread {
    private MushroomThread preGrowed;
    private MushroomThread nexrtGrowed;

    public void speedUpGrowing(){
        System.out.println("speedUpGrowing():void");
    }
    public void addThread(MushroomThread thread){
        System.out.println("addThread(MushroomThread thread):void");
    }
    public void removeThreadAfter(){
        System.out.println("removeThreadAfter():void");
    }
    public MushroomBody checkOwner(){
        System.out.println("checkOwner():MushroomBody");
        return preGrowed.checkOwner();
    }

}

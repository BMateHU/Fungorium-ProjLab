public class MushroomThread {
    private MushroomThread preGrowed;
    private MushroomThread nexrtGrowed;

    public void speedUpGrowing(){
        System.out.println("MushroomThread.speedUpGrowing():void");
    }
    public void addThread(MushroomThread thread){
        System.out.println("MushroomThread.addThread(MushroomThread thread):void");
    }
    public void removeThreadAfter(){
        System.out.println("MushroomThread.removeThreadAfter():void");
    }
    public MushroomBody checkOwner(){
        System.out.println("MushroomThread.checkOwner():MushroomBody");
        if(preGrowed == null){
            return null;
        }
        return preGrowed.checkOwner();
    }

}

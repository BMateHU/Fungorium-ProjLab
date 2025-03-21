public class MushroomThread {
    private MushroomThread preGrowed;
    private MushroomThread nexrtGrowed;

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

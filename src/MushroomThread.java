public class MushroomThread {
    private MushroomThread preGrowed;
    private MushroomThread nexrtGrowed;

    public void speedUpGrowing(){
        System.out.println("\t>MushroomThread.speedUpGrowing():void");
        System.out.println("<");
    }
    public void addThread(MushroomThread thread){
        System.out.println("\t>MushroomThread.addThread(MushroomThread thread):void");
        System.out.println("<");

    }
    public void removeThreadAfter(){
        System.out.println("\t>MushroomThread.removeThreadAfter():void");
        System.out.println("<");

    }
    public MushroomBody checkOwner(){
        System.out.println("\t>MushroomThread.checkOwner():MushroomBody");
        System.out.println("<");

        if(preGrowed == null){
            return null;
        }
        return preGrowed.checkOwner();
    }

}

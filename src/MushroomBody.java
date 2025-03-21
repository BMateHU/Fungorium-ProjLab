public class MushroomBody {
    private int bodyAge;
    private int sporeNumber;

    public MushroomBody() {
        bodyAge = 0;
        sporeNumber = 5;
    }
    public boolean spreadSpore(Tekton tekton) {
        System.out.println("MushroomBody.spreadSpore(Tekton tekton):boolean)");
        if(sporeNumber > 0) {
            return  true;
        }else {
            return false;
        }
    }
    //?
    public boolean growThread(MushroomThread mushroomThread, Tekton tekton) {
        System.out.println("MushroomBody.growThread(MushroomThread mushroomThread, Tekton tekton):boolean");
        return false;
    }
    public void popSpore(){
        System.out.println("MushroomBody.popSpore():void");
    }

}

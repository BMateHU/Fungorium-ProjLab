public class MushroomBody {
    private int bodyAge;
    private int sporeNumber;

    public MushroomBody() {
        bodyAge = 0;
        sporeNumber = 5;
    }
    public boolean spreadSpore(Tekton tekton) {
        System.out.println("\t>MushroomBody.spreadSpore(Tekton tekton):boolean)");
        System.out.println("<");

        if(sporeNumber > 0) {
            return  true;
        }else {
            return false;
        }
    }
    //?
    public boolean growThread(MushroomThread mushroomThread, Tekton tekton) {
        System.out.println("\t>MushroomBody.growThread(MushroomThread mushroomThread, Tekton tekton):boolean");
        System.out.println("<");

        return false;
    }
    public void popSpore(){
        System.out.println("\t>MushroomBody.popSpore():void");        System.out.println("<");

    }

}

public class MushroomBody {
    public int bodyAge;
    public int sporeNumber;
    public Tekton location;
    public MushroomBody() {
        bodyAge = 0;
        sporeNumber = 5;
    }
    public boolean spreadSpore(Tekton tekton) {
        System.out.println(">MushroomBody.spreadSpore(Tekton tekton):boolean)");
        if(tekton.checkNeighbor(location) && bodyAge >= 2 && sporeNumber > 0) {
            popSpore();
            Spore S = new Spore(1);
            tekton.addSpore(S);
            System.out.println("<result:true");
            return true;
        }
        System.out.println("<result:false");
        return false;
    }

    public boolean growThread(MushroomThread mushroomThread, Tekton tekton) {
        System.out.println(">MushroomBody.growThread(MushroomThread mushroomThread, Tekton tekton):boolean");
        if(tekton.checkNeighbor(location) && mushroomThread == null) {
            MushroomThread MT2 = new MushroomThread();
            if (tekton.addThread(MT2)) {
                if(!tekton.spore.isEmpty()){
                    //nincs random meg
                    for(Tekton t : tekton.neighbors) {
                        if(t != location) {
                            MushroomThread MT3 = new MushroomThread();
                            t.addThread(MT3);
                        }
                    }
                }
                System.out.println("<result:true");
                return true;
            }
            else{
                System.out.println("<result:false");
                return false;
            }
        }
        System.out.println("<result:false");
        return false;
    }
    public void popSpore(){
        System.out.println(">MushroomBody.popSpore():void");
        System.out.println("<");

    }

}

public class MushroomBody {
    public int bodyAge;
    public int sporeNumber;
    public Tekton location;
    public MushroomBody() {
        bodyAge = 0;
        sporeNumber = 5;
    }
    public boolean spreadSpore(Tekton tekton) {
        Szkeleton.indentation++;
        Szkeleton.printIndentation();
        System.out.println(">MushroomBody.spreadSpore(Tekton tekton):boolean)");
        if(tekton.checkNeighbor(location) && bodyAge >= 2 && sporeNumber > 0) {
            popSpore();
            Spore S = new Spore(1);
            tekton.addSpore(S);
            Szkeleton.printIndentation();
            System.out.println("<result:true");
            Szkeleton.indentation--;
            return true;
        }
        Szkeleton.printIndentation();
        System.out.println("<result:false");
        Szkeleton.indentation--;
        return false;
    }

    public boolean growThread(MushroomThread mushroomThread, Tekton tekton) {
        Szkeleton.indentation++;
        Szkeleton.printIndentation();
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
                Szkeleton.printIndentation();

                System.out.println("<result:true");
                Szkeleton.indentation--;

                return true;
            }
            else{
                Szkeleton.printIndentation();

                System.out.println("<result:false");
                Szkeleton.indentation--;

                return false;
            }
        }
        Szkeleton.printIndentation();

        System.out.println("<result:false");
        Szkeleton.indentation--;

        return false;
    }
    public void popSpore(){
        Szkeleton.indentation++;
        Szkeleton.printIndentation();
        System.out.println(">MushroomBody.popSpore():void");
        Szkeleton.printIndentation();

        System.out.println("<");
        Szkeleton.indentation--;


    }

}

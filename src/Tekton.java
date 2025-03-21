public class Tekton {
    public boolean addThread(MushroomThread mt){
        System.out.println(">Tekton.addThread(): Boolean");
        System.out.println("<");
        return true;
    }

   public void tektonBreak(){
    System.out.println(">Tekton.tektonBreak(): void");
    System.out.println("<");
   }

    public void tektonEffect(){
        System.out.println(">Tekton.tektonEffect(): void");
        System.out.println("<");
    }

    public Spore PopSpore(){
        System.out.println(">Tekton.PopSpore(): Spore");
        System.out.println("<");
    }

    public void clearSpore(){
        System.out.println(">Tekton.clearSpore(): void");
        System.out.println("<");
    }

    public boolean growMushroomBody(MushroomSpecies ms){
        System.out.println(">Tekton.growMushroomBody(): boolean");
        System.out.println("<");
        return ms;
    }

    public List<Tekton> getNeighbor(){
        System.out.println(">Tekton.getNeighbor(): List<Tekton>");
        System.out.println("<");
    }

    public boolean checkNeighbor(Tekton t){
        System.out.println(">Tekton.checkNeighbor(): boolean");
        System.out.println("<");
        return true;
    }

    public void addSpore(Spore s){
        System.out.println(">Tekton.addSpore(): void");
        System.out.println("<");
    }

    public void updateNeighbor(){
        System.out.println(">Tekton.updateNeighbor(): void");
        System.out.println("<");
    }
    
}

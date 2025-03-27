package com.beingchilling;

public class Szkeleton {

    public static int indentation = 0;

    public static void printIndentation() {
        for(int i = 0; i < indentation-1; i++) {
            System.out.print("\t");
        }
    }

    //Rovar mozgás effektus nélkül
    public void useCase1(){
        Tekton T1 = new Tekton();
        Tekton T2 = new Tekton();

        T1.neighbors.add(T2);
        T2.neighbors.add(T1);

        Insect I = new Insect();
        I.location = T2;
        T2.insect = I;

        MushroomThread MT1 = new MushroomThread();
        MT1.location = T1;
        MushroomThread MT2 = new MushroomThread();
        MT2.location = T2;

        I.insectSpeed = 2;

        I.insectMove(T1);
        indentation = 0;
    }

    //Rovar mozgás lassító effektussal
    public void useCase2(){
        Tekton T1 = new Tekton();
        Tekton T2 = new Tekton();

        T1.neighbors.add(T2);
        T2.neighbors.add(T1);

        Insect I = new Insect();
        I.location = T2;
        T2.insect = I;

        MushroomThread MT1 = new MushroomThread();
        MT1.location = T1;
        MushroomThread MT2 = new MushroomThread();
        MT2.location = T2;

        I.insectSpeed = 1;

        I.insectMove(T1);
        indentation = 0;
    }

    //Rovar mozgás gyorsító effektussal
    public void useCase3(){
        Tekton T1 = new Tekton();
        Tekton T2 = new Tekton();

        T1.neighbors.add(T2);
        T2.neighbors.add(T1);

        Insect I = new Insect();
        I.location = T2;
        T2.insect = I;

        MushroomThread MT1 = new MushroomThread();
        MT1.location = T1;
        MushroomThread MT2 = new MushroomThread();
        MT2.location = T2;

        I.insectSpeed = 3;

        I.insectMove(T1);
        indentation = 0;
    }

    //Rovar mozgás bénító effektussal
    public void useCase4() {
        Tekton T1 = new Tekton();
        Tekton T2 = new Tekton();

        T1.neighbors.add(T2);
        T2.neighbors.add(T1);

        Insect I = new Insect();
        I.location = T2;
        T2.insect = I;

        MushroomThread MT1 = new MushroomThread();
        MT1.location = T1;
        MushroomThread MT2 = new MushroomThread();
        MT2.location = T2;

        I.insectSpeed = 0;

        I.insectMove(T1);
        indentation = 0;
    }

    //Rovar mozgás sikertelen
    public void useCase5(){
         
        Tekton T1 = new Tekton();
        Tekton T2 = new Tekton();

        T1.neighbors.add(T2);
        T2.neighbors.add(T1);

        Insect I = new Insect();
        I.location = T2;
        T2.insect = I;

        MushroomThread MT1 = new MushroomThread();
        MT1.location = T1;

        I.insectMove(T1);
        indentation = 0;
    }

    //Rovar vágás sikertelen
    public void useCase6(){
         
        Tekton T1 = new Tekton();
        MushroomThread MT = new MushroomThread();
        MT.location = T1;
        Insect I = new Insect();
        I.cutThread = false;

        I.insectCut(MT);
        indentation = 0;
    }

    //Rovar vágás
    public void useCase7(){
         
        Tekton T1 = new Tekton();
        MushroomThread MT = new MushroomThread();
        MT.location = T1;
        Insect I = new Insect();

        I.insectCut(MT);
        indentation = 0;
    }

    //Gombatest növesztés
    public void useCase8(){
         
        Tekton T1 = new Tekton();
        MushroomThread MT = new MushroomThread();
        MT.location = T1;
        T1.mushroomThread.add(MT);
        for(int i = 0; i<3; i++)
            T1.spore.add(new Spore(1));

        T1.growMushroomBody(new MushroomSpecies());
        indentation = 0;
    }
    //Gombatest növesztése sikertelen
    public void useCase9ab(){
        System.out.println("Gombatest növesztés sikertelen a)b)");
         
        Tekton T1 = new Tekton();
        T1.spore.add(new Spore(1));
        T1.growMushroomBody(new MushroomSpecies());
        indentation = 0;
    }

    //Gombatest növesztése sikertelen
    public void useCase9c(){
        System.out.println("Gombatest növesztés sikertelen c)");
         
        MushroomlessTekton T1 = new MushroomlessTekton();
        T1.growMushroomBody(new MushroomSpecies());
        indentation = 0;
    }

    //Spóraszórás
    public void useCase10(){
         
        Tekton T1 = new Tekton();
        Tekton T2 = new Tekton();
        T1.neighbors.add(T2);
        T2.neighbors.add(T1);

        MushroomBody MB = new MushroomBody();
        MB.location = T1;
        MB.sporeNumber = 1;
        MB.bodyAge = 2;

        MB.spreadSpore(T2);
        indentation = 0;
    }

    //Spóraszórás sikertelen
    public void useCase11(){
         
        Tekton T1 = new Tekton();
        Tekton T2 = new Tekton();
        T1.neighbors.add(T2);
        T2.neighbors.add(T1);

        MushroomBody MB = new MushroomBody();
        MB.location = T1;
        MB.sporeNumber = 0;
        MB.bodyAge = 0;

        MB.spreadSpore(T2);
        indentation = 0;
    }

    //Gombafonal növesztése
    public void useCase12(){
         
        Tekton T1 = new Tekton();
        Tekton T2 = new Tekton();
        T1.neighbors.add(T2);
        T2.neighbors.add(T1);

        MushroomBody MB = new MushroomBody();
        MB.location = T1;
        MushroomThread MT1 = new MushroomThread();
        MT1.location = T1;

        MB.growThread(MT1,T2);
        indentation = 0;
    }

    //Gombafonal növesztés sikertelen
    public void useCase13(){
         
        Tekton T1 = new Tekton();
        Tekton T2 = new Tekton();
        T1.neighbors.add(T2);
        T2.neighbors.add(T1);

        MushroomBody MB = new MushroomBody();
        MB.location = T1;
        MushroomThread MT1 = new MushroomThread();
        MT1.location = T1;
        MushroomThread MT2 = new MushroomThread();
        MT2.location = T2;

        MB.growThread(MT1,T2);
        indentation = 0;
    }

    //Gombafonal gyorsítás
    public void useCase14(){
         
        Tekton T1 = new Tekton();
        Tekton T2 = new Tekton();
        T1.neighbors.add(T2);
        T2.neighbors.add(T1);

        Tekton T3 = new Tekton();
        T3.neighbors.add(T2);
        T2.neighbors.add(T3);
        T2.spore.add(new Spore(1));

        MushroomBody MB = new MushroomBody();
        MB.location = T1;
        MushroomThread MT1 = new MushroomThread();
        MT1.location = T1;

        MB.growThread(MT1,T2);
        indentation = 0;
    }

    //Tekton kettétörése
    public void useCase15(){
         
        Tekton T1 = new Tekton();
        MushroomThread MT = new MushroomThread();
        MT.location = T1;
        T1.mushroomThread.add(MT);

        T1.tektonBreak();
        indentation = 0;
    }

    //Tekton kettétörése sikertelen
    public void useCase16(){
         
        Tekton T1 = new Tekton();
        Insect I = new Insect();
        I.location = T1;
        T1.insect = I;

        T1.tektonBreak();
        indentation = 0;
    }

    //Rovar Eszik (gyorsító Spóra)
    public void useCase17(){
         
        Tekton T1 = new Tekton();
        Insect I = new Insect();
        I.location = T1;
        HasteSpore S = new HasteSpore(1);
        T1.spore.add(S);

        I.insectEat();
        indentation = 0;
    }

    //Rovar Eszik(lassító Spóra)
    public void useCase18(){
         
        Tekton T1 = new Tekton();
        Insect I = new Insect();
        I.location = T1;
        SlowSpore S = new SlowSpore(1);
        T1.spore.add(S);

        I.insectEat();
        indentation = 0;
    }

    //Rovar Eszik(bénító Spóra)
    public void useCase19(){
         
        Tekton T1 = new Tekton();
        Insect I = new Insect();
        I.location = T1;
        ParaSpore S = new ParaSpore(1);
        T1.spore.add(S);

        I.insectEat();
        indentation = 0;
    }

    //Rovar Eszik(némító Spóra)
    public void useCase20(){
         
        Tekton T1 = new Tekton();
        Insect I = new Insect();
        I.location = T1;
        MuteSpore S = new MuteSpore(1);
        T1.spore.add(S);

        I.insectEat();
        indentation = 0;
    }

    //Rovar Eszik
    public void useCase21(){
         
        Tekton T1 = new Tekton();
        Insect I = new Insect();
        I.location = T1;
        Spore S = new Spore(1);
        T1.spore.add(S);

        I.insectEat();
        indentation = 0;
    }
}

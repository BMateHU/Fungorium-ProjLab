import com.beingchilling.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class SzkeletonTest {

    //Rovar mozgás effektus nélkül
    @Test
    public void useCase1(){
        Tekton T1 = new Tekton();
        Tekton T2 = new Tekton();

        T1.neighbors.add(T2);
        T2.neighbors.add(T1);

        Insect I = new Insect();
        I.setLocation(T1);
        T1.insect = I;

        MushroomThread MT1 = new MushroomThread();
        MT1.setLocation(T1);
        MushroomThread MT2 = new MushroomThread();
        MT2.setLocation(T2);

        T1.mushroomThread.add(MT1);
        T2.mushroomThread.add(MT2);

        MT1.addThread(MT2);

        I.setInsectSpeed(2);
        Assertions.assertTrue(I.insectMove(T2));
    }

    //Rovar mozgás lassító effektussal
    @Test
    public void useCase2(){
        Tekton T1 = new Tekton();
        Tekton T2 = new Tekton();

        T1.neighbors.add(T2);
        T2.neighbors.add(T1);

        Insect I = new Insect();
        I.setLocation(T1);
        T1.insect = I;

        MushroomThread MT1 = new MushroomThread();
        MT1.setLocation(T1);
        MushroomThread MT2 = new MushroomThread();
        MT2.setLocation(T2);

        T1.mushroomThread.add(MT1);
        T2.mushroomThread.add(MT2);

        MT1.addThread(MT2);

        I.setInsectSpeed(1);

        Assertions.assertTrue(I.insectMove(T2));
    }

    //Rovar mozgás gyorsító effektussal
    @Test
    public void useCase3(){
        Tekton T1 = new Tekton();
        Tekton T2 = new Tekton();

        T1.neighbors.add(T2);
        T2.neighbors.add(T1);

        Insect I = new Insect();
        I.setLocation(T1);
        T1.insect = I;

        MushroomThread MT1 = new MushroomThread();
        MT1.setLocation(T1);
        MushroomThread MT2 = new MushroomThread();
        MT2.setLocation(T2);

        T1.mushroomThread.add(MT1);
        T2.mushroomThread.add(MT2);

        MT1.addThread(MT2);

        I.setInsectSpeed(3);

        Assertions.assertTrue(I.insectMove(T2));
    }

    //Rovar mozgás bénító effektussal
    @Test
    public void useCase4() {
        Tekton T1 = new Tekton();
        Tekton T2 = new Tekton();

        T1.neighbors.add(T2);
        T2.neighbors.add(T1);

        Insect I = new Insect();
        I.setLocation(T2);
        T2.insect = I;

        MushroomThread MT1 = new MushroomThread();
        MT1.setLocation(T1);
        MushroomThread MT2 = new MushroomThread();
        MT2.setLocation(T2);

        I.setInsectSpeed(0);

        Assertions.assertFalse(I.insectMove(T1));
    }

    //Rovar mozgás sikertelen
    @Test
    public void useCase5a(){
        Tekton T1 = new Tekton();

        Insect I = new Insect();
        I.setLocation(T1);
        T1.insect = I;

        Assertions.assertFalse(I.insectMove(T1));
    }

    //Rovar mozgás sikertelen
    @Test
    public void useCase5b(){
        Tekton T1 = new Tekton();
        Tekton T2 = new Tekton();
        Tekton T3 = new Tekton();
        Tekton T4 = new Tekton();

        Insect I = new Insect();
        I.setLocation(T1);
        T1.insect = I;

        MushroomThread MT1 = new MushroomThread();
        MushroomThread MT2 = new MushroomThread();
        MushroomThread MT3 = new MushroomThread();
        MushroomThread MT4 = new MushroomThread();

        MT1.setLocation(T1);
        MT2.setLocation(T2);
        MT3.setLocation(T3);
        MT4.setLocation(T4);

        T1.mushroomThread.add(MT1);
        T2.mushroomThread.add(MT2);
        T3.mushroomThread.add(MT3);
        T4.mushroomThread.add(MT4);

        T1.neighbors.add(T2);
        T2.neighbors.add(T1);
        T2.neighbors.add(T3);
        T3.neighbors.add(T2);
        T3.neighbors.add(T4);
        T4.neighbors.add(T3);

        Assertions.assertFalse(I.insectMove(T1));
    }

    //Rovar vágás sikertelen
    @Test
    public void useCase6(){
        Tekton T1 = new Tekton();
        MushroomThread MT = new MushroomThread();
        MT.setLocation(T1);
        Insect I = new Insect();
        I.setCutThread(false);

        Assertions.assertFalse(I.insectCut(MT));
    }

    //Rovar vágás
    @Test
    public void useCase7(){
        Tekton T1 = new Tekton();
        MushroomThread MT = new MushroomThread();
        MT.setLocation(T1);
        Insect I = new Insect();

        Assertions.assertTrue(I.insectCut(MT));
    }

    //Gombatest növesztés
    @Test
    public void useCase8(){
        Tekton T1 = new Tekton();
        MushroomThread MT = new MushroomThread();
        MT.setLocation(T1);
        T1.mushroomThread.add(MT);
        for(int i = 0; i<3; i++)
            T1.spore.add(new Spore(1));

        Assertions.assertTrue(T1.growMushroomBody(new MushroomSpecies()));
    }

    //Gombatest növesztése sikertelen
    @Test
    public void useCase9ab(){
        System.out.println("Gombatest növesztés sikertelen a)b)");

        Tekton T1 = new Tekton();
        T1.spore.add(new Spore(1));
        Assertions.assertFalse(T1.growMushroomBody(new MushroomSpecies()));
    }

    //Gombatest növesztése sikertelen
    @Test
    public void useCase9c(){
        System.out.println("Gombatest növesztés sikertelen c)");

        MushroomlessTekton T1 = new MushroomlessTekton();
        Assertions.assertFalse(T1.growMushroomBody(new MushroomSpecies()));
    }

    //Spóraszórás
    @Test
    public void useCase10(){
        Tekton T1 = new Tekton();
        Tekton T2 = new Tekton();
        T1.neighbors.add(T2);
        T2.neighbors.add(T1);

        MushroomBody MB = new MushroomBody();
        MB.setLocation(T1);
        MB.setSporeNumber(3);
        MB.setBodyAge(2);

        Assertions.assertTrue(MB.spreadSpore(T2));
    }

    //Spóraszórás sikertelen
    @Test
    public void useCase11(){
        Tekton T1 = new Tekton();
        Tekton T2 = new Tekton();
        T1.neighbors.add(T2);
        T2.neighbors.add(T1);

        MushroomBody MB = new MushroomBody();
        MB.setLocation(T1);
        MB.setSporeNumber(0);
        MB.setBodyAge(0);

        Assertions.assertFalse(MB.spreadSpore(T2));
    }

    //Gombafonal növesztése
    @Test
    public void useCase12(){
        Tekton T1 = new Tekton();
        Tekton T2 = new Tekton();
        T1.neighbors.add(T2);
        T2.neighbors.add(T1);

        MushroomBody MB = new MushroomBody();
        MB.setLocation(T1);
        MushroomThread MT1 = new MushroomThread();
        MT1.setLocation(T1);
        T1.mushroomThread.add(MT1);

        Assertions.assertTrue(MB.growThread(MT1,T2));
    }

    //Gombafonal növesztés sikertelen
    @Test
    public void useCase13() {
        Tekton T1 = new Tekton();
        Tekton T2 = new Tekton();
        T1.neighbors.add(T2);
        T2.neighbors.add(T1);

        MushroomBody MB = new MushroomBody();
        MB.setLocation(T1);
        MushroomThread MT1 = new MushroomThread();
        MT1.setLocation(T1);
        T1.mushroomThread.add(MT1);
        MushroomThread MT2 = new MushroomThread();
        MT2.setLocation(T2);
        T2.mushroomThread.add(MT2);

        Assertions.assertFalse(MB.growThread(MT1,T2));
    }

    //Gombafonal gyorsítás
    @Test
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
        MB.setLocation(T1);
        MushroomThread MT1 = new MushroomThread();
        MT1.setLocation(T1);

        Assertions.assertTrue(MB.growThread(MT1,T2));
    }

    //Tekton kettétörése
    @Test
    public void useCase15(){
        Tekton T1 = new Tekton();
        MushroomThread MT = new MushroomThread();
        MT.setLocation(T1);
        T1.mushroomThread.add(MT);

        Assertions.assertTrue(T1.tektonBreak());
    }

    //Tekton kettétörése sikertelen
    @Test
    public void useCase16(){
        Tekton T1 = new Tekton();
        Insect I = new Insect();
        I.setLocation(T1);
        T1.insect = I;

        Assertions.assertFalse(T1.tektonBreak());
    }

    //Rovar Eszik (gyorsító Spóra)
    @Test
    public void useCase17(){
        Tekton T1 = new Tekton();
        Insect I = new Insect();
        I.setLocation(T1);
        HasteSpore S = new HasteSpore(1);
        T1.spore.add(S);

        I.insectEat();
        Assertions.assertTrue(T1.spore.isEmpty());
    }

    //Rovar Eszik(lassító Spóra)
    @Test
    public void useCase18(){
        Tekton T1 = new Tekton();
        Insect I = new Insect();
        I.setLocation(T1);
        SlowSpore S = new SlowSpore(1);
        T1.spore.add(S);

        I.insectEat();
        Assertions.assertTrue(T1.spore.isEmpty());
    }

    //Rovar Eszik(bénító Spóra)
    @Test
    public void useCase19(){
        Tekton T1 = new Tekton();
        Insect I = new Insect();
        I.setLocation(T1);
        ParaSpore S = new ParaSpore(1);
        T1.spore.add(S);

        I.insectEat();
        Assertions.assertTrue(T1.spore.isEmpty());
    }

    //Rovar Eszik(némító Spóra)
    @Test
    public void useCase20(){
        Tekton T1 = new Tekton();
        Insect I = new Insect();
        I.setLocation(T1);
        MuteSpore S = new MuteSpore(1);
        T1.spore.add(S);

        I.insectEat();
        Assertions.assertTrue(T1.spore.isEmpty());
    }

    //Rovar Eszik
    @Test
    public void useCase21(){
        Tekton T1 = new Tekton();
        Insect I = new Insect();
        I.setLocation(T1);
        Spore S = new Spore(1);
        T1.spore.add(S);

        I.insectEat();
        Assertions.assertTrue(T1.spore.isEmpty());
    }
}

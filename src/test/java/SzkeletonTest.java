import com.beingchilling.model.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class SzkeletonTest {

    //Rovar mozgás effektus nélkül
    @Test
    public void useCase1(){
        Tekton T1 = new Tekton();
        Tekton T2 = new Tekton();

        T1.addNeighbor(T2);
        T2.addNeighbor(T1);

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

        Assertions.assertTrue(I.insectMove(T2));
        Assertions.assertEquals(2, I.getInsectSpeed());
    }

    //Rovar mozgás lassító effektussal
    @Test
    public void useCase2(){
        Tekton T1 = new Tekton();
        Tekton T2 = new Tekton();

        T1.addNeighbor(T2);
        T2.addNeighbor(T1);

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

        I.slowEffect();

        Assertions.assertTrue(I.insectMove(T2));
        Assertions.assertEquals(1, I.getInsectSpeed());
    }

    //Rovar mozgás gyorsító effektussal
    @Test
    public void useCase3(){
        Tekton T1 = new Tekton();
        Tekton T2 = new Tekton();

        T1.addNeighbor(T2);
        T2.addNeighbor(T1);

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

        I.hasteEffect();

        Assertions.assertTrue(I.insectMove(T2));
        Assertions.assertEquals(3, I.getInsectSpeed());
    }

    //Rovar mozgás bénító effektussal
    @Test
    public void useCase4() {
        Tekton T1 = new Tekton();
        Tekton T2 = new Tekton();

        T1.addNeighbor(T2);
        T2.addNeighbor(T1);

        Insect I = new Insect();
        I.setLocation(T2);
        T2.addInsect(I);
        T2.addSpore(new ParaSpore(1));
        I.insectEat();

        MushroomThread MT1 = new MushroomThread();
        MT1.setLocation(T1);
        MushroomThread MT2 = new MushroomThread();
        MT2.setLocation(T2);

        Assertions.assertFalse(I.insectMove(T1));
        Assertions.assertEquals(0, I.getInsectSpeed());
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

        T1.addNeighbor(T2);
        T2.addNeighbor(T1);
        T2.addNeighbor(T3);
        T3.addNeighbor(T2);
        T3.addNeighbor(T4);
        T4.addNeighbor(T3);

        Assertions.assertFalse(I.insectMove(T1));
    }

    //Rovar vágás sikertelen
    @Test
    public void useCase6(){
        Tekton T1 = new Tekton();
        MushroomThread MT = new MushroomThread();
        MT.setLocation(T1);
        Insect I = new Insect();
        I.muteEffect();

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
        T1.addNeighbor(T2);
        T2.addNeighbor(T1);

        MushroomBody MB = new MushroomBody(T1);
        MB.setBodyAge(2);

        Assertions.assertTrue(MB.spreadSpore(T2));
    }

    //Spóraszórás sikertelen
    @Test
    public void useCase11(){
        Tekton T1 = new Tekton();
        Tekton T2 = new Tekton();
        T1.addNeighbor(T2);
        T2.addNeighbor(T1);

        MushroomBody MB = new MushroomBody(T1);
        MB.spreadSpore(T2);
        MB.spreadSpore(T2);
        MB.spreadSpore(T2);
        MB.spreadSpore(T2);
        MB.spreadSpore(T2);
        MB.setBodyAge(0);

        Assertions.assertFalse(MB.spreadSpore(T2));
    }

    //Gombafonal növesztése
    @Test
    public void useCase12(){
        Tekton T1 = new Tekton();
        Tekton T2 = new Tekton();
        T1.addNeighbor(T2);
        T2.addNeighbor(T1);

        MushroomBody MB = new MushroomBody(T1);
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
        T1.addNeighbor(T2);
        T2.addNeighbor(T1);

        MushroomBody MB = new MushroomBody(T1);
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
        T1.addNeighbor(T2);
        T2.addNeighbor(T1);

        Tekton T3 = new Tekton();
        T3.addNeighbor(T2);
        T2.addNeighbor(T3);
        T2.spore.add(new Spore(1));

        MushroomBody MB = new MushroomBody(T1);
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

    //Rovar Eszik sikertelen
    @Test
    public void useCase22(){
        Tekton T1 = new Tekton();
        Insect I = new Insect();
        I.setLocation(T1);
        Spore S = new Spore(1);
        T1.spore.add(S);
        I.paraEffect();

        Assertions.assertThrows(ArrayIndexOutOfBoundsException.class, I::insectEat);
        Assertions.assertFalse(T1.spore.isEmpty());
    }

    //Absorb siker
    @Test
    public void useCase23() {
        Tekton T1 = new Tekton();
        Assertions.assertTrue(T1.addThread(new MushroomThread()));
        for(int i = 0; i < 3; i++)
            T1.absorb();
        Assertions.assertTrue(T1.mushroomThread.isEmpty());
    }

    //Fonal hozzaad ellenorzes
    @Test
    public void useCase24() {
        Tekton T1 = new Tekton();
        T1.addThread(new MushroomThread());
        Assertions.assertFalse(T1.addThread(new MushroomThread()));
        Assertions.assertFalse(T1.addThread(new MushroomThread()));
    }

    //MultiThreadTekton success
    @Test
    public void useCase25() {
        Tekton T1 = new MultiThreadTekton();
        T1.addThread(new MushroomThread());
        Assertions.assertTrue(T1.addThread(new MushroomThread()));
        Assertions.assertTrue(T1.addThread(new MushroomThread()));
    }

    //MultiThreadTekton absorb
    @Test
    public void useCase26() {
        Tekton T1 = new MultiThreadTekton();
        T1.addThread(new MushroomThread());
        Assertions.assertTrue(T1.addThread(new MushroomThread()));
        Assertions.assertTrue(T1.addThread(new MushroomThread()));
        for(int i = 0; i < 3; i++)
            T1.absorb();
        Assertions.assertTrue(T1.mushroomThread.isEmpty());
    }

    //MultiThreadTekton absorb, LifeThread and normal thread mix
    @Test
    public void useCase27() {
        Tekton T1 = new MultiThreadTekton();
        T1.addThread(new MushroomThread());
        Assertions.assertTrue(T1.addThread(new MushroomThread()));
        Assertions.assertTrue(T1.addThread(new MushroomThread()));
        for(int i = 0; i < 1; i++)
            T1.absorb();
        Assertions.assertEquals(2, T1.mushroomThread.size());
        for(int i = 0; i < 2; i++)
            T1.absorb();
        Assertions.assertTrue(T1.mushroomThread.isEmpty());
    }

    //LifeSupportTekton test
    @Test
    public void useCase28() {
        Tekton T1 = new LifeSupportTekton();
        T1.addThread(new MushroomThread());
        Assertions.assertFalse(T1.addThread(new MushroomThread()));
        for(int i = 0; i < 100; i++)
            T1.absorb();
        Assertions.assertFalse(T1.mushroomThread.isEmpty());
    }

    //Check lifeSupport attr
    @Test
    public void useCase29() {
        Tekton T1 = new Tekton();
        T1.addThread(new MushroomThread());
        T1.mushroomThread.getFirst().setLifeSupport(true);
        for(int i = 0; i < 100; i++)
            T1.absorb();
        Assertions.assertFalse(T1.mushroomThread.isEmpty());
    }

    //Check absorb is --
    @Test
    public void useCase30() {
        Tekton T1 = new Tekton();
        T1.addThread(new MushroomThread());
        T1.absorb();
        Assertions.assertEquals(2, T1.mushroomThread.getFirst().getLife());
    }

    //Fonal absorb insect
    @Test
    public void useCase31() {
        Tekton T1 = new Tekton();
        T1.addThread(new MushroomThread());
        T1.mushroomThread.getFirst().setLocation(T1);
        T1.addInsect(new Insect());
        T1.mushroomThread.getFirst().absorbInsect();
        Assertions.assertNotNull(T1.mushroomBody);
    }

    //Clone effect test
    @Test
    public void useCase32() {
        Tekton T1 = new Tekton();
        Tekton T2 = new Tekton();

        T1.addNeighbor(T2);
        T2.addNeighbor(T1);

        T1.addInsect(new Insect());
        T1.addSpore(new CloneSpore(1));

        T1.insect.setLocation(T1);
        T1.insect.insectEat();

        Assertions.assertNotNull(T1.insect);
        Assertions.assertNotNull(T2.insect);
        Assertions.assertTrue(T1.spore.isEmpty());
    }

    //Delete neighbor test
    @Test
    public void useCase33() {
        Tekton T1 = new Tekton();
        Tekton T2 = new Tekton();

        T1.addNeighbor(T2);
        T2.addNeighbor(T1);

        Assertions.assertEquals(T2, T1.neighbors.getFirst());
        Assertions.assertEquals(T1, T2.neighbors.getFirst());

        T1.deleteNeighbor(T2);
        T2.deleteNeighbor(T1);

        Assertions.assertTrue(T1.neighbors.isEmpty());
        Assertions.assertTrue(T2.neighbors.isEmpty());
    }

    //Test mushroomthread disconnect
    @Test
    public void useCase34() {
        MushroomThread mt = new MushroomThread();
        MushroomThread mt2 = new MushroomThread();
        MushroomThread mt3 = new MushroomThread();
        MushroomThread mt4 = new MushroomThread();

        mt.addThread(mt2);
        mt2.addThread(mt3);
        mt3.addThread(mt4);

        Assertions.assertNotNull(mt.getNextGrowed().getFirst());
        Assertions.assertNotNull(mt2.getPreGrowed());
        Assertions.assertNotNull(mt2.getNextGrowed().getFirst());
        Assertions.assertNotNull(mt3.getPreGrowed());
        Assertions.assertNotNull(mt3.getNextGrowed().getFirst());
        Assertions.assertNotNull(mt4.getPreGrowed());

        mt3.disconnectThread();

        Assertions.assertTrue(mt2.getNextGrowed().isEmpty());
        Assertions.assertNull(mt3.getPreGrowed());
    }

    //Test destroy() NOT FINISHED ONLY REMOVES FROM TEKTON
    @Test
    public void useCase35() {
        Tekton T1 = new Tekton();
        MushroomThread mt = new MushroomThread();
        T1.addThread(mt);
        mt.setLocation(T1);

        mt.destroy();

        Assertions.assertTrue(T1.mushroomThread.isEmpty());
        Assertions.assertNull(mt.getLocation());
    }

    //Insect eat para spore -> restore (end effect)
    @Test
    public void useCase36() {
        Tekton T1 = new Tekton();
        Tekton T2 = new Tekton();

        T1.addNeighbor(T2);
        T2.addNeighbor(T1);

        Insect I = new Insect();
        I.setLocation(T2);
        T2.addInsect(I);
        T2.addSpore(new ParaSpore(1));
        I.insectEat();

        Assertions.assertEquals(0, I.getInsectSpeed());
        Assertions.assertFalse(I.canEatSpore());
        Assertions.assertFalse(I.canCutThread());

        I.endEffect();

        Assertions.assertEquals(2, I.getInsectSpeed());
        Assertions.assertTrue(I.canEatSpore());
        Assertions.assertTrue(I.canCutThread());
    }
}

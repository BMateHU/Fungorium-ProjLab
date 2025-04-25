import com.beingchilling.controller.ControllerComponent;
import com.beingchilling.game.BiMap;
import com.beingchilling.game.GameModel;
import com.beingchilling.model.Tekton;
import com.beingchilling.view.ViewComponent;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.net.URL;
import java.util.*;
import java.util.logging.Logger;
import java.util.regex.Pattern;

public class TesztMain {

    private final String commandFile = "input.txt";
    private final String expectedFile = "output.txt";
    private static final ViewComponent vc = new ViewComponent();
    private static final ControllerComponent cc = new ControllerComponent(vc);

    public static Logger log = Logger.getLogger(TesztMain.class.getName());

    //bemeneti
    private void interpretCommands(String fileName) {
        File file = new File(fileName);
        try {
            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);
            if(!br.ready())
                Assertions.fail();
            while(br.ready()) {
                String command = br.readLine();
                if(vc.validate(command)) {
                        cc.ArgumentManagement(command);
                }
                else {
                    log.info("Invalid command: " + command);
                    Assertions.fail();
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    //kimeneti
    private boolean translateExpectedTo(String fileName) {
        File file = new File(fileName);
        boolean result = true;
        boolean whatToExpect = true;
        try {
            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);
            if(!br.ready())
                Assertions.fail();
            while(br.ready()) {
                String expected = br.readLine();
                String[] eTrimmed = expected.trim().split("[()]");
                if(eTrimmed.length == 1) {
                    String[] temp = expected.split(" ");
                    if(temp[temp.length-1].equals("failed")) {
                        whatToExpect = false;
                        continue;
                    }
                }
                try {
                    if (eTrimmed[1].equals(GameModel.gameObjects.getV(eTrimmed[0]).toString()))
                        ;
                    else
                        result = false;
                } catch (Exception ignored) {}
            }
        }
        catch (IOException e)
        {
            throw new RuntimeException(e);
        }
        return result == whatToExpect;
    }

    @Test
    public void runTests() {
        vc.setControllerComponent(cc);
        int tests = 0;
        URL asd = this.getClass().getResource("tests");
        Assertions.assertNotNull(asd);
        File test = new File(asd.getPath());
        List<File> fileList = new ArrayList<>(Arrays.asList(Objects.requireNonNull(test.listFiles())));
        for(File dir : fileList) {
            if(dir.isDirectory()) {
                tests++;
                PrintStream out;
                try {
                    out = new PrintStream(new FileOutputStream(dir.getAbsolutePath() + "/result.txt", true));
                    System.setOut(out);
                } catch (FileNotFoundException e) {
                    throw new RuntimeException(e);
                }
                log.info("Hanyadik teszt: " + tests);
                log.info("Teszt neve: " + dir.getName());
                beforeTests();
                interpretCommands(dir.getAbsolutePath() + "/" + commandFile);
                boolean result = translateExpectedTo(dir.getAbsolutePath() + "/" + expectedFile);
                File resultFile = new File(dir.getAbsolutePath() + "/result.txt");
                if(result) {
                    try {
                        FileWriter fw = new FileWriter(resultFile);
                        fw.write("Test passed");
                        fw.close();
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
                else {
                    try {
                        FileWriter fw = new FileWriter(resultFile);
                        fw.write("Test failed");
                        for(Object o : GameModel.gameObjects.valueSet()) {
                            fw.append(o.toString() + "\n");
                        }
                        fw.close();
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                } //not working :) valszeg azert mert csak a translateExpectedTo eredmenyet nezi, nem mas throwjat
                //idk
                System.setOut(System.out);
                out.close();
            }
        }
    }

    private void beforeTests() {
        GameModel.randomSwitch = false;
        GameModel.gameObjects = new BiMap<>();
        GameModel.gombasz = new HashMap<>();
        GameModel.map = new com.beingchilling.game.Map();
        GameModel.rovarasz = new HashMap<>();
    }
}

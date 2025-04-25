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
import java.util.regex.Pattern;

public class TesztMain {

    private final String commandFile = "input.txt";
    private final String expectedFile = "output.txt";
    private static final ViewComponent vc = new ViewComponent();
    private static final ControllerComponent cc = new ControllerComponent(vc);

    //bemeneti
    private void interpretCommands(String fileName) {
        File file = new File(fileName);
        try {
            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);
            while(br.ready()) {
                String command = br.readLine();
                if(vc.validate(command))
                    cc.ArgumentManagement(command);
                else
                    Assertions.fail();
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
                if(eTrimmed[1].equals(GameModel.gameObjects.getV(eTrimmed[0]).toString()))
                    ;
                else
                    result = false;
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

        URL asd = this.getClass().getResource("tests");
        Assertions.assertNotNull(asd);
        File test = new File(asd.getPath());
        List<File> fileList = new ArrayList<>(Arrays.asList(Objects.requireNonNull(test.listFiles())));
        for(File dir : fileList) {
            if(dir.isDirectory()) {
                beforeTests();
                interpretCommands(dir.getAbsolutePath() + "/" + commandFile);
                Assertions.assertTrue(translateExpectedTo(dir.getAbsolutePath() + "/" + expectedFile));
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

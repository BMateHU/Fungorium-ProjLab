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

public class TesztMain {

    private final String commandFile = "input.txt";
    private final String expectedFile = "output.txt";
    private final GameModel expectedModel = null;
    private final GameModel actualModel = null;
    private static ViewComponent vc = new ViewComponent();
    private static ControllerComponent cc = new ControllerComponent(vc);

    private boolean parseModel(GameModel actual, GameModel expected) {
        return actual.toString().equals(expected.toString());
    }

    //bemeneti
    private void interpretCommands(String fileName, GameModel model) {
        File file = new File(fileName);
        try {
            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);
            while(br.ready()) {
                vc.validate(br.readLine());
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    //kimeneti
    private boolean translateExpectedTo(String fileName, GameModel model) {
        File file = new File(fileName);

        return true;
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
                interpretCommands(dir.getAbsolutePath() + "/" + commandFile, actualModel);
                translateExpectedTo(dir.getAbsolutePath() + "/" + expectedFile, expectedModel);
                Assertions.assertTrue(parseModel(actualModel, expectedModel));
            }
        }
    }

    private void beforeTests() {
        GameModel.gameObjects = new BiMap<>();
        GameModel.gombasz = new HashMap<>();
        GameModel.map = new com.beingchilling.game.Map();
        GameModel.rovarasz = new HashMap<>();
    }
}

import com.beingchilling.game.GameModel;
import com.beingchilling.model.MushroomBody;
import com.beingchilling.model.Tekton;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.*;

public class TesztMain {

    class Qu {
        public Object mit;
        public Object hova;
        public Qu(Object mit, Object hova) {
            this.mit = mit;
            this.hova = hova;
        }
    }

    private final String commandFile = "";
    private final String expectedFile = "";
    private final GameModel expectedModel = new GameModel();
    private final GameModel actualModel = new GameModel();

    private boolean parseModel(GameModel actual, GameModel expected) {
        return actual.toString().equals(expected.toString());
    }

    //bemeneti
    private void interpretCommands(String fileName, GameModel model) {
        File file = new File(fileName);
    }

    //kimeneti
    private boolean translateExpectedTo(String fileName, GameModel model) {
        File file = new File(fileName);
        Queue<Qu> queue = new LinkedList<>();
        Tekton t1 = new Tekton();

        queue.add(new Qu("mushroom=m1", t1));
        queue.add(new Qu("neighbours=t2", t1));

        for(int i = 0; i < queue.size(); i++) {

        }

        return true;
    }

    @Test
    public void runTests() {
        List<File> fileList = new ArrayList<>();
        File asd = new File("root");
        for(int i = 1; i <= 36; i++) {
            fileList.add(new File("useCase" + i));
        }
        while(asd.isDirectory()) {
            asd.listFiles();
            beforeTests();
            interpretCommands(commandFile, actualModel);
            translateExpectedTo(expectedFile, expectedModel);
            Assertions.assertTrue(parseModel(actualModel, expectedModel));
        }
    }

    private void beforeTests() {

    }
}

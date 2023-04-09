import java.io.IOException;

import edu.neu.genetic.GeneticAlgorithm;
import edu.neu.genetic.Preset;

public class Main {
    public static void main(String[] args) {

        GeneticAlgorithm geneticAlgorithm;
        try {
            geneticAlgorithm = Preset.getDefaultGA();
            geneticAlgorithm.runWithDebugMode();
            geneticAlgorithm.showGraphInWindow();
            geneticAlgorithm.printProperties();
            geneticAlgorithm.printResults();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
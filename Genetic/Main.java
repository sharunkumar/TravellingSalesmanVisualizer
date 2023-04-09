import java.io.IOException;

import GeneticAlgorithms.GeneticAlgorithm;
import GeneticAlgorithms.Preset;

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
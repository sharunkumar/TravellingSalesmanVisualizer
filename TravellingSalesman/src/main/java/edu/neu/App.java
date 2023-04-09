package edu.neu;

import edu.neu.genetic.GeneticAlgorithm;
import edu.neu.genetic.Preset;

import java.io.IOException;

public class App {
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

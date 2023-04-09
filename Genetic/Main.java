import GeneticAlgorithms.GeneticAlgorithm;
import GeneticAlgorithms.Preset;

public class Main {
    public static void main(String[] args) {

        GeneticAlgorithm geneticAlgorithm = Preset.getDefaultGA();

        geneticAlgorithm.runWithDebugMode();
        geneticAlgorithm.showGraphInWindow();
        geneticAlgorithm.printProperties();
        geneticAlgorithm.printResults();

    }
}
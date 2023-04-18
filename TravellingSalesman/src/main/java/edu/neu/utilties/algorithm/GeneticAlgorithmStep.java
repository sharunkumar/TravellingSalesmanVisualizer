package edu.neu.utilties.algorithm;

import edu.neu.display.TravellingSalesmanWindow;
import edu.neu.genetic.GeneticAlgorithm;
import edu.neu.modals.Location;
import edu.neu.modals.Population;
import edu.neu.utilties.abstractions.IAlgorithmStep;

import static edu.neu.christofides.Constants.RANDOM;

public class GeneticAlgorithmStep implements IAlgorithmStep {

    private final int popSize;
    private final int maxGen;
    private final double crossoverRate;
    private final double mutationRate;
    private final Location[] locations;

    public GeneticAlgorithmStep(int popSize, int maxGen, double crossoverRate, double mutationRate,
                                Location[] locations) {
        this.popSize = popSize;
        this.maxGen = maxGen;
        this.crossoverRate = crossoverRate;
        this.mutationRate = mutationRate;
        this.locations = locations;
    }

    @Override
    public int[] run(Location[] locations, double[][] weightMatrix, int[] route, TravellingSalesmanWindow window) {
        GeneticAlgorithm geneticAlgorithm = new GeneticAlgorithm();
        var population = new Population(popSize);

        population.populate(locations);
        geneticAlgorithm.setPopulation(population);
        geneticAlgorithm.setMaxGen(maxGen);
        geneticAlgorithm.setK(3);
        geneticAlgorithm.setElitismValue(1);
        geneticAlgorithm.setCrossoverRate(crossoverRate);
        geneticAlgorithm.setMutationRate(mutationRate);
        geneticAlgorithm.setRandom(RANDOM);
        geneticAlgorithm.forceUniqueness(false);
        geneticAlgorithm.setLocalSearchRate(0.00);
        geneticAlgorithm.setCrossoverType(GeneticAlgorithm.CrossoverType.UNIFORM_ORDER);
        geneticAlgorithm.setMutationType(GeneticAlgorithm.MutationType.INSERTION);
        geneticAlgorithm.runWithDebugMode(window);
        geneticAlgorithm.showGraphInWindow();
        geneticAlgorithm.printProperties();
        geneticAlgorithm.printResults();

        return geneticAlgorithm.getPopulation().getMostFit().getRoute();
    }
}

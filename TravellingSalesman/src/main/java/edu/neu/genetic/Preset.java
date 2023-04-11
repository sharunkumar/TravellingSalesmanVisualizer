package edu.neu.genetic;

import edu.neu.io.DataSet;

import java.io.IOException;
import java.util.Random;

import static edu.neu.modals.Population.fromDataSet;

public class Preset {
    private Preset() {
    }

    public static GeneticAlgorithm getDefaultGA() throws IOException {

        Random random = new Random();
        long seed = random.nextLong();
        System.out.println("Seed: " + seed);
        Random r = new Random();
        r.setSeed(seed);

        // Parameters.
        int popSize = 500; // Size of the population.
        int maxGen = 1000; // Number of generations to run.
        double crossoverRate = 0.90; // Odds that crossover will occur.
        double mutationRate = 0.04; // Odds that mutation will occur.

        GeneticAlgorithm geneticAlgorithm = new GeneticAlgorithm();

        geneticAlgorithm.setPopulation(fromDataSet(popSize, DataSet.DefaultDataSet(), r));
        // geneticAlgorithm.setPopulation(Population.getRandomPopulation(100, popSize,
        // r));
        geneticAlgorithm.setMaxGen(maxGen);
        geneticAlgorithm.setK(3);
        geneticAlgorithm.setElitismValue(1);
        geneticAlgorithm.setCrossoverRate(crossoverRate);
        geneticAlgorithm.setMutationRate(mutationRate);
        geneticAlgorithm.setRandom(r);
        geneticAlgorithm.forceUniqueness(false);
        geneticAlgorithm.setLocalSearchRate(0.00);
        geneticAlgorithm.setCrossoverType(GeneticAlgorithm.CrossoverType.UNIFORM_ORDER);
        geneticAlgorithm.setMutationType(GeneticAlgorithm.MutationType.INSERTION);

        return geneticAlgorithm;
    }

}

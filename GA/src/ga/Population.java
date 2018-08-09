package ga;

import java.util.*;

public class Population {
	
	ArrayList<Individual> chromosomes = new ArrayList<Individual>();
	ArrayList<Individual> toBeCrossed = new ArrayList<Individual>();

	float totalFitness;
	float totalProbability;
	float probability = 0;
	int currentFittest;
	int parent;
	int leastFit;
	
	public void initialiseChromosomes() {
		for(int i = 0; i < Globals.populationSize; i++) {
			chromosomes.add(new Individual());
		}
	}
	
	public void getFitnesses() {
		for(int i = 0; i < chromosomes.size(); i++) {
			chromosomes.get(i).fitness = 0;
			for (int j = 0; j < chromosomes.get(i).genes.length; j++) {
	            if (chromosomes.get(i).genes[j] == 1) {
	            		chromosomes.get(i).fitness++;
	            }
	        }
		}
	}
	
	public void getProbabilities(float totalFitness) {
		for(int i = 0; i < chromosomes.size(); i++) {
			chromosomes.get(i).probability = chromosomes.get(i).fitness / totalFitness;
		}
	}
	
	public void getTotalProbabilities() {
		totalProbability = 0;
		for(int i = 0; i < chromosomes.size(); i++) {
    			totalProbability += chromosomes.get(i).probability;
    		}
	}
	
	public void getFittestChromosome() {
		currentFittest = 0;
		for(int i = 0; i < chromosomes.size(); i++) {
			if(chromosomes.get(i).fitness > chromosomes.get(currentFittest).fitness) {
				currentFittest = i;
			}
		}
	}
	
	public void getLeastFitChromosome() {
		leastFit = 0;
		for(int i = 0; i < chromosomes.size(); i++) {
			if(chromosomes.get(i).fitness < chromosomes.get(leastFit).fitness) {
				leastFit = i;
			}
		}
	}
	
	public void getTotalFitness() {
		totalFitness = 0;
	    	for(int i = 0; i < chromosomes.size(); i++) {
	    		totalFitness += chromosomes.get(i).fitness;
	    	}
	}
	
	public void chooseParents() {
		getTotalProbabilities();
		Random random = new Random();
		float randomNumber = random.nextFloat() * totalProbability;
		float lastRunningTotal = 0;
		float runningTotal = 0;
		System.out.println("Random number: " + randomNumber);
		for(int i = 0; i < chromosomes.size(); i++) {
			runningTotal += chromosomes.get(i).probability;
			if(randomNumber > lastRunningTotal && randomNumber <= runningTotal) {
				parent = i;
				System.out.println("Parent: " + Arrays.toString(chromosomes.get(i).genes));
				probability = chromosomes.get(i).probability;
				toBeCrossed.add(chromosomes.get(i));
				break;
			}
			lastRunningTotal += chromosomes.get(i).probability;
		}
	}
}

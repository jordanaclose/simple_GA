package ga;

import java.util.*;
import processing.core.PApplet;

public class GA extends PApplet{
	
	int generationNumber = 0;

	public static void main(String[] args) {
		PApplet.main("ga.GA");
		GA ga = new GA();
		Population population = new Population();
		population.initialiseChromosomes();
		while(population.totalFitness < Globals.optimal * Globals.populationSize) {
			System.out.println("Current generation: " + ga.generationNumber);
			population.getFitnesses();
			population.getTotalFitness();
			population.getProbabilities(population.totalFitness);
			System.out.println("Total fitness: " + population.totalFitness);
			for(int i = 0; i < population.chromosomes.size(); i++) {
				System.out.print("Fitness: " + population.chromosomes.get(i).fitness + " ");
				System.out.print("Probability: " + population.chromosomes.get(i).probability + " ");
				System.out.println(Arrays.toString(population.chromosomes.get(i).genes));
			}
			population.getFittestChromosome();
			System.out.println("Fittest: " + Arrays.toString(population.chromosomes.get(population.currentFittest).genes));
			ga.selection(population);
			for(int j = 0; j < population.chromosomes.size(); j++) {
				System.out.print("Fitness: " + population.chromosomes.get(j).fitness + " ");
				System.out.print("Probability: " + population.chromosomes.get(j).probability + " ");
				System.out.println(Arrays.toString(population.chromosomes.get(j).genes));
			}
			population.getFitnesses();
			population.getTotalFitness();
			population.getProbabilities(population.totalFitness);
			ga.crossover(population);
			for(int k = 0; k < population.chromosomes.size(); k++) {
				System.out.print("Fitness: " + population.chromosomes.get(k).fitness + " ");
				System.out.print("Probability: " + population.chromosomes.get(k).probability + " ");
				System.out.println(Arrays.toString(population.chromosomes.get(k).genes));
			}
			population.getFitnesses();
			population.getTotalFitness();
			population.getProbabilities(population.totalFitness);
			for(int l = 0; l < 2; l++) {
				population.getLeastFitChromosome();
				System.out.println("Least fit: " + Arrays.toString(population.chromosomes.get(population.leastFit).genes));
				population.chromosomes.remove(population.leastFit);
			}
			population.getFitnesses();
			population.getTotalFitness();
			population.getProbabilities(population.totalFitness);
			for(int m = 0; m < population.chromosomes.size(); m++) {
				System.out.print("Fitness: " + population.chromosomes.get(m).fitness + " ");
				System.out.print("Probability: " + population.chromosomes.get(m).probability + " ");
				System.out.println(Arrays.toString(population.chromosomes.get(m).genes));
			}
			ga.generationNumber++;
		}
	}
	
	public void settings(){
		//size(600, 600);
    }

    public void setup(){
    		//background(0,0,0);
    }

    public void draw(){
    	
    }
    
    public void selection(Population population) {
    		for(int j = 0; j < 2; j++) {
    			population.chooseParents();
    		}
    		//population.chromosomes.clear();
    		for(int i = 0; i < population.toBeCrossed.size(); i++) {
    			System.out.print("Fitness: " + population.toBeCrossed.get(i).fitness + " ");
    			System.out.println("Selected: " + Arrays.toString(population.toBeCrossed.get(i).genes));
    		}
    }
    
	public void crossover(Population population) {
		for(int i = 0; i < Globals.crossoverPoint; i++) {
    			int tempValue = population.toBeCrossed.get(0).genes[i];
    			population.toBeCrossed.get(0).genes[i] = population.toBeCrossed.get(1).genes[i];
    			population.toBeCrossed.get(1).genes[i] = tempValue;
		}
		for(int j = 0; j < population.toBeCrossed.size(); j++) {
			System.out.println("Crossed: " + Arrays.toString(population.toBeCrossed.get(j).genes));
		}
		mutate(population);
		population.chromosomes.add(population.toBeCrossed.get(0));
		population.toBeCrossed.remove(0);
		population.chromosomes.add(population.toBeCrossed.get(0));
		population.toBeCrossed.clear();
	}
	
	public void mutate(Population population) {
		for(int i = 0; i < 2; i++) {
			Random random = new Random();
			int randomInt = random.nextInt(Globals.geneLength);
			float randomFloat = random.nextFloat() * 10; //10% chance of mutation
			if(randomFloat < 1) {
				if(population.toBeCrossed.get(i).genes[randomInt] == 1) {
					population.toBeCrossed.get(i).genes[randomInt] = 0;
				}
				else {
					population.toBeCrossed.get(i).genes[randomInt] = 1;
				}
				System.out.println("MUTATED: " + Arrays.toString(population.toBeCrossed.get(i).genes));
			}
		}
	}
}

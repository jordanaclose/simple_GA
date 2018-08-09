package ga;

import java.util.Random;

public class Individual {
	float fitness;
	float probability = 0;
	int[] genes = new int[Globals.geneLength];
	
	public Individual() {
        Random random = new Random();

        //Set genes randomly for each individual
        for (int i = 0; i < genes.length; i++) {
            genes[i] = random.nextInt(2); //random integer out of 0 and 1
        }
    }
}

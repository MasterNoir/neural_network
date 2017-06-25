package de.masternoir.perceptron;

public class Driver {

	public static void main(String[] args) {
		
		double[][][] trainingDataset = {{{0,0},{0}},
										{{1,0},{0}},
										{{0,1},{0}},
										{{1,1},{1}}};
		Perceptron perceptron = new Perceptron(0.05,2,0);
		
		
		for(int i = 0; i < 20; i++){
			perceptron.trainDataset(trainingDataset[0][0], trainingDataset[0][1][0]);
			perceptron.trainDataset(trainingDataset[1][0], trainingDataset[1][1][0]);
			perceptron.trainDataset(trainingDataset[2][0], trainingDataset[2][1][0]);
			perceptron.trainDataset(trainingDataset[3][0], trainingDataset[3][1][0]);
			System.out.println(perceptron.getError());
			System.out.println("###########");
		}
		
	}

}

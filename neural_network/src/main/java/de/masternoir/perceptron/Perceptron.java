package de.masternoir.perceptron;

public class Perceptron {
	
	private double learning_rate;
	private double inputs[];
	private double weights[];
	private double threshold;
	private double error;
	
	public Perceptron(double learning_rate, int nmbrOfInputs, double threshold){
		this.learning_rate = learning_rate;
		
		this.weights = new double[nmbrOfInputs+1];
		for(int i = 0; i < weights.length; i++){
			this.weights[i] = Math.random();
		}
		
		this.threshold = threshold;
	}
	
	public void trainDataset(double inputs[], double expectation){
		
		int prediction = 0;
		this.inputs = new double[inputs.length+1];
		error = 0;
		
		this.inputs[0] = 1;
		for(int i = 1; i < this.inputs.length; i++){
			this.inputs[i] = inputs[i-1];
		}
		
		prediction = predict();
		
		for(int i = 0; i < weights.length; i++){
			weights[i] = adjustWeights(weights[i], expectation, prediction, this.inputs[i]);
		}
	}
	
	private int predict(){
		
		double activation = 0;
		
		for(int i = 0; i < inputs.length; i++){
			activation = activation + inputs[i]*weights[i];
		}
		if (activation > threshold){
			return 1;
		}else{
			return 0;
		}
	}
	
	private void adjustError(double error){
		if(Math.abs(this.error) < Math.abs(error)){
			this.error = error;
		}
	}
	
	private double adjustWeights(double weight, double expected, double predicted, double input){
		
		double error = expected - predicted;
		
		adjustError(error);
		weight = weight + learning_rate * error * input;
		
		return weight;
	}
	
	public double getError(){
		return error;
	}
	
	public double getLearningRate(){
		return learning_rate;
	}
	
	public double getThreshold(){
		return threshold;
	}
	
	public double[] getInputs(){
		return inputs;
	}
	
	public void setInputs(double inputs[]){
		this.inputs = inputs;
	}
	
	public double[] getWeights(){
		return weights;
	}
}

package de.masternoir.perceptron;

import static org.junit.Assert.*;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.junit.Test;


public class PerceptronTest {

	double[][][] trainingDataset = {{{0,0},{0}},
			{{1,0},{0}},
			{{0,1},{0}},
			{{1,1},{1}}};
	
	Perceptron perceptron;
	
	protected void setUp() throws Exception {
		
		perceptron = new Perceptron(0.05,2,0);
	}
	
	@Test
	public void adjustErrorTest(){
		
		perceptron = new Perceptron(0.05,2,0);
        try {
        	Method method = Perceptron.class.getDeclaredMethod("adjustError", double.class);
            method.setAccessible(true);
            
			method.invoke(perceptron, 1.0);
			assertEquals(perceptron.getError(), 1.0, 0.0);
			
			method.invoke(perceptron, 2.0);
			assertEquals(perceptron.getError(), 2.0, 0.0);
			
			method.invoke(perceptron, 0.0);
			assertEquals(perceptron.getError(), 2.0, 0.0);
			
			method.invoke(perceptron, 0.5);
			assertEquals(perceptron.getError(), 2.0, 0.0);
			
			method.invoke(perceptron, -0.5);
			assertEquals(perceptron.getError(), 2.0, 0.0);
			
			method.invoke(perceptron, -3.5);
			assertEquals(perceptron.getError(), -3.5, 0.0);
			
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void adjustWeightsTest(){
		
		perceptron = new Perceptron(0.05,2,0);
        try {
        	Method method = Perceptron.class.getDeclaredMethod("adjustWeights", double.class, double.class, double.class, double.class);
            method.setAccessible(true);
            
			Object result = method.invoke(perceptron, 0.5, 1.0, 0.0, 1.0);
			assertEquals(0.5 + perceptron.getLearningRate() * (1.0 - 0.0) * 1.0, result);
			
			
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void predictTest(){
		
		perceptron = new Perceptron(0.05,2,0);
        try {
        	Method method = Perceptron.class.getDeclaredMethod("predict");
            method.setAccessible(true);
            
            double inputs[] = {1.0,0.0};
            perceptron.setInputs(inputs);
            
			Object result = method.invoke(perceptron);
			assertEquals(1.0 * perceptron.getWeights()[0] + 0.0 * perceptron.getWeights()[1] > 0 ? 1 : 0, result);
			
			inputs[0] = 0.0;
			inputs[1] = 1.0;
            perceptron.setInputs(inputs);
            
			result = method.invoke(perceptron);
			assertEquals(0.0 * perceptron.getWeights()[0] + 1.0 * perceptron.getWeights()[1] > 0 ? 1 : 0, result);
			
			inputs[0] = 1.0;
			inputs[1] = 1.0;
            perceptron.setInputs(inputs);
            
			result = method.invoke(perceptron);
			assertEquals(1.0 * perceptron.getWeights()[0] + 1.0 * perceptron.getWeights()[1] > 0 ? 1 : 0, result);
			
			inputs[0] = 0.0;
			inputs[1] = 0.0;
            perceptron.setInputs(inputs);
            
			result = method.invoke(perceptron);
			assertEquals(0.0 * perceptron.getWeights()[0] + 0.0 * perceptron.getWeights()[1] > 0 ? 1 : 0, result);
			
			
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void trainDatasetTest(){
		
		perceptron = new Perceptron(0.05,2,1);
		double error;
		int counter = 1;
		do{
			System.out.println("1. epoche ---" + counter + "---");
			error = 0;
			perceptron.trainDataset(trainingDataset[0][0], trainingDataset[0][1][0]);
			error = perceptron.getError() > error ? perceptron.getError() : error;
			
			perceptron.trainDataset(trainingDataset[1][0], trainingDataset[1][1][0]);
			error = perceptron.getError() > error ? perceptron.getError() : error;
			
			perceptron.trainDataset(trainingDataset[2][0], trainingDataset[2][1][0]);
			error = perceptron.getError() > error ? perceptron.getError() : error;
			
			perceptron.trainDataset(trainingDataset[3][0], trainingDataset[3][1][0]);
			error = perceptron.getError() > error ? perceptron.getError() : error;
			System.out.println(error);
			counter++;
		}while(error > 0);
		
		System.out.println("_____________________________________________________");
		
		perceptron = new Perceptron(0.05,2,2);
		counter = 1;
		do{
			System.out.println("2. epoche ---" + counter + "---");
			error = 0;
			perceptron.trainDataset(trainingDataset[0][0], trainingDataset[0][1][0]);
			error = perceptron.getError() > error ? perceptron.getError() : error;
			
			perceptron.trainDataset(trainingDataset[1][0], trainingDataset[1][1][0]);
			error = perceptron.getError() > error ? perceptron.getError() : error;
			
			perceptron.trainDataset(trainingDataset[2][0], trainingDataset[2][1][0]);
			error = perceptron.getError() > error ? perceptron.getError() : error;
			
			perceptron.trainDataset(trainingDataset[3][0], trainingDataset[3][1][0]);
			error = perceptron.getError() > error ? perceptron.getError() : error;
			System.out.println(error);
			counter++;
		}while(error > 0);
	}

}

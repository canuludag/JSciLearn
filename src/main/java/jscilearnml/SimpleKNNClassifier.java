package jscilearnml;

import java.lang.Math;
import java.util.ArrayList;

public class SimpleKNNClassifier{

	// We'll use these lists in fit method
	private ArrayList<ArrayList<Double>> featuresTrain;
	private ArrayList<Integer> labelsTrain;

	// Calculates the Euclidean Distance between two points
	// Simple formula is:
	// d(q,p) = sqrt((q1-p1)**2 + (q2-p2)**2 + ... + (qn-pn)**2)
	public double calculateEuclideanDistance(ArrayList<Double> pointsQ, ArrayList<Double> pointsP){
		double sumOfDifferenceSquared = 0.0;

		for(int i = 0; i < pointsQ.size(); i++){
			sumOfDifferenceSquared += Math.pow((pointsP.get(i)-pointsQ.get(i)), 2);
		}
		return Math.sqrt(sumOfDifferenceSquared);
	}

	// Fit the data
	public void fit(ArrayList<ArrayList<Double>> featuresTrain, ArrayList<Integer> labelsTrain){

		this.featuresTrain = featuresTrain;
		this.labelsTrain = labelsTrain;

	}

	// Makes prediction about the new given values
	public ArrayList<Integer> predict(ArrayList<ArrayList<Double>> featuresTest){

		ArrayList<Integer> predictions = new ArrayList<Integer>();

		for(ArrayList<Double> row : featuresTest){

			int label = closest(row);
			predictions.add(label);

		}

		return predictions;

	}

	// Find the closest point
	// K = 1
	public int closest(ArrayList<Double> row){

		// Let's assume that the first data point has the bes distance at first
		double bestDistance = calculateEuclideanDistance(row, featuresTrain.get(0));
		// And the best index is the first data points' index
		int bestIndex = 0;

		for(int i = 0; i < featuresTrain.size(); i++){
			double distance = calculateEuclideanDistance(row, featuresTrain.get(i));
			if (distance < bestDistance){
				bestDistance = distance;
				bestIndex = i;
			}
		}

		return labelsTrain.get(bestIndex);

	}

}

import jscilearnml.*;
import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;

// This class is for testing our SimpleKNNClassifier
public class Workspace{

	public static void main(String[] args){

		// Lists for features(X), labels(Y) data lists
		ArrayList<ArrayList<Double>> featuresX = new ArrayList<ArrayList<Double>>();
		ArrayList<Integer> labelsY = new ArrayList<Integer>();
		// Lists for resulting predictions
		ArrayList<Integer> predictions = new ArrayList<Integer>();
		// Lists for testing new data on our classifier to predict
		ArrayList<ArrayList<Double>> featuresTest = new ArrayList<ArrayList<Double>>();

		// Create an instance of the classifier
		//SimpleKNNClassifier classifier = new SimpleKNNClassifier();

		// Create an instance of the classifier by loading saved model
		SimpleKNNClassifier classifier = TrainedModelHelper.loadModel("trained_knn_model");

		// A class for processing CSV files
		// and separating them as features and labels lists
		DataPreProcessing preProcess = new DataPreProcessing();
		preProcess.processCSVFile("../datasets/iris-with-names.csv", "target", "categoric", ",");

		featuresX = preProcess.getFeatures(); // processed features list
		labelsY = preProcess.getLabels(); // processed labels list

		System.out.printf("FEATURES: %n %s %n", featuresX.toString());
		System.out.printf("LABELS: %n %s %n", labelsY.toString());

		// Assign the test list with the returning list
		featuresTest = preProcess.splitTrainTest(featuresX, 0.2);

		// Let's add some sample test features from the actual training features set
		/*featuresTest.add(featuresX.get(0));
		featuresTest.add(featuresX.get(78));
		featuresTest.add(featuresX.get(145));*/

		// Fit and train the classifier
		classifier.fit(featuresX, labelsY);

		// Save trained model classifier for later use
		//TrainedModelHelper.saveModel(classifier, "trained_knn_model");

		// Make predictions on new features test data
		predictions = classifier.predict(featuresTest);

		System.out.printf("Predictions: %s %n", predictions.toString());

	}

}

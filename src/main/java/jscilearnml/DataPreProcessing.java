package jscilearnml;

import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.Set;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Iterator;
import java.util.Random;

public class DataPreProcessing{

	// Features and labels lists
	// We'll access them after preprocessing
	public ArrayList<ArrayList<Double>> features = new ArrayList<ArrayList<Double>>();
	public ArrayList<Integer> labels = new ArrayList<Integer>();

	// Default constructor
	public DataPreProcessing(){

	}

	// Getters and Setters
	public ArrayList<ArrayList<Double>> getFeatures(){
		return this.features;
	}

	public ArrayList<Integer> getLabels(){
		return this.labels;
	}

	/*  -- Gets the CSV file and sets features and labels lists --
	 	This method requires:
			CSV filename: 'iris.csv', (String)
			name of the label: 'target', (String)
			label type: 'numeric' or 'categoric' (String)
			delimiter: ',' (String)
	*/
	public void processCSVFile(String fileName,
							   String labelColumnName, String labelType, String delimiter){

		ArrayList<ArrayList<Double>> featuresList = new ArrayList<ArrayList<Double>>();
		ArrayList<Integer> labelsList = new ArrayList<Integer>();
		ArrayList<String> categoricLabels = new ArrayList<String>();
		int indexOfLabelColumn = 0;

		try{

			Scanner fileScanner = new Scanner(new File(fileName));
			int rowIndex = 0;

			while(fileScanner.hasNextLine()){

				String line = fileScanner.nextLine();

				ArrayList<Double> rowFeatures = new ArrayList<Double>();

				Scanner lineScanner = new Scanner(line);
				lineScanner.useDelimiter(delimiter);
				int colIndex = 0;
				while(lineScanner.hasNext()){

					String item = lineScanner.next();

					// Find the index of labels column
					if (item.equals(labelColumnName)) {
						indexOfLabelColumn = colIndex;
					}

					if (rowIndex != 0) {
						if (colIndex != indexOfLabelColumn) {
							rowFeatures.add(Double.parseDouble(item));
						}else{

							if (labelType.equals("numeric")) {

								labelsList.add(Integer.parseInt(item));

							}else if(labelType.equals("categoric")){

								categoricLabels.add(item);

							}

						}
					}

					colIndex++;

				}

				if (rowFeatures.size() > 0) {
					featuresList.add(rowFeatures);
				}

				lineScanner.close();

				rowIndex++;

			}

			fileScanner.close();

			features = featuresList;
			if (labelType.equals("numeric")) {
				labels = labelsList;
			}else if(labelType.equals("categoric")){
				labels = labelNumericEncoder(categoricLabels);
			}

		} catch (FileNotFoundException fnfe){
			System.out.printf("An error occured. Message is %s", fnfe.getMessage());
		}

	}

	/* 	-- Gets the CSV file and sets features and labels lists --
		This method requires:
			CSV filename: 'iris.csv', (String)
			column index of the labels: 3-4-5, (int)
			label type: 'numeric' or 'categoric' (String)
			delimiter: ',' (String)
	*/
	public void processCSVFile(String fileName,
								int labelColumnIndex, String labelType, String delimiter){

		ArrayList<ArrayList<Double>> featuresList = new ArrayList<ArrayList<Double>>();
		ArrayList<Integer> labelsList = new ArrayList<Integer>();
		ArrayList<String> categoricLabels = new ArrayList<String>();

		try{

			Scanner fileScanner = new Scanner(new File(fileName));
			int rowIndex = 0;

			while(fileScanner.hasNextLine()){

				String line = fileScanner.nextLine();

				ArrayList<Double> rowFeatures = new ArrayList<Double>();

				Scanner lineScanner = new Scanner(line);
				lineScanner.useDelimiter(delimiter);
				int colIndex = 0;
				while(lineScanner.hasNext()){

					String item = lineScanner.next();

					if (rowIndex != 0) {
						if (colIndex != labelColumnIndex) {
							rowFeatures.add(Double.parseDouble(item));
						}else{

							if (labelType.equals("numeric")) {

								labelsList.add(Integer.parseInt(item));

							}else if(labelType.equals("categoric")){

								categoricLabels.add(item);

							}
						}
					}

					colIndex++;

				}

				if (rowFeatures.size() > 0) {
					featuresList.add(rowFeatures);
				}

				lineScanner.close();

				rowIndex++;

			}

			fileScanner.close();

			features = featuresList;
			if (labelType.equals("numeric")) {
				labels = labelsList;
			}else if(labelType.equals("categoric")){
				labels = labelNumericEncoder(categoricLabels);
			}

		} catch (FileNotFoundException fnfe){
			System.out.printf("An error occured. Message is %s", fnfe.getMessage());
		}

	}

	// Randomly selects test features from the actual features list
	// by using percentage value. 0.1 = 10% --> for test list
	public ArrayList<ArrayList<Double>> splitTrainTest(ArrayList<ArrayList<Double>> trainFeatures,
														double splitPercentage){

		ArrayList<ArrayList<Double>> testFeatures = new ArrayList<ArrayList<Double>>();
		Random rand = new Random();

		int elementSize = trainFeatures.size();
		int testElementSize = (int)(elementSize * splitPercentage);
		for(int i=0; i < testElementSize; i++){
			int selectIndex = rand.nextInt(elementSize);
			testFeatures.add(trainFeatures.get(selectIndex));
		}

		return testFeatures;

	}

	// Converts the categorical(Strings) label data set into numeric(Integers) labels
	private ArrayList<Integer> labelNumericEncoder(ArrayList<String> categoricList){

		ArrayList<Integer> numericList = new ArrayList<Integer>();
		Set<String> uniqueCategoriesSet = new LinkedHashSet<String>(categoricList);
		ArrayList<String> uniqueCategoriesList = new ArrayList<String>(uniqueCategoriesSet);

		String indexElement = "";

		for(int i = 0; i < categoricList.size(); i++){
			indexElement = categoricList.get(i);

			if (uniqueCategoriesList.contains(indexElement)) {
				numericList.add(uniqueCategoriesList.indexOf(indexElement));
			}

		}

		return numericList;
	}

	// Checks if a String's format is number 
	private boolean isNumeric(String str){
		 //match a number with optional '-' and decimal.
  		return str.matches("-?\\d+(\\.\\d+)?");
  	}

}

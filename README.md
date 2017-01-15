# JSciLearn
This is a simple **machine learning** library that written in **Java**. It can be used for Java or Android projects easily without any additional library requirements. I'll be trying to keep this library very neat as possible as it can be. Pure Java libraries, no extra jars.

### Prerequisites

* Java knowledge
* Java Compiler

### Folder Structure

Tried to implement [Maven Folder Structure](https://maven.apache.org/guides/introduction/introduction-to-the-standard-directory-layout.html)

- **bin**
    - *jscilearnml*
        - Compiled class files
    - Workspace.class (class that contain main method)
- **datasets**
    - Sample CSV files
- **src**
    - *main*
        - *java*
            - *jscilearnml*
                - Machine learning library java files
            - Workspace.java

### Installing

You can copy Java files in **src/main/java/jscilearnml** package into your Java or Android project. It can be compiled by using javac or IDE without any additional Java library requirements.

### Built-in Machine Learning Algorithms

* K Nearest Neighbors *(Simple version. K assumed as 1)*

### Built-in Data Processing Methods

```
// Processing CSV files into features and labels ArrayList objects
processCSVFile(String fileName, String labelColumnName, String labelType, String delimiter);
processCSVFile(String fileName, int labelColumnIndex, String labelType, String delimiter);

// Split a percentage of the training data set into test list
splitTrainTest(ArrayList<ArrayList<Double>> trainFeatures, double splitPercentage);

``` 

### Saving/Loading Classifier Models

```
// Save trained classifier model
TrainedModelHelper.saveModel(SimpleKNNClassifier classifier, String fileName);
// Load trained classifier model
TrainedModelHelper.loadModel(String fileName);
```

### Usage

You can process your raw CSV files into List objects by using *DataPreProcessing* class.
``` 
// Lists for features(X), labels(Y) data lists
ArrayList<ArrayList<Double>> featuresX = new ArrayList<ArrayList<Double>>();
ArrayList<Integer> labelsY = new ArrayList<Integer>();

// A class for processing CSV files 
// and separating them as features and labels lists
DataPreProcessing preProcess = new DataPreProcessing();
preProcess.processCSVFile("../datasets/iris-with-names.csv", "target", "categoric", ",");

featuresX = preProcess.getFeatures(); // processed features list
labelsY = preProcess.getLabels(); // processed labels list
``` 
Create a test features list for prediction. Also create a list for predictions.
```
// List for predictions
ArrayList<Integer> predictions = new ArrayList<Integer>();

// List for testing new data on our classifier to predict
ArrayList<ArrayList<Double>> featuresTest = new ArrayList<ArrayList<Double>>();

// Assign the test list with the returning list
// Split the original training data set by percentage value
featuresTest = preProcess.splitTrainTest(featuresX, 0.2);
```
Fit and train the classifier by creating and instance of SimpleKNNClassifier class. Than you can make predictions.
```
// Create an instance of classifier
SimpleKNNClassifier classifier = new SimpleKNNClassifier();

// Fit and train the classifier
classifier.fit(featuresX, labelsY);

// You can save your classifier model to a file if you want
TrainedModelHelper.saveModel(classifier, "trained_knn_model");

// You can load your classifier model from file
TrainedModelHelper.loadModel("trained_knn_model");

// Make predictions on new features test data
predictions = classifier.predict(featuresTest);
```

### How To Compile

If you compile the code in old fashion way. Open your terminal and compile library Java files into **bin** folder.
```
javac -d bin -classpath "" src/main/java/jscilearnml/SimpleKNNClassifier.java
javac -d bin -classpath "" src/main/java/jscilearnml/DataPreProcessing.java
```
Then compile Workspace.java file by using library classes in your **bin** folder.
```
javac -d bin -classpath bin src/main/java/Workspace.java
```
Finally in your **bin** classes folder, run Workspace.class
```
java Workspace
```

### Authors
* **Can Uludağ** - [Linkedin Profile](https://tr.linkedin.com/in/canuludag) - [Github Profile](https://github.com/canuludag)

### License
This project is licensed under the MIT License - see the [LICENSE.md](LICENSE.md) file for details

# JSciLearn
This is a simple **machine learning** library that written in **Java**. Today the best scientific and machine learning related libraries are written in Python. Altough Python is very clean and powerful for that purposes, libraries that written in Java would be very helpful. I'll be trying to keep this library very neat as possible as it can be. Pure Java libraries, no extra jars.

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

* ```processCSVFile();``` *(Processing CSV files into features and labels ArrayList objects)*
* ```splitTrainTest();``` *(Coming soon)*

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
// Object for resulting predictions
ArrayList<Integer> predictions = new ArrayList<Integer>();

// Object for testing new data on our classifier to predict
ArrayList<ArrayList<Double>> featuresTest = new ArrayList<ArrayList<Double>>();

// Let's add some sample test features from getting the actual training features set
featuresTest.add(featuresX.get(0));
featuresTest.add(featuresX.get(78));
featuresTest.add(featuresX.get(145));
```
Fit and train the classifier by creating and instance of SimpleKNNClassifier class. Than you can make predictions.
```
// Our custom classifier
SimpleKNNClassifier classifier = new SimpleKNNClassifier();

// Fit and train the classifier
classifier.fit(featuresX, labelsY);

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

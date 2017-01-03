package jscilearnml;

import java.io.*;

public class TrainedModelHelper{

    // Deafult constructor
    public TrainedModelHelper(){

    }

    // Saves the trained classifier model to a .ser file
    public static void saveModel(SimpleKNNClassifier classifier, String fileName){
        try(
            FileOutputStream fos = new FileOutputStream(fileName + ".ser");
            ObjectOutputStream oos = new ObjectOutputStream(fos);
        ){
            oos.writeObject(classifier);
        }catch(IOException ioe){
            System.out.println("An error occured while saving the classifier");
            ioe.printStackTrace();
        }
    }

    // Loads the saved trained model from file
    // Returns SimpleKNNClassifier
    public static SimpleKNNClassifier loadModel(String fileName){
        SimpleKNNClassifier classifier = new SimpleKNNClassifier();
        try(
            FileInputStream fis = new FileInputStream(fileName + ".ser");
            ObjectInputStream ois = new ObjectInputStream(fis);
        ){
            classifier = (SimpleKNNClassifier) ois.readObject();
        }catch(IOException ioe){
            System.out.println("An error occured while reading the classifier");
            ioe.printStackTrace();
        }catch(ClassNotFoundException cnfe){
            System.out.println("An error occured while loading the classifier");
            cnfe.printStackTrace();
        }
        return classifier;
    }
}

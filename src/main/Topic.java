package main;

public enum Topic {
	// Enum for all different available topics to select from
    AI("AI"), CV("Computer Vision"), DS("Data Structure"), DB("Databases"), NN("Neural Networks"), SE("SE"), ALL("Misc");

    String name;

    Topic(String name) {
        this.name = name;
    }

}

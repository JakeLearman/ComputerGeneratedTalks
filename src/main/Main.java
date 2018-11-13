package main;

import java.io.BufferedReader;
import java.io.FileReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.Random;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.stage.Stage;

import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Slider;
import javafx.scene.control.Spinner;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import main.lexer.Lexeme;
import main.lexer.Lexer;
import main.parser.LexemeList;
import main.parser.Parser;
import main.postprocessor.Postprocessor;
import main.preprocessor.Preprocessor;


public class Main extends Application {

    // Windows Size
    final int SCENE_WIDTH = 800;
    final int SCENE_HEIGHT = 300;

    private static int accuracyVal; // A global variable to determine the accuracy (1-5) of the generated paper.

    // JavaFX variable creation.
    RadioButton generic, AI, dataS, dataB, SE, CV, NN, wiki;
    Slider slider;
    Button generate;
    Label title, status, accuracy, lengthLabel;
    VBox vbRadio, vbSettings, vbGenerate;
    HBox subject, subject2, settings, settingsTitle, hbStatus, hbGenerate;
    static Topic topic = Topic.ALL;
    ToggleGroup group;
    static Spinner<Integer> lengthSpinner;

    @Override
    public void start(Stage primaryStage) {
        try {
            // Make the radio buttons for the different paper options.
            generic = new RadioButton("Generic");
            generic.setPrefWidth(400);
            AI = new RadioButton("AI");
            AI.setPrefWidth(400);
            dataS = new RadioButton("Datastructures");
            dataS.setPrefWidth(400);
            dataB = new RadioButton("Databases");
            dataB.setPrefWidth(400);
            SE = new RadioButton("Software Dev");
            SE.setPrefWidth(400);
            CV = new RadioButton("Computer Vision");
            CV.setPrefWidth(400);
            NN = new RadioButton("Neural Networks");
            NN.setPrefWidth(400);
            wiki = new RadioButton("Wikipedia");
            wiki.setPrefWidth(400);

            // Add all the groups.
            group = new ToggleGroup();
            generic.setToggleGroup(group);
            AI.setToggleGroup(group);
            dataS.setToggleGroup(group);
            dataB.setToggleGroup(group);
            SE.setToggleGroup(group);
            wiki.setToggleGroup(group);
            CV.setToggleGroup(group);
            NN.setToggleGroup(group);

            generic.setSelected(true);

            subject = new HBox(8, generic, AI, dataS, dataB);
            subject2 = new HBox(8, SE, wiki, CV, NN);

            vbRadio = new VBox(2, subject, subject2);
            vbRadio.setPadding(new Insets(10,0,0,0));

            // Make the slider.
            slider = new Slider();
            slider.setMin(1);
            slider.setMax(5);
            slider.setValue(3);
            slider.setShowTickLabels(true);
            slider.setShowTickMarks(true);
            slider.setMajorTickUnit(1);
            slider.setSnapToTicks(true);
            slider.setBlockIncrement(1);
            slider.valueProperty().addListener((obs, oldval, newVal) -> slider.setValue(Math.round(newVal.doubleValue())));

            accuracy = new Label("Accuracy:");
            accuracy.setAlignment(Pos.TOP_CENTER);

            lengthLabel = new Label("Length:");
            lengthLabel.setAlignment(Pos.TOP_CENTER);
            lengthLabel.setPadding(new Insets(0, 25, 0, 0));

            lengthSpinner = new Spinner<>(100, 1000000, 100, 50);
            lengthSpinner.setEditable(true);

            settingsTitle = new HBox(4, accuracy, slider);
            settings = new HBox(4, lengthLabel, lengthSpinner);

            vbSettings = new VBox(4, settingsTitle,settings);

            generate = new Button("Generate Paper");
            generate.setOnAction(e -> {
                try {
                    handleButtonAction(e);
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
            });
            generate.setAlignment(Pos.BOTTOM_CENTER);

            hbGenerate = new HBox(1, generate);

            status = new Label("Status: Waiting..");
            status.setAlignment(Pos.CENTER);

            hbStatus = new HBox(1,status);

            vbGenerate = new VBox(2, status, generate);
            vbGenerate.setAlignment(Pos.CENTER_RIGHT);

            title = new Label("Choose a topic and generate a paper");
            title.setMinWidth(350);
            title.setAlignment(Pos.CENTER);
            title.setPadding(new Insets(10, 0, 0, 0));
            title.getStyleClass().add("title");

            // Create the root of the scene
            GridPane root = new GridPane();
            root.setPadding(new Insets(10, 50, 10, 50));
            root.setVgap(20);
            root.setHgap(10);
            //root.setGridLinesVisible(true);
            root.addRow(0, title);
            GridPane.setColumnSpan(title, 2);
            GridPane.setHalignment(title, HPos.CENTER);
            root.addRow(1, vbRadio);
            GridPane.setColumnSpan(vbRadio, 2);
            root.addRow(2, vbSettings, vbGenerate);

            // Create and set the settings of the scene.
            Scene scene = new Scene(root, SCENE_WIDTH, SCENE_HEIGHT);
            URL resource = getClass().getResource("application.css");
            scene.getStylesheets().add(resource.toExternalForm());
            primaryStage.setTitle("Scientific Paper Generator");
            primaryStage.setScene(scene);
            primaryStage.setResizable(false);
            primaryStage.show();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
    private void handleButtonAction(ActionEvent e) throws Exception {
        status.setText("Status: Making...");
        accuracyVal = (int) slider.getValue();

        if(generic.isSelected()) {
            topic = Topic.ALL;
        }
        else if(AI.isSelected()) {
            topic = Topic.AI;
        }
        else if(dataS.isSelected()) {
            topic = Topic.DS;
        }
        else if(dataB.isSelected()) {
            topic = Topic.DB;
        }
        else if(SE.isSelected()){
            topic = Topic.SE;
        }
        else if(CV.isSelected()){
            topic = Topic.CV;
        }
        else if(NN.isSelected()){
            topic = Topic.NN;
        }

        generate();
        status.setText("Status: Made");
    }
  //-------End JavaFx Code-------------

    // Main Routine for the paper generation.
    public static void generate() throws Exception {
        Parser parser;
        Lexer lexer;
        ArrayList<Lexeme> xs;
        LexemeList init;
        
        // Each topic has it's own title, abstract, introduction, method, and conclusion text document.
        // Each text document is filled with training data from external papers.
        final String filepath = "thesis/";

        String titles = Preprocessor.process(Main.read(filepath + "/titles.txt"));
        String abstracts = Preprocessor.process(Main.read(filepath + topic.name + "/abstracts.txt"));
        String introductions = Preprocessor.process(Main.read(filepath + topic.name + "/introductions.txt"));
        String methods = Preprocessor.process(Main.read(filepath + topic.name + "/methods.txt"));
        String conclusions = Preprocessor.process(Main.read(filepath + topic.name + "/conclusions.txt"));

        lexer = new Lexer(titles);
        xs = lexer.toLexemes();
        init = chooseInit(xs, 2);
        parser = new Parser(xs);
        int length = 0;
        try {
        	length = Integer.parseInt(lengthSpinner.getEditor().getText());
        	if(length<=50 || length >=50000) { throw new Exception(); } //Preventing papers of too long length.
        }catch(Exception ex) {
        	Alert alert = new Alert(AlertType.INFORMATION, "Invalid length, please try again.", ButtonType.OK);
        	alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
        	alert.show();
        	return;
        }

        String titleString = Postprocessor.process(parser.parse(init, (int) (Math.log(length) + 5))); //For the tile of the paper.
        titleString = titleString.toUpperCase();

        // Generate author name out of a set of random names
        String[] firstName = Main.read(filepath + "firstName.txt").split("\n");
        String[] lastName = Main.read(filepath + "lastName.txt").split("\n");
        String authorString = choose(firstName) + choose(lastName);
        authorString = removeExtraWhitespace(authorString);
        
        // Generate references out of set of random references.
        String[] references = Main.read(filepath + "references.txt").split("\n");
        String referenceString = new String();
        Random random = new Random();
        int refCount = (random.nextInt(5) + 3);
        for(int i = 0; i < refCount; i++) {
        	referenceString += choose(references) + "\n\n";
        }
        

        // Lexer management
        // Create lexer for the abstracts.
        lexer = new Lexer(abstracts);
        xs = lexer.toLexemes(); //Convert the lexer to a set of lexemes.
        init = chooseInit(xs, accuracyVal);
        parser = new Parser(xs); //Create a parser for the result of the generation.

        String abstractString = Postprocessor.process(parser.parse(init, length));
        abstractString = trim(abstractString);

        lexer = new Lexer(introductions);
        xs = lexer.toLexemes();
        init = chooseInit(xs, accuracyVal);
        parser = new Parser(xs);

        String introductionString = Postprocessor.process(parser.parse(init, length * 2));
        introductionString = trim(introductionString);

        lexer = new Lexer(methods);
        xs = lexer.toLexemes();
        init = chooseInit(xs, accuracyVal);
        parser = new Parser(xs);

        // Postprocessor uses the parser to format the generation.
        String methodString = Postprocessor.process(parser.parse(init, length * 5));
        methodString = trim(methodString);

        lexer = new Lexer(conclusions);
        xs = lexer.toLexemes();
        init = chooseInit(xs, accuracyVal);
        parser = new Parser(xs);

        String conclusionString = Postprocessor.process(parser.parse(init, length * 2));
        conclusionString = trim(conclusionString);

        try {
            // Generate paper
            Writer.write(titleString, authorString, abstractString, introductionString, methodString, conclusionString, referenceString);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    // For extra format repairs.
    private static String removeExtraWhitespace(String string) {
        StringBuilder builder = new StringBuilder();

        boolean whitespace = true;
        for(int i = 0; i < string.length(); i++) {
            char ch = string.charAt(i);

            if(Character.isWhitespace(ch)) {
                if(whitespace) continue;
                builder.append(' ');
                whitespace = true;
            } else {
                builder.append(ch);
                whitespace = false;
            }
        }

        return builder.toString();
    }

    private static String trim(String string) {
        String temp = string.substring(string.indexOf('.') + 1, string.lastIndexOf('.') + 1);
        return (temp.length() > 0) ? temp : string;
    }

    // Chose a random position from the lexeme stream to build a list of consecutive lexemes.
    @SuppressWarnings("unused")
	private static LexemeList chooseInit(ArrayList<Lexeme> xs, int length) {
        Random random = new Random();
        ArrayList<Lexeme> temp = new ArrayList<>();

        outer: do {
            temp.clear();
            int index = random.nextInt(xs.size() - length);
            for(int i = 0; i < length; i++) {
                Lexeme x = xs.get(index + i);
                temp.add(x);
            }
        } while(temp.size() < length);

        LexemeList init = new LexemeList();
        for(int i = 0; i < temp.size(); i++) {
            init.add(temp.get(i));
        }
        return init;
    }

    //Reads the files line by line and adds them to a string.
    private static String read(String filename) throws Exception {
        StringBuilder builder = new StringBuilder();
        BufferedReader reader = new BufferedReader(new FileReader(filename));

        while(true) {
            String line = reader.readLine();
            if(line == null) break;
            builder.append(line);
            builder.append('\n');
        }

        reader.close();
        return builder.toString();
    }

    private static String choose(String...strings) {
        Random random = new Random();
        return strings[random.nextInt(strings.length)];
    }

    public static void main(String[] args) {
        launch(args);
    }
}

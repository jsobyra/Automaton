package Controller;

import Model.*;

import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.*;
import javafx.scene.paint.Color;

import java.util.Random;


/**
 * Created by KUBA on 2016-05-23.
 */
public class AutomatonController {
    private static Automaton object;
    private final int canvasSize = 500;
    private final int lineWidth = 2;

    @FXML
    Button nextGeneration;
    @FXML
    Button startButton;
    @FXML
    Button makeBoard;
    @FXML
    Button randomBoard;
    @FXML
    Button createBoard;
    @FXML
    Button stopButton;
    @FXML
    Button clearButton;
    @FXML
    TextField sizeBoard;
    @FXML
    Canvas canvas;
    @FXML
    ChoiceBox algorithm;
    @FXML
    ChoiceBox structure;

    @FXML
    private void initialize(){
        clearBoard();
        initializeAll();

        canvas.setOnMouseClicked(event -> {
            if(!makeBoard.isDisabled()){
                double x = event.getX(), y = event.getY();
                drawSquare(x, y);
            }
        });

       algorithm.getSelectionModel().selectedItemProperty()
               .addListener((ObservableValue observable,
                             Object oldValue, Object newValue) -> {
                   initializeStructureChoice();
               });
    }


    private void initializeStructureChoice(){

        if(algorithm.getValue().toString().equals("Convay")){
            structure.setItems(FXCollections.observableArrayList("White - dead cell", "Black - live cell"));
            structure.getSelectionModel().selectFirst();
        }
        else if(algorithm.getValue().toString().equals("Langton")){
            structure.setItems(FXCollections.observableArrayList("White cell", "Black cell"));
            structure.getSelectionModel().selectFirst();
        }
        else if(algorithm.getValue().toString().equals("Quadlife")){
            structure.setItems(FXCollections.observableArrayList("White - dead cell", "Blue - live cell", "Green - live cell", "Yellow - live cell", "Red - live cell"));
            structure.getSelectionModel().selectFirst();
        }
        else if(algorithm.getValue().toString().equals("Wirewold")){
            structure.setItems(FXCollections.observableArrayList("Black - empty", "Blue - electron head", "Red - electron tail", "Yellow - conductor"));
            structure.getSelectionModel().selectFirst();
        }
    }

    private void initializeAll(){
        startButton.setVisible(false);
        nextGeneration.setVisible(false);
        randomBoard.setVisible(false);
        makeBoard.setVisible(false);
        algorithm.setVisible(false);
        structure.setVisible(false);
        stopButton.setVisible(false);
        clearButton.setVisible(false);
        canvas.setDisable(true);


        structure.setItems(FXCollections.observableArrayList("White - dead cell", "Black - live cell"));
        structure.getSelectionModel().selectFirst();
    }

    @FXML
    private void handleNextGenerationButton(ActionEvent event){
        nextGeneration();
        drawBoard();
    }

    @FXML
    private void handleCreateButton(ActionEvent event){
        prepareBoard();
        choice();
        randomBoard.setVisible(true);
        makeBoard.setVisible(true);
        algorithm.setVisible(true);
        structure.setVisible(true);
        startButton.setVisible(true);
        makeBoard.setDisable(false);
        randomBoard.setDisable(false);
        startButton.setDisable(false);
        sizeBoard.setDisable(true);
    }

    @FXML
    private void handleClearButton(ActionEvent event){
        startButton.setDisable(true);
        nextGeneration.setDisable(true);
        algorithm.setDisable(false);
        structure.setDisable(false);
        createBoard.setDisable(false);
        clearButton.setDisable(true);
        sizeBoard.setDisable(false);
        clearBoard();
    }

    @FXML
    private void handleStopButton(ActionEvent event){
        stopButton.setDisable(true);
        nextGeneration.setDisable(false);
        startButton.setDisable(false);
        nextGeneration.setDisable(true);
        clearButton.setVisible(true);
        clearButton.setDisable(false);



    }

    @FXML
    private void handleStartButton(ActionEvent event){
        makeBoard.setDisable(true);
        randomBoard.setDisable(true);
        nextGeneration.setVisible(true);
        startButton.setDisable(true);
        stopButton.setVisible(true);
        algorithm.setDisable(true);
        structure.setDisable(true);
        nextGeneration.setDisable(false);
        stopButton.setDisable(false);
        clearButton.setDisable(true);
    }

    @FXML
    private void handleRandomButton(ActionEvent event){
        makeBoard.setDisable(true);
        createBoard.setDisable(true);
        algorithm.setDisable(true);
        structure.setDisable(true);
        setBoard();
        drawBoard();
    }

    @FXML
    private void handleMakeBoard(ActionEvent event){
        randomBoard.setDisable(true);
        createBoard.setDisable(true);
        canvas.setDisable(false);

    }

    private void prepareBoard(){
        int amount = Integer.valueOf(sizeBoard.getText().toString());
        int width = canvasSquareWidth();
        clearBoard();
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.setStroke(Color.BROWN);
        gc.setLineWidth(1.0);
        gc.beginPath();
        int totalSize = amount*(width+lineWidth) + lineWidth;
        for(int i = 0; i <= totalSize; i+=width+lineWidth){
            gc.moveTo(i,0);
            gc.lineTo(i, totalSize);
            gc.stroke();

            gc.moveTo(0,i);
            gc.lineTo(totalSize, i);
            gc.stroke();
        }
    }

    private void drawCell(int height, int width, int state){
        GraphicsContext gc = canvas.getGraphicsContext2D();
        if(algorithm.getValue().toString().equals("Convay")){
            if(state == 1)
                gc.setFill(Color.BLACK);
            else if(state == 0)
                gc.setFill(Color.WHITE);
        }
        else if(algorithm.getValue().toString().equals("Quadlife")){
            if(state == 0)
                gc.setFill(Color.WHITE);
            else if(state == 1)
                gc.setFill(Color.BLUE);
            else if(state == 2)
                gc.setFill(Color.RED);
            else if(state == 3)
                gc.setFill(Color.GREEN);
            else if(state == 4)
                gc.setFill(Color.YELLOW);
        }
        else if(algorithm.getValue().toString().equals("Langton")){
            if(state == 1)
                gc.setFill(Color.BLACK);
            else if(state == 0)
                gc.setFill(Color.WHITE);
            else if(state == 2)
                gc.setFill(Color.YELLOW);
        }
        else if(algorithm.getValue().toString().equals("Wireworld")){
            if(state == 0)
                gc.setFill(Color.BLACK);
            else if(state == 1)
                gc.setFill(Color.BLUE);
            else if(state == 2)
                gc.setFill(Color.RED);
            else if(state == 3)
                gc.setFill(Color.YELLOW);
        }
        else if(state == -1){
            gc.setFill(Color.WHITE);
        }

        if(height <= object.getHeight() - 1 && width <= object.getWidth() - 1)
            gc.fillRect(lineWidth + (lineWidth+canvasSquareWidth())*width, lineWidth + (lineWidth+canvasSquareWidth())*height, canvasSquareWidth(), canvasSquareWidth());
    }

    private int canvasSquareWidth(){
        return canvasSize/Integer.valueOf(sizeBoard.getText().toString());
    }

    private void choice(){
        if(algorithm.getValue().toString().equals("Convay")){
            object = new Convay(Integer.valueOf(sizeBoard.getText().toString()), Integer.valueOf(sizeBoard.getText().toString()));
        }
        else if(algorithm.getValue().toString().equals("Quadlife")){
            object = new Quadlife(Integer.valueOf(sizeBoard.getText().toString()), Integer.valueOf(sizeBoard.getText().toString()));
        }
        else if(algorithm.getValue().toString().equals("Langton")){
            object = new Langton(Integer.valueOf(sizeBoard.getText().toString()), Integer.valueOf(sizeBoard.getText().toString()));
        }
        else if(algorithm.getValue().toString().equals("Wireworld")){
            object = new Wireworld(Integer.valueOf(sizeBoard.getText().toString()), Integer.valueOf(sizeBoard.getText().toString()));
        }

    }

    private void nextGeneration(){
        if(algorithm.getValue().toString().equals("Convay")){
            ((Convay) object).nextGeneration();
        }
        else if(algorithm.getValue().toString().equals("Quadlife")){
            ((Quadlife) object).nextGeneration();
        }
        else if(algorithm.getValue().toString().equals("Langton")){
            ((Langton) object).nextGeneration();
        }
        else if(algorithm.getValue().toString().equals("Wireworld")){
            ((Wireworld) object).nextGeneration();
        }
    }

    private void drawBoard(){
        if(algorithm.getValue().toString().equals("Convay") || algorithm.getValue().toString().equals("Quadlife") || algorithm.getValue().toString().equals("Wireworld")){
            for(int i = 0; i <  object.getHeight(); i++){
                for(int j = 0; j < object.getWidth(); j++){
                    drawCell(i, j, object.getBoard()[i][j]);
                }
            }
        }
        else if(algorithm.getValue().toString().equals("Langton")){
            for(int i = 0; i <  object.getHeight(); i++){
                for(int j = 0; j < object.getWidth(); j++){
                    drawCell(i, j, object.getBoard()[i][j]);
                }
            }
            drawCell(((Langton) object).getAnt().getY(), ((Langton) object).getAnt().getX(), 2);
        }
    }

    private int[][] makeBoard(int[][] sample){
        int length = sample[0].length;
        int[][] temporaryBoard = new int[object.getHeight()][object.getWidth()];


        for(int i = 0; i < object.getHeight(); i++){
            for(int j = 0; j < object.getWidth(); j++){
                temporaryBoard[i][j] = sample[i%length][j%length];
            }
        }

        return temporaryBoard;
    }

    private void clearBoard(){
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.setFill(Color.WHITE);
        gc.fillRect(0, 0, 900, 600);
    }

    private int[][] randomBoard(int height, int width, int numberStates){
        Random random = new Random();
        int randomBoard[][] = new int[height][width];
        for(int i = 0; i < height; i++){
            for(int j = 0; j < width; j++){
                randomBoard[i][j] = random.nextInt(numberStates);
            }
        }

        return randomBoard;
    }

    private void setBoard(){
        if(algorithm.getValue().toString().equals("Convay")){
            object.setBoard(makeBoard(randomBoard(object.getHeight(), object.getWidth(), 2)));
        }
        else if(algorithm.getValue().toString().equals("Quadlife")){
            object.setBoard(makeBoard(randomBoard(object.getHeight(), object.getWidth(), 5)));
        }
        else if(algorithm.getValue().toString().equals("Langton")){
            object.setBoard(makeBoard(randomBoard(object.getHeight(), object.getWidth(), 2)));
        }
        else if(algorithm.getValue().toString().equals("Wireworld")){
            object.setBoard(makeBoard(randomBoard(object.getHeight(), object.getWidth(), 4)));
        }

    }

    private void drawSquare(double w, double h){
        int height = lineWidth + canvasSquareWidth();
        int width = lineWidth + canvasSquareWidth();
        int x = width, y = height;
        int counterX = 0;
        int counterY = 0;

        for(int i = height; ; i += lineWidth + canvasSquareWidth()){
            counterY++;
            if(i >= h && h > i - canvasSquareWidth()){
                y = i - canvasSquareWidth();
                break;
            }
        }

        for(int i = width; ; i += lineWidth + canvasSquareWidth()){
            counterX++;
            if(i >= w && w > i - canvasSquareWidth()){
                x = i - canvasSquareWidth();
                break;
            }
        }

        initializeCell(y, x, counterY - 1, counterX - 1);

    }

    private void initializeCell(int height, int width, int y, int x){
        GraphicsContext gc = canvas.getGraphicsContext2D();
        if(algorithm.getValue().toString().equals("Convay")){
            if(structure.getValue().toString().equals("White - dead cell")){
                gc.setFill(Color.WHITE);
                object.setCell(x, y, 0);
            }
            else if(structure.getValue().toString().equals("Black - live cell")) {
                gc.setFill(Color.BLACK);
                object.setCell(x, y, 1);
            }
        }
        else if(algorithm.getValue().toString().equals("Langton")){
            if(structure.getValue().toString().equals("White cell")){
                gc.setFill(Color.WHITE);
                object.setCell(x, y, 0);
            }
            else if(structure.getValue().toString().equals("Black cell")){
                gc.setFill(Color.BLACK);
                object.setCell(x, y, 1);
            }
        }
        else if(algorithm.getValue().toString().equals("Quadlife")){
            if(structure.getValue().toString().equals("White - dead cell")){
                gc.setFill(Color.WHITE);
                object.setCell(x, y, 0);
            }
            else if(structure.getValue().toString().equals("Blue - live cell")){
                gc.setFill(Color.BLUE);
                object.setCell(x, y, 1);
            }

            else if(structure.getValue().toString().equals("Green - live cell")){
                gc.setFill(Color.GREEN);
                object.setCell(x, y, 2);
            }
            else if(structure.getValue().toString().equals("Yellow - live cell")){
                gc.setFill(Color.YELLOW);
                object.setCell(x, y, 3);
            }
            else if(structure.getValue().toString().equals("Red - live cell")){
                gc.setFill(Color.RED);
                object.setCell(x, y, 4);
            }
        }
        else if(algorithm.getValue().toString().equals("Wireworld")){
            if(structure.getValue().toString().equals("Black - empty")){
                gc.setFill(Color.BLACK);
                object.setCell(x, y, 0);
            }
            else if(structure.getValue().toString().equals("Blue - electron head")){
                gc.setFill(Color.BLUE);
                object.setCell(x, y, 1);
            }
            else if(structure.getValue().toString().equals("Red - electron tail")){
                gc.setFill(Color.RED);
                object.setCell(x, y, 2);
            }
            else if(structure.getValue().toString().equals("Yellow - conductor")){
                gc.setFill(Color.YELLOW);
                object.setCell(x, y, 3);
            }

        }

        gc.fillRect(width, height, canvasSquareWidth(), canvasSquareWidth());
    }

}
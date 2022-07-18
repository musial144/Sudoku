package pl.kompo;

import static java.lang.Integer.parseInt;

import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import pl.kompo.exceptions.NoDataException;
import pl.kompo.exceptions.NoFileException;

public class Controller {

    @FXML
    private Button polishButton;
    @FXML
    private Button englishButton;
    @FXML
    private Button okButton;
    @FXML
    private Button startButton;
    @FXML
    private Button saveButton;
    @FXML
    private Button loadButton;
    @FXML
    private Button easyButton;
    @FXML
    private Button normalButton;
    @FXML
    private Button hardButton;
    @FXML
    private GridPane grid = new GridPane();



    private ResourceBundle bundle = ResourceBundle.getBundle("GameLanguage", new Locale("pl"));

    private Stage primaryStage = new Stage();

    private static Level level;

    private static String language = "en";

    private TextField[][] textField = new TextField[9][9];

    private static final Logger logger = LogManager.getLogger(Controller.class.getName());

    public void setLevel(Level level) {
        this.level = level;
    }

    public Level getLevel() {
        return level;
    }

    public static void setLanguage(String language) {
        Controller.language = language;
    }

    public static String getLanguage() {
        return language;
    }

    @FXML
    public void initialize(Stage primaryStage) throws NoFileException {
        try {
            this.primaryStage = primaryStage;
            ResourceBundle bundle = ResourceBundle.getBundle("MenuLanguage", new Locale("en"));
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Menu.fxml"), bundle);
            Parent root = loader.load();
            Scene scene = new Scene(root);
            this.primaryStage.setScene(scene);
            this.primaryStage.show();
        } catch (IOException | IllegalStateException e) {
            throw new NoFileException("There is no file with name /Menu.fxml",e);
        }
    }

    public void setEasy(ActionEvent actionEvent) throws NoFileException {
        try {
            logger.info("Loading easy mode");
            setLevel(Level.easy);
            if (getLanguage().equals("pl")) {
                bundle = ResourceBundle.getBundle("GameLanguage", new Locale("pl"));
            }
            if (getLanguage().equals("en")) {
                bundle = ResourceBundle.getBundle("GameLanguage", new Locale("en"));
            }
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Game.fxml"), bundle);
            Parent root = loader.load();
            Scene scene = new Scene(root);
            this.primaryStage.setScene(scene);
            this.primaryStage.show();
        } catch (IOException | IllegalStateException e) {
            throw new NoFileException("There is no file with name /Game.fxml",e);
        }
    }

    public void setNormal(ActionEvent actionEvent) throws NoFileException {
        try {
            logger.info("Loading normal mode");
            setLevel(Level.normal);
            if (getLanguage().equals("pl")) {
                bundle = ResourceBundle.getBundle("GameLanguage", new Locale("pl"));
            }
            if (getLanguage().equals("en")) {
                bundle = ResourceBundle.getBundle("GameLanguage", new Locale("en"));
            }
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Game.fxml"), bundle);
            Parent root = loader.load();
            Scene scene = new Scene(root);
            this.primaryStage.setScene(scene);
            this.primaryStage.show();
        } catch (IOException | IllegalStateException e) {
            throw new NoFileException("There is no file with name /Game.fxml",e);
        }
    }

    public void setHard(ActionEvent actionEvent) throws NoFileException {
        try {
            logger.info("Loading hard mode");
            setLevel(Level.hard);
            if (getLanguage().equals("pl")) {
                bundle = ResourceBundle.getBundle("GameLanguage", new Locale("pl"));
            }
            if (getLanguage().equals("en")) {
                bundle = ResourceBundle.getBundle("GameLanguage", new Locale("en"));
            }
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Game.fxml"), bundle);
            Parent root = loader.load();
            Scene scene = new Scene(root);
            this.primaryStage.setScene(scene);
            this.primaryStage.show();
        } catch (IOException | IllegalStateException e) {
            throw new NoFileException("There is no file with name /Game.fxml", e);
        }
    }

    public void startGame(ActionEvent actionEvent) {
        try {
            logger.info("OpenApplication");
            SudokuSolver sudokuSolver = new BacktrackingSudokuSolver();
            SudokuBoard sudokuBoard = new SudokuBoard(sudokuSolver);
            sudokuBoard.solveGame();
            SudokuField[][] gameBoard = level.deleteFields(sudokuBoard);
            for (int i = 0; i < 9; i++) {
                for (int j = 0; j < 9; j++) {

                    int startIndex = gameBoard[i][j].toString().length() - 2;
                    int endIndex = gameBoard[i][j].toString().length() - 1;
                    String substring = gameBoard[i][j].toString().substring(startIndex, endIndex);
                    textField[i][j] = new TextField(substring);
                    textField[i][j].setEditable(false);
                    if (textField[i][j].getText().equals("0")) {
                        textField[i][j].setText("");
                        textField[i][j].setEditable(true);
                    }
                    textField[i][j].setPrefHeight(55);
                    textField[i][j].setPrefWidth(55);
                    textField[i][j].setAlignment(Pos.CENTER);
                    grid.add(textField[i][j], i, j);
                }
            }
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
    }

    public void saveGame(ActionEvent actionEvent) throws NoFileException {
        SudokuBoard board = new SudokuBoard(new BacktrackingSudokuSolver());
        TextField text = new TextField();
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (textField[i][j].getText().equals("")) {
                    text.setText("0");
                } else {
                    text.setText(textField[i][j].getText());
                }
                board.set(i,j,parseInt(text.getText()));
            }
        }
        try {
            Dao<SudokuBoard> file = new SudokuBoardDaoFactory().getJdbcDao("zapis");
            file.write(board);
            logger.info("GameSaved");
        } catch (Throwable e) {
            throw new NoFileException("There is no file with name /zapis.txt ", e);
        }
    }

    public void loadGame(ActionEvent actionEvent) throws NoFileException {
        try {
            SudokuBoard board;
            Dao<SudokuBoard> file = new SudokuBoardDaoFactory().getFileDao("zapis");
            try {
                board = file.read();
                if (board == null) {
                    throw new NoDataException();
                }
            } catch (Throwable e) {
                throw new NoDataException("Wrong data in file");
            }

            for (int i = 0; i < 9; i++) {
                for (int j = 0; j < 9; j++) {
                    textField[i][j].setText(Integer.toString(board.get(i,j)));
                    if (textField[i][j].getText().equals("0")) {
                        textField[i][j].setText("");
                        textField[i][j].setEditable(true);
                    }
                }
            }
            logger.info("GameLoaded");
        } catch (Throwable e) {
            throw new NoFileException("There is no file with name /zapis.txt ", e);
        }
    }

    public void polishLanguage(ActionEvent actionEvent) throws NoFileException {
        try {
            logger.info("SetPolishLanguage");
            setLanguage("pl");
            Stage stage = (Stage) polishButton.getScene().getWindow();
            stage.close();
            primaryStage.close();
            bundle = ResourceBundle.getBundle("MenuLanguage", new Locale("pl"));
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Menu.fxml"), bundle);
            Parent root = loader.load();
            Scene scene = new Scene(root);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException e) {
            throw new NoFileException("There is no file with name /Menu.fxml",e);
        }
    }

    public void englishLanguage(ActionEvent actionEvent) throws NoFileException {
        try {
            logger.info("SetEnglishLanguage");
            setLanguage("en");
            Stage stage = (Stage) englishButton.getScene().getWindow();
            stage.close();
            bundle = ResourceBundle.getBundle("MenuLanguage", new Locale("en"));
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Menu.fxml"), bundle);
            Parent root = loader.load();
            Scene scene = new Scene(root);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException e) {
            throw new NoFileException("There is no file with name /Menu.fxml",e);
        }
    }
}
package pl.kompo;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import pl.kompo.exceptions.ClassNotFoundFileDaoException;
import pl.kompo.exceptions.NoFileFileDaoException;

public class FileSudokuBoardDao implements Dao<SudokuBoard> {

    private String fileName;


    public FileSudokuBoardDao(String fileName) {
        this.fileName = fileName + ".txt";
    }

    @Override
    public SudokuBoard read() throws NoFileFileDaoException, ClassNotFoundFileDaoException {
        SudokuBoard sudokuBoard = null;

        try (FileInputStream fileReader = new FileInputStream(fileName)) {
            ObjectInputStream reader = new ObjectInputStream(fileReader);
            sudokuBoard = (SudokuBoard) reader.readObject();

        } catch (IOException e) {
            throw new NoFileFileDaoException("There is no file with name " + fileName,e);
        } catch (ClassNotFoundException e) {
            throw new ClassNotFoundFileDaoException("Class SudokuBoard.class not found", e);
        }
        return sudokuBoard;
    }

    @Override
    public void write(SudokuBoard sudokuBoard) throws NoFileFileDaoException {
        try (FileOutputStream fileWriter = new FileOutputStream(fileName)) {
            ObjectOutputStream writer = new ObjectOutputStream(fileWriter);
            writer.writeObject(sudokuBoard);

        } catch (IOException e) {
            throw new NoFileFileDaoException("Failed execute file " + fileName,e);
        }
    }

}

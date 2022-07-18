package pl.kompo;

import pl.kompo.exceptions.ClassNotFoundFileDaoException;
import pl.kompo.exceptions.ClassNotFoundJdbcDaoException;
import pl.kompo.exceptions.NoFileFileDaoException;
import pl.kompo.exceptions.NoFileJdbcDaoException;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JdbcSudokuBoardDao implements Dao<SudokuBoard> {
    private String fileName;
    private EntityManagerFactory entityManagerFactory =
            Persistence.createEntityManagerFactory("Sudokus");
    private EntityManager entityManager =
            entityManagerFactory.createEntityManager();
    private SudokuBoardRepository boardRepository = new SudokuBoardRepository(entityManager);

    public JdbcSudokuBoardDao(String fileName) {
        this.fileName = fileName;
    }


    @Override
    public SudokuBoard read() throws NoFileJdbcDaoException, ClassNotFoundJdbcDaoException {
        SudokuBoard sudokuBoard = null;

        try (FileInputStream fileReader = new FileInputStream(fileName)) {
            ObjectInputStream reader = new ObjectInputStream(fileReader);
            boardRepository = (SudokuBoardRepository) reader.readObject();
            sudokuBoard = boardRepository.findById(0);
        } catch (IOException e) {
            throw new NoFileJdbcDaoException("There is no file with name " + fileName,e);
        } catch (ClassNotFoundException e) {
            throw new ClassNotFoundJdbcDaoException("Class SudokuBoard.class not found", e);
        }
        return sudokuBoard;
    }

    @Override
    public void write(SudokuBoard sudokuBoard) throws NoFileJdbcDaoException {
        boardRepository.save(sudokuBoard);
        try (FileOutputStream fileWriter = new FileOutputStream(fileName)) {
            ObjectOutputStream writer = new ObjectOutputStream(fileWriter);
            writer.writeObject(boardRepository);
        } catch (IOException e) {
            throw new NoFileJdbcDaoException("Failed execute file " + fileName,e);
        }
    }

}

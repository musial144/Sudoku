package pl.kompo;


import org.junit.jupiter.api.Test;
import pl.kompo.exceptions.NoFileFileDaoException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
//import static org.testng.Assert.assertThrows;


public class FileSudokuBoardDaoTest {

    @Test
    public void readFileNotFoundTest() {
        Dao<SudokuBoard> fileSudokuBoardDao= new SudokuBoardDaoFactory().getFileDao("L:\\przyklad");
        assertThrows(NoFileFileDaoException.class, () -> fileSudokuBoardDao.read());
    }

    @Test
    public void writeRuntimeExceptionTest() {
        SudokuBoard board = new SudokuBoard(new BacktrackingSudokuSolver());
        Dao<SudokuBoard> fileSudokuBoardDao = new SudokuBoardDaoFactory().getFileDao("L:\\przyklad");
        assertThrows(NoFileFileDaoException.class, () -> fileSudokuBoardDao.write(board));
    }

    @Test
        public void writeReadTest() throws Throwable {
            SudokuBoard board_1 = new SudokuBoard(new BacktrackingSudokuSolver());
            SudokuBoard board_2;
            board_1.solveGame();
            Dao<SudokuBoard> file = new SudokuBoardDaoFactory().getFileDao("dzialaj");

            file.write(board_1);
            board_2 = file.read();
            board_1.toString();
            assertEquals(board_1.hashCode(), board_2.hashCode());

    }
    }



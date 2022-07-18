package pl.kompo;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class SudokuBoardDaoFactoryTest {
    SudokuBoardDaoFactory factory = new SudokuBoardDaoFactory();

    @Test
    void getFileDao(){
        assertTrue(factory.getFileDao("przyklad.pl")!=null);
    }
}

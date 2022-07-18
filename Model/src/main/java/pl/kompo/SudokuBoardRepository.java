package pl.kompo;
import javax.persistence.EntityManager;
import java.util.List;

public class SudokuBoardRepository {
    private EntityManager entityManager;

    public SudokuBoardRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public SudokuBoard findById(Integer id) {
        SudokuBoard sudokuBoard = entityManager.find(SudokuBoard.class, id);
        return sudokuBoard;
    }
//    public List<SudokuBoard> findAll() {
//    }

    public SudokuBoard save(SudokuBoard sudokuBoard) {
            entityManager.getTransaction().begin();
            entityManager.persist(sudokuBoard);
            entityManager.getTransaction().commit();;
        return sudokuBoard;
    }
}

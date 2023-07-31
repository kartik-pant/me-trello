package trello.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;

import trello.models.Board;
import trello.models.Column;
import trello.models.User;

@TestInstance(Lifecycle.PER_CLASS)
public class BoardRepositoryTest {
    BoardRepository repo = new BoardRepositoryImpl();
    User boardOwner;

    @BeforeAll
    void createNewUser() {
        UserRepository userRepo = new UserRepositoryImpl();
        User user = new User("username", "email", "password");
        boardOwner = userRepo.save(user);
    }

    @Test
    void addNewBoard() {
        Board board1 = new Board("Board1", "Description", false, boardOwner);
        Board board2 = new Board("Board2", "Description", false, boardOwner);

        Board createdBoard1 = repo.save(board1);
        Board createdBoard2 = repo.save(board2);

        assertNotNull(createdBoard1);
        assertNotNull(createdBoard2);
        assertEquals(1, createdBoard2.getId() - createdBoard1.getId());
    }

    @Test
    void addNewColumn() {
        Board board = new Board("Board", "Description", false, boardOwner);
        Board createdBoard = repo.save(board);

        Column createdColumn = repo.addColumn(createdBoard.getId(), new Column("Column1"));
        assertNotNull(createdColumn);
        assertEquals("Column1", createdColumn.getTitle());

        Column fetchedColumn = repo.findColumnById(createdColumn.getId()).get();
        assertTrue(createdColumn.equals(fetchedColumn));
    }

    @Test
    void renameColumn() {
        Board board = new Board("Board", "Description", false, boardOwner);
        Board createdBoard = repo.save(board);

        Column createdColumn = repo.addColumn(createdBoard.getId(), new Column("Column1"));
        assertEquals("Column1", createdColumn.getTitle());

        String newTitle = "New Title";
        repo.renameColumn(createdColumn.getId(), newTitle);

        Column fetchedColumn = repo.findColumnById(createdColumn.getId()).get();
        assertEquals(newTitle, fetchedColumn.getTitle());
    }

}

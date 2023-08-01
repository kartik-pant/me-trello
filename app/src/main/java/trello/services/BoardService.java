package trello.services;

import java.util.Optional;

import trello.models.Board;
import trello.models.BoardVisbility;
import trello.models.Column;
import trello.models.User;
import trello.repository.BoardRepository;
import trello.repository.UserRepository;
import trello.utils.AuthHelpers;

public class BoardService {
    BoardRepository boardRepo;
    UserRepository userRepo;
    AuthHelpers authHelpers = new AuthHelpers();

    public BoardService(BoardRepository boardRepo, UserRepository userRepo) {
        this.boardRepo = boardRepo;
        this.userRepo = userRepo;
    }

    public Board createBoard(Long ownerId, String title, String description, BoardVisbility visbility) {
        Optional<User> user = userRepo.findById(ownerId);
        if (!user.isPresent())
            throw new RuntimeException("Requeted user does not exist");

        Board board = new Board(title, description, visbility, user.get());
        return boardRepo.save(board);
    }

    Board getBoard(Long userId, Long boardId) {
        if (!authHelpers.isUserBoardMember(boardId, userId))
            throw new RuntimeException("Non board members cannot view board content");
        Optional<Board> board = boardRepo.findBoardById(boardId);
        return board.get();
    }

    /**
     * Only those users who are members in a board can add new
     * columns to the groups.
     * 
     * @return newly created column.
     */
    public Column createColumn(Long createdBy, Long boardId, String title) {
        if (!authHelpers.isUserBoardMember(boardId, createdBy))
            throw new RuntimeException("Non board members cannot add new columns.");
        return boardRepo.addColumn(boardId, new Column(title));
    }

    public void moveColumn(Long userId, Long boardId, Long columnId, int newPosition) {
        if (!authHelpers.isUserBoardMember(boardId, userId))
            throw new RuntimeException("Non board members cannot add move columns.");
        Optional<Board> board = boardRepo.findBoardById(boardId);
        if (!board.isPresent())
            throw new RuntimeException("Requested board does not exist");
        board.get().moveColumn(columnId, newPosition);
    }

    public void renameColumn(Long userId, Long boardId, Long columnId, String newTitle) {
        if (!authHelpers.isUserBoardMember(boardId, userId))
            throw new RuntimeException("Non board members cannot add move columns.");
        Optional<Column> column = boardRepo.findColumnById(columnId);
        if (!column.isPresent())
            throw new RuntimeException("Requested column does not exist");
        column.get().setTitle(newTitle);
    }

    public void deleteColumn(Long userId, Long boardId, Long columnId) {
        if (!authHelpers.isUserBoardMember(boardId, userId))
            throw new RuntimeException("Non board members cannot add move columns.");
        boardRepo.deleteColumn(boardId, columnId);
    }

    public void addMembers(Long boardId, User user) {
        Optional<Board> board = boardRepo.findBoardById(boardId);
        if (!board.isPresent())
            throw new RuntimeException("Requested board does not exist");
        board.get().addNewMember(user);
    }

}

package trello.utils;

import java.util.Optional;

import trello.models.Board;
import trello.models.User;
import trello.repository.BoardRepository;
import trello.repository.BoardRepositoryImpl;
import trello.repository.UserRepository;
import trello.repository.UserRepositoryImpl;

public class AuthHelpers {
    BoardRepository boardRepo = new BoardRepositoryImpl();
    UserRepository userRepo = new UserRepositoryImpl();

    public boolean isUserBoardMember(Long boardId, Long userId) {
        Optional<Board> board = boardRepo.findBoardById(boardId);
        Optional<User> user = userRepo.findById(userId);
        if (!board.isPresent() || !user.isPresent())
            return false;
        return board.get().getMembers().contains(user.get());
    }

}

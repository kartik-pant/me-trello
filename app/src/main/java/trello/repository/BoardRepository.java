package trello.repository;

import java.util.List;
import java.util.Optional;

import trello.models.Board;
import trello.models.Column;

public interface BoardRepository {
    Board save(Board board);

    Optional<Board> findBoardById(long boardId);

    Column addColumn(Long boardId, Column column);

    List<Column> findAllColumns(Long boardId);

    Optional<Column> findColumnById(Long columnId);

    void renameColumn(Long columnId, String newTitle);

    void moveColumn(Long columnId, int newPosition);

    void deleteColumn(Long columnId);
}
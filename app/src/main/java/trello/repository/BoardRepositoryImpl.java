package trello.repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import trello.models.Board;
import trello.models.Column;

public class BoardRepositoryImpl implements BoardRepository {
    private static Map<Long, Board> boards = new HashMap<>();
    private static Map<Long, Column> columns = new HashMap<>();
    private static Long autoIncrementBoardId = 1L;
    private static Long autoIncrementColumnId = 1L;

    @Override
    public Board save(Board board) {
        Board createdBoard = new Board(autoIncrementBoardId, board);
        boards.put(autoIncrementBoardId, createdBoard);
        autoIncrementBoardId++;
        return createdBoard;
    }

    @Override
    public Optional<Board> findBoardById(long boardId) {
        return Optional.ofNullable(boards.get(boardId));
    }

    @Override
    public Column addColumn(Long boardId, Column column) {
        Column createdColumn = new Column(autoIncrementColumnId, column);

        // adding the new column to the board
        Board board = findBoardById(boardId).get();
        board.addNewColumn(createdColumn);

        // creating new column and incrementing column id
        columns.put(autoIncrementColumnId, createdColumn);
        autoIncrementColumnId++;
        return createdColumn;
    }

    @Override
    public void deleteColumn(Long columnId) {
        Column column = findColumnById(columnId).get();
        // boards.get(column.getBoardId()).removeColumn(column);
        columns.remove(column.getId());
    }

    @Override
    public List<Column> findAllColumns(Long boardId) {
        List<Column> columnsList = new ArrayList<>(columns.values());
        return columnsList;
    }

    @Override
    public Optional<Column> findColumnById(Long columnId) {
        return Optional.ofNullable(columns.get(columnId));
    }

    @Override
    public void moveColumn(Long columnId, int newPosition) {
        Column column = columns.get(columnId);
        // boards.get(column.getBoardId()).moveColumn(column, newPosition);
    }

    @Override
    public void renameColumn(Long columnId, String newTitle) {
        columns.get(columnId).setTitle(newTitle);
    }

}

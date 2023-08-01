package trello.repository;

import java.util.List;
import java.util.Optional;

import trello.models.Label;

public interface LabelRepository {
    Label save(Label label);

    List<Label> findAllLabels();

    Optional<Label> findById(Long labelId);
}

package trello.repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import trello.models.Label;

public class LabelRepositoryImpl implements LabelRepository {
    private static Map<Long, Label> labels = new HashMap<>();
    private static Long autoIncrementLabelId = 1L;

    @Override
    public List<Label> findAllLabels() {
        return new ArrayList<>(labels.values());
    }

    @Override
    public Optional<Label> findById(Long labelId) {
        return Optional.ofNullable(labels.get(labelId));
    }

    @Override
    public Label save(Label label) {
        Label createdLabel = new Label(autoIncrementLabelId, label);
        labels.put(autoIncrementLabelId, createdLabel);
        autoIncrementLabelId++;
        return createdLabel;
    }

}

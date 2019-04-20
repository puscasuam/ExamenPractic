package sample.Service;

import sample.Domain.Entity;
import sample.Repository.IRepository;

import java.util.List;

public class DeleteOperation<T extends Entity> extends UndoRedoOperation {

    private T deletedEntity;

    DeleteOperation(IRepository<T> repository, T deletedEntity) {
        super(repository);
        this.deletedEntity = deletedEntity;
    }

    @Override
    public void doUndo() {
        repository.upsert(deletedEntity);
    }

    @Override
    public void doRedo() {
        repository.remove(deletedEntity.getId());
    }


}
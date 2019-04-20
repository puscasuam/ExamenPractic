package sample.Service;

import sample.Domain.Car;
import sample.Repository.IRepository;
import java.util.List;
import java.util.Stack;

public class CarService {
    private IRepository<Car> repository;
    private Stack<UndoRedoOperation<Car>> undoableOperations = new Stack<>();
    private Stack<UndoRedoOperation<Car>> redoableOperations = new Stack<>();

    /**
     * Constructs a service.
     *
     * @param repository the backing repository.
     */
    public CarService(IRepository<Car> repository) {
        this.repository = repository;
    }


    /**
     * Adds an car with the given fields.
     *
     * @param id    - the id - must be unique.
     * @param model - the model
     * @param basekm - the base km
     * @param rentPrice    - the rental price / day
     */
    public void add(int id, String model, int basekm, double rentPrice) {

        Car car = new Car(id, model, basekm, rentPrice);
        repository.upsert(car);
        undoableOperations.add(new AddOperation<>(repository, car));
        redoableOperations.clear();
    }

//

    /**
     * Gets a list of all cars.
     *
     * @return a list of all cars.
     */
    public List<Car> getAll() {
        return repository.getAll();
    }



    public void undo() {
        if (!undoableOperations.empty()) {
            UndoRedoOperation<Car> lastOperation = undoableOperations.pop();
            lastOperation.doUndo();
            redoableOperations.add(lastOperation);
        }
    }

    public void redo() {
        if (!redoableOperations.empty()) {
            UndoRedoOperation<Car> lastOperation = redoableOperations.pop();
            lastOperation.doRedo();
            undoableOperations.add(lastOperation);
        }
    }




}


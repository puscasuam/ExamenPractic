package sample.Domain;


public class CarValidator implements IValidator<Car> {

    public void validate(Car car) throws CarIdException {
        if (car.getId() <= 0) {
            throw new CarIdException("This is an invalid id number");
        }
    }
}



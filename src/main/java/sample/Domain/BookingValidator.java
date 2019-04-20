package sample.Domain;

public class BookingValidator implements IValidator<Booking>{

    public void validate(Booking booking){
        if (booking.getId() <= 0) {
            throw new RuntimeException("This is an invalid id number");
        }
    }
}

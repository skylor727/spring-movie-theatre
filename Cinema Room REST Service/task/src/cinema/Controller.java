package cinema;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;

@RestController
public class Controller {
    Theatre theatre = new Theatre(9, 9);

    @GetMapping("/seats")
    public Theatre seats() {
        return theatre;
    }

    //iterate array and if row & column = what im searching for return i
    //IF ROW * COLUMN FROM POST BODY IS > THEATRE.SEATLIST SIZE RETURN ERROR AS ITS OUT OF BOUNDS
    @PostMapping("/purchase")
    public Seats purchase(@RequestBody Seats seat) {
        ArrayList<Seats> seats = theatre.getSeatList();
        int row = seat.getRow();
        int column = seat.getColumn();
        int seatIdx = 0;
        boolean seatFound = false;
        //Iterating through the arraylist and searching for the seat sent in the post request and returning the index
        for (int i = 0; i < seats.size(); i++) {
            Seats seatToCheck = seats.get(i);
            int seatToCheckRow = seatToCheck.getRow();
            int seatToCheckColumn = seatToCheck.getColumn();
            if (seatToCheckRow * seatToCheckColumn > seats.size()) {
                throw new OutOfBoundsException("The number of a row or column is out of bounds!");
            }
            if (row == seatToCheckRow && column == seatToCheckColumn) {
                seatFound = true;
                seatIdx = i;
                break;
            }
        }
        if (seatFound) {
            seats.remove(seatIdx);
            return seat;
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "The ticket has been already purchased!");
        }
    }
}


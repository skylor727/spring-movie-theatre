package cinema;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

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
    public ResponseEntity purchase(@RequestBody Seats seat) {
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
                Error error = new Error("The number of a row or column is out of bounds!");
                return new ResponseEntity<Error>(error, HttpStatus.BAD_REQUEST);
            }
            if (row == seatToCheckRow && column == seatToCheckColumn) {
                seatFound = true;
                seatIdx = i;
                break;
            }
        }
        if (seatFound) {
            seats.remove(seatIdx);
            return new ResponseEntity<Seats>(seat, HttpStatus.OK);
        } else {
            Error error = new Error("The ticket has already been purchased!");
            return new ResponseEntity<Error>(error, HttpStatus.BAD_REQUEST);
        }
    }
}


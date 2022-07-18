package cinema;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;

public class Theatre {
    @JsonProperty("total_rows")
    private int rows;
    @JsonProperty("total_columns")
    private int columns;
    @JsonProperty("available_seats")
    private ArrayList<Seats> seatList = new ArrayList<>();

    public int getRows() {
        return rows;
    }

    public void setRows(int rows) {
        this.rows = rows;
    }

    public int getColumns() {
        return columns;
    }

    public void setColumns(int columns) {
        this.columns = columns;
    }

    public ArrayList<Seats> getSeatList() {
        return seatList;
    }


    Theatre(int rows, int columns) {
        this.rows = rows;
        this.columns = columns;
        initSeats();

    }

    @Override
    public String toString() {
        String string = " rows: " + this.rows +
                "\n columns: " + columns +
                "\n seats: \n\n";
        for (int i = 0; i < seatList.size(); i++) {
            string += " " + seatList.get(i).toString();
        }
        return string;
    }

    //Initialize the base state of all seats with rows and columns
    public void initSeats() {
        for (int i = 1; i <= this.rows; i++) {
            for (int j = 1; j <= this.columns; j++) {
                Seats seat = new Seats(i, j);
                this.seatList.add(seat);
            }
        }
    }
}
package cinema;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Seats {
    @JsonProperty("row")
    private int row;
    @JsonProperty("column")
    private int column;
    private int price;

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    Seats(@JsonProperty("row") int row, @JsonProperty("column") int column) {
        this.row = row;
        this.column = column;
        this.price = row <= 4 ? 8 : 10;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getColumn() {
        return column;
    }

    public void setColumn(int column) {
        this.column = column;
    }

    @Override
    public String toString() {
        return "row: " + this.row + "\n" +
                "column: " + this.column + "\n\n";
    }
}

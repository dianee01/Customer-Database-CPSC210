package model;

public class Date {
    private int year;
    private boolean update;

    //REQUIRES: y to be larger than 0
    //MODIFIES: this
    //EFFECTS: set year to y, and update to false
    public Date(int y) {
        if (y > 0) {
            this.year = y;
            this.update = false;
        }
    }

    public int getYear() {
        return year;
    }

    public boolean getUpdate() {
        return update;
    }

    //MODIFIES: this
    //EFFECTS: if update is true, set year to y
    public void setYear(int y) {
        if (update) {
            this.year = y;
        }
    }

    //MODIFIES: this
    //EFFECTS: set update to true
    public void setUpdateTrue() {
        this.update = true;
    }

    //REQUIRES: y to be larger than 0
    //EFFECTS: return true if y equals year
    public boolean equals(int y) {
        return y > 0 && y == year;
    }

}

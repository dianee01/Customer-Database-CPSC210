package model;

import org.json.JSONObject;
import persistence.Writable;

//create a date with its year only, as well as a boolean that determines whether the date can be updated or not
public class Date implements Writable {
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

    //REQUIRES: y to be larger than 0
    //MODIFIES: this
    //EFFECTS: set year to y, and update to b
    public Date(int y, boolean u) {
        if (y > 0) {
            this.year = y;
        }
        this.update = u;
    }

    //Getters

    public int getYear() {
        return year;
    }

    public boolean getUpdate() {
        return update;
    }

    //Setters

    //MODIFIES: this
    //EFFECTS: if update is true, set year to y and update to false
    public void setYear(int y) {
        if (update) {
            this.year = y;
        }
        this.update = false;
    }

    //MODIFIES: this
    //EFFECTS: set update to true
    public void setUpdateTrue() {
        this.update = true;
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("year", year);
        json.put("update", update);
        return json;
    }
}

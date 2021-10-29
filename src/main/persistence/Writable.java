package persistence;

import org.json.JSONObject;

//This class references code from this JsonSerializationDemo
//Link: https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
public interface Writable {
    // EFFECTS: returns this as JSON object
    JSONObject toJson();
}

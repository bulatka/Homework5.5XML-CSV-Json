package netology;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.LinkedList;
import java.util.List;

public class JsonApp {
    public static void main(String[] args) {
        String json = readString("CSVtoJson.json");
        List<Employee> list = jsonToList(json);
        for (Employee emp : list) {
            System.out.println(emp);
        }
    }

    public static List<Employee> jsonToList(String json) {
        List<Employee> employees = new LinkedList<>();
        JSONParser jsonParser = new JSONParser();
        GsonBuilder gsonBuilder = new GsonBuilder();
        Gson gson = gsonBuilder.create();
        JSONArray jsonArray = new JSONArray();
        try {
            jsonArray = (JSONArray) jsonParser.parse(json);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        for (int i = 0; i < jsonArray.size(); i++) {
            Employee employee = gson.fromJson(jsonArray.get(i).toString(), Employee.class);
            employees.add(employee);
        }
        return employees;
    }

    public static String readString(String filePath) {
        String readString = null;
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(filePath))) {
            readString = bufferedReader.readLine();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return readString;
    }
}
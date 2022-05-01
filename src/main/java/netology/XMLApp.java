package netology;

import org.w3c.dom.*;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.util.LinkedList;
import java.util.List;

public class XMLApp {

    public static void main(String[] args) {
        List<Employee> list = parseXML("data.xml");
        String json = CSVApp.listToJson(list);
        CSVApp.writeString(json, "XMLtoJSON.json");
    }

    public static List<Employee> parseXML(String filePath) {
        List<Employee> employees = new LinkedList<>();
        try {
            DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            Document doc = builder.parse(new File(filePath));
            Node root = doc.getDocumentElement();
            NodeList nodeList = root.getChildNodes();
            for (int i = 0; i < nodeList.getLength(); i++) {
                Node node = nodeList.item(i);
                if (Node.ELEMENT_NODE == node.getNodeType()) {
                    Element element = (Element) node;
                    employees.add(new Employee
                            (Long.parseLong(element.getElementsByTagName("id").item(0).getTextContent()),
                                    element.getElementsByTagName("firstName").item(0).getTextContent(),
                                    element.getElementsByTagName("lastName").item(0).getTextContent(),
                                    element.getElementsByTagName("country").item(0).getTextContent(),
                                    Integer.parseInt(element.getElementsByTagName("age").item(0).getTextContent())));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return employees;
    }
}
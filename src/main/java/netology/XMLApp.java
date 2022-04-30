package netology;

import org.w3c.dom.*;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class XMLApp {

    public static void main(String[] args) {
        List<Employee> list = parseXML("data.xml");
    }

    public static List<Employee> parseXML(String filePath) {
        List<Employee> employees = new LinkedList<>();
        try {
            HashMap<String, String> values = new HashMap<>();
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse(new File(filePath));
            Node root = doc.getDocumentElement();
            NodeList nodeList = root.getChildNodes();
            for (int i = 0; i < nodeList.getLength(); i++) {
                Node node1 = nodeList.item(i);
                NodeList nodeList1 = node1.getChildNodes();
                for (int j = 0; j < nodeList1.getLength(); j++) {
                    Node node2 = nodeList1.item(j);
                    if (Node.ELEMENT_NODE == node2.getNodeType()) {
                        Element element = (Element) node2;
                        values.put(element.getNodeName(), element.getTextContent());
                    }
                }
                System.out.println(values.get("id"));
                Employee employee = new Employee(Long.parseLong(values.get("id")),
                        values.get("firstName"),
                        values.get("lastName"),
                        values.get("country"),
                        Integer.parseInt(values.get("age")));
                employees.add(employee);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return employees;
    }

}


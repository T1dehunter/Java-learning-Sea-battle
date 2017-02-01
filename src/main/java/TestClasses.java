//import java.util.Objects;
import core.GameConfig;
import core.Point;

import java.util.ArrayList;
import static java.util.stream.Collectors.*;
import java.util.stream.Collectors;
import java.util.Random;
import java.util.stream.Stream;
import java.nio.*;
import java.util.stream.Stream;


import java.io.File;
import java.io.IOException;
import java.util.Scanner;

import java.io.File;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;


// for method testGetProperty
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class TestClasses {
    private boolean checkPoint(Point point) {
       if (point.getCell() == 5 && point.getRow() == 5) {
           return false;
       }

       return true;
    }

    public void run() {
//        Core c = new Core();
//
//        Builder b = new Builder("Vasya", "Petya", c);
//
//        c.setBuilder(b);
//
//        c.run();
//        Random randGen = new Random();
//        for (int i = 0; i < 20; i++) {
//            int r = randGen.nextInt(10);
//            System.out.print("\n" + r);
//        }
//
//
//        ArrayList<Point> list = new ArrayList<Point>();
//
//        list.add(new Point(0, 0));
//        list.add(new Point(5, 5));
//        list.add(new Point(0, -1));
//        list.add(new Point(0, -2));
//
//        ArrayList<Point> res = list.stream()
//                .filter(p -> p.getRow() >= 0 && p.getCell() >= 0)
//                .filter(p -> checkPoint(p))
//                .collect(Collectors.toCollection(ArrayList::new));
//
//
//        System.out.print("\n TEST FILTER -> ");
//        System.out.print(res.size());
//        File f = new File("settings.xml");
//        System.out.print("\n TEST GET FILE -> " + f.getAbsolutePath());
//        JAXBContext jaxbContext = JAXBContext.newInstance(GameConfig.class);

//        Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();

//        StringBuilder result = new StringBuilder("");
//
//        //Get file from resources folder
//        ClassLoader classLoader = getClass().getClassLoader();
//        System.out.print("\n TEST classloader -> " + classLoader);
//        File file = new File(classLoader.getResource("file/settings.xml").getFile());
//        try {
//            JAXBContext jaxbContext = JAXBContext.newInstance(GameConfig.class);
//
//            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
//            GameConfig customer = (GameConfig) jaxbUnmarshaller.unmarshal(file);
//            System.out.println(customer);
//            System.out.println("\n test get field from conf " + customer.getAge());
//            System.out.println("\n test get field ships " + customer.getShips().get(0).getColor());
//            System.out.println(customer);
//        } catch (JAXBException e) {
//            e.printStackTrace();
//        }
//        try (Scanner scanner = new Scanner(file)) {
//
//            while (scanner.hasNextLine()) {
//                String line = scanner.nextLine();
//                result.append(line).append("\n");
//            }
//
//            scanner.close();
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//        System.out.print("\n TEST GET FILE -> " + result.toString());
        testGetProperty();
    }

    public void testGetProperty() {
        Properties prop = new Properties();
        InputStream input = null;

        try {
            ClassLoader classLoader = getClass().getClassLoader();
            input = getClass().getClassLoader().getResourceAsStream("config/config.properties");

//            input = new FileInputStream("java/resources/config/config.properties");

            // load a properties file
            prop.load(input);

            // get the property value and print it out
            System.out.println(prop.getProperty("database"));
            System.out.println(prop.getProperty("dbuser"));
            System.out.println(prop.getProperty("dbpassword"));

        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            if (input != null) {
                try {
                    input.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }
}


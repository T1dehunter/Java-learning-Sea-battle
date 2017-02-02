//import java.util.Objects;
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


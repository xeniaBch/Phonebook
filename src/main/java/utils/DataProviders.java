package utils;

import model.Contact;
import org.testng.annotations.DataProvider;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class DataProviders {
    @DataProvider
    public Iterator<Object[]> newContact() {
        List<Object[]> list = new ArrayList<>();
        list.add(new Object[]{"Nevil", "Dolgopups", "015239655336", "nevil.d@hogwarts", "London", "Griffindor"});
        list.add(new Object[]{"Ron", "Whisley", "015212368321", "ron.w@hogwarts", "London", "Griffindor"});
        list.add(new Object[]{"Germiona", "Greindger", "0166533200534", "germiona.g@hogwarts", "London", "Griffindor"});
        list.add(new Object[]{"Drako", "Malfoi", "018543246546", "drako.m@hogwarts", "London", "Slizerin"});
        return list.iterator();
    }

    @DataProvider
    public Iterator<Object[]> newContactWithCsv() throws IOException {
        List<Object[]> list = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/contacts.csv")));
        String line = reader.readLine();
        while (line!=null) {
            String[] split = line.split(",");
            list.add(new Object[]{new Contact()
                    .setName(split[0])
                    .setLastname(split[1])
                    .setPhone(split[2])
                    .setEmail(split[3])
                    .setCity(split[4])
                    .setComment(split[5])});
            line = reader.readLine();
        }
        return list.iterator();
    }
}

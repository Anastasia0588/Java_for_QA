package ru.stqa.pft.addressbook.generator;

import com.beust.jcommander.Parameter;
import ru.stqa.pft.addressbook.model.ContactData;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

public class ContactDataGenerator {
    public static void main(String[] args) throws IOException {
        int count = Integer.parseInt(args[0]);
        File file = new File(args[1]);

        List<ContactData> contacts = generateContacts(count);
        save(contacts, file);

    }

    private static void save(List<ContactData> contacts, File file) throws IOException {
        Writer writer = new FileWriter(file);
        for (ContactData contact:contacts){
            writer.write(String.format("%s;%s;%s\n", contact.getName(), contact.getLastName(), contact.getMobilephone(), contact.getAddress(), contact.getEmail()));
        }
        writer.close();
    }

    private static List<ContactData> generateContacts(int count) {
        List<ContactData> contacts = new ArrayList<ContactData>();
        for (int i = 0; i < count; i++){
           contacts.add(new ContactData().withName(String.format("gName %s", i))
           .withLastName(String.format("gLastName %s", i))
           .withMobilephone(String.format("+7(111)000 00 0%s", i))
           .withAddress(String.format("gAdress %s", i))
           .withEmail(String.format("%semail@yandex.ru", i)));
        }
        return contacts;
    }
}

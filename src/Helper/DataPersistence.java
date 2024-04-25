package Helper;
import java.io.*;

public class DataPersistence {

    public static void serialize(Object obj, String filename) {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(filename))) {
            out.writeObject(obj);
        } catch (IOException e) {
            System.err.println("Error during serialization: " + e.getMessage());
        }
    }

    public static Object deserialize(String filename) {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(filename))) {
            return in.readObject();
        } catch (ClassNotFoundException | IOException e) {
            System.err.println("Error during deserialization: " + e.getMessage());
            return null;
        }
    }
}

package Helper;
import java.io.*;

/**
 * The {@code DataPersistence} class provides static methods to serialize and deserialize objects.
 * It uses Java's built-in serialization mechanisms to write objects to files and read them back.
 */
public class DataPersistence {

    /**
     * Serializes an object to a file with the given filename.
     *
     * @param obj The object to serialize.
     * @param filename The name of the file to which the object is to be serialized.
     *                 The file will be created in the current directory if it does not exist,
     *                 or overwritten if it does.
     */
    public static void serialize(Object obj, String filename) {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(filename))) {
            out.writeObject(obj);
        } catch (IOException e) {
            System.err.println("Error during serialization: " + e.getMessage());
        }
    }

    /**
     * Deserializes an object from a file with the given filename.
     *
     * @param filename The name of the file from which the object is to be deserialized.
     * @return The deserialized object read from the file. Returns {@code null} if an error occurs during deserialization.
     * @throws ClassNotFoundException if the class of the serialized object cannot be found.
     * @throws IOException if an I/O error occurs while reading from the file.
     */
    public static Object deserialize(String filename) {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(filename))) {
            return in.readObject();
        } catch (ClassNotFoundException | IOException e) {
            System.err.println("Error during deserialization: " + e.getMessage());
            return null;
        }
    }
}

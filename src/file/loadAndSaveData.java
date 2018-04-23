
package file;

import java.io.File;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Paths;

public class loadAndSaveData {
    
    public static void save (Serializable data) throws IOException{

        try(ObjectOutputStream oos = new ObjectOutputStream( Files.newOutputStream(Paths.get("saveData")))){
            
            oos.writeObject(data);
        }

    }
    
    public static Object load (Serializable data) throws IOException, ClassNotFoundException{

        try(ObjectInputStream ois = new ObjectInputStream(Files.newInputStream(Paths.get("saveData")))){
            
           return ois.readObject();
        }

    }
}



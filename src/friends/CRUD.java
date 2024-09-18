package friends;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.lang.NumberFormatException;


public class CRUD {
    
    
    //
public String addFriend(String newName,long newNumber){
     try {
         
         String nameNumberString;
         String name;
         long number;
         int index;
         
            File file = new File("friendsContact.txt");
 
            if (!file.exists()) {
                file.createNewFile();
            }
 
 
            RandomAccessFile raf = new RandomAccessFile(file, "rw"); 
            boolean found = false;

            while (raf.getFilePointer() < raf.length()) {
 
                nameNumberString = raf.readLine();
                String[] lineSplit = nameNumberString.split("!");
                name = lineSplit[0];
                number = Long.parseLong(lineSplit[1]);

                if (name == newName || number == newNumber) {
                    found = true;
                    break;
                }
            }
 
            if (found == false) {

                nameNumberString  = newName + "!"  + String.valueOf(newNumber);
                raf.writeBytes(nameNumberString);
                raf.writeBytes(System.lineSeparator());            
                raf.close();
                return " Friend added. ";
            }

            else {

                raf.close();
                return " Input name does not exists. ";
            }
        }
 
        catch (IOException ioe) {
            String mensaje = ioe.getMessage();
            return mensaje;
        }
        catch (NumberFormatException nef) {
            String mensaje = nef.getMessage();
            return mensaje;
        }
    }
    //
    //
public String displayFriend(){
    String resultado = "";
    try {
        String nameNumberString;
        String name;
        long number;
        int index;

        File file = new File("friendsContact.txt");

        if (!file.exists()) {
                file.createNewFile();
        }
        RandomAccessFile raf= new RandomAccessFile(file, "rw");
        boolean found = false;

        while (raf.getFilePointer() < raf.length()) {
                nameNumberString = raf.readLine();
                String[] lineSplit = nameNumberString.split("!");
                name = lineSplit[0];
                number = Long.parseLong(lineSplit[1]);
               resultado += "Friend Name: " + name + "\n"
                           + "Contact Number: " + number + "\n\n";
        }
        raf.close();
    }
        catch (IOException ioe) {
                String mensaje = ioe.getMessage();
                return mensaje;
        }
        catch (NumberFormatException nef) {
                String mensaje = nef.getMessage();
                return mensaje;	
}
 return resultado;
}
    //
    //
public String updateFriend(String newName, long newNumber){
    try {
        String nameNumberString;
        String name;
        long number;
        int index;

        File file = new File("friendsContact.txt");

        if (!file.exists()) {
            file.createNewFile();
        }
        RandomAccessFile raf = new RandomAccessFile(file, "rw");
        boolean found = false;
        while (raf.getFilePointer() < raf.length()) {
                nameNumberString = raf.readLine();
                String[] lineSplit = nameNumberString.split("!");
                name = lineSplit[0];
                number = Long.parseLong(lineSplit[1]);
                if (name.equals(newName) || number == newNumber) {
                        found = true;
                        break;
                }
        }
        if (found == true) {
            File tmpFile = new File("temp.txt");
            RandomAccessFile tmpraf = new RandomAccessFile(tmpFile, "rw");
            raf.seek(0);
            while (raf.getFilePointer()< raf.length()) {
                    nameNumberString = raf.readLine();

                    index = nameNumberString.indexOf('!');
                    name = nameNumberString.substring(0, index);
                    if (name.equals(newName)) {
                        nameNumberString= name + "!"+ String.valueOf(newNumber);
                    }
                    tmpraf.writeBytes(nameNumberString);
                    tmpraf.writeBytes(System.lineSeparator());
            }
            raf.seek(0);
            tmpraf.seek(0);
            while (tmpraf.getFilePointer()< tmpraf.length()) {
                    raf.writeBytes(tmpraf.readLine());
                    raf.writeBytes(System.lineSeparator());
            }
            raf.setLength(tmpraf.length());
            tmpraf.close();
            raf.close();
            tmpFile.delete();
            return " Friend updated. ";
        }
        else {
                raf.close();
                return " Input name does not exists. ";
        }
}
        catch (IOException ioe) {
                String mensaje = ioe.getMessage();
                return mensaje;
        }
        catch (NumberFormatException nef) {
                String mensajito = nef.getMessage();
                return mensajito;
        }
}
    //
//
public String deleteFriend(String newName){
  try {
    String nameNumberString;
    String name;
    long number;
    int index;
    File file = new File("friendsContact.txt");
    if (!file.exists()) {
            file.createNewFile();
    }
    RandomAccessFile raf = new RandomAccessFile(file, "rw");
    boolean found = false;
    while (raf.getFilePointer() < raf.length()) {
            nameNumberString = raf.readLine();
            String[] lineSplit = nameNumberString.split("!");
            name = lineSplit[0];
            number = Long.parseLong(lineSplit[1]);
            if (name.equals(newName)) {
                    found = true;
                    break;
            }
    }
    if (found == true) {
        File tmpFile = new File("temp.txt");
        RandomAccessFile tmpraf= new RandomAccessFile(tmpFile, "rw");
        raf.seek(0);
        while (raf.getFilePointer() < raf.length()) {
            nameNumberString = raf.readLine();
            index = nameNumberString.indexOf('!');
            name = nameNumberString.substring(0, index);
            if (name.equals(newName)) {
                    continue;
            }
            tmpraf.writeBytes(nameNumberString);
            tmpraf.writeBytes(System.lineSeparator());
        }
        raf.seek(0);
        tmpraf.seek(0);
        while (tmpraf.getFilePointer()< tmpraf.length()) {
            raf.writeBytes(tmpraf.readLine());
            raf.writeBytes(System.lineSeparator());
        }
        raf.setLength(tmpraf.length());
        tmpraf.close();
        raf.close();
        tmpFile.delete();
        return " Friend deleted. ";
    }
            else {
                    raf.close();
                    return " Input name does not exists. ";
            }
    }
    catch (IOException ioe) {
            String mensaje = ioe.getMessage();
            return mensaje;
    }
}

//
}
    


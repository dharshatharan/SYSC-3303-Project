import java.io.*;
import java.util.*;

public class Parser 
{
public static void main(String[] args) throws FileNotFoundException
{
File simulation = new File("/Users/alexbhend/Desktop/SYSC3303/SYSC-3303-Project/Alex-Work/Sim.txt");

try
{
Scanner sc = new Scanner(simulation);
while(sc.hasNextLine()) {
    System.out.println(sc.nextLine());
}
}
catch(FileNotFoundException ex)
{
    System.out.println("File not found");
}

}
}
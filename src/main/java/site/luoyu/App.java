package site.luoyu;

import java.util.Scanner;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()){
            String line = sc.nextLine();
            if(line.equals("")){

            }
        }
        System.out.println( "Hello World!" );
    }

    public void handleInput(String input){
        String[] temp = input.split("\\s");
        if(temp.length<4||temp.length>5){
            System.out.println("Error: the booking is invalid!");
        }
    }
}

package ge.edu.btu.chat.client;

import java.io.*;
import java.lang .*;

class Client {


    private static void displayMenu(boolean startup) {
        if(startup) {
            System.out.println("Please enter a command.");
        }
        System.out.print("> ");
    }


    private static int getLines(String filename) {
        int lines = 0;

        try(BufferedReader br = new BufferedReader(
                new FileReader(filename))) {
            while(br.readLine() != null) lines++;
        } catch(IOException exc) {
            System.out.println("I/O Exception: " + exc);
        }

        return lines;
    }


    private static String getUserInput() {
        String userInput = null;

        try {
            BufferedReader br = new BufferedReader(
                    new InputStreamReader(System.in));

            userInput = br.readLine();
        } catch(IOException exc) {
            System.out.println("I/O Exception: " + exc);
        }

        return userInput;
    }


    private static String[] getResponsesArray(String filename, int lines) {
        int lineCount = 0;

        String line;
        String[] responsesArray = new String[lines];

        try(BufferedReader br = new BufferedReader(
                new FileReader(filename))) {

            do {
                line = br.readLine();

                if(line != null) {
                    responsesArray[lineCount] = line;
                    lineCount++;
                }
            } while(line != null);
        } catch(FileNotFoundException exc) {
            System.out.println("FileNotFoundException: " + exc);
        } catch(IOException exc) {
            System.out.println("I/O Exception: " + exc);
        }

        return responsesArray;
    }


    private static String getResponse(String[] responses, String userInput) {
        String tag, response;
        String[] array;

        for(String responseLine: responses) {
            if(responseLine != null) {
                array = responseLine.split(" - ");
                tag = array[0];
                response = array[1];

                if(tag.compareToIgnoreCase(userInput) == 0) {
                    return response;
                }
            }
        }
        if(userInput.equals("მაჩვენე კურსი")){
            return " 2.97";
        }
        else if (userInput.equals("მაჩვენე ფილიალი")){
            return " ი.ჭავჭავაძის გამზირი. , ვაჟა-ფშაველას გამზირი , . გურამიშვილის გამზირი.";
        }
        else if (userInput.equals("ნახვამდის")){
            return " გმადლობთ რომ დაგვიკავშირდით, ნახვამდის!" +
                    "\nკავშირის დასასრული";
        }


        return " სამწუხაროდ ამ კითხვაზე პასუხი არ მაქვს.";
    }


    public static void main(String args[]) {


        String userInput, response;
        String filename = "src/chatbot/responses.txt";

        int lines = getLines(filename);
        String[] responsesArray = getResponsesArray(filename, lines);


        displayMenu(true);


        do {
            userInput = getUserInput();
            response = getResponse(responsesArray, userInput);

            System.out.println("პასუხი: " + response);

            if(!userInput.equals("ნახვამდის")) {
                displayMenu(false);
            }
        } while(!userInput.equals("ნახვამდის"));
    }
}
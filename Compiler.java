package lab4;

import javax.xml.crypto.dsig.keyinfo.KeyValue;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.*;

public class Compiler {

    private HashMap<Position, Message> messages;
    private HashMap<String, Integer> nameCodes;
    private ArrayList<String> names;


    Compiler(){
        messages = new HashMap<Position, Message>();
        nameCodes = new HashMap<String, Integer>();
        names = new ArrayList<String>();
    }

    public void addMessage(boolean isError, Position c, String text){
        messages.put(c, new Message(text, isError));
    }


    public Scanner getScanner(String program){
        return new Scanner(program, this);
    }

    public Integer addName(String name){
        if(nameCodes.containsKey(name))
            return nameCodes.get(name);
        else {
            int code = names.size();
            names.add(name);
            nameCodes.put(name, code);
            return code;
        }
    }

    public String getName(int code){
        return names.get(code);
    }

    public void outputMessages(){
        for(Map.Entry<Position, Message> entry: messages.entrySet()){
            System.out.println("\nERROR LIST:");
            System.out.print(entry.getValue().isError ? "\tERROR " : "\tWARNING ");
            System.out.print("\t" + entry.getKey() + ": ");
            System.out.println("\t" + entry.getValue().text);
        }
    }

    public static void main(String[] args) throws Exception {

        if(args.length != 1){
            throw new Exception("Missing parsing file!");
        }
        BufferedReader bufferedReader = new BufferedReader(new FileReader(args[0]));
        StringBuilder text = new StringBuilder();
        String line;
        try {
            while ((line = bufferedReader.readLine()) != null){
                text
                        .append(line)
                        .append("\n");
            }
        }finally {
            bufferedReader.close();
        }

        Compiler compiler = new Compiler();
        Scanner scanner = compiler.getScanner(text.toString());
        Token token = scanner.nextToken();
        while (token.tag != DomainTag.EOF)
        {
            System.out.println(token);
            token = scanner.nextToken();
        }
        compiler.outputMessages();
    }

}

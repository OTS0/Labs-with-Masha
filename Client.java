import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.*;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Client extends Object {
    int port=0;
    private String address="";
    private Socket ClientSocket;
    boolean fromFile=false;
    boolean check=false;
    int id=0;
    HumanBeing human=null;
    String path="";
    String WeaponType="";
    String SoundTrackNme="";
    public void workClient(){

    try{
        ClientSocket =new Socket (address,port);
    }
    catch (IOException e){
    }
        start();
    }
    String str = "";
    public void start() {
        try {
            System.out.println("Hello!");
            Scanner in = new Scanner(System.in);
            while (!equalsPart(str, "exit")) {
                try {
                    str = in.nextLine();

                }
                catch (NoSuchElementException e){
                    System.out.println("Invalid input!");
                    System.out.println("It is very bad.");
                    System.out.println(":(");
                    System.out.println("Goodbye");
                    System.exit(0);
                }
                try{
                command(str,in);}
                catch (IOException e){
                    System.out.println(e.getMessage());
                } catch (IdException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("Goodbye");
        }
        catch (NoSuchElementException ignored){}
    }
    public void command (String str,Scanner in) throws IOException, IdException {
        ObjectOutputStream toServer = new ObjectOutputStream(ClientSocket.getOutputStream());
        ObjectInputStream outServer = new ObjectInputStream(ClientSocket.getInputStream());
        if (equalsPart(str, "help")) {
            Request request = new Request(null,null,null,str,null,null);
            toServer.writeObject(request);
            try {
                Answer answer = (Answer) outServer.readObject();
                if (answer.getCheck()){
                    System.out.println(answer.getAnswer());
                }
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }

        } else if (equalsPart(str, "info")) {
            Request request = new Request(null,null,null,str,null,null);
            toServer.writeObject(request);
            try {
                Answer answer = (Answer) outServer.readObject();
                if (answer.getCheck()){
                    System.out.println(answer.getAnswer());
                }
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }

        } else if (equalsPart(str, "show")) {
            Request request = new Request(null,null,null,str,null,null);
            toServer.writeObject(request);
            try {
                Answer answer = (Answer) outServer.readObject();
                if (answer.getCheck()){
                    System.out.println(answer.getAnswer());
                }
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }

        } else if (equalsPart(str, "add")) {
            HumanBeing human = new HumanBeing();
            // ввод данных в нужные поля
            try {
                setAll(human, in, check);
            } catch (CollectionException e) {
                e.printStackTrace();
            }
            // добавляем в коллекцию данные по умолчанию
            Request request = new Request(null,human,null,str,null,null);
            toServer.writeObject(request);
            try {
                Answer answer = (Answer) outServer.readObject();
                if (answer.getCheck()){
                    System.out.println("A new object has been added to the collection");
                }
                else{}
                System.out.println("A new object has not been added to the collection");
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        } else if (equalsPart(str, "update id")) {
            String argument = trimString(str, "update id");
            id=Integer.parseInt(argument);
            Request request = new Request(id,null,null,str,null,null);
            toServer.writeObject(request);
            try {
                Answer answer = (Answer) outServer.readObject();
                if (answer.getCheck()){
                    HumanBeing human = new HumanBeing();
                    setAll(human, in, check);
                    human.setId(id);
                    Request request1 = new Request(null,human,null,str,null,null);
                    toServer.writeObject(request1);
                    Answer answer1 = (Answer) outServer.readObject();
                    if (answer1.getCheck()){
                        System.out.println("Object updated.");
                    }
                }
            } catch (ClassNotFoundException | CollectionException e) {
                e.printStackTrace();
            }
        } else if (equalsPart(str, "remove_by_id")) {
            String argument = trimString(str, "remove_by_id");
            id=Integer.parseInt(argument);
            Request request = new Request(id,null,null,str,null,null);
            toServer.writeObject(request);

        } else if (equalsPart(str, "clear")) {
            Request request = new Request(null,null,null,str,null,null);
            toServer.writeObject(request);
            try {
                Answer answer = (Answer) outServer.readObject();
                if (answer.getCheck()){
                    System.out.println("Collection is empty.");
                }
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }

        }  else if (equalsPart(str, "execute_script")) {

        } else if (equalsPart(str, "head")) {
            Request request = new Request(null,null,null,str,null,null);
            toServer.writeObject(request);
            try {
                Answer answer = (Answer) outServer.readObject();
                if (answer.getCheck()){
                    System.out.println(answer.getAnswer());
                }
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        } else if (equalsPart(str, "remove_head")) {
            Request request=new Request(null,null,null,str,null,null);
            toServer.writeObject(request);
        } else if (equalsPart(str, "add_if_min")) {
            HumanBeing human = new HumanBeing();
            // ввод данных в нужные поля
            try {
                setAll(human, in, check);
            } catch (CollectionException e) {
                e.printStackTrace();
            }
            // добавляем в коллекцию данные по умолчанию
            Request request = new Request(null,human,null,str,null,null);
            toServer.writeObject(request);
            try {
                Answer answer = (Answer) outServer.readObject();
                if (answer.getCheck()){
                    System.out.println("A new object has been added to the collection");
                }
                else{}
                System.out.println("A new object has not been added to the collection");
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        } else if (equalsPart(str, "remove_all_by_weapon_type")) {

        } else if (equalsPart(str, "filter_by_mood")) {
        } else if (equalsPart(str, "filter_contains_soundtrack_name")) {
        } else if (!equals(str, "") && (!equalsPart(str, "exit")) && (!fromFile)) {
            System.out.println("Invalid command. Enter \"help\" to see valid commands.");
        }
    }

    public static void setAll(HumanBeing human, Scanner in, Boolean check) throws CollectionException{
        human.setName(in, check);
        human.setCoordx(in, check);
        human.setCoordy(in, check);
        human.setRealHero(in, check);
        human.setHasToothpick(in, check);
        human.setImpactSpeed(in, check);
        human.setSoundtrackName(in, check);
        human.setWeaponType(in, check);
        human.setMood(in, check);
        human.setCarName(in, check);
        human.setCarCool(in, check);
    }
}



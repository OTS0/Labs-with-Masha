import java.text.SimpleDateFormat;
import java.util.*;
/**
 * @author Ilyakova Maria собственной персоной
 * @version 42
 * Класс, запускающий обработку команд пользователя и несколько автоматических протоколов работы с коллекцией
 */
public class App extends Object{

    /** Поле введенная команда (изначально пуста) */
    String str = "";
    /** Поле время создания коллекции = время запуска приложения  */
    static String time;

    /**
     * Конструктор, пустой, как вы заметили
     */
    public App() {
    }


    /**
     * @param collection - коллекция объектов HumanBeing
     * @return переменную окружения
     */
    public String readFile(LinkedList<HumanBeing> collection){
        String path;
        path = System.getenv("LABA5json");
        Comand.read(collection, path);
        return path;
    }

    /**
     * Метод, создающий коллекцию, сортирующий ее, запускающий цикл обработки команд пользователя
     */
    public void Start() {
        try {
            System.out.println("Hello!");
            Scanner in = new Scanner(System.in);
            LinkedList<HumanBeing> collection = new LinkedList<>();
            String path = readFile(collection);
            Comparator3000 comparator = new Comparator3000();
            collection.sort(comparator);
            //определяем дату
            Date creation = new Date();
            SimpleDateFormat formatForDateNow = new SimpleDateFormat("E dd.MM.yyyy hh:mm:ss a");
            time = formatForDateNow.format(creation);
            while (!Comand.equalsPart(str, "exit")) {
                collection.sort(comparator);
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
                Comands(collection, str, in, true, path, false);
            }
            System.out.println("Goodbye");
        } catch(CollectionException f1){
            System.out.println(f1.getMessage());
            System.out.println(f1.getInfo());
        }
        catch(IdException i){
            System.out.println("Три строчки кода, которые не должны существовать");
        }
        catch (NoSuchElementException ignored){}
    }

    /**
     * Класс, реализующий обработку введенных команд и вызывающий соответствующие методы
     * @param collection - коллекция HumanBeing
     * @param str - введенная команда
     * @param in - объект Scanner для последующего ввода команд внутри других методов
     * @param check - проверка&&&&7
     * @param path - путь к файлу, нужен для сохранения коллекции в файл
     * @param fromFile - true, если производится обработка команд скрипта (не будет высвечиваться сообщение об ошибке ввода)
     * @throws CollectionException - выбрасывается, если в скрипте ошибки или коллекция пустая
     * @throws IdException - add, executeScript могут выбросить исключение
     * @throws NoSuchElementException
     */

    public static void Comands(LinkedList<HumanBeing> collection, String str, Scanner in, Boolean check, String path, boolean fromFile) throws CollectionException, IdException, NoSuchElementException {
        if (Comand.equalsPart(str, "help")) {
            Comand.help();
        } else if (Comand.equalsPart(str, "info")) {
            Comand.info(collection, time);
        } else if (Comand.equalsPart(str, "show")) {
            Comand.show(collection);
        } else if (Comand.equalsPart(str, "add")) {
            Comand.add(in, collection, check);
        } else if (Comand.equalsPart(str, "update id")) {
            Comand.updateId(in, collection, str, check);
        } else if (Comand.equalsPart(str, "remove_by_id")) {
            Comand.removeById(collection, str);
        } else if (Comand.equalsPart(str, "clear")) {
            Comand.clear(collection);
        } else if (Comand.equalsPart(str, "save")) {
            Comand.save(collection, path);
        } else if (Comand.equalsPart(str, "execute_script")) {
            Comand.executeScript(collection, str);
        } else if (Comand.equalsPart(str, "head")) {
            Comand.head(collection);
        } else if (Comand.equalsPart(str, "remove_head")) {
            Comand.removeHead(collection);
        } else if (Comand.equalsPart(str, "add_if_min")) {
            Comand.addIfMin(in, collection, check);
        } else if (Comand.equalsPart(str, "remove_all_by_weapon_type")) {
            Comand.removeAllByWeaponType(collection, str);
        } else if (Comand.equalsPart(str, "filter_by_mood")) {
            Comand.filterByMood(collection, str);
        } else if (Comand.equalsPart(str, "filter_contains_soundtrack_name")) {
            Comand.filterContainsSoundtrackName(collection, str);
        } else if (!Comand.equals(str, "") && (!Comand.equalsPart(str, "exit")) && (!fromFile)) {
            System.out.println("Invalid command. Enter \"help\" to see valid commands.");
        }
    }
}






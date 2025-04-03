import java.util.List;
import java.util.ArrayList;

class Encyclopedia {
    private String title;
    private int publicationYear;
    private List<Person> persons;

    public Encyclopedia()
    {
        System.out.println("Конструтор класса Encyclopedia без параметров.");
    }

    public Encyclopedia(String title, int publicationYear) {
        this.title = title;
        this.publicationYear = publicationYear;
        persons = new ArrayList<>();
        System.out.println("Конструктор класса Encyclopedia с параметрами.");
    }

    public Encyclopedia(Encyclopedia other)
    {
        this.title = other.title;
        this.publicationYear = other.publicationYear;
        System.out.println("Конструктор класса Encyclopedia копирования.");
    }

    //деструктор
    public void cleanup(){
        persons.clear();
        System.out.println("Ресурсы очищены.");
        System.out.println("Деструктор класса Encyclopedia.");
    }

    public void CreatePerson(String name, String career, String description){
        Person person = new Person(name, career, description);
        add(person);
    }

    public void CreateCopies(Person person, int n){
        for(int i = 0; i < n; i++){
            Person copy = new Person(person);
            add(copy);
        }
    }

    public void add(Person person) {
        persons.add(person);
    }

    public void remove(String name) {
        persons.removeIf(person -> person.getName().equals(name));
    }

    public void reductPerson(String oldName, String newName, String newCareer, String newDescription) {
        for (Person person : persons) {
            if (person.getName().equals(oldName)) {
                person.changeName(newName);
                person.changeCareer(newCareer);
                person.changeDescription(newDescription);
                break;
            }
        }
    }

    public void showPerson() {
        for (Person person : persons) {
            person.showIhfo();
        }
    }
}
import java.util.List;
import java.util.ArrayList;

class Encyclopedia {
    private String title;
    private int publicationYear;
    private List<Person> persons;

    public Encyclopedia() {
        this.persons = new ArrayList<>();
        System.out.println("Конструктор класса Encyclopedia без параметров.");
    }

    public Encyclopedia(String title, int publicationYear) {
        this.title = title;
        this.publicationYear = publicationYear;
        this.persons = new ArrayList<>();
        System.out.println("Конструктор класса Encyclopedia с параметрами.");
    }

    public Encyclopedia(Encyclopedia other) {
        this.title = other.title;
        this.publicationYear = other.publicationYear;
        this.persons = new ArrayList<>(other.persons); // Копируем список
        System.out.println("Конструктор класса Encyclopedia копирования.");
    }

    public void cleanup() {
        persons.clear();
        System.out.println("Ресурсы очищены.");
        System.out.println("Деструктор класса Encyclopedia.");
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
        System.out.println("Список людей в энциклопедии:");
        System.out.println("========================================");
        for (Person person : persons) {
            person.showIhfo(); // Вызываем метод showIhfo для каждого человека
        }
        System.out.println("========================================");
    }
}
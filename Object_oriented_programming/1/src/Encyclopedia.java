import java.util.List;
import java.util.ArrayList;

class Encyclopedia {
    private String title;
    private int publicationYear;
    private List<Person> persons;

    public Encyclopedia(String title, int publicationYear) {
        this.title = title;
        this.publicationYear = publicationYear;
        persons = new ArrayList<>();
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
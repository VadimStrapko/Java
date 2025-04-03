import java.util.List;

public class Writers extends Person {
    private String genre;
    private int birthYear;
    private String nationality;
    private List<String> famousWorks;
    private Integer deathYear;
    private String biography;
    private List<String> awards;

    public Writers(String name, String career, String description, String genre, int birthYear, String nationality, List<String> famousWorks, Integer deathYear, String biography, List<String> awards) {
        super(name, career, description);
        this.genre = genre;
        this.birthYear = birthYear;
        this.nationality = nationality;
        this.famousWorks = famousWorks;
        this.deathYear = deathYear;
        this.biography = biography;
        this.awards = awards;
        System.out.println("Конструктор класса Writers c параметрами.");
    }

    public Writers() {
        System.out.println("Конструктор класса Writers без параметров.");
    }

    public Writers(Writers other) {
        super(other.getName(), other.getCareer(), other.getDescription());
        this.genre = other.genre;
        this.birthYear = other.birthYear;
        this.nationality = other.nationality;
        this.famousWorks = List.copyOf(other.famousWorks);
        this.deathYear = other.deathYear;
        this.biography = other.biography;
        this.awards = List.copyOf(other.awards);
        System.out.println("Конструктор класса Writers копирования.");
    }

    public String getGenre() {
        return genre;
    }

    public int getBirthYear() {
        return birthYear;
    }

    public String getNationality() {
        return nationality;
    }

    public List<String> getFamousWorks() {
        return famousWorks;
    }

    public Integer getDeathYear() {
        return deathYear;
    }

    public String getBiography() {
        return biography;
    }

    public List<String> getAwards() {
        return awards;
    }

    @Override
    public void showInfo() {
        System.out.println("========================================");
        System.out.println("Информация о писателе:");
        System.out.println("----------------------------------------");
        System.out.printf("Имя: %s%n", getName());
        System.out.printf("Род деятельности: %s%n", getCareer());
        System.out.printf("Описание: %s%n", getBiography());
        System.out.printf("Жанр: %s%n", getGenre());
        System.out.printf("Год рождения: %d%n", getBirthYear());
        System.out.printf("Национальность: %s%n", getNationality());
        System.out.printf("Известные работы: %s%n", String.join(", ", getFamousWorks()));
        System.out.printf("Год смерти: %s%n", (getDeathYear() != null ? getDeathYear().toString() : "Жив"));
        System.out.printf("Награды: %s%n", String.join(", ", getAwards()));
        System.out.println("========================================");
    }
}
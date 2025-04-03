import java.util.List;

public class Painters extends Person {
    private String style;
    private int birthYear;
    private String nationality;
    private List<String> famousWorks;
    private Integer deathYear;
    private String biography;

    public Painters(String name, String career, String description, String style, int birthYear,
                    String nationality, List<String> famousWorks, Integer deathYear, String biography) {
        super(name, career, description);
        this.style = style;
        this.birthYear = birthYear;
        this.nationality = nationality;
        this.famousWorks = famousWorks;
        this.deathYear = deathYear;
        this.biography = biography;
        System.out.println("Конструктор класса Painters с параметрами.");
    }

    public Painters(Painters other) {
        super(other.getName(), other.getCareer(), other.getDescription());
        this.style = other.style;
        this.birthYear = other.birthYear;
        this.nationality = other.nationality;
        this.famousWorks = List.copyOf(other.famousWorks);
        this.deathYear = other.deathYear;
        this.biography = other.biography;
        System.out.println("Конструктор класса Painters копирования.");
    }

    public Painters() {
        System.out.println("Конструктор класса Painters без параметров.");
    }

    @Override
    public void showInfo() {
        System.out.println("========================================");
        System.out.println("Информация о художнике:");
        System.out.println("----------------------------------------");
        System.out.printf("Стиль: %s%n", style);
        System.out.printf("Год рождения: %d%n", birthYear);
        System.out.printf("Национальность: %s%n", nationality);
        System.out.printf("Известные работы: %s%n", String.join(", ", famousWorks));
        System.out.printf("Год смерти: %s%n", (deathYear != null ? deathYear.toString() : "Жив"));
        System.out.printf("Биография: %s%n", biography);
        System.out.println("========================================");
    }

    // Геттеры
    public String getStyle() {
        return style;
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
}
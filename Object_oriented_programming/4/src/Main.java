import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;
public class Main {
    public static void main(String[] args) {
        Encyclopedia encyclopedia = new Encyclopedia("Большая Советская Энциклопедия", 1926);
        Encyclopedia emptyEn = new Encyclopedia();

        List<String> famousWorks = new ArrayList<>();
        famousWorks.add("Звёздная ночь");
        famousWorks.add("Автопортрет");

        Painters painter1 = new Painters("Винсент Ван Гог", "Художник", "Импрессионист и постимпрессионист.",
                "Импрессионизм", 1853, "Нидерланды", famousWorks, null, "Известный голландский художник.");

        famousWorks.add("Мона Лиза");
        famousWorks.add("Тайная вечеря");

        Painters painter2 = new Painters("Леонардо да Винчи", "Художник", "Ренессансный художник.",
                "Ренессанс", 1452, "Италия", famousWorks, 1519, "Гений Ренессанса.");

        Painters painter3 = new Painters(painter1);


        List<String> famousWorks1 = new ArrayList<>();
        famousWorks1.add("Война и мир");
        famousWorks1.add("Анна Каренина");

        Writers writer1 = new Writers(
                "Лев Толстой",
                "Писатель",
                "Основоположник русской литературы.",
                "Роман",
                1828,
                "Россия",
                famousWorks1,
                1910,
                "Выдающийся русский писатель.",
                new ArrayList<>()
        );

        List<String> famousWorks2 = new ArrayList<>();
        famousWorks2.add("Евгений Онегин");
        famousWorks2.add("Руслан и Людмила");

        Writers writer2 = new Writers(
                "Александр Пушкин",
                "Поэт",
                "Основоположник современного русского языка и литературы.",
                "Поэзия",
                1799,
                "Россия",
                famousWorks2,
                1837,
                "Великий русский поэт.",
                new ArrayList<>()
        );

        Writers writer3 = new Writers(writer1);

        encyclopedia.add(writer3);
        encyclopedia.add(writer2);
        encyclopedia.add(writer1);
        encyclopedia.add(painter1);
        encyclopedia.add(painter2);
        encyclopedia.add(painter3);



        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("══════════════════════════════════");
            System.out.println("1. Добавить личность.");//не используется, так как класс стал абстракным
            System.out.println("2. Удалить личность.");//не используется, так как класс стал абстракным
            System.out.println("3. Редактировать личность.");//не используется, так как класс стал абстракным
            System.out.println("4. Просмотр всех личностей.");
            System.out.println("5. Просмотр всех живописцев.");
            System.out.println("6. Просмотр всех писателей.");
            System.out.println("7. Выход.");
            System.out.println("══════════════════════════════════");

            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    System.out.println("Добавление личности: ");
                    System.out.println("Введите имя: ");
                    String name = scanner.nextLine();
                    if (name.isEmpty() || !name.matches("[а-яА-Я]+")) {
                        System.out.println("Некорректный ввод имени.");
                    } else {
                        System.out.println("Введите род деятельности: ");
                        String career = scanner.nextLine();
                        if (career.isEmpty()) {
                            System.out.println("Некорректный ввод рода деятельности.");
                        } else {
                            System.out.println("Введите описание: ");
                            String description = scanner.nextLine();
                            if (description.isEmpty()) {
                                System.out.println("Некорректный ввод описания.");
                            } else {
                                //encyclopedia.add(new Person(name, career, description));
                                System.out.println("Личность добавлена.");
                            }
                        }
                    }
                    break;

                case "2":
                    System.out.println("Удаление личности: ");
                    System.out.println("Введите имя, по которому будет проводиться удаление: ");
                    String removeName = scanner.nextLine();
                    if (removeName.isEmpty() || !removeName.matches("[а-яА-Я]+")) {
                        System.out.println("Некорректный ввод.");
                    } else {
                        encyclopedia.remove(removeName);
                        System.out.println("Личность удалена.");
                    }
                    break;

                case "3":
                    System.out.println("Редактирование личности: ");
                    System.out.println("Введите имя, по которому будет проводиться редактирование: ");
                    String reductName = scanner.nextLine();

                    if (reductName.isEmpty()) {
                        System.out.println("Некорректный ввод.");
                    } else {
                        System.out.print("Введите новое имя: ");
                        String redName = scanner.nextLine().trim();

                        if (redName.isEmpty()) {
                            System.out.println("Имя не может быть пустым.");
                        } else {
                            System.out.println("Подтвердите ввод нового имени: ");
                            String confirmName = scanner.nextLine().trim();

                            if (redName.equals(confirmName)) {
                                System.out.print("Введите новый род деятельности: ");
                                String redCareer = scanner.nextLine().trim();

                                if (redCareer.isEmpty()) {
                                    System.out.println("Род деятельности не может быть пустым.");
                                } else {
                                    System.out.print("Введите новое описание: ");
                                    String redDescription = scanner.nextLine().trim();

                                    if (redDescription.isEmpty()) {
                                        System.out.println("Описание не может быть пустым.");
                                    } else {
                                        encyclopedia.reductPerson(reductName, redName, redCareer, redDescription);
                                        System.out.println("Пользователь с именем " + reductName + " изменен.");
                                    }
                                }
                            } else {
                                System.out.println("Имена не совпадают.");
                            }
                        }
                    }
                    break;

                case "4":
                    encyclopedia.showPerson();
                    break;

                case "5":
                    painter1.showInfo();
                    painter2.showInfo();
                    painter3.showInfo();
                     break;
                case "6":

                    break;

                case "7":
                    System.out.println("Выход из программы.");
                    scanner.close();
                    return;

                default:
                    System.out.println("Некорректный выбор. Пожалуйста, попробуйте снова.");
            }
        }
    }
}

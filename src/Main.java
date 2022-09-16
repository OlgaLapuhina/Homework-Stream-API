import java.util.*;
import java.util.stream.Collectors;


public class Main {
    public static void main(String[] args) {
        List<String> names = Arrays.asList("Jack", "Connor", "Harry", "George", "Samuel", "John");
        List<String> families = Arrays.asList("Evans", "Young", "Harris", "Wilson", "Davies", "Adamson", "Brown");
        Collection<Person> persons = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            persons.add(new Person(
                    names.get(new Random().nextInt(names.size())),
                    families.get(new Random().nextInt(families.size())),
                    new Random().nextInt(100),
                    Sex.values()[new Random().nextInt(Sex.values().length)],
                    Education.values()[new Random().nextInt(Education.values().length)])
            );
        }
        System.out.println(persons);
        System.out.println("Данные по итогам переписи населения города Лондон:");
        long counterMinors = persons.stream()
                .filter(l -> l.getAge() < 18)
                .count();
        System.out.println("Количество несовершеннолетних жителей: " + counterMinors);

        List<String> listLegionnaires = persons.stream()
                .filter(s -> s.getSex() == Sex.MAN)
                .filter(b -> b.getAge() >= 18)
                .filter(c -> c.getAge() <= 27)
                .map(Person::getFamily)
                .collect(Collectors.toList());
        System.out.println("Список фамилий призывников: " + listLegionnaires);

        Collection<Person> workable = persons.stream()
                .filter(e -> e.getEducation() == Education.HIGHER)
                .filter(p -> p.getAge() >= 18)
                .filter(p -> (p.getSex() == Sex.WOMAN && p.getAge() < 60) || (p.getSex() == Sex.MAN && p.getAge() < 65))
                .sorted(Comparator.comparing(Person::getFamily))
                .collect(Collectors.toList());
        System.out.println("Отсортированный по фамилии список потенциально работоспособных людей с высшим образованием:");
        System.out.print(workable);
    }
}

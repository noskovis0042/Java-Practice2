package Task13;

import java.util.StringTokenizer;

public class Address {
    private String country;
    private String region;
    private String city;
    private String street;
    private String house;
    private String building;
    private String apartment;

    // (а) через split — только запятая
    public void parseWithSplit(String input) {
        String[] parts = input.split(",");
        // split может вернуть меньше 7 частей — getPart это учитывает
        country    = getPart(parts, 0);
        region     = getPart(parts, 1);
        city       = getPart(parts, 2);
        street     = getPart(parts, 3);
        house      = getPart(parts, 4);
        building   = getPart(parts, 5);
        apartment  = getPart(parts, 6);
    }

    // (б) через StringTokenizer — любой из , . ;
    public void parseWithTokenizer(String input) {
        StringTokenizer st = new StringTokenizer(input, ",.;");
        country    = st.hasMoreTokens() ? st.nextToken().trim() : "";
        region     = st.hasMoreTokens() ? st.nextToken().trim() : "";
        city       = st.hasMoreTokens() ? st.nextToken().trim() : "";
        street     = st.hasMoreTokens() ? st.nextToken().trim() : "";
        house      = st.hasMoreTokens() ? st.nextToken().trim() : "";
        building   = st.hasMoreTokens() ? st.nextToken().trim() : "";
        apartment  = st.hasMoreTokens() ? st.nextToken().trim() : "";
    }

    private static String getPart(String[] arr, int i) {
        return i < arr.length ? arr[i].trim() : "";
    }

    @Override
    public String toString() {
        return String.format(
                "Address{country='%s', region='%s', city='%s', street='%s', house='%s', building='%s', apartment='%s'}",
                country, region, city, street, house, building, apartment);
    }

    public static void main(String[] args) {
        String[] inputs = {
                "Россия, Московская область, Москва, Тверская, 12, 3, 45",
                "  Россия ,  Ленинградская область , Санкт-Петербург , Невский проспект , 1 , 2 , 10  ",
                "Россия. Татарстан. Казань. Баумана. 5. 1. 22",                  // точки
                "Россия; Свердловская область; Екатеринбург; Ленина; 10; 2; 33", // точки с запятой
                "США, Калифорния, Лос-Анджелес, Голливудский бульвар, 100"      // не хватает частей
        };

        for (String input : inputs) {
            System.out.println("ВХОД: \"" + input + "\"");

            Address a1 = new Address();
            a1.parseWithSplit(input);
            System.out.println("  split()        → " + a1);

            Address a2 = new Address();
            a2.parseWithTokenizer(input);
            System.out.println("  StringTokenizer → " + a2);
            System.out.println();
        }
    }
}

package SubStringAlgs;

import java.util.*;

class Algs {

    static class BoyerMoor {
        private String text;
        private String pattern;

        BoyerMoor(String text, String pattern) {
            this.text = text;
            this.pattern = pattern;
            System.out.println("Запуск алгоритма Бойера-Мура поиска подстроки!");
            if (text.equals("")) {
                System.out.println("You didn't enter the text!");
                return;
            }
            if (pattern.equals("")) {
                System.out.println("Nothing to search!");
                return;
            }
            fillMap();
        }

        private Map<Character, Integer> map = new TreeMap<>();

        private void fillMap() {
            for (int i = 0; i < pattern.length() - 1; i++) {
                int value = pattern.length() - i - 1;
                map.put(pattern.charAt(i), value);
            }
            map.put(pattern.charAt(pattern.length() - 1), pattern.length());

            System.out.println("Массив значений ");
            for (Map.Entry<Character, Integer> s : map.entrySet()) {
                System.out.print(s.getKey() + " - " + s.getValue() + " ");
            }
            System.out.println();
        }

        public List<Integer> searchSubstring() {
            if (text.equals("")) {
                return null;
            }
            if (pattern.equals("")) {
                return null;
            }
            List<Integer> list = new LinkedList<>();
            int i = 0;
            int j = pattern.length() - 1;

            while (i < text.length()) {
                while (j >= 0 && i < text.length() && text.charAt(i) == pattern.charAt(j)) {
                    if (pattern.length() != 1) {
                        i--;
                        j--;
                    }
                    if (j == 0) {
                        System.out.println("Подстрока найдена: начальная позиция " + i + " ,конечная позиция " + (i + pattern.length() - 1));
                        list.add(i);
                        list.add((i + pattern.length() - 1));
                        j = pattern.length() - 1;
                        i = (i + pattern.length());
                    }
                }
                boolean bol = false;
                for (Map.Entry<Character, Integer> s : map.entrySet()) {
                    if (i < text.length() && s.getKey() == text.charAt(i)) {
                        i += s.getValue();
                        bol = true;
                        break;
                    }
                }
                if (!bol) {
                    i += map.get(pattern.charAt(pattern.length() - 1));
                }
            }
            for (Integer integer : list) {
                System.out.print(integer + " ");
            }
            System.out.println();
            System.out.println();
            return list;
        }
    } //in best try 0(n/m), in the worst try 0(n*m)

    static class RabinKurp {
        private String text;
        private String pattern;
        private int r = 2_147_489;
        private int d = 5;


        RabinKurp(String text, String pattern) {
            this.text = text;
            this.pattern = pattern;
        }

        public void matcher() {
            long mathPow = (long) Math.pow(d, pattern.length() - 1);
            long k = 0;
            long s = 0;

            for (int i = 0; i < pattern.length(); i++) {
                s = (d * s) + pattern.charAt(i) % r;
                k = (d * k) + text.charAt(i) % r;
            }

            for (int i = 0; i < text.length() - pattern.length(); i++) {
                String tmp = text.substring(i, i + pattern.length());

                if (k == s) {
                    if (Objects.equals(tmp, pattern)) {
                        System.out.println("Нашли! Начало подстроки: " + i + ". Конец подстроки: " + (i + pattern.length() - 1));
                    }

                }
                if (i < text.length() - pattern.length())
                    k = d * (k - mathPow * (text.charAt(i) % r))
                            + text.charAt(i + pattern.length()) % r;
            }
        }
    } //0(m+n), m - String's length, n = pattern's length

    static class KMP {
        private String text;
        private String pattern;
        private List<Integer> listForPrefix = new LinkedList<>();
        private List<Integer> listForPositions = new LinkedList<>();

        KMP(String text, String pattern) {
            this.pattern = pattern;
            this.text = text;
            System.out.println("Запуск алгоритма Кнута-Морриса-Пратта поиска подстроки!");
            doPrefixFunc();
        }

        private void doPrefixFunc() {
            for (int i = 0; i < text.length(); i++)
                listForPrefix.add(0);

            for (int i = 1; i < text.length(); i++) {
                int j = listForPrefix.get(i - 1);

                while (j > 0 && text.charAt(i) != text.charAt(j))
                    j = listForPrefix.get(j - 1);

                if (text.charAt(i) == text.charAt(j))
                    ++j;

                listForPrefix.add(i, j);
                listForPrefix.remove(listForPrefix.size() - 1);
            }
            System.out.println("Список префиксов:");
            for (Integer forPrefix : listForPrefix) {
                System.out.print(forPrefix + " ");
            }
            System.out.println();
        }

        public List<Integer> doSearchSubstring() {
            if (text.equals("")) {
                System.out.println("no text");
                return null;
            }
            if (pattern.equals("")) {
                System.out.println("no patter");
                return null;
            }
            int k = 0;
            for (int i = 0; i < text.length(); i++) {
                while (k > 0 && pattern.charAt(k) != text.charAt(i))
                    k = listForPrefix.get(k - 1);

                if (pattern.charAt(k) == text.charAt(i))
                    k++;

                if (pattern.length() == k) {
                    listForPositions.add(i - pattern.length() + 1);
                    listForPositions.add(listForPositions.get(listForPositions.size() - 1) + pattern.length() - 1);
                    k = 0;
                    System.out.println("Подстрока найдена: начальная позиция "
                            + listForPositions.get(listForPositions.size() - 2) + " ,конечная позиция " + listForPositions.get(listForPositions.size() - 1));
                }

            }
            for (Integer listForPosition : listForPositions) {
                System.out.print(listForPosition + " ");
            }
            System.out.println();
            System.out.println();

            return listForPositions;
        }
    } //0(m+n), m - String's length, n = pattern's length
}

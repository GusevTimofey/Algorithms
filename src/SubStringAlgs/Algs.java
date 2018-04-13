package SubStringAlgs;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

class Algs {

    static class BoyerMoor {
        private String text;
        private String pattern;

        BoyerMoor(String text, String pattern) {
            this.text = text;
            this.pattern = pattern;
            System.out.println("Запуск алгоритма Бойера-Мура поиска подстроки!");
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
            List<Integer> list = new LinkedList<>();
            int i = 0;
            int j = pattern.length() - 1;

            while (i < text.length()) {
                while (j > 0 && i < text.length() && text.charAt(i) == pattern.charAt(j)) {
                    j--;
                    i--;
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

    }

    static class RabinKurp {
        private String text;
        private String pattern;
        private int q = 13;
        private int r = 2_000_003;
        private int[] hashArray;
        private int hashPattern;

        RabinKurp(String text, String patter) {
            this.text = text;
            this.pattern = patter;
            hashArray = new int[text.length() - 4];
            System.out.println("Запуск алгоритма Рабина-Карпа поиска подстроки!");
            doHash();
        }

        private void doHash() {
            int sumSymb = 0;
            int sumSymbForText = 0;

            for (int i = 0; i < pattern.length(); i++)
                sumSymb += (pattern.charAt(i) * Math.pow(q, pattern.length() - i - 1));
            hashPattern = sumSymb % r;

            int k = 0;
            String subStrText;
            for (int i = 0; i < text.length(); i++) {
                if (text.length() >= pattern.length() + i) {
                    subStrText = text.substring(i, pattern.length() + i);

                    for (int j = 0; j < subStrText.length(); j++)
                        sumSymbForText += (subStrText.charAt(j) * Math.pow(q, subStrText.length() - j - 1));

                    int hashText = sumSymbForText % r;
                    hashArray[k] = hashText;
                    k++;
                    sumSymbForText = 0;
                }
            }
        }

        public List<Integer> searchSubstring() {
            List<Integer> list = new LinkedList<>();

            for (int i = 0; i < hashArray.length; i++) {
                if (hashArray[i] == hashPattern) {
                    System.out.println("Подстрока найдена: начальная позиция " + i + " ,конечная позиция " + (pattern.length() + i - 1));
                    list.add(i);
                    list.add(pattern.length() + i - 1);
                }
            }

            for (Integer integer : list)
                System.out.print(integer + " ");
            System.out.println();
            System.out.println();
            return list;
        }
    }

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
    }
}

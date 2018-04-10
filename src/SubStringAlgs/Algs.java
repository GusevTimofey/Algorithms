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
            fillMap();
            searchSubstring();
        }

        private Map<Character, Integer> map = new TreeMap<>();

        private void fillMap() {
            for (int i = 0; i < pattern.length() - 1; i++) {
                int value = pattern.length() - i - 1;
                map.put(pattern.charAt(i), value);
            }
            map.put(pattern.charAt(pattern.length() - 1), pattern.length());

            for (Map.Entry<Character, Integer> s : map.entrySet()) {
                System.out.println(s.getKey() + " " + s.getValue());
            }
        }

        private List<Integer> searchSubstring() {
            List<Integer> list = new LinkedList<>();
            int i = 0;
            int j = pattern.length() - 1;

            while (i < text.length()) {
                while (j > 0 && i < text.length() && text.charAt(i) == pattern.charAt(j)) {
                    j--;
                    i--;
                    if (j == 0) {
                        System.out.println("Подстрока найдена: начальная позиция " + i + " ,конечная позиция " + (i + pattern.length() - 1));
                        System.out.println(text.substring(i, (i + pattern.length())));
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
            return list;
        }

    }

    static class RabinKurp {
        private String text;
        private String pattern;
        private int q = 13;
        int r = 2 ^ 32;
        private int[] hashArray;

        RabinKurp(String text, String patter) {
            this.text = text;
            this.pattern = patter;
            hashArray = new int[text.length() / patter.length()];
            doHash();
        }

        public void doHash() {
            int sumSymb = 0;
            int sumSymbForText = 0;
            for (int i = 0; i < pattern.length(); i++) {
                sumSymb += (pattern.charAt(i) * (q ^ i));
            }
            int hashPattern = sumSymb % r;
            System.out.println(hashPattern);

            int k = 0;
            int m = 0;
            String subStrText = null;
            for (int i = 0; i < text.length(); i++) {
                if(m<text.length() - pattern.length()-1) subStrText = text.substring(m, pattern.length() - 1);
                m++;
                for (int j = 0; j < subStrText.length(); j++) {
                    sumSymbForText += (subStrText.charAt(j) * (q ^ i));
                }
                int hashText = sumSymbForText % r;
                hashArray[k] = hashText;
                k++;
            }
            for (int i : hashArray) {
                System.out.println(i);
            }
        }

        private List<Integer> searchSubstring() {
            List<Integer> list = new LinkedList<>();

            return list;
        }
    }
}

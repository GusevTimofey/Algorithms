package SubStringAlgs;

public class Main {
    public static void main(String[] args) {

        String text = "kkkkkkkkk";
        String pattern = "k";

        String text1 = "qwe1eqwe1eqwe1eqwe1e";
        String pattern1 = "qwe1e";

        String text2 = "qwerty1234";
        String pattern2 = "";

        String text3 = "Hfjknefernge34th3l4dj53p4cmfjiwkfnxi4hthmn4ihit5hgoitcc45ithihtcoi54hti5";
        String pattern3 = "c";

        String text4 = "Hfjknefernge34th3l4djc1153p4cmfjiwkfnxi4hthmn4c11ihit5hgoitcc45ithihtcoi54hti5";
        String pattern4 = "c11";

        /**
         Constructor of Object @test takes two string. First - original text, second - searching substring.
         Object @test in the constructor calls:
         first - initialisation of values'map
         second - call the search method the result of which is the list where:
         first index - start of the searching substring, second index - end of the searching substring, also it's looped;
         */

        Algs.BoyerMoor test = new Algs.BoyerMoor(text, pattern);
        test.searchSubstring();
        Algs.BoyerMoor test11 = new Algs.BoyerMoor(text1, pattern1);
        test11.searchSubstring();
        Algs.BoyerMoor test12 = new Algs.BoyerMoor(text2, pattern2);
        test12.searchSubstring();
        Algs.BoyerMoor test13 = new Algs.BoyerMoor(text3, pattern3);
        test13.searchSubstring();
        Algs.BoyerMoor test14 = new Algs.BoyerMoor(text4, pattern4);
        test14.searchSubstring();

        /**
         Constructor of the Object @test1 takes two Strings. First - original text, second - searching substring.
         @test1's constructor calls initialisation of arrays's capacity. Next it calls searching substring algorithm.
         The result of this algorithm is the LinkedList, which consists: first position on the index i(i = 0), second position on the index i+1.
         It's looped.
         */

        Algs.RabinKurp test3 = new Algs.RabinKurp(text, pattern);
        test3.searchSubstring();
        Algs.RabinKurp test31 = new Algs.RabinKurp(text1, pattern1);
        test31.searchSubstring();
        Algs.RabinKurp test32 = new Algs.RabinKurp(text2, pattern2);
        test32.searchSubstring();
        Algs.RabinKurp test33 = new Algs.RabinKurp(text3, pattern3);
        test33.searchSubstring();
        Algs.RabinKurp test34 = new Algs.RabinKurp(text4, pattern4);
        test34.searchSubstring();


        /**
         Construction of the Object @test2 takes two Strings as arguments. First string is the text, where we want to find substring,
         second - our pattern( substring which we will search.
         The constructor starting work with function of initialisation prefix values. Next doSearchSubstr starting work.
         The result is LinkedList with start of substr and end of it.
         */

        Algs.KMP test2 = new Algs.KMP(text, pattern);
        test2.doSearchSubstring();
        Algs.KMP test21 = new Algs.KMP(text1, pattern1);
        test21.doSearchSubstring();
        Algs.KMP test22 = new Algs.KMP(text2, pattern2);
        test22.doSearchSubstring();
        Algs.KMP test43 = new Algs.KMP(text3, pattern3);
        test43.doSearchSubstring();
        Algs.KMP test42 = new Algs.KMP(text4, pattern4);
        test42.doSearchSubstring();

    }
}

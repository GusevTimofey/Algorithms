package SubStringAlgs;

public class Main {
    public static void main(String[] args) {

        String text = "toothtrusbrotherthgtoothkkk";
        String pattern = "tooth";

        /*
        Constructor of Object @test takes two string. First - original text, second - searching substring.
        Object @test in the constructor calls:
        first - initialisation of values'map
        second - call the search method the result of which is the list where:
            first index - start of the searching substring, second index - end of the searching substring, also it's looped;
        */

        Algs.BoyerMoor test = new Algs.BoyerMoor(text, pattern);
        test.searchSubstring();

        /*
        Constructor of the Object @test1 takes two Strings. First - original text, second - searching substring.
        @test1's constructor calls initialisation of arrays's capacity. Next it calls searching substring algorithm.
        The result of this algorithm is the LinkedList, which consists: first position on the index i(i = 0), second position on the index i+1.
        It's looped.
         */

        Algs.RabinKurp test1 = new Algs.RabinKurp(text, pattern);
        test1.searchSubstring();


        /*
        Construction of the Object @test2 takes two Strings as arguments. First string is the text, where we want to find substring,
        second - our pattern( substring which we will search.
        The constructor starting work with function of initialisation prefix values. Next doSearchSubstr starting work.
        The result is LinkedList with start of substr and end of it.
         */

        Algs.KMP test2 = new Algs.KMP(text, pattern);
        test2.doSearchSubstring();

    }
}

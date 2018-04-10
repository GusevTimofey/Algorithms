package SubStringAlgs;

public class Main {
    public static void main(String[] args) {
        String text = "trusthardtoothbtoothrushhesftooth";
        String pattern = "tooth";
        /*
        Constructor of Object @test takes two string. First - original text, second - searching substring.
        Object @test in the constructor calls:
        first - initialisation of values'map
        second - call the search method the result of which is the list where:
            first index - start of the searching substring, second index - end of the searching substring, also in looped;
        */
//        Algs.BoyerMoor test = new Algs.BoyerMoor(text,pattern);

        Algs.RabinKurp test1 = new Algs.RabinKurp(text,pattern);

    }
}

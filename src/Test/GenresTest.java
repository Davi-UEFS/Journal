package Test;
import Model.Enums.Genres;
import org.junit.jupiter.api.Test;

public class GenresTest {

    @Test
    public void testAvaiableGenres() {
        for(int i = 0; i < Genres.values().length; ++i){
            System.out.println(i+1 + " - " + Genres.values()[i].name());
        }
    }

    @Test
    public void testGenres() {
        testAvaiableGenres();
        System.out.println("Escolhendo Terror (0), Romance (4) e Outros(11)");
        int genreNum1 = 1;
        int genreNum2 = 4;
        int genreNum3 = 11;

        assert(Genres.values()[genreNum1] == Genres.AÇÃO);
        assert(Genres.values()[genreNum2] == Genres.ROMANCE);
        assert(Genres.values()[genreNum3] == Genres.OUTROS);
    }
}

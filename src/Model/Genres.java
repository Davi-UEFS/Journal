package Model;

public enum Genres {
    TERROR,
    AÇÃO,
    AVENTURA,
    SUSPENSE,
    ROMANCE,
    FICÇÃO,
    ESPORTES,
    COMÉDIA,
    MISTÉRIO,
    CRIMINAL,
    INFANTIL,
    OUTROS;

    public static void showGenres(){
        for(int i = 0; i < Genres.values().length; ++i){
            System.out.println(i+1 + " - " + Genres.values()[i].name());
        }
    }

}

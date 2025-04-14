package Controller;

import Model.Genres;
import Model.Library;
import Model.Medias.Media;
import Model.Result.*;

import java.util.*;

public abstract class CommonService<T extends Media> implements IMediaService<T> {
    protected final Library journal;

    public CommonService(Library journal) {
        this.journal = journal;
    }

    public IResult markAsSeen(T media){
        if(media.isSeen()){
            return new Failure(media.getMediaType(), "Já marcado como visto");
        }else{
            media.setSeen(true);
            return new Success(media.getMediaType(), "Marcado como visto");
        }
    }

    @Override
    public IResult rate(T media, double rating) {
        if(media.isSeen()){
            media.setRating(rating);
            return new Success(media.getMediaType(), "Avaliação salva com sucesso.)");

        }
        return new Failure(media.getMediaType(), "Marque como visto antes de avaliar");

    }
    @Override
    public IResult writeReview(T media, String review) {
        if(media.isSeen()){
            media.setReview(review);
            return new Success(media.getMediaType(),"Review salva com sucesso.");

        }
        return new Failure(media.getMediaType(), "Marque como visto antes de escrever uma review");

    }

    @Override
    public String readReview(T media) {

        return "Review: " + ((media.getReview() == null) ?
                "Você ainda não escreveu uma review" : media.getReview());
    }
    @Override
    public String showRating(T media){

        return "Nota: " + ((media.getRating() == 0.0) ?
                "Você ainda não avaliou a obra" : media.getRating());

    }

    @Override
    public List<T> searchByTitle(String title, List<T> mediaList) {
        String titleLower = title.toLowerCase().trim();
        List<T> filteredMedia = mediaList.stream().filter
                (media -> media.getTitle().toLowerCase().contains(titleLower)).toList();

        return sortAscending(filteredMedia);
    }

    @Override
    public List<T> searchByYear(int year, List<T> mediaList) {
        List<T> filteredMedia = mediaList.stream().filter
                (media -> media.getYear() == year).toList();

        return sortAscending(filteredMedia);
    }

    @Override
    public List<T> searchByGenre(Genres genre, List<T> mediaList) {
        List<T> filteredMedia = mediaList.stream().filter
                (media -> media.getGenre() == genre).toList();

        return sortAscending(filteredMedia);
    }

    //TODO: CRESCENTE E DA MENOR NOTA PARA A MAIOR OU DA MAIOR PARA A MENOR?

    public List<T> sortAscending(List<T> mediaList) {
        return mediaList.stream().sorted(Comparator.comparing(Media::getRating)).toList();
    }

    public List<T> sortDescending(List<T> mediaList) {
        return mediaList.stream().sorted(Comparator.comparing(Media::getRating)).toList().reversed();
    }

    /**Gera um LinkedHashMap com as chaves sendo o ano e
     * os valores sendo uma lista com midias deste ano.
     */
    public Map<Integer, List<T>> mapByYearRate(List<T> mediaList, boolean ascendingYear,boolean
                                               ascendingRate) {
        Map<Integer, List<T>> mapYearMedia = new LinkedHashMap<>();
        List<Integer> years = new ArrayList<>(journal.getYearsRegistered());

        if(!ascendingYear)
            Collections.reverse(years);

        for (Integer year : years) {

            List<T> filteredMedia;
            if(ascendingRate) {
                filteredMedia = searchByYear(year, mediaList);
            } else{
                filteredMedia = sortDescending(searchByYear(year, mediaList));
            }

            if(!filteredMedia.isEmpty())
                mapYearMedia.put(year, filteredMedia);
        }
        return mapYearMedia;
    }


    /**Gera um EnumMap (para manter a ordem) com as chaves sendo
     *os generos e os valores sendo as listas de cada genero.
     */
    public Map<Genres, List<T>> mapByGenreRate(List<T> mediaList, boolean ascendingRate) {
        Map<Genres, List<T>> mapGenreMedia = new EnumMap<>(Genres.class);

        for (Genres genre : Genres.values()) {
            List<T> filteredMedia;
            if(ascendingRate) {
                filteredMedia = searchByGenre(genre, mediaList);
            } else{
                filteredMedia = sortDescending(searchByGenre(genre, mediaList));
            }
            if(!filteredMedia.isEmpty())
                mapGenreMedia.put(genre, filteredMedia);

        }
        return mapGenreMedia;
    }

}

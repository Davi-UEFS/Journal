package Controller;

import Model.Genres;
import Model.Medias.Media;
import Model.Result.IResult;
import java.util.List;
import java.util.Map;

public interface IMediaService <T extends Media> {
    //TODO: IDEIA, USAR BUILDER
    IResult markAsSeen(T media);
    IResult rate(T media, double rating);
    IResult writeReview(T media, String review);
    String showRating(T media);
    String readReview(T media);

    List<T> searchByTitle(String title, List<T> mediaList);
    List<T> searchByYear(int year, List<T> mediaList);
    List<T> searchByGenre(Genres genre, List<T> mediaList);

    List<T> sortAscending(List<T> mediaList);
    List<T> sortDescending(List<T> mediaList);
    Map<Integer, List<T>> mapByYearRate(List<T> mediaList, boolean ascendingYear, boolean ascendingRate);
    Map<Genres, List<T>> mapByGenreRate(List<T> mediaList, boolean ascendingRate);
}

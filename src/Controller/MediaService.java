package Controller;

import Model.Exceptions.MediaNotFoundException;
import Model.Genres;
import Model.Library;
import Model.Media.Media;
import Model.Media.Season;
import Model.Media.Series;

import java.util.*;

public abstract class MediaService<T extends Media> {
    protected final Library journal;

    public MediaService(Library journal) {
        this.journal = journal;
    }

    public String rate(String title, double rating) {
        try {
            Media media = journal.findMedia(title);
            media.setSeen(true);
            media.setRating(rating);
            return "Avaliação salva com sucesso";

        } catch (MediaNotFoundException e) {
            return e.getMessage();
        }

    }

    public String writeReview(String title, String review) {
        try {
            Media media = journal.findMedia(title);
            media.setSeen(true);
            media.setReview(review);
            return "Review salva com sucesso";

        } catch (MediaNotFoundException e) {
            return e.getMessage();
        }

    }

    public String readReview(String title) {

        try {
            Media media = journal.findMedia(title);
            return "Review: " + ((media.getReview() == null) ?
                    "Você ainda não escreveu uma review" : media.getReview());
        } catch (MediaNotFoundException e) {
            return e.getMessage();

        }
    }

    public String showRating(String title) {

        try {
            Media media = journal.findMedia(title);
            return "Nota: " + ((media.getRating() == 0.0) ?
                    "Você ainda não avaliou a obra" : media.getRating());
        } catch (MediaNotFoundException e) {
            return e.getMessage();

        }
    }

    //TODO: NECESSARIO CAST AO USAR Os METODOs DE BUSCA
    public List<T> searchByTitle(String title, List<T> mediaList) {
        String titleLower = title.toLowerCase().trim();
        List<T> filteredMedia = mediaList.stream().filter
                (media -> media.getTitle().toLowerCase().contains(titleLower)).toList();

        return sortAscending(filteredMedia);
    }

    public List<T> searchByYear(int year, List<T> mediaList) {
        List<T> filteredMedia = mediaList.stream().filter
                (media -> media.getYear() == year).toList();

        return sortAscending(filteredMedia);
    }

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

    public Map<Integer, List<T>> mapByAscendingYearAscendingRate(List<T> mediaList) {
        Map<Integer, List<T>> mapYearMedia = new LinkedHashMap<>();

        for (Integer year : journal.getYearsRegistered()) {
            List<T> filteredMedia = searchByYear(year, mediaList);
            if(!filteredMedia.isEmpty())
                mapYearMedia.put(year, filteredMedia);
        }
        return mapYearMedia;
    }

    public Map<Integer, List<T>> mapByAscendingYearDescendingRate(List<T> mediaList) {
        Map<Integer, List<T>> mapYearMedia = new LinkedHashMap<>();

        for (Integer year : journal.getYearsRegistered()) {
            List<T> filteredMedia = sortDescending(searchByYear(year, mediaList));
            if(!filteredMedia.isEmpty())
                mapYearMedia.put(year, filteredMedia);
        }
        return mapYearMedia;
    }

    public Map<Integer, List<T>> mapByDescendingYearAscendingRate(List<T> mediaList) {
        Map<Integer, List<T>> mapYearMedia = new LinkedHashMap<>();

        for (Integer year : journal.getYearsRegistered().reversed()) {
            List<T> filteredMedia = searchByYear(year, mediaList);
            if(!filteredMedia.isEmpty())
                mapYearMedia.put(year, filteredMedia);
        }
        return mapYearMedia;
    }

    public Map<Integer, List<T>> mapByDescendingYearDescendingRate(List<T> mediaList) {
        Map<Integer, List<T>> mapYearMedia = new LinkedHashMap<>();

        for (Integer year : journal.getYearsRegistered().reversed()) {
            List<T> filteredMedia = sortDescending(searchByYear(year, mediaList));
            if(!filteredMedia.isEmpty())
                mapYearMedia.put(year, filteredMedia);
        }
        return mapYearMedia;
    }

    /*Gera um EnumMap (para manter a ordem) com as chaves sendo
     os generos e os valores sendo as listas de cada genero.
     */
    public Map<Genres, List<T>> byGenreAscendingRate(List<T> mediaList) {
        Map<Genres, List<T>> mapGenreMedia = new EnumMap<>(Genres.class);

        for (Genres genre : Genres.values()) {
            List<T> filteredMedia = searchByGenre(genre, mediaList);
            if(!filteredMedia.isEmpty())
                mapGenreMedia.put(genre, filteredMedia);

        }
        return mapGenreMedia;
    }

    public Map<Genres, List<T>> byGenreDescendingRate(List<T> mediaList) {
        Map<Genres, List<T>> mapGenreT = new EnumMap<>(Genres.class);

        for (Genres genre : Genres.values()) {
            List<T> filteredMedia = sortDescending(searchByGenre(genre, mediaList));
            if(!filteredMedia.isEmpty())
                mapGenreT.put(genre, filteredMedia);

        }
        return mapGenreT;
    }
}

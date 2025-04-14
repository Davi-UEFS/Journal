package Model.Result;

public class Failure implements IResult{
    private final String message;
    private final String mediaType;;

    public Failure(String mediaType, String message){
        this.message = message;
        this.mediaType = mediaType;
    }

    public String getMessage(){
        return "ERRO. " + message;
    }
}

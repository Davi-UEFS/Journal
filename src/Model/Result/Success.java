package Model.Result;

public class Success implements IResult{
    private final String message;
    private final String mediaType;

    public Success(String mediaType, String message){
        this.message = message;
        this.mediaType = mediaType;
    }

    public String getMessage(){
        return mediaType + ":" + message + "✔";
    }


}

package lab4;



public class Message {
    public final boolean isError;
    public final String text;

    Message(String text, boolean isError){
        this.text = text;
        this.isError = isError;
    }
}

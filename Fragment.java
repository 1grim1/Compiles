package lab4;

public class Fragment {
    private final Position starting;
    private final Position following;

    Fragment(Position starting, Position following){
        this.starting = starting;
        this.following = following;
    }

    @Override
    public String toString(){
        return starting.toString() + " - " + following.toString();
    }
}

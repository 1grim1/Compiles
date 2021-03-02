package lab4;

enum DomainTag{
    ERROR,
    IDENT,
    DIRECTIVE,
    EOF,
}

public class Token {
    public DomainTag tag;
    private Fragment fragment;
    private String value;

    Token(DomainTag tag, Position starting, Position following, String value){
        this.tag =  tag;
        this.fragment = new Fragment(starting, following);
        this.value = value;
    }

    @Override
    public String toString(){
        return tag + " " + fragment.toString() + ": " + value;
    }
}

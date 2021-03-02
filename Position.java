package lab4;

public class Position implements Comparable<Position> {

    private final String text;
    private Integer line;
    private Integer pos;
    public Integer index;

    Position(String text){
        this.text = text;
        line = pos = 1;
        index = 0;
    }

    Position(Position p){
        this.text = p.text;
        this.pos = p.pos;
        this.line = p.line;
        this.index = p.index;
    }

    public static Position copy(Position p){
        return new Position(p);
    }


    public boolean isDirective(){
        return text.charAt(index) == '$' || text.charAt(index) == '€' || text.charAt(index) == '£' ||text.charAt(index) == '¥';
    }

    public char cp(){
        return text.charAt(index);
    }

    public boolean isWhiteSpace(){
        return Character.isWhitespace(text.charAt(index));
    }

    public boolean isUpperCase(){
        return Character.isUpperCase(text.charAt(index));
    }

    public boolean isHyphen(){
        return text.charAt(index) == '-';
    }

    public boolean isLetter(){
        return Character.isLetter(text.charAt(index));
    }

    public boolean isLetterOrDigit(){
        return Character.isLetterOrDigit(text.charAt(index));
    }

    public boolean isDecimalDigit(){
        return Character.isDigit(text.charAt(index));
    }

    public boolean isNewLine(){
       return text.charAt(index) == '\n';
    }

    public boolean EOF(){
        return index >= text.length() - 1;
    }

    String getValue(Position p){
        return this.text.substring(this.index, p.index);
    }

    public Position next(){
        if (index < text.length()) {
            if (isNewLine() && index < text.length()) {
                line++;
                pos = 1;
            }else {
                pos++;
            }
            index++;
        }
        return this;
    }

    @Override
    public int compareTo(Position o) {
        return index.compareTo(o.index);
    }

    @Override
    public String toString(){
        return "(" + line + ", " + pos + ") ";
    }
}

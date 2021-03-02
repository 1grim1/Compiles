package lab4;

import java.util.*;

public class Scanner {
    private Compiler compiler;
    private Position cur;
    private ArrayList<Fragment> comments;

    public void skipSpaces(){
        while(!cur.EOF() && (cur.isWhiteSpace() || cur.isNewLine())){
            cur.next();
        }
    }

    public Token nextToken(){
        while (!cur.EOF()){
            skipSpaces();
            Position start = Position.copy(cur);
            if(cur.isDirective()){
                cur.next();
                boolean flag = true;
                while (!cur.EOF() && cur.isLetterOrDigit()){
                    flag &= cur.isUpperCase();
                    cur.next();
                }
                if(flag){
                    return new Token(DomainTag.DIRECTIVE, start, cur, start.getValue(cur));
                }
                else {
                    compiler.addMessage(true, start, "BAD DIRECTIVE");
                    return new Token(DomainTag.ERROR, start, cur, start.getValue(cur));
                }
            }
            else if(cur.isUpperCase()){
                cur.next();
                boolean flag = true;
                while (!cur.EOF() && !cur.isWhiteSpace()){
                    flag &= (cur.isLetterOrDigit() || cur.isHyphen());
                    cur.next();
                }
                if(flag){
                    return new Token(DomainTag.IDENT, start, cur, start.getValue(cur));
                }
                else {
                    compiler.addMessage(true, start, "BAD IDENT");
                    return new Token(DomainTag.ERROR, start, cur, start.getValue(cur));
                }
            }
            else
            {
                while (!cur.EOF() && !cur.isWhiteSpace()){
                    cur.next();
                }
                compiler.addMessage(true, start, "ERROR");
                return new Token(DomainTag.ERROR, start, cur, start.getValue(cur));
            }
        }
        return new Token(DomainTag.EOF, cur, cur, "");
    }

    Scanner(String program, Compiler compiler){
        this.compiler = compiler;
        cur = new Position(program);
        comments = new ArrayList<>();
    }
}

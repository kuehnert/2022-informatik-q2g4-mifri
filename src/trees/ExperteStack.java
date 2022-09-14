package trees;

import java.util.Stack;

public class ExperteStack extends Expertensystem {
    public ExperteStack(String input) {
        fromString(input);
    }

    public void fromString(String input) {
        String currentToken = "";
        Stack<Frage> stapel = new Stack<>();
        wurzel = new Frage("");
        stapel.push(wurzel);

        for (int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);

            if (c == '<') {
                stapel.peek().inhalt = currentToken;
                currentToken = "";

                var neueFrage = new Frage("");
                stapel.peek().ja = neueFrage;
                stapel.push(neueFrage);
            } else if (c == '[') {
                var neueFrage = new Frage("");
                stapel.peek().nein = neueFrage;
                stapel.push(neueFrage);
            } else if (c == '>' || c == ']') {
                if (!currentToken.equals("")) {
                    stapel.peek().inhalt = currentToken;
                    currentToken = "";
                }

                stapel.pop();
            } else {
                currentToken += c;
            }
        }
    }

    public static void main(String[] args) {
        var e = new Expertensystem();
        var text = e.exportieren();
        System.out.println(text);

        var es = new ExperteStack(text);
        System.out.println(es.exportieren());
    }
}

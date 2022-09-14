package trees;

public class ExperteMarius extends Expertensystem {
    private int offset = 0; // offset

    public ExperteMarius(String inputStr) {
        wurzel = importieren(inputStr);
    }

    private Frage importieren(String input) {
        int startIndex = 0; // offset2
        Frage ja = null;
        Frage nein = null;
        // schleife geht durch den kompletten String durch
        for (int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);
            // Sonderfaelle: Anfang eines neuen Knoten ('<' oder '['), oder
            // Ende ('>' oder
            // ']')
            if (c == '<') {
                // Rekursiver aufruf des "inneren Knotens" + bis zum Ende
                ja = importieren(input.substring(i + 1));
                // Setzte startIndex, damit wir im "dritten Fall" (der Frage)
                // einen
                // Sprung im String machen koennen
                startIndex += offset + 1;
                // Setze i auf den zuvor definierten offset, um den Sprung im
                // String im Parent
                // zur Frage zu machen
                i += offset;
            } else if (c == '>') {
                // Setzte den offset + 1, da wir die Klammer auch mitspringen
                // müssen.
                offset = i + 1;
                // return eine neue Frage. Im 1. und 2. Fall der Blaetter mit
                // einem Inhalt von
                // einem Substring von 0 bis i, im 3. Fall von dem offset
                // (startIndex) bis i.
                // Die Kinder "ja", "nein" werden mit angehangem, auch wenn
                // sie null sind.
                return new Frage(input.substring(startIndex, i), ja, nein);
            } else if (c == '[') {
                nein = importieren(input.substring(i + 1));
                startIndex += offset + 1;
                i += offset;
            } else if (c == ']') {
                offset = i + 1;
                return new Frage(input.substring(startIndex, i), ja, nein);
            }
        }
        // Keine Funktion, nur zur vermeidung eines Fehlers
        return null;
    }

    public static void main(String[] args) {
        Expertensystem es = new Expertensystem();
        String in = es.exportieren();
        System.out.println(in);
        System.out.println("Eingabe: "+ in);
        System.out.println("-------------------------------");

        ExperteMarius em = new ExperteMarius(in);
        System.out.println("Ausgabe: "+ em.exportieren());
    }
}

package trees;

import java.io.Serializable;

class Frage implements Serializable {
    String inhalt;
    Frage ja;
    Frage nein;

    public Frage(String inhalt) {
        this.inhalt = inhalt;
    }

    public Frage(String inhalt, Frage ja, Frage nein) {
        this.inhalt = inhalt;
        this.ja = ja;
        this.nein = nein;
    }

    public boolean istBlatt() {
        return ja == null; // && nein == null; -> unnoetig
    }

    public String exportieren() {
        String out = "";

        if (ja != null) {
            out += "<" + ja.exportieren() + ">";
        }

        if (nein != null) {
            out += "[" + nein.exportieren() + "]";
        }

        out += inhalt;

        return out;
    }
}

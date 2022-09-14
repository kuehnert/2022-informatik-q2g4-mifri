package trees;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Comparator;
import java.util.Objects;

public class ExperteFinn extends Expertensystem {
    public ExperteFinn() {
        super();
    }

    public ExperteFinn(boolean load) {
        if (load) {loadFileSystem();}
    }

    private void createFolder(String path) throws IOException {
        new File(path + File.separator + "ja").mkdir();
        new File(path + File.separator + "nein").mkdir();
    }

    private void saveFileSystem(String path, Frage frage) throws IOException {
        if (frage.istBlatt()) {
            File f = new File(path + File.separator + frage.inhalt);
            f.createNewFile();
            return;
        }
        String newFolder = path + "/" + frage.inhalt;
        File f = new File(newFolder);
        f.mkdir();
        createFolder(newFolder);
        saveFileSystem(path + File.separator + frage.inhalt + File.separator + "nein", frage.nein);
        saveFileSystem(path + File.separator + frage.inhalt + File.separator + "ja", frage.ja);
    }

    public void saveFileSystem() throws IOException {
        String base = System.getProperty("user.dir");
        var storePath = base + File.separator + "store";
        try (var dirStream = Files.walk(Paths.get(storePath))) {
            dirStream.map(Path::toFile).sorted(Comparator.reverseOrder()).forEach(File::delete);
        } catch (IOException ignored) {}
        new File(storePath).mkdir();
        saveFileSystem(storePath, wurzel);
    }

    private Frage loadFileSystem(String s) {
        return loadFileSystem(Path.of(s));
    }

    public void loadFileSystem() {
        wurzel =
                loadFileSystem(System.getProperty("user.dir") + File.separator + "store");
    }

    public Frage loadFileSystem(Path path) {
        File f = new File(path.toUri());
        File content = Objects.requireNonNull(f.listFiles())[0];
        if (content.isFile()) {
            return new Frage(content.getName(), null, null);
        }
        String newPath = content.toString();
        return new Frage(content.getName(),
                loadFileSystem(newPath + File.separator + "ja"),
                loadFileSystem(newPath + File.separator + "nein"));
    }

    public static void main(String[] args) {
        // try {
        //     ExperteFinn e = new ExperteFinn();
        //     e.spiele();
        //     e.saveFileSystem();
        // } catch (IOException e) {
        //     System.out.println("IO Error");
        // }
        ExperteFinn ef = new ExperteFinn(true);
        System.out.println(ef.exportieren());
    }
}

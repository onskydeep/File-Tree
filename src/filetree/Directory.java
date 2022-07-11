package filetree;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

public class Directory extends File {

    private final List<File> files;

    public Directory(Path path, List<File> files) {
        super(path);
        this.files = files;
    }

    @Override
    public Iterator<File> iterator() {
        Queue<File> queue = new LinkedList<>();
        queue.add(this);
        return new Iterator<File>() {
            @Override
            public boolean hasNext() {
                return !queue.isEmpty();
            }

            @Override
            public File next() {
                if (!hasNext()) if (!hasNext()) throw new NoSuchElementException("There is no next element!");
                if (queue.peek().isRegularFile()) {
                    File returnedvalue = queue.peek();
                    queue.remove();
                    return returnedvalue;
                } else {
                    Directory removed = (Directory) queue.peek();
                    queue.remove();
                    for (int i = 0; i < removed.files.size(); i++)
                        queue.add(removed.files.get(i));
                    return removed;

                }

            }

        };
    }

    @Override
    public int getHeight() {
        int ans = 0;
        for (File children : files) {
            if (ans < children.getHeight() + 1) ans = children.getHeight() + 1;
        }
        return ans;
    }

    @Override
    public boolean isRegularFile() {
        return false;
    }

    public List<File> getFiles() {
        return files;
    }

}

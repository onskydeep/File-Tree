package filetree;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.stream.*;

public class FileUtil {

	public static File toFileRepresentation(Path path) throws IOException {

		if (Files.isRegularFile(path)) {
			return new RegularFile(path);
		}

		else if(Files.isDirectory(path)){
			List<Path> pathsList = Files.list(path).collect(Collectors.toList());
			List<File> filesList = new LinkedList<>();
			for (Path currentpath : pathsList) {
				filesList.add(toFileRepresentation(currentpath));
			}
			return new Directory(path, filesList);
		} else throw new IOException("Unknown situation!");


	}

}

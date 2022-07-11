package filetree;

import java.nio.file.Path;
import java.util.*;

public class RegularFile extends File {

	public RegularFile(Path path) {
		super(path);
	}

	@Override
	public Iterator<File> iterator() {
		RegularFile temp = this;
		return new Iterator<File>() {
			boolean isevercalled = false;
			@Override
			public boolean hasNext() {
				return !isevercalled;
			}
			@Override
			public File next() {
				if(!hasNext()) throw new NoSuchElementException("There is no next element!");
				isevercalled=true;
				return temp;
			}
		};
	}

	@Override
	public int getHeight() {
		return 0;
	}

	@Override
	public boolean isRegularFile() {
		return true;
	}

}

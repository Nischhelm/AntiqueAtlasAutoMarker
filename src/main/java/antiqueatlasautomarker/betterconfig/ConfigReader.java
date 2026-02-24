package antiqueatlasautomarker.betterconfig;

import javax.annotation.Nullable;
import java.io.BufferedReader;
import java.io.EOFException;
import java.io.IOException;
import java.util.function.Predicate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ConfigReader implements AutoCloseable {

	private final BufferedReader reader;
	private String currentLine;

	public ConfigReader(BufferedReader reader) {
		this.reader = reader;
	}

	public String peekLine() throws IOException {
		if (this.currentLine == null) {
			this.currentLine = this.nextLine();
		}
		return this.currentLine;
	}

	public String readLine() throws IOException {
		String line = this.peekLine();
		this.currentLine = null;
		return line;
	}

	private String nextLine() throws IOException {
		String line;
		do {
			line = this.reader.readLine();
			if (line == null) {
				throw new EOFException();
			}
			line = line.trim();
		} while (line.isEmpty() || line.startsWith("#") || line.startsWith("~"));
		return line;
	}

	public boolean readLineIfEqual(String s) throws IOException {
		return this.readLineIfMatching(s::equals);
	}

	public boolean readLineIfMatching(Predicate<String> predicate) throws IOException {
		if (!predicate.test(this.peekLine())) {
			return false;
		}
		this.readLine();
		return true;
	}

	@Nullable
	public Matcher readMatching(Pattern pattern) throws IOException {
		Matcher matcher = pattern.matcher(this.peekLine());
		if (matcher.find()) {
			this.currentLine = this.currentLine.substring(matcher.end());
			return matcher;
		}
		return null;
	}

	public boolean hasNext() throws IOException {
		try {
			this.peekLine();
			return true;
		} catch (EOFException e) {
			return false;
		}
	}

	@Override
	public void close() throws IOException {
		this.reader.close();
	}

}

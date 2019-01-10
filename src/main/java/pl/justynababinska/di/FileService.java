package pl.justynababinska.di;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import pl.justynababinska.service.CipherService;

@Service
public class FileService {
	
	
	private String fileName;
	private CipherService code;
	
	@Autowired
	public FileService(@Value("${filename}") String fileName, CipherService code) {
		this.fileName = fileName;
		this.code = code;

	}
	
	List<Entry> readAllFile() throws IOException {
		return Files.readAllLines(Paths.get(fileName)).stream().map(code::decrypt).map(CsvEntryConverter::parse)
				.collect(Collectors.toList());
	}

	void saveEntries(List<Entry> entries) throws IOException {
		BufferedWriter writer = new BufferedWriter(new FileWriter(fileName));
		for (Entry entry : entries) {
			writer.write(code.encrypt(entry.toString()));
			writer.newLine();
		}
		writer.close();
	}

	private static class CsvEntryConverter {
		static Entry parse(String text) {
			String[] split = text.split(";");
			return new Entry(split[0], split[1]);
		}
	}
}
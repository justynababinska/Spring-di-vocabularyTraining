package pl.justynababinska.di;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import pl.justynababinska.formatter.TextFormatter;

@Component
public class ConsoleOutputWriter {

	private TextFormatter textFormatter;
	
	@Autowired
	public ConsoleOutputWriter( TextFormatter textFormatter) {
		this.textFormatter = textFormatter;
	}
	
	public void printText(String text) {
		System.out.println(textFormatter.formatter(text));
	}
	
}

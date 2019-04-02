package pl.justynababinska.formatter;

import org.springframework.stereotype.Component;

@Component
public class UpperCaseTextFormatter implements TextFormatter{

	@Override
	public String formatter(String text) {
		return text.toUpperCase();
	}

}

package pl.justynababinska.formatter;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component
@Primary
public class BasicTextFormatter implements TextFormatter{

	@Override
	public String formatter(String text) {
		return text;
	}



}

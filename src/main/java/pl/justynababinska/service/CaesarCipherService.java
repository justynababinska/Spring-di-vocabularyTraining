package pl.justynababinska.service;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Service
@Profile("prod")
public class CaesarCipherService implements CipherService {

	private static final int SHIFT = 4;

	@Override
	public String encrypt(String text) {
		return text.chars().map(CaesarCipherService::shift)
				.collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append).toString();
	}

	@Override
	public String decrypt(String text) {
		return text.chars().map(CaesarCipherService::shiftBack)
				.collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append).toString();
	}

	private static int shift(int character) {
		return character + SHIFT;
	}

	private static int shiftBack(int character) {
		return character - SHIFT;
	}
}

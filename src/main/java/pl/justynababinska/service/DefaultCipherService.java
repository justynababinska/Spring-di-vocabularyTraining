package pl.justynababinska.service;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Service
@Profile("dev")
public class DefaultCipherService implements CipherService {

	@Override
	public String encrypt(String text) {
		return text;
	}

	@Override
	public String decrypt(String text) {
		return text;
	}

	
}

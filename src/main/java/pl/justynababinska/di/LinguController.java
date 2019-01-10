package pl.justynababinska.di;

import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class LinguController {
	 private static final int UNDEFINED = -1;
	    private static final int ADD_ENTRY = 0;
	    private static final int TEST = 1;
	    private static final int CLOSE_APP = 2;

	    private EntryRepository entryRepository;
	    private FileService fileService;
	    private Scanner scanner;
	    private ConsoleOutputWriter consoleOutputWriter;

	    @Autowired
	    public LinguController(EntryRepository entryRepository, FileService fileService, Scanner scanner, ConsoleOutputWriter consoleOutputWriter) {
	    	this.entryRepository = entryRepository;
		    this.fileService = fileService;
		    this.scanner = scanner;
		    this.consoleOutputWriter = consoleOutputWriter;
	    }
	    
	    public void mainLoop() {
	        consoleOutputWriter.printText("Witaj w aplikacji LinguApp");
	        int option = UNDEFINED;
	        while(option != CLOSE_APP) {
	            printMenu();
	            option = chooseOption();
	            executeOption(option);
	        }
	    }

	    private void executeOption(int option) {
	        switch (option) {
	            case ADD_ENTRY:
	                addEntry();
	                break;
	            case TEST:
	                test();
	                break;
	            case CLOSE_APP:
	                close();
	                break;
	            default:
	            	consoleOutputWriter.printText("Opcja niezdefiniowana");
	        }
	    }

	    private void test() {
	        if(entryRepository.isEmpty()) {
	        	consoleOutputWriter.printText("Dodaj przynajmniej jedną frazę do bazy.");
	            return;
	        }
	        final int testSize = entryRepository.size() > 10? 10 : entryRepository.size();
	        Set<Entry> randomEntries = entryRepository.getRandomEntries(testSize);
	        int score = 0;
	        for (Entry entry : randomEntries) {
	        	consoleOutputWriter.printText(String.format("Podaj tłumaczenie dla :\"%s\"\n", entry.getOriginal()));
	            String translation = scanner.nextLine();
	            if(entry.getTranslation().equalsIgnoreCase(translation)) {
	            	consoleOutputWriter.printText("Odpowiedź poprawna");
	                score++;
	            } else {
	            	consoleOutputWriter.printText("Odpowiedź niepoprawna - " + entry.getTranslation());
	            }
	        }
	        consoleOutputWriter.printText(String.format("Twój wynik: %d/%d\n", score, testSize));
	    }

	    private void addEntry() {
	    	consoleOutputWriter.printText("Podaj oryginalną frazę");
	        String original = scanner.nextLine();
	        consoleOutputWriter.printText("Podaj tłumaczenie");
	        String translation = scanner.nextLine();
	        Entry entry = new Entry(original, translation);
	        entryRepository.add(entry);
	    }

	    private void close() {
	        try {
	            fileService.saveEntries(entryRepository.getAll());
	            consoleOutputWriter.printText("Zapisano stan aplikacji");
	        } catch (IOException e) {
	        	consoleOutputWriter.printText("Nie udało się zapisać zmian");
	        }
	        consoleOutputWriter.printText("Bye Bye!");
	    }

	    private void printMenu() {
	    	consoleOutputWriter.printText("Wybierz opcję:");
	    	consoleOutputWriter.printText("0 - Dodaj frazę");
	    	consoleOutputWriter.printText("1 - Test");
	    	consoleOutputWriter.printText("2 - Koniec programu");
	    }

	    private int chooseOption() {
	        int option;
	        try {
	            option = scanner.nextInt();
	        } catch(InputMismatchException e) {
	            option = UNDEFINED;
	        } finally {
	            scanner.nextLine();
	        }
	        if(option > UNDEFINED && option <= CLOSE_APP)
	            return option;
	        else
	            return UNDEFINED;
	    }
	}
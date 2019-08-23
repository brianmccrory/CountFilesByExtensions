import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;

public class CountFiles {

	public static String getExtension(Path path) {
		String f = path.getFileName().toString();
		if (f.lastIndexOf('.') < 0) {
			//System.out.println("Warning: no filename extension: " + f);
			return "none";
		} 
		return f.substring(f.lastIndexOf('.')+1).toUpperCase();
	}
	
 	public static void main(String[] args) {
 		
 		String inputFile = "input.txt";
 		if (args.length> 0) {
 			inputFile = args[0];
 		}
 				
 		List<Path> paths = new ArrayList<>();
 		try (Scanner scanner = new Scanner(new File(inputFile))) {
 			while (scanner.hasNextLine()) {
 				String line = scanner.nextLine();
 				line.trim();
 				if (!line.isEmpty() && !line.startsWith("#"))
 						paths.add(Paths.get(line));
 			}
 		} catch (IOException e) {
 			System.out.println("Could not open path: " + e);
 		}
 		for (Path path : paths) {
 			System.out.println("\nPath: " + path);
			try {
				Map<String, List<Path>> map = Files.walk(path).filter(p->!Files.isDirectory(p)).collect(Collectors.groupingBy(CountFiles::getExtension));
		
				// Map<String, List<Path>> map = Files.walk(path).filter(p->!Files.isDirectory(p) && Files.isReadable(p) && Files.isRegularFile(p)).collect(Collectors.groupingBy(CountFiles::getExtension));
				int totalCount = 0;
				for (String k : map.keySet()) {
					int size = map.get(k).size();
					System.out.println(" * " + k + ": " + size);
					totalCount += size;
				}
				System.out.println("Total: " + totalCount);
			} catch (IOException e) {
				System.out.println("Could not walk path: " + e);
			}		
 		}
	}
}

package ty;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.FileVisitor;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;


public class fileVisitor {
	static DbWriter db = new DbWriter();
	
	 void visit(Path path) {
		FileVisitor<Path> filevisit = new FileVisitor<Path>() {

			@Override
			public FileVisitResult visitFileFailed(Path file, IOException exc)
					throws IOException {
				// TODO Auto-generated method stub
				return FileVisitResult.CONTINUE;
			}
			
			@Override
			public FileVisitResult visitFile(Path file, BasicFileAttributes attrs)
					throws IOException {
				if(file.getFileName().toFile().toString().endsWith(".mp3")){
					//transfer to db code here
					db.write(file.toAbsolutePath().toString());
				}
				return FileVisitResult.CONTINUE;
			}
			
			@Override
			public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs)
					throws IOException {
				// TODO Auto-generated method stub
				return FileVisitResult.CONTINUE;
			}
			
			@Override
			public FileVisitResult postVisitDirectory(Path dir, IOException exc)
					throws IOException {
				return FileVisitResult.CONTINUE;
			}
		};
		try {
			Files.walkFileTree(path, filevisit);
		} catch (IOException e) {
			// TODO Auto-generated catch block
		}
	}

}

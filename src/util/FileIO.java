package util;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import classes.Maze;

public class FileIO {
	//Write all the utilities of all existing states to a file
	public static void writeUtility(List<Maze> memory, String filename) {
		StringBuilder sb = new StringBuilder();
		
		for(int row = 0; row < Constant.ROWS; row++) {
			for(int col =0; col < Constant.COLS; col++) {
				sb.append(String.format("(%1d;%1d)",row,col));
				for(Maze mz: memory) {
					sb.append(",");
					sb.append(mz.getMaze()[row][col].getUtility());
				}
				sb.append("\n");
			}
		}	
		writeToFile(sb.toString(),filename);
	}
	
    public static void writeToFile(String content, String filename) {
        try {
            FileWriter fw = new FileWriter(new File(filename), false);
            fw.write(content);
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
}
}

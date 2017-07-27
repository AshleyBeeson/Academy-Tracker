package dto;

import java.io.*;

public class TestFileDto {

    private final String filename;

    public TestFileDto(String filename){
        this.filename = filename;
    }


    public String readFile(){
        FileReader fileReader;
        BufferedReader bufferedReader = null;
        PrintWriter writer = null;
        StringBuilder builder = new StringBuilder();
        try {
            File f = new File(filename);
            if (!f.exists()) {
                f.createNewFile();
                writer = new PrintWriter(new FileWriter(f));
                writer.println(0);
            }
            if (writer != null) {
                writer.close();
            }

            fileReader = new FileReader(f);
            bufferedReader = new BufferedReader(fileReader);
            String initial = bufferedReader.readLine();
            builder.append(initial);
        } catch (Exception ex) {
            if (writer != null) {
                writer.close();
            }
        }
        if (bufferedReader != null) {
            try {
                bufferedReader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return builder.toString();
    }


    public void save(String text) throws Exception {
        FileWriter fileWriter;
        PrintWriter printWriter;
        fileWriter = new FileWriter(filename);
        printWriter = new PrintWriter(fileWriter);
        printWriter.println(text);

        // make sure to close the file
        if (printWriter != null) {
            printWriter.close();
        }
    }

}

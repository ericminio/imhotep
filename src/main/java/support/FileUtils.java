package support;

import java.io.*;
import java.nio.charset.Charset;

public class FileUtils {

    public static void createOrCleanDirectory(String path) {
        File dir = createDirectoryIfNotExists( path );
        for(File file:dir.listFiles()) {
            file.delete();
        }
    }

    public static File createDirectoryIfNotExists(String path) {
        File dir = new File( path );
        if (!dir.exists()) {
            dir.mkdirs();
        }
        return dir;
    }

    public static void writeStringToFile(String content, String path) throws IOException {
        PrintWriter writer = new PrintWriter( new File( path ), "UTF-8" );
        writer.write( content );
        writer.close();
    }

    public static String readFileToString(File file) throws IOException {
        String content = "";
        String line = "" ;
        BufferedReader reader = new BufferedReader( new InputStreamReader(new FileInputStream( file ), Charset.forName( "UTF-8" )) );
        while ( (line = reader.readLine()) != null ){
            if (content != "") { content += "\n"; }
            content += line;
        }
        return content;
    }
}

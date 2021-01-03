package asim.utility;

import java.net.URI;
import java.nio.file.*;
import java.util.Map;
import java.util.HashMap;
import java.io.IOException;
import java.net.URISyntaxException;

public class AppUtility {

    public static synchronized Path getResourcePath(URI uri) throws
            IllegalArgumentException, FileSystemNotFoundException, SecurityException, URISyntaxException, IOException {

        Map<String, String> env = new HashMap<>();
        env.put("create", "true");
        FileSystems.newFileSystem(uri, env);
        return Paths.get(uri);
    }

}

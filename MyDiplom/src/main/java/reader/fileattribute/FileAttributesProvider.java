package reader.fileattribute;

import java.util.Map;

public interface FileAttributesProvider {
    String getFileHeader();
    Map<String, Object> getFileAttributes();
}

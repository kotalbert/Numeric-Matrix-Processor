package processor.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Helper class for making copies of input stream.
 * <p>
 * Makes a copy od input stream as ByteArray and returns copied bytes as input
 * stream to be processed.
 * Rationale behind this is stream can be read only once nad if need be to
 * reuse same stream, rather than re creating the stream again, copies are used.
 */
public class InputStreamCopier {

    private final ByteArrayOutputStream byteArrayOutputStream;

    public InputStreamCopier(InputStream inputStream) throws IOException {
        byteArrayOutputStream = new ByteArrayOutputStream();
        inputStream.transferTo(byteArrayOutputStream);
    }

    /**
     * Copy input stream, that was passed to constructor.
     *
     * @return input stream copy
     */
    public InputStream getStreamCopy() {
        return new ByteArrayInputStream(byteArrayOutputStream.toByteArray());
    }
}

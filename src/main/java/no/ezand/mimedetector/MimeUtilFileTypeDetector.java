package no.ezand.mimedetector;

import eu.medsea.mimeutil.MimeType;
import eu.medsea.mimeutil.MimeUtil2;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.spi.FileTypeDetector;
import java.util.Collection;

/**
 * @author Eirik Stenersen Sand
 */
public class MimeUtilFileTypeDetector extends FileTypeDetector {

    private MimeUtil2 mimeUtil;

    public MimeUtilFileTypeDetector() {
        mimeUtil = new MimeUtil2();
        mimeUtil.registerMimeDetector("eu.medsea.mimeutil.detector.MagicMimeMimeDetector");
        mimeUtil.registerMimeDetector("eu.medsea.mimeutil.detector.ExtensionMimeDetector");
        mimeUtil.registerMimeDetector("eu.medsea.mimeutil.detector.OpendesktopMimeDetector");
        mimeUtil.registerMimeDetector("eu.medsea.mimeutil.detector.WindowsRegistryMimeDetector");
    }

    @SuppressWarnings("unchecked")
    @Override
    public String probeContentType(Path path) throws IOException {
        Collection<MimeType> mimeTypes = mimeUtil.getMimeTypes(path.toUri().toURL());
        if (null == mimeTypes || mimeTypes.size() <= 0) {
            return null;
        }

        MimeType mimeType = mimeTypes.iterator().next();
        return String.format("%s/%s", mimeType.getMediaType(), mimeType.getSubType());
    }
}
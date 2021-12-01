package dev.chalmers.photoshare;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class ImageService {
    private final ResourceLoader resourceLoader;

    @Autowired
    public ImageService(ResourceLoader loader) {
        this.resourceLoader = loader;
    }

    public byte[] findPhotoById(String photoId) {
        try {
            String location = "classpath:images/" + photoId + ".jpeg";
            Resource resource = resourceLoader.getResource(location);

            if (resource != null) {
                return IOUtils.toByteArray(resource.getInputStream());
            }
        } catch (IOException ex) {
            // empty by design
        }

        return null;
    }
}

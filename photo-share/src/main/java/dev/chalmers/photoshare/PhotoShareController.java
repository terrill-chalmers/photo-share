package dev.chalmers.photoshare;

import dev.chalmers.photoshare.domain.Photo;
import org.apache.commons.io.IOUtils;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

@RestController
public class PhotoShareController {
    @PostMapping(value="/find", consumes= MediaType.APPLICATION_JSON_VALUE, produces=MediaType.IMAGE_JPEG_VALUE)
    public @ResponseBody byte[] findPhotoById(@RequestBody Photo photo) {
        // TODO: prevent SQL injection and cross side scripting
        try {
            String id = photo.getId();
            InputStream in = new FileInputStream("/Users/tb0t/code/photo-share/photo-share/src/main/resources/images/" + id + ".jpeg");
            return IOUtils.toByteArray(in);
        } catch (IOException ex) {
            System.out.println(ex);
            return null;
        }
    }

}

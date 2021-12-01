package dev.chalmers.photoshare;

import dev.chalmers.photoshare.domain.Photo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PhotoShareController {
    private final IdValidator idValidator;
    private final ImageService photoService;

    @Autowired
    public PhotoShareController(IdValidator idValidator, ImageService photoService) {
        this.idValidator = idValidator;
        this.photoService = photoService;
    }

    @PostMapping(value = "/find", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.IMAGE_JPEG_VALUE)
    public @ResponseBody
    byte[] findPhotoById(@RequestBody Photo photo) {
        String photoId = photo.getId();
        if (idValidator.isValidateUuid(photoId)) {
            return photoService.findPhotoById(photoId);
        }

        return null;
    }
}

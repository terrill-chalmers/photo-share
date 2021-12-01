package dev.chalmers.photoshare;

import dev.chalmers.photoshare.domain.Photo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import java.nio.charset.StandardCharsets;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.nullValue;
import static org.mockito.Mockito.*;

@SpringBootTest
@AutoConfigureMockMvc
class PhotoShareControllerTest {
    private IdValidator mockIdValidator;
    private ImageService mockImageService;
    private PhotoShareController controller;

    @Autowired
    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        this.mockIdValidator = mock(IdValidator.class);
        this.mockImageService = mock(ImageService.class);
        controller = new PhotoShareController(mockIdValidator, mockImageService);
    }

    @Test
    void findPhotoById_hasValidId_findsPhoto() {
        when(mockIdValidator.isValidateUuid(anyString())).thenReturn(true);
        byte[] expectedByteArray = "found".getBytes(StandardCharsets.UTF_8);
        when(mockImageService.findPhotoById(anyString())).thenReturn(expectedByteArray);

        String testPhotoId = "faa68eba-0e27-478c-b5a1-f5501dfd408c";
        byte[] resultByteArray = controller.findPhotoById(Photo.Builder.builder()
                .id(testPhotoId)
                .build());

        assertThat(resultByteArray, is(expectedByteArray));
        verify(mockIdValidator).isValidateUuid(testPhotoId);
        verify(mockImageService).findPhotoById(testPhotoId);
    }

    @Test
    void findPhotoById_hasValidId_doesNotFindPhoto() {
        when(mockIdValidator.isValidateUuid(anyString())).thenReturn(true);
        when(mockImageService.findPhotoById(anyString())).thenReturn(null);

        String testPhotoId = "faa68eba-0e27-478c-b5a1-f5501dfd408c";
        byte[] resultByteArray = controller.findPhotoById(Photo.Builder.builder()
                .id(testPhotoId)
                .build());

        assertThat(resultByteArray, is(nullValue()));
        verify(mockIdValidator).isValidateUuid(testPhotoId);
        verify(mockImageService).findPhotoById(testPhotoId);
    }

    @Test
    void findPhotoById_hasInvalidId() {
        when(mockIdValidator.isValidateUuid(anyString())).thenReturn(false);

        String testPhotoId = "bad-id";
        byte[] photoByteArray = controller.findPhotoById(Photo.Builder.builder()
                .id(testPhotoId)
                .build());

        assertThat(photoByteArray, is(nullValue()));
        verify(mockIdValidator).isValidateUuid(testPhotoId);
        verify(mockImageService, times(0)).findPhotoById(testPhotoId);
    }
}
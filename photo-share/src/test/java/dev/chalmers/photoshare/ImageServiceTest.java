package dev.chalmers.photoshare;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.nullValue;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ImageServiceTest {
    @InjectMocks
    private ImageService service;

    @Mock
    private DefaultResourceLoader mockResourceLoader;

    @Test
    void findPhotoById_findsResource() {
        String testId = "faa68eba-0e27-478c-b5a1-f5501dfd408c";
        InputStream is = new ByteArrayInputStream(testId.getBytes());
        Resource resource = new InputStreamResource(is);
        when(mockResourceLoader.getResource(anyString())).thenReturn(resource);
        byte[] expected = testId.getBytes();

        byte[] result = service.findPhotoById(testId);
        assertThat(result, is(expected));
    }

    @Test
    void findPhotoById_doesNotFindResource() {
        byte[] result = service.findPhotoById("testId");
        assertThat(result, is(nullValue()));
    }
}
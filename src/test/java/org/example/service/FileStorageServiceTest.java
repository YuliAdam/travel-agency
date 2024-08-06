package org.example.service;

import org.example.config.FileStorageProperties;
import org.example.repository.HotelRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
class FileStorageServiceTest {

    FileStorageService fileStorageService;
    @Mock
    HotelRepository hotelRepository;
    @Mock
    FileStorageProperties fileStorageProperties;

    @BeforeEach
    void setUp() {
        this.fileStorageService = new FileStorageService(hotelRepository, fileStorageProperties);
    }

    @Test
    void getImageInUrls() {
        List<String> expectedResult = List.of("/img/1/one", "/img/1/two");
        when(hotelRepository.findGallery(1l)).thenReturn("one,two");
        List<String> actualResult = fileStorageService.getImage(1l);
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void getOneImageInUrls() {
        List<String> expectedResult = List.of("/img/1/one");
        when(hotelRepository.findGallery(1l)).thenReturn("one");
        List<String> actualResult = fileStorageService.getImage(1l);
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void getNullImageInUrls() {
        when(hotelRepository.findGallery(1l)).thenReturn(null);
        List<String> actualResult = fileStorageService.getImage(1l);
        assertTrue(actualResult.isEmpty());
    }
}
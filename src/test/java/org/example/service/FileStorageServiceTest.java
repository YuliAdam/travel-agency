package org.example.service;

import org.assertj.core.api.Assert;
import org.example.config.FileStorageProperties;
import org.example.entity.Hotel;
import org.example.repository.HotelRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.multipart.MultipartFile;

import java.lang.reflect.Array;
import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
class FileStorageServiceTest {

    FileStorageService fileStorageService;
    @Mock
    HotelRepository hotelRepository;
    @Mock
    FileStorageProperties fileStorageProperties;
    @Mock
    MultipartFile multipartFile;

    @BeforeEach
    void setUp() {
        this.fileStorageService= new FileStorageService(hotelRepository,fileStorageProperties);
    }

    @Test
    void getImageInUrls() {
        when(hotelRepository.findGallery(1l)).thenReturn("one,two");
        List<String> list= fileStorageService.getImage(1l);
        String urls= list.getFirst()+list.getLast();
        assertEquals(urls,"img/1/oneimg/1/two");
    }
}
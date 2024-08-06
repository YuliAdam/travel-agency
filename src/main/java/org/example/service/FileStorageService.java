package org.example.service;

import lombok.RequiredArgsConstructor;
import org.example.config.FileStorageProperties;
import org.example.entity.Hotel;
import org.example.exception.EntityNotExistException;
import org.example.exception.ErrorCode;
import org.example.exception.FileStorageException;
import org.example.utils.Messages;
import org.example.utils.StringConstants;
import org.example.repository.HotelRepository;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
public class FileStorageService {

    public static final String GALLERY_DIRECTOR_PART = "/img/";

    private final HotelRepository hotelRepository;
    private final FileStorageProperties fileStorageProperties;


    @Transactional
    public String storeFile(MultipartFile file, Long id) {
        // Create directories
        Path fileStorageLocation = Paths.get(fileStorageProperties.getUploadDir() + StringConstants.COMMA + id)
                .toAbsolutePath().normalize();
        try {
            Files.createDirectories(fileStorageLocation);
        } catch (Exception ex) {
            throw new FileStorageException(ErrorCode.DIRECTORY_NOT_CREATE.toString(), ex);
        }
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        try {
            // Check if the file's name contains invalid characters
            if (fileName.contains(StringConstants.DOT_DOT)) {
                throw new FileStorageException(ErrorCode.FILENAME_WITH_INVALID_PATH_SEQUENCE + fileName);
            }
            // Copy file to the target location (Replacing existing file with the same name)
            Path targetLocation = fileStorageLocation.resolve(fileName);
            Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);
            // Save filename in gallery in DB
            Hotel existingHotel = hotelRepository.findById(id).orElseThrow(() -> new EntityNotExistException(String.format(Messages.HOTEL_NOT_EXIST, id)));
            String gallery = existingHotel.getGallery();
            gallery = (gallery.isBlank()) ? fileName : (gallery + StringConstants.SLASH + fileName);
            existingHotel.setGallery(gallery);
            hotelRepository.save(existingHotel);
            return fileName;
        } catch (IOException ex) {
            throw new FileStorageException(fileName + ErrorCode.FILE_IS_NOT_STORE, ex);
        }
    }

    public List<String> getImage(Long id) {
        String urlsString = hotelRepository.findGallery(id);
        if (org.apache.commons.lang3.StringUtils.isNotEmpty(urlsString)) {
            return Arrays.stream(urlsString.split(StringConstants.COMMA))
                    .map(url -> GALLERY_DIRECTOR_PART + id + StringConstants.SLASH + url)
                    .toList();
        } else {
            return Collections.emptyList();
        }
    }

}
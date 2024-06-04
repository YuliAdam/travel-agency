package org.example.service;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.example.controller.request.HotelRequest;
import org.example.entity.Hotel;
import org.example.exception.FileStorageException;
import org.example.exception.MyFileNotFoundException;
import org.example.repository.HotelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Arrays;
import java.util.List;

import org.example.config.FileStorageProperties;

@Service
@RequiredArgsConstructor
public class FileStorageService {
    private final HotelRepository hotelRepository;
    private final FileStorageProperties fileStorageProperties;

    public String storeFile(MultipartFile file, Long id) {
        Path fileStorageLocation = Paths.get(fileStorageProperties.getUploadDir() + "/" + id)
                .toAbsolutePath().normalize();

        try {
            Files.createDirectories(fileStorageLocation);
        } catch (Exception ex) {
            throw new FileStorageException("Could not create the directory where the uploaded files will be stored.", ex);
        }
        // Normalize file name
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());

        try {
            // Check if the file's name contains invalid characters
            if (fileName.contains("..")) {
                throw new FileStorageException("Sorry! Filename contains invalid path sequence " + fileName);
            }
            // Copy file to the target location (Replacing existing file with the same name)
            Path targetLocation = fileStorageLocation.resolve(fileName);
            Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);
            Hotel existingHotel = hotelRepository.findById(id).get();
            String gallery = hotelRepository.findGallery(id);
            if (gallery == null || gallery.isEmpty() || gallery.isBlank()) {
                gallery = fileName;
            } else {
                gallery = gallery + "," + fileName;
            }
            existingHotel.setGallery(gallery);
            hotelRepository.save(existingHotel);
            return fileName;
        } catch (IOException ex) {
            throw new FileStorageException("Could not store file " + fileName + ". Please try again!", ex);
        }
    }

    public List<String> getImage(Long id) {
        String urlsString = hotelRepository.findGallery(id);
        if (!urlsString.equals(null) || !urlsString.isBlank()) {
            List<String> fileUrls = Arrays.stream(urlsString.split(",")).map(url -> "/img/" + id + "/" + url).toList();
            return fileUrls;
        } else {
            urlsString = "360_F_581847176_eF540XqFGHDdGPZxyh5NtWHNzgs0XFk6.jpg";
            List<String> fileUrls = Arrays.stream(urlsString.split(",")).map(url -> "/img/" + url).toList();
            return fileUrls;
        }
    }
}
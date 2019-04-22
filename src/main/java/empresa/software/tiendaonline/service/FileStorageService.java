/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package empresa.software.tiendaonline.service;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import empresa.software.tiendaonline.exception.FileStorageException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.io.FilenameUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author pedro
 */
@Service
public class FileStorageService {

    public String storeFile(MultipartFile file) {

        Cloudinary cloudinary = new Cloudinary(ObjectUtils.asMap(
                "cloud_name", "hv7wxttwe",
                "api_key", "274716381915121",
                "api_secret", "U_5QfOSihgcAT9k7K_0yOtePlug"));

        // Normalize file name
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());

        try {
            // Check if the file's name contains invalid characters
            if (fileName.contains("..")) {
                throw new FileStorageException("Sorry! Filename contains invalid path sequence " + fileName);
            }

            File convFile = convert(file);

            Map uploadResult = cloudinary.uploader().upload(convFile, ObjectUtils.emptyMap());

            return uploadResult.get("secure_url").toString();

        } catch (IOException ex) {
            throw new FileStorageException("Could not store file " + fileName + ". Please try again!", ex);
        }
    }

    public String storeFile(MultipartFile file, String urlLogo) {

        URL url = null; 
        try {
            url = new URL(urlLogo);
        } catch (MalformedURLException ex) {
            Logger.getLogger(FileStorageService.class.getName()).log(Level.SEVERE, null, ex);
        }
        String public_id = FilenameUtils.getBaseName(url.getPath());

        Cloudinary cloudinary = new Cloudinary(ObjectUtils.asMap(
                "cloud_name", "hv7wxttwe",
                "api_key", "274716381915121",
                "api_secret", "U_5QfOSihgcAT9k7K_0yOtePlug"));

        // Normalize file name
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());

        try {
            // Check if the file's name contains invalid characters
            if (fileName.contains("..")) {
                throw new FileStorageException("Sorry! Filename contains invalid path sequence " + fileName);
            }

            File convFile = convert(file);

            Map uploadResult = cloudinary.uploader().upload(convFile, ObjectUtils.emptyMap());

            cloudinary.uploader().destroy(public_id, ObjectUtils.emptyMap());


            
            return uploadResult.get("secure_url").toString();

        } catch (IOException ex) {
            throw new FileStorageException("Could not store file " + fileName + ". Please try again!", ex);
        }
    }

    public File convert(MultipartFile file) throws FileNotFoundException, IOException {
        File convFile = new File(file.getOriginalFilename());
        convFile.createNewFile();
        FileOutputStream fos = new FileOutputStream(convFile);
        fos.write(file.getBytes());
        fos.close();
        return convFile;
    }

}

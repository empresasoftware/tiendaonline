/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package empresa.software.tiendaonline.controller;

/**
 *
 * @author pedro
 */

import com.cloudinary.*;
import empresa.software.tiendaonline.model.Vendedor;
import empresa.software.tiendaonline.payload.ApiResponse;
import empresa.software.tiendaonline.repository.UserRepository;
import empresa.software.tiendaonline.repository.VendedorRepository;
import empresa.software.tiendaonline.security.CurrentUser;
import empresa.software.tiendaonline.security.UserPrincipal;
import empresa.software.tiendaonline.service.FileStorageService;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/images")
public class ImagenController {
    
    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    FileStorageService fileStorageService;
    
    @Autowired
    private VendedorRepository vendedorRepository;

    @Secured({"ROLE_USER"})
    @PostMapping("/uploadFoto")
    public String uploadFile(@CurrentUser UserPrincipal userprincipal,
            @RequestParam("file") MultipartFile file) {
        String UrlFoto = fileStorageService.storeFile(file);
        return UrlFoto;
    }
    
    @Secured({"ROLE_SHOP"})
    @PutMapping("/logoTienda")
    public ResponseEntity<?> uploadLogoTienda(@CurrentUser UserPrincipal userprincipal,
            @RequestParam("file") MultipartFile file) {
        Vendedor vendedor = (Vendedor) userRepository.findById(userprincipal.getId()).get();
        String urlLogo = fileStorageService.storeFile(file);
        vendedor.getTienda().setLogo(urlLogo);
        vendedorRepository.save(vendedor);

        return ResponseEntity.ok().body(new ApiResponse(true, "Logo updated successfully"));
    }
}

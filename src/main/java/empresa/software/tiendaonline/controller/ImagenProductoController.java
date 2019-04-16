/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package empresa.software.tiendaonline.controller;

import empresa.software.tiendaonline.model.ImagenProducto;
import empresa.software.tiendaonline.model.Producto;
import empresa.software.tiendaonline.payload.ApiResponse;
import empresa.software.tiendaonline.repository.ProductoRepository;
import empresa.software.tiendaonline.repository.ImagenProductoRepository;
import empresa.software.tiendaonline.security.CurrentUser;
import empresa.software.tiendaonline.security.UserPrincipal;
import empresa.software.tiendaonline.service.FileStorageService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author pedro
 */
@RestController
@RequestMapping("/api/imagenes/productos")
public class ImagenProductoController {
    @Autowired
    ProductoRepository productoRepository;

    @Autowired
    ImagenProductoRepository imagenProductoRepository;
    
    @Autowired
    FileStorageService fileStorageService;
    
    @Secured({"ROLE_SHOP"})
    @PostMapping("/{id}")
    public ResponseEntity<?> newImagenProducto(@CurrentUser UserPrincipal userprincipal, @PathVariable Long id,
            @RequestParam("file") MultipartFile file) {
        Producto producto = productoRepository.findById(id).get();
        String urlImagenProducto = fileStorageService.storeFile(file);
        ImagenProducto imagenProducto = new ImagenProducto(urlImagenProducto, producto);             
        imagenProductoRepository.save(imagenProducto);
        return new ResponseEntity(new ApiResponse(true, "Imagen de Producto Ingresado"),
                HttpStatus.ACCEPTED);
    }
    
    @GetMapping("/{id}")
    public List<ImagenProducto> getImagenProductoAll(@CurrentUser UserPrincipal userprincipal, @PathVariable Long id) {
        Producto producto = productoRepository.findById(id).get();             
        return imagenProductoRepository.findByProducto(producto);
    }
}

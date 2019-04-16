/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package empresa.software.tiendaonline.controller;

import empresa.software.tiendaonline.model.Producto;
import empresa.software.tiendaonline.model.ProductoComentario;
import empresa.software.tiendaonline.model.Tienda;
import empresa.software.tiendaonline.model.Vendedor;
import empresa.software.tiendaonline.payload.ApiResponse;
import empresa.software.tiendaonline.payload.ProductoRequest;
import empresa.software.tiendaonline.repository.ProductoRepository;
import empresa.software.tiendaonline.repository.TiendaRepository;
import empresa.software.tiendaonline.repository.VendedorRepository;
import empresa.software.tiendaonline.security.CurrentUser;
import empresa.software.tiendaonline.security.UserPrincipal;
import empresa.software.tiendaonline.service.FileStorageService;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author pedro
 */
@RestController
@RequestMapping("/api/productos")
public class ProductoController {
    @Autowired
    VendedorRepository vendedorRepository;

    @Autowired
    ProductoRepository productoRepository;

    @Autowired
    TiendaRepository tiendaRepository;
    
    @Autowired
    FileStorageService fileStorageService;
    
    @Secured({"ROLE_SHOP"})
    @PostMapping
    public ResponseEntity<?> newProducto(@CurrentUser UserPrincipal userprincipal, @Valid @RequestBody ProductoRequest productoRequest) {
        
        Tienda tienda = vendedorRepository.findById(userprincipal.getId()).get().getTienda();
        //String urlImagenPrincipal = fileStorageService.storeFile(file);
        String urlImagenPrincipal = "https://res.cloudinary.com/hv7wxttwe/image/upload/v1555426080/default-product-image.png";
        
        Producto producto = new Producto(productoRequest.getNombre(), productoRequest.getDescripcion(), urlImagenPrincipal ,tienda);
        productoRepository.save(producto);

        return new ResponseEntity(new ApiResponse(true, "Producto Ingresado"),
                HttpStatus.ACCEPTED);
    }
    
    @Secured({"ROLE_SHOP"})
    @PostMapping("/id/{id}")
    public ResponseEntity<?> updateProducto(@CurrentUser UserPrincipal userprincipal, @RequestParam("id") Long id, @Valid @RequestBody ProductoRequest productoRequest, @RequestParam("file") MultipartFile file) {
        
        Producto producto = productoRepository.findById(id).get();
        producto.setNombre(productoRequest.getNombre());
        producto.setDescripcion(productoRequest.getDescripcion());
        productoRepository.save(producto);

        return new ResponseEntity(new ApiResponse(true, "Producto Actualizado"),
                HttpStatus.ACCEPTED);
    }
    @Secured({"ROLE_SHOP"})
    @PutMapping("/imagen/id/{id}")
    public ResponseEntity<?> uploadImagenProducto(@CurrentUser UserPrincipal userprincipal, @RequestParam("id") Long id,
            @RequestParam("file") MultipartFile file) {
        Producto producto = productoRepository.findById(id).get();
        String urlLogo = fileStorageService.storeFile(file);
        producto.setImagenPrincipal(urlLogo);
        productoRepository.save(producto);
        return ResponseEntity.ok().body(new ApiResponse(true, "Imagen Principal updated successfully"));
    }
}

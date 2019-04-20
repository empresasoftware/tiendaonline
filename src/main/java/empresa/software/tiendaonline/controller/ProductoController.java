/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package empresa.software.tiendaonline.controller;

import empresa.software.tiendaonline.model.Producto;
import empresa.software.tiendaonline.model.Categoria;
import empresa.software.tiendaonline.model.Tienda;
import empresa.software.tiendaonline.model.Vendedor;
import empresa.software.tiendaonline.payload.ApiResponse;
import empresa.software.tiendaonline.payload.ProductoRequest;
import empresa.software.tiendaonline.repository.CategoriaRepository;
import empresa.software.tiendaonline.repository.ProductoRepository;
import empresa.software.tiendaonline.repository.TiendaRepository;
import empresa.software.tiendaonline.repository.VendedorRepository;
import empresa.software.tiendaonline.security.CurrentUser;
import empresa.software.tiendaonline.security.UserPrincipal;
import empresa.software.tiendaonline.service.FileStorageService;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
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
    CategoriaRepository categoriaRepository;
    
    @Autowired
    FileStorageService fileStorageService;
    
    @GetMapping("/{id}")
    public Producto getProducto(@CurrentUser UserPrincipal userprincipal, @RequestParam("id") Long id) {
        Producto producto = productoRepository.findById(id).get();
        return producto;
    }

    @GetMapping("/categoria/{id}/{page}/{pageSize}")
    public Page<Producto> getProductoByCategoria(@CurrentUser UserPrincipal userprincipal, @RequestParam("id") Long id, @PathVariable int page, @PathVariable int pageSize) {
        Categoria categoria = categoriaRepository.findById(id).get();
        Page<Producto> productos = productoRepository.findByCategoria(categoria, PageRequest.of(page, pageSize));
        return productos;
    }
    
    
    @Secured({"ROLE_SHOP"})
    @PostMapping
    public Producto newProducto(@CurrentUser UserPrincipal userprincipal, @Valid @RequestBody ProductoRequest productoRequest) {
        
        Tienda tienda = vendedorRepository.findById(userprincipal.getId()).get().getTienda();
        //String urlImagenPrincipal = fileStorageService.storeFile(file);
        String urlImagenPrincipal = "https://res.cloudinary.com/hv7wxttwe/image/upload/v1555426080/default-product-image.png";
        Categoria categoria = categoriaRepository.findById(productoRequest.getCategoria()).get();
        Producto producto = new Producto(productoRequest.getNombre(), productoRequest.getDescripcion(), urlImagenPrincipal, categoria, tienda);
        productoRepository.save(producto);

        return producto;
    }
    
    @Secured({"ROLE_SHOP"})
    @PutMapping("/{id}/actualizar")
    public ResponseEntity<?> updateProducto(@CurrentUser UserPrincipal userprincipal, @RequestParam("id") Long id, @Valid @RequestBody ProductoRequest productoRequest, @RequestParam("file") MultipartFile file) {
        Categoria categoria = categoriaRepository.findById(productoRequest.getCategoria()).get();
        Producto producto = productoRepository.findById(id).get();
        producto.setNombre(productoRequest.getNombre());
        producto.setDescripcion(productoRequest.getDescripcion());
        producto.setCategoria(categoria);
        productoRepository.save(producto);

        return new ResponseEntity(new ApiResponse(true, "Producto Actualizado"),
                HttpStatus.ACCEPTED);
    }
    @Secured({"ROLE_SHOP"})
    @PutMapping("/{id}/imagen")
    public ResponseEntity<?> uploadImagenProducto(@CurrentUser UserPrincipal userprincipal, @RequestParam("id") Long id,
            @RequestParam("file") MultipartFile file) {
        Producto producto = productoRepository.findById(id).get();
        String urlLogo = fileStorageService.storeFile(file);
        producto.setImagenPrincipal(urlLogo);
        productoRepository.save(producto);
        return ResponseEntity.ok().body(new ApiResponse(true, "Imagen Principal updated successfully"));
    }
}

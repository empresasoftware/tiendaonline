/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package empresa.software.tiendaonline.controller;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.fasterxml.jackson.databind.util.Converter;
import empresa.software.tiendaonline.exception.AppException;
import empresa.software.tiendaonline.exception.ResourceNotFoundException;
import empresa.software.tiendaonline.model.Tienda;
import empresa.software.tiendaonline.model.TipoTienda;
import empresa.software.tiendaonline.model.TipoTiendaName;
import empresa.software.tiendaonline.model.Vendedor;
import empresa.software.tiendaonline.payload.ApiResponse;
import empresa.software.tiendaonline.payload.TiendaRequest;
import empresa.software.tiendaonline.repository.TiendaRepository;
import empresa.software.tiendaonline.repository.TipoTiendaRepository;
import empresa.software.tiendaonline.repository.UserRepository;
import empresa.software.tiendaonline.repository.VendedorRepository;
import empresa.software.tiendaonline.security.CurrentUser;
import empresa.software.tiendaonline.security.UserPrincipal;
import empresa.software.tiendaonline.service.FileStorageService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import java.net.URI;

 import lombok.SneakyThrows;

import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

/**
 *
 * @author pedro
 */
@RestController
@RequestMapping("/api/tiendas")
public class TiendaController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private VendedorRepository vendedorRepository;

    @Autowired
    private TiendaRepository tiendaRepository;

    @Autowired
    private TipoTiendaRepository tipoTiendaRepository;
    
    @Autowired
    FileStorageService fileStorageService;

    @GetMapping("/all/{page}/{pageSize}")
    public Page<Tienda> getAllTiendas(@PathVariable int page, @PathVariable int pageSize, Pageable pageable) {
        Page<Tienda> resultPage = tiendaRepository.findAll(PageRequest.of(page, pageSize));
        if (page > resultPage.getTotalPages()) {
            throw new ResourceNotFoundException("TiendaPage", "page", page);
        }
        return resultPage;
    }
    
    @GetMapping("/shopname/{shopname}")
    public Tienda getTiendaShopname(@CurrentUser UserPrincipal userprincipal, @PathVariable String shopname) {
        return tiendaRepository.findByShopname(shopname).get();
    }

    @GetMapping("/id/{id}")
    public Tienda getTiendaId(@CurrentUser UserPrincipal userprincipal, @PathVariable Long id) {
        return tiendaRepository.findById(id).get();
    }

    @Secured({"ROLE_SHOP"})
    @GetMapping
    public Tienda getTienda(@CurrentUser UserPrincipal userprincipal) {
        Vendedor vendedor = (Vendedor) userRepository.findById(userprincipal.getId()).get();
        return vendedor.getTienda();
    }

    @Secured({"ROLE_SHOP"})
    @PostMapping("/registrar")
    public ResponseEntity<?> nuevaTienda(@CurrentUser UserPrincipal userprincipal, @Valid @RequestBody TiendaRequest tiendaRequest) {
        Vendedor vendedor = (Vendedor) userRepository.findById(userprincipal.getId()).get();

        TipoTiendaName tipoTiendaName = null;
        for (TipoTiendaName myVar : TipoTiendaName.values()) {
            if (myVar.toString().equals(tiendaRequest.getTipoTienda())){
                tipoTiendaName = myVar;
            }
        }
        
        if (tipoTiendaName == null){
            return new ResponseEntity(new ApiResponse(false, "TipoTienda Error!"),
                    HttpStatus.BAD_REQUEST);
        }

        TipoTienda tipoTienda = tipoTiendaRepository.findByName(tipoTiendaName)
                .orElseThrow(() -> new AppException("Tipo Tienda not set."));
        Tienda tienda = vendedor.getTienda();
        if (tienda == null ){
            tienda = new Tienda(tiendaRequest.getName(), tiendaRequest.getShopname(), tiendaRequest.getDescripcion(),tiendaRequest.getUrlLogo() ,tipoTienda);
        }
        else{
            return ResponseEntity.badRequest().body(new ApiResponse(false, "La tienda ya ha sido creada!"));

        }
        tiendaRepository.save(tienda);
        vendedor.setTienda(tienda);
        vendedorRepository.save(vendedor);
        
        URI location = ServletUriComponentsBuilder
                .fromCurrentContextPath().path("/tiendas/{shopname}")
                .buildAndExpand(tienda.getShopname()).toUri();
        
        return ResponseEntity.created(location).body(new ApiResponse(true, "Tienda registered successfully"));
    }

    @Secured({"ROLE_SHOP"})
    @PutMapping("/actualizar")
    public ResponseEntity<?> actualizarTienda(@CurrentUser UserPrincipal userprincipal, @Valid @RequestBody TiendaRequest tiendaRequest) {
        Vendedor vendedor = (Vendedor) userRepository.findById(userprincipal.getId()).get();

        TipoTiendaName tipoTiendaName = null;
        for (TipoTiendaName myVar : TipoTiendaName.values()) {
            if (myVar.toString().equals(tiendaRequest.getTipoTienda())){
                tipoTiendaName = myVar;
            }
        }
        
        if (tipoTiendaName == null){
            return new ResponseEntity(new ApiResponse(false, "TipoTienda Error!"),
                    HttpStatus.BAD_REQUEST);
        }

        TipoTienda tipoTienda = tipoTiendaRepository.findByName(tipoTiendaName)
                .orElseThrow(() -> new AppException("Tipo Tienda not set."));
        Tienda tienda = vendedor.getTienda();
        
        if (tienda == null) {
            return ResponseEntity.badRequest().body(new ApiResponse(false, "Primero debe crear la tienda!"));
        }
        
        tienda.setName(tiendaRequest.getName());
        tienda.setShopname(tiendaRequest.getShopname());
        tienda.setDescripcion(tiendaRequest.getDescripcion());
        tienda.setTipoTienda(tipoTienda);
        tienda.setLogo(tiendaRequest.getUrlLogo());

        vendedor.setTienda(tienda);
        vendedorRepository.save(vendedor);
        
        return ResponseEntity.ok().body(new ApiResponse(true, "Tienda updated successfully"));
    }

    @Secured({"ROLE_SHOP"})
    @PutMapping("/logo")
    public ResponseEntity<?> uploadLogoTienda(@CurrentUser UserPrincipal userprincipal,
            @RequestParam("logo") MultipartFile file) {
        Vendedor vendedor = vendedorRepository.findById(userprincipal.getId()).get();
        String urlLogo = fileStorageService.storeFile(file);
        vendedor.getTienda().setLogo(urlLogo);
        vendedorRepository.save(vendedor);
        return ResponseEntity.ok().body(new ApiResponse(true, "Logo updated successfully"));
    }
}

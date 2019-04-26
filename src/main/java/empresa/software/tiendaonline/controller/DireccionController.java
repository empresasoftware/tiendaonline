/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package empresa.software.tiendaonline.controller;

import empresa.software.tiendaonline.exception.AppException;
import empresa.software.tiendaonline.model.Direccion;
import empresa.software.tiendaonline.model.Tienda;
import empresa.software.tiendaonline.model.TipoDireccion;
import empresa.software.tiendaonline.model.TipoDireccionName;
import empresa.software.tiendaonline.model.User;
import empresa.software.tiendaonline.model.Vendedor;
import empresa.software.tiendaonline.payload.ApiResponse;
import empresa.software.tiendaonline.payload.DireccionRequest;
import empresa.software.tiendaonline.repository.DireccionRepository;
import empresa.software.tiendaonline.repository.TipoDireccionRepository;
import empresa.software.tiendaonline.repository.UserRepository;
import empresa.software.tiendaonline.repository.TiendaRepository;
import empresa.software.tiendaonline.repository.VendedorRepository;
import empresa.software.tiendaonline.security.CurrentUser;
import empresa.software.tiendaonline.security.UserPrincipal;
import java.util.Set;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author pedro
 */
@RestController
@RequestMapping("/api/direcciones")
public class DireccionController {
    
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TiendaRepository tiendaRepository;

    @Autowired
    private VendedorRepository vendedorRepository;
    
    @Autowired
    private DireccionRepository direccionRepository;
    
    @Autowired
    private TipoDireccionRepository tipoDireccionRepository;
    
    @PostMapping("/user/tipoDireccion/{tipoDireccion}")
    public ResponseEntity<?> newDireccionUser(@CurrentUser UserPrincipal userprincipal, @Valid @RequestBody DireccionRequest direccionRequest, @PathVariable("tipoDireccion") String tipoDireccionParam){

        TipoDireccionName tipoDireccionName = null;
        for (TipoDireccionName myVar : TipoDireccionName.values()) {
            if (myVar.toString().equals(tipoDireccionParam)){
                tipoDireccionName = myVar;
            }
        }
        
        TipoDireccion tipoDireccion = tipoDireccionRepository.findByName(tipoDireccionName)
                .orElseThrow(() -> new AppException("Tipo Direccion not set."));
        
        Direccion direccion = new Direccion(direccionRequest.getLatitud(), direccionRequest.getLongitud(), direccionRequest.getCallePrincipal(), direccionRequest.getCalleSecundaria(), direccionRequest.getDescripcionUbicacion(), direccionRequest.getPais(), direccionRequest.getProvincia(), direccionRequest.getCiudad(), direccionRequest.getCodigoZipPostal(), direccionRequest.getNumeroTelefono(), tipoDireccion);
        direccion = direccionRepository.save(direccion);
        
        User user = userRepository.findById(userprincipal.getId()).get();
        user.getDirecciones().add(direccion);
        userRepository.save(user);
        
        return ResponseEntity.ok().body(new ApiResponse(true, "Direccion successfully created"));
    }


    @PutMapping("/user/tipoDireccion/{tipoDireccion}")
    public ResponseEntity<?> updateDireccionUser(@CurrentUser UserPrincipal userprincipal, @Valid @RequestBody DireccionRequest direccionRequest, @PathVariable("tipoDireccion") String tipoDireccionParam){

        TipoDireccionName tipoDireccionName = null;
        for (TipoDireccionName myVar : TipoDireccionName.values()) {
            if (myVar.toString().equals(tipoDireccionParam)){
                tipoDireccionName = myVar;
            }
        }
        
        TipoDireccion tipoDireccion = tipoDireccionRepository.findByName(tipoDireccionName)
                .orElseThrow(() -> new AppException("Tipo Direccion not set."));
        
        User user = userRepository.findById(userprincipal.getId()).get();
        Set<Direccion> direcciones = user.getDirecciones();
        
        Direccion direccionUpdate = null;
        
        for (Direccion direccion : direcciones) {
            if(direccion.getTipoDireccion().equals(tipoDireccion)){
                direccionUpdate = direccion;
            }
        }
        
        if (direccionUpdate == null){
            return new ResponseEntity(new ApiResponse(false, "Direreccion Not Found!"),
                    HttpStatus.NOT_FOUND);        }
        
        direccionUpdate.setCallePrincipal(direccionRequest.getCallePrincipal());
        direccionUpdate.setCalleSecundaria(direccionRequest.getCalleSecundaria());
        direccionUpdate.setCiudad(direccionRequest.getCiudad());
        direccionUpdate.setCodigoZipPostal(direccionRequest.getCodigoZipPostal());
        direccionUpdate.setDescripcionUbicacion(direccionRequest.getDescripcionUbicacion());
        direccionUpdate.setLatitud(direccionRequest.getLatitud());
        direccionUpdate.setLongitud(direccionRequest.getLongitud());
        direccionUpdate.setNumeroTelefono(direccionRequest.getNumeroTelefono());
        direccionUpdate.setPais(direccionRequest.getPais());
        direccionUpdate.setProvincia(direccionRequest.getProvincia());        
        
        userRepository.save(user);
        
        return ResponseEntity.ok().body(new ApiResponse(true, "Direccion successfully updated"));
    }

    @PostMapping("/tienda")
    public ResponseEntity<?> newDireccionTienda(@CurrentUser UserPrincipal userprincipal, @Valid @RequestBody DireccionRequest direccionRequest, @PathVariable("tipoDireccion") String tipoDireccionParam){

        TipoDireccionName tipoDireccionName = null;
        for (TipoDireccionName myVar : TipoDireccionName.values()) {
            if (myVar.toString().equals(tipoDireccionParam)){
                tipoDireccionName = myVar;
            }
        }
        
        TipoDireccion tipoDireccion = tipoDireccionRepository.findByName(tipoDireccionName)
                .orElseThrow(() -> new AppException("Tipo Direccion not set."));
        
        Direccion direccion = new Direccion(direccionRequest.getLatitud(), direccionRequest.getLongitud(), direccionRequest.getCallePrincipal(), direccionRequest.getCalleSecundaria(), direccionRequest.getDescripcionUbicacion(), direccionRequest.getPais(), direccionRequest.getProvincia(), direccionRequest.getCiudad(), direccionRequest.getCodigoZipPostal(), direccionRequest.getNumeroTelefono(), tipoDireccion);
        direccion = direccionRepository.save(direccion);
        
        Vendedor vendedor = vendedorRepository.findById(userprincipal.getId()).get();
        Tienda tienda = vendedor.getTienda();
        tienda.getDirecciones().add(direccion);
        tiendaRepository.save(tienda);
        
        return ResponseEntity.ok().body(new ApiResponse(true, "Direccion successfully created"));
    }


    @PutMapping("/tienda/tipoDireccion/{tipoDireccion}")
    public ResponseEntity<?> updateDireccionTienda(@CurrentUser UserPrincipal userprincipal, @Valid @RequestBody DireccionRequest direccionRequest, @PathVariable("tipoDireccion") String tipoDireccionParam){

        TipoDireccionName tipoDireccionName = null;
        for (TipoDireccionName myVar : TipoDireccionName.values()) {
            if (myVar.toString().equals(tipoDireccionParam)){
                tipoDireccionName = myVar;
            }
        }
        
        TipoDireccion tipoDireccion = tipoDireccionRepository.findByName(tipoDireccionName)
                .orElseThrow(() -> new AppException("Tipo Direccion not set."));
        
        Vendedor vendedor = vendedorRepository.findById(userprincipal.getId()).get();
        Tienda tienda = vendedor.getTienda();
        Set<Direccion> direcciones = tienda.getDirecciones();
        
        Direccion direccionUpdate = null;
        
        for (Direccion direccion : direcciones) {
            if(direccion.getTipoDireccion().equals(tipoDireccion)){
                direccionUpdate = direccion;
            }
        }
        
        if (direccionUpdate == null){
            return new ResponseEntity(new ApiResponse(false, "Direccion Not Found!"),
                    HttpStatus.NOT_FOUND);        }
        
        direccionUpdate.setCallePrincipal(direccionRequest.getCallePrincipal());
        direccionUpdate.setCalleSecundaria(direccionRequest.getCalleSecundaria());
        direccionUpdate.setCiudad(direccionRequest.getCiudad());
        direccionUpdate.setCodigoZipPostal(direccionRequest.getCodigoZipPostal());
        direccionUpdate.setDescripcionUbicacion(direccionRequest.getDescripcionUbicacion());
        direccionUpdate.setLatitud(direccionRequest.getLatitud());
        direccionUpdate.setLongitud(direccionRequest.getLongitud());
        direccionUpdate.setNumeroTelefono(direccionRequest.getNumeroTelefono());
        direccionUpdate.setPais(direccionRequest.getPais());
        direccionUpdate.setProvincia(direccionRequest.getProvincia());        

        tiendaRepository.save(tienda);
        
        return ResponseEntity.ok().body(new ApiResponse(true, "Direccion successfully updated"));
    }
    
    @GetMapping("/tienda/tipoDireccion/{tipoDireccion}")
    public Direccion getDireccionUser(@CurrentUser UserPrincipal userprincipal, @PathVariable("tipoDireccion") String tipoDireccionParam){

        TipoDireccionName tipoDireccionName = null;
        for (TipoDireccionName myVar : TipoDireccionName.values()) {
            if (myVar.toString().equals(tipoDireccionParam)){
                tipoDireccionName = myVar;
            }
        }
        
        TipoDireccion tipoDireccion = tipoDireccionRepository.findByName(tipoDireccionName)
                .orElseThrow(() -> new AppException("Tipo Direccion not set."));
                
        User user = userRepository.findById(userprincipal.getId()).get();
        Set<Direccion> direcciones = user.getDirecciones();
        
        for (Direccion direccion : direcciones) {
            if(direccion.getTipoDireccion().equals(tipoDireccion)){
                return direccion;
            }
        }
        
        return null;
    }

    @GetMapping("/tienda/tipoDireccion/{tipoDireccion}")
    public Direccion getDireccionTienda(@CurrentUser UserPrincipal userprincipal, @PathVariable("tipoDireccion") String tipoDireccionParam){

        TipoDireccionName tipoDireccionName = null;
        for (TipoDireccionName myVar : TipoDireccionName.values()) {
            if (myVar.toString().equals(tipoDireccionParam)){
                tipoDireccionName = myVar;
            }
        }
        
        TipoDireccion tipoDireccion = tipoDireccionRepository.findByName(tipoDireccionName)
                .orElseThrow(() -> new AppException("Tipo Direccion not set."));
                
        Vendedor vendedor = vendedorRepository.findById(userprincipal.getId()).get();
        Tienda tienda = vendedor.getTienda();
        Set<Direccion> direcciones = tienda.getDirecciones();
        
        for (Direccion direccion : direcciones) {
            if(direccion.getTipoDireccion().equals(tipoDireccion)){
                return direccion;
            }
        }
        
        return null;
    }
}

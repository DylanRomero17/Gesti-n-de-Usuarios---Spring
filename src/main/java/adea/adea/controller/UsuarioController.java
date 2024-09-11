package adea.adea.controller;

import adea.adea.entity.Usuario;
import adea.adea.repository.UsuarioRepository;
import java.text.SimpleDateFormat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/adea")
public class UsuarioController {

    @Autowired
    private UsuarioRepository usuarioRepository;
    
    @Autowired
    private PasswordEncoder passwordEncoder;
    
    @GetMapping
    public String mostrarPagina() {
        return "adea";
    }
    
    @GetMapping("/")
    public String home() {
        return "adea"; 
    }
    
        @GetMapping("/login")
    public String login() {
        return "login"; // Retorna el nombre del archivo login.html
    }
    
        @GetMapping("/registro")
    public String registerUserAccount(Model model) {
        Usuario usuario = new Usuario();
        model.addAttribute("usuario", usuario);
        model.addAttribute("pageTitle", "Nuevo Usuario");
        return "registro";  
    }

    @GetMapping("/usuarios")
    public String getAllUsuarios(Model model, @Param("keyword") String keyword, @Param("status") Character status, @Param("fechaInicio") Date fechaInicio, @Param("fechaFin") Date fechaFin) {
        try{
            List<Usuario> usuarios = new ArrayList<Usuario>();
            
            if(fechaInicio != null && fechaFin != null){
                usuarios = usuarioRepository.findByFechaAltaBetween(fechaInicio, fechaFin);
                model.addAttribute("usuarios", usuarios);
            }
            else if(status != null ){
                usuarios.addAll(usuarioRepository.findByStatus(status));
                model.addAttribute("status", status);
            }
            else if(keyword == null ){
                usuarios.addAll(usuarioRepository.findAll());
            }else {
                usuarios.addAll(usuarioRepository.findByNombreContainingIgnoreCase(keyword));
                model.addAttribute("keyword", keyword);
            }

            model.addAttribute("usuarios", usuarios);
        } catch (Exception e) {
            model.addAttribute("message", e.getMessage());
        }
        return "/usuarios";
    }

    @GetMapping("/usuarios/new")
    public String addUsuario(Model model) {
        Usuario usuario = new Usuario();
        model.addAttribute("usuario", usuario);
        model.addAttribute("pageTitle", "Nuevo Usuario");

        return "/usuario_form";
    }

        @InitBinder
    public void initBinder(WebDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateFormat.setLenient(false);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
    }
    
    @PostMapping("/usuarios/save")
    public String saveUsuario(Usuario usuario, @RequestParam(value = "bool", required = false) Integer bool, RedirectAttributes redirectAttributes, @RequestParam(value = "pass", required = false) String pass) {
        try {
            if (usuario.getPassword() != null && !usuario.getPassword().isEmpty()) {
                if(usuario.getPassword().equals(pass)){
                    usuario.setPassword(usuario.getPassword());
                }else{
                    String encryptedPassword = passwordEncoder.encode(usuario.getPassword());
                usuario.setPassword(encryptedPassword);
                }
            }

            if (bool != null && bool == 1) {
                usuario.setFechaModificacion(new Date());
                if (usuario.getFechaBaja() == null) {
                    usuario.setFechaBaja(null);
                }
                usuarioRepository.save(usuario);
                redirectAttributes.addFlashAttribute("message", "Usuario actualizado exitosamente");
            } else {
                usuario.setFechaAlta(new Date());
                usuario.setStatus('A');
                usuario.setIntentos(0.0F);
                usuario.setFechaModificacion(new Date());
                usuarioRepository.save(usuario);
                redirectAttributes.addFlashAttribute("message", "Usuario guardado exitosamente");
            }
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("message", e.getMessage());
        }
        return "redirect:/adea/usuarios";
    }

    @GetMapping("/usuarios/{login}")
    public String editUsuario(@PathVariable("login") String login, Model model, RedirectAttributes redirectAttributes){
        try{
            Usuario usuario = usuarioRepository.findById(login).get();
            String pass = usuario.getPassword();
            
            model.addAttribute("pass", pass);
            model.addAttribute("usuario", usuario);
            model.addAttribute("pageTitle", "Editar Usuario - Login: " + login);

            return "/usuario_form";
        }catch (Exception e) {
            redirectAttributes.addFlashAttribute("message", e.getMessage());
            return "redirect:/adea/usuarios";
        }
    }

    @GetMapping("/usuarios/delete/{login}")
    public String deleteUsuario(@PathVariable("login") String login, Model model, RedirectAttributes redirectAttributes){
        try{
            usuarioRepository.deleteById(login);

            redirectAttributes.addFlashAttribute("message", "Usuario: " + login + " eliminado exitosamente");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("message", e.getMessage());
        }
        return "redirect:/adea/usuarios";
    }    
    
    @PostMapping("/registrar")
    public String registrarUsuario( Usuario usuario, RedirectAttributes redirectAttributes) {
        try {
            String encryptedPassword = passwordEncoder.encode(usuario.getPassword());
            usuario.setPassword(encryptedPassword);
            usuario.setFechaAlta(new Date());
            usuario.setStatus('A');
            usuario.setCliente(0.0F);
            usuario.setIntentos(0.0F);
            usuario.setFechaModificacion(new Date());
            usuarioRepository.save(usuario);
            redirectAttributes.addFlashAttribute("message", "Usuario registrado exitosamente");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("message", e.getMessage());
        }
        return "redirect:/adea/login";
    }

}

package adea.adea.entity;

import javax.persistence.*;

import java.math.BigDecimal;
import java.util.Date;
import lombok.Data;

@Table(name = "usuario")
@Data
@Entity
public class Usuario {

    @Id
    @Column(name = "login", nullable = false, length = 20)
    private String login;

    @Column(name = "password", nullable = false, length = 30)
    private String password;

    @Column(name = "nombre", nullable = false, length = 50)
    private String nombre;

    @Column(name = "cliente", nullable = false)
    private Float cliente;

    @Column(name = "email", length = 50)
    private String email;

    @Column(name = "fecha_alta", nullable = false)
    private Date fechaAlta;

    @Column(name = "fecha_baja")
    private Date fechaBaja;

    @Column(name = "status", nullable = false)
    private Character status;

    @Column(name = "intentos", nullable = false)
    private Float intentos;

    @Column(name = "fecha_revocado")
    private Date fechaRevocado;

    @Column(name = "fecha_vigencia")
    private Date fechaVigencia;

    @Column(name = "no_acceso")
    private Integer noAcceso;

    @Column(name = "apellido_paterno", length = 50)
    private String apellidoPaterno;

    @Column(name = "apellido_materno", length = 50)
    private String apellidoMaterno;

    @Column(name = "area", precision = 4)
    private BigDecimal area;

    @Column(name = "fecha_modificacion", nullable = false)
    private Date fechaModificacion;

    public Usuario() {
    }

    public Usuario(String login, String password, String nombre, Float cliente, String email, Date fechaAlta,
                   Date fechaBaja, Character status, Float intentos, Date fechaRevocado,
                   Date fechaVigencia, Integer noAcceso, String apellidoPaterno, String apellidoMaterno, BigDecimal area, Date fechaModificacion) {
        this.login = login;
        this.password = password;
        this.nombre = nombre;
        this.cliente = cliente;
        this.email = email;
        this.fechaAlta = fechaAlta;
        this.fechaBaja = fechaBaja;
        this.status = status;
        this.intentos = intentos;
        this.fechaRevocado = fechaRevocado;
        this.fechaVigencia = fechaVigencia;
        this.noAcceso = noAcceso;
        this.apellidoPaterno = apellidoPaterno;
        this.apellidoMaterno = apellidoMaterno;
        this.area = area;
        this.fechaModificacion = fechaModificacion;
    }

    @Override
    public String toString() {
        return "Usuario{" + "login=" + login + ", password=" + password + ", nombre=" + nombre + ", cliente=" + cliente + ", email=" + email + ", fechaAlta=" + fechaAlta + ", fechaBaja=" + fechaBaja + ", status=" + status + ", intentos=" + intentos + ", fechaRevocado=" + fechaRevocado + ", fechaVigencia=" + fechaVigencia + ", noAcceso=" + noAcceso + ", apellidoPaterno=" + apellidoPaterno + ", apellidoMaterno=" + apellidoMaterno + ", area=" + area + ", fechaModificacion=" + fechaModificacion + '}';
    }
    
}

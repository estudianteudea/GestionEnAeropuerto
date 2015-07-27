/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Usuariox
 */
@Entity
@Table(name = "aeropuerto")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Aeropuerto.findAll", query = "SELECT a FROM Aeropuerto a"),
    @NamedQuery(name = "Aeropuerto.findByIdAeropuerto", query = "SELECT a FROM Aeropuerto a WHERE a.idAeropuerto = :idAeropuerto"),
    @NamedQuery(name = "Aeropuerto.findByNombreAeropuerto", query = "SELECT a FROM Aeropuerto a WHERE a.nombreAeropuerto = :nombreAeropuerto")})
public class Aeropuerto implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "idAeropuerto")
    private Integer idAeropuerto;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "nombreAeropuerto")
    private String nombreAeropuerto;
    @JoinColumn(name = "ciudad", referencedColumnName = "idCiudad")
    @ManyToOne(optional = false)
    private Ciudad ciudad;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "origen")
    private Collection<Trayecto> trayectoCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "destino")
    private Collection<Trayecto> trayectoCollection1;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "origen")
    private Collection<Viaje> viajeCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "destino")
    private Collection<Viaje> viajeCollection1;

    public Aeropuerto() {
    }

    public Aeropuerto(Integer idAeropuerto) {
        this.idAeropuerto = idAeropuerto;
    }

    public Aeropuerto(Integer idAeropuerto, String nombreAeropuerto) {
        this.idAeropuerto = idAeropuerto;
        this.nombreAeropuerto = nombreAeropuerto;
    }

    public Integer getIdAeropuerto() {
        return idAeropuerto;
    }

    public void setIdAeropuerto(Integer idAeropuerto) {
        this.idAeropuerto = idAeropuerto;
    }

    public String getNombreAeropuerto() {
        return nombreAeropuerto;
    }

    public void setNombreAeropuerto(String nombreAeropuerto) {
        this.nombreAeropuerto = nombreAeropuerto;
    }

    public Ciudad getCiudad() {
        return ciudad;
    }

    public void setCiudad(Ciudad ciudad) {
        this.ciudad = ciudad;
    }

    @XmlTransient
    public Collection<Trayecto> getTrayectoCollection() {
        return trayectoCollection;
    }

    public void setTrayectoCollection(Collection<Trayecto> trayectoCollection) {
        this.trayectoCollection = trayectoCollection;
    }

    @XmlTransient
    public Collection<Trayecto> getTrayectoCollection1() {
        return trayectoCollection1;
    }

    public void setTrayectoCollection1(Collection<Trayecto> trayectoCollection1) {
        this.trayectoCollection1 = trayectoCollection1;
    }

    @XmlTransient
    public Collection<Viaje> getViajeCollection() {
        return viajeCollection;
    }

    public void setViajeCollection(Collection<Viaje> viajeCollection) {
        this.viajeCollection = viajeCollection;
    }

    @XmlTransient
    public Collection<Viaje> getViajeCollection1() {
        return viajeCollection1;
    }

    public void setViajeCollection1(Collection<Viaje> viajeCollection1) {
        this.viajeCollection1 = viajeCollection1;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idAeropuerto != null ? idAeropuerto.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Aeropuerto)) {
            return false;
        }
        Aeropuerto other = (Aeropuerto) object;
        if ((this.idAeropuerto == null && other.idAeropuerto != null) || (this.idAeropuerto != null && !this.idAeropuerto.equals(other.idAeropuerto))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.Aeropuerto[ idAeropuerto=" + idAeropuerto + " ]";
    }
    
}

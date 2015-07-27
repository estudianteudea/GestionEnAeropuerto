/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Usuariox
 */
@Entity
@Table(name = "avion")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Avion.findAll", query = "SELECT a FROM Avion a"),
    @NamedQuery(name = "Avion.findByIdAvion", query = "SELECT a FROM Avion a WHERE a.idAvion = :idAvion"),
    @NamedQuery(name = "Avion.findByCapacidadPasajeros", query = "SELECT a FROM Avion a WHERE a.capacidadPasajeros = :capacidadPasajeros"),
    @NamedQuery(name = "Avion.findByCapacidadCarga", query = "SELECT a FROM Avion a WHERE a.capacidadCarga = :capacidadCarga"),
    @NamedQuery(name = "Avion.findByCapacidadCombustible", query = "SELECT a FROM Avion a WHERE a.capacidadCombustible = :capacidadCombustible")})
public class Avion implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "idAvion")
    private Integer idAvion;
    @Basic(optional = false)
    @NotNull
    @Column(name = "capacidadPasajeros")
    private int capacidadPasajeros;
    @Basic(optional = false)
    @NotNull
    @Column(name = "capacidadCarga")
    private float capacidadCarga;
    @Basic(optional = false)
    @NotNull
    @Column(name = "capacidadCombustible")
    private float capacidadCombustible;

    public Avion() {
    }

    public Avion(Integer idAvion) {
        this.idAvion = idAvion;
    }

    public Avion(Integer idAvion, int capacidadPasajeros, float capacidadCarga, float capacidadCombustible) {
        this.idAvion = idAvion;
        this.capacidadPasajeros = capacidadPasajeros;
        this.capacidadCarga = capacidadCarga;
        this.capacidadCombustible = capacidadCombustible;
    }

    public Integer getIdAvion() {
        return idAvion;
    }

    public void setIdAvion(Integer idAvion) {
        this.idAvion = idAvion;
    }

    public int getCapacidadPasajeros() {
        return capacidadPasajeros;
    }

    public void setCapacidadPasajeros(int capacidadPasajeros) {
        this.capacidadPasajeros = capacidadPasajeros;
    }

    public float getCapacidadCarga() {
        return capacidadCarga;
    }

    public void setCapacidadCarga(float capacidadCarga) {
        this.capacidadCarga = capacidadCarga;
    }

    public float getCapacidadCombustible() {
        return capacidadCombustible;
    }

    public void setCapacidadCombustible(float capacidadCombustible) {
        this.capacidadCombustible = capacidadCombustible;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idAvion != null ? idAvion.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Avion)) {
            return false;
        }
        Avion other = (Avion) object;
        if ((this.idAvion == null && other.idAvion != null) || (this.idAvion != null && !this.idAvion.equals(other.idAvion))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.Avion[ idAvion=" + idAvion + " ]";
    }
    
}

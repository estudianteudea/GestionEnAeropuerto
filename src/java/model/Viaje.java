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
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Usuariox
 */
@Entity
@Table(name = "viaje")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Viaje.findAll", query = "SELECT v FROM Viaje v"),
    @NamedQuery(name = "Viaje.findByIdViaje", query = "SELECT v FROM Viaje v WHERE v.idViaje = :idViaje")})
public class Viaje implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "idViaje")
    private Integer idViaje;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "viaje")
    private Collection<Trayecto> trayectoCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "viaje")
    private Collection<Reprogramacionporviaje> reprogramacionporviajeCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "viaje1")
    private Collection<Embarque> embarqueCollection;
    @JoinColumn(name = "origen", referencedColumnName = "idAeropuerto")
    @ManyToOne(optional = false)
    private Aeropuerto origen;
    @JoinColumn(name = "destino", referencedColumnName = "idAeropuerto")
    @ManyToOne(optional = false)
    private Aeropuerto destino;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "viaje")
    private Collection<Reserva> reservaCollection;

    public Viaje() {
    }

    public Viaje(Integer idViaje) {
        this.idViaje = idViaje;
    }

    public Integer getIdViaje() {
        return idViaje;
    }

    public void setIdViaje(Integer idViaje) {
        this.idViaje = idViaje;
    }

    @XmlTransient
    public Collection<Trayecto> getTrayectoCollection() {
        return trayectoCollection;
    }

    public void setTrayectoCollection(Collection<Trayecto> trayectoCollection) {
        this.trayectoCollection = trayectoCollection;
    }

    @XmlTransient
    public Collection<Reprogramacionporviaje> getReprogramacionporviajeCollection() {
        return reprogramacionporviajeCollection;
    }

    public void setReprogramacionporviajeCollection(Collection<Reprogramacionporviaje> reprogramacionporviajeCollection) {
        this.reprogramacionporviajeCollection = reprogramacionporviajeCollection;
    }

    @XmlTransient
    public Collection<Embarque> getEmbarqueCollection() {
        return embarqueCollection;
    }

    public void setEmbarqueCollection(Collection<Embarque> embarqueCollection) {
        this.embarqueCollection = embarqueCollection;
    }

    public Aeropuerto getOrigen() {
        return origen;
    }

    public void setOrigen(Aeropuerto origen) {
        this.origen = origen;
    }

    public Aeropuerto getDestino() {
        return destino;
    }

    public void setDestino(Aeropuerto destino) {
        this.destino = destino;
    }

    @XmlTransient
    public Collection<Reserva> getReservaCollection() {
        return reservaCollection;
    }

    public void setReservaCollection(Collection<Reserva> reservaCollection) {
        this.reservaCollection = reservaCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idViaje != null ? idViaje.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Viaje)) {
            return false;
        }
        Viaje other = (Viaje) object;
        if ((this.idViaje == null && other.idViaje != null) || (this.idViaje != null && !this.idViaje.equals(other.idViaje))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.Viaje[ idViaje=" + idViaje + " ]";
    }
    
}

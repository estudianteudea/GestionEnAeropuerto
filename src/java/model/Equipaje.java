/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Usuariox
 */
@Entity
@Table(name = "equipaje")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Equipaje.findAll", query = "SELECT e FROM Equipaje e"),
    @NamedQuery(name = "Equipaje.findByIdEquipaje", query = "SELECT e FROM Equipaje e WHERE e.idEquipaje = :idEquipaje"),
    @NamedQuery(name = "Equipaje.findByPesoEquipaje", query = "SELECT e FROM Equipaje e WHERE e.pesoEquipaje = :pesoEquipaje"),
    @NamedQuery(name = "Equipaje.findByFechaRegistro", query = "SELECT e FROM Equipaje e WHERE e.fechaRegistro = :fechaRegistro")})
public class Equipaje implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "idEquipaje")
    private Integer idEquipaje;
    @Basic(optional = false)
    @NotNull
    @Column(name = "pesoEquipaje")
    private float pesoEquipaje;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fechaRegistro")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaRegistro;
    @JoinColumn(name = "reserva", referencedColumnName = "idReserva")
    @ManyToOne(optional = false)
    private Reserva reserva;
    @JoinColumn(name = "ubicacion", referencedColumnName = "idUbicacion")
    @ManyToOne(optional = false)
    private Ubicacion ubicacion;
    @JoinColumn(name = "estado", referencedColumnName = "idEstado")
    @ManyToOne(optional = false)
    private Estado estado;

    public Equipaje() {
    }

    public Equipaje(Integer idEquipaje) {
        this.idEquipaje = idEquipaje;
    }

    public Equipaje(Integer idEquipaje, float pesoEquipaje, Date fechaRegistro) {
        this.idEquipaje = idEquipaje;
        this.pesoEquipaje = pesoEquipaje;
        this.fechaRegistro = fechaRegistro;
    }

    public Integer getIdEquipaje() {
        return idEquipaje;
    }

    public void setIdEquipaje(Integer idEquipaje) {
        this.idEquipaje = idEquipaje;
    }

    public float getPesoEquipaje() {
        return pesoEquipaje;
    }

    public void setPesoEquipaje(float pesoEquipaje) {
        this.pesoEquipaje = pesoEquipaje;
    }

    public Date getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(Date fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public Reserva getReserva() {
        return reserva;
    }

    public void setReserva(Reserva reserva) {
        this.reserva = reserva;
    }

    public Ubicacion getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(Ubicacion ubicacion) {
        this.ubicacion = ubicacion;
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idEquipaje != null ? idEquipaje.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Equipaje)) {
            return false;
        }
        Equipaje other = (Equipaje) object;
        if ((this.idEquipaje == null && other.idEquipaje != null) || (this.idEquipaje != null && !this.idEquipaje.equals(other.idEquipaje))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.Equipaje[ idEquipaje=" + idEquipaje + " ]";
    }
    
}

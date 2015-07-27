/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Usuariox
 */
@Entity
@Table(name = "reserva")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Reserva.findAll", query = "SELECT r FROM Reserva r"),
    @NamedQuery(name = "Reserva.findByIdReserva", query = "SELECT r FROM Reserva r WHERE r.idReserva = :idReserva"),
    @NamedQuery(name = "Reserva.findByFechaReserva", query = "SELECT r FROM Reserva r WHERE r.fechaReserva = :fechaReserva"),
    @NamedQuery(name = "Reserva.findByConfirmacion", query = "SELECT r FROM Reserva r WHERE r.confirmacion = :confirmacion"),
    @NamedQuery(name = "Reserva.findByFechaConfirmacion", query = "SELECT r FROM Reserva r WHERE r.fechaConfirmacion = :fechaConfirmacion")})
public class Reserva implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "idReserva")
    private Integer idReserva;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fechaReserva")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaReserva;
    @Column(name = "confirmacion")
    private Boolean confirmacion;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fechaConfirmacion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaConfirmacion;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "reserva")
    private Collection<Reprogramacionporusuario> reprogramacionporusuarioCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "reserva")
    private Collection<Equipaje> equipajeCollection;
    @JoinColumn(name = "usuario", referencedColumnName = "idUsuario")
    @ManyToOne(optional = false)
    private Usuario usuario;
    @JoinColumn(name = "viaje", referencedColumnName = "idViaje")
    @ManyToOne(optional = false)
    private Viaje viaje;
    @JoinColumn(name = "puertaEmbarque", referencedColumnName = "idPuertaEmbarque")
    @ManyToOne(optional = false)
    private Puertaembarque puertaEmbarque;

    public Reserva() {
    }

    public Reserva(Integer idReserva) {
        this.idReserva = idReserva;
    }

    public Reserva(Integer idReserva, Date fechaReserva, Date fechaConfirmacion) {
        this.idReserva = idReserva;
        this.fechaReserva = fechaReserva;
        this.fechaConfirmacion = fechaConfirmacion;
    }

    public Integer getIdReserva() {
        return idReserva;
    }

    public void setIdReserva(Integer idReserva) {
        this.idReserva = idReserva;
    }

    public Date getFechaReserva() {
        return fechaReserva;
    }

    public void setFechaReserva(Date fechaReserva) {
        this.fechaReserva = fechaReserva;
    }

    public Boolean getConfirmacion() {
        return confirmacion;
    }

    public void setConfirmacion(Boolean confirmacion) {
        this.confirmacion = confirmacion;
    }

    public Date getFechaConfirmacion() {
        return fechaConfirmacion;
    }

    public void setFechaConfirmacion(Date fechaConfirmacion) {
        this.fechaConfirmacion = fechaConfirmacion;
    }

    @XmlTransient
    public Collection<Reprogramacionporusuario> getReprogramacionporusuarioCollection() {
        return reprogramacionporusuarioCollection;
    }

    public void setReprogramacionporusuarioCollection(Collection<Reprogramacionporusuario> reprogramacionporusuarioCollection) {
        this.reprogramacionporusuarioCollection = reprogramacionporusuarioCollection;
    }

    @XmlTransient
    public Collection<Equipaje> getEquipajeCollection() {
        return equipajeCollection;
    }

    public void setEquipajeCollection(Collection<Equipaje> equipajeCollection) {
        this.equipajeCollection = equipajeCollection;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Viaje getViaje() {
        return viaje;
    }

    public void setViaje(Viaje viaje) {
        this.viaje = viaje;
    }

    public Puertaembarque getPuertaEmbarque() {
        return puertaEmbarque;
    }

    public void setPuertaEmbarque(Puertaembarque puertaEmbarque) {
        this.puertaEmbarque = puertaEmbarque;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idReserva != null ? idReserva.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Reserva)) {
            return false;
        }
        Reserva other = (Reserva) object;
        if ((this.idReserva == null && other.idReserva != null) || (this.idReserva != null && !this.idReserva.equals(other.idReserva))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.Reserva[ idReserva=" + idReserva + " ]";
    }
    
}

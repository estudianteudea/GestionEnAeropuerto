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
@Table(name = "reprogramacionporusuario")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Reprogramacionporusuario.findAll", query = "SELECT r FROM Reprogramacionporusuario r"),
    @NamedQuery(name = "Reprogramacionporusuario.findByIdReprogramacion", query = "SELECT r FROM Reprogramacionporusuario r WHERE r.idReprogramacion = :idReprogramacion"),
    @NamedQuery(name = "Reprogramacionporusuario.findByFechaReprogramacion", query = "SELECT r FROM Reprogramacionporusuario r WHERE r.fechaReprogramacion = :fechaReprogramacion")})
public class Reprogramacionporusuario implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "idReprogramacion")
    private Integer idReprogramacion;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fechaReprogramacion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaReprogramacion;
    @JoinColumn(name = "reserva", referencedColumnName = "idReserva")
    @ManyToOne(optional = false)
    private Reserva reserva;

    public Reprogramacionporusuario() {
    }

    public Reprogramacionporusuario(Integer idReprogramacion) {
        this.idReprogramacion = idReprogramacion;
    }

    public Reprogramacionporusuario(Integer idReprogramacion, Date fechaReprogramacion) {
        this.idReprogramacion = idReprogramacion;
        this.fechaReprogramacion = fechaReprogramacion;
    }

    public Integer getIdReprogramacion() {
        return idReprogramacion;
    }

    public void setIdReprogramacion(Integer idReprogramacion) {
        this.idReprogramacion = idReprogramacion;
    }

    public Date getFechaReprogramacion() {
        return fechaReprogramacion;
    }

    public void setFechaReprogramacion(Date fechaReprogramacion) {
        this.fechaReprogramacion = fechaReprogramacion;
    }

    public Reserva getReserva() {
        return reserva;
    }

    public void setReserva(Reserva reserva) {
        this.reserva = reserva;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idReprogramacion != null ? idReprogramacion.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Reprogramacionporusuario)) {
            return false;
        }
        Reprogramacionporusuario other = (Reprogramacionporusuario) object;
        if ((this.idReprogramacion == null && other.idReprogramacion != null) || (this.idReprogramacion != null && !this.idReprogramacion.equals(other.idReprogramacion))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.Reprogramacionporusuario[ idReprogramacion=" + idReprogramacion + " ]";
    }
    
}

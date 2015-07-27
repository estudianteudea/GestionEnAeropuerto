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
@Table(name = "trayecto")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Trayecto.findAll", query = "SELECT t FROM Trayecto t"),
    @NamedQuery(name = "Trayecto.findByIdTrayecto", query = "SELECT t FROM Trayecto t WHERE t.idTrayecto = :idTrayecto"),
    @NamedQuery(name = "Trayecto.findByHoraSalida", query = "SELECT t FROM Trayecto t WHERE t.horaSalida = :horaSalida"),
    @NamedQuery(name = "Trayecto.findByHoraLlegada", query = "SELECT t FROM Trayecto t WHERE t.horaLlegada = :horaLlegada")})
public class Trayecto implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "idTrayecto")
    private Integer idTrayecto;
    @Basic(optional = false)
    @NotNull
    @Column(name = "horaSalida")
    @Temporal(TemporalType.TIMESTAMP)
    private Date horaSalida;
    @Basic(optional = false)
    @NotNull
    @Column(name = "horaLlegada")
    @Temporal(TemporalType.TIMESTAMP)
    private Date horaLlegada;
    @JoinColumn(name = "origen", referencedColumnName = "idAeropuerto")
    @ManyToOne(optional = false)
    private Aeropuerto origen;
    @JoinColumn(name = "destino", referencedColumnName = "idAeropuerto")
    @ManyToOne(optional = false)
    private Aeropuerto destino;
    @JoinColumn(name = "viaje", referencedColumnName = "idViaje")
    @ManyToOne(optional = false)
    private Viaje viaje;

    public Trayecto() {
    }

    public Trayecto(Integer idTrayecto) {
        this.idTrayecto = idTrayecto;
    }

    public Trayecto(Integer idTrayecto, Date horaSalida, Date horaLlegada) {
        this.idTrayecto = idTrayecto;
        this.horaSalida = horaSalida;
        this.horaLlegada = horaLlegada;
    }

    public Integer getIdTrayecto() {
        return idTrayecto;
    }

    public void setIdTrayecto(Integer idTrayecto) {
        this.idTrayecto = idTrayecto;
    }

    public Date getHoraSalida() {
        return horaSalida;
    }

    public void setHoraSalida(Date horaSalida) {
        this.horaSalida = horaSalida;
    }

    public Date getHoraLlegada() {
        return horaLlegada;
    }

    public void setHoraLlegada(Date horaLlegada) {
        this.horaLlegada = horaLlegada;
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

    public Viaje getViaje() {
        return viaje;
    }

    public void setViaje(Viaje viaje) {
        this.viaje = viaje;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idTrayecto != null ? idTrayecto.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Trayecto)) {
            return false;
        }
        Trayecto other = (Trayecto) object;
        if ((this.idTrayecto == null && other.idTrayecto != null) || (this.idTrayecto != null && !this.idTrayecto.equals(other.idTrayecto))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.Trayecto[ idTrayecto=" + idTrayecto + " ]";
    }
    
}

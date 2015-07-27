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
@Table(name = "puertaembarque")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Puertaembarque.findAll", query = "SELECT p FROM Puertaembarque p"),
    @NamedQuery(name = "Puertaembarque.findByIdPuertaEmbarque", query = "SELECT p FROM Puertaembarque p WHERE p.idPuertaEmbarque = :idPuertaEmbarque"),
    @NamedQuery(name = "Puertaembarque.findByNombrePuertaEmbarque", query = "SELECT p FROM Puertaembarque p WHERE p.nombrePuertaEmbarque = :nombrePuertaEmbarque")})
public class Puertaembarque implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "idPuertaEmbarque")
    private Integer idPuertaEmbarque;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "nombrePuertaEmbarque")
    private String nombrePuertaEmbarque;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "puertaembarque")
    private Collection<Embarque> embarqueCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "puertaEmbarque")
    private Collection<Reserva> reservaCollection;

    public Puertaembarque() {
    }

    public Puertaembarque(Integer idPuertaEmbarque) {
        this.idPuertaEmbarque = idPuertaEmbarque;
    }

    public Puertaembarque(Integer idPuertaEmbarque, String nombrePuertaEmbarque) {
        this.idPuertaEmbarque = idPuertaEmbarque;
        this.nombrePuertaEmbarque = nombrePuertaEmbarque;
    }

    public Integer getIdPuertaEmbarque() {
        return idPuertaEmbarque;
    }

    public void setIdPuertaEmbarque(Integer idPuertaEmbarque) {
        this.idPuertaEmbarque = idPuertaEmbarque;
    }

    public String getNombrePuertaEmbarque() {
        return nombrePuertaEmbarque;
    }

    public void setNombrePuertaEmbarque(String nombrePuertaEmbarque) {
        this.nombrePuertaEmbarque = nombrePuertaEmbarque;
    }

    @XmlTransient
    public Collection<Embarque> getEmbarqueCollection() {
        return embarqueCollection;
    }

    public void setEmbarqueCollection(Collection<Embarque> embarqueCollection) {
        this.embarqueCollection = embarqueCollection;
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
        hash += (idPuertaEmbarque != null ? idPuertaEmbarque.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Puertaembarque)) {
            return false;
        }
        Puertaembarque other = (Puertaembarque) object;
        if ((this.idPuertaEmbarque == null && other.idPuertaEmbarque != null) || (this.idPuertaEmbarque != null && !this.idPuertaEmbarque.equals(other.idPuertaEmbarque))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.Puertaembarque[ idPuertaEmbarque=" + idPuertaEmbarque + " ]";
    }
    
}

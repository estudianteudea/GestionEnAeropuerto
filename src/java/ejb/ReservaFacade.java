/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import static javafx.scene.input.KeyCode.T;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.Predicate;
import model.Embarque;
import model.Reserva;
import model.Usuario;

/**
 *
 * @author Usuariox
 */
@Stateless
public class ReservaFacade extends AbstractFacade<Reserva> {
    @PersistenceContext(unitName = "GestionEnAeropuertoPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ReservaFacade() {
        super(Reserva.class);
    }
    
    public Object findUsuario(Object idUsuario) {
        int id = (Integer) idUsuario;
        javax.persistence.criteria.CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        javax.persistence.criteria.CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        javax.persistence.criteria.Root rt = cq.from(Reserva.class);
        javax.persistence.criteria.Root usuario = cq.from(Usuario.class);
        
        //List<Predicate> criteriaList = new ArrayList<>();
        
        Predicate predicado1 = cb.equal(usuario.get("idUsuario"), id );
        cq.select(usuario);
        cq.where(predicado1);
        javax.persistence.Query q = getEntityManager().createQuery(cq);
        Usuario findusuario = (Usuario)q.getSingleResult();
        
        //criteriaList.add(predicado1);
        Predicate predicado2 = cb.equal(rt.get("usuario"), findusuario);
        //criteriaList.add(predicado2);
        
        cq.select(rt);
        cq.where(predicado2);
        
        //cq.select(rt);
        //cq.where(cb.and(criteriaList.toArray(new Predicate[0])));
        q = getEntityManager().createQuery(cq);
        return q.getResultList();
    }
    
    
    // envia true si la hora de confirmar es media hora antes de la hora de cierre de puerta
    
    public boolean confirmarViaje(String reserva, Timestamp hora){
        int idReserva = Integer.parseInt(reserva);
        javax.persistence.criteria.CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        javax.persistence.criteria.CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();        
        javax.persistence.criteria.Root reserve = cq.from(Reserva.class);
        javax.persistence.criteria.Root embarque = cq.from(Embarque.class);
        
        List<Predicate> criteriaList = new ArrayList<>();
        
        Predicate predicado1 = cb.equal(reserve.get("idReserva"), idReserva);
        criteriaList.add(predicado1);
        Predicate predicado2 = cb.equal(reserve.get("puertaEmbarque").get("idPuertaEmbarque"), embarque.get("puertaembarque").get("idPuertaEmbarque"));
        criteriaList.add(predicado2);
        Predicate predicado3 = cb.equal(reserve.get("viaje").get("idViaje"), embarque.get("viaje1").get("idViaje"));
        criteriaList.add(predicado3);
        Predicate predicado4 = cb.greaterThan(embarque.get("horaCierre"), hora);
        
         cq.select(embarque);
        cq.where(cb.and(criteriaList.toArray(new Predicate[0])));        
        javax.persistence.Query q = getEntityManager().createQuery(cq);
        Embarque embarqueComparar = (Embarque) q.getSingleResult();
        
        
        long t = hora.getTime();
        Date horaComparar = new Date(t - 30*60*1000);
        
        Date horaReserva = embarqueComparar.getHoraCierre();
        System.out.println("horaReserva: "+horaReserva);
        if(!horaComparar.before(horaReserva)){
            
            /*Reserva r = find(idReserva);
            r.setConfirmacion(Boolean.FALSE);
            r.setFechaConfirmacion(hora);            
            edit(r);*/
            return true;
            
        }
        
        
        /*Iterator iterator = embarqueComparar.iterator();        
        while(iterator.hasNext()){
            Embarque embarqueI = (Embarque)iterator.next();
            if(hora.after(embarque.))
        }*/
        
        return false;
    }
}

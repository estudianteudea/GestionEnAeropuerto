/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb;

import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.Predicate;
import model.Aeropuerto;
import model.Viaje;

/**
 *
 * @author Usuariox
 */
@Stateless
public class ViajeFacade extends AbstractFacade<Viaje> {
    @PersistenceContext(unitName = "GestionEnAeropuertoPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ViajeFacade() {
        super(Viaje.class);
    }
    
    public Object findByOrigenDestino(Aeropuerto origen, Aeropuerto destino) {
       
        javax.persistence.criteria.CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        javax.persistence.criteria.CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        
        //Identificamos la tablas que vamos a utilizar en nuestro query
        javax.persistence.criteria.Root viajeRoot = cq.from(Viaje.class);        
        
        //creamos una lista que contendra los criterios de busqueda
        List<Predicate> criteriaList = new ArrayList<>();
        
        //condicion: todos los viajes que tengan el origen de un usuario en especifico
        Predicate predicado1 = cb.equal(viajeRoot.get("origen"), origen);
        criteriaList.add(predicado1);
        //condicion: todos los viajes que tengan el destino de un usuario en especifico
        Predicate predicado2 = cb.equal(viajeRoot.get("destino"), destino);
        criteriaList.add(predicado2);       
        
        //Pasamos las tablas para el select del query
        cq.select(viajeRoot);
        //añadimos las condiciones en los predicados
        cq.where(cb.and(criteriaList.toArray(new Predicate[0])));
        
        javax.persistence.Query q = getEntityManager().createQuery(cq);
        
        return q.getResultList();
    }
    
}

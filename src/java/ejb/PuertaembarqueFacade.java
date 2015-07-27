/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import model.Puertaembarque;

/**
 *
 * @author Usuariox
 */
@Stateless
public class PuertaembarqueFacade extends AbstractFacade<Puertaembarque> {
    @PersistenceContext(unitName = "GestionEnAeropuertoPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public PuertaembarqueFacade() {
        super(Puertaembarque.class);
    }
    
}

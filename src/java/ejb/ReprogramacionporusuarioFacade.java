/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import model.Reprogramacionporusuario;

/**
 *
 * @author Usuariox
 */
@Stateless
public class ReprogramacionporusuarioFacade extends AbstractFacade<Reprogramacionporusuario> {
    @PersistenceContext(unitName = "GestionEnAeropuertoPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ReprogramacionporusuarioFacade() {
        super(Reprogramacionporusuario.class);
    }
    
}

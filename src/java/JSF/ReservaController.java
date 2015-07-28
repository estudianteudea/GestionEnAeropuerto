package JSF;

import model.Reserva;
import JSF.util.JsfUtil;
import JSF.util.JsfUtil.PersistAction;
import ejb.ReservaFacade;
import ejb.UsuarioFacade;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import model.Usuario;
import model.Viaje;
import org.primefaces.context.RequestContext;
import org.primefaces.event.SelectEvent;
import org.primefaces.event.UnselectEvent;

@ManagedBean(name = "reservaController")
@SessionScoped
public class ReservaController implements Serializable {

    @EJB
    private ejb.ReservaFacade ejbFacade;
    private List<Reserva> items = null;
    private Reserva selected;
    private Object item = null;
    private Object findObject = null;
    private String findReserva = null;
    private Object findReservaObject = null;
    private boolean confirmarVuelo;    
    private static boolean btnReasignar = true;
    private static boolean btnConfirmarV = true;
    private static int IDRESERVA;

    public boolean isBtnReasignar() {
        return btnReasignar;
    }

    public void setBtnReasignar(boolean btnReasignar) {
        this.btnReasignar = btnReasignar;
    }
    
    public boolean isBtnConfirmarV() {
        return btnConfirmarV;
    }

    public void setBtnConfirmarV(boolean btnReasignar) {
        this.btnConfirmarV = btnReasignar;
    }

    public ReservaController() {
    }

    public Reserva getSelected() {
        return selected;
    }

    public void setSelected(Reserva selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private ReservaFacade getFacade() {
        return ejbFacade;
    }

    public Reserva prepareCreate() {
        selected = new Reserva();
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("ReservaCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("ReservaUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("ReservaDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    //Metodo para retornar una reserva en especifico
    public Object getFindReserva() {
        if(findReserva != null){            
            int id = (Integer)Integer.parseInt(findReserva);
            findReservaObject = getFacade().find(id);
        }        
        return findReservaObject;
    }

    //Metodo para indicar el ID de una reserva que sera buscada
    public void setFindReserva(String findReserva) {
        this.findReserva = findReserva;
    }

    //Metodo para averiguar si una reserva existe
    public void checkReserva(){
        int id = (Integer)Integer.parseInt(findReserva);
        findReservaObject = getFacade().find(id);
        RequestContext context = RequestContext.getCurrentInstance();
        Reserva reserva = (Reserva)findReservaObject;
        if(reserva.getConfirmacion() == false){
            context.openDialog("AlertDialog");
            //execute("PF('AlertDialog').show()");         
        }
        else{
            context.execute("List");
        }
    }
    
    public Object getItem(Object id) {
        item = getFacade().find(id);
        return item;
    }    

    public void setItemFound(String objeto) {
        findObject = Integer.parseInt(objeto);        
    }

    public Object getItemFound() {
        if (findObject != null) {
            item = getFacade().findUsuario(findObject);  
        }
        return item;
    }
    
    public void setConfirmarVuelo(String reserva){
        Date date = new java.util.Date();
        Timestamp timeStamp = new Timestamp(date.getTime());
        confirmarVuelo = getFacade().confirmarViaje(reserva, timeStamp); 
        if(confirmarVuelo){
            int idReserva = Integer.parseInt(reserva);
            Reserva r = getFacade().find(idReserva);
            r.setConfirmacion(Boolean.TRUE);
            r.setFechaConfirmacion(timeStamp);            
            getFacade().edit(r);
        }
        
    }
    
    public boolean getCofirmarVuelo(){
        return confirmarVuelo;
    }

    public List<Reserva> getItems() {
        if (items == null) {
            items = getFacade().findAll();
        }
        return items;
    }

    private void persist(PersistAction persistAction, String successMessage) {
        if (selected != null) {
            setEmbeddableKeys();
            try {
                if (persistAction != PersistAction.DELETE) {
                    getFacade().edit(selected);
                } else {
                    getFacade().remove(selected);
                }
                JsfUtil.addSuccessMessage(successMessage);
            } catch (EJBException ex) {
                String msg = "";
                Throwable cause = ex.getCause();
                if (cause != null) {
                    msg = cause.getLocalizedMessage();
                }
                if (msg.length() > 0) {
                    JsfUtil.addErrorMessage(msg);
                } else {
                    JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
                }
            } catch (Exception ex) {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
                JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            }
        }
    }

    public List<Reserva> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<Reserva> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    @FacesConverter(forClass = Reserva.class)
    public static class ReservaControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            ReservaController controller = (ReservaController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "reservaController");
            return controller.getFacade().find(getKey(value));
        }

        java.lang.Integer getKey(String value) {
            java.lang.Integer key;
            key = Integer.valueOf(value);
            return key;
        }

        String getStringKey(java.lang.Integer value) {
            StringBuilder sb = new StringBuilder();
            sb.append(value);
            return sb.toString();
        }

        @Override
        public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
            if (object == null) {
                return null;
            }
            if (object instanceof Reserva) {
                Reserva o = (Reserva) object;
                return getStringKey(o.getIdReserva());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), Reserva.class.getName()});
                return null;
            }
        }

    }
    
    
    public void onRowSelect(SelectEvent event) {
       
        
        int reserva = ((Reserva) event.getObject()).getIdReserva(); 
        IDRESERVA = ((Reserva) event.getObject()).getIdReserva(); 
        
        Date date = new java.util.Date();
        Timestamp timeStamp = new Timestamp(date.getTime());         
        boolean bandera=getFacade().confirmarViaje(Integer.toString(reserva),timeStamp);
        System.out.println(bandera);
        if(!bandera){
            ReservaController.btnReasignar=true; // se desactiva
            ReservaController.btnConfirmarV=false; // se desactiva
            
        }else{
            
            ReservaController.btnReasignar=false; // se desactiva
            ReservaController.btnConfirmarV=true; // se desactiva
            
        }
        
    }
 
    public void onRowUnselect(UnselectEvent event) {
        ((Reserva) event.getObject()).getUsuario();
        
      
    }
    public void dialogFindTrip(){
        RequestContext.getCurrentInstance().openDialog("DialogChooseTrip");
    }
    
    public void actualizarVuelo(Viaje idViaje){
        int idReserva = IDRESERVA;
        Reserva r = getFacade().find(idReserva);
        //r.setViaje(idViaje);
        r.setConfirmacion(true);            
        getFacade().edit(r);
        
    }
    
    

}

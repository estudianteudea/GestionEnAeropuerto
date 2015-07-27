package JSF;

import model.Viaje;
import JSF.util.JsfUtil;
import JSF.util.JsfUtil.PersistAction;
import ejb.ViajeFacade;

import java.io.Serializable;
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
import model.Aeropuerto;

@ManagedBean(name = "viajeController")
@SessionScoped
public class ViajeController implements Serializable {

    @EJB
    private ejb.ViajeFacade ejbFacade;
    private List<Viaje> items = null;
    private Viaje selected;
    private Object findTrip;
    private String viaje;
    private int idViaje = 0;

    public ViajeController() {
    }

    public Viaje getSelected() {
        return selected;
    }

    public void setSelected(Viaje selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private ViajeFacade getFacade() {
        return ejbFacade;
    }

    public Viaje prepareCreate() {
        selected = new Viaje();
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("ViajeCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("ViajeUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("ViajeDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public String getViaje() {
        return viaje;
    }

    public void setViaje(String viaje) {
        this.viaje = viaje;
        idViaje = Integer.parseInt(viaje);
    }

    public Object getFindTrip() { 
        
        idViaje=10001;
        if (idViaje != 0) {
            findTrip = getFacade().find(idViaje);
            Viaje viaje = (Viaje) findTrip;
            Aeropuerto origen = viaje.getOrigen();
            Aeropuerto destino = viaje.getDestino();
            findTrip = getFacade().findByOrigenDestino(origen, destino);
        }
        return findTrip;
    }

    public void setFindTrip(Object findTrip) {
        this.findTrip = findTrip;
    }

    public List<Viaje> getItems() {
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

    public List<Viaje> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<Viaje> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    @FacesConverter(forClass = Viaje.class)
    public static class ViajeControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            ViajeController controller = (ViajeController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "viajeController");
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
            if (object instanceof Viaje) {
                Viaje o = (Viaje) object;
                return getStringKey(o.getIdViaje());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), Viaje.class.getName()});
                return null;
            }
        }

    }

}

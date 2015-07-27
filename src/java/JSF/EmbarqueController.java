package JSF;

import model.Embarque;
import JSF.util.JsfUtil;
import JSF.util.JsfUtil.PersistAction;
import ejb.EmbarqueFacade;

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

@ManagedBean(name = "embarqueController")
@SessionScoped
public class EmbarqueController implements Serializable {

    @EJB
    private ejb.EmbarqueFacade ejbFacade;
    private List<Embarque> items = null;
    private Embarque selected;

    public EmbarqueController() {
    }

    public Embarque getSelected() {
        return selected;
    }

    public void setSelected(Embarque selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
        selected.getEmbarquePK().setPuertaEmbarque(selected.getPuertaembarque().getIdPuertaEmbarque());
        selected.getEmbarquePK().setViaje(selected.getViaje1().getIdViaje());
    }

    protected void initializeEmbeddableKey() {
        selected.setEmbarquePK(new model.EmbarquePK());
    }

    private EmbarqueFacade getFacade() {
        return ejbFacade;
    }

    public Embarque prepareCreate() {
        selected = new Embarque();
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("EmbarqueCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("EmbarqueUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("EmbarqueDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<Embarque> getItems() {
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

    public List<Embarque> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<Embarque> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    @FacesConverter(forClass = Embarque.class)
    public static class EmbarqueControllerConverter implements Converter {

        private static final String SEPARATOR = "#";
        private static final String SEPARATOR_ESCAPED = "\\#";

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            EmbarqueController controller = (EmbarqueController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "embarqueController");
            return controller.getFacade().find(getKey(value));
        }

        model.EmbarquePK getKey(String value) {
            model.EmbarquePK key;
            String values[] = value.split(SEPARATOR_ESCAPED);
            key = new model.EmbarquePK();
            key.setPuertaEmbarque(Integer.parseInt(values[0]));
            key.setViaje(Integer.parseInt(values[1]));
            return key;
        }

        String getStringKey(model.EmbarquePK value) {
            StringBuilder sb = new StringBuilder();
            sb.append(value.getPuertaEmbarque());
            sb.append(SEPARATOR);
            sb.append(value.getViaje());
            return sb.toString();
        }

        @Override
        public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
            if (object == null) {
                return null;
            }
            if (object instanceof Embarque) {
                Embarque o = (Embarque) object;
                return getStringKey(o.getEmbarquePK());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), Embarque.class.getName()});
                return null;
            }
        }

    }

}

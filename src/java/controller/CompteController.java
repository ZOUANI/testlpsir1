/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import bean.Compte;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import service.CompteFacade;

/**
 *
 * @author YOUNES
 */
@Named(value = "compteController")
@SessionScoped
public class CompteController implements Serializable {

    private Compte selected;
    private List<Compte> items;
    @EJB
    private CompteFacade ejbFacade;
    private Double montant;

    public void debiter() {
        ejbFacade.debiter(selected, montant);
        selected = null;
    }

    public void crediter() {
        ejbFacade.crediter(selected, montant);
        selected = null;
    }

    public String save() {
        ejbFacade.save(selected);
        selected = null;
        return "List";
    }

    /**
     * Creates a new instance of CompteController
     */
    public CompteController() {
    }

    public Compte getSelected() {
        if (selected == null) {
            selected = new Compte();
        }
        return selected;
    }

    public void setSelected(Compte selected) {
        this.selected = selected;
    }

    public List<Compte> getItems() {
        items = ejbFacade.findAll();
        return items;
    }

    public void setItems(List<Compte> items) {
        this.items = items;
    }

    public CompteFacade getEjbFacade() {
        return ejbFacade;
    }

    public void setEjbFacade(CompteFacade ejbFacade) {
        this.ejbFacade = ejbFacade;
    }

    public Double getMontant() {
        return montant;
    }

    public void setMontant(Double montant) {
        this.montant = montant;
    }

}

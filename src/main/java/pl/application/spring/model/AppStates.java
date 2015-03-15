/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package pl.application.spring.model;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author tomek
 */
@Entity
@Table(name = "APP_STATES", schema = "PUBLIC")
@NamedQueries({
    @NamedQuery(name = "AppStates.findAll", query = "SELECT a FROM AppStates a"),
    @NamedQuery(name = "AppStates.findById", query = "SELECT a FROM AppStates a WHERE a.id = :id"),
    @NamedQuery(name = "AppStates.findByStateName", query = "SELECT a FROM AppStates a WHERE a.stateName = :stateName")})
public class AppStates implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "STATE_NAME")
    private String stateName;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "stateId", fetch = FetchType.EAGER)
    private List<AppHistory> appHistoryList;

    public AppStates() {
    }

    public AppStates(Integer id) {
        this.id = id;
    }

    public AppStates(Integer id, String stateName) {
        this.id = id;
        this.stateName = stateName;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getStateName() {
        return stateName;
    }

    public void setStateName(String stateName) {
        this.stateName = stateName;
    }

    public List<AppHistory> getAppHistoryList() {
        return appHistoryList;
    }

    public void setAppHistoryList(List<AppHistory> appHistoryList) {
        this.appHistoryList = appHistoryList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof AppStates)) {
            return false;
        }
        AppStates other = (AppStates) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "pl.application.entities.AppStates[ id=" + id + " ]";
    }
    
}

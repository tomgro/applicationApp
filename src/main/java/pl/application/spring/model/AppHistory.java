/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package pl.application.spring.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author tomek
 */
// insert into app_history (state_id, application_id, reason, mod_date) values(2, 3, null, current_date)
@Entity
@Table(name = "APP_HISTORY", schema = "PUBLIC")
@NamedQueries({
    @NamedQuery(name = "AppHistory.findAll", query = "SELECT a FROM AppHistory a"),
    @NamedQuery(name = "AppHistory.findById", query = "SELECT a FROM AppHistory a WHERE a.id = :id"),
    @NamedQuery(name = "AppHistory.findByReason", query = "SELECT a FROM AppHistory a WHERE a.reason = :reason"),
    @NamedQuery(name = "AppHistory.findByModDate", query = "SELECT a FROM AppHistory a WHERE a.modDate = :modDate")})
public class AppHistory implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    @Column(name = "REASON")
    private String reason;
    @Basic(optional = false)
    @Column(name = "MOD_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date modDate;
    @Column(name = "NEW_CONTENT")
    private String newContent;
    @JoinColumn(name = "APPLICATION_ID", referencedColumnName = "ID")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Application applicationId;
    @JoinColumn(name = "STATE_ID", referencedColumnName = "ID")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private AppStates stateId;

    public AppHistory() {
    }

    public AppHistory(Integer id) {
        this.id = id;
    }

    public AppHistory(Integer id, Date modDate) {
        this.id = id;
        this.modDate = modDate;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public Date getModDate() {
        return modDate;
    }

    public void setModDate(Date modDate) {
        this.modDate = modDate;
    }

    public Application getApplicationId() {
        return applicationId;
    }

    public void setApplicationId(Application applicationId) {
        this.applicationId = applicationId;
    }

    public AppStates getStateId() {
        return stateId;
    }

    public void setStateId(AppStates stateId) {
        this.stateId = stateId;
    }

    public String getNewContent() {
        return newContent;
    }

    public void setNewContent(String newContent) {
        this.newContent = newContent;
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
        if (!(object instanceof AppHistory)) {
            return false;
        }
        AppHistory other = (AppHistory) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "pl.application.entities.AppHistory[ id=" + id + " ]";
    }
    
}

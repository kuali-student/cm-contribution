package org.kuali.student.lum.lu.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.kuali.student.core.entity.Type;

@Entity
@Table(name = "KSLU_STMT_TYPE_T")
public class LuStatementType extends Type<LuStatementTypeAttribute> {

	@ManyToMany
	@JoinTable(name = "KSLU_LU_STMT_TYPE_JN_LU_TYPE", joinColumns = @JoinColumn(name = "LU_STMT_TYPE_ID"), inverseJoinColumns = @JoinColumn(name = "LU_TYPE_ID"))
	private List<LuType> luTypes;
   
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "OWNER")
    private List<LuStatementTypeAttribute> attributes;
    
	public List<LuType> getLuTypes() {
		return luTypes;
	}

	public void setLuTypes(List<LuType> luTypes) {
		this.luTypes = luTypes;
	}

	public List<LuStatementTypeAttribute> getAttributes() {
        if(attributes==null){
            attributes = new ArrayList<LuStatementTypeAttribute>();
        }        
        return attributes;
    }

    public void setAttributes(List<LuStatementTypeAttribute> attributes) {
        this.attributes = attributes;
    }
}

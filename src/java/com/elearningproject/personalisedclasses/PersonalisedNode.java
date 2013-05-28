/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.elearningproject.personalisedclasses;


import java.io.Serializable;
import org.primefaces.model.DefaultTreeNode;
import org.primefaces.model.TreeNode;
 
/**
 * Extension of the {@link org.primefaces.model.DefaultTreeNode} class that
 * overrides the node type, and includes a data component.
 *
 * @see org.primefaces.model.DefaultTreeNode
 * @author John Yeary
 * @version 1.0
 */
public class PersonalisedNode extends DefaultTreeNode implements Serializable {
 
    private String nodetype;
    private Object entity;
    private PersonalisedNode parentPerso;

    public Object getEntity() {
        return entity;
    }

    public void setEntity(Object entity) {
        this.entity = entity;
    }

    public String getNodetype() {
        return nodetype;
    }

    public void setNodetype(String nodetype) {
        this.nodetype = nodetype;
    }

    public PersonalisedNode getParentPerso() {
        return parentPerso;
    }

    public void setParentPerso(PersonalisedNode parentPerso) {
        this.parentPerso = parentPerso;
    }
    

    public PersonalisedNode(Object data, TreeNode parent ,String type ) {
        
       
        super(data, parent);
        this.nodetype=type;
        
        
    }
    
      public PersonalisedNode(Object data, TreeNode parent ,String type , Object entity, PersonalisedNode parentPerso ) {
        
       
        super(data, parent);
        this.nodetype=type;
        this.entity=entity;
        this.parentPerso=parentPerso;
    }

          public PersonalisedNode(Object data, TreeNode parent ,String type , Object entity ) {
        
       
        super(data, parent);
        this.nodetype=type;
        this.entity=entity;
    }
    public PersonalisedNode(Object data, TreeNode parent) {
        super(data, parent);
    }


    
    

  
    
   
 
    
   
}
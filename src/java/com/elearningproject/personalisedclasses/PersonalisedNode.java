/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.elearningproject.personalisedclasses;
/*
import org.primefaces.model.DefaultTreeNode;
import org.primefaces.model.TreeNode;


public class PersonalisedNode extends DefaultTreeNode {
    

    private String nodetype;

    public PersonalisedNode(String type, Object data, TreeNode parent) {
        super(type, data, parent);
    }

    public PersonalisedNode(Object data, TreeNode parent, String nodetype ) {
        super(data, parent);
        setNodetype(nodetype);
    }

    public String getNodetype() {
        return nodetype;
    }
    
    
    public void setNodetype(String nodetype) {
        this.nodetype = nodetype;
    }

    public PersonalisedNode() {
        super();
    }
}
*/

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

    public String getNodetype() {
        return nodetype;
    }

    public void setNodetype(String nodetype) {
        this.nodetype = nodetype;
    }

    public PersonalisedNode(Object data, TreeNode parent ,String type ) {
        
       
        super(data, parent);
         System.out.println("im here with  "+ type);
        this.nodetype=type;
        
    }

    public PersonalisedNode(Object data, TreeNode parent) {
        super(data, parent);
    }
 
   
}
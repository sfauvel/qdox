package com.thoughtworks.qdox.model;

import java.util.Iterator;
import java.util.List;

public class DefaultJavaConstructor
    extends AbstractBaseMethod implements JavaConstructor
{

    public int compareTo( Object o )
    {
        // TODO Auto-generated method stub
        return 0;
    }

    public String getCodeBlock()
    {
        return getModelWriter().writeConstructor( this ).toString();
    }
    
    public String toString() {
        StringBuffer result = new StringBuffer();
        if(isPrivate()) {
            result.append("private ");
        }
        else if(isProtected()) {
            result.append("protected ");
        }
        else if(isPublic()) {
            result.append("public ");
        }
        if(getParentClass() != null) {
            result.append(getParentClass().getFullyQualifiedName());
        }
        result.append("(");
        for(int paramIndex=0;paramIndex<getParameters().size();paramIndex++) {
            if(paramIndex>0) {
                result.append(",");
            }
            String typeValue = getParameters().get(paramIndex).getType().getResolvedValue(getTypeParameters());
            result.append(typeValue);
        }
        result.append(")");
        if (exceptions.size() > 0) {
            result.append(" throws ");
            for (Iterator<Type> excIter = exceptions.iterator();excIter.hasNext();) {
                result.append(excIter.next().getValue());
                if(excIter.hasNext()) {
                    result.append(",");
                }
            }
        }
        return result.toString();
    }
    
    /* (non-Javadoc)
     * @see com.thoughtworks.qdox.model.JavaMethod#equals(java.lang.Object)
     */
    public boolean equals(Object obj) {
        if (this == obj) 
        {
            return true;
        }
        if (obj == null) 
        {
            return false;   
        }

        JavaConstructor c = (JavaConstructor) obj;
        if (!c.getName().equals(getName())) {
            return false;   
        }
        
        List<JavaParameter> myParams = getParameters();
        List<JavaParameter> otherParams = c.getParameters();
        if (otherParams.size() != myParams.size()) 
        {
            return false;   
        }
        for (int i = 0; i < myParams.size(); i++) 
        {
            if (!otherParams.get(i).equals(myParams.get(i)))
            {
                return false;
            }
        }
        return this.varArgs == c.isVarArgs();
    }

    public int hashCode() {
        int hashCode = getName().hashCode();
        hashCode *= getParameters().size();
        return hashCode;
    }
}

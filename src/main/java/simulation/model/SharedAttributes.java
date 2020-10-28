package simulation.model;

import db.data.ISharedAttributesFactory;

public class SharedAttributes {

    public SharedAttributes(){}

    public SharedAttributes(int id){
        setId(id);
    }

    public SharedAttributes(int id, ISharedAttributesFactory parentObjFactory) throws Exception {
        setId(id);
        parentObjFactory.loadParentObj(id, this);
    }

    private int id;

    private String name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isNull(String input){
        boolean isNull = false;
        if(input == null){
            isNull = true;
        }
        return isNull;
    }

    public boolean isNotNull(String input){
        boolean isNotNull = false;
        if(input != null){
            isNotNull = true;
        }
        return isNotNull;
    }

    public boolean isNotEmpty(String input){
        boolean isNotEmpty = false;
        if(input != null && input.trim() != ""){
            isNotEmpty = true;
        }
        return isNotEmpty;
    }

    public boolean validName(){
        boolean isValid = false;

        if(isNotNull(getName()) && isNotEmpty(getName())){
            isValid = true;
        }

        return isValid;
    }

}

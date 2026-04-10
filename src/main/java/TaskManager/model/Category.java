package TaskManager.model;

import java.util.Objects;

public class Category {
    private String name;

    public Category(String name){
        if(name == null || name.isBlank()){
            throw new IllegalArgumentException("Kategorie braucht einen Namen!");
        }
        this.name = name.trim();
    }

    public String getName(){
        return name;
    }

    public void setName(String name){
        if(name == null || name.isBlank()){
            throw new IllegalArgumentException("Kategorie braucht einen Namen!");
        }
        this.name = name.trim();
    }

    @Override
    public String toString(){
        return name;
    }

    @Override
    public boolean equals(Object o){
        if(this == o) return true;
        if(!(o instanceof Category category)) return false;
        return name.equalsIgnoreCase(category.name);
    }

    @Override
    public int hashCode(){
        return Objects.hash(name.toLowerCase());
    }
}

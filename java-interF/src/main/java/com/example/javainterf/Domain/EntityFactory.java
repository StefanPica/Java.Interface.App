package com.example.javainterf.Domain;

public class EntityFactory implements IEntityFactory<Entity> {
    @Override
    public String entityToString(Entity entity) {
        String output = new String(String.valueOf(entity.getId()));
        return output;
    }

    @Override
    public Entity StringToEntity(String line) {

        return null;
    }
}

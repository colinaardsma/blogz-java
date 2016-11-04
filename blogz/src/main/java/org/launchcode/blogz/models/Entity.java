package org.launchcode.blogz.models;

import java.util.ArrayList;
import java.util.Objects;

public abstract class Entity {
	private final int uid;
	private static ArrayList<Entity> entityList = new ArrayList<Entity>();

	public Entity() {
		this.uid = entityList.size() + 1;
		Entity.entityList.add(this);
	}
	
	public int getUID() {
		return this.uid;
	}
	
	public static ArrayList<Entity> getEntityList() {
//		System.out.println(entityList);
		return entityList;
	}
	
	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		
		if (o == null) {
			return false;
		}
		
		if (this.getClass() != o.getClass()) {
			return false;
		}
		
		Entity entity = (Entity) o;
		
		return Objects.equals(this.uid, entity.uid);
	}
	
}

package org.launchcode.blogz.models;

import javax.validation.constraints.NotNull;

@MappedSuperclass
public abstract class Entity {
	private int uid;
//	private static ArrayList<Entity> entityList = new ArrayList<Entity>();

//	public Entity() {
//		this.uid = entityList.size() + 1;
//		Entity.entityList.add(this);
//	}
	
	@Id
	@GeneratedValue
	@NotNull
	@Column(name = "uid", unique = true)
	public int getUID() {
		return this.uid;
	}
	
	protected void setUid(int uid) {
		this.uid = uid;
	}
	
//	public static ArrayList<Entity> getEntityList() {
////		System.out.println(entityList);
//		return entityList;
//	}
//	
//	@Override
//	public boolean equals(Object o) {
//		if (this == o) {
//			return true;
//		}
//		
//		if (o == null) {
//			return false;
//		}
//		
//		if (this.getClass() != o.getClass()) {
//			return false;
//		}
//		
//		Entity entity = (Entity) o;
//		
//		return Objects.equals(this.uid, entity.uid);
//	}
	
}

package fr.iagl.gamification.model;

import fr.iagl.gamification.mapper.MappingEnum;

public class MappingModel {
	
	public int i;
	
	public void setI() {
		i = 4;
	}
	
	public MappingModel map(MappingEnum mapper){
		return this;
	}
}

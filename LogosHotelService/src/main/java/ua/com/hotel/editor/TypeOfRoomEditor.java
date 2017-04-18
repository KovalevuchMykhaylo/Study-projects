package ua.com.hotel.editor;

import java.beans.PropertyEditorSupport;

import ua.com.hotel.entity.TypeOfRoom;
import ua.com.hotel.service.TypeOfRoomService;


public class TypeOfRoomEditor extends PropertyEditorSupport{
	
	private final TypeOfRoomService typeOfRoomService;

	
	public TypeOfRoomEditor(TypeOfRoomService typeOfRoomService) {
		this.typeOfRoomService = typeOfRoomService;
	}

	@Override
	public void setAsText(String text) throws IllegalArgumentException {
		TypeOfRoom typeOfRoom = typeOfRoomService.findOne(Long.valueOf(text));
		setValue(typeOfRoom);
	}

}
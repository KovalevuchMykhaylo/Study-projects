package ua.com.hotel.editor;

import java.beans.PropertyEditorSupport;

import ua.com.hotel.entity.TypeOfBathRoom;
import ua.com.hotel.service.TypeOfBathRoomService;

public class TypeOfBathRoomEditor extends PropertyEditorSupport{
	
	private final TypeOfBathRoomService typeOfBathRoomService;

	public TypeOfBathRoomEditor(TypeOfBathRoomService typeOfBathRoomService) {
		this.typeOfBathRoomService = typeOfBathRoomService;
	}

	@Override
	public void setAsText(String text) throws IllegalArgumentException {
		TypeOfBathRoom typeOfBathRoom = typeOfBathRoomService.findOne(Long.valueOf(text));
		setValue(typeOfBathRoom);
	}

}
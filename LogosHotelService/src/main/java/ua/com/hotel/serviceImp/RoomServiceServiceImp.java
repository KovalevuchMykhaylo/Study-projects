package ua.com.hotel.serviceImp;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import ua.com.hotel.dao.RoomServiceDao;
import ua.com.hotel.dto.filter.RoomServiceFilter;
import ua.com.hotel.dto.form.RoomServiceForm;
import ua.com.hotel.entity.HotelName;
import ua.com.hotel.entity.RoomService;
import ua.com.hotel.entity.TypeOfBathRoom;
import ua.com.hotel.entity.TypeOfRoom;
import ua.com.hotel.service.RoomServiceService;
import ua.com.hotel.service.FileWriter.Folder;
import ua.com.hotel.specification.RoomServiceSpecification;
import ua.com.hotel.service.FileWriter;

@Service
public class RoomServiceServiceImp implements RoomServiceService {
	@Autowired
	private RoomServiceDao roomServiceDao;
	@Autowired
	private FileWriter fileWriter;

	public void save(RoomServiceForm form) {
		RoomService entity = new RoomService();
		MultipartFile file = form.getFile();
		entity = roomServiceDao.saveAndFlush(entity);
		entity.setPrice(new BigDecimal(form.getPrice().replace(',', '.')));
		entity.setRoom(new Integer(form.getRoom()));
		entity.setRoomNumber(new Integer(form.getRoomNumber()));
//		entity.setVersion(new Integer(form.getVersion()));
		entity.setId(form.getId());
		entity.setHotelName(form.getHotelName());
		entity.setTypeOfBathRoom(form.getTypeOfBathRoom());
		entity.setTypeOfRoom(form.getTypeOfRoom());
		entity = roomServiceDao.saveAndFlush(entity);
		if(fileWriter.write(Folder.ROOMSERVICE, file, entity.getId())){
			entity.setVersion(form.getVersion()+1);
			roomServiceDao.save(entity);
		}
	}

	public List<RoomService> findAll() {
		return roomServiceDao.findAll();
	}

	public RoomService findOne(Long id) {
		return roomServiceDao.findOne(id);
	}

	public void delete(Long id) {
		roomServiceDao.delete(id);
	}

	@Override
	public List<RoomService> findByHotelNameId(Long id) {
		return roomServiceDao.findByHotelNameId(id);
	}

	@Override
	public RoomService findUnique(String price, String room, String roomNumber, HotelName hotelName,
			TypeOfBathRoom typeOfBathRoom, TypeOfRoom typeOfRoom) {
		return roomServiceDao.findUnique(new BigDecimal(price.replace(',', '.')), new Integer(room), new Integer(roomNumber), hotelName.getId(), typeOfBathRoom.getId(), typeOfRoom.getId());
	}

	@Override
	public RoomServiceForm finForm(Long id) {
		RoomService entity = findOne(id);
		RoomServiceForm form = new RoomServiceForm();
		form.setPrice(String.valueOf(form.getPrice()));
		form.setRoom(String.valueOf(form.getRoom()));
		form.setRoomNumber(String.valueOf(form.getRoomNumber()));
		form.setId(entity.getId());
		form.setHotelName(entity.getHotelName());
		form.setTypeOfBathRoom(entity.getTypeOfBathRoom());
		form.setTypeOfRoom(entity.getTypeOfRoom());
		return form;
	}

	@Override
	public List<RoomService> finRoomService() {
		return roomServiceDao.findAllRoomsServices();
	}

	@Override
	public Page<RoomService> findAll(Pageable pageable, RoomServiceFilter filter) {
		return roomServiceDao.findAll(new RoomServiceSpecification(filter), pageable);
	}

}

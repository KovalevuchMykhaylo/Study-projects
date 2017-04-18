package ua.com.hotel.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import ua.com.hotel.entity.TypeOfRoom;

public interface TypeOfRoomDao extends JpaRepository<TypeOfRoom, Long>, JpaSpecificationExecutor<TypeOfRoom> {
	TypeOfRoom findByType(String type);
}

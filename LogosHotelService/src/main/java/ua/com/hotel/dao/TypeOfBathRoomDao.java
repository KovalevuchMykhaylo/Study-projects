package ua.com.hotel.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import ua.com.hotel.entity.TypeOfBathRoom;

public interface TypeOfBathRoomDao extends JpaRepository<TypeOfBathRoom, Long>, JpaSpecificationExecutor<TypeOfBathRoom>{
	TypeOfBathRoom findByType (String type);
}

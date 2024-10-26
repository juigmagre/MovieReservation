package com.jimg.myalbatross.modules.reservation.application.mapper;

import com.jimg.myalbatross.modules.reservation.application.dto.ReservationResponse;
import com.jimg.myalbatross.modules.reservation.domain.entity.Reservation;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface ReservationMapper {
    ReservationResponse toReservationResponse(Reservation reservation);

    List<ReservationResponse> toReservationResponseList(List<Reservation> reservations);
}

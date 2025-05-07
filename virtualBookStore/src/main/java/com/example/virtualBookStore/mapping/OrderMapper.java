package com.example.virtualBookStore.mapping;

import com.example.virtualBookStore.DTO.orderDto.CreateOrderRequestDto;
import com.example.virtualBookStore.DTO.orderDto.OrderResponseDto;
import com.example.virtualBookStore.model.Order;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface OrderMapper {
    OrderResponseDto toOrderResponseDto(Order order);
}

package com.example.virtualBookStore.mapping;

import com.example.virtualBookStore.DTO.paymentLogDto.PaymentLogResponseDto;
import com.example.virtualBookStore.model.PaymentLog;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface PaymentLogMapper {
    PaymentLogResponseDto toPaymentLogResponseDto(PaymentLog log);


}

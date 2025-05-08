package com.example.virtualBookStore.mapping;

import com.example.virtualBookStore.DTO.orderDto.OrderResponseDto;
import com.example.virtualBookStore.DTO.paymentLogDto.PaymentLogResponseDto;
import com.example.virtualBookStore.model.Order;
import com.example.virtualBookStore.model.OrderItem;
import com.example.virtualBookStore.model.PaymentLog;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface OrderMapper {
    @Mapping(target = "id",source = "order.id")
    @Mapping(target = "status",source = "order.status")
    @Mapping(target = "totalAmount",source = "order.totalAmount")
    @Mapping(target = "createdAt",source = "order.createdAt")
    @Mapping(target = "items",source = "order.items")
    @Mapping(target = "payment",source = "order.paymentLog")
    OrderResponseDto toOrderResponseDto(Order order);

    List<OrderResponseDto.OrderItemDto> mapItems(List<OrderItem> items);
    @Mapping(target = "bookId",source = "book.id")
    @Mapping(target = "bookTitle",source = "book.title")
    @Mapping(target = "quantity",source = "quantity")
    @Mapping(target = "priceAtPurchase",source = "priceAtPurchase")
    OrderResponseDto.OrderItemDto toOrderItemDto(OrderItem item);

    @Mapping(target = "id",            source = "id")
    @Mapping(target = "paymentType",   source = "paymentType")
    @Mapping(target = "amount",        source = "amount")
    @Mapping(target = "status",        source = "status")
    @Mapping(target = "timestamp",     source = "timestamp")
    PaymentLogResponseDto toPaymentLogResponseDto(PaymentLog log);
}

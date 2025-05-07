package com.example.virtualBookStore.service;

import com.example.virtualBookStore.DTO.orderDto.CreateOrderRequestDto;
import com.example.virtualBookStore.DTO.orderDto.OrderResponseDto;
import com.example.virtualBookStore.enums.PaymentStatus;
import com.example.virtualBookStore.enums.Status;
import com.example.virtualBookStore.exceptions.NoSuchBookException;
import com.example.virtualBookStore.exceptions.NoSuchOrderException;
import com.example.virtualBookStore.mapping.OrderMapper;
import com.example.virtualBookStore.model.*;
import com.example.virtualBookStore.repository.BookRepository;
import com.example.virtualBookStore.repository.OrderRepository;
import com.example.virtualBookStore.service.payment.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final UserService userService;
    private final OrderMapper orderMapper;
    private final BookRepository bookRepository;
    private final PaymentService paymentService;

    public OrderResponseDto placeOrder(CreateOrderRequestDto orderRequestDto,String username){
        User user = userService.getUserByUsername(username);

        Order order = new Order();
        order.setUser(user);
        order.setStatus(Status.NEW);
        order.setCreatedAt(Instant.now());

        BigDecimal total = BigDecimal.ZERO;
        for (var item : orderRequestDto.getItems()) {
            Book book = bookRepository.findById(item.getBookId())
                    .orElseThrow(() -> new NoSuchBookException("Книга id=" + item.getBookId() + " не найдена"));
            OrderItem oi = new OrderItem();
            oi.setBook(book);
            oi.setOrder(order);
            oi.setQuantity(item.getQuantity());
            oi.setPriceAtPurchase(book.getPrice());
            order.getItems().add(oi);

            total = total.add(book.getPrice().multiply(BigDecimal.valueOf(item.getQuantity())));

            // уменьшаем склад
            book.setStock(book.getStock() - item.getQuantity());
            bookRepository.save(book);
        }
        order.setTotalAmount(total);

        Order savedOrder = orderRepository.save(order);

        // Оплата
        PaymentLog log = paymentService.pay(savedOrder, orderRequestDto.getPaymentType());
        savedOrder.setPaymentLog(log);
        savedOrder.setStatus(log.getStatus() == PaymentStatus.SUCCESS ? Status.PAID : Status.CANCELLED);
        orderRepository.save(savedOrder);

        return orderMapper.toOrderResponseDto(savedOrder);

    }

    public List<OrderResponseDto> listUserOrders(String username){
        User user = userService.getUserByUsername(username);
        List<Order> orders = orderRepository.findByUser_Id(user.getId());
        if (orders.isEmpty()) {
            throw new NoSuchOrderException("У пользователя нет заказов");
        }
        return orders.stream()
                .map(orderMapper::toOrderResponseDto)
                .toList();
    }

    public OrderResponseDto getOneOrder(Long orderId,String username){
        User user = userService.getUserByUsername(username);
        Order order = orderRepository.findById(orderId)
                .filter(o->o.getUser().getId().equals(user.getId()))
                .orElseThrow(()->new NoSuchOrderException("Заказ не найден или доступ запрещён"));
        return orderMapper.toOrderResponseDto(order);
    }
}

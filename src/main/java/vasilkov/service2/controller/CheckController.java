package vasilkov.service2.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vasilkov.service2.repo.OrderRepository;
@Deprecated
@RestController
@RequestMapping("/api/v2/orders/check")
@RequiredArgsConstructor
@Slf4j
public class CheckController {
    private final OrderRepository orderRepository;
    @PostMapping()
    public Boolean addNewOrder(@RequestBody Long orderId){
        return orderRepository.existsById(Math.toIntExact(orderId));
    }
}

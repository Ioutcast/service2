package vasilkov.service2.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vasilkov.service2.api.request.OrderCheckRequest;
import vasilkov.service2.domain.repository.OrderRepository;

import java.util.Map;


@RestController
@RequestMapping("/api/v2/orders/check")
@RequiredArgsConstructor
@Slf4j
public class CheckController {

    private final OrderRepository orderRepository;

    @PostMapping()
    public ResponseEntity<?> addNewOrder(@RequestBody OrderCheckRequest orderIdObj){
            return ResponseEntity.ok(orderRepository.existsById(orderIdObj));
    }
}

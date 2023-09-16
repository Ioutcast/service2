package vasilkov.service2;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

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

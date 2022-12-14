package com.jamiesandison.demo.car.api.controller;

import com.jamiesandison.demo.car.api.Controller.CarsController;
import com.jamiesandison.demo.car.api.Model.Car;
import com.jamiesandison.demo.car.api.service.CarService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Map;

@ExtendWith(MockitoExtension.class)
public class CarsControllerTest {

    @InjectMocks
    private CarsController controller; // sut = system under test

    @Mock
    private CarService carService;

    @Test
    void addCar_Returns_Response() {

        ResponseEntity<Map<String, String>> response = controller.addCars(List.of(
                new Car(
                        "Ford",
                        "mustang",
                        2022,
                        40000,
                        10,
                        "magenta")));

        Assertions.assertEquals(HttpStatus.CREATED, response.getStatusCode());
        Assertions.assertTrue(response.getBody().containsKey("description"));
        Assertions.assertEquals("Database updated", response.getBody().get("description"));

        Mockito.verify(carService, Mockito.times(1)).addCar(Mockito.anyList());
    }

}

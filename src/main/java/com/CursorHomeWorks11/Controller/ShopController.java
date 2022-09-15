package com.CursorHomeWorks11.Controller;

import com.CursorHomeWorks11.Entity.ShopDTO;
import com.CursorHomeWorks11.Entity.Shop;
import com.CursorHomeWorks11.Exception.ShopNotFoundException;
import com.CursorHomeWorks11.Service.ShopService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.PrintWriter;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@RestController
public class ShopController {

    private final ShopService shopService;

    @SneakyThrows
    @GetMapping("/shops")
    public void getShops(HttpServletRequest request, HttpServletResponse response) {
        PrintWriter writer = response.getWriter();
        shopService.getShopList().forEach(e -> writer.println(e.toString()));
        writer.flush();
    }

    @SneakyThrows
    @PostMapping("/create")
    public String addShop(HttpServletRequest request, HttpServletResponse response) {
        BufferedReader reader = request.getReader();
        String shopJson = reader.lines().collect(Collectors.joining());
        ObjectMapper objectMapper = new ObjectMapper();
        Shop shop = objectMapper.readValue(shopJson, Shop.class);
        shopService.createShop(shop);
        return "Created success: " + shop;
    }

    @SneakyThrows
    @PostMapping("/create/dto")
    public String addShopDTO(HttpServletRequest request, HttpServletResponse response) {
        BufferedReader reader = request.getReader();
        String shopJson = reader.lines().collect(Collectors.joining());
        ObjectMapper objectMapper = new ObjectMapper();
        ShopDTO shopDto = objectMapper.readValue(shopJson, ShopDTO.class);
        shopService.createShopDTO(shopDto);
        return "Created success: " + shopDto;
    }

    @GetMapping("/findById/{id}")
    public String getShop(@PathVariable Long id) {
        try {
            Shop shopFind = shopService.findByShopId(id);
            return "Found: " + shopFind;
        } catch (ShopNotFoundException exception) {
            return "Shop \"id=" + id + "\" no found";
        }
    }

    @DeleteMapping("/delete/{id}")
    public String deleteShops(@PathVariable Long id) {
        try {
            Shop shopDelete = shopService.findByShopId(id);
            shopService.deleteShop(id);
            return "Deleted: " + shopDelete;
        } catch (ShopNotFoundException exception) {
            return "Shop \"id=" + id + "\" no found";
        }
    }

    @PatchMapping("/update/{id}")
    public Object updateShop(@PathVariable("id") Long id, @RequestBody Shop shop) {
        try {
            Shop shopUpdate = shopService.findByShopId(id); //updateShop(shop, id);
            shopService.updateShop(shop, id);
            return "Updated: " + shopUpdate;
        } catch (ShopNotFoundException exception) {
            return "Shop \"id=" + id + "\" no found";
        }
    }
}
